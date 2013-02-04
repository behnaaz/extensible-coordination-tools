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
package org.ect.ea.util.text.serialize;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;

@SuppressWarnings("unchecked")
public abstract class EaVisitor<T> {
	public final String VISIT = "visit";
	public abstract T visit(State s);
	
	public abstract T visit(Transition t);
	
	public abstract T visit(Automaton a);
	
	protected void visit(IExtendible b){
		for (IExtension e: b.getExtensions())
			visit(e);				
	}
	
	protected T visit(Object o) {
		try {
			return (T) getMethod( o.getClass() ).invoke( this, o );
		} catch (Exception e) {
			throw new RuntimeException("unknown extension:"+ o.getClass(), e);
		}
	}
	
	protected Method getMethod(Class c) throws NoSuchMethodException {
		Class newc = c;
		Method m = null;
		// Try the superclasses
		while (m == null && newc != Object.class) 
			try {
				return getClass().getMethod(VISIT, newc);
			} catch (NoSuchMethodException e) {
				newc = newc.getSuperclass();
			}

		// Try the interfaces travesing them breadth-first. 
		List<Class> interfaces = new ArrayList<Class>(Arrays.asList(c.getInterfaces()));
		while (!interfaces.isEmpty()) {
			Class head = interfaces.remove(0);
			try {
//				System.out.println(head);
				return getClass().getMethod(VISIT, head);
			} catch (NoSuchMethodException e) {
				for (Class s: head.getInterfaces())				
					interfaces.add(s);
			}
		}
		throw new NoSuchMethodException("no visit() for:"+ c);
	}
}
