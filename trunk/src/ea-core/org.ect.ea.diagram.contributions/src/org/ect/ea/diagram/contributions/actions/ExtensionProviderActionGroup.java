package org.ect.ea.diagram.contributions.actions;

import java.util.List;
import java.util.Vector;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionGroup;
import org.ect.ea.EA;
import org.ect.ea.IEditPartProvider;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;



public class ExtensionProviderActionGroup extends ActionGroup {

	public static String EXTENSIONS_GROUP_ID = "extensionGroup";

	private IDiagramWorkbenchPart part;

	private IGraphicalEditPart[] editparts;

	
	/**
	 * Default constructor.
	 */
	public ExtensionProviderActionGroup(IDiagramWorkbenchPart part) {
		this.part = part;
	}
	
	
	@Override
	public void fillContextMenu(IMenuManager menu) {

		IExtensionRegistry registry = EA.getExtensionRegistry();

		refreshEditParts();
		
		// Iterate all extension definitions.
		for (IExtensionDefinition definition : registry.getExtensionDefinitions()) {
			
			// Check if it is a custom extension.
			//if (!definition.getType().equals( IExtensionDefinition.CUSTOM_EXTENSION )) continue;
			//if (!(definition.getProvider() instanceof ICustomExtensionProvider)) continue;
			
			// Get the editpart provider.
			IEditPartProvider provider = (IEditPartProvider) definition.getEditPartProvider();
			
			// Contribute to the menu if the extension is active for in the current selection. 
			if (isActiveExtension(definition) && provider!=null) {
				provider.contributeToContextMenu(menu, editparts);
			}
			
		}
		
    }
	
	
	/**
	 * Refresh the list of currently selected ediparts.
	 */
	private void refreshEditParts() {
		
		List<IGraphicalEditPart> result = new Vector<IGraphicalEditPart>();
		List<?> selection = part.getDiagramGraphicalViewer().getSelectedEditParts();
		for (Object item : selection) {
			if (item instanceof IGraphicalEditPart) result.add((IGraphicalEditPart) item);
		}
		
		editparts = new IGraphicalEditPart[result.size()];
		result.toArray(editparts);
	
	}



	private boolean isActiveExtension(IExtensionDefinition definition) {
		
		for (IGraphicalEditPart editpart : editparts) {

			// If a transition, check the source.
			if (editpart instanceof TransitionEditPart) {
				editpart = (IGraphicalEditPart) ((TransitionEditPart) editpart).getSource();
			}
			
			// If its a state, check the compartment where its contained in.
			if (editpart instanceof StateEditPart) {
				editpart = (IGraphicalEditPart) editpart.getParent();
			}

			// If its a compartment, check the parent.
			if (editpart instanceof AutomatonCompartmentEditPart) {
				editpart = ((AutomatonEditPart) editpart.getParent());
			}

			
			AutomatonProxyEditPart automatonEditPart = null;

			if (editpart instanceof AutomatonProxyEditPart) {
				automatonEditPart = ((AutomatonProxyEditPart) editpart);
			}

			if (automatonEditPart==null || !automatonEditPart.getAutomaton().isActiveExtension(definition.getId())) {
				return false;
			}
			
		}
		
		return true;
	}
	
	
}
