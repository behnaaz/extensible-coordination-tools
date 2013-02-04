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
package org.ect.reo.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.channels.FIFO;
import org.ect.reo.diagram.edit.policies.ChannelItemSemanticEditPolicy;
import org.ect.reo.diagram.figures.FIFOLine;


/**
 * @generated
 */
public class FIFOEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3003;

	/**
	 * @generated
	 */
	public FIFOEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void activate() {
		super.activate();
		if (getFIFO() != null) {
			getFIFO().eAdapters().add(ADAPTER);
		}
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void deactivate() {
		if (getFIFO() != null) {
			getFIFO().eAdapters().remove(ADAPTER);
		}
		super.deactivate();
	}

	/**
	 * @generated NOT
	 */
	public FIFO getFIFO() {
		return (FIFO) getNotationView().getElement();
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Connection createConnectionFigure() {
		FIFOLine line = new FIFOLine();
		line.setFull(getFIFO().isFull());
		return line;
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ChannelItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	public FIFOLine getPrimaryShape() {
		return (FIFOLine) getFigure();
	}

	/**
	 * @generated NOT
	 */
	private final Adapter ADAPTER = new AdapterImpl() {
		public void notifyChanged(Notification event) {
			if (event.getEventType() == Notification.SET
					&& getPrimaryShape() != null && getFIFO() != null) {
				getPrimaryShape().setFull(getFIFO().isFull());
			}
		}
	};
}
