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
package org.ect.codegen.reo2mc.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.ect.codegen.reo2ea.qia.plugin.Reo2QiaApp;

import org.ect.ea.automata.Automaton;
import org.ect.codegen.ea2mc.actions.QIAintoMC;

public class BatchMCWizard extends Wizard{

	private FileCreationPage newfile;
	private org.ect.reo.Module circuits;
	private IStructuredSelection selection;
	private EList<Automaton> MC;
	
	public BatchMCWizard (org.ect.reo.Module module){
		super();
		this.circuits = module;
		setWindowTitle("Reo to Markov Chain batch convertor");
	}
	
	public void init(IWorkbench workbench, IStructuredSelection currentSelection){
		setNeedsProgressMonitor(true);
        selection = currentSelection;
	}
	
	public void addPage(){
		super.addPages();
		newfile = new FileCreationPage("Save MC to ",selection);
		addPage(newfile);
	}
	@Override
	public boolean performFinish() {
		final IPath path = newfile.getContainerFullPath().addTrailingSeparator()
		.append(newfile.getFileName());		

		final String fileType = newfile.getFileExtension();

		IRunnableWithProgress op = new  IRunnableWithProgress() {
		public void run(IProgressMonitor monitor)
		throws InvocationTargetException, InterruptedException {
			monitor.beginTask("Transforming connector:", circuits.getConnectors().size()+2);
			
			try {
				org.ect.ea.automata.Module	mod = Reo2QiaApp.batchTransform(circuits, monitor);
				for(Automaton automaton : mod.getAutomata()){
					QIAintoMC translation = new QIAintoMC(automaton);
					MC.add(translation.compute());
				}
				Generation generation = new Generation(newfile,MC.get(0),fileType);
				generation.compute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new InvocationTargetException(e);
			}
		} 
	};
	
	return true;
	}

}
