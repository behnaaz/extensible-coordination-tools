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
package org.ect.reo.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;


/**
 * @generated
 */
public class ReoNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 5010;

	/**
	 * @generated
	 */
	@Override
	public int category(Object element) {
		if (element instanceof ReoNavigatorItem) {
			ReoNavigatorItem item = (ReoNavigatorItem) element;
			return ReoVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
