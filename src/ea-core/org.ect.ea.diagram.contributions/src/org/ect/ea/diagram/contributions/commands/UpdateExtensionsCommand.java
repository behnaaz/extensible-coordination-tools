package org.ect.ea.diagram.contributions.commands;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * An empty implementation of ChangeExtensionsCommand.
 * This can be used to validate the extension views 
 * of an automaton.
 * 
 * @author Christian Koehler
 *
 */
public final class UpdateExtensionsCommand extends ChangeExtensionsCommand {

	public UpdateExtensionsCommand(IGraphicalEditPart selected) {
		super("Update extensions", selected);
	}

	
	protected final void performExtensionsChange(IProgressMonitor monitor) {
		// Do nothing. The extensions are automatically updated.
	}
	
}

