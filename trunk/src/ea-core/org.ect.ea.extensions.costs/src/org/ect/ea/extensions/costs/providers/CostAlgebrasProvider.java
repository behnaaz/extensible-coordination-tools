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
package org.ect.ea.extensions.costs.providers;

import java.text.ParseException;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.ICustomExtensionProvider;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.costs.CostsAlgebraExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;


public class CostAlgebrasProvider implements ITextualExtensionProvider, ICustomExtensionProvider {

	// The ID of the extensions.
	public static final String EXTENSION_ID = "cwi.ea.extensions.costAlgebras";
	
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Cost Algebras";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.AUTOMATA, new CostAlgebrasProvider());

	
	/**
	 * Create a new CostsAlgebraExtension.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new CostsAlgebraExtension();
	}
	
	/**
	 * Create silent extension. Only needed for transitions.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return null;
	}
	
	/**
	 * Not needed.
	 */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}
	
	
	public String printExtension(IExtension extension) {
		return extension.toString();
	}

	public String editExtension(IExtension extension) {
		return printExtension(extension);
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		throw new ParseException("Cannot parse a cost algebra.", 0);
	}
	
	
	/**
	 * Compute the product of to cost algebras / cost objects.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {

		EList<IExtension> result = new BasicEList<IExtension>();
		
		CostsAlgebraExtension algebras1 = (CostsAlgebraExtension) x1;
		CostsAlgebraExtension algebras2 = (CostsAlgebraExtension) x2;
					
		if (algebras1.equals(algebras2)) {
			result.add( (IExtension) EcoreUtil.copy(algebras1) );
		} else {
			result.add( new CostsAlgebraExtension() );
		}
		
		return result;
	}

	
	public IValidationResult validateExtension(IExtension x) {
		return ValidationResult.newOKResult();
	}
	
	
	/**
	 * Contribute to the context menu.
	 */
	public void contributeToContextMenu(IMenuManager menu, IGraphicalEditPart[] selection) {
		
		if (selection.length!=1) return;
		
		if (selection[0] instanceof AutomatonEditPart) {
			AutomatonEditPart selected = (AutomatonEditPart) selection[0];
			menu.add(new CostsMenuManager(true, selected));
			menu.add(new CostsMenuManager(false, selected));
		}	
	}
	
	
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkGreen;
	}

	public boolean isReadOnly(IExtension extension) {
		return true;
	}

	
	public IGraphicalEditPart createEditPart(View view) {
		// Not required.
		return null;
	}

	public void updateExtensionView(IExtension extension, View view) {
		// Do nothing.
	}
	
	public void prepareExtensionRemoval(IExtension extension, View view) {
		// Do nothing.
	}

}
