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
package org.ect.reo.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.progress.IProgressConstants;

/**
 * Reo perspective factory.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoPerspectiveFactory implements IPerspectiveFactory {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		
 		String editorArea = layout.getEditorArea();
		
 		// Left panel.
		IFolderLayout folder= layout.createFolder("left", IPageLayout.LEFT, (float)0.2, editorArea);
		folder.addView("org.eclipse.jdt.ui.PackageExplorer");
		folder.addView("org.eclipse.jdt.ui.TypeHierarchy");
		folder.addPlaceholder(IPageLayout.ID_RES_NAV);
		
		// Bottom.
		IFolderLayout outputfolder= layout.createFolder("bottom", IPageLayout.BOTTOM, (float)0.6, editorArea);
		outputfolder.addView("org.ect.reo.animation.parts.AnimationView");
		outputfolder.addView("org.ect.reo.stochastic.views.SimulationView");
		outputfolder.addView("org.ect.reo.distengine.ui.views.EnginesView");
		
		if (Platform.getBundle("org.ect.reo.modchk.ui")!=null) {
			outputfolder.addView("org.ect.reo.modchk.ui.ModelCheckerView");
		}
		if (Platform.getBundle("org.ect.reo.engine.ui")!=null) {
			outputfolder.addView("org.ect.reo.engine.ui.views.EnginesView");
		}
		
		outputfolder.addView(IPageLayout.ID_OUTLINE);
		outputfolder.addView(IPageLayout.ID_PROP_SHEET);
		outputfolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		outputfolder.addView("org.eclipse.ui.console.ConsoleView");
		
		outputfolder.addPlaceholder("org.eclipse.search.ui.views.SearchView");
		outputfolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		outputfolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
		
		// Action sets.
		layout.addActionSet("org.eclipse.debug.ui.launchActionSet");
		//layout.addActionSet("org.eclipse.jdt.ui.JavaActionSet");
		//layout.addActionSet("org.eclipse.jdt.ui.JavaElementCreationActionSet");
		
		
		// Shortcuts.
		
		layout.addShowViewShortcut("org.ect.reo.animation.parts.AnimationView");
		layout.addShowViewShortcut("org.ect.reo.stochastic.views.SimulationView");
		
		if (Platform.getBundle("org.ect.reo.modchk.ui")!=null) {
			layout.addShowViewShortcut("org.ect.reo.modchk.ui.ModelCheckerView");
		}
		
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);
		layout.addShowViewShortcut("org.eclipse.ui.console.ConsoleView");
		layout.addShowViewShortcut("org.eclipse.jdt.ui.PackageExplorer");
		layout.addShowViewShortcut("org.eclipse.jdt.ui.TypeHierarchy");
		
		
		// Creation wizards.
		layout.addNewWizardShortcut("org.ect.reo.diagram.part.ReoCreationWizardID");
		if (Platform.getBundle("org.ect.ea.diagram")!=null) {
			layout.addNewWizardShortcut("org.ect.ea.diagram.part.AutomataCreationWizardID");
		}		
		if (Platform.getBundle("org.ect.reo.modchk.ui")!=null) {
			layout.addNewWizardShortcut("org.ect.reo.modchk.ui.wizards.new_rsl_code");
			layout.addNewWizardShortcut("org.ect.reo.modchk.ui.wizards.new_carml_code");
		}
		//layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.JavaProjectWizard"); //$NON-NLS-1$
		//layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewPackageCreationWizard"); //$NON-NLS-1$
		//layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewClassCreationWizard"); //$NON-NLS-1$
		//layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewInterfaceCreationWizard"); //$NON-NLS-1$
		//layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewSourceFolderCreationWizard");	 //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$
		
	}
	
}
