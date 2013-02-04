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
package org.ect.reo.animation.generators;

import org.ect.reo.animation.CreateStep;
import org.ect.reo.animation.DestroyStep;
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.animation.flash.ReoPreferences;

import com.anotherbigidea.flash.movie.Frame;



/**
 * This animation generator only highlights the colouring, without
 * creating any tokens. The colouring is also not removed in the end.
 * 
 * @author Christian Krause
 *
 */
public class PreviewFlashGenerator extends BasicFlashGenerator {
	
	/**
	 * Default constructor.
	 */
	public PreviewFlashGenerator() {
		super();
	}
	
	@Override
	public void stopAnimation(int index) {
	}
	
	@Override
	protected void doCreateStep(CreateStep step, int index) {
	}
	
	@Override
	protected void doDestroyStep(DestroyStep step, int index) {
	}
	
	@Override
	protected void doSendStep(SendStep step, int index) {
	}
	
	@Override
	protected void doReceiveStep(ReceiveStep step, int index) {
	}
		
	@Override
	protected void doReplicateStep(ReplicateStep step, int index) {
	}
	
	@Override
	protected void doReceiveAndDestroyStep(ReceiveStep receive, DestroyStep destroy, int index) {
	}

	
	@Override
	public void stopMovie(int index) {
		// If the preferences say we should loop, we ignore that and stop the movie.
		if (ReoPreferences.loopAnimations()) {
			Frame frame = movie.appendFrame();
			frame.stop();
		}
        // Do the cleanup.
		super.stopMovie(index);
	}
}
