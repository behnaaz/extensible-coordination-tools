package org.ect.ea.diagram.contributions.commands;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.AutomataPlugin;
import org.ect.ea.EA;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.automata.AutomataCorrector;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.diagram.contributions.ExtensionViewUpdater;
import org.ect.ea.util.IExtensionViewUpdater;



/**
 * Abstract command for changing extensions of an automaton,
 * a state or a transition. Using this command ensures, that
 * the extensions are validated automatically and that the
 * views in the diagram editor are updated using the extension
 * providers.
 * 
 * @author Christian Koehler
 * 
 */
public abstract class ChangeExtensionsCommand extends AbstractTransactionalCommand {
	
	// Extension registry.
	protected IExtensionRegistry registry;
	
	// View to be used.
	protected View view;
	
	// EditDomain.
	protected EditDomain editDomain;
	
	// Diagram editpart.
	protected DiagramEditPart diagramEditpart;
	
	protected IExtensionViewUpdater viewUpdater;

	/**
	 * Default constructor.
	 */
	public ChangeExtensionsCommand(String name, IGraphicalEditPart editpart) {
		this(name, editpart.getNotationView());
		this.editDomain = editpart.getViewer().getEditDomain();
		this.viewUpdater = new ExtensionViewUpdater();
		
		// TODO: improve this.
		while (diagramEditpart==null && editpart!=null) {
			if (editpart instanceof DiagramEditPart) {
				diagramEditpart = (DiagramEditPart) editpart;
				break;
			}
			if (editpart.getParent() instanceof IGraphicalEditPart &&
				editpart.getParent()!=editpart) {
				editpart = (IGraphicalEditPart) editpart.getParent();
			} else {
				editpart = null;
			}
		}
	}
	
	/**
	 * Alternative constructor (not preferred).
	 */
	public ChangeExtensionsCommand(String name, View view) {
		super(TransactionUtil.getEditingDomain(view), name, null);
		this.registry = EA.getExtensionRegistry();
		this.view = view;
	}

	
	/**
	 * Perform the extension changes. These changes don't need to be 
	 * restricted to the selected edit part. The complete automaton 
	 * will be updated automatically after this operation.
	 */
	protected abstract void performExtensionsChange(IProgressMonitor monitor) throws ExecutionException;
	
	
	/**
	 * Convenience execute method.
	 */
	public void execute() {
		try {
			execute(new NullProgressMonitor(), null);
		}
		catch (ExecutionException e) {
			AutomataPlugin.getInstance().logError("Error in extensions command.", e);
		}
	}
	
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		monitor.beginTask(getLabel() + "", 10);
				
		// Perform the changes.
		IProgressMonitor sub = new SubProgressMonitor(monitor, 7);
		performExtensionsChange(sub);
		sub.done();
		
		// Get the automata that need an update of the views and the extensions.
		List<Automaton> automata = getAutomataToUpdate();
		
		// Update extensions.
		sub = new SubProgressMonitor(monitor, 3);
		sub.beginTask("Updating extensions", automata.size());
		for (Automaton automaton : automata) {
			updateAutomatonExtensions(automaton, new SubProgressMonitor(sub, 1));
		}
		sub.done();
		
		// Done.
		monitor.done();
		
		return CommandResult.newOKCommandResult();		
	}
	
	
	
	protected List<Automaton> getAutomataToUpdate() {
		
		List<Automaton> automata = new Vector<Automaton>();
		
		if (view instanceof Edge) {
			view = view.getDiagram();
		}
		
		if (view instanceof Node) {
			if (view.getElement() instanceof State) view = (View) view.eContainer();
			Node node = (Node) view;
			if (node.getElement() instanceof Automaton) {
				automata.add((Automaton) node.getElement());
			}
		}
		
		if (view instanceof Diagram) {
			for (Object child : view.getChildren()) {
				if (!(child instanceof Node)) continue;
				Node node = (Node) child;
				if (node.getElement() instanceof Automaton) {
					automata.add((Automaton) node.getElement());
				}
			}
		}
		
		return automata;
		
	}
	
	
	protected void updateNormalViews() {
		
		if (diagramEditpart!=null) {
			
			diagramEditpart.refresh();
			diagramEditpart.getFigure().invalidate();
			diagramEditpart.getFigure().validate();
						
			CanonicalEditPolicy policy = (CanonicalEditPolicy) diagramEditpart.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
			if (policy!=null) policy.refresh();
		}
	}
	
	
	/**
	 * Update all extension in an automaton. This includes
	 * automaton extensions, state extensions and transition
	 * extensions.
	 * 
	 * @param view Automaton node.
	 */
	private void updateAutomatonExtensions(Automaton automaton, IProgressMonitor monitor) {
		
		// System.out.print("Updating extensions for " + automaton + "... ");
		
		// Check the extension ids of the automaton.
		AutomataCorrector.correctExtensionIds(automaton);
		AutomataCorrector.correctExtensions(automaton, view, viewUpdater, monitor);
		
		// System.out.println("done.");
		
	}
	
	
	public EditDomain getEditDomain() {
		return editDomain;
	}

	public void setEditDomain(EditDomain editDomain) {
		this.editDomain = editDomain;
	}
	
	@Override
	public List<?> getAffectedFiles() {
		if (diagramEditpart!= null) {
			View view = (View) diagramEditpart.getModel();
			if (view != null) {
				IFile f = WorkspaceSynchronizer.getFile(view.eResource());
				return f != null ? Collections.singletonList(f) : Collections.EMPTY_LIST;
			}
		}
		return super.getAffectedFiles();
	}
}
