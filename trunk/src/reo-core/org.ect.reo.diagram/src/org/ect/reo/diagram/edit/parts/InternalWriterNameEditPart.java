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
import org.ect.reo.Nameable;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;
import org.ect.reo.diagram.providers.ReoParserProvider;
import org.ect.reo.util.ReoNames;


/**
 * @generated NOT
 */
public class InternalWriterNameEditPart extends WriterNameEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4017;

	/**
	 * @generated
	 */
	private IParser parser;

	/**
	 * @generated
	 */
	public InternalWriterNameEditPart(View view) {
		super(view);
	}

	/**
	 * Override the default display name.
	 * @generated NOT
	 */
	protected String getLabelText() {
		// Use the default names if the name is empty.
		return ReoNames.getName((Nameable) getParserElement());
	}

	/**
	 * @generated
	 */
	public IParser getParser() {
		if (parser == null) {
			parser = ReoParserProvider
					.getParser(
							ReoElementTypes.Writer_2009,
							getParserElement(),
							ReoVisualIDRegistry
									.getType(org.ect.reo.diagram.edit.parts.InternalWriterNameEditPart.VISUAL_ID));
		}
		return parser;
	}

}
