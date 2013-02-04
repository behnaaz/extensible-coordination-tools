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
package org.ect.reo.animation.flash.figures;

import org.ect.reo.diagram.view.util.NetworkView;

/**
 * By default, figures are defined in the context of a connector only.
 * When a figure implements this interface it can be positioned also
 * correctly inside of a network.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public interface NetworkAwareFigure {
	
	/**
	 * Set the network view to be used.
	 */
	public void setNetworkView(NetworkView networkView);
	
}
