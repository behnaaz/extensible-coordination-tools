/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IMenuManager;

/**
 * Extension providers are allowed to create there own edit parts.
 * This is what this interface is for.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public interface IEditPartProvider {
	
	/**
	 * Make a contribution to the diagram's context menu.
	 * This method is always invoked when the context menu
	 * is opened. So there is no need to add selection 
	 * listener or anything else.
	 * 
	 * @param menu The context menu.
	 * @param selection The currently selected views.
	 */
	public void contributeToContextMenu(IMenuManager menu, IGraphicalEditPart[] selection);
	
	
	/**
	 * Create an edit part for a given view. This is only
	 * meant for extension providers who produce their own
	 * views.
	 * @param view View to be displayed.
	 * @return The corresponding edit part.
	 */
	public IGraphicalEditPart createEditPart(View view);
	
}
