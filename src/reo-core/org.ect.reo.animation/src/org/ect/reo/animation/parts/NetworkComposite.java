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
package org.ect.reo.animation.parts;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.ect.reo.Network;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.AnimationMatrix;
import org.ect.reo.animation.generators.BasicFlashGenerator;
import org.ect.reo.animation.generators.PreviewFlashGenerator;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.util.Firing;

import com.anotherbigidea.flash.movie.Movie;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class NetworkComposite extends Composite {
	
	// Browser instance:
	private Browser browser;
	
	// SWF file:
	private File swf;
	
	// Preview generator:
	private PreviewFlashGenerator previewGenerator;
	
	// Basic generator:
	private BasicFlashGenerator basicGenerator;

	private boolean showNoFlow = false;
	
	/**
	 * Default constructor.
	 * @param parent Parent composite.
	 * @param style Style byte.
	 */
	public NetworkComposite(Composite parent, int style) {
		
		super(parent, style);
		setLayout(new FillLayout());
		
		previewGenerator = new PreviewFlashGenerator();
		previewGenerator.setShowNoFlow(true);
		previewGenerator.setCleanUpTokens(true);
		
		basicGenerator = new BasicFlashGenerator();
		basicGenerator.setShowNoFlow(false);
		basicGenerator.setCleanUpTokens(false);
		
		try {
	    	browser = new Browser(this, SWT.MOZILLA);
	    } catch (SWTError e1) {
	    	try {
	    		browser = new Browser(this, SWT.NONE);
	    	} catch (SWTError e2) {
		        Link link = new Link(this, SWT.NONE);
		        link.setText("Error loading browser. See <a href=\"http://reo.project.cwi.nl\">http://reo.project.cwi.nl</a> for help.");
		        browser = null;
	    	}
	    }
	}
	
	/**
	 * Set the network to be displayed.
	 * @param network Network.
	 */
	public void setNetwork(Network network) {

		if (browser==null) return;
		deleteSwf();
		
		if (network!=null) {
			
			NetworkView view = new NetworkView(network);
			previewGenerator.setNetworkView(view);
			basicGenerator.setNetworkView(view);
			
			createSwf(null, false);
			browser.setUrl(swf.getAbsolutePath());
			
		} else {
			browser.setText("");
		}
		
	}
	
	/**
	 * Set the animation / colouring to be displayed.
	 * @param animation Animation.
	 * @param showTokens Show-tokens-flag.
	 */
	public void setAnimation(Animation animation, boolean showTokens) {
		if (browser==null) return;
		deleteSwf();
		createSwf(animation, showTokens);
		browser.setUrl(swf.getAbsolutePath());
	}
	
	/**
	 * Set the animation / colouring to be displayed.
	 * @param animation Animation.
	 */
	public void setAnimation(Animation animation) {
		setAnimation(animation, false);
	}
	
	/**
	 * Set the trace to be shown.
	 * @param trace Trace.
	 * @param beginIndex Begin index.
	 * @param endIndex End index.
	 */
	public void setTrace(Firing trace, int beginIndex, int endIndex) {
		
		// Compute animations:
		AnimationList animations = trace.computeAnimationList(new NullProgressMonitor(), showNoFlow);
		AnimationList skip = new AnimationList();
		AnimationList show = new AnimationList();
		
		for (int i=0; i<beginIndex; i++) {
			skip.add(animations.get(i));
		}
		for (int i=beginIndex; i<endIndex; i++) {
			show.add(animations.get(i));
		}

		// Clear tokens first:
		clearTokens();
		
		// Skip the first part:
		AnimationMatrix matrix = new AnimationMatrix();
		matrix.add(skip);		
		basicGenerator.animate(matrix, new NullProgressMonitor());

		basicGenerator.setShowNoFlow(showNoFlow);
		
		matrix.clear();
		matrix.add(show);
		basicGenerator.animate(matrix, new NullProgressMonitor());
		
		// Update the Swf:
		deleteSwf();
		saveSwf(basicGenerator.getLastMovie());
		browser.setUrl(swf.getAbsolutePath());
		
	}
	
	/*
	 * Generate Swf file for the current network.
	 */
	private void createSwf(Animation animation, boolean showTokens) {
		
		// Create animation matrix:
		AnimationList list = new AnimationList();
		if (animation!=null) list.add(animation);
		AnimationMatrix matrix = new AnimationMatrix();
		matrix.add(list);
		
		// Generate the Flash code:
		BasicFlashGenerator generator = showTokens ? basicGenerator : previewGenerator;
		generator.animate(matrix, new NullProgressMonitor());
		
		// Save the Swf:
		saveSwf(generator.getLastMovie());
		
	}

	/*
	 * Save Swf to a temporary file.
	 */
	private void saveSwf(Movie movie) {
		try {
			swf = File.createTempFile("anim", ".swf");
			movie.write(swf.getAbsolutePath());
		} catch (IOException e) {
			browser.setText("Error saving temporary data");
			return;
		}
	}
	
	/**
	 * Clear tokens. This only updates the Flash generator.
	 */
	public void clearTokens() {
		previewGenerator.clearTokens();
		basicGenerator.clearTokens();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		deleteSwf();
	}
	
	/*
	 * Delete the current Swf file.
	 */
	private void deleteSwf() {
		if (swf!=null && swf.exists()) {
			swf.delete();
		}		
	}
	
	/*
	 * Refresh the browser used for displaying the Network/Animation. 
	 */
	public void refreshBrowser() {
		if (browser!=null) browser.refresh();
	}

	/*
	 * Save the generate SWF file to destination 
	 */
	public boolean saveSWF(File destination) throws CoreException {
		if (swf==null || !swf.exists()) {
			return false;
		}
		
		IFileSystem fileSystem = EFS.getLocalFileSystem();
		IFileStore swf_file=fileSystem.getStore(swf.toURI());
		IFileStore destination_file=fileSystem.getStore(destination.toURI());
		swf_file.copy(destination_file, EFS.OVERWRITE, null);
		return true;
	}

	/**
	 * Getter for showNoFlow flag
	 */
	public boolean getShowNoFlow() {
		return showNoFlow;
	}

	/**
	 * Setter for showNoFlow flag
	 */
	public void setShowNoFlow(boolean showNoFlow) {
		this.showNoFlow = showNoFlow;
	}
	
}
