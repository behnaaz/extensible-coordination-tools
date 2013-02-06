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
package org.ect.codegen.reo2ea.transform;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.ea.automata.Automaton;
import org.ect.reo.Connectable;
import org.ect.reo.Node;

@SuppressWarnings("unchecked")
public class Transformation<T extends Connectable> implements ITransformation<T> {
	protected EndNamingPolicy endNames;	
	protected  Map<String, ITransformation>  delegates;	

	public void setEndNamingPolicy(EndNamingPolicy endNames) {
		this.endNames = endNames;
		if (delegates!=null)
			for (ITransformation top: delegates.values() )
				top.setEndNamingPolicy(endNames);
	}
	
	public void addDelegate (String key, ITransformation op) {
		if (delegates==null)
			 delegates 	= new HashMap<String, ITransformation>();
		
		op.setEndNamingPolicy(endNames);
		delegates.put(key, op);
	}	

	protected ITransformation getOperator(String className) 
	throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	 {
		ITransformation t = (ITransformation)	Class.forName(className).newInstance();
		t.setEndNamingPolicy(endNames);
		return t;
	}
	
	public Automaton transform(T primitive) throws TransformationException {
		Class<? extends Connectable> clazz = primitive.getClass();
		
		if (clazz.isAssignableFrom(Node.class)) { 
			String type = ((Node)primitive).getType().name();
			if (delegates.containsKey(type)) 
				return delegates.get(type).transform(primitive);
		}
		
		String canonicalName = clazz.getCanonicalName();		
		if (delegates.containsKey(canonicalName)) 
			return delegates.get(canonicalName).transform(primitive);
		
		for (EClass c: primitive.eClass().getEAllSuperTypes()) {
			String typeName = c.getInstanceTypeName();
			if (delegates.containsKey(typeName)) 
				return delegates.get(typeName).transform(primitive);			
		}
		
		Reo2EAPlugin.log("No transformation for " + primitive+ " found");
		return IDENITIY;
	}	
}
