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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import org.ect.codegen.reo2ea.core.IComposeAction;
import org.ect.codegen.reo2ea.plugin.ExtensionManager;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.util.ReoUtil;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.part.AutomataDiagramEditorPlugin;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.libraries.ReoLibraryUtil;
import org.ect.reo.util.PropertyHelper;

public class ComponentWizard extends BasicNewResourceWizard {
	private EaSelectionPage extension;
	Connector connector;
	private TransactionalEditingDomain editingDomain;
	private Diagram diagram;
//	private Node view;	
	private URI eaURI;

	public ComponentWizard() {
		super();
		setForcePreviousAndNextButtons(true);
	}
	@Override
	public void addPages() {
		extension = new EaSelectionPage(
				new ExtensionManager().getExtensions().values(), 
				connector);   
		addPage(extension);    	
	}

	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);

		Object o = ((IStructuredSelection)selection).getFirstElement();
		if (o instanceof ConnectorEditPart) {
			ConnectorEditPart connectorPart = (ConnectorEditPart)o;
			connector = (Connector) connectorPart.getNotationView().getElement();
			editingDomain = ((IGraphicalEditPart)connectorPart.getParent()).getEditingDomain();
			diagram = ((DiagramEditPart)connectorPart.getParent()).getDiagramView();
			//			view = (Node) connectorPart.getNotationView();
		}
		setWindowTitle("Reo to Extendible automata convertor");
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish() {
		final Module result;
		IWizard choice = extension.getSelectedNode().getWizard();
		if (!(choice instanceof IComposeAction) ||
				(result = ((IComposeAction) choice).getResult())==null) 
			return false;

		try {
			AddComponent command = 
				new AddComponent(editingDomain);
			command.execute(new NullProgressMonitor(), null);

			EASave op = new EASave(result);
			getContainer().run(true, true, op);   
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Reo2EAPlugin.handleError(e);
			return false;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 

		return true;
	}

	final class EASave extends WorkspaceModifyOperation {
		final Module result;

		EASave(Module result) {
			this.result = result;
		}

		@Override
		protected void execute(IProgressMonitor monitor) throws CoreException,
		InvocationTargetException, InterruptedException {
			Diagram diagram = ViewService.createDiagram(result,
					ModuleEditPart.MODEL_ID,
					AutomataDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
			diagram.setElement(result);
	
			GMFResource resource;
			resource = new GMFResource(eaURI);
out:
			try {
				resource.load(Collections.EMPTY_MAP);
				//ignore Diagrams elements in resource
				for (EObject o : resource.getContents()) 
					if (o instanceof Module) { 
						((Module) o).getAutomata().addAll(result.getAutomata());
						break out;
					} 
				throw new InvocationTargetException(new IOException("Error loading component file"));
			} catch (IOException e) {
				//no existing resource found
				resource.getContents().add(result);
				resource.getContents().add(diagram);
			}
			
			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new InvocationTargetException(e);
			}

		}
	}

	final class AddComponent extends AbstractTransactionalCommand {
		AddComponent(TransactionalEditingDomain domain) {
			super(domain, "add component", null);
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			Component comp = new Component(connector.getName());

			for (org.ect.reo.Node n: connector.getNodes())
				if (n.isSinkNode())
					comp.getSinkEnds().add(new SinkEnd(ReoUtil.node2PortName(n)));
				else if (n.isSourceNode())
					comp.getSourceEnds().add(new SourceEnd(ReoUtil.node2PortName(n)));

			eaURI = connector.getModule().eResource().getURI()
				.trimFileExtension().appendFileExtension("ea");			
			PropertyHelper.setOrAdd(comp, ReoLibraryUtil.TYPE_URI_KEY, eaURI.lastSegment()+"#"+connector.getName());
			connector.getModule().getComponents().add(comp);
			diagram.persistChildren();
/*			
			final Bounds bounds = (Bounds) view.getLayoutConstraint();
	
			Node componentView = ReoViewCreator.createComponentView(comp, diagram);
			BoundsImpl newbounds = new BoundsImpl(){{
				setY(bounds.getHeight()+bounds.getY());
				setX(bounds.getWidth()+bounds.getX());
	
			}};
			componentView.setLayoutConstraint(newbounds);
			diagram.getcomponentView
			
*/			return CommandResult.newOKCommandResult();
		}
	}
}
