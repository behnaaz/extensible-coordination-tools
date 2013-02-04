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

package org.ect.reo.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.diagram.navigator.ReoNavigatorItem;
import org.ect.reo.diagram.providers.ReoDropTargetListener;
import org.ect.reo.diagram.view.util.CreateMissingViewsCommand;
import org.ect.reo.diagram.view.util.ReoViewFinder;
import org.ect.reo.diagram.view.util.UpgradeViewsCommand;
import org.ect.reo.reconf.ReoReconfRegistry;


/**
 * @generated
 */
public class ReoDiagramEditor extends DiagramDocumentEditor implements
		IGotoMarker {

	/**
	 * @generated
	 */
	public static final String ID = "org.ect.reo.diagram.part.ReoDiagramEditorID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String CONTEXT_ID = "org.ect.reo.diagram.ui.diagramContext"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public ReoDiagramEditor() {
		super(true);
	}

	/**
	 * @generated
	 */
	@Override
	protected String getContextID() {
		return CONTEXT_ID;
	}

	/**
	 * @generated
	 */
	@Override
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		new ReoPaletteFactory().fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	@Override
	protected void setDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			setDocumentProvider(ReoDiagramEditorPlugin.getInstance()
					.getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	/**
	 * @generated
	 */
	public void gotoMarker(IMarker marker) {
		MarkerNavigationService.getInstance().gotoMarker(this, marker);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	public void doSaveAs() {
		performSaveAs(new NullProgressMonitor());
	}

	/**
	 * @generated
	 */
	@Override
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input)
				.getFile() : null;
		if (original != null) {
			dialog.setOriginalFile(original);
		}
		dialog.create();
		IDocumentProvider provider = getDocumentProvider();
		if (provider == null) {
			// editor has been programmatically closed while the dialog was open
			return;
		}
		if (provider.isDeleted(input) && original != null) {
			String message = NLS.bind(
					Messages.ReoDiagramEditor_SavingDeletedFile,
					original.getName());
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.WARNING);
		}
		if (dialog.open() == Window.CANCEL) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IPath filePath = dialog.getResult();
		if (filePath == null) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = workspaceRoot.getFile(filePath);
		final IEditorInput newInput = new FileEditorInput(file);
		// Check if the editor is already open
		IEditorMatchingStrategy matchingStrategy = getEditorDescriptor()
				.getEditorMatchingStrategy();
		IEditorReference[] editorRefs = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (int i = 0; i < editorRefs.length; i++) {
			if (matchingStrategy.matches(editorRefs[i], newInput)) {
				MessageDialog.openWarning(shell,
						Messages.ReoDiagramEditor_SaveAsErrorTitle,
						Messages.ReoDiagramEditor_SaveAsErrorMessage);
				return;
			}
		}
		boolean success = false;
		try {
			provider.aboutToChange(newInput);
			getDocumentProvider(newInput).saveDocument(progressMonitor,
					newInput,
					getDocumentProvider().getDocument(getEditorInput()), true);
			success = true;
		} catch (CoreException x) {
			IStatus status = x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				ErrorDialog.openError(shell,
						Messages.ReoDiagramEditor_SaveErrorTitle,
						Messages.ReoDiagramEditor_SaveErrorMessage,
						x.getStatus());
			}
		} finally {
			provider.changed(newInput);
			if (success) {
				setInput(newInput);
			}
		}
		if (progressMonitor != null) {
			progressMonitor.setCanceled(!success);
		}
	}

	/**
	 * @generated
	 */
	@Override
	public ShowInContext getShowInContext() {
		return new ShowInContext(getEditorInput(), getNavigatorSelection());
	}

	/**
	 * @generated
	 */
	private ISelection getNavigatorSelection() {
		IDiagramDocument document = getDiagramDocument();
		if (document == null) {
			return StructuredSelection.EMPTY;
		}
		Diagram diagram = document.getDiagram();
		IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
		if (file != null) {
			ReoNavigatorItem item = new ReoNavigatorItem(diagram, file, false);
			return new StructuredSelection(item);
		}
		return StructuredSelection.EMPTY;
	}

	/**
	 * @generated
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(
				this, getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,
				provider, getDiagramGraphicalViewer());
	}

	/**
	 * @generated
	 */
	@Override
	protected PreferencesHint getPreferencesHint() {
		return ReoDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated
	 */
	@Override
	public String getContributorId() {
		return ReoDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class type) {
		if (type == IShowInTargetList.class) {
			return new IShowInTargetList() {
				public String[] getShowInTargetIds() {
					return new String[] { ProjectExplorer.VIEW_ID };
				}
			};
		}
		return super.getAdapter(type);
	}

	/**
	 * @generated
	 */
	@Override
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			return ReoDiagramEditorPlugin.getInstance().getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		IDocument document = getEditorInput() != null ? getDocumentProvider()
				.getDocument(getEditorInput()) : null;
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}
		return super.getEditingDomain();
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void initializeGraphicalViewer() {

		// Check for Model upgrades.
		performUpgrades();

		// Initialize viewer. 
		super.initializeGraphicalViewer();

		// Add DropTargetListener.
		ReoDropTargetListener listener = new ReoDropTargetListener(
				getGraphicalViewer(), getEditingDomain());
		getGraphicalViewer().addDropTargetListener(listener);

		// Register reconfiguration rules.
		Module module = (Module) getDiagram().getElement();
		ReoReconfRegistry.INSTANCE.addRules(module);

	}

	/**
	 * @generated NOT
	 */
	public void dispose() {

		// Unregister reconfiguration rules.
		Module module = (Module) getDiagram().getElement();
		ReoReconfRegistry.INSTANCE.removeRules(module);

		super.dispose();
	}

	/**
	 * Check for upgrades and apply them if necessary.
	 * @generated NOT
	 */
	private void performUpgrades() {

		// Create a new upgrade command.
		Module module = (Module) getDiagram().getElement();
		UpgradeViewsCommand upgrade = new UpgradeViewsCommand(
				getEditingDomain(), getDiagram(), null);
		CreateMissingViewsCommand missing = new CreateMissingViewsCommand(
				getEditingDomain(), module, null);

		// Check whether we can execute it.
		if (missing.shouldExecute() || upgrade.canExecute()) {

			// Execute the commands.
			try {
				missing.execute(new NullProgressMonitor(), null);
				upgrade.execute(new NullProgressMonitor(), null);
				ReoDiagramEditorPlugin.getInstance().logInfo(
						"Graphical model in " + getTitle() + " upgraded.");
			} catch (ExecutionException e) {
				ReoDiagramEditorPlugin.getInstance().logError(
						"Error upgrading views in " + getTitle() + ".", e);
			}
		}

	}

	/**
	 * Select the connectors and components of a network in this editor.
	 * @param network Network.
	 * @generated NOT
	 */
	public void selectNetwork(Network network) {

		final IDiagramGraphicalViewer viewer = getDiagramGraphicalViewer();
		final List<IGraphicalEditPart> editparts = new ArrayList<IGraphicalEditPart>();

		for (Connector connector : network.getConnectors()) {
			View view = ReoViewFinder
					.findConnectorView(connector, getDiagram());
			if (view == null)
				continue;
			IGraphicalEditPart editpart = (IGraphicalEditPart) viewer
					.getEditPartRegistry().get(view);
			if (editpart != null)
				editparts.add(editpart);
		}
		for (Component component : network.getComponents()) {
			View view = ReoViewFinder
					.findComponentView(component, getDiagram());
			if (view == null)
				continue;
			IGraphicalEditPart editpart = (IGraphicalEditPart) viewer
					.getEditPartRegistry().get(view);
			if (editpart != null)
				editparts.add(editpart);
		}

		if (!editparts.isEmpty()) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					viewer.setSelection(new StructuredSelection(editparts));
				}
			});
		}
	}
}
