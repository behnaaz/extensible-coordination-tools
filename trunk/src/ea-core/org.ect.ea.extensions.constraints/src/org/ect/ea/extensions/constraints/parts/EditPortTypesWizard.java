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
