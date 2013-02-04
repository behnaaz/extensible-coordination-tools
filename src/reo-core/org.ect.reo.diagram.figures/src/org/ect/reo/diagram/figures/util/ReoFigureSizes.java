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
package org.ect.reo.diagram.figures.util;

import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;


/**
 * @generated NOT
 */
public class ReoFigureSizes {
	
	public static final Size READER = createSize(110, 50);
	
	public static final Size WRITER = READER;
	
	public static final Size CONNECTOR = createSize(240, 180);
	
	public static final Size SUBCONNECTOR = createSize(120, 90);
	
	public static final Size COMPONENT = createSize(160, 100);
	
	public static final Size NODE = createSize(16, 16);

	public static final Size SOURCE_END = createSize(14, 14);
	
	public static final Size SINK_END = SOURCE_END;
	
	/**
	 * Helper method for creating a Size object.
	 * @param width Width.
	 * @param height Height.
	 * @return The Size object.
	 */
	public static Size createSize(int width, int height) {
		Size size = NotationFactory.eINSTANCE.createSize();
		size.setWidth(width);
		size.setHeight(height);
		return size;
	}
}
