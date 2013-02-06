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
package org.ect.codegen.reo2ea.ca.wizards;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.ect.codegen.reo2ea.ca.Reo2CaApp;
import org.ect.codegen.reo2ea.wizards.EditorUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.part.AutomataDiagramEditorPlugin;

public class BatchEAWizard extends Wizard {

    private WizardNewFileCreationPage mainPage;
    private org.ect.reo.Module circuits;
	private IStructuredSelection selection;
	protected InputStream contents;
    /**
     * Creates a wizard for creating a new file resource in the workspace.
     */
    public BatchEAWizard(org.ect.reo.Module module) {
        super();
        this.circuits = module;
        setWindowTitle("Reo to Extendible automata batch convertor");
    }

    public void addPages() {
        super.addPages();
        mainPage = new WizardNewFileCreationPage("Save EA to", selection) {
        	@Override
        	protected InputStream getInitialContents() {
        		return contents;
        	}        	
        };
        
//        mainPage.setDescription(ResourceMessages.FileResource_description); 
        addPage(mainPage);
    }

    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
//        setWindowTitle(ResourceMessages.FileResource_shellTitle);
        setNeedsProgressMonitor(true);
        selection = currentSelection;
    }

	@Override
	public boolean performFinish() {	
		final IPath path = mainPage.getContainerFullPath().addTrailingSeparator()
				.append(mainPage.getFileName());		

		final String fileType = mainPage.getFileExtension();
		
		IRunnableWithProgress op = new  IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {
				monitor.beginTask("Transforming connector:", circuits.getConnectors().size()+2);
				
				try {
					org.ect.ea.automata.Module	mod = Reo2CaApp.batchTransform(circuits, monitor);
					
					if (fileType=="ea")
						saveEA(mod, path, monitor);
					else {
						monitor.setTaskName("Creating content buffer");
						StringBuffer buf = new StringBuffer();
						for (Automaton ca: mod.getAutomata())
							buf.append(ca);

						contents = new StringBufferInputStream(buf.toString()); 
						monitor.setTaskName("Writing to file");
						mainPage.createNewFile();
					}
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}
			} 
		};
		try {
            getContainer().run(false, true, op);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	void saveEA(org.ect.ea.automata.Module mod, IPath path, IProgressMonitor monitor) 
	throws ExecutionException, IOException, CoreException {	
		monitor.setTaskName("Creating resource");
		final Diagram diagram = ViewService.createDiagram(mod,
				ModuleEditPart.MODEL_ID,
				AutomataDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		diagram.setElement(mod);
		
		GMFResource res = new GMFResource(
				URI.createPlatformResourceURI(path.toPortableString(), false));
		res.getContents().add(mod);
		res.getContents().add(diagram);
		monitor.worked(1);	
		
		monitor.setTaskName("Creating diagram elements");
		ResourceSet rset = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain().getResourceSet();
	    rset.getResources().add(res);
	    
		monitor.setTaskName("Laying out states");
		DiagramEditPart part = OffscreenEditPartFactory.getInstance()
		.createDiagramEditPart(diagram, getContainer().getShell());
		EditorUtil.layout(part);
		monitor.worked(1);

		new ChangeExtensionsCommand("Create EA", part) {
			protected void performExtensionsChange(	IProgressMonitor monitor)
					throws ExecutionException {
				diagram.persistChildren();	
				monitor.worked(1);
			}
			
		}.execute(monitor, null);
		

		monitor.setTaskName("Saving file " + path.lastSegment());
		res.save(Collections.EMPTY_MAP);
		IFile newFile = WorkspaceSynchronizer.getFile(res);
		newFile.setCharset("UTF-8", monitor);
		monitor.done(); 		
	}
}