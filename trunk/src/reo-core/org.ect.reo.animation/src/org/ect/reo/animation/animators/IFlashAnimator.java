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
package org.ect.reo.animation.animators;

import java.net.URL;

import org.ect.reo.animation.folders.BasicAnimationFolder;



public interface IFlashAnimator extends IViewAnimator {
	
	/**
	 * Folder where the flash animation are generated.
	 */
	public BasicAnimationFolder getFolder();
	
	/**
	 * Get the URL of the index file.
	 */
	public URL getURL();
	
}
