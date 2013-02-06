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
package org.ect.convert.bpel2reo.transform.manager;

import java.util.TreeMap;
import java.util.logging.Level;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.framework.Bundle;

import org.ect.convert.bpel2reo.Activator;
import org.ect.convert.bpel2reo.util.PrimitivesManager;
import org.ect.reo.*;

public class ComponentLoader {

	static TreeMap<String, Connector> bufferConnector = new TreeMap<String, Connector>();
	static TreeMap<String, Component> bufferComponent = new TreeMap<String, Component>();

	public static Component LoadComponent(String name) {
		if (bufferComponent.containsKey(name))
			return (Component) EcoreUtil.copy(bufferComponent.get(name));
		else {
			Bundle bundle = Activator.getDefault().getBundle();//"cwi.bpel2reo"
			String resourcePath = bundle.getLocation().substring(15,
					bundle.getLocation().length() - 1)
					+ PrimitivesManager.getResourcePath(name);
			try {
				Module mod = Reo.loadModule(URI.createFileURI(resourcePath));
				if (mod == null)
					Activator.logger.log(Level.SEVERE, "Reo.loadModule failed on loading " + resourcePath);
				else if (mod.getComponents().size() == 1)
				{
					Activator.logger.log(Level.INFO, "Reo.loadModule " + resourcePath);
					bufferComponent.put(name, mod.getComponents().get(0));
					return mod.getComponents().get(0);
				}
				for (Component cmp : mod.getComponents())
					if (cmp.getName() != null && name.compareTo(cmp.getName()) == 0) {
						bufferComponent.put(name, cmp);
						return cmp;
					}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}