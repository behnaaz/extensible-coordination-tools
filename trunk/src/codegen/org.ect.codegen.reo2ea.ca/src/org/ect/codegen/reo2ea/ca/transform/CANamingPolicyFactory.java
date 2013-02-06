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
package org.ect.codegen.reo2ea.ca.transform;

import static org.ect.reo.NodeType.REPLICATE;
import static org.ect.reo.NodeType.ROUTE;
import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import java.util.ArrayList;
import java.util.List;

import org.ect.codegen.reo2ea.core.INamingPolicyFactory;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;

import org.ect.reo.Node;
import org.ect.reo.NodeType;
import org.ect.reo.PrimitiveEnd;

public class CANamingPolicyFactory implements INamingPolicyFactory{
	class CAPrimitveEndNames extends EndNamingPolicy {
		@Override
		protected void setName(PrimitiveEnd end) {
			Node node = end.getNode();
			NodeType nType = node.getType();
			int numSinks = node.getSinkEnds().size();
			int numSources = node.getSourceEnds().size();
			String newName = node2PortName(node);

			if ((nType==ROUTE && numSources>1) || (nType==REPLICATE && numSinks>1)) {
				//A "mixed node" so give each incident end a unique name
				String opp =  oppositesName(end);
				newName +=  opp;
			}
			cache.put(end, newName);
		}
		
		private String oppositesName(PrimitiveEnd end) {
			List<PrimitiveEnd> pes = end.getPrimitive().getAllEnds();
			//It's an I/O so just return name of the node it's attached to
			if (pes.size()==1)
				return "";

			List<PrimitiveEnd> copy = new ArrayList<PrimitiveEnd>(pes);			
			copy.remove(end);

			return node2PortName(copy.get(0).getNode());		
		}

	}

	public EndNamingPolicy newInstance() {
		return new CAPrimitveEndNames();
	}

}
