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

import org.eclipse.jface.wizard.Wizard;
import org.ect.ea.automata.Automaton;

public class EditPortTypesWizard extends Wizard {
	
	private NodesWizardPage page;
	
	private Automaton automaton;
	
    public void addPages() {
    	if (page==null) {
    		addPage(page = new NodesWizardPage());
    		page.setAutomaton(automaton);
    	}
    }
	
	@Override
	public boolean performFinish() {
		page.updateAutomaton();
		return true;
	}
	
	public void setAutomaton(Automaton automaton) {
		this.automaton = automaton;
		if (page!=null) {
			page.setAutomaton(automaton);
		}
	}
	
}
