package org.ect.ea.diagram.contributions.edit;

import java.util.HashMap;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
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
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.contributions.commands.UpdateExtensionsCommand;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;



public class TransitionProxyEditPart extends TransitionEditPart {

	private HashMap<TransitionExtensionEditPart, WrappingLabel> extensionLabels;

	/**
	 * Default constructor.
	 * @param view The transition edge.
	 */
	public TransitionProxyEditPart(View view) {
		super(view);
		this.extensionLabels = new HashMap<TransitionExtensionEditPart, WrappingLabel>();
	}


	/**
	 * Overrides TransitionEditPart#createConnectionFigure().
	 */
	@Override
	protected Connection createConnectionFigure() {
		TransitionFigure figure = (TransitionFigure) super.createConnectionFigure();
		// Remove the extension label.
		figure.remove(figure.getTransitionExtensionLabel());
		return figure;
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
	protected void addChildVisual(EditPart child, int index) {
		
		if (child instanceof TransitionExtensionEditPart) {
			TransitionExtensionEditPart editpart = (TransitionExtensionEditPart) child;
			WrappingLabel label = new WrappingLabel("");
			getFigure().add(label, index);
			editpart.setLabel(label);
			extensionLabels.put(editpart, label);
		} else {
			super.addChildVisual(child, index);
		}

	}

	
	@Override
	protected void removeChildVisual(EditPart child) {
		
		if (child instanceof TransitionExtensionEditPart) {
			TransitionExtensionEditPart editpart = (TransitionExtensionEditPart) child;
			WrappingLabel label = extensionLabels.get(editpart);
			if (label!=null) {
				getFigure().remove(label);
				extensionLabels.remove(editpart);
			}
		} else {
			super.removeChildVisual(child);
		}
	}

	
	@Override
	public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
		// If the childFigure is already removed, its parent will be null.
		if (child!=null && childFigure!=null && childFigure.getParent()!=null)
			childFigure.getParent().setConstraint(childFigure, constraint);
	}
	
	
	public Transition getTransition() {
		return (Transition) getNotationView().getElement();
	}
	
}
