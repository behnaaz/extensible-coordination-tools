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
package org.ect.reo.ui.properties.helpers;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.ect.reo.Reo;
import org.ect.reo.diagram.part.ReoDiagramEditor;


/**
 * @author Christian Krause
 */
public abstract class ReoPropertySection extends AbstractPropertySection {
	
	// Suspended flag:
	private boolean suspended = false;
	
	// Reo diagram editor:
	private ReoDiagramEditor editor;
	
	// Currently active shell:
	private Shell shell;
	
	// A modify listener for text widgests:
	private ModifyListener listener = new ModifyListener() {
    	public void modifyText(ModifyEvent event) {
    		if (!suspended) commit();
    	}
    };
    
    /**
     * Add a listener to a text widgets that updates the model automatically.
     * @param text Text widgets.
     */
    protected void addTextListener(Text text) {
    	text.addModifyListener(listener);
    }

    protected void addTextListener(StyledText text) {
    	text.addModifyListener(listener);
    }

    /**
     * Remove a listener from a text widgets.
     * @param text Text widgets.
     */
    protected void removeTextListener(Text text) {
    	text.removeModifyListener(listener);
    }
    
    protected void removeTextListener(StyledText text) {
    	text.removeModifyListener(listener);
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public final void refresh() {
    	suspended = true;
    	updateViews();
    	suspended = false;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    @Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (part instanceof ReoDiagramEditor) editor = (ReoDiagramEditor) part;
		else editor = null;
		shell = part.getSite().getShell();
	}
    
    /**
     * Commit the changes to the model.
     */
    protected void commit() {
		executeAsCommand("Change Property", new Runnable() {
			public void run() {
				// Ask the subclass to do the actual commit.
				updateProperties();
			}			
		});	
    }
    
    /**
     * Execute an action as a transactional command.
     * @param name Name of the action.
     * @param runnable Action.
     */
    protected void executeAsCommand(String name, final Runnable runnable) {
    	
    	Assert.isTrue(editor!=null);
    	
    	// Get the editing domain and the affected file.
    	TransactionalEditingDomain domain = editor.getEditingDomain();
    	List<IFile> files = new Vector<IFile>();
    	if (editor.getEditorInput() instanceof IFileEditorInput) {
    		files.add(((IFileEditorInput) editor.getEditorInput()).getFile());
    	}
    	
    	// Create a command.
    	AbstractTransactionalCommand command = new AbstractTransactionalCommand(domain, name, files) {

			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				
				runnable.run();
				
				return CommandResult.newOKCommandResult();
			}	
    	};
    	
    	// Execute the command.
    	try {
			command.execute(new NullProgressMonitor(), editor);
		} catch (ExecutionException e) {
			Reo.logError("Error executing command '" + name + "'.", e);
			MessageDialog.openError(editor.getSite().getShell(), "Error", 
					"Error executing command '" + name + "'. See the error log for more information.");
		}

    }
    
    /**
     * Get the currently active shell.
     * @return Shell
     */
    protected Shell getShell() {
    	return shell;
    }
    
    /**
     * Get the currently active diagram editor.
     * @return Diagram editor
     */
    protected IEditorPart getEditor() {
    	return editor;
    }

    /**
     * Get the currently Reo diagram editor.
     * @return Reo diagram editor
     */
    protected ReoDiagramEditor getReoEditor() {
    	return editor;
    }

	/**
	 * Update the views based on the content of the properties.
	 */
	protected abstract void updateViews();
	
	/**
	 * Update the properties based on the content of the views.
	 */
	protected abstract void updateProperties();
	
}
