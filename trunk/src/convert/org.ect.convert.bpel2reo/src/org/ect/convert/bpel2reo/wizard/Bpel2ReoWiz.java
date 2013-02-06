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
package org.ect.convert.bpel2reo.wizard;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewFileResourceWizard;

import org.ect.convert.bpel2reo.transform.manager.Transformer;
import org.ect.convert.bpel2reo.util.ConnectorInfo;
import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;
import org.ect.reo.diagram.view.util.ReoViewCreator;

public class Bpel2ReoWiz extends BasicNewFileResourceWizard {
	class MyWizardNewFileCreationPage extends WizardNewFileCreationPage {
		InputStream inputStream;

		public MyWizardNewFileCreationPage(String pageName,
				IStructuredSelection selection) {
			super(pageName, selection);
		}

		@Override
		protected InputStream getInitialContents() {
			return inputStream;
		}
	};

	private MyWizardNewFileCreationPage newFilePage;
	protected String bpelInputFile;
	protected Connector reoResult;
	ConnectorInfo res = null;
	
	// private String outputFile =
	// "/ufs/behnaz/runtime-EclipseApplication/popup/b.reo";

	@Override
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);

		newFilePage = new MyWizardNewFileCreationPage("BPEL to Reo",
				StructuredSelection.EMPTY);
		newFilePage.setFileExtension("reo");

		Object first = currentSelection.getFirstElement();
		if (first instanceof IFile)
			bpelInputFile = ((IFile) first).getLocationURI().getPath();
	}

	@Override
	public void addPages() {
		addPage(newFilePage);
	}

	@Override
	public boolean performFinish() {
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor)
					throws CoreException, InvocationTargetException,
					InterruptedException {
				try {
					res = transform(bpelInputFile, monitor);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}
			}
		};
		try {
			getContainer().run(true, true, op);
			IFile ifile = newFilePage.createNewFile();
			doSave(res, URI.createURI(ifile.getLocationURI().toString()));
			ifile.getParent().refreshLocal(IProject.DEPTH_INFINITE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private ConnectorInfo transform(String filename, IProgressMonitor monitor) {
		monitor.beginTask("BPEL to Reo", 2);
		monitor.subTask("converting ...");
		ConnectorInfo result = doTransform(filename);
		monitor.worked(1);
		System.out.println("WARNING: In order to see the complete generated Reo circuit, right-click on the file and choose 'Initialize *.reo diagram file'.");
		monitor.subTask("saving the result");
		
		monitor.worked(1);
		monitor.done();
		return result;
	}

	private ConnectorInfo doTransform(String filename) {
	/*	Copy c1 = new Copy("az", "be");
		c1.transform(connector);
		Copy c2 = new Copy("az2", "be2");
		c2.transform(connector);
		Copy c3 = new Copy("az3", "be3");
		c3.transform(connector);
		Sequence seq = new Sequence();
		seq.AddPart(c1.getStart(), c1.getEnd());
		seq.AddPart(c2.getStart(), c2.getEnd());
		seq.AddPart(c3.getStart(), c3.getEnd());
		seq.transform(connector);*/
		
		return new Transformer().transform(filename);
	}

	private void AddWriterReader(Module mod, Pair<Node, Node> startend) {
		Writer wrt;
		Node tmpw = startend.getFirst();
		if (tmpw.isSourceNode()){
			wrt = new Writer(tmpw);
		}
		else {
			Node startNode = new Node();
			Channel chnl = new Sync();
			chnl.setNodeOne(startNode);
			chnl.setNodeTwo(tmpw);
			tmpw.getConnector().getNodes().add(startNode);
			tmpw.getConnector().getPrimitives().add(chnl);
			wrt = new Writer(tmpw);
		}
		wrt.setName("Start");
		wrt.setRequests(-1);
		mod.getComponents().add(wrt);
		
		
		
		Reader rdr;
		Node tmpr = startend.getSecond();
		if (tmpr.isSinkNode()){
			rdr = new Reader(tmpr);
		}
		else {
			Node endNode = new Node();
			Channel chnl = new Sync();
			chnl.setNodeOne(tmpr);
			chnl.setNodeTwo(endNode);
			tmpr.getConnector().getNodes().add(endNode);
			tmpr.getConnector().getPrimitives().add(chnl);
			rdr = new Reader(endNode);
		}		
		rdr.setName("End");
		rdr.setRequests(-1);
		mod.getComponents().add(rdr);
	}
	
	private void doSave(ConnectorInfo connectorinfo, URI uri) {
		try {
			Module module = Reo.createModule(uri);
			module.getConnectors().add(connectorinfo.getConnector());
			ReoViewCreator.createModuleView(module);
			AddWriterReader(module, connectorinfo.getPair());
			Reo.saveModule(module);
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}
