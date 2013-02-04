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
import org.ect.reo.animation.CreateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.channels.ChannelsFactory#createAsyncSpout()
 * @model kind="class"
 * @generated
 */
public class AsyncSpout extends SpoutChannel {		
	
	/**
	 * @see org.ect.reo.channels.SpoutChannel#SpoutChannel(Node, Node)
	 * @generated NOT
	 */
	public AsyncSpout(Node node1, Node node2) {
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
		
		if (getSinkEndOne()==null || getSinkEndTwo()==null) {
			return table;
		}

		// Flow at the first end.
		flow1 = new Animation();
		flow1.setColour(getSinkEndOne(), FlowColour.FLOW_LITERAL);
		flow1.setColour(getSinkEndTwo(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flow1.setNextAnimationTable(table);
		
		CreateStep createStep = new CreateStep();
		createStep.getActiveEnds().add(getSinkEndOne());

		SendStep sendStep = new SendStep();
		sendStep.getActiveEnds().add(getSinkEndOne());
		
		flow1.getSteps().add(createStep);
		flow1.getSteps().add(sendStep);

		table.add(flow1);
		
		
		// Flow at the second end.
		flow2 = new Animation();
		flow2.setColour(getSinkEndOne(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flow2.setColour(getSinkEndTwo(), FlowColour.FLOW_LITERAL);
		flow2.setNextAnimationTable(table);
		
		createStep = new CreateStep();
		createStep.getActiveEnds().add(getSinkEndTwo());

		sendStep = new SendStep();
		sendStep.getActiveEnds().add(getSinkEndTwo());
		
		flow2.getSteps().add(createStep);
		flow2.getSteps().add(sendStep);

		table.add(flow2);
		
		
		// No flow.
		noflow = new Animation();
		noflow.setColour(getSinkEndOne(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow.setColour(getSinkEndTwo(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
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
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.ASYNC_SPOUT;
	}
	
	/**
	 * @generated
	 */
	public AsyncSpout() {
		super();
	}
	
} // AsyncSpout