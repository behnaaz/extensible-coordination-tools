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

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.ect.reo.Network;
import org.ect.reo.Reo;
import org.ect.reo.animation.animators.AbstractNetworkAnimator;
import org.ect.reo.animation.animators.BasicFlashAnimator;
import org.ect.reo.animation.animators.GuidedFlashAnimator;
import org.ect.reo.animation.animators.IFlashAnimator;
import org.ect.reo.animation.animators.IViewAnimatorListener;
import org.ect.reo.util.Firing;


/**
 * Reo animation view.
 * @author Christian Krause
 * @generated NOT
 */
public class AnimationView extends ViewPart {
	
	// Id of this view.
	public static final String ID = "org.ect.reo.animation.parts.AnimationView";
	
	// Browser instance.
	private Browser browser;
	
	// Actions.
	private ShowWebReoAction webReo;
	private GuidedAnimationAction guided;
	private SyncAction synced;
	
	// Animator.
	private IFlashAnimator animator;
	
	
	/**
	 * Get the current instance of the animation view.
	 * @return The current instance.
	 */
	public static AnimationView getInstance() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		return (AnimationView) window.getActivePage().findView(ID);
	}
	
	
	/**
	 * Show a trace in the animation view.
	 * @param network The network.
	 * @param trace The trace.
	 */
	public void showTrace(Network network, Firing trace) {
		
		// Open the animation view first.
		try {
			getSite().getWorkbenchWindow().getActivePage().showView(ID);
		} catch (PartInitException e) {
			String message = "Error opening animation view.";
			MessageDialog.openError(getSite().getShell(), "Show Trace", message);
			Reo.logError(message, e);
			return;	
		}
		
		// Generate the animation.
		initAnimator(getSite().getWorkbenchWindow().getActivePage(), true, false);
		((AbstractNetworkAnimator) animator).update(network, trace);
		animator.setEnabled(false);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		
		try {
			browser = new Browser(parent, SWT.NONE);
			browser.addLocationListener(locationListener);
			System.out.println("Using " + browser.getBrowserType() + " browser engine in Reo animation view.");
		} catch (SWTError e) {
			AnimationPlugin.getInstance().logError("Browser cannot be initialized.", e);
			MessageBox messageBox = new MessageBox(parent.getShell(), SWT.ICON_ERROR | SWT.OK);
			messageBox.setMessage("Browser cannot be initialized. See the error log for more information.");
			messageBox.open();
			return;
		}
	    
		// Add actions to the view.
		IToolBarManager manager = getViewSite().getActionBars().getToolBarManager();
		
		// Synchronize animation action.
		synced = new SyncAction();
		synced.addPropertyChangeListener(propertyListener);
		manager.add(synced);
		
		// Guided animation action.
		guided = new GuidedAnimationAction();
		guided.addPropertyChangeListener(propertyListener);
		manager.add(guided);
		//manager.add(new Separator());
				
		// Back, forward, refresh, home actions.
		//BrowserNavigationAction.addDefaultBrowserActions(manager, browser, ShowWebReoAction.WEB_REO_URL);
		
		// WebReo action.
		webReo = new ShowWebReoAction(browser, "about:blank");
		manager.add(webReo);
				
		try {
			URL bundleURL = FileLocator.find(Platform.getBundle(AnimationPlugin.ID), 
							Path.fromOSString("default.html"), null);
			URL fileURL = FileLocator.toFileURL(bundleURL);
			browser.setUrl(fileURL.toString());
		} catch (IOException e) {
			// Not critical.
		}
		
		// Initialize animator if possible.
		initAnimator(getSite().getPage(), isSynced(), isGuided());
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		// Listen to part events.
		site.getPage().addPartListener(partListener);		
	}
	
	
	/*
	 * Initialize animator.
	 */
	private void initAnimator(IWorkbenchPage page, boolean enabled, boolean guided) {
		
		View last = null;
		
		// Dispose the old animator.
		if (animator!=null) {
			last = animator.getView();
			animator.removeAnimationListener(animationListener);
			animator.dispose();
		}
		
		// Create a new one.
		if (guided) {
			animator = new GuidedFlashAnimator(page, browser);
		} else {
			animator = new BasicFlashAnimator(page);			
		}
		
		// Listen to events from the animator.
		animator.addAnimationListener(animationListener);
		animator.setEnabled(enabled);
		animator.setView(last);
		animator.update();
		
	}
	
	/**
	 * Get the currently used animator.
	 * @return Current animator.
	 */
	public IFlashAnimator getAnimator() {
		return animator;
	}
	
	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		browser.setFocus();
	}
	
	/**
	 * Dispose the view.
	 */
	@Override
	public void dispose() {
		// Remove all listeners.
		if (animator!=null) {
			animator.removeAnimationListener(animationListener);
			animator.dispose();
		}
		guided.removePropertyChangeListener(propertyListener);
		synced.removePropertyChangeListener(propertyListener);
		getSite().getPage().removePartListener(partListener);
		super.dispose();
	}
	
		
	public void setGuided(boolean guided) {
		this.guided.setChecked(guided);
	}
	
	public boolean isGuided() {
		return guided.isChecked();
	}
	
	public void setSynced(boolean synced) {
		this.synced.setChecked(synced);
	}
	
	public boolean isSynced() {
		return synced.isChecked();
	}
	
	// ------------- Listeners ---------- //
	
	/**
	 * Animation listener.
	 */
	private IViewAnimatorListener animationListener = new IViewAnimatorListener() {
		
		public void animationUpdated() {
			if (!webReo.isChecked() && animator.getURL()!=null) {
				browser.setUrl(animator.getURL().toString());
			}
		}
		
		public void animationCleared() {
			// Not used.
		}
	};
	
	/**
	 * Worbench part listener.
	 */
	private static IPartListener partListener = new IPartListener() {
		
		public void partActivated(IWorkbenchPart part) {
			updateAnimator(part);
		}

		public void partDeactivated(IWorkbenchPart part) {
			updateAnimator(part);
		}

		public void partBroughtToTop(IWorkbenchPart part) {
		}

		public void partOpened(IWorkbenchPart part) {
		}
		
		public void partClosed(IWorkbenchPart part) {
		}
		
		private void updateAnimator(IWorkbenchPart part) {
			AnimationView view = AnimationView.getInstance();
			if (view!=null && view.getAnimator()!=null) {
				// Disable the animator:
				boolean enabled = view.isSynced() && part.getSite().getPage().isPartVisible(view);
				view.getAnimator().setEnabled(enabled);
			}
		}
		
	};
		
	/**
	 * Property change listener.
	 */
	private IPropertyChangeListener propertyListener = new IPropertyChangeListener() {
		
		public void propertyChange(PropertyChangeEvent event) {
			if (event.getSource()==guided) {
				initAnimator(getSite().getPage(), isSynced(), isGuided());
			} else if (event.getSource()==synced && animator!=null) {
				animator.setEnabled(synced.isChecked());
				if (!synced.isChecked()) {
					animator.cancel();
				} else {
					animator.update();					
				}
			}
		}
	};
	
	/**
	 * Location listener.
	 */
	private LocationListener locationListener = new LocationListener() {
		
		public void changing(LocationEvent event) {
			if (event.location.endsWith("cancel") && animator!=null) {
				animator.cancel();
				event.doit = false;
			}
		}
		
		public void changed(LocationEvent event) {
			// Not used.
		}

	};
}
