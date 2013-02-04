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
package org.ect.reo.animation.flash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Network;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationExpander;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.AnimationMatrix;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.generators.BasicFlashGenerator;
import org.ect.reo.animation.generators.AbstractFlashGenerator.IGeneratorListener;
import org.ect.reo.diagram.view.util.NetworkView;

import com.anotherbigidea.flash.movie.Movie;



public class ReoAnimations {
		
	/**
	 * Generate all possible Flash animations for a connector.
	 * @param view Network view.
	 * @param monitor Progress monitor.
	 * @return List of Flash movies.
	 */
	public static List<Movie> generateAll(NetworkView view, IProgressMonitor monitor) {
		
		// Extract the network.
		Network network = view.getNetwork();
		monitor.beginTask("Generating animations", 100);
		
		//IColouringEngine engine = ReoPreferences.getColouringEngine();
		//network.setColouringEngine(engine);
		
		AnimationTable table = network.getAnimationTable(new SubProgressMonitor(monitor, 80));
		if (monitor.isCanceled()) return Collections.emptyList();
		
		AnimationExpander expander = new AnimationExpander();
		AnimationMatrix animations = expander.expand(table, new SubProgressMonitor(monitor, 15));		
		if (monitor.isCanceled()) return Collections.emptyList();
				
		// Add an empty animation.
		AnimationList list = new AnimationList();
		list.add(new Animation());
		animations.add(0, list);
		
		return generate(view, animations, new SubProgressMonitor(monitor, 5));
		
	}

	
	/**
	 * Generate an empty Flash animation for a connector.
	 * @param view Network view.
	 * @param monitor Progress monitor.
	 * @return Empty Flash movie.
	 */
	public static Movie generateEmpty(NetworkView view, IProgressMonitor monitor) {
				
		AnimationMatrix animations = new AnimationMatrix();	
		AnimationList list = new AnimationList();
		list.add(new Animation());
		animations.add(0, list);
		
		List<Movie> movies = generate(view, animations, monitor);
		return movies.get(0);
	}

	
	/**
	 * Generate Flash movies from animation specifications.
	 */
	public static List<Movie> generate(NetworkView view, AnimationMatrix animations, IProgressMonitor monitor) {
		
		BasicFlashGenerator generator = new BasicFlashGenerator();
		generator.setNetworkView(view);
		
		final List<Movie> movies = new ArrayList<Movie>();
		
		generator.addListener(new IGeneratorListener() {
			public void movieGenerated(Movie movie, int index) {
				movies.add(movie);
			}
		});
		
		// Generate the animations.
		generator.animate(animations, monitor);
		if (monitor.isCanceled()) return Collections.emptyList();
		
		return movies;
		
	}
	
}
