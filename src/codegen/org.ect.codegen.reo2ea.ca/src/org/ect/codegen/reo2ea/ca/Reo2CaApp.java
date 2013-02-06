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
package org.ect.codegen.reo2ea.ca;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.ect.codegen.reo2ea.plugin.ExtensionManager;
import org.ect.codegen.reo2ea.util.XMIWrangler;

import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.Automaton;
import org.ect.reo.Connector;

public class Reo2CaApp implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
//		System.err.println("Current dir: "+new File(".").getCanonicalPath());
		
		String[] args = (String[]) context.getArguments()
		.get(IApplicationContext.APPLICATION_ARGS);
		if (args.length<2) {
			System.err.println("Usage: <connector.reo> <automaton.ea>");
			return IApplication.EXIT_OK;
		}
		
		XMIWrangler.saveAutomata(
				batchTransform(XMIWrangler.loadReo((args[0])), new NullProgressMonitor()), 
				args[1]);
		
		return IApplication.EXIT_OK;
	}

	
	public static org.ect.ea.automata.Module batchTransform(org.ect.reo.Module reoMod, IProgressMonitor monitor) {
		ExtensionManager.GUI = false;
		org.ect.ea.automata.Module transformed = AutomataFactory.eINSTANCE.createModule();
		long now = System.currentTimeMillis();
		
		for (Connector c : reoMod.getConnectors()) {
			monitor.setTaskName("Transforming connector "+c.getName());
			try {
				Automaton a = new Reo2CA(c).getResult();			
				transformed.getAutomata().add(a);
				monitor.worked(1);
//				System.out.println("Result "+a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.err.println("time " + (System.currentTimeMillis()-now) + "ms");

		return transformed;
	}

	public static Automaton batchTransform(Connector c, IProgressMonitor monitor) throws Exception {
		return new Reo2CA(c).getResult();
	}
	
	public void stop() {
		// TODO Auto-generated method stub
	}
}
