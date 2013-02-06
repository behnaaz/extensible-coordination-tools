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
package org.ect.ea.extensions.costs;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.Action;
import org.ect.ea.costs.CostsPackage;
import org.ect.ea.costs.algebras.AlgebrasPackage;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;

public class CostsMenuManager extends ActionMenuManager {
	
	public static final String ADD_TEXT = "Add Cost Algebra";
	public static final String REMOVE_TEXT = "Remove Cost Algebra";
	
	private static EList<EClass> algebraClasses = null;	
	
	
	/**
	 * Creates a new instance of the menu manager.
	 */
	public CostsMenuManager(boolean add, AutomatonEditPart selected) {

		super("costsMenu_" + add, new CostsMenuAction( add ? ADD_TEXT : REMOVE_TEXT), true);
		
		if (algebraClasses==null) {
			initAlgebraClasses();
		}
		
		for (EClass eclass : algebraClasses) {
			add(new CostsAction(selected, add, eclass));
		}
	
	}
	
	
	private void initAlgebraClasses() {
		
		algebraClasses = new BasicEList<EClass>();
		EClass costsAlgebraClass = CostsPackage.eINSTANCE.getCostsAlgebra();
		
		for (EClassifier classifier : AlgebrasPackage.eINSTANCE.getEClassifiers()) {
			if (!(classifier instanceof EClass)) continue;
			EClass eclass = (EClass) classifier;
			if (eclass.getEAllSuperTypes().contains( costsAlgebraClass )) {
				algebraClasses.add(eclass);
			}
		}

	}


	private static class CostsMenuAction extends Action {
		public CostsMenuAction(String name) {
			setText(name);
			setToolTipText(name);
		}
	}
	
}

