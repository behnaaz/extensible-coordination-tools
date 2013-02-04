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
package org.ect.reo.diagram.providers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Cursors;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

/**
 * @generated NOT
 * @author Christian Krause
 */
public abstract class AbstractDropTargetListener extends AbstractTransferDropTargetListener {
	
	private TransactionalEditingDomain domain;

	/**
	 * Default constructor.
	 * 
	 * @param viewer EditPart viewer of the editor.
	 * @param domain Transactional EditingDomain of the editor. 
	 */
	public AbstractDropTargetListener(EditPartViewer viewer, TransactionalEditingDomain domain) {
		super(viewer, LocalSelectionTransfer.getInstance());
		this.domain = domain;
	}

	/**
	 * Check whether the current selection can be dropped
	 * to a given editpart. Subclasses must implement this.
	 */
	protected abstract boolean canDrop(ISelection selection, EditPart editPart);

	/**
	 * Perform the actual drop.
	 */
	protected abstract void doDrop(ISelection selection, EditPart editPart);
	

	
	/**
	 * Checks whether the current selection can be dropped.
	 * If so, it return the current editpart, otherwise null.
	 * 
	 * @return The currently hovered editpart.
	 */
	private EditPart calculateTargetEditPart() {
	
		// Get the selection.
		ISelection selection = LocalSelectionTransfer.getInstance().getSelection();
	
		EditPart editPart = getViewer().findObjectAtExcluding(getDropLocation(), getExclusionSet());
		
		if (editPart!=null && canDrop(selection, editPart)) {
			return editPart;
		}
	
		return null;
	
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDrop()
	 */
	@Override
	protected void handleDrop() {

		getViewer().setCursor(Cursors.WAIT);

		updateTargetEditPart();

		final ISelection selection = LocalSelectionTransfer.getInstance().getSelection();
				
		// Do the drop within a command.
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(domain, "Drop object", null) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				doDrop(selection, getTargetEditPart());
				return CommandResult.newOKCommandResult();
			}
			
		};
		
		// Execute the command.
		try {
			command.execute(new NullProgressMonitor(), null);
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		getCurrentEvent().detail = DND.DROP_NONE;
				
		getViewer().setCursor(null);
		// selectAddedViews();
	}

	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#dragEnter(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragEnter(DropTargetEvent event) {
		super.dragEnter(event);
		handleDragEnter(); // called to properly initialize the effect
	}

	
	/**
	 * Called whenever the User enters the target. 
	 */
	protected void handleDragEnter() {
		handleDragOver();
	}	

	/**
	 * The purpose of a template is to be copied. 
	 * Therefore, the drop operation can't be
	 * anything but <code>DND.DROP_COPY</code>.
	 * 
	 * @see AbstractTransferDropTargetListener#handleDragOperationChanged()
	 */
	@Override
	protected void handleDragOperationChanged() {
		super.handleDragOperationChanged();
		getCurrentEvent().detail = DND.DROP_COPY;
	}

	/**
	 * The purpose of a template is to be copied. 
	 * Therefore, the Drop operation is set to
	 * <code>DND.DROP_COPY</code> by default.
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDragOver()
	 */
	@Override
	protected void handleDragOver() {
		super.handleDragOver();
		getCurrentEvent().detail = DND.DROP_COPY;
		getCurrentEvent().feedback = DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND;
	}


	/**
	 * Make sure the the object can be dropped.
	 */
	@Override
	public boolean isEnabled(DropTargetEvent event) {
		if (super.isEnabled(event)) {
			boolean result = calculateTargetEditPart() != null;
			return result;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#updateTargetEditPart()
	 */
	@Override
	protected void updateTargetEditPart() {
		setTargetEditPart(calculateTargetEditPart());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
	 */
	@Override
	protected Request createTargetRequest() {
		DropObjectsRequest request =  new DropObjectsRequest();
		request.setAllowedDetail(getCurrentEvent().operations);
		request.setRequiredDetail(DND.DROP_COPY);
		return request;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#updateTargetRequest()
	 */
	@Override
	protected void updateTargetRequest() {
		// not needed anymore.
	}
		
	/**
	 * Get the editing domain.
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return domain;
	}

}