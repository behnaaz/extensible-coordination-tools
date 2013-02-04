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
package org.ect.reo.diagram.figures;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.Module;
import org.ect.reo.Node;
import org.ect.reo.NodeType;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.diagram.figures.util.ReoFigureColors;


/**
 * @generated NOT
 */
public class NodeFigure extends Ellipse {
	
	// Name label.
	private WrappingLabel label;
	
	// The node.
	private Node node;
	
	// The module.
	private Module module;
	
	// Use local coordinates?
	private boolean localCoordinates = false;

	/**
	 * Constructor.
	 */
	public NodeFigure(Node node) {
		
		this.node = node;
		
		// Set the color of the node.
		setForegroundColor(ReoFigureColors.FG_CHANNEL);
		setBackgroundColor(getNodeColor());
		setLineWidth(1);

		// Set the LayoutManager.
		GridLayout layoutThis = new GridLayout();
		layoutThis.numColumns = 1;
		layoutThis.makeColumnsEqualWidth = true;
		layoutThis.marginWidth = 4;
		layoutThis.marginHeight = 2;
		this.setLayoutManager(layoutThis);

		// Add the label.
		add(label = new WrappingLabel(getNodeText()));

	}
	
	
	/**
	 * Compute the color that the node should have.
	 */
	public Color getNodeColor() {

		// Color based on the node type.
		Color color = node.isMixedNode() ? 
				ReoFigureColors.MIXED_NODE :
				ReoFigureColors.BOUNDARY_NODE;
		
		ReconfRule rule = getModule().getActiveReconfRule();
		if (rule != null && ReconfType.get(node, rule) != ReconfType.NONE) {
			Color reconf = ReoFigureColors.getReconfColor(node, rule);
			color = new Color(null, (reconf.getRed() + 255) / 2, (reconf
					.getGreen() + 255) / 2, (reconf.getBlue() + 255) / 2);
		}

		return color;
	}
	
	/**
	 * Get the module.
	 * @return The module.
	 */
	private Module getModule() {
		if (module==null) {
			EObject current = node.eContainer();
			while (current!=null && !(current instanceof Module)) {
				current = current.eContainer();
			}
			if (current instanceof Module) {
				module = (Module) current;
			}
		}
		return module;
	}
	
	/**
	 * Compute the text inside the node.
	 * @generated NOT
	 */
	public String getNodeText() {
		if (node.getType() == NodeType.ROUTE)
			return "X";
		if (node.getType() == NodeType.JOIN)
			return "+";
		return "";
	}
	
	/**
	 * @generated
	 */
	public void setText(String text) {
		label.setText(text);
	}
	
	/**
	 * Update the figure.
	 */
	public void update() {
		setBackgroundColor(getNodeColor());
		setText(getNodeText());
	}
	
	protected boolean useLocalCoordinates() {
		return localCoordinates;
	}

	protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
		localCoordinates = useLocalCoordinates;
	}

}
