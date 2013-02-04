package org.ect.ea.diagram.contributions.commands;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.ect.ea.EA;
import org.ect.ea.IAutomatonType;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.contributions.actions.CreateAutomatonWizardPage;



public class AutomatonCreateCommand extends ChangeExtensionsCommand {

	private IGraphicalEditPart host;
	private Automaton automaton;
	private CreateElementRequest request;
	
	public AutomatonCreateCommand(IGraphicalEditPart host, CreateElementRequest request) {
		super("Create automaton", host);
		this.host = host;
		this.request = request;
	}

	@Override
	protected void performExtensionsChange(IProgressMonitor monitor)
			throws ExecutionException {
		
		Shell shell = host.getViewer().getControl().getShell();
		Module module = (Module) host.getNotationView().getElement();
		
		// Open a small wizard.
		CreateAutomatonWizard wizard = new CreateAutomatonWizard();		
		wizard.setForcePreviousAndNextButtons(false);
		wizard.setWindowTitle("Create new automaton");
		
		// Important: canceling this wizard does not cancel the operation.
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.getShell().setSize(550, 430);
		dialog.setBlockOnOpen(true);
		dialog.open();
		
		// Initialize the automaton.
		automaton = AutomataFactory.eINSTANCE.createAutomaton();
		automaton.setName(wizard.getAutomatonName());
		if (wizard.getAutomatonType()!=null) {
			EA.getAutomatonTypeRegistry().setAutomatonType(automaton, wizard.getAutomatonType());
		}
		// Add it to the module.
		module.getAutomata().add(automaton);
		
		// Update the request. THIS IS IMPORTANT FOR CREATING THE VIEWS FOR THE EXTENSIONS!
		// See the proxy view factories for details.
		request.setEditingDomain(getEditingDomain());
		request.setNewElement(automaton);
		
	}
	
	
	@Override
	protected List<Automaton> getAutomataToUpdate() {	
		List<Automaton> automata = new Vector<Automaton>();
		if (automaton!=null) automata.add(automaton);
		return automata;
	}
	
	
	
	public static class CreateAutomatonWizard extends Wizard {
		
		protected CreateAutomatonWizardPage page;
		protected String name;
		protected IAutomatonType type;
		
		public CreateAutomatonWizard() {
			super();
			this.page = new CreateAutomatonWizardPage();
		}
				
		public void addPages() {
			if (getPageCount()>0) return;
			addPage(page);
		}
				
		public boolean performFinish() {
			// Default implementation does nothing.
			this.name = page.getAutomatonName();
			this.type = page.getAutomatonType();
			return true;
		}
		
		public String getAutomatonName() {
			return name;
		}

		public IAutomatonType getAutomatonType() {
			return type;
		}
	}

}
