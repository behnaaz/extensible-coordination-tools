/**
 * Copyright (C) 2006  Christian Koehler, 
 * Centrum voor Wiskunde en Informatica (CWI)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */
package org.ect.ea.diagram.contributions.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.ect.ea.EA;
import org.ect.ea.IProductDefinition;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.contributions.commands.ComputeProductCommand;
import org.ect.ea.diagram.contributions.commands.LayoutNewAutomatonCommand;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;



public class ComputeProductWizard extends Wizard {
	
	protected ComputeProductWizardPage wizardPage;
	protected ModuleEditPart moduleEditPart;
	protected IProductDefinition definition;
	
	/**
	 * Constructor.
	 * @param definition Product definition to be used.
	 */
	public ComputeProductWizard(IProductDefinition definition) {
		super();
		this.wizardPage = new ComputeProductWizardPage();
		this.definition = definition;
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Uses default product definition.
	 */
	public ComputeProductWizard() {
		this(EA.getProductRegistry().getDefault());
	}

	
	public void addPages() {
		if (getPageCount()>0) return;
		addPage(wizardPage);
	}
	
	
	protected Command getCommand() {
		CompoundCommand command = new CompoundCommand();
		command.add(new ICommandProxy(new ComputeProductCommand(moduleEditPart, wizardPage.getSelection(), definition)) );
		command.add(new ICommandProxy(new LayoutNewAutomatonCommand(moduleEditPart, true)) );
		return command;
	}
	
	
	public boolean performFinish() {
		
		final Command command = getCommand();
		final DiagramCommandStack stack = (DiagramCommandStack) moduleEditPart.getViewer().getEditDomain().getCommandStack();
		
		try {
			getContainer().run(true, false, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					stack.execute(command, monitor);
				}
			});
		} catch (Exception e) {
			MessageDialog.openError(getShell(), "Error", e.toString());
		}
		
		return true;
	
	}
	
	
	public void init(ModuleEditPart moduleEditPart, IStructuredSelection selection) {
		
		Set<Automaton> automata = new HashSet<Automaton>();
		Set<Automaton> selected = new HashSet<Automaton>();
	
		if (selection==null || selection.isEmpty()) {
			this.moduleEditPart = moduleEditPart;
			Module module = (Module) moduleEditPart.getNotationView().getElement();
			automata.addAll(module.getAutomata());
		}
		else {
			
			for (Object item : selection.toList()) {
				
				if (item instanceof AutomatonEditPart) {
					AutomatonEditPart editpart = (AutomatonEditPart) item;
					Automaton automaton = (Automaton) editpart.getNotationView().getElement();
					Module module = automaton.getModule();
					selected.add(automaton);
					automata.addAll(module.getAutomata());
					item = editpart.getParent();
				}
				if (item instanceof ModuleEditPart) {
					this.moduleEditPart = (ModuleEditPart) item;
					Module module = (Module) moduleEditPart.getNotationView().getElement();
					automata.addAll(module.getAutomata());
				}
			
			}
		}
		
		// Sort the list of automata.
		List<Automaton> sorted = new Vector<Automaton>(automata);
		Collections.sort(sorted, new Comparator<Automaton>() {

			public int compare(Automaton o1, Automaton o2) {
				String n1 = o1.getName()!=null ? o1.getName() : "";
				String n2 = o2.getName()!=null ? o2.getName() : "";
				return n1.compareTo(n2);
			}
			
		});
		
		wizardPage.setAutomata(sorted);
		wizardPage.setSelection(new Vector<Automaton>(selected));
	}
	
}
