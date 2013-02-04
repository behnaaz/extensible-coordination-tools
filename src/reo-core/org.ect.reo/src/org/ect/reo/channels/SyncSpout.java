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
 * @see org.ect.reo.channels.ChannelsPackage#createSyncSpout()
 * @model kind="class"
 * @generated
 */
public class SyncSpout extends SpoutChannel {

	/**
	 * @see org.ect.reo.channels.SpoutChannel#SpoutChannel(Node, Node)
	 * @generated NOT
	 */
	public SyncSpout(Node node1, Node node2) {
		super(node1, node2);
	}
	
	/**
	 * Check whether this channel is synchronous.
	 * @generated NOT
	 */
	@Override
	public boolean isSynchronous() {
		return true;
	}

	/**
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {

		AnimationTable table = new AnimationTable();
		Animation flow, noflow1, noflow2;
		
		if (getSinkEndOne()==null || getSinkEndTwo()==null) {
			return table;
		}

		// Flow.
		flow = new Animation();
		flow.setColour(getSinkEndOne(), FlowColour.FLOW_LITERAL);
		flow.setColour(getSinkEndTwo(), FlowColour.FLOW_LITERAL);
		flow.setNextAnimationTable(table);
		
		CreateStep createStep = new CreateStep();
		createStep.getActiveEnds().add(getSinkEndOne());
		createStep.getActiveEnds().add(getSinkEndTwo());
		
		SendStep sendStep = new SendStep();
		sendStep.getActiveEnds().add(getSinkEndOne());
		sendStep.getActiveEnds().add(getSinkEndTwo());
		
		flow.getSteps().add(createStep);
		flow.getSteps().add(sendStep);

		table.add(flow);
		
		// No flow -<-<-
		noflow1 = new Animation();
		noflow1.setColour(getSinkEndOne(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow1.setColour(getSinkEndTwo(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow1.setNextAnimationTable(table);
		
		table.add(noflow1);

		
		// No flow ->->-
		noflow2 = new Animation();
		noflow2.setColour(getSinkEndOne(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow2.setColour(getSinkEndTwo(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow2.setNextAnimationTable(table);

		table.add(noflow2);

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
	public SyncSpout() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.SYNC_SPOUT;
	}

}
