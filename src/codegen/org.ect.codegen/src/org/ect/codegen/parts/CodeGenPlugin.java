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
package org.ect.codegen.parts;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


public class CodeGenPlugin extends AbstractUIPlugin {

	public static final String ID = "org.ect.codegen";

	public static final String CODEGEN_MENU_LABEL = "Generate code";	// Name of the context submenu..
	public static final String CODEGEN_MENU_PATH = "additions";			// Where the submenu appears.
	public static final String CODEGEN_MENU_ID = "codeGen";				// Id of the submenu.
	public static final String CODEGEN_MENU_GROUP = "default";			// Id of the default group in the submenu.

	public static final String CODEGEN_ACTION_ID = "org.ect.codegen.CodeGenerationAction";
	public static final String CODEGEN_ACTION_CLASS = "org.ect.codegen.wizards.GenerateCodeAction";
	public static final String CODEGEN_ACTION_MENUBAR_PATH = CODEGEN_MENU_ID + "/" + CODEGEN_MENU_GROUP;

	public static final String CODEGEN_WIZARD_IMAGE = "icons/codegen_wizard.png";

	private static CodeGenPlugin instance;
	private CodeGenExtensionRegistry extensionRegistry;
	
	
	/**
	 * Default constructor.
	 */
	public CodeGenPlugin() {
		extensionRegistry = new CodeGenExtensionRegistry();
	}

	
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
		extensionRegistry.init();				
	}

	
	public void stop(BundleContext context) throws Exception {
		extensionRegistry.dispose();
		instance = null;
		super.stop(context);
	}

	public static CodeGenPlugin getInstance() {
		return instance;
	}
	
	public CodeGenExtensionRegistry getExtensionRegistry() {
		return extensionRegistry;
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
		getLog().log(new Status(IStatus.ERROR, CodeGenPlugin.ID, IStatus.OK, error, throwable));
		debug(error, throwable);
	}


	public void logInfo(String message) {
		logInfo(message, null);
	}

	
	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		getLog().log(new Status(IStatus.INFO, CodeGenPlugin.ID, IStatus.OK, message, throwable));
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
