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
package org.ect.codegen.reo2ea.qia.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.qia.immediateAction.ImmediateAction;
import org.ect.codegen.reo2ea.qia.util.QIA;
import org.ect.codegen.reo2ea.qia.util.QIAUtil;
import org.ect.codegen.reo2ea.wizards.CheckboxPage;
import org.ect.codegen.reo2ea.wizards.EditorUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;

public class ImmediateActionWizard extends Wizard{
	private Automaton qia;
	private CheckboxPage immediatePage;
    private CheckboxPage optionsPage;
    private AutomatonEditPart automatonPart;
    
    public ImmediateActionWizard(AutomatonEditPart automatonPart) {
		super();
		this.automatonPart = automatonPart;
		qia = (Automaton) automatonPart.getNotationView().getElement();
	}
    
    @Override
	public void addPages() {
		immediatePage = new CheckboxPage("Choose mixed ports to make immediate actions");
		for (String id: QIA.getPortNames(qia).getValues())
			immediatePage.addOption(id, id, false, false);
		
		addPage(immediatePage);
		
		optionsPage = new CheckboxPage("Options");
		optionsPage.addOption("trim", "Remove unreachable states", true, false);
		addPage(optionsPage);
	}
    
	@Override
	public boolean performFinish() {
		WorkspaceModifyOperation op = 	
			new EditorUtil(qia.getModule(),	(ModuleEditPart) automatonPart.getParent()) {
			@Override
			protected void execute(IProgressMonitor monitor)
			throws CoreException, InvocationTargetException, InterruptedException {
				Automaton automaton = ImmediateAction.getResult(qia, immediatePage.getChoice(false));
				if (optionsPage.isSelected("trim"))
					QIAUtil.trim(automaton);

				List<Automaton> additions = new ArrayList<Automaton>();
				additions.add(automaton);
				
				try {
					persistModify(additions).execute(monitor, null);
					int size = modPart.getChildren().size();								
					layoutAll(modPart.getChildren().subList(size-1, size)).execute(monitor, null);
				} catch (ExecutionException e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		
        try {
            getContainer().run(false, false, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
    		MessageDialog.openError(getShell(), "Reo2EA Error", Reo2EAPlugin.handleError(e));
            return false;
        } 
		return true;
	}
}
