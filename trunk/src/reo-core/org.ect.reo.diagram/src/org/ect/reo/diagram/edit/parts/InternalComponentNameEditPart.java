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
package org.ect.reo.diagram.edit.parts;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;
import org.ect.reo.diagram.providers.ReoParserProvider;


/**
 * @generated NOT
 */
public class InternalComponentNameEditPart extends ComponentNameEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4018;

	/**
	 * @generated
	 */
	private IParser parser;

	/**
	 * @generated
	 */
	public InternalComponentNameEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	public IParser getParser() {
		if (parser == null) {
			parser = ReoParserProvider
					.getParser(
							ReoElementTypes.Component_2010,
							getParserElement(),
							ReoVisualIDRegistry
									.getType(org.ect.reo.diagram.edit.parts.InternalComponentNameEditPart.VISUAL_ID));
		}
		return parser;
	}

}
