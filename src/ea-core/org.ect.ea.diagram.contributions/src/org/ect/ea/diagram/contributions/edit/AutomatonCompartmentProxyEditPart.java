package org.ect.ea.diagram.contributions.edit;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.SetAllBendpointRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.ToggleConnectionLabelsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.contributions.commands.UpdateExtensionsCommand;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;



public class AutomatonCompartmentProxyEditPart extends AutomatonCompartmentEditPart {

	/**
	 * Default constructor.
	 * @param view Automaton compartment view.
	 */
	public AutomatonCompartmentProxyEditPart(View view) {
		super(view);
	}

	
	/**
	 * Overrides GraphicalEditPart#getCommand(). This ensures
	 * that the views are updated after each command.
	 */
	@Override
	public Command getCommand(Request request) {

		// Get the original command.
		Command command = super.getCommand(request);
		
		// These are not interesting for us.
		if (request instanceof CreateConnectionViewRequest ||
			request instanceof CreateViewRequest ||
			request instanceof ChangeBoundsRequest ||
			request instanceof SetAllBendpointRequest ||
			request instanceof AlignmentRequest ||
			request instanceof ToggleConnectionLabelsRequest ||
			request instanceof ArrangeRequest) return command;
		
		// Add an update command.
		CompoundCommand compound = new CompoundCommand();
		compound.add(command);
		compound.add(new ICommandProxy(new UpdateExtensionsCommand(this)));
		
		return compound;
	}
	
	@Override
	public boolean isSelectable() {
		return false;
	}
	
}
