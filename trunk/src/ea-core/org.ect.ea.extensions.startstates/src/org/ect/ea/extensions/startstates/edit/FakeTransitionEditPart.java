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
package org.ect.ea.extensions.startstates.edit;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;

public class FakeTransitionEditPart extends ConnectionNodeEditPart {

	public static final String VISUAL_ID = StartStateExtensionProvider.EXTENSION_ID + "_fakeTransition";
	
	/**
	 * Listener for updating the position.
	 */
	private Adapter listener = new AdapterImpl() {
		
		@Override
		public void notifyChanged(final Notification event) {			
			try {
				// Create a command.
				AbstractTransactionalCommand command = new AbstractTransactionalCommand(getEditingDomain(), "Update position", null) {
					@Override
					protected CommandResult doExecuteWithResult(
							IProgressMonitor monitor, IAdaptable info)
							throws ExecutionException {
						updatePosition(event);						
						return CommandResult.newOKCommandResult();
					}
				};
				// Execute the command.
				command.execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				// Do nothing.
			}
		}
	};
	
	/**
	 * Default constructor.
	 */
	public FakeTransitionEditPart(View view) {
		super(view);		
	}
	
	
    @Override
    public void activate() {
    	super.activate();
		// Add a listener that updates the position of the invisible node.
		Node target = (Node) ((Edge) getNotationView()).getTarget();
		if (target!=null && target.getLayoutConstraint()!=null) {
			target.getLayoutConstraint().eAdapters().add(listener);
		}
    }
    
    
    @Override
    public void deactivate() {
		// Remove the listener.
		Node target = (Node) ((Edge) getNotationView()).getTarget();
		if (target!=null && target.getLayoutConstraint()!=null) {
			target.getLayoutConstraint().eAdapters().add(listener);
		}
		super.deactivate();
    }
	
	
	
	/**
	 * Update the position of the invisible node when the state node is moved.
	 */
	private void updatePosition(Notification event) {
		
		// Get the invisible node.
		Node source = (Node) ((Edge) getNotationView()).getSource();		
		if (source==null) return;
		
		// Check if it is the correct event.
		if (event.getEventType()==Notification.SET) {
			
			Location loc = (Location) source.getLayoutConstraint();
			int diff;
			
			switch (event.getFeatureID(Location.class)) {
			
			case NotationPackage.LOCATION__X	: 
				diff = event.getNewIntValue() - event.getOldIntValue();
				loc.setX(loc.getX() + diff);
				break;
			
			case NotationPackage.LOCATION__Y	: 
				diff = event.getNewIntValue() - event.getOldIntValue();
				loc.setY(loc.getY() + diff);
				break;
			}
			
		}
	}
	
	
	/**
	 * Creates figure for this edit part.
	 */
	protected Connection createConnectionFigure() {
		return new FakeTransitionFigure();
	}

	
	public class FakeTransitionFigure extends PolylineConnectionEx {
		
		public FakeTransitionFigure() {
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(ColorConstants.black);
			setTargetDecoration(createTargetDecoration());
		}

		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			PointList pl = new PointList();
			pl.addPoint(getMapMode().DPtoLP(-1), getMapMode().DPtoLP(1));
			pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
			pl.addPoint(getMapMode().DPtoLP(-1), getMapMode().DPtoLP(-1));
			df.setTemplate(pl);
			df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
			return df;
		}
	}
	
}
