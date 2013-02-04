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
package org.ect.reo.mcrl2.conversion;

import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;

/**
 * @author Christian Krause
 * @generated NOT
 */
class ElementNames {

	/**
	 * Compute a nice name for an element.
	 */
	public static String getName(Connectable element, Connector connector, Network network) {
		
		String name = null;
		
		// Primitives:
		if (element instanceof Primitive) {	
			name = ((Primitive) element).getName();
			if (name==null) name = element.getClass().getSimpleName();
			
			if (connector!=null) {
				name = name + (connector.getPrimitives().indexOf(element)+1);
			}
			else if (network!=null) {
				name = name + (network.getAllPrimitives().indexOf(element)+1);
			}
		}
		
		// Nodes:
		else if (element instanceof Node) {
			name = "Node";
			if (connector!=null) {
				name = name + (connector.getNodes().indexOf(element)+1);
			}
			else if (network!=null) {
				name = name + (network.getAllNodes().indexOf(element)+1);
			}
		} else {
			name = element.getClass().getSimpleName();
		}
		
		return name;
		
	}
	
}
