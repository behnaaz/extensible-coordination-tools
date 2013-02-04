/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * @generated NOT
 * @author Christian Krause
 *
 */
public class AutomataPlugin extends AbstractUIPlugin {

	/**
	 * Extensible automata plug-in ID.
	 * @deprecated Use EA.PLUGIN_ID
	 */ 
	public static final String ID = EA.PLUGIN_ID;
	
	/** 
	 * The supported extension point id.
	 * @deprecated Use EA.EXTENSION_POINT_ID
	 */
	public static final String EXTENSION_POINT_ID = "org.ect.ea.extensions";
	
	// Plug-in instance.
	private static AutomataPlugin instance;
		
	
	/**
	 * Default constructor.
	 */
	public AutomataPlugin() {
	}

	
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	
	public static AutomataPlugin getInstance() {
		return instance;
	}

	/**
	 * @deprecated Use {@link EA#getExtensionRegistry()}
	 */
	public IExtensionRegistry getExtensionRegistry() {
		return EA.getExtensionRegistry();
	}
	

	/**
	 * @deprecated Use {@link EA#getAutomatonTypeRegistry()}
	 */
	public IAutomatonTypeRegistry getTypeRegistry() {
		return EA.getAutomatonTypeRegistry();
	}
	
	
	
	// ***** Images ****************************** //

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path Path of the image.
	 * @return The descriptor for that image.
	 */
	public static ImageDescriptor getBundledImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
	}

	
	/**
	 * Returns an image for the image file at the given plug-in relative path.
	 * Client do not need to dispose this image. Images will be disposed automatically.
	 *
	 * @param path the path
	 * @return image instance
	 */
	public Image getBundledImage(String path) {
		Image image = getImageRegistry().get(path);
		if (image == null) {
			getImageRegistry().put(path, getBundledImageDescriptor(path));
			image = getImageRegistry().get(path);
		}
		return image;
	}


	
	// ***** Logging ****************************** //
	
	public void logError(String error) {
		logError(error, null);
	}

	
	public void logError(String error, Throwable throwable) {
		if (error == null && throwable != null) {
			error = throwable.getMessage();
		}
		getLog().log(new Status(IStatus.ERROR, EA.PLUGIN_ID, IStatus.OK, error, throwable));
		debug(error, throwable);
	}


	public void logInfo(String message) {
		logInfo(message, null);
	}

	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		getLog().log(new Status(IStatus.INFO, EA.PLUGIN_ID, IStatus.OK, message, throwable));
		debug(message, throwable);
	}

	private void debug(String message, Throwable throwable) {
		if (!isDebugging()) return;
		if (message != null) {
			System.err.println(message);
		}
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}

}
