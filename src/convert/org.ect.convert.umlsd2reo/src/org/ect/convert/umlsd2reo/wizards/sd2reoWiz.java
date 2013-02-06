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
package org.ect.convert.umlsd2reo.wizards;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;
import org.ect.convert.umlsd2reo.UMLSD2Reo;
import org.ect.convert.umlsd2reo.xmireader.XMIReader;


public class sd2reoWiz extends BasicNewFileResourceWizard{
	class MyWizardNewFileCreationPage extends WizardNewFileCreationPage{
		InputStream inputStream;
		
		public MyWizardNewFileCreationPage(String pageName,
				IStructuredSelection selection) {
			super(pageName, selection);
		}

		@Override
		protected InputStream getInitialContents() {
			return inputStream;
		};
	}
	
	private MyWizardNewFileCreationPage newFilePage;
	private String inputFileName;
	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		if (!(selection instanceof IStructuredSelection))		return;
		newFilePage =	new MyWizardNewFileCreationPage("Sequence Diagram to Reo", StructuredSelection.EMPTY);
		newFilePage.setFileExtension("reo");
			
		IStructuredSelection structured = (IStructuredSelection) selection;
		Object first = structured.getFirstElement();
		if (first instanceof File)
		{
			File inputfile = (File)first;
			inputFileName = inputfile.getProject().getLocation().toString() + "/" + inputfile.getProjectRelativePath();
		}
	}
	
	
	
	@Override
	public void addPages() {
		addPage(newFilePage);
	}
	
	@Override
	public boolean performFinish() {
		try {
        		IFile ifile = newFilePage.createNewFile();
        		final URI fileURI = URI.createURI(ifile.getLocationURI().toString());
        		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor monitor)
						throws CoreException, InvocationTargetException,
						InterruptedException {
		        	try {
		        		transform(fileURI, monitor);
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}				
				}
			};
            getContainer().run(true, true, op);            
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		return true;
	}	
	
	public java.io.InputStream parseStringToIS(String str){
		   if(str==null) return null;
		   str = str.trim();
		   java.io.InputStream in = null;
		   try{
		     in = new java.io.ByteArrayInputStream(str.getBytes("UTF-8"));
		   }catch(Exception ex){}
		   return in;      
		 }	
	
	private void transform(URI path, IProgressMonitor monitor) throws Exception {
		
		monitor.beginTask("UMLSD to Reo", 2);
		
		monitor.subTask("extracting diagram info");	
		XMIReader rdr = new XMIReader();
		String umlsd = rdr.transform(inputFileName);
		System.out.println(umlsd);
		monitor.worked(1);
	
	 	monitor.subTask("transforming to reo");		
	 	UMLSD2Reo s2r = new UMLSD2Reo(new InputStreamReader(parseStringToIS(umlsd)));
		s2r.print(path);
	  	monitor.worked(1);
		monitor.done();
	}
}

