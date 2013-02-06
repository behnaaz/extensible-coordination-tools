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
package org.ect.codegen.reo2ea.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import org.ect.codegen.reo2ea.core.IComposeAction;
import org.ect.codegen.reo2ea.plugin.ExtensionManager;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.plugin.ExtensionManager.TransformExt;
import org.ect.codegen.reo2ea.preferences.PreferenceConstants;
import org.ect.codegen.reo2ea.util.CAUtil;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.contributions.commands.LayoutNewAutomatonCommand;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;

public class ExtensibleWizard extends BasicNewResourceWizard {
    private WizardNewFileCreationPage newFilePage;
    private EaSelectionPage types;
	private Map<String, TransformExt> extensions;
    Connector connector;
	private TransactionalEditingDomain editingDomain;	
	
    @Override
    public void addPages() {
        newFilePage = new WizardNewFileCreationPage("Save EA to", getSelection());
        newFilePage.setAllowExistingResources(true);
        newFilePage.setFileName(connector.getName());
        newFilePage.setFileExtension("ea");
        newFilePage.setContainerFullPath(Path.ROOT);

        addPage(newFilePage);

    	types = new EaSelectionPage(extensions.values(), connector);   
    	addPage(types);    	
    }

    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        super.init(workbench, currentSelection);
        extensions = new ExtensionManager().getExtensions();
        
		Object o = ((IStructuredSelection)selection).getFirstElement();
		if (o instanceof ConnectorEditPart) {
			ConnectorEditPart connectorPart = (ConnectorEditPart)o;
			connector = (Connector) connectorPart.getNotationView().getElement();
			editingDomain = connectorPart.getEditingDomain();
		}
        setWindowTitle("Reo to Extendible automata convertor");
        setNeedsProgressMonitor(true);
    }
    
    @Override
    public boolean performFinish() {
    	Module result;
    	IWizard choice = types.selection.wizard;
    	if (!(choice instanceof IComposeAction) ||
    			(result = ((IComposeAction) choice).getResult())==null) 
    		return false;

    	final IPreferenceStore prefs = Reo2EAPlugin.getDefault().getPreferenceStore();
    	for (Automaton a: result.getAutomata()){
    		if(prefs.getBoolean(PreferenceConstants.TRIM))
    			CAUtil.trim(a);				
    		if(prefs.getBoolean(PreferenceConstants.PRETTY))
    			CAUtil.beautify(a, prefs.getString(PreferenceConstants.PREFIX));    
    	}

    	final IPath path = newFilePage.getContainerFullPath().addTrailingSeparator()
    	.append(newFilePage.getFileName());        

    	EditorUtil editorUtil = new EditorUtil(result) {
    		@Override
    		protected void execute(IProgressMonitor monitor)
    		throws CoreException, InvocationTargetException,InterruptedException {
    			try {
    				DiagramEditor editor = saveAndOpen(path, monitor);
    				if (prefs.getBoolean(PreferenceConstants.LAYOUT))
    					new LayoutNewAutomatonCommand(modPart, false).execute(monitor, null);
//    					layoutAll(modPart.getChildren()).execute(monitor, null);
    				editor.doSave(monitor);
    				
    				Component comp = new Component(connector.getName());
					new AddCommand(editingDomain, connector.getModule().getConnectors(), comp);
    			} catch (Exception e) {
    				throw new InvocationTargetException(e);
    			}

    		}

    	};
    	/*
		if (fileType=="cat") {
			monitor.setTaskName("Creating content buffer");

			StringBuffer buf = new StringBuffer();
			for (Automaton ca: module.getAutomata()) 
				buf.append(	
						ca instanceof CA ? 	ca.toString() : new CA(ca).toString()
				);

			newFilePage.setInitialContents(buf);
			newFilePage.createNewFile();
		}
    	 */
    	try {
    		getContainer().run(false, false, editorUtil);    

    	} catch (InterruptedException e) {
    		return false;
    	} catch (InvocationTargetException e) {
    		Throwable realException = e; 
    		while (realException.getCause()!=null)
    			realException = realException.getCause();
    		
    		String message = realException.getMessage()==null ? "Unknown error. See error log."
    															:realException.getMessage(); 
			MessageDialog.openError(getShell(), "Error", message);
    		Reo2EAPlugin.log("Error", realException);
    		return false;
    	} 

    	return true;
	}
}
