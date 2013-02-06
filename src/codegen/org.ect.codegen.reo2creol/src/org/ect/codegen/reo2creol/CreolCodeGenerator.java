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
package org.ect.codegen.reo2creol;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gmf.runtime.notation.Node;
import org.ect.codegen.AbstractCodeGenerator;
import org.ect.codegen.IGenModel;
import org.ect.codegen.reo2ea.ca.Reo2CA;
import org.ect.ea.automata.Automaton;
import org.ect.reo.Connector;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;

public class CreolCodeGenerator  extends AbstractCodeGenerator{
	
	// Keys of the properties used in the genModel. These are the same as 
	// the ones defined in the plugin.xml (extension 'codeGenerators').
	public static final String PROPERTY_PROJECT_NAME = IGenModel.PROJECT_NAME;
	
	public static final String PLUGIN_ID = "org.ect.codegen.reo2creol";
		
	public void generateCode(IGenModel genModel, IProgressMonitor monitor)
			throws CoreException {
		
		monitor.beginTask("Generating code", 2);
		String projectName = genModel.getProperty(PROPERTY_PROJECT_NAME);
		
		IProject project = createProject(projectName, new SubProgressMonitor(monitor, 1));
		
		try {
			Connector connector = (Connector) genModel.getTarget();
			Automaton a =  new Reo2CA(connector).getResult();
			CreolTemplate template = new CreolTemplate();
			String result = template.generate(a);
			
			createFileFromString(project, a.getName()+".creol", result, new SubProgressMonitor(monitor, 1));
			
		} catch (Exception e) {
			throw new CoreException(new Status(Status.ERROR, PLUGIN_ID,e.getMessage(),e));
		}
				
		monitor.done();		
	}

	
	public void initGenModel(Object target, IGenModel genModel) 
	{
		Connector connector = (Connector) ((Node) 
				((ConnectorEditPart) target).getModel()).getElement();
		
		// The connector name is used as a default class name.
		String projectName = connector.getName()==null ? "connector" : connector.getName();		
		genModel.setProperty(IGenModel.PROJECT_NAME, projectName);	
		genModel.setTarget(connector);
	}

	public IStatus validateGenModel(IGenModel genModel) {
		return Status.OK_STATUS;
	}
	
    public static String capitalize(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
