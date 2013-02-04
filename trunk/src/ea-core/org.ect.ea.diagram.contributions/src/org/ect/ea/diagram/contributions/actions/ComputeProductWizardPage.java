package org.ect.ea.diagram.contributions.actions;

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
import org.ect.ea.automata.Automaton;



public class ComputeProductWizardPage extends WizardPage implements SelectionListener {

	// List of all automatons in this diagram.
	private List<Automaton> automata;
	
	// List of selected automatons in this diagram.
	private List<Automaton> selection;

	// Tree is used to display the automatons.
	private Tree tree;
	
	/**
	 * Initialize Wizard Page.
	 * @param selection
	 */
	public ComputeProductWizardPage() {
		super("Compute Product");
		setTitle("Compute Product");
		setDescription( "This wizard computes the product of the the currently selected automata. " +
						"Select the automata you would like to combine.");
	}
	
	
	public void createControl(Composite parent) {

		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 5;
		parent.setLayout(layout);
		
		Label label = new Label(parent, SWT.FILL);
		label.setText("Automata:  ");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		tree = new Tree(parent, SWT.CHECK | SWT.SINGLE | SWT.BORDER);
		tree.setLayoutData(new GridData(GridData.FILL_BOTH));
				
		for (Automaton automaton : automata) {
			TreeItem item = new TreeItem(tree, SWT.NORMAL);
			String name = automaton.getName();
			if (name==null || "".equals(name)) name = "Unnamed automaton";
			item.setText(name + " (" + automaton.getStates().size() + " states)");
			item.setChecked(selection.contains(automaton));
			item.setImage((Image) null);
		}
		tree.addSelectionListener(this);
		widgetSelected(null);
		
		setControl(parent);
	
	}


	public void setAutomata(List<Automaton> automata) {
		this.automata = automata;
	}


	public List<Automaton> getAutomata() {
		return automata;
	}

	
	public void setSelection(List<Automaton> selection) {
		this.selection = selection;
	}

	
	public List<Automaton> getSelection() {
		List<Automaton> result = new Vector<Automaton>();
		for (int i=0; i<tree.getItemCount(); i++) {
			if (tree.getItem(i).getChecked()) result.add(automata.get(i));
		}
		return result;
	}

	
	// ----- Listen to selection events in the tree ----- //
	
	public void widgetSelected(SelectionEvent e) {
		if (getSelection().size()<2) {
			setErrorMessage("Please select two or more automata.");
			setPageComplete(false);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

	public void widgetDefaultSelected(SelectionEvent e) {}

}
