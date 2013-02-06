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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutService;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.part.AutomataDiagramEditorPlugin;

public abstract class EditorUtil extends WorkspaceModifyOperation {
	private static final Map<String, String> RES_OPTS = new HashMap<String, String>();
	static {
		RES_OPTS.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, 
				XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
	}
	protected Module module;
	protected ModuleEditPart modPart;
	
	public EditorUtil(Module context) {
		this(context, null);
	}
	
	protected EditorUtil(Module context, ModuleEditPart modPart) {
		super(null);
		this.module = context;	
		this.modPart = modPart;
	}
	
	@SuppressWarnings("unchecked")
	public DiagramEditor saveAndOpen(IPath path, IProgressMonitor monitor) 
	throws ExecutionException, CoreException, IOException {
		GMFResource res = new GMFResource(
				URI.createPlatformResourceURI(path.toPortableString(), false));				

		DiagramEditor editor = saveAndOpen(res, monitor);
		persistModify(Collections.EMPTY_LIST).execute(monitor, null);
		return editor;
	}
	
	protected DiagramEditor saveAndOpen(Resource resource, 
			IProgressMonitor monitor) 
	throws CoreException, IOException, ExecutionException {
		monitor.subTask("saving file");

        Diagram diagram = ViewService.createDiagram(module,
				ModuleEditPart.MODEL_ID,
				AutomataDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
        resource.getContents().add(module);
        resource.getContents().add(diagram);
		diagram.setElement(module);
		
		resource.save(Collections.EMPTY_MAP);
		IFile newFile = WorkspaceSynchronizer.getFile(resource);
		newFile.setCharset("UTF-8", monitor);
		
		monitor.worked(1);
		monitor.subTask("opening file");
		
		DiagramEditor editor = (DiagramEditor) IDE.openEditor(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
				newFile);
		monitor.worked(1);
		
		modPart = (ModuleEditPart) editor.getDiagramEditPart();
		return editor;
	}
		
	protected AbstractTransactionalCommand persistModify(
			final Collection<Automaton> additions) throws ExecutionException {
		if (modPart==null)
			throw new ExecutionException("ModuleEditPart is null!");
		
		return new ChangeExtensionsCommand("Add automata",modPart) {
			@Override
			protected void performExtensionsChange(IProgressMonitor monitor)
					throws ExecutionException {
				module.getAutomata().addAll(additions);				
				modPart.getDiagramView().persistChildren();				
			}
		};		
	}
	
	protected AbstractTransactionalCommand layoutAll(final List<GraphicalEditPart> parts) throws ExecutionException {
		if (modPart==null)
			throw new ExecutionException("ModuleEditPart is null!");

		return new ChangeExtensionsCommand("Add automata",modPart) {
			@Override
			protected void performExtensionsChange(IProgressMonitor monitor)
					throws ExecutionException {
				monitor.subTask("laying out states");
				for (GraphicalEditPart part: parts) {
//					LayoutService.getInstance().layout(part.getPrimaryView(), LayoutType.DEFAULT);
					layout(part);
					monitor.worked(1);
				}
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public static void layout(GraphicalEditPart part) throws ExecutionException {
		List<EditPart> children = new ArrayList<EditPart>(part.getChildren());
		List<Node> states = new ArrayList<Node>();
		
		while (!children.isEmpty()) {
			EditPart child =  children.remove(0);
			if (child instanceof StateEditPart)
				states.add((Node) child.getModel());

			children.addAll(
					child.getChildren());
		}			
		
//		IStatus status = new DeferredLayoutCommand(
//				part.getEditingDomain(),
//				children,
//				part).execute(new NullProgressMonitor(), null);
		
		LayoutService.getInstance().layoutNodes(
				states, true, LayoutType.DEFAULT);
	}
}
