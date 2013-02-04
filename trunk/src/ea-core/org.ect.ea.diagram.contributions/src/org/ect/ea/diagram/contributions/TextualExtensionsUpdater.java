package org.ect.ea.diagram.contributions;

import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.edit.AutomatonCompartmentProxyEditPart;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;
import org.ect.ea.diagram.contributions.edit.StateProxyEditPart;
import org.ect.ea.diagram.contributions.edit.TransitionProxyEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonExtensionEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.StateExtensionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;



public class TextualExtensionsUpdater {

	/**
	 * Checks for a given textual extension whether the view of its
	 * owner contains a node for the extension label. If no such
	 * node exists, a new one is created and added to the view.
	 * 
	 * @param extension Textual extension.
	 * @param view View of the owner.
	 */
	public static void updateTextualExtension(IExtension extension, View view) {
		
		// Check if it is already the extension label.
		if (isExtensionLabel(view)) {
			view = (View) view.eContainer();
		}
		
		// Check if it is an automaton compartment view.
		if (isAutomatonCompartment(view)) {
			view = (View) view.eContainer();			
		}
		
		// Check if it is a valid IExtendible view.
		if (!isExtendibleView(view)) return;
		
		// Find the extension label.
		Node extensionLabel = findExtensionLabel(extension.getId(), view);
		
		// Create if not found.
		if (extensionLabel==null) {
			
			extensionLabel = NotationFactory.eINSTANCE.createNode();
			
			int ownerType = AutomataVisualIDRegistry.getVisualID(view);			

			// Automaton labels.
			if (ownerType==AutomatonEditPart.VISUAL_ID) {
				extensionLabel.setType( AutomataVisualIDRegistry.getType(AutomatonExtensionEditPart.VISUAL_ID) );
			}

			// State labels.
			if (ownerType==StateEditPart.VISUAL_ID) {
				extensionLabel.setType( AutomataVisualIDRegistry.getType(StateExtensionEditPart.VISUAL_ID) );
				Location location = createExternalLabelLocation(extension);
				extensionLabel.setLayoutConstraint(location);
			}

			// Transition labels.
			if (ownerType==TransitionEditPart.VISUAL_ID) {
				extensionLabel.setType( AutomataVisualIDRegistry.getType(TransitionExtensionEditPart.VISUAL_ID) );
				Location location = createExternalLabelLocation(extension);
				extensionLabel.setLayoutConstraint(location);
			}
			
			view.insertChild(extensionLabel);
		}
		
		// Update the label element.
		extensionLabel.setElement(extension);
				
	}

	
	
	private static Location createExternalLabelLocation(IExtension extension) {

		Location location = NotationFactory.eINSTANCE.createLocation();
		int offset = 0;
		int factor = 1;
		if (extension.getOwner()!=null) {
			int index = extension.getOwner().getExtensions().indexOf(extension);
			offset = index * 6;
			factor = (index % 2) * 2 - 1;
		}
		location.setX((0 + offset) * factor);
		location.setY((20 + offset) * factor);
		
		return location;
	
	}
	

	/**
	 * Remove the visual labels for a textual extension.
	 * This is basically the opposite of {@link #updateTextualExtension(IExtension, View)}
	 * 
	 * @param extension Extension that is going to be removed.
	 * @param view The view of the extension's owner.
	 */
	public static void removeTextualExtension(IExtension extension, View view) {
		
		// Check if it is already the extension label.
		if (isExtensionLabel(view)) {
			view = (View) view.eContainer();
		}
		
		// Find the label.
		Node extensionLabel = findExtensionLabel(extension.getId(), view);
		
		// Remove it if found.
		if (extensionLabel!=null) {			
			view.removeChild(extensionLabel);			
		}
		
	}


	
	/**
	 * Search for the label of a textual extension.
	 * @param extension Extension that this label represents.
	 * @param view View that contains the label.
	 * @return The label node if found.
	 */
	private static Node findExtensionLabel(String extensionId, View view) {
		
		// Try to find the label view.
		for (Object object : view.getPersistedChildren()) {
			
			if (!(object instanceof Node)) continue;
			Node node = (Node) object;
			
			if (!isExtensionLabel(node)) continue;
			if (!(node.getElement() instanceof IExtension)) continue;
			IExtension current = (IExtension) node.getElement();
			
			if (extensionId.equals( current.getId() )) {
				return node;
			}
		}
		
		// Not found.
		return null;
	
	}
	
	
	
	/**
	 * Check whether a view is a label for a textual extension.
	 * @param view View to be checked.
	 * @return True, if the type matches one of the label extension types.
	 */
	private static boolean isExtensionLabel(View view) {
		int type = AutomataVisualIDRegistry.getVisualID(view);
		return (type==TransitionExtensionEditPart.VISUAL_ID ||
				type==StateExtensionEditPart.VISUAL_ID ||
				type==AutomatonExtensionEditPart.VISUAL_ID);
	}
	

	private static boolean isExtendibleView(View view) {
		if (!(view.getElement() instanceof IExtendible)) return false;
		int type = AutomataVisualIDRegistry.getVisualID(view);
		return (type==TransitionProxyEditPart.VISUAL_ID ||
				type==StateProxyEditPart.VISUAL_ID ||
				type==AutomatonProxyEditPart.VISUAL_ID);
	}

	
	private static boolean isAutomatonCompartment(View view) {
		if (!(view.getElement() instanceof Automaton)) return false;
		int type = AutomataVisualIDRegistry.getVisualID(view);
		return (type==AutomatonCompartmentProxyEditPart.VISUAL_ID);
	}

}
