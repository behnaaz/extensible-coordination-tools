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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.ui.IWorkbenchPage;
import org.ect.reo.Network;
import org.ect.reo.Reo;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.AnimationMatrix;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.animation.folders.BasicAnimationFolder;
import org.ect.reo.animation.folders.GuidedAnimationFolder;
import org.ect.reo.animation.generators.PreviewFlashGenerator;
import org.ect.reo.colouring.ColouringEngine;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.util.Firing;



public class GuidedFlashAnimator extends BasicFlashAnimator implements LocationListener {
	
	// Browser of the animation view.
	protected Browser browser;
	
	// Flash generator for the previews.
	protected PreviewFlashGenerator previewGenerator;
	
	// Current animation.
	protected Animation currentAnimation;
	
	// Current animation table.
	protected AnimationTable currentTable;
	
	
	/**
	 * Default constructor for the guided animator.
	 * @param page Workbench page.
	 * @param browser Browser of the animation view.
	 */
	public GuidedFlashAnimator(IWorkbenchPage page, Browser browser) {
		super(page);
		
		// Listen to browser events.
		this.browser = browser;
		browser.addLocationListener(this);
		
		// Create a preview generator.
		this.previewGenerator = new PreviewFlashGenerator();
		this.previewGenerator.addListener(this);
		
		// Setup the default generator.
		super.generator.setCleanUpTokens(false);

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.BasicFlashAnimator#createAnimationFolder(java.lang.String)
	 */
	@Override
	protected BasicAnimationFolder createAnimationFolder(String name) {
		// Create a special folder for guided animations.
		return new GuidedAnimationFolder(name);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.BasicFlashAnimator#initAnimationGeneration(org.ect.reo.diagram.view.util.NetworkView, boolean)
	 */
	@Override
	protected void initGeneration(NetworkView view, Firing trace, boolean isNewNetwork) {
		
		// Extract the network.
		Network network = view.getNetwork();
		
		// Create new temporary folder.
		String name = getNetworkName(network);
		folder = createAnimationFolder(trace!=null ? "Trace: " + name : name);
		folder.setScaleFactor(ReoPreferences.getScaleFactor());
		
		// Reset generator if the network changed.
		if (isNewNetwork) {
			generator.clearTokens();
			currentAnimation = null;
		}

		// Create the current or an empty animation.
		AnimationList list = new AnimationList();
		if (currentAnimation!=null) list.add(currentAnimation);
		
		animations = new AnimationMatrix();
		animations.add(list);
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
		Network network = view.getNetwork();
		
		Reo.debug("\n=== GENERATING GUIDED ANIMATIONS FOR: " + getNetworkName(network) + " ===\n");
		Reo.debug("Current " + currentAnimation);
		monitor.beginTask("Generating next step", 100);		
		
		ColouringEngine engine = getColouringEngine();
		engine.setMaxSteps(1);
		engine.setCurrent(currentAnimation);
		
		network.setColouringEngine(engine);
		//network.updateColouringTable();
		
		// Compute the animation table.
		currentTable = network.getAnimationTable(new SubProgressMonitor(monitor, 90));
		if (currentTable==null || monitor.isCanceled()) return;
		Reo.debug("\nComputed colouring table has " + currentTable.size() + " entries.");
		
		// Filter the animation table.
		filterAnimationTable(currentTable, new SubProgressMonitor(monitor, 5));
		if (monitor.isCanceled()) return;
		
		// Generate the preview animations for the next steps.
		animations = new AnimationMatrix();
		for (int i=0; i<currentTable.size(); i++) {
			AnimationList list = new AnimationList();
			list.add(currentTable.get(i));
			animations.add(list);
		}
		
		// Generate the previews.
		previewGenerator.setNetworkView(view);
		previewGenerator.setShowNoFlow(ReoPreferences.showNoFlowAnims());
		previewGenerator.animate(animations, new SubProgressMonitor(monitor, 4));
		
		// Finish the animation generation.
		finishFolder();
		
		// Clean up.
		if (animations.isEmpty()) {
			generator.clearTokens();
		}
		System.gc();
		monitor.worked(1);
		monitor.done();
		
	}
	
	
	/**
	 * Switch to the next animation.
	 */
	protected void switchToNext(int index) {
		
		// Check if the index is ok.
		if (currentTable==null || currentTable.size()<=index) return;
		currentAnimation = currentTable.get(index);
		
		// Update the animations and notify all listeners. 
		if (getNetworkView()!=null) {
			update(getNetworkView().getNetwork(), null);			
		}
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.AbstractViewAnimator#dispose()
	 */
	public void dispose() {
		// Stop listing to the browser events.
		browser.removeLocationListener(this);
		super.dispose();
	}
	
	
	/* ------ Browser listener. ------- */
	
	public void changing(LocationEvent event) {
		
		int index = event.location.lastIndexOf("next_");
		if (index<0) return;
		
		try {
			String id = event.location.substring(index);
			int next = Integer.parseInt(id.replaceFirst("next_", ""));
			switchToNext(next-1);
			event.doit = false;
		} catch (Throwable t) {
			System.err.println(t);
		}
	}
	
	public void changed(LocationEvent event) {
		// Not used.
	}
	
}
