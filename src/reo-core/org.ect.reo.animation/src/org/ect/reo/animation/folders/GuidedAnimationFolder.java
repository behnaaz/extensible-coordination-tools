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
package org.ect.reo.animation.folders;

import java.io.IOException;

import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.templates.GuidedIndexTemplate;
import org.ect.reo.animation.templates.GuidedMenuFrameTemplate;

import com.anotherbigidea.flash.movie.Movie;



public class GuidedAnimationFolder extends BasicAnimationFolder {
	
	// Width of the preview animations.
	public static final int PREVIEW_WIDTH = 120;
	
	/**
	 * Default constructor.
	 */
	public GuidedAnimationFolder(String name) {
		super(name /*+ " (guided)"*/);
	}
	
	@Override
	protected String generateIndex() {
		GuidedIndexTemplate index = new GuidedIndexTemplate();
		return index.generate(this);
	}
	
	@Override
	protected String generateMenuFrame() {
		GuidedMenuFrameTemplate menu = new GuidedMenuFrameTemplate();
		return menu.generate(this);
	}
	
	@Override
	public void addAnimation(AnimationList animation, Movie movie) throws IOException {
		
		// Adjust the size of the preview animations.
		double oldScaleFactor = getScaleFactor();
		if (getLength()>0) setScaleFactor((double) PREVIEW_WIDTH / (double) movie.getWidth() );
		
		super.addAnimation(animation, movie);
		
		// Reset the scale factor again.
		setScaleFactor(oldScaleFactor);
		
	}
		
	
	// Machine-generated serial id.
	private static final long serialVersionUID = -5640717507676242057L;

}
