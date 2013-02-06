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
package org.ect.codegen.ea2mc.actions;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;

public class TranslateAction implements IObjectActionDelegate {

	private Shell shell;
	private AutomatonEditPart editpart;
	private Automaton automaton;
	private Automaton translated;
	
	
	/**
	 * Constructor for this action.
	 */
	public TranslateAction() {
		super();
	}
	
	
	/**
	 * Do the translation.
	 */
	protected void doTranslation(IProgressMonitor monitor) {
		
		monitor.beginTask("Translate to MC", 3);
		
		// Get the module.
		Module module = automaton.getModule();
		
		// Do the translation.
		translated = new Automaton("Translated");
		
		QIAintoMC translation = new QIAintoMC(automaton);
		monitor.worked(1);
		
		translated = translation.compute();
		monitor.worked(1);
		if (monitor.isCanceled()) return;
		
		// Add it to the module.
		module.getAutomata().add(translated);
		monitor.worked(1);
		
		monitor.done();
		
	}

	
	/**
	 * Run this action.
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		Job job = new Job("Translation") {
		     protected IStatus run(IProgressMonitor monitor) {
		    	 Command command = getCommand();
		    	 DiagramCommandStack stack = (DiagramCommandStack) editpart.getViewer().getEditDomain().getCommandStack();
		    	 stack.execute(command, monitor);
		         return Status.OK_STATUS;
		     }
		};
		job.schedule();
		
	}
	
	
	/**
	 * Get the command that should be executed.
	 */
	protected Command getCommand() {
		
		CompoundCommand command = new CompoundCommand();
		command.add(new ICommandProxy(new AbstractTransactionalCommand(editpart.getEditingDomain(), "Translate to MC", null) {

			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				doTranslation(monitor);
				return null;
			}
			
		}));
		
		IGraphicalEditPart moduleEditpart = editpart;
		while (moduleEditpart.getParent()!=null && !(moduleEditpart instanceof ModuleEditPart)) {
			moduleEditpart = (IGraphicalEditPart) moduleEditpart.getParent();
		}
		
		command.add(new ICommandProxy(new ChangeExtensionsCommand("Update extensions", moduleEditpart) {

			@Override
			protected void performExtensionsChange(IProgressMonitor monitor)
					throws ExecutionException {
				// Do nothing.
			}
			
			@Override
			protected List<Automaton> getAutomataToUpdate() {
				List<Automaton> automata = new Vector<Automaton>();
				automata.add(translated);
				return automata;
			}
			
		}));
		command.add(new ICommandProxy(new LayoutCommand((ModuleEditPart) moduleEditpart)) );
		
		return command;

	}
			
	
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

		if (!(selection instanceof IStructuredSelection)) return;
		IStructuredSelection structured = (IStructuredSelection) selection;
		
		Object selected = structured.getFirstElement();
		if (selected instanceof AutomatonEditPart) {
			this.editpart = (AutomatonEditPart) selected;
			this.automaton = (Automaton) editpart.getNotationView().getElement();
		}
		
	}
	
	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}
	
	
	
	private class LayoutCommand extends DeferredLayoutCommand {
		
		private ModuleEditPart moduleEditPart;
		
		public LayoutCommand(ModuleEditPart editpart) {
			super(editpart.getEditingDomain(), null, editpart);
			this.moduleEditPart = editpart;
		}

		@SuppressWarnings("unchecked")
		protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
			
			AutomatonCompartmentEditPart compartment = null;
			
			// Find automaton editpart.
			for (int i=0; i<moduleEditPart.getChildren().size(); i++) {
				if (!(moduleEditPart.getChildren().get(i) instanceof AutomatonEditPart)) continue;
				AutomatonEditPart editpart = (AutomatonEditPart) moduleEditPart.getChildren().get(i);
				if (editpart.getNotationView().getElement()!=translated) continue;
				compartment = (AutomatonCompartmentEditPart) editpart.getChildren().get(1);
			}
			
			if (compartment==null) {
				return CommandResult.newOKCommandResult();
			}
			
			super.containerEP = compartment;
			super.viewAdapters = new Vector();
			
			for (Object child : compartment.getChildren()) {
				if (child instanceof StateEditPart) {
					super.viewAdapters.add(child);
				}
			}
			
			return super.doExecuteWithResult(progressMonitor, info);
			
		}
		
	}
}
