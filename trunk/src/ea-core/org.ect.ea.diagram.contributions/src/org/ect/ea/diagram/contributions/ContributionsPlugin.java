package org.ect.ea.diagram.contributions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


public class ContributionsPlugin extends AbstractUIPlugin {
	
	// ID of this plugin.
	public static final String ID = "org.ect.ea.diagram.contributions";

	// Icons.
	public static final String ICON_ERROR = "icons/error_st_obj.gif";
	public static final String ICON_WARNING = "icons/warning_st_obj.gif";
	
	// Shared instance.
	private static ContributionsPlugin instance;

	
	/**
	 * Default constructor.
	 */
	public ContributionsPlugin() {
	}

	
	public void start(BundleContext context) throws Exception {
		super.start(context);	
		instance = this;
	}

	
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	
	public static ContributionsPlugin getInstance() {
		return instance;
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
		getLog().log(new Status(IStatus.ERROR, ContributionsPlugin.ID, IStatus.OK, error, throwable));
		debug(error, throwable);
	}


	public void logInfo(String message) {
		logInfo(message, null);
	}

	
	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		getLog().log(new Status(IStatus.INFO, ContributionsPlugin.ID, IStatus.OK, message, throwable));
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
