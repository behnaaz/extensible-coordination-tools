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
package org.ect.ea.extensions.constraints.parts;

import java.util.ArrayList;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.CA.PortType;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNames;

public class NodesWizardPage extends WizardPage {

	protected static final int SIZING_TEXT_FIELD_WIDTH = 250;
		
	private Automaton automaton;
	private AutomatonPortNames nodes;

	private Composite nodeNamesContainer;
	private ArrayList<NodeGroup> nodeProperties = new ArrayList<NodeGroup>();
	
	
	/**
	 * Initialize Wizard Page.
	 * @param selection
	 */
	public NodesWizardPage() {	
		super("Node Properties");
		setTitle("Node Properties");
		setDescription( "Set node properties");
	}
	

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setFont(parent.getFont());

		initializeDialogUnits(parent);

		//PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    
		nodeNamesContainer = new Composite(composite, SWT.NULL);
		nodeNamesContainer.setLayout(new GridLayout());
		nodeNamesContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		createNodeNamesGroup();
		
		// Show description on opening
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
	
	}

	/**
	 * Creates the node names specification controls.
	 * @param parent the parent composite
	 */
	private final void createNodeNamesGroup() {
		
		if (!nodeNamesContainer.isDisposed() && nodeNamesContainer.getChildren().length!=0) {
			// Already initialized.
			return;
		}
		
		NodeLabelsGroup nodeLabels = new NodeLabelsGroup(nodeNamesContainer);
		nodeLabels.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				
		// Clear the old widgets.
		nodeProperties.clear();
		
		nodes = CA.getPortNames(automaton);
		
		for(int i=0; i<nodes.getValues().size();i++) {
			String name = nodes.getValues().get(i);
			NodeGroup nodeGroup = new NodeGroup(name, nodeNamesContainer);
			nodeProperties.add(nodeGroup);
			nodeProperties.get(i).setPortType(CA.getPortType(name, automaton));
		}

		// Relayout everything.
		nodeNamesContainer.getParent().layout(true, true);

	}


	/**
	 * @return the nodeNames
	 */
	public String getNodeName(int index) {
		return nodeProperties.get(index).getNodeName();
	}


	/**
	 * @return the nodeType
	 */
	public PortType getNodeType(int index) {
		return nodeProperties.get(index).getPortType();
	}
	
	
	public int numberOfNodes() {
		return nodeProperties.size();
	}

	public int numberOfTransitions() {
		return automaton.getTransitions().size();
	}

	
	public String[] getTransitionNodes(int index) {
		Transition transition = (Transition) automaton.getTransitions().get(index);
		TransitionPortNames portNames = CA.getPortNames(transition);
		String[] transitionNodes = new String[portNames.getValues().size()];
		for(int i=0;i<portNames.getValues().size();i++){
			transitionNodes[i] = portNames.getValues().get(i);
		}
		return transitionNodes;
	}
	
	
	public int getTransitionStart(int index) {
		Transition transition = (Transition) automaton.getTransitions().get(index);
		return automaton.getStates().indexOf(transition.getSource()); 
	}
	
	
	public int getTransitionEnd(int index) {
		Transition transition = (Transition) automaton.getTransitions().get(index);
		return automaton.getStates().indexOf(transition.getTarget()); 
	}

	
	public int getAutomatonStart() {
		return 0; 
	}
	
	
	public Constraint getContraints(int index) {
		Transition transition = (Transition) automaton.getTransitions().get(index);
		return CA.getConstraint(transition); 
	}
		
	
	public void updateAutomaton() {
		
		String[] names = new String[nodeProperties.size()];
		PortType[] types = new PortType[nodeProperties.size()];
		
		for (int i=0; i<nodeProperties.size(); i++) {
			names[i] = nodeProperties.get(i).getNodeName();
			types[i] = nodeProperties.get(i).getPortType();
		}
		
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(automaton);
		ChangePortTypesCommand command = new ChangePortTypesCommand(domain, automaton, names, types);
		
		try {
			command.execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	
	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
	}	

}