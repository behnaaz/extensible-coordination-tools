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
package org.ect.reo.distengine.ui.views;

import org.ect.reo.Connector;
import org.ect.reo.Property;
import org.ect.reo.util.PropertyHelper;

/**
 * @author Christian Krause
 */
public class DeploymentProperties {
	
	public static String getIP(Connector connector) {
    	Property property = PropertyHelper.getFirst(connector, "ip");
		return (property==null) ? null : property.getValue();
	}
	
	public static void setIP(Connector connector, String ip) {
		if (ip==null || ip.equals("")) {
			PropertyHelper.removeAll(connector, "ip");
    	} else {
    		PropertyHelper.setOrAdd(connector, "ip", ip);
    	}
	}
	
}
