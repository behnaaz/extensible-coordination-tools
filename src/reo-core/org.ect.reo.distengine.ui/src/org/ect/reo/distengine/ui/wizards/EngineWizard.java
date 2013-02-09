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
/**
 * 
 */
package org.ect.reo.distengine.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.ect.reo.distengine.ui.views.EnginesView;


/**
 * @author proenca
 *
 */
public class EngineWizard extends Wizard implements INewWizard {

	EngineMainPage enginePage;
	EnginesView engView;
	
	public void setEngManager(EnginesView eng) {
		engView = eng;
	}
	
	// workbench selection when the wizard was started
	protected IStructuredSelection selection;

	// here should go stuff the wizards use...
	EngineModel model;
	protected boolean engineCompleted = false;

	// the workbench instance
	protected IWorkbench workbench;

	/**
	 * Constructor for EngineWizard.
	 */
	public EngineWizard() {
		super();
		model = new EngineModel();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	//@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		//if (selection != null && !selection.isEmpty()) {
			//Object obj = selection.getFirstElement();
			//if (obj  instanceof IFolder) {
			//	IFolder folder = (IFolder) obj;				
			//	if (folder.getName().equals("Discounts"))
			//		model.discounted = true;				
			//}
		//}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {
		enginePage = new EngineMainPage(workbench, selection);
		addPage(enginePage);
	}
	
	@Override
	public boolean performFinish() {
		//String summary = model.toString();
		//MessageDialog.openInformation(workbench.getActiveWorkbenchWindow().getShell(), 
		//	"Engine info", summary);
		engView.addEngine(model.name, model.ip, model.port);
		return true;
	}

	
	

}
