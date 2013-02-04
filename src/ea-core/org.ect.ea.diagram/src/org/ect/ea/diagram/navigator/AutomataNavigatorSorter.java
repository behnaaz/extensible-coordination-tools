package org.ect.ea.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;


/**
 * @generated
 */
public class AutomataNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 5003;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof AutomataNavigatorItem) {
			AutomataNavigatorItem item = (AutomataNavigatorItem) element;
			return AutomataVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
