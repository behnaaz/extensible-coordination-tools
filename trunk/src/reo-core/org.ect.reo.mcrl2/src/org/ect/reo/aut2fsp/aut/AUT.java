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
package org.ect.reo.aut2fsp.aut;

import java.util.ArrayList;
/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class AUT {
	ArrayList<State> states;
	ArrayList<Transition> transitions;

	public AUT() {
		states = new ArrayList<State>();
		transitions = new ArrayList<Transition>();
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public State AddState(State state) {
		State res = state;
		boolean contain = false;
		for (State st : states)
			if (st.getName() == state.getName()) {
				contain = true;
				res = state;
			}
		if (!contain)
			states.add(state);
		return res;
	}

	public void AddTransition(Transition trns) {
		if (!transitions.contains(trns)) {
			transitions.add(trns);
		}
	}

	public ArrayList<Transition> getTransStringFrom(int src) {
		ArrayList<Transition> res = new ArrayList<Transition>();
		for (Transition trns : transitions) {
			if (trns.getSource().getName() == src)
				res.add(trns);
		}
		return res;
	}

}
