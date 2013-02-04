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
package org.ect.reo.channels;

import org.eclipse.emf.ecore.EClass;
import org.ect.reo.Node;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.DestroyStep;
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.channels.ChannelsFactory#createAsyncDrain()
 * @model kind="class"
 * @generated
 */
public class AsyncDrain extends DrainChannel {
		
	/**
	 * @see org.ect.reo.channels.DrainChannel#DrainChannel(Node, Node)
	 * @generated NOT
	 */
	public AsyncDrain(Node node1, Node node2) {
		super(node1, node2);
	}

	/**
	 * Check whether this channel is synchronous.
	 * @generated NOT
	 */
	@Override
	public boolean isSynchronous() {
		return false;
	}
	
	/**
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {

		AnimationTable table = new AnimationTable();
		Animation flow1, flow2, noflow;
		
		if (getSourceEndOne()==null || getSourceEndTwo()==null) {
			return table;
		}

		// Flow at the first end.
		flow1 = new Animation();
		flow1.setColour(getSourceEndOne(), FlowColour.FLOW_LITERAL);
		flow1.setColour(getSourceEndTwo(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flow1.setNextAnimationTable(table);
		
		ReceiveStep receiveStep = new ReceiveStep();
		receiveStep.getActiveEnds().add(getSourceEndOne());

		DestroyStep destroyStep = new DestroyStep();
		destroyStep.getActiveEnds().add(getSourceEndOne());
		
		flow1.getSteps().add(receiveStep);
		flow1.getSteps().add(destroyStep);

		table.add(flow1);
		
		
		// Flow at the second end.
		flow2 = new Animation();
		flow2.setColour(getSourceEndOne(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flow2.setColour(getSourceEndTwo(), FlowColour.FLOW_LITERAL);
		flow2.setNextAnimationTable(table);
		
		receiveStep = new ReceiveStep();
		receiveStep.getActiveEnds().add(getSourceEndTwo());

		destroyStep = new DestroyStep();
		destroyStep.getActiveEnds().add(getSourceEndTwo());
		
		flow2.getSteps().add(receiveStep);
		flow2.getSteps().add(destroyStep);

		table.add(flow2);
		
		
		// No flow.
		noflow = new Animation();
		noflow.setColour(getSourceEndOne(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow.setColour(getSourceEndTwo(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow.setNextAnimationTable(table);
		
		table.add(noflow);

		return table;

	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	public AsyncDrain() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.ASYNC_DRAIN;
	}

} 
