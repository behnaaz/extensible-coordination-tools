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
package org.ect.reo.ui.properties.reconf;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Connector;
import org.ect.reo.Primitive;
import org.ect.reo.Reconfigurable;
import org.ect.reo.Reo;
import org.ect.reo.diagram.view.util.GenericViewUtil;
import org.ect.reo.diagram.view.util.ReoViewCreator;
import org.ect.reo.diagram.view.util.ReoViewFinder;
import org.ect.reo.diagram.view.util.ReoViewRemover;
import org.ect.reo.reconf.ReconfListener;


/**
 * 
 * @generated NOT
 * @author Christian Krause
 *
 */
public class ReconfViewUpdater implements ReconfListener {
	
	public static final int SHIFT_X = 0;
	public static final int SHIFT_Y = 40;
	
	private Diagram source, target;
	
	/**
	 * Constructor for a reconfiguration view updater.
	 * @param diagram Target diagram.
	 */
	public ReconfViewUpdater(Diagram source, Diagram target) {
		this.source = source;
		this.target = target;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.reconf.ReconfListener#elementCreated(org.ect.reo.Reconfigurable, org.ect.reo.Reconfigurable)
	 */
	public void elementCreated(Reconfigurable element, Reconfigurable template) {
		
		//System.out.println("created " + element);
		View oldView = null, newView = null;
		
		if (element instanceof org.ect.reo.Node) {
			oldView = ReoViewFinder.findNodeView((org.ect.reo.Node) template, source);
			newView = ReoViewCreator.createNodeView((org.ect.reo.Node) element, target);
		}
		else if (element instanceof Primitive) {
			oldView = ReoViewFinder.findPrimitiveView((Primitive) template, source);
			newView = ReoViewCreator.createPrimitiveView((Primitive) element, target);
		}
		else if (element instanceof Connector) {
			oldView = ReoViewFinder.findConnectorView((Connector) template, source);
			newView = ReoViewCreator.createConnectorView((Connector) element, target);
		}
		
		// Copy layout constraints.
		if (oldView!=null && newView!=null) {
			GenericViewUtil.copyLayout(oldView, newView);
			if (newView instanceof Node) {
				GenericViewUtil.freeNode((Node) newView, SHIFT_X, SHIFT_Y);
			}
		} else {
			Reo.logError("Error creating view for " + element + ".");
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.reconf.ReconfListener#elementDeleted(org.ect.reo.Reconfigurable, org.ect.reo.Reconfigurable)
	 */
	public void elementDeleted(Reconfigurable element, Reconfigurable template) {
		
		//System.out.println("deleted " + element);
		
		boolean deleted = false;
		if (element instanceof org.ect.reo.Node) {
			deleted = ReoViewRemover.removeNodeView((org.ect.reo.Node) element, target);
		}
		else if (element instanceof Primitive) {
			deleted = ReoViewRemover.removePrimitiveView((Primitive) element, target);	
		}
		else if (element instanceof Connector) {
			deleted = ReoViewRemover.removeConnectorView((Connector) element, target);
		}
		
		if (!deleted) {
			Reo.logError("Error deleting view for " + element);
		}
		
	}
}
