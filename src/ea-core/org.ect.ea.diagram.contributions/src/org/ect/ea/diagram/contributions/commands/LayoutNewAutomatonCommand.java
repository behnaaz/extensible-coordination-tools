package org.ect.ea.diagram.contributions.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;



/**
 * Layout a newly creating automaton in a diagram. 
 * 
 * @author Christian Krause
 */
public class LayoutNewAutomatonCommand extends DeferredLayoutCommand {
	
	// The module editpart.
	private final ModuleEditPart editpart;
	
	// List of existing automaton editparts.
	private List<AutomatonEditPart> existing = new ArrayList<AutomatonEditPart>();
	
	/**
	 * Constructor.
	 * @param editpart Module editpart.
	 * @param ommitExisting layout automata created between instantiation time 
	 * and execution time of this command,
	 */
	public LayoutNewAutomatonCommand(ModuleEditPart editpart, boolean ommitExisting) {
		super(editpart.getEditingDomain(), null, editpart);
		this.editpart = editpart;
		
		// Check which automata exist already before executing this command.
		if (ommitExisting)
			for (int i=0; i<editpart.getChildren().size(); i++) {
				if (editpart.getChildren().get(i) instanceof AutomatonEditPart) {
					existing.add((AutomatonEditPart) editpart.getChildren().get(i));
				}
			}
	}

	
	@SuppressWarnings("unchecked")
	protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

		AutomatonCompartmentEditPart compartment = null;
		
		// Find new automata editpart.
		for (int i=0; i<editpart.getChildren().size(); i++) {
			
			if (editpart.getChildren().get(i) instanceof AutomatonEditPart &&
				!existing.contains(editpart.getChildren().get(i))) {
				
				// Remember the compartment.
				AutomatonEditPart current = (AutomatonEditPart) editpart.getChildren().get(i);
				compartment = (AutomatonCompartmentEditPart) current.getChildren().get(1);
				break;
			}
		}
		
		// Nothing to do?
		if (compartment==null) {
			return CommandResult.newOKCommandResult();
		}
		
		super.containerEP = compartment;
		super.viewAdapters = new Vector<Object>();
		
		for (Object child : compartment.getChildren()) {
			// Layout only state editparts.
			if (child instanceof StateEditPart) {
				super.viewAdapters.add(child);
			}
		}
		
		return super.doExecuteWithResult(progressMonitor, info);
		
	}
	
}