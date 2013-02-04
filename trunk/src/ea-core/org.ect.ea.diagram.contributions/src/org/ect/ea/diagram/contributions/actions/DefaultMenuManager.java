package org.ect.ea.diagram.contributions.actions;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.Action;


public class DefaultMenuManager extends ActionMenuManager {
	
	/**
	 * Creates a new instance of the menu manager.
	 */
	public DefaultMenuManager(String id, String name) {
		super(id, new DefaultMenuAction(name), true);
	}
	
	/**
	 * The menu action containing the UI for the menu manager.
	 */
	private static class DefaultMenuAction extends Action {
		public DefaultMenuAction(String name) {
			setText(name);
			setToolTipText(name);
		}
	}
	
}

