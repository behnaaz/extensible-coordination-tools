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
import java.util.Vector;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.ect.codegen.ICodeGenWizardPage;
import org.ect.codegen.IGenModel;
import org.ect.ea.automata.Automaton;


/** This class implements a code generation wizard page which allows to choose
 * the automata to generate the code for. */

public class SMTFormulaGenProductWizardPage extends WizardPage 
	implements ICodeGenWizardPage, SelectionListener {

	/* Apart from the last two methods (which need to be implemented from 
	 * ICodeGenWizardPage), the contents have mainly been copied from 
	 * ComputeProductWizardPage */
	
	// List of all automata in this diagram.
	private List<Automaton> automata;
	
	// List of selected automata in this diagram.
	//private List<Automaton> selection = new LinkedList<Automaton>();

	// Tree is used to display the automata.
	private Tree tree;
	
	// Name of the property holding the number of selected automata
	final static String SELECTION_SIZE = "selectionSize";
	
	// (Prefix of the) Names of the properties holding the names of selected automata
	final static String SELECTION_NAME = "selectedAutomaton";
	
	private boolean updated = false;
	
	/**
	 * Initialise Wizard Page.
	 * @param selection
	 */
	public SMTFormulaGenProductWizardPage() {
		super("Generate Formulas for one or more Automata");
		setTitle("Generate Formulas for One or more Automata");
		setDescription("Generate formulas for a number of automata.");
	}
	
	
	public void createControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 5;
		composite.setLayout(layout);
	
		Label label = new Label(composite, SWT.FILL);
		label.setText("Automata:  ");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		tree = new Tree(composite, SWT.CHECK | SWT.SINGLE | SWT.BORDER);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		updated = false;
		
		setControl(composite);
	}


	public void setAutomata(List<Automaton> automata) {
		this.automata = automata;
	}


	public List<Automaton> getAutomata() {
		return automata;
	}

	
//	public void setSelection(List<Automaton> selection) {
//		this.selection = selection;
//	}

	
	public List<Automaton> getSelection() {
		List<Automaton> result = new Vector<Automaton>();
		for (int i=0; i<tree.getItemCount(); i++) {
			if (tree.getItem(i).getChecked()) result.add(automata.get(i));
		}
		return result;
	}

	
	// ----- Listen to selection events in the tree ----- //
	
	public void widgetSelected(SelectionEvent e) {
		if (getSelection().size()<1) {
			setErrorMessage("Please select one or more automata.");
			setPageComplete(false);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

	public void widgetDefaultSelected(SelectionEvent e) {}

	/** Update the genModel with the content of the wizard page. 
	 * The code generation needs to be aware of the number of automata selected
	 * (i.e., whether to generate formulas for a single automaton or for a 
	 * product of automata). This number is stored in a property with key 
	 * <tt>{@link org.ect.codegen.ea2smt.SMTFormulaGenProductWizardPage#SELECTION_SIZE SELECTION_SIZE}</tt>. 
	 * The names of the selected automata are then stored in properties 
	 * <tt>{@link org.ect.codegen.ea2smt.SMTFormulaGenProductWizardPage#SELECTION_NAME SELECTION_NAME}1</tt> 
	 * to 
	 * <tt>{@link org.ect.codegen.ea2smt.SMTFormulaGenProductWizardPage#SELECTION_NAME SELECTION_NAME}1</tt>
	 * for <tt>i = {@link org.ect.codegen.ea2smt.SMTFormulaGenProductWizardPage#SELECTION_SIZE SELECTION_SIZE}</tt>. */
	public void updateGenModel(IGenModel genModel) {
				
		//store the number of selected automata
		genModel.setProperty(SELECTION_SIZE, String.valueOf(getSelection().size()));
		
		//store the names of selected automata
		int i = 1;
		for (Automaton automaton: getSelection()) {
			genModel.setProperty(SELECTION_NAME + String.valueOf(i), automaton.getName());
			i++;
		}
	}

	/** Update the wizard page. Called every time the wizard page displayed. */
	public void updatePage(IGenModel genModel) {
		
		//tree.removeAll();
		if (!updated) {
			automata = ((Automaton) genModel.getTarget()).getModule().getAutomata();
			for (Automaton automaton : automata) {
				TreeItem item = new TreeItem(tree, SWT.NORMAL);
				String name = automaton.getName();
				if (name==null || "".equals(name)) name = "Unnamed automaton";
				item.setText(name + " (" + automaton.getStates().size() + " states)");
				
				//default: select the first element
				if (automata.get(0).equals(automaton)) {
					item.setChecked(true);
				}
				item.setImage((Image) null);
			}
			tree.addSelectionListener(this);
			widgetSelected(null);
			updated = true;
		}
	}

}
