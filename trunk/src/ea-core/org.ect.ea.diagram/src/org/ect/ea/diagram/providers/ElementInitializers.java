package org.ect.ea.diagram.providers;

import org.ect.ea.automata.State;
import org.ect.ea.diagram.part.AutomataDiagramEditorPlugin;


/**
 * @generated
 */
public class ElementInitializers {

	/**
	 * @generated
	 */
	public static void init_State_2001(State instance) {
		try {
			Object value_0 = name_State_2001(instance);
			instance.setName((String) value_0);
		} catch (RuntimeException e) {
			AutomataDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * Initialize the name of states.
	 * @generated NOT
	 */
	private static String name_State_2001(State state) {
		return "q" + state.getAutomaton().getStates().indexOf(state);
	}
}
