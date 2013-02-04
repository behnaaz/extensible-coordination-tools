/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.util;

import java.util.HashMap;

import org.ect.ea.automata.State;



/**
 * @generated NOT
 * @author Christian Krause
 * @deprecated Use {@link ProductCache}
 */
public class StateProductCache {

	private HashMap<State, HashMap<State,State>> cache;
	
	public StateProductCache() {
		cache = new HashMap<State, HashMap<State,State>>();
	}
	
	public State createProductState(State s1, State s2) {
		State product = new State();
		registerProductState(s1, s2, product);
		return product;
	}

	
	public void registerProductState(State s1, State s2, State product) {		
		initMap(s1);
		cache.get(s1).put(s2, product);
	}


	
	public State findProductState(State s1, State s2) {
		initMap(s1);
		return cache.get(s1).get(s2);
	}
	
	
	public void clear() {
		cache.clear();
	}
	
	
	private void initMap(State s1) {
		if (!cache.containsKey(s1)) {
			cache.put(s1, new HashMap<State, State>());
		}		
	}

}
