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

import org.ect.convert.bpel2reo.transform.manager.ComponentLoader;
import org.ect.convert.bpel2reo.util.PrimitivesManager;
import org.ect.convert.bpel2reo.util.PrimitivesManager.ComponentName;
import org.ect.reo.Component;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;

public class Variable {
	
	Component comp;
	String varibleName;

	public Variable(String name){
		varibleName = name; 
		comp = ComponentLoader.LoadComponent(ComponentName.Variable);
		comp.setName(varibleName);
	}
	
	public Component getComponent() {
	    return comp;		
	}

	public SourceEnd getRead(){return (SourceEnd) PrimitivesManager.getEnd(comp, "read");}
	
	public SinkEnd getWrite(){return (SinkEnd) PrimitivesManager.getEnd(comp, "write");}
	
	public String getName(){return varibleName;}
}
