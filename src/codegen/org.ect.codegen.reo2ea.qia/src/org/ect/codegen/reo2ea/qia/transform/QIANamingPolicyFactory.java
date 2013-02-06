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
package org.ect.codegen.reo2ea.qia.transform;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import java.util.HashMap;
import java.util.Map;

import org.ect.codegen.reo2ea.core.INamingPolicyFactory;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;

import org.ect.reo.Component;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;

public class QIANamingPolicyFactory implements INamingPolicyFactory {
	class QIAPrimitveEndNames extends EndNamingPolicy {
		private Map<Node,Integer> indices = new HashMap<Node, Integer>();
		@Override
		protected void setName(PrimitiveEnd end) {
			Node node = end.getNode();
			String newName = node2PortName(node); 
			if ((node.getSinkEnds().size()>1 || node.getSourceEnds().size()>1)
					&& !(end.getPrimitive() instanceof Component)) {
				int index = indices.containsKey(node) ? 
						indices.get(node) : 0;
				newName += index;
				indices.put(node, index+1);
			}
			
			cache.put(end, newName);
		}
	}

	public EndNamingPolicy newInstance() {
		return new QIAPrimitveEndNames();
	}
}
