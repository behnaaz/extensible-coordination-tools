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
