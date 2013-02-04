/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.ea.diagram.contributions.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.SetAllBendpointRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.ToggleConnectionLabelsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.State;
import org.ect.ea.diagram.contributions.commands.UpdateExtensionsCommand;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.StateExtensionEditPart;

public class StateProxyEditPart extends StateEditPart {
	
	static final int BORDER_ITEM_DISTANCE = 16;

	/**
	 * Default constructor.
	 * @param view The state node.
	 */
	public StateProxyEditPart(View view) {
		super(view);
	}
	
	
	@Override
	protected IFigure createNodeShape() {
		StateFigure figure = (StateFigure) super.createNodeShape();
		// Remove the generated extension label.
		figure.remove(figure.getStateExtensionLabel());
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
			request instanceof ArrangeRequest ||
			request instanceof ReconnectRequest) return command;
		
		// Add an update command.
		CompoundCommand compound = new CompoundCommand();
		compound.add(command);
		compound.add(new ICommandProxy(new UpdateExtensionsCommand(this)));
		
		return compound;
	}
	
		
	// Adjust the distance of the labels.
	@Override
	protected void addBorderItem(IFigure borderItemContainer, IBorderItemEditPart borderItemEditPart) {
		
		if (borderItemEditPart instanceof StateExtensionEditPart) {
			BorderItemLocator locator = new BorderItemLocator(getMainFigure(), PositionConstants.SOUTH);
			locator.setBorderItemOffset(new Dimension(-BORDER_ITEM_DISTANCE, -BORDER_ITEM_DISTANCE));
			borderItemContainer.add(borderItemEditPart.getFigure(), locator);
		} else {
			super.addBorderItem(borderItemContainer, borderItemEditPart);
		}
		
	}

	
	@Override
	public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
		// If the childFigure is already removed, its parent will be null.
		if (child!=null && childFigure!=null && childFigure.getParent()!=null)
			childFigure.getParent().setConstraint(childFigure, constraint);
	}


	public State getState() {
		return (State) getNotationView().getElement();
	}
	
}
