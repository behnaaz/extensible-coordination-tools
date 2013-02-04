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
import java.util.Iterator;
import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;


/**
 * Abstract class that is the basis for an animation generated
 * out of an GMF Diagram editor. This animation directly listens
 * to the workbench and triggers updating of the animation whenever
 * the selection changes or something in the diagram is changed.
 * @author Christian Krause
 *
 */
public abstract class AbstractViewAnimator implements IViewAnimator, IPartListener, ISelectionChangedListener {
	
	// Workbench page and diagram editor.
	private IWorkbenchPage page;
	private DiagramEditor editor;
	
	// Views to be animated.
	private View selectedView, rootView;
	
	// Animation listeners.
	private Set<IViewAnimatorListener> listeners;
	
	// Enabled-flag;
	private boolean enabled;
	
	/**
	 * Default constructor. Must be invoked by subclasses.
	 * @param page Workbench page carrying potential diagram editors.
	 */
	protected AbstractViewAnimator(IWorkbenchPage page) {
		this.page = page;
		this.editor = null;
		this.listeners = new HashSet<IViewAnimatorListener>();
		this.enabled = true;
		page.addPartListener(this);
		
		// Try to activate animation.
		if (page.getActivePart()!=null) {
			activate(page.getActivePart());
		}
	}
	
	/**
	 * Update the animation given a diagram element. This method
	 * must be implemented by subclasses.
	 * @param rootView The diagram element that should be animated.
	 */
	public abstract void update(View rootView);
	
	/**
	 * Check if an editor is valid, so that animations can be
	 * generated out of the content. A good way of doing this
	 * is to compare the editor's ID and an 'instanceof' test.
	 * @param editor Diagram editor to be checked.
	 * @return <code>True</code> if the editor is valid for this type of animation.
	 */
	public abstract boolean isValidEditor(DiagramEditor editor);

	
	/**
	 * Check if a diagram element is an appropriate root element for the 
	 * generated animation. This method must be implemented by subclasses.
	 * If this method returns <code>true</code>, the method <code>update()</code>
	 * will be invoked with that diagram element. The best way is to check
	 * the visual ID of the view and compare it to the IDs in the generated
	 * edit parts.
	 * @param view The diagram element to be checked.
	 * @return <code>True</code> if an animation can be generated.
	 */
	public abstract boolean isValidRoot(View view);
	
	
	/**
	 * Stop listening to page events.
	 */
	public void dispose() {
		page.removePartListener(this);
		deactivate();
	}
	
	
	// ----- Allow other classes to informed about animation events. ----- // 

	public void addAnimationListener(IViewAnimatorListener listener) {
		listeners.add(listener);
	}
	
	public void removeAnimationListener(IViewAnimatorListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}
	
	
	// ----- Listen to selection change events and update the animation ----- // 
	
	public void selectionChanged(SelectionChangedEvent event) {
		
		// Get selection.
		if (!(event.getSelection() instanceof IStructuredSelection)) return;
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		
		// Empty selection.
		if (selection.isEmpty()) return;
		
		// If not empty, check the first element.
		if (!(selection.getFirstElement() instanceof EditPart)) return;
		EditPart editpart = (EditPart) selection.getFirstElement();
		if (!(editpart.getModel() instanceof View)) return;
		
		View view = (View) editpart.getModel();
		
		// If it is already selected, stop. This fixes a bug.
		if (selectedView==view) return;
		selectedView = view;
		
		// Set the view:
		if (rootView!=view) {
			setView(view);
		}
		
		// Update?
		if (rootView==view) {
			update();
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.IViewAnimator#setView(org.eclipse.gmf.runtime.notation.View)
	 */
	public void setView(View view) {
		if (view==null || isValidRoot(view)) {
			this.rootView = view;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.IViewAnimator#getView()
	 */
	public View getView() {
		return rootView;
	}

	
	public void update() {
		if (enabled && rootView!=null) {
			// Do the actual update.
			update(rootView);
			notifyAnimationUpdate();
		}
	}
	
	protected void notifyAnimationUpdate() {
		// Notify listeners.
		Iterator<IViewAnimatorListener> it = listeners.iterator();
		while (it.hasNext()) it.next().animationUpdated();
	}
	
	protected DiagramEditor getDiagramEditor() {
		return editor;
	}
	
	protected IWorkbenchPage getWorkbenchPage() {
		return page;
	}
	
	protected Shell getShell() {
		return page.getActivePart().getSite().getShell();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.animators.IViewAnimator#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	protected void openError(String message) {
		MessageDialog.openError(getShell(), "Error", message);
	}
	
	protected void openWarning(String message) {
		MessageDialog.openWarning(getShell(), "Warning", message);
	}
	
	protected void openInfo(String message) {
		MessageDialog.openInformation(getShell(), "Information", message);
	}
	
	protected void asyncOpenError(final String message) {
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				openError(message);
			}
		});
	}
	
	
	// ------------------------------------------------------------ //
	// --- Notification                                         --- //
	// ------------------------------------------------------------ //
	
	
	// ----- Listen to workbench events and update the editor ----- // 
	
	public void partActivated(IWorkbenchPart part) {
		activate(part);
	}

	public void partBroughtToTop(IWorkbenchPart part) {
		activate(part);
	}

	public void partOpened(IWorkbenchPart part) {
		activate(part);
	}

	public void partClosed(IWorkbenchPart part) {
		if (part==editor) deactivate();
	}

	public void partDeactivated(IWorkbenchPart part) {
		if (part==editor) deactivate();
	}
	
	// ----- Activating / deactivating the selection listener. ----- //

	private void activate(IWorkbenchPart part) {
		if (part==editor) return;
		deactivate();
		if (part instanceof DiagramEditor && isValidEditor((DiagramEditor) part)) {
			editor = (DiagramEditor) part;
			editor.getDiagramGraphicalViewer().addSelectionChangedListener(this);
		}	
	}
		
	private void deactivate() {		
		if (editor!=null) {
			editor.getDiagramGraphicalViewer().removeSelectionChangedListener(this);
			editor = null;
		}
	}

}
