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
package org.ect.convert.bpel2reo.transform;

import org.ect.reo.Node;

public class ControlFlow {
	protected Node sNode, eNode;
	
	public Node getEnd() {
		return eNode;
	}

	public Node getStart() {
		return sNode;
	}
}
