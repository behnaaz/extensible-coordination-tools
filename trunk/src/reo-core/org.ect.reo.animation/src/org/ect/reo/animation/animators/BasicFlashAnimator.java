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
package org.ect.reo.animation.animators;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.ui.IWorkbenchPage;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Reo;
import org.ect.reo.animation.AnimationExpander;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.AnimationMatrix;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.animation.folders.BasicAnimationFolder;
import org.ect.reo.animation.folders.TemporaryFolder;
import org.ect.reo.animation.generators.BasicFlashGenerator;
import org.ect.reo.animation.generators.AbstractFlashGenerator.IGeneratorListener;
import org.ect.reo.animation.parts.AnimationPlugin;
import org.ect.reo.colouring.ColouringFilter;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.util.Firing;

import com.anotherbigidea.flash.movie.Movie;


/**
 * This is the default Flash animator for Reo networks. It generates ALL animations.
 * 
 * @author Christian Krause
 *
 */
public class BasicFlashAnimator extends AbstractNetworkAnimator implements IFlashAnimator, IGeneratorListener {
	
	protected BasicFlashGenerator generator;
	protected AnimationExpander expander;
	protected AnimationMatrix animations;
	
	protected BasicAnimationFolder folder;
	protected File styleSheet;
	
	/**
	 * Default constructor.
	 * @param page Workbench page.
	 */
	public BasicFlashAnimator(IWorkbenchPage page) {
		super(page);
		expander = new AnimationExpander();
		generator = new BasicFlashGenerator();
		generator.addListener(this);
		
		try {
			styleSheet = TemporaryFolder.findEclipseResource(AnimationPlugin.ID, "animation.css");
		} catch (Throwable e) {
			asyncOpenError("Can't find style sheet file.");
		}
		
	}
	
	
	/**
	 * Create a new animation folder. Subclasses may override this.
	 */
	protected BasicAnimationFolder createAnimationFolder(String name) {
		return new BasicAnimationFolder(name);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.AbstractNetworkAnimator#initAnimationGeneration(org.ect.reo.diagram.view.util.NetworkView, boolean)
	 */
	@Override
	protected void initGeneration(NetworkView view, Firing trace, boolean isNewNetwork) {
		
		// Extract the network.
		Network network = view.getNetwork();
		
		// Create new temporary folder.
		String name = getNetworkName(network);
		folder = createAnimationFolder(trace!=null ? "Trace: " + name : name);
		folder.setScaleFactor(ReoPreferences.getScaleFactor());
				
		// Create an empty animation.
		animations = new AnimationMatrix();
		animations.add(new AnimationList());
		generator.setNetworkView(view);
		generator.animate(animations, new NullProgressMonitor());
		
	}
	
	
	/**
	 * Update the Flash animation. This generates the 
	 * flash code and writes it to the temporary file.
	 */
	@Override
	protected void generate(NetworkView view, Firing firing, IProgressMonitor monitor) {
		
		// Extract the network.
		final Network network = view.getNetwork();
		
		Reo.debug("\n=== GENERATING ANIMATIONS FOR: " + getNetworkName(network) + " ===\n");
		monitor.beginTask("Generating animations", 100);
		
		network.setColouringEngine(getColouringEngine());
		
		// Compute the animation table.
		AnimationTable table;
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 85);
		if (firing!=null) {
			table = firing.computeAnimations(subMonitor);
		} else {
			table = network.getAnimationTable(subMonitor);
		}
		if (table==null || monitor.isCanceled()) return;
		
		// Filter the animation table.
		filterAnimationTable(table, new SubProgressMonitor(monitor, 3));
		if (monitor.isCanceled()) return;
		
		// Expand the animations.
		expander.setRemoveNoFlow(!ReoPreferences.showNoFlowAnims());
		expander.setRemoveDuplicateFlow(!ReoPreferences.showNoFlowAnims());
		expander.setRemovePartial(!ReoPreferences.showNoFlowAnims());
		expander.setMaxLength(ReoPreferences.getMaxAnimationLength());
		expander.setMaxCount(ReoPreferences.getMaxAnimationCount());
		
		animations = new AnimationMatrix(expander.expand(table, new SubProgressMonitor(monitor, 5)));
		if (monitor.isCanceled()) return;
		
		// Generate the Flash animations.
		generator.setShowNoFlow(ReoPreferences.showNoFlowAnims());
		generator.animate(animations, new SubProgressMonitor(monitor, 6));
		
		// Finish the animation generation.
		finishFolder();
	    
		// Clean up.
		System.gc();
		monitor.worked(1);
		monitor.done();
		
	}
	
	
	/**
	 * Filter an animation table according to the Reo preferences.
	 * @param table Animation table that should be filtered.
	 * @param monitor A progress monitor.
	 */
	protected void filterAnimationTable(AnimationTable table, IProgressMonitor monitor) {
		
		// Remove no-flow animations etc.
		monitor.beginTask("Filter animation table", 3);
		
		int removed = ColouringFilter.removeEmptyColourings(table);
		if (removed>0) Reo.debug("\nRemoved " + removed + " empty colourings.");
		monitor.worked(1);
		
		if (!ReoPreferences.showNoFlowAnims()) {
			removed = ColouringFilter.removeNoFlowColourings(table);
			if (removed>0) Reo.debug("\nRemoved " + removed + " no-flow colourings.");
			monitor.worked(1);
			
			removed = ColouringFilter.removeDuplicateFlows(table);
			if (removed>0) Reo.debug("\nRemoved " + removed + " duplicate flow colourings.");
			monitor.worked(1);			
		} else {
			monitor.worked(2);
		}
		
		Reo.debug("\nComputed LTS has " + table.getAllTables().size() + " states.");
		//AnimationPrinter printer = new AnimationPrinter();
		//Reo.debug(printer.printTable(table) + "\n");
		monitor.worked(1);
		
		monitor.done();
		
	}
	
	
	/**
	 * This finishes the animation generation. It finishes the animation
	 * folder and handles possible exceptions by opening an error dialog.
	 */
	protected void finishFolder() {
		try {
			folder.finish();
		} catch (IOException e) {
			asyncOpenError("Error finishing animations: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Compute a name of the network.
	 */
	protected String getNetworkName(Network network) {
		String name = null;
		for (Connector connector : network.getConnectors()) {
			if (connector.getName()!=null && !connector.getName().equals("")) {
				name = connector.getName() + " (Network)";
				break;
			}
		}
		if (name==null) {
			name = network.toString();
		}
		return name;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.generators.AbstractFlashGenerator.IGeneratorListener#movieGenerated(com.anotherbigidea.flash.movie.Movie, int)
	 */
	public void movieGenerated(Movie movie, int index) {
		try {
			// Either start the generation or add a new movie.
			if (folder.isFinished()) {
				folder.start(movie, styleSheet);
			} else {
				folder.addAnimation(animations.get(index), movie);
			}
		} catch (IOException e) {
			asyncOpenError("Error saving movie: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see org.ect.reo.animation.animators.IFlashAnimator#getFolder()
	 */
	public BasicAnimationFolder getFolder() {
		return folder;
	}
	
	/* (non-Javadoc)
	 * @see org.ect.reo.animation.animators.IFlashAnimator#getURL()
	 */
	public URL getURL() {
		if (folder==null) return null;
		try {
			return folder.getChild(folder.getIndex().toString()).toURI().toURL();
		} catch (MalformedURLException e) {
			asyncOpenError("Error getting index file: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.AbstractViewAnimator#dispose()
	 */
	@Override
	public void dispose() {
		if (generator!=null) {
			generator.removeListener(this);
		}
		super.dispose();
	}
	
}
