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
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.channels.ChannelsFactory#createSync()
 * @model kind="class"
 * @generated
 */
public class Sync extends DirectedChannel {
		
	/**
	 * @see org.ect.reo.channels.DirectedChannel#DirectedChannel(Node, Node)
	 * @generated NOT
	 */
	public Sync(Node node1, Node node2) {
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

		if (getSourceEnd()==null || getSinkEnd()==null) {
			return table;
		}
		
		// Flow.
		flow = new Animation();
		flow.setColour(getSourceEnd(), FlowColour.FLOW_LITERAL);
		flow.setColour(getSinkEnd(), FlowColour.FLOW_LITERAL);
		flow.setNextAnimationTable(table);
		
		ReceiveStep receiveStep = new ReceiveStep(getSourceEnd());
		ReplicateStep replicateStep = new ReplicateStep(getSourceEnd());
		SendStep sendStep = new SendStep(getSinkEnd());
		
		flow.getSteps().add(receiveStep);
		flow.getSteps().add(replicateStep);
		flow.getSteps().add(sendStep);

		table.add(flow);
		
		// No flow -<-<-
		noflow1 = new Animation();
		noflow1.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow1.setColour(getSinkEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow1.setNextAnimationTable(table);

		table.add(noflow1);

		
		// No flow ->->-
		noflow2 = new Animation();
		noflow2.setColour(getSourceEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow2.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
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
	public Sync() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.SYNC;
	}

} // Sync