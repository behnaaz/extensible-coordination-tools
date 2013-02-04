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

import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPage;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.animation.parts.AnimationPlugin;
import org.ect.reo.colouring.AbstractColouringEngine;
import org.ect.reo.colouring.ColouringEngine;
import org.ect.reo.colouring.DefaultColouringEngine;
import org.ect.reo.colouring.StepwiseColouringEngine;
import org.ect.reo.diagram.part.ReoDiagramEditor;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.ui.ReoUIPlugin;
import org.ect.reo.util.Firing;



/**
 * Abstract class for an animator of Reo networks. This handles the animation
 * job, the colouring engine including updates when the user is changes something
 * in the preferences.
 * 
 * @author Christian Krause
 * 
 */
public abstract class AbstractNetworkAnimator extends AbstractViewAnimator implements IPropertyChangeListener {
	
	// The network view to be animated.
	private NetworkView networkView;
	
	private ColouringEngine engine;
	
	// Cached connectors.
	private HashSet<Connector> cached;
	
	// Animation job.
	protected GenerateAnimationsJob animateJob;
	
	// Job is running?
	protected boolean isRunning;
	
	
	/**
	 * Default constructor.
	 * @param page Workbench page.
	 */
	protected AbstractNetworkAnimator(IWorkbenchPage page) {	
		super(page);
		this.cached  = new HashSet<Connector>();
		
		// Listen to preference changes.
		ReoUIPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(this);
		setupEngine();
	}
	
	/**
	 * Initialize the animation generation.
	 * @param view The network view.
	 * @param trace Trace.
	 * @param isNewNetwork Indicates whether the network has changed.
	 */
	protected abstract void initGeneration(NetworkView view, Firing trace, boolean isNewNetwork);
	
	/**
	 * Do the actual animation generation.
	 */
	protected abstract void generate(NetworkView view, Firing firing, IProgressMonitor monitor);
	
	
	/**
	 * Call-back function that is needed to determine whether
	 * an editor is valid in the sense that it carries a model
	 * that this animation understands. We only support
	 * ReoDiagramEditors here.
	 */
	public boolean isValidEditor(DiagramEditor editor) {
		return (editor instanceof ReoDiagramEditor);
	}
	
	
	/**
	 * Call-back function that is needed to determine the root view.
	 * In this case we want connector nodes as root.
	 */
	public boolean isValidRoot(View view) {
		
		Connector connector = null;
		Component component = null;
		
		if (view.getElement() instanceof Connector) {
			connector = (Connector) view.getElement();
		}

		if (view.getElement() instanceof Component) {
			component = (Component) view.getElement();
		}
		
		// Only top-level connectors / components.
		if (connector!=null && !connector.isSubConnector()) return true;
		if (component!=null && !component.isInternal()) return true;		
		
		return false;
		
	}
	
	
	/**
	 * Update the Flash animation. This generates the 
	 * flash code and writes it to the temporary file.
	 */
	public void update(View view) {
		
		// Create a new network.
		Network network = null;
		Module module = null;
		if (view.getElement() instanceof Connector) {
			Connector connector = (Connector) view.getElement();
			network = new Network(connector);
			module = connector.getModule();
		}
		if (view.getElement() instanceof Component) {
			Component component = (Component) view.getElement();
			network = new Network(component);
			module = component.getModule();
		}
		
		if (module==null || network==null) {
			return; // Abort.
		}
		
		// Module scope?
		if (ReoPreferences.useModuleScope()) {
			network = new Network(module);
		}
		
		// Check if all component ends are connected.
		for (Component component : network.getAllComponents()) {
			for (PrimitiveEnd end : component.getAllEnds()) {
				if (end.getNode()==null) return; // Abort.
			}
		}
		
		// Do the actual job.
		update(network, null);
		
	}
	
	
	
	/**
	 * Update the Flash animation. This generates the 
	 * flash code and writes it to the temporary file.
	 */
	public void update(Network network, Firing trace) {
		
		// Is it a new network?
		boolean isNewNetwork = (networkView==null || networkView.getNetwork()!=network);
		
		// Initialize animation job.
		if (animateJob==null) {
			animateJob = new GenerateAnimationsJob();
			isRunning = false;
		}
		
		if (isRunning) {
			// Cancel only if it is a new network.
			if (isNewNetwork) {
				animateJob.cancel();
			}
		} else {
			isRunning = true;
	    	try {	    		
	    		// Initialize the animation generation.
	    		networkView = new NetworkView(network);
				initGeneration(networkView, trace, isNewNetwork);
	    	} 
	    	catch (Throwable e) {
	    		openError("Error initializing animation generation: " + e);
	    		Reo.logError("Error initializing animation generation", e);
	    		isRunning = false;
	    		return;
	    	}
			
			// Now schedule the actual job.
			animateJob.setNetworkView(networkView);
			animateJob.setFiring(trace);
			animateJob.schedule();
		}
		
		notifyAnimationUpdate();

	}
	
	protected NetworkView getNetworkView() {
		return networkView;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.IViewAnimator#cancel()
	 */
	public void cancel() {
		if (animateJob!=null && isRunning) {
			animateJob.cancel();
		}
	}
	
	
	public ColouringEngine getColouringEngine() {
		if (engine==null) setupEngine();
		return engine;
	}
	
	
	/*
	 * Setup the colouring engine.
	 */
	private void setupEngine() {
		
		engine = ReoPreferences.getColouringEngine();
		if (engine==null) {
			engine = new StepwiseColouringEngine();
		}
		
		engine.setColours(ReoPreferences.getColours());
		engine.setMaxSteps(ReoPreferences.getMaxAnimationLength());
		engine.setIgnoreNoFlow(!ReoPreferences.showNoFlowAnims());
		
		//Reo.debug("Setting engine properties: colours=" + ReoPreferences.getColours() +
		//					", maxSteps=" + ReoPreferences.getMaxAnimationLength() + 
		//					", noFlow=" + ReoPreferences.showNoFlowAnims() +
		//					", multiCore=" + ReoPreferences.enableMultiCoreSupport());

		if (engine instanceof AbstractColouringEngine) {
			((AbstractColouringEngine) engine).setTraversalType(ReoPreferences.getTraversalType());
		}

		if (engine instanceof DefaultColouringEngine) {
			((DefaultColouringEngine) engine).setMultiThreaded(ReoPreferences.enableMultiCoreSupport());
		}
		
		cached.clear();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		setupEngine();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.AbstractViewAnimator#dispose()
	 */
	@Override
	public void dispose() {
		if (animateJob!=null) {
			animateJob.cancel();
		}
		ReoUIPlugin.getInstance().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}
	
	
	/*
	 * Job for the animation generations.
	 */
	public class GenerateAnimationsJob extends Job {
		
		private NetworkView view;
		private Firing firing;
		
		public GenerateAnimationsJob() {
			super("Generating animations");
			setPriority(BUILD);
		}		
		
	    public IStatus run(IProgressMonitor monitor) {
	    	isRunning = true;
	    	try {    		
	    		// Generate the animations.
	    		generate(view, firing, monitor);
	    		
	    		// Mark connectors as cached.
	    		cached.addAll(networkView.getNetwork().getConnectors());
	    	}
	    	catch (Throwable e) {
	    		return new Status(IStatus.ERROR, AnimationPlugin.ID, IStatus.ERROR, "Generating animations failed: " + e, e);
	    	} finally {
	    		isRunning = false;
	    	}
		    return Status.OK_STATUS;
	    }
	    
		public void setNetworkView(NetworkView view) {
			this.view = view;
		}
		
		public void setFiring(Firing firing) {
			this.firing = firing;
		}
	};

}
