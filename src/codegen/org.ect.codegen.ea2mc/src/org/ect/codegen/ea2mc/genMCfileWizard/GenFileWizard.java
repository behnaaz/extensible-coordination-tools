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
package org.ect.codegen.ea2mc.genMCfileWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;

public class GenFileWizard extends BasicNewResourceWizard{
	private FileCreationPage newFilePage;
	private Automaton MC;
	
	public GenFileWizard(Automaton automaton){
		super();
		MC = automaton;
		setWindowTitle("Generate MC matrix or PRISM input file");
	}
	
	public void addPages(){
		newFilePage = new FileCreationPage("Save the input file to", getSelection());
        newFilePage.setAllowExistingResources(true);
        newFilePage.setFileName(MC.getName());
        addPage(newFilePage);
	}
	
	 /* (non-Javadoc)
     * Method declared on IWorkbenchWizard.
     */
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        super.init(workbench, currentSelection);
        setNeedsProgressMonitor(true);
    }
    
	@Override
	public boolean performFinish() {
		final String fileType = newFilePage.getFileType();
		Automaton automaton = EA.copyAutomaton(MC);
		Generation generation = new Generation(newFilePage,automaton,fileType);
		generation.compute();
		
        return true;
	}
}
