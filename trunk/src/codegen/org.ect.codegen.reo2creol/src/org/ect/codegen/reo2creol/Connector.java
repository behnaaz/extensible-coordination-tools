/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.codegen.reo2creol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.extensions.constraints.Function;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.operations.Normaliser;
import org.ect.ea.extensions.portnames.AutomatonPortNames;

public class Connector {

	final static String READY = "ready_";
	final static String DATA = "d_";
	final static String STATE = "s_";
	/*No prefix is assumed for variable names to allow flexible constants in out-arguments*/

//	boolean unsupportedCE = false;
	boolean hasSource=false;
	boolean hasSink=false;
	boolean hasSignal=false;
	List<ConnectorEnd> connectorEndList;
	StringBuffer transitions; 
	StringBuffer varDecl;
	int transCount;
	Automaton automaton;
	
	private State transSource, transTarget;
	
	public Connector(Automaton automaton) {
		this.automaton = automaton;
		processAutomaton();
	}

	public String getInitialState(){
		// Assuming that there is only one start state.
		Iterator<State> states = automaton.getStates().iterator();
		while(states.hasNext()) {
			State s = states.next();
			if (CA.isStartState(s)) {
				return s.getName();
			}
		}
		return "No_Initial_State_Error";
	}
	
	private void processAutomaton() { 
		connectorEndList = new ArrayList<ConnectorEnd>();
		transitions = new StringBuffer(); 
		varDecl = new StringBuffer();

		AutomatonPortNames portNames = CA.getPortNames(automaton);
		processSources (portNames.getInPorts());
		processSinks (portNames.getOutPorts());
//		processSignals();
		processMemoryCells(automaton);
	
		// process transitions 
		transCount = 0;
		for (Transition currentTrans:automaton.getTransitions()) {		// calculate the count of transitions
			transCount++;
			transSource = currentTrans.getSource();
			transTarget = currentTrans.getTarget();
			
			StringBuffer assignments = new StringBuffer();
	
			transitions.append("\n\top transition"+transCount+" ==\n");
			transitions.append("\t\tawait (state = \""+STATE+currentTrans.getSource().getName()+"\"");
	
			// check if all participating ports are ready (and set them back to false)
	        for (String port : CA.getPortNames(currentTrans).getValues()){
	        	String name = ConnectorEnd.prefix(port);
				transitions.append("\n\t\t       and "+READY+name);
				assignments.append("\t\t"+READY+name+" := false;\n");
			}
			transitions.append(");\n");	// closing the "await" statement

			// TODO Data flow
//			Constraint c = CA.getConstraint(currentTrans);
//			List<Element> elements = c.getAllElements();
//			elements.get(0).

			assignments.append("\t\t"+processConstraint((Disjunction) new Normaliser().doSwitch(CA.getConstraint(currentTrans)))+"\n");
			
			// finalize the transition
			transitions.append(assignments);
			transitions.append("\t\tstate := \""+STATE+currentTrans.getTarget().getName()+"\" ;\n");
			transitions.append("\t\t! transition"+transCount+"\n");
		}
	}

	private String processConstraint( Object constraint ) {
		if (constraint instanceof Conjunction)
			return processConstraint((Conjunction)constraint);
		else if (constraint instanceof Function)
			return processConstraint((Function)constraint);
		else if (constraint instanceof Disjunction)
			return processConstraint((Disjunction)constraint);
		else if (constraint instanceof Literal)
			return processConstraint((Literal)constraint);
		else if (constraint instanceof Equation)
			return processConstraint((Equation)constraint);

		return "unknown_constraint";
	}

	private String processConstraint(Conjunction c) {
		StringBuffer result = new StringBuffer();
		result.append("(");
		for (int i=0; i<c.getMembers().size(); i++) {
			result.append(processConstraint(c.getMembers().get(i)));
			if (i!=c.getMembers().size()-1) result.append("; ");
		}
		result.append(")");
		return result.toString();
	}
	
	private String processConstraint(Function c) {
		throw new UnsupportedOperationException("Functions on constraints are not supported.");
	}
		
	private String processConstraint(Equation c) {
		return processElement((Element) c.getLeft()) + ":=" + processElement((Element) c.getRight());
	}
	
	private String processElement(Element el) {
		if (el.isIdentifier())
			return DATA+ConnectorEnd.prefix(el.getValue());
		else if (el.isIntegerValue() || el.isPrimitiveValue() || el.isStringValue())
			return el.getValue();
		else if(el.isSourceMemory())
			return transSource.getName()+"_"+el.getValue();
		else if(el.isTargetMemory())
			return transTarget.getName()+"_"+el.getValue();
		return "unknown_element";
	}

	private String processConstraint(Literal c) {
		return String.valueOf(c.getValue());
	}


	private String processConstraint(Disjunction c) {
		// TODO Auto-generated method stub
		StringBuffer result = new StringBuffer();
		result.append("(");
		for (int i=0; i<c.getMembers().size(); i++) {
			result.append(processConstraint(c.getMembers().get(i)));
			if (i!=c.getMembers().size()-1) result.append(" || ");
		}
		result.append(")");
		return result.toString();
	}

	private void processSinks(EList<String> sinkList) {
		// reset the static fields
		Sink.reset();
		Sink sink;
		if (!sinkList.isEmpty()) hasSink = true;
		for (String sinkName : sinkList) {
			sink = new Sink(sinkName);
			connectorEndList.add(sink);
			varDecl.append(sink.process());
		}
	}

	private void processSources(EList<String> sourceList) {
		// reset the static fields
		Source.reset();
		Source source;
		if (!sourceList.isEmpty()) hasSource = true;
		for (String srcName : sourceList) {
			source = new Source(srcName);
			connectorEndList.add(source);
			varDecl.append(source.process());
		}
	}

//	private void processSignals() {
//		// reset the static fields
//		Signal.reset();
//		// TODO Noting to be done because CA have no Signal ends!
//	}

	private void processMemoryCells(Automaton automaton) {
		for (State state : automaton.getStates()) {
			String prefix = state.getName();
			for(String cellName : CA.getMemoryCells(state).getValues())
				varDecl.append("\tvar "+prefix+"_"+cellName+" : Data\n");
		}

	}
}
