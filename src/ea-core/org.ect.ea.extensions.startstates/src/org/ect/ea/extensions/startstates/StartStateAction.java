package org.ect.ea.extensions.startstates;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.State;
import org.ect.ea.diagram.contributions.actions.ChangeExtensionsAction;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.extensions.BooleanExtension;



/**
 * Action for changing the start state extension of states.
 * @author Christian Koehler
 */
public class StartStateAction extends ChangeExtensionsAction {
	
	// Id of this action.
	public static final String ID = "org.ect.ea.extensions.startStates.StartStateAction";
	
	// Name of this action, as it is displayed in the context menu.
	public static final String NAME = "Start State";

	// State extension to be modified.
	private BooleanExtension startStateExtension;
	
	// Selected EditPart.
	private IGraphicalEditPart editpart;
	
	
	/**
	 * Default constructor.
	 * @param selection Current selection.
	 */
	public StartStateAction(IGraphicalEditPart[] selection) {

		setId(ID);
		setText(NAME);
		setToolTipText(NAME);
		
		boolean enabled = isStateSelection(selection) && isSingletonSelection(selection);
		
		setEnabled(enabled);
		
		if (enabled) {
			
			editpart = selection[0];
			View view = (View) editpart.getModel();
			
			State state = (State) view.getElement();
			startStateExtension = (BooleanExtension) state.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			
			setChecked(startStateExtension.getValue());
			
		} else {
			setChecked(false);
		}
		
	}


	@Override
	protected ChangeExtensionsCommand getCommand() {
		
		if (editpart==null || startStateExtension==null) return null;
		
		return new ChangeExtensionsCommand("Toggle Start State", editpart) {
			
			protected void performExtensionsChange(IProgressMonitor monitor) {				
				
				monitor.beginTask("Toggle start state", 1);
				
				boolean oldValue = startStateExtension.getValue();
				startStateExtension.setValue(!oldValue);
				
				monitor.worked(1);
				monitor.done();
				
			}
		};
	}
	

}
