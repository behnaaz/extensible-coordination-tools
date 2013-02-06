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
package org.ect.codegen.ea2smt;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.ect.codegen.ICodeGenWizardPage;
import org.ect.codegen.IGenModel;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;


public class SMTFormulaGenPropertyWizardPage extends WizardPage implements
		ICodeGenWizardPage, SelectionListener {

	// List of all automata in this diagram.
	private List<Automaton> automata;

	//Button but1, but2, but3, but4;
	private Composite body; 
	private Tree tree;
	
	private String NO_STATE_SELECTED = "";
	static String STATE_SELECTED_SUFFIX = "_SELECTED_STATE";
	
	public SMTFormulaGenPropertyWizardPage() {
		super("Reachability property");

		setTitle("Reachability property");
		setDescription("Choose up to one state per automaton. Reachability is " +
				"checked for the product state.");
	}
	
	public void createControl(Composite parent) {
		
		body = new Composite(parent, SWT.FILL);
		body.setLayout(new GridLayout());

		tree = new Tree(body, SWT.CHECK | SWT.VIRTUAL);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		tree.addSelectionListener(this);
		
		setControl(body);
	}
	

	public void updateGenModel(IGenModel genModel) {
		automata = ((Automaton) genModel.getTarget()).getModule().getAutomata();

		for (Automaton auto: automata) {
			genModel.setProperty(auto.getName() + STATE_SELECTED_SUFFIX, NO_STATE_SELECTED);
		}
		
		//set the properties which states are selected
		for (TreeItem item: tree.getItems()) {
			for (TreeItem item2: item.getItems()) {
				if (item2.getChecked()) {
					genModel.setProperty(item.getText() + STATE_SELECTED_SUFFIX, item2.getText());
				}
			}
		}
	}
	
	public void updatePage(IGenModel genModel) {
		
		tree.removeAll();

		//show states only for automata that have been selected on previous page
		automata = ((SMTFormulaGenProductWizardPage) this.getPreviousPage()).getSelection();
		
		//create one TreeItem per automaton
		for (Automaton auto: automata) {
			TreeItem item = new TreeItem(tree, SWT.NORMAL);
			item.setText(auto.getName());
			//create one TreeItem per state
			for (State s: auto.getStates()) {
				TreeItem sItem = new TreeItem(item, SWT.NORMAL);
				sItem.setText(s.getName());
			}
		}
	    widgetSelected(null);		
	}

	public void widgetDefaultSelected(SelectionEvent e) {}

	/** Called whenever an item in the tree is selected. This method ensures 
	 * that (1) the tree itself cannot be selected, and that (2) if one subitem
	 * in a tree is selected, all others are desected, i.e., at most one item 
	 * per subtree can be selected. */
	public void widgetSelected(SelectionEvent e) {
		//Cannot check 'whole automata'
		for (TreeItem item: tree.getItems()) {
			if (item.getChecked()) {
				item.setChecked(false);
			}
			if (!(e == null)) {
				for (TreeItem item2: item.getItems()) { 
					if (item2.equals((TreeItem)e.item)) {
						for (TreeItem item3: item.getItems()) {
							if (item3.equals(item2)) {
								item3.setChecked(!item3.getChecked()); //invert 'check status' of checked item
							} else {
								item3.setChecked(false); //uncheck all others
							}
						}
					}
				}
			}
		}
	}
}	