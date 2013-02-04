package org.ect.ea.diagram.contributions.edit;

import java.util.HashMap;

import org.eclipse.draw2d.GridData;
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
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.commands.UpdateExtensionsCommand;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonExtensionEditPart;



public class AutomatonProxyEditPart extends AutomatonEditPart {
	
	private HashMap<AutomatonExtensionEditPart, WrappingLabel> extensionLabels;
	
	/**
	 * Default constructor.
	 * @param view The automaton view.
	 */
	public AutomatonProxyEditPart(View view) {
		super(view);
		this.extensionLabels = new HashMap<AutomatonExtensionEditPart, WrappingLabel>();
	}

	
	/**
	 * Overrides AutomatonEditPart#createNodeShape().
	 * We create our own figure.
	 */
	@Override
	protected IFigure createNodeShape() {
		super.primaryShape = new AutomatonProxyFigure();
		return super.primaryShape;
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

		// Check if it is an extension.
		if (child instanceof AutomatonExtensionEditPart) {
			AutomatonExtensionEditPart editpart = (AutomatonExtensionEditPart) child;
			WrappingLabel label = new WrappingLabel("");
			getProxyFigure().addExtensionLabel(label);
			editpart.setLabel(label);
			extensionLabels.put(editpart, label);
		}
		else {
			super.addChildVisual(child, index);	
		}
	}

	@Override
	protected void removeChildVisual(EditPart child) {
		
		// Check if it is an extension.
		if (child instanceof AutomatonExtensionEditPart) {
			AutomatonExtensionEditPart editpart = (AutomatonExtensionEditPart) child;
			WrappingLabel label = extensionLabels.get(editpart);
			if (label!=null) {
				getProxyFigure().removeExtensionLabel(label);
				extensionLabels.remove(editpart);
			}
		}
		else {
			super.removeChildVisual(child);
		}
	}
	

	@Override
	public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
		// If the childFigure is already removed, its parent will be null.
		if (child!=null && childFigure!=null && childFigure.getParent()!=null)
			childFigure.getParent().setConstraint(childFigure, constraint);
	}

	
	public Automaton getAutomaton() {
		return (Automaton) getNotationView().getElement();
	}
	
	
	public AutomatonProxyFigure getProxyFigure() {
		return (AutomatonProxyFigure) super.primaryShape;
	}
	
	
	
	/**
	 * Custom automaton figure.
	 */
	public class AutomatonProxyFigure extends AutomatonFigure {
		
		private int extensionCount = 0;
		
		public AutomatonProxyFigure() {
			super();
			// Remove the default extension label.
			remove(getAutomatonExtensionLabel());
		}
		
		public void addExtensionLabel(WrappingLabel label) {
			// Set the layout data.
			GridData data = new GridData(GridData.CENTER, GridData.CENTER, true, false);
			data.horizontalIndent = 0;
			add(label, data, extensionCount + 1);
			extensionCount++;
			validate();
		}

		public void removeExtensionLabel(WrappingLabel label) {
			remove(label);
			extensionCount--;
			validate();
		}

	}

}
