/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.AutomatonMonitor;
import org.ect.ea.automata.Module;
import org.ect.ea.configuration.AutomatonTypeRegistry;
import org.ect.ea.configuration.ExtensionRegistry;
import org.ect.ea.configuration.ProductRegistry;
import org.ect.ea.extensions.ExtensionsPackage;



/**
 * @generated NOT
 * @author Christian Krause
 *
 */
public class EA {
		
	// File extension for EA files.
	public static final String FILE_EXT = "ea";
	
	// Extensible automata plug-in ID. 
	public static final String PLUGIN_ID = "org.ect.ea";
	
	// The supported extension point IDs.
	public static final String EXTENSION_POINT_ID = "org.ect.ea.extensions";
	public static final String PRODUCT_EXTENSION_POINT_ID = "org.ect.ea.products";
	
	// Automata extensions.
	private static ExtensionRegistry extensions;
	
	// Automata types.
	private static AutomatonTypeRegistry types;

	// Product definitions.
	private static ProductRegistry products;

	
	// Initialize the packages when the class is loaded.
	static {
		initStandalone();
	}
	
	/**
	 * If the EA model is used outside of Eclipse the packages
	 * have to be initialized before they can be used.
	 */
	public static void initStandalone() {
		
		AutomataPackage.init();
		ExtensionsPackage.init();
		NotationPackage.eINSTANCE.getDiagram();		
				
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(FILE_EXT, new XMIResourceFactoryImpl());

		extensions = new ExtensionRegistry();
		types = new AutomatonTypeRegistry();
		products = new ProductRegistry();
		
		// Carefully checking if Eclipse is running.
		if (EA.hasPlatform()) {
			org.eclipse.core.runtime.IExtensionRegistry registry = Platform.getExtensionRegistry();
			if (registry!=null) {
				IExtensionPoint point = registry.getExtensionPoint(EXTENSION_POINT_ID);
				if (point!=null) {
					extensions.load(point);
					types.load(point);
				}
				point = registry.getExtensionPoint(PRODUCT_EXTENSION_POINT_ID);
				if (point!=null) {
					products.load(point);
				}
			}
		} 
		
	}
	
	
	/**
	 * Check whether the Eclipse platform is present.
	 */
	public static boolean hasPlatform() {
		try {
			return AutomataPlugin.getInstance()!=null;
		} catch (Throwable e) {
			return false;
		}
	}
	
	public static IExtensionRegistry getExtensionRegistry() {
		return extensions;
	}
	
	public static IAutomatonTypeRegistry getAutomatonTypeRegistry() {
		return types;
	}
	
	public static IProductRegistry getProductRegistry() {
		return products;
	}

	
	/**
	 * Copy an automaton.
	 * @param automaton Automaton to be copied.
	 * @return The copy.
	 */
	public static Automaton copyAutomaton(Automaton automaton) {
		Automaton copy = (Automaton) EcoreUtil.copy(automaton);
		if (automaton.eAdapters().contains(MONITOR)) {
			monitorExtensions(copy, true);
		}
		return copy;
	}
	
	
	// ***** Loading and saving ************** //
	
	/**
	 * Load a EA file (*.ea). Return's <code>null</code> if the file was not found.
	 * @param uri Path of the file.
	 * @return Module containing the automata.
	 */
	public static Module load(URI fileURI) {
		
		// Demand load the resource for this file.
		Resource resource = null;
		try {
			resource = new ResourceSetImpl().getResource(fileURI, true);
		} catch (Throwable t) {
			return null;
		}
		
		// Make sure the loading was really successful.
		if (resource==null) return null;
		
		// Find the module in the resource.
		Module module = null;
		for (EObject item : resource.getContents()) {
			if (item instanceof Module) {
				module = (Module) item;
				break;
			}
		}
		
		// Resolve possible proxies.
		if (module!=null) {
			EcoreUtil.resolveAll(module);
		}
		
		return module;
		
	}
	
	
	/**
	 * Load a EA file (*.ea).
	 * @param path Path of the file.
	 * @return Module containing the automata.
	 */
	public static Module load(String path) {
		URI fileURI = URI.createFileURI(path);
		return load(fileURI);		
	}

	
	/**
	 * Save a module to its currently associated resource.
	 * @param module Module to be saved.
	 * @throws IOException IO exception.
	 */
	public static void save(Module module) throws IOException {
		module.eResource().save(Collections.EMPTY_MAP);
	}

	
	/**
	 * Create a EA file.
	 * @throws IOException On IO errors.
	 */
	public static Module create(URI fileURI) throws IOException {
		
		// Create a resource for this file.
		Resource resource = new ResourceSetImpl().createResource(fileURI);
		
		// Create the Module and a Diagram.
		Module module = AutomataFactory.eINSTANCE.createModule();
		Diagram diagram = createDiagram(module);
		
		// Add the module and the diagram.
		resource.getContents().add(module);
		resource.getContents().add(diagram);
		
		// Save the resource.
		resource.save(Collections.EMPTY_MAP);
		
		return module;
		
	}
	
	
	/**
	 * Create a EA file.
	 * @throws IOException On IO errors.
	 */
	public static Module create(String path) throws IOException {
		URI fileURI = URI.createFileURI(path);
		return create(fileURI);
	}
	
	
	/**
	 * Create a EA diagram.
	 * @param module EA module.
	 * @return Diagram for the module.
	 */
	public static Diagram createDiagram(Module module) {
		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
		diagram.setElement(module);
		diagram.setType("Automata");
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		return diagram;
	}
	
	
	// ***** Monitoring ****************************** //
	
	private final static AutomatonMonitor MONITOR = new AutomatonMonitor();
	
	public static void monitorExtensions(Automaton automaton, boolean monitor) {
		if (automaton==null) return;
		if (monitor) {
			MONITOR.updateExtensions(automaton);
			automaton.eAdapters().add(MONITOR);
		} else {
			automaton.eAdapters().remove(MONITOR);		
		}
	}
	
	
	
	// ***** Logging ****************************** //
	
	public static void logError(String error) {
		if (hasPlatform()) AutomataPlugin.getInstance().logError(error);
		else System.err.println(error);
	}

	
	public static void logError(String error, Throwable t) {
		if (hasPlatform()) AutomataPlugin.getInstance().logError(error, t);
		else {
			System.err.println(error);
			t.printStackTrace();
		}
	}


	public static void logInfo(String message) {
		if (hasPlatform()) AutomataPlugin.getInstance().logInfo(message);
		else System.out.println(message);
	}

	
	public static void logInfo(String message, Throwable t) {
		if (hasPlatform()) AutomataPlugin.getInstance().logInfo(message, t);
		else {
			System.out.println(message);
			t.printStackTrace();
		}
	}

}
