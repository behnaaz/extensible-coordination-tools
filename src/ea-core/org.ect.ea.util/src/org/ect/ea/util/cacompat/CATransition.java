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
package org.ect.ea.util.cacompat;

import static org.ect.ea.util.cacompat.CA.CONSTRAINTS_ID;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.operations.Normaliser;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider;
import org.ect.ea.util.cacompat.CA.PortType;

public class CATransition extends org.ect.ea.automata.Transition {
	public static final String SPACER="::";
	public static final String TRANSITION_PORTNAMES_ID = TransitionPortNamesProvider.EXTENSION_ID;

	private static IExtensionProvider portProvider = new TransitionPortNamesProvider();
	private static IExtensionProvider consProvider = new ConstraintExtensionProvider();
	
	public CATransition(TransitionPortNames portExtension, Constraint consExtension) {
		portExtension.setId(TRANSITION_PORTNAMES_ID);
		updateExtension(portExtension);
		consExtension.setId(CONSTRAINTS_ID);
		updateExtension(consExtension);
	}
	
	public CATransition() {
		setExtension(portProvider, TransitionPortNamesProvider.EXTENSION_ID);
		setExtension(consProvider, ConstraintExtensionProvider.EXTENSION_ID);		
	}
	
	public CATransition(org.ect.ea.automata.Transition transition) {
		this();
		
//		for (IExtension ext: transition.getExtensions())
//			updateExtension(
//					(IExtension)EcoreUtil.copy(ext));
		IExtension portNames = transition.findExtension(TRANSITION_PORTNAMES_ID);
		if (portNames==null)
			setExtension(portProvider, TransitionPortNamesProvider.EXTENSION_ID);
		else
			updateExtension((IExtension)EcoreUtil.copy(portNames));
		
		Constraint cons = (Constraint)transition.findExtension(CONSTRAINTS_ID);
		if (cons==null)
			setExtension(consProvider, ConstraintExtensionProvider.EXTENSION_ID);
		else
			updateExtension(new Normaliser().doSwitch(cons));		
	}

	/**
	 * Stupid fucking Java has no multiple inheritance or mixins so this is in 2 places
	 * @see CAState#setExtension(IExtensionProvider)
	 * @param provider
	 */
	void setExtension(IExtensionProvider provider, String id) {
		IExtension extension = provider.createDefaultExtension(this);
		extension.setId(id);
		updateExtension(extension);		
	}

	/**
	 * Get the port names from a transition.
	 */
	public TransitionPortNames getPortNames() {
		return (TransitionPortNames) findExtension(TRANSITION_PORTNAMES_ID);
	}

	
	/**
	 * Set the port names of a transition.
	 */
	public void setPortNames(TransitionPortNames portNames) {
		portNames.setId(TRANSITION_PORTNAMES_ID);
		updateExtension(portNames);
	}

	/**
	 * Get the constraint from a transition.
	 */
	public Constraint getConstraint() {
		return (Constraint) findExtension(CONSTRAINTS_ID);
	}

	/**
	 * Set the constraint of a transition.
	 */
	public void setConstraint(Constraint constraint) {
		constraint.setId(CONSTRAINTS_ID);
		updateExtension(constraint);
	}
	
	/**
	 * An unbelievably <em>UGLY</em> hack to get around duplicate port names.<br/>
	 * PRECONDITION: Automaton to which the transition belongs MUST have a PortNames Extension 
	 * @param transition
	 * @param portName
	 * @param type
	 */
	public void addPortName(String portName, PortType type) {
		if (!getPortNames().getValues().contains(portName))
			getPortNames().getValues().add(portName);
		
		CA automaton = (CA) getAutomaton();
		if (automaton!=null && 
				!automaton.getPortNames().getValues().contains(portName)) {
			automaton.getPortNames().getValues().add(portName);
			automaton.setPortType(portName, type);
		}
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
//		buf.append('\n').append(getSource().getName())
			buf.append("\n\t")
			.append(getPortNames().getValues())
			.append(SPACER).append(getConstraint())		
			.append(SPACER).append(getTarget().getName());

		return buf.toString();
	}
}
