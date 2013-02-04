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
package org.ect.ea;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;



/**
 * This type of extension provider is the most generic one.
 * It gives full control of how the extensions are integrated
 * in the graphical automata editor. Contributions to the
 * context menu are supported by default, but also other
 * integration methods are possible.
 * 
 * @generated NOT
 */
public interface ICustomExtensionProvider extends IExtensionProvider {	

	/**
	 * Update the view of an {@link IExtendible} that contains
	 * an extension that comes from this provider. This method
	 * should create or delete additional views, depending on
	 * the extension.
	 * 
	 * @param extension The extension to be used.
	 * @param view The view that is associated to the owner.
	 */
	public void updateExtensionView(IExtension extension, View view);
	
	
	/**
	 * This method is called before an extension is removed.
	 * This should do the necessary clean up, e.g. removing
	 * visual representations of the extension etc.
	 *  
	 * @param extension Extension to be removed from the model
	 * @param view The view that is associated to the owner.
	 */
	public void prepareExtensionRemoval(IExtension extension, View view);

}
