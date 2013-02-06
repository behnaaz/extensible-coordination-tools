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
package org.ect.codegen.ea.ui;

import java.util.ArrayList;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaConventions;
import org.ect.codegen.IGenModel;
import org.ect.codegen.JavaCodeGenerator;
import org.ect.codegen.ea.generator.CATDriver;
import org.ect.codegen.ea.generator.TemplateLoader;
import org.ect.codegen.reo2ea.ca.Reo2CA;

import org.ect.ea.automata.Automaton;
import org.ect.ea.util.text.serialize.CatPrinter;

import org.ect.reo.Connector;
import org.ect.reo.Primitive;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.Transform;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;


public class CaCodeGenerator extends JavaCodeGenerator{
	
	// The plug-in ID of the code generator.
	public static final String PLUGIN_ID = "org.ect.codegen.ea.ui";
	
	public static final String PROPERTY_BASE_PACKAGE = "package";
	public static final String PROPERTY_CONNECTOR_CLASS = "connectorClass";
	public static final String PROPERTY_FUNCTION_CLASS = "functionClass";
	public static final String PROPERTY_GENERATE_MAIN = "generateMain";
	public static final String PROPERTY_IMPORTS = "staticImports";

	private IGenModel genModel;
	private IProgressMonitor monitor;
	private IFolder baseFolder;

	private Connector connector;
	private  TemplateLoader tl;
	/**
	 * 
	 * @param imports 
	 * @return list of ports
	 * @throws CoreException
	 */
	private List<String>  genCoord(List<String> imports) throws CoreException {
		try { 
			// Create the Java file from a template.
			Automaton a =  new Reo2CA((Connector)genModel.getTarget()).getResult();
//			StringTemplateGroup catGroup = tl.getGroup("cat");
//			StringTemplate cat = catGroup.getInstanceOf("automaton");
//			cat.setAttribute("ca", a);

			CATDriver driver = new  CATDriver(tl);
			driver.getAttributes().putAll(genModel.getProperties());
			driver.setAttribute("staticImports", imports);
			String coordCode;
			String automaton = new CatPrinter().visit(a);
			coordCode = driver.generate(
					new ANTLRStringStream(automaton))
					.toString(80);
			//			System.err.println(cat.toString());
			//			System.err.println(a.toString());

			createFileFromString(baseFolder, genModel.getProperty(PROPERTY_CONNECTOR_CLASS)+".java", 
					coordCode, new SubProgressMonitor(monitor, 1));

			return (List<String>) driver.getAttributes().get("ports");
		} catch (Exception e) {
			throw new CoreException(new Status(Status.ERROR,PLUGIN_ID,"",e));
		}
	}
	/**
	 * 
	 * @return list of function names
	 * @throws CoreException
	 */
	private List<String>  genFuns() throws CoreException {
		List<String> funcs = new ArrayList<String>();
		
		for (Primitive p:connector.getPrimitives())
			if (p instanceof Filter) 
				funcs.add(
						((Filter)p).getExpression());
			else if (p instanceof Transform)  
				funcs.add(
						((Transform)p).getExpression());

		StringTemplateGroup mainGroup = tl.getGroup("component");
		StringTemplate funDefs = mainGroup.getInstanceOf("functionDefs");

		String name = genModel.getProperty(PROPERTY_FUNCTION_CLASS);
		funDefs.setAttributes(genModel.getProperties());
		funDefs.setAttribute("funcs", funcs);		

		createFileFromString(baseFolder, 
				name+".java", 
				funDefs.toString(), new SubProgressMonitor(monitor, 1));

		return funcs;
	}
	
	
	public void generateCode(IGenModel genModel, IProgressMonitor monitor)
			throws CoreException {
		this.genModel = genModel;
		this.monitor = monitor;		

		String projectName = genModel.getProperty(IGenModel.PROJECT_NAME);
		IJavaProject project = createJavaProject(projectName, new SubProgressMonitor(monitor, 1));
		// Add the runtime library.
		addLibrary(PLUGIN_ID, "runtime.jar", project, new SubProgressMonitor(monitor, 1));

		String basePackage = genModel.getProperty(PROPERTY_BASE_PACKAGE);
		baseFolder = createPackage(basePackage, project);
			

		try {
			tl = new TemplateLoader(Activator.instance.templateRoot);
		} catch (Exception e) {
			throw new CoreException(new Status(Status.ERROR,PLUGIN_ID,"",e));
		}
		
		monitor.beginTask("Generating Java code", 3);

		List<String> ports = genCoord(genFuns());
		
		String mainCode = new MainTemplate(connector, tl.getGroup("wiring"))
		.withCoordinator(genModel.getProperty(PROPERTY_CONNECTOR_CLASS))
		.withPorts(ports)
		.inPackage(basePackage)
		.toString();
		createFileFromString(baseFolder, "Main.java", mainCode, new SubProgressMonitor(monitor, 1));

		monitor.done();		
	}

	public void initGenModel(Object target, IGenModel genModel) 
	{
		connector = (Connector) ((Node) 
				((ConnectorEditPart) target).getModel()).getElement();
		
		// The connector name is used as a default class name.
		String projectName = connector.getName()==null ? "connector" : connector.getName();
		
		// Remove special characters for the class name.
		String className = projectName.replaceAll("\\W","");
		
		
		genModel.setProperty(IGenModel.PROJECT_NAME, projectName);
		String packageName = "connector"; 
//			connector.getPackage()==null || connector.getPackage().isEmpty()
//			? "connector": connector.getPackage();
		genModel.setProperty(PROPERTY_BASE_PACKAGE, packageName);
		genModel.setProperty(PROPERTY_CONNECTOR_CLASS, capitalize(className));
		genModel.setProperty(PROPERTY_FUNCTION_CLASS, "Functions");
		genModel.setProperty(PROPERTY_GENERATE_MAIN, "true");
	
		genModel.setTarget(connector);
	}

	public IStatus validateGenModel(IGenModel genModel) {
		// Extract genModel properties.
		String basePackage = genModel.getProperty(PROPERTY_BASE_PACKAGE);
		String javaClass = genModel.getProperty(PROPERTY_CONNECTOR_CLASS);
		
		// Check whether the class file name is ok.
		IStatus status = JavaConventions.validateCompilationUnitName(javaClass + ".java");
		if (!status.isOK()) return status;

		// Check whether the class name is not according to the conventions.
		// This includes a check for capital letter at the beginning.
		status = JavaConventions.validateJavaTypeName(javaClass);
		if (!status.isOK()) return status;
		
		// Validate the package name.
		status = JavaConventions.validatePackageName(basePackage);
		if (!status.isOK()) return status;

		return Status.OK_STATUS;
	}
	
    public static String capitalize(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
