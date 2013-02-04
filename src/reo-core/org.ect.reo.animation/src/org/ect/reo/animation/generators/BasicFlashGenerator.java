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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.ect.reo.Primitive;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.CreateStep;
import org.ect.reo.animation.DestroyStep;
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.animation.flash.figures.TokenFigure;
import org.ect.reo.animation.flash.figures.factories.DefaultFigureFactory;
import org.ect.reo.animation.flash.figures.factories.IFlashFigureFactory;
import org.ect.reo.animation.flash.figures.factories.ProxyFigureFactory;
import org.ect.reo.channels.FIFO;

import com.anotherbigidea.flash.movie.Frame;



public class BasicFlashGenerator extends AbstractFlashGenerator {
	
	// Remove the tokens at the end of the movie?
	private boolean cleanUpTokens = true;

	// Set of initially created token.
	private Set<FIFO> initialTokens = new HashSet<FIFO>();

	/**
	 * Default constructor.
	 */
	public BasicFlashGenerator() {
		super();
    	
		// Initialize the figure factory. In stand-alone applications we use the default factory only.
		IFlashFigureFactory factory = null;
		try {
			factory = new ProxyFigureFactory();
		} catch (Throwable t) {
			factory = new DefaultFigureFactory();
		}
	    setFigureFactory(factory);
	    
	    // Set the speed reference value.
	    setSpeedReference(AnimationConstants.SPEED_REFERENCE_VALUE);
	}
	
	
	
	@Override
	public void startMovie(int index, AnimationList animations) {
		
		Bounds bounds = super.networkView.getBounds();
        
        movie = createMovie(AnimationConstants.COLOR_WHITE, bounds.getWidth(), bounds.getHeight(), AnimationConstants.FRAME_RATE);        
    	Frame frame = movie.appendFrame();
    	
		removeFigures(frame);
       	createFigures(frame, new Animation());
       	
       	drawFigures(frame);
    	
		// Figures for the tokens of initially full FIFOs:
		for (Primitive primitive : networkView.getNetwork().getAllPrimitives()) {
			if (primitive instanceof FIFO) {
				FIFO fifo = (FIFO) primitive;
				if (fifo.isFull() && !initialTokens.contains(fifo)) {
					TokenFigure token = createTokenFigure(primitive.getSinkEnd(0), nextTokenColor());
					token.draw(frame);
					token.highlight(frame, 1);
					initialTokens.add(fifo);
				}
			}
		}
		

	}
	
	
	public void stopMovie(int index) {
		
		// Stop the movie.
		if (!ReoPreferences.loopAnimations()) {
			Frame frame = movie.appendFrame();
			frame.stop();
		}
        
		// Remove the old tokens.
		if (cleanUpTokens) {
			clearTokens();
			initialTokens.clear();
		}
		
		// Notify that the movie is done.
        notifyListeners(index);
        
	}
	
	@Override
	public void startAnimation(int index, Animation animation) {
		Frame frame = movie.appendFrame();
		removeFigures(frame);
		createFigures(frame, animation);
		drawFigures(frame);
        highlightColouring(true, AnimationConstants.SPEED_COLOURING_FADE * ReoPreferences.getAnimationSpeed());
	}
	
	@Override
	public void stopAnimation(int index) {
        highlightColouring(false, AnimationConstants.SPEED_COLOURING_FADE * ReoPreferences.getAnimationSpeed());
	}
	
	@Override	
	protected void doCreateStep(CreateStep step, int index) {
		Frame frame = movie.appendFrame();
		setSameTokenColor(ReoPreferences.useSameTokenColor());
		createTokens(step, frame);
		fadeTokens(step, true, AnimationConstants.SPEED_TOKEN_FADE * ReoPreferences.getAnimationSpeed());
	}
		
	@Override
	protected void doDestroyStep(DestroyStep step, int index) {
		Frame frame = movie.appendFrame();
		fadeTokens(step, false, AnimationConstants.SPEED_TOKEN_FADE * ReoPreferences.getAnimationSpeed());
		removeTokens(step, frame);
	}
	
	@Override
	protected void doSendStep(SendStep step, int index) {
		moveTokens(step, AnimationConstants.SPEED_TOKEN_MOVE * ReoPreferences.getAnimationSpeed(), false);
	}
	
	@Override
	protected void doReceiveStep(ReceiveStep step, int index) {
		moveTokens(step, AnimationConstants.SPEED_TOKEN_MOVE * ReoPreferences.getAnimationSpeed(), false);
	}
	
	@Override
	protected void doReceiveAndDestroyStep(ReceiveStep receive, DestroyStep destroy, int index) {
		moveAndFadeTokens(receive, destroy, false, false, AnimationConstants.SPEED_TOKEN_FADE * ReoPreferences.getAnimationSpeed());
	}

	@Override	
	protected void doReplicateStep(ReplicateStep step, int index) {
		Frame frame = movie.getFrame(movie.getFrameCount()-1);
		replicateTokens(step, frame);	
	}
	
	/**
	 * Clean up the tokens at the end of a movie.
	 */
	public void setCleanUpTokens(boolean cleanUpTokens) {
		this.cleanUpTokens = cleanUpTokens;
	}
}
