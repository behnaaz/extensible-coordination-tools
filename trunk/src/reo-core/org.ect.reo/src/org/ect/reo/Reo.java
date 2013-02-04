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
package org.ect.reo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * Static methods for handling Reo files and for logging.
 * @author Christian Krause
 * @generated NOT
 */
public class Reo {
	
	/**
	 *  File extension for Reo files.
	 */
	public static final String FILE_EXT = "reo";
	
	/**
	 * If the Reo model is used outside of Eclipse the packages
	 * have to be initialized before they can be used. This can
	 * be done by invoking this method.
	 */
	public static void initStandalone() {
		ReoPackage.init();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(FILE_EXT, new XMIResourceFactoryImpl());		
	}

	
	// ********************************************************** //
	// * Convenience methods for creating and loading Reo files * //
	// ********************************************************** //
	
	/**
	 * Create a new Reo file with a module inside. Note that this method does not create 
	 * an associated diagram for the module. This must be done explicitly afterwards.
	 * This method invokes {@link #createModule(URI, ResourceSet)} with a fresh resource set.
	 * @param uri File URI.
	 * @param resourceSet Resource set.
	 * @return The new module.
	 * @throws IOException On I/O errors.
	 */
	public static Module createModule(URI uri) throws IOException {
		return createModule(uri, new ResourceSetImpl());
	}
	
	/**
	 * Load a Reo file. Returns <code>null</code> if the module could not be loaded.
	 * This method invokes {@link #loadModule(URI, ResourceSet)} with a fresh resource set.
	 * @param uri URI of the Reo file.
	 * @return Reo module.
	 */
	public static Module loadModule(URI uri) {
		return loadModule(uri, new ResourceSetImpl());
	}
	
	/**
	 * Load all nameable objects from the given argument URI. The fragment in the URI is 
	 * used as target name. This method invokes {@link #loadNameables(URI, ResourceSet)}
	 * with a fresh resource set.
	 * @param uri URI of a Reo file with a non-empty fragment.
	 * @return All matched nameable objects.
	 */
	public static List<Nameable> loadNameables(URI uri) {
		return loadNameables(uri, new ResourceSetImpl());
	}
	
	
	// ****************************************** //
	// * Actual create, load and save operation * //
	// ****************************************** //
	
	/**
	 * Create a new Reo file with a module inside. Note that this method does not create 
	 * an associated diagram for the module. This must be done explicitly afterwards.
	 * @param uri File URI.
	 * @return The new module.
	 * @throws IOException On I/O errors.
	 */
	public static Module createModule(URI uri, ResourceSet resourceSet) throws IOException {
		
		// Create a resource for this file.
		Resource resource = resourceSet.createResource(uri);
		
		// Create the module and add it to the resource.
		Module module = ReoFactory.eINSTANCE.createModule();
		resource.getContents().add(module);
		
		// Save the resource.
		resource.save(Collections.EMPTY_MAP);
		return module;
		
	}
	
	/**
	 * Load a Reo file. Returns <code>null</code> if the module could not be loaded.
	 * This is a convenience method which catches exceptions and searches the
	 * module in the loaded resource.
	 * @param uri URI of the Reo file.
	 * @param resourceSet Resource set.
	 * @return Reo module.
	 */
	public static Module loadModule(URI uri, ResourceSet resourceSet) {		
		Resource resource;
		try {
			resource = resourceSet.getResource(uri.trimFragment(), true);
		} catch (Throwable t) {
			resource = null;
		}
		// Get the module in the resource.
		return (resource==null) ? null : findModule(resource);
		
	}
	
	/**
	 * Load all nameable objects from the given argument URI.
	 * The fragment in the URI is used as target name. If the
	 * fragment is <code>null</code> the method returns an empty
	 * list. Otherwise all found object with the given name are
	 * returned.
	 * @param uri URI of a Reo file with a non-empty fragment.
	 * @param resourceSet Resource set.
	 * @return All matched nameable objects.
	 */
	public static List<Nameable> loadNameables(URI uri, ResourceSet resourceSet) {
		
		// The fragment of the URI is the target name.
		List<Nameable> result = new ArrayList<Nameable>();
		String name = uri.fragment();
		if (name==null) return result;
		
		Resource resource;
		try {
			resource = resourceSet.getResource(uri.trimFragment(), true);
		} catch (Throwable t) {
			resource = null;
		}
		
		// Load nameables.
		if (resource!=null) {
			TreeIterator<EObject> contents = resource.getAllContents();
			while (contents.hasNext()) {
				EObject object = contents.next();
				
				// We do not want any transient objects!
				EObject container = object.eContainer();
				EStructuralFeature containment = object.eContainingFeature();
				if (container==null || (containment!=null && containment.isTransient())) {
					continue;
				}

				// Check the name.
				if (object instanceof Nameable && 
					name.equals(((Nameable) object).getName())) {
						result.add((Nameable) object);
				}
			}
		}
		
		// Done.
		return result;
		
	}
	
	/**
	 * Find the first module contained in a resource. This iterates
	 * over all top-level elements in the resource and returns
	 * the first module found, or <code>null</code> if none was found.
	 * @param resource Resource.
	 * @return Module.
	 */
	public static Module findModule(Resource resource) {
		
		// Iterate over all top-level elements.
		for (EObject item : resource.getContents()) {
			if (item instanceof Module) {
				return (Module) item;
			}
		}
		// Not found.
		return null;
		
	}
	
	/**
	 * Save a module to its currently associated resource.
	 * @param module Module to be saved.
	 * @throws IOException IO exception.
	 */
	public static void saveModule(Module module) throws IOException {
		module.eResource().save(Collections.EMPTY_MAP);
	}
	
	
	// *******************************
	// ***  Logging and debugging  ***
	// *******************************
	
	private static String lastMessage = null;
	
	public static void logInfo(String message) {
		log(IStatus.INFO, message, null);
	}

	public static void logWarning(String message) {
		log(IStatus.WARNING, message, null);
	}

	public static void logError(String message) {
		log(IStatus.ERROR, message, null);
	}

	public static void logError(String message, Throwable t) {
		log(IStatus.ERROR, message, t);
	}
	
	/*
	 * Private logging method.
	 */
	private static void log(int severity, String message, Throwable t) {
		
		// Check the message.
		if (message==null && t!=null) {
			message = t.getMessage();
		}
		
		// Don't print duplicate message.
		if (lastMessage!=null && lastMessage.equals(message)) return;
		lastMessage = message;
		
		if (ReoPlugin.getInstance()!=null && ReoPlugin.getInstance().getLog()!=null) {
			ReoPlugin.getInstance().getLog().log(new Status(severity, ReoPlugin.ID, IStatus.OK, message, t));
		}
		Reo.debug(message, t);
		
	}
	
	public static void debug(String message) {
		debug(message, null);
	}
	
	public static void debug(String message, Throwable t) {
		if (message!=null) System.out.println(message);
		if (t!=null) t.printStackTrace();
	}
	
}
