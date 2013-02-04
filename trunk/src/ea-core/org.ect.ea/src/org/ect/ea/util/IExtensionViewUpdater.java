/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.util;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.extensions.IExtension;


/**
 * @author Christian Krause
 * @generated NOT
 */
public interface IExtensionViewUpdater {

	/**
	 * Update the view of an extension.
	 * @param extension Extension.
	 * @param view View of the extension or of the owner of the extension.
	 */
	public void updateExtensionView(IExtension extension, View view);

	/**
	 * Prepare the removal of an extension. This should usually delete the views
	 * associated to an extension.
	 * @param extension Extension.
	 * @param view View of the extension or of the owner of the extension.
	 */
	public void prepareExtensionRemoval(IExtension extension, View view);
	
}
