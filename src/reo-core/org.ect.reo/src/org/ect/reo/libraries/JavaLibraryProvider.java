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
package org.ect.reo.libraries;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.animation.AnimationPrinter;
import org.ect.reo.animation.AnimationSemanticsProvider;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.ColouringRefactoring;
import org.ect.reo.util.PrimitiveEndNames;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class JavaLibraryProvider implements LibraryProvider {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#createPrimitive(org.ect.reo.CustomPrimitive, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public CustomPrimitive createPrimitive(CustomPrimitive stub, IProgressMonitor monitor) {
		
		// Get and check the URI:
		URI uri = ReoLibraryUtil.getAbsoluteTypeURI(stub);
		if (uri==null || !uri.isPlatform() || !"java".equals(uri.fileExtension())) {
			return null;
		}
		
		// Get the Java file:
		String path = uri.toPlatformString(false);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile javaFile = root.getFile(new Path(path));
		if (!javaFile.exists()) return null;
		
		// Try to create a compilation unit out of it:
		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(javaFile);
		if (unit==null) return null;
		
		try {
			
			// Create parser instance.
			ASTParser parser= ASTParser.newParser(AST.JLS4);
			parser.setSource(unit);
			parser.setResolveBindings(true);
			ASTNode node= parser.createAST(null);
			
			Component result = (Component) EcoreUtil.copy(stub);
			result.disconnectEnds();
			
			PrimitiveEndNames names = new PrimitiveEndNames();
			names.generate(result);
			
			JavaLibraryHelper helper = new JavaLibraryHelper(names);
			node.accept(helper);
			
			AnimationTable table = helper.getAnimationTable();
			AnimationSemanticsProvider.createAnimationSteps(table);
			ColouringRefactoring.resetTableNames(table);
			
			String definition = new AnimationPrinter(result).printAllTables(table);
			AnimationSemanticsProvider.setAnimationDefinition(result, definition);
			return result;
			
		}
		catch (Throwable t) {
			System.err.println(t);
		}
		
		return null;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#canCreatePrimitive(org.ect.reo.CustomPrimitive)
	 */
	public boolean canCreatePrimitive(CustomPrimitive stub) {
		return createPrimitive(stub, new NullProgressMonitor())!=null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#canCreateConnector(org.ect.reo.CustomPrimitive)
	 */
	public boolean canCreateConnector(CustomPrimitive stub) {
		// We cannot generate connectors.
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#createConnector(org.ect.reo.CustomPrimitive, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Connector createConnector(CustomPrimitive stub, IProgressMonitor monitor) {
		// We cannot generate connectors.
		return null;
	}

}
