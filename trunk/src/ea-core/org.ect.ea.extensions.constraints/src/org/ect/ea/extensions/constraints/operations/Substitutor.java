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
package org.ect.ea.extensions.constraints.operations;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.ConstraintsSwitch;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.extensions.constraints.Function;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.Parameter;
import org.ect.ea.extensions.constraints.Predicate;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;

/**
 * Unifies Equations i.e. <pre>a=b & b=c => a=c</pre>
 */
public final class Substitutor extends ConstraintsSwitch<Constraint> {
	private Set<String> names;
	private Map<Parameter, Parameter> eqns = new HashMap<Parameter, Parameter> ();		
	private boolean remove;

	public Substitutor(Collection<String> names, boolean remove) {
		super();
		this.names = new HashSet<String>(names);
		this.remove = remove;
	}

	@Override
	public Constraint caseDisjunction(Disjunction con) {
		final Deque<Constraint> ret = new LinkedList<Constraint>();
		for (Constraint c : con.getMembers())
			ret.add(doSwitch(c));
		
		if (ret.isEmpty())
			return new Literal(true);
		else if (ret.size()==1)
			return ret.getFirst();
		else {
			Disjunction d = new Disjunction();
			d.getMembers().addAll(ret);
			return d;
		}
	}

	@Override
	public Constraint caseConjunction(Conjunction con) {				
		final Deque<Constraint> ret = new LinkedList<Constraint>();
		Set<String> done = new HashSet<String>(names);
		init(con);
		
		for (Constraint c : con.getMembers())
			if (c instanceof Equation) {
				Parameter left = ((Equation) c).getLeft();
				Parameter right = ((Equation) c).getRight();

				if (c instanceof Predicate) {
					ret.addFirst( new Predicate(findSub(left), findSub(right),
							((Predicate) c).getType()));					
				}
				else if (done.contains(left.toString()) ^ remove) {					
					ret.addLast( new Equation(
							(Parameter)EcoreUtil.copy(left),
							findSub(right)));
				}						
			} else
				throw new IllegalArgumentException("nested constraints unsupported "+c);
		
		if (ret.isEmpty())
			return new Literal(false);
		else if (ret.size()==1)
			return ret.getFirst();
		else {
			Conjunction c = new Conjunction();
			c.getMembers().addAll(ret);
			return c;
		}
	}

	private void init(Conjunction con) {
		for (Constraint c : con.getMembers()) 
			if (c.getClass()==Equation.class) { //ignore predicates
				Equation eq = (Equation) c;				
				eqns.put(eq.getLeft(), eq.getRight());
			}
	}
	
	private Parameter findSub(Parameter p) {
		Parameter sub = p;

		while (!names.contains(sub.toString()) ^ remove)	{
//			System.err.println(sub);
			sub = eqns.get(sub);
			if (sub==null || EcoreUtil.equals(p,sub))	//Cycle check
				throw new IllegalStateException("no substitution for "+p + " in "+eqns);
		}

		if (sub instanceof Function) {
			Function ret = new Function(sub.getValue());
			for (Parameter p1:((Function) sub).getParameters()) 
				ret.getParameters().add(findSub(p1));
			
			return ret;
		}
		
		return (Parameter)EcoreUtil.copy(sub);
	}
	
	@Override
	public Constraint caseConstraint(Constraint c) {
		return (Constraint)EcoreUtil.copy(c);
	}
	
	public static void main(String[] args) throws Exception {
		String[] constraints = new String[]{
				"F=X & Z=W & X=M & M=W & M=E",
				"F=X & Z=U & X=N & N=U & N=B",
				"C=$s.ac & xxx=C & B=xxx",
				"FD=xxx & FB=FD & B=FB & OUTPUT=B & $t.bc=B & D=$s.ad & xxx=D",
				"Y=B & B=A & A!=10 & A=X",
				"f(g(X))==true & X=Y",
				"AX=AP & S=X & AP=P & R=X & X=foo(AX)"
		};
		String [][] hide = new String [][] {
				{"X","Z","M","W"},
				{"X","Z","N","U"},
				{"xxx","C"},
				{"FB","FD","D","B","xxx"},
				{"A","B"},
				{"X"},
				{"AX","AP","X"}
		};
		String [][] keep = new String [][] {
				{"F","E"},
				{"F","B"},
				{"B","$s.ac"},
				{"OUTPUT","$s.ad","$t.bc"},
				{"X","Y","10"},
				{"Y","f"},
				{"P","Q","R","S"}
		};
		
		for (int i=0; i<constraints.length; i++){
			Constraint c = ConstraintParserHelper.parse(constraints[i]);
			System.out.println(c);
			Substitutor s1 = new Substitutor(Arrays.asList(hide[i]), true);
			System.out.println(	"\t"+s1.doSwitch( c));
			
//			Substitution s2 = new Substitution(Arrays.asList(keep[i]), false);
//			System.out.println(	"\t"+s2.perform( c));
		}
	}	
}
