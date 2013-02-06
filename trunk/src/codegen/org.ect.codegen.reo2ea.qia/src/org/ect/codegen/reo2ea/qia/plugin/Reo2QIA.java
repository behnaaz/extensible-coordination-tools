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
package org.ect.codegen.reo2ea.qia.plugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.plugin.ExtensionManager;
import org.ect.codegen.reo2ea.plugin.ExtensionManager.TransformExt;
import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.codegen.reo2ea.util.CAUtil;

import org.ect.ea.automata.Automaton;
import org.ect.reo.Connector;

/**
 * Nice facade to all the plugin's functionality
 * @author maraikar
 *
 */
public class Reo2QIA {
	
	public static boolean DEBUG = true;	
	
	private static final String DEF_PREFIX = "s";
	
	private static final String REO2EA = "org.ect.codegen.reo2ea.qia";
	
	public static final Composition composition; 
	
	static {
		TransformExt qiaTransform = new ExtensionManager().getExtensions().get(REO2EA);
		try {
			qiaTransform.create();
		} catch (CoreException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			composition = qiaTransform.composition;			
		}
	}
	
	Connector connector;
	
	public Reo2QIA(Connector c) throws Exception {			
		connector = c;
	}

	public Automaton getResult() throws TransformationException {
		Automaton qia = composition.compose(composition.transform(connector).automata, new NullProgressMonitor()) ;
		beautify(qia, "S");
		
//		setPortTypes(ca);		
		CAUtil.trim(qia);
		beautify(qia, null);
		
		return qia;
	}	
	

	public Automaton beautify(Automaton a, String statePrefix){				
		a.setName(connector.getName());
		
		if (null==statePrefix && null==connector.getName())
			statePrefix = DEF_PREFIX;
		else
			statePrefix = connector.getName().trim().toLowerCase().replaceAll("\\W", "");
		CAUtil.beautify(a, statePrefix);
		
		return a;
	}
	
}

