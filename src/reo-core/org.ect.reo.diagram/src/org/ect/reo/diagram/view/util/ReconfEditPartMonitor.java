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
package org.ect.reo.diagram.view.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.Module;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.ReconfAction;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;
import org.ect.reo.diagram.edit.parts.ComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.ReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.WriterCompartmentEditPart;
import org.ect.reo.diagram.figures.ChannelLine;
import org.ect.reo.diagram.figures.NodeFigure;
import org.ect.reo.diagram.figures.util.ReoFigureColors;
import org.ect.reo.reconf.ReconfChangeListener;



/**
 * @author Christian Krause
 * @generated NOT
 */
public class ReconfEditPartMonitor {
	
	// Ediparts that should be ignored.
	private static final Class<?>[] IGNORE_TYPES = new Class<?>[] { 
		ConnectorCompartmentEditPart.class,
		SubConnectorCompartmentEditPart.class,
		ComponentCompartmentEditPart.class,
		ReaderCompartmentEditPart.class, 
		WriterCompartmentEditPart.class,
		InternalComponentCompartmentEditPart.class,
		InternalReaderCompartmentEditPart.class, 
		InternalWriterCompartmentEditPart.class
	};
	
	
	// Remember the edit parts.
	private Map<Reconfigurable,Set<IGraphicalEditPart>> editparts;

	// Cache for ignored types.
	private final Set<Class<?>> ignoreTypes = new HashSet<Class<?>>();
	
	// Change listener.
	private ReconfChangeListener listener = new ReconfChangeListener() {
		@Override
		protected void actionChanged(Reconfigurable element, ReconfAction action) {
			updateElement(element);
		}
		@Override
		protected void ruleAdded(ReconfRule rule, Module module) {
		}
		@Override
		protected void ruleRemoved(ReconfRule rule, Module module) {
		}
		@Override
		protected void activeRuleChanged(Module module) {
			updateAll();
		}
	};
	
	/**
	 * Default constructor.
	 */
	public ReconfEditPartMonitor() {
		editparts = new HashMap<Reconfigurable,Set<IGraphicalEditPart>>();			
		for (Class<?> type : IGNORE_TYPES) {
			ignoreTypes.add(type);
		}
	}
	
	
	public void monitor(final IGraphicalEditPart editpart, boolean monitor) {
		
		// Edit part must be of a valid type.
		if (ignoreTypes.contains(editpart.getClass())) return;
			
		// Module edit part?
		if (editpart instanceof ModuleEditPart) {
			Module module = (Module) editpart.getNotationView().getElement();
			listener.monitor(module, monitor);
			return;
		}
		
		
		// Find the associated element.
		final Reconfigurable element = findReconfigurable(editpart);
		if (element==null) return;
		
		// Add the property change listener.
		listener.monitor(element, monitor);
		
		if (monitor) {
			
			// Remember the editpart.
			if (!editparts.containsKey(element)) {
				editparts.put(element, new HashSet<IGraphicalEditPart>());
			}
			// Add it only once.
			if (editparts.get(element).contains(editpart)) return;
			editparts.get(element).add(editpart);

			// Monitor the editpart.
			editpart.addEditPartListener(new EditPartListener.Stub() {
				
				public void partActivated(EditPart part) {
					// Update it ones in the beginning.
					updateEditPart(editpart);
				}
				public void partDeactivated(EditPart part) {
					monitor(editpart, false);
				}
			});
						
		}
		else {
			// Stop monitoring the editpart.
			if (editparts.containsKey(element)) {			
				editparts.get(element).remove(editpart);
				if (editparts.get(element).isEmpty()) {
					editparts.remove(element);
				}
			}
		}
		
	}
	
	/**
	 * Update the views of an element.
	 * @param element Element.
	 */
	private void updateElement(Reconfigurable element) {		
		// Update the editparts of this element.
		if (editparts.containsKey(element)) {
			for (IGraphicalEditPart part : editparts.get(element)) {
				updateEditPart(part);
			}
		}
	}
	
	/**
	 * Update the views of all elements in the current module.
	 * @param module Module.
	 */
	private void updateAll() {
		// Update all editparts.
		for (Set<IGraphicalEditPart> parts : editparts.values()) {
			for (IGraphicalEditPart part : parts) {
				updateEditPart(part);
			}
		}
	}
	
	
	/**
	 * Update the figure of an editpart.
	 * @param editpart Editpart.
	 */
	private void updateEditPart(IGraphicalEditPart editpart) {
		
		// Must be active.
		if (!editpart.isActive()) return;
		
		// Get the elements.
		Reconfigurable element = findReconfigurable(editpart);
		Module module = findModule(editpart);
		if (element==null || module==null) return;
		
		// Get the active rule.
		ReconfRule active = module.getActiveReconfRule();
		//System.out.println("Elem: " + element + "  rule: " + current + "  action " + ReconfType.get(element,current));
		
		// Calculate the color to be used.
		Color color = ReoFigureColors.getReconfColor(element, active);		
		if ((active==null || ReconfType.get(element,active)==ReconfType.NONE) &&
			editpart instanceof ITextAwareEditPart) {
			color = ColorConstants.black;
		}
		
		// Get the GEF figure.
		IFigure figure = editpart.getFigure();
		if (figure instanceof BorderedNodeFigure) {
			figure = (IFigure) ((IFigure) figure.getChildren().get(0)).getChildren().get(0);
		}
		
		// Set foreground color.
		if (figure instanceof ChannelLine) {			
			((ChannelLine) figure).setCustomColor(color);
		} else {
			figure.setForegroundColor(color);
		}
			
		// Update background color of nodes.
		if (editpart instanceof NodeEditPart) {
			((NodeFigure) figure).update();
		}
		
	}

	
	/*
	 * Find the element that an editpart belongs to.
	 */
	private EObject findElement(IGraphicalEditPart editpart) {
		EObject object = editpart.getNotationView().getElement();
		// Is it a link?
		if (editpart instanceof SinkEndNodeEditPart) {
			Edge edge = (Edge) editpart.getNotationView();
			if (edge.getSource()!=null) object = edge.getSource().getElement();
		}
		if (editpart instanceof NodeSourceEndsEditPart) {
			Edge edge = (Edge) editpart.getNotationView();
			if (edge.getTarget()!=null) object = edge.getTarget().getElement();
		}
		return object;
	}
	
	/*
	 * Find the reconfigurable element that an editpart belongs to.
	 */
	private Reconfigurable findReconfigurable(IGraphicalEditPart editpart) {
		EObject object = findElement(editpart);
		// A property or an end?
		if (object instanceof Property || object instanceof PrimitiveEnd) {
			object = object.eContainer();
		}
		if (object instanceof Reconfigurable) return (Reconfigurable) object;
		return null;
	}
	
	/*
	 * Find the root module for an editpart element.
	 */
	private Module findModule(IGraphicalEditPart editpart) {
		EObject object = findElement(editpart);
		while (object!=null && !(object instanceof Module)) {
			object = object.eContainer();
		}
		if (object instanceof Module) {
			return (Module) object;
		} else {
			return null;
		}
	}
	
}
