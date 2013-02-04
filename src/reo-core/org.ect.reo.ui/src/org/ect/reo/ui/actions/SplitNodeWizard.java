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
package org.ect.reo.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.ect.reo.Nameable;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.diagram.view.util.GenericViewUtil;
import org.ect.reo.diagram.view.util.ReoViewCreator;
import org.ect.reo.diagram.view.util.ReoViewFinder;
import org.ect.reo.util.ReoNames;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class SplitNodeWizard extends Wizard {
	
	private Node node;
	private List<PrimitiveEnd> ends;
	private TransactionalEditingDomain domain;
	private Diagram diagram;

	public SplitNodeWizard(Node node, TransactionalEditingDomain domain, Diagram diagram) {
		this.node = node;
		this.ends = new ArrayList<PrimitiveEnd>();
		this.domain = domain;
		this.diagram = diagram;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
    public void addPages() {
		addPage(new ChooseEndsPage());
    }

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			new AbstractTransactionalCommand(domain, "Split node", null) {
				@Override
				protected CommandResult doExecuteWithResult(
						IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					
					// Create the new node:
					Node split = new Node(node.getName());
					node.getConnector().getNodes().add(split);
					View splitView = ReoViewCreator.createNodeView(split, diagram);
					View nodeView = ReoViewFinder.findNodeView(node, diagram);
					if (nodeView!=null) {
						GenericViewUtil.copyLayout(nodeView, splitView);
						GenericViewUtil.move((org.eclipse.gmf.runtime.notation.Node) splitView, 20, 0);
					}			
					
					// Migrate channel ends:
					for (PrimitiveEnd end : ends) {
						JoinNodesAction.migrateEnd(end, split, diagram);
					}
					
					return CommandResult.newOKCommandResult();
				}
			}.execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			Reo.logError("Error splitting node", e);
			return false;
		}
		return true;
	}
	
	private class ChooseEndsPage extends WizardPage {
		
		protected ChooseEndsPage() {
			super("Split Node");
			setTitle("Split Node");
			setDescription("Choose primitive ends that should be migrated");
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
		 */
		public void createControl(Composite parent) {
			parent = new Composite(parent, SWT.NONE);
			parent.setLayout(new GridLayout(1, false));
			for (final PrimitiveEnd end : node.getAllEnds()) {
				final Button checkbox = new Button(parent, SWT.CHECK);
				String text = end.getClass().getSimpleName();
				if (end.getName()!=null) text = text + " '" + end.getName() + "'";
				if (end.getPrimitive() instanceof Nameable) {
					text = text + " at " + ReoNames.getName((Nameable) end.getPrimitive());
				} else if (end.getPrimitive()!=null) {
					text = text + " at " + end.getPrimitive().getName();					
				}
				checkbox.setText(text);
				checkbox.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent e) {
						if (checkbox.getSelection() && !ends.contains(end)) {
							ends.add(end);
						} else {
							ends.remove(end);
						}
					}
					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}
				});
			}
			setControl(parent);
		}
	}
}
