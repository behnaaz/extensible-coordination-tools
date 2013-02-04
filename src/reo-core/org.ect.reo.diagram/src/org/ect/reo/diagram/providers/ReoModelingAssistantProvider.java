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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorEditPart;
import org.ect.reo.diagram.edit.parts.WriterEditPart;
import org.ect.reo.diagram.part.Messages;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;



/**
 * @generated
 */
public class ReoModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof ModuleEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(4);
			types.add(ReoElementTypes.Connector_1001);
			types.add(ReoElementTypes.Reader_1002);
			types.add(ReoElementTypes.Writer_1003);
			types.add(ReoElementTypes.Component_1004);
			return types;
		}
		if (editPart instanceof ReaderEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(ReoElementTypes.SourceEnd_2006);
			types.add(ReoElementTypes.SinkEnd_2007);
			types.add(ReoElementTypes.Property_2004);
			return types;
		}
		if (editPart instanceof WriterEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(ReoElementTypes.SourceEnd_2006);
			types.add(ReoElementTypes.SinkEnd_2007);
			types.add(ReoElementTypes.Property_2004);
			return types;
		}
		if (editPart instanceof ComponentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(ReoElementTypes.SourceEnd_2006);
			types.add(ReoElementTypes.SinkEnd_2007);
			types.add(ReoElementTypes.Property_2004);
			return types;
		}
		if (editPart instanceof InternalReaderEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(ReoElementTypes.SourceEnd_2006);
			types.add(ReoElementTypes.SinkEnd_2007);
			types.add(ReoElementTypes.Property_2004);
			return types;
		}
		if (editPart instanceof InternalWriterEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(ReoElementTypes.SourceEnd_2006);
			types.add(ReoElementTypes.SinkEnd_2007);
			types.add(ReoElementTypes.Property_2004);
			return types;
		}
		if (editPart instanceof InternalComponentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(ReoElementTypes.SourceEnd_2006);
			types.add(ReoElementTypes.SinkEnd_2007);
			types.add(ReoElementTypes.Property_2004);
			return types;
		}
		if (editPart instanceof ConnectorCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(5);
			types.add(ReoElementTypes.Node_2001);
			types.add(ReoElementTypes.Connector_2005);
			types.add(ReoElementTypes.Reader_2008);
			types.add(ReoElementTypes.Writer_2009);
			types.add(ReoElementTypes.Component_2010);
			return types;
		}
		if (editPart instanceof SubConnectorCompartmentEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(5);
			types.add(ReoElementTypes.Node_2001);
			types.add(ReoElementTypes.Connector_2005);
			types.add(ReoElementTypes.Reader_2008);
			types.add(ReoElementTypes.Writer_2009);
			types.add(ReoElementTypes.Component_2010);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof SinkEndEditPart) {
			return ((SinkEndEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof SourceEndEditPart) {
			return ((SourceEndEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof SinkEndEditPart) {
			return ((SinkEndEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof SourceEndEditPart) {
			return ((SourceEndEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof SinkEndEditPart) {
			return ((SinkEndEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target,
				getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	@Override
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source,
				getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		HashSet<EObject> elements = new HashSet<EObject>();
		for (Iterator<EObject> it = diagram.getElement().eAllContents(); it
				.hasNext();) {
			EObject element = it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				ReoDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(Messages.ReoModelingAssistantProviderMessage);
		dialog.setTitle(Messages.ReoModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
