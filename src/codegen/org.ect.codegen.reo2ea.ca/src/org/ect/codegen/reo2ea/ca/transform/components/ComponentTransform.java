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
package org.ect.codegen.reo2ea.ca.transform.components;

import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.libraryprovider.AutomataLibraryProvider;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.CARefactoring;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.reo.Component;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;

public class ComponentTransform implements ITransformation<Component>{
//	private EndNamingPolicy endNames;

	public Automaton transform(Component primitive) throws TransformationException {
		try {
			Automaton a = AutomataLibraryProvider.loadTypeURI(primitive);
			if (a==null)
				return IDENITIY;
//		else	
			mapPortnames(a, primitive);
			return a;
		} catch (Exception e) {
			throw new TransformationException(e);
		}
/*		
		String str = PropertyHelper.getFirstValue((PropertyHolder) primitive, "CA");
		if (str==null)
			return ITransformation.IDENITIY;
		
		String[] semantics;
		if ((semantics = str.split(":")).length<2)
			throw new TransformationException("invalid CA semantics for " + primitive.getName());
		
		Module module;
		try {
			module = XMIWrangler.loadAutomata(
					URI.createPlatformResourceURI(semantics[0],  true));
		} catch (IOException e) {
			throw new TransformationException(e);
		}
		for (Automaton a: module.getAutomata()) 
			if (a.getName().equals(semantics[1])) {
				mapPortnames(a, primitive);
				return a;
			}

		throw new TransformationException("invalid CA semantics for " + primitive.getName());
*/	
		}

	private void mapPortnames(Automaton a, Component c) throws TransformationException {
		AutomatonPortNames ports = (AutomatonPortNames) 
			a.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		for (PrimitiveEnd pe : c.getAllEnds()) {
			int i = ports.getValues().indexOf(pe.getName());
			if (i<0)
				throw new TransformationException("cannot map ends in "+c.getName()+" to ports in "+a.getName());
			
			Node node = pe.getNode();
			if (node!=null) //skip disconnected ends
				CARefactoring.renamePortName(ports.getValues().get(i), 
						ReoUtil.node2PortName(node), a);
			
//			System.err.println("renamed "+ports.getValues().get(i) +
//					" to "+pe.getNode().getName());			
		}
	}

	public void setEndNamingPolicy(EndNamingPolicy endNames) {
//		this.endNames = endNames;		
	}
}
