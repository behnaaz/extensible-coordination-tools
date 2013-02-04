package org.ect.ea.extensions.constraints;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class CARefactoring {
	
	/**
	 * Rename a port name in an automaton. This updates
	 * all references of this port name in the constraint
	 * automaton, including the port names fields and the
	 * constraints.
	 */
	public static void renamePortName(String oldName, String newName, Automaton automaton) {
		rename(oldName, newName, CA.getPortNames(automaton).getValues());
		for (Transition transition : automaton.getTransitions()) {
			rename(oldName, newName, CA.getPortNames(transition).getValues());
			renameElement(oldName, newName, CA.getConstraint(transition));
		}
	}
	
	/**
	 * Renames state memory in an automaton. This updates
	 * all references to the buffer name in the constraint
	 * automaton, including the port names fields and the
	 * constraints.
	 */
	public static void renameStateMemory(String oldName, String newName, State state) {
		rename(oldName, newName, CA.getAllMemoryCells(state.getAutomaton()));
		rename(oldName, newName, CA.getMemoryCells(state).getValues());
		
		for (Transition transition : state.getIncoming()) 
			renameElement(oldName, newName, CA.getConstraint(transition));
		
		for (Transition transition : state.getOutgoing()) 
			renameElement(oldName, newName, CA.getConstraint(transition));
	}
	
	
	protected static void rename(String oldName, String newName, List<String> names) {
		assert names!=null;
		
		ListIterator<String> it = (ListIterator<String>) names.listIterator();	
		while (it.hasNext()) 
			if (it.next().equals(oldName))
				it.set(newName);
	}
	
	
	public static void renameElement(String oldName, String newName, Constraint constraint) {
		assert constraint!=null; 
		Iterator<EObject> elements = constraint.eAllContents();
		while (elements.hasNext()) {
			EObject e = elements.next();
			if (e instanceof Parameter && 
					((Parameter) e).getValue().equals(oldName) ) 
				((Parameter) e).setValue(newName);							
		}		
	}	

	public static void renameElement(Constraint constraint, Map<Parameter, Parameter> replacements) {
		assert constraint!=null; 
		Iterator<EObject> elements = constraint.eAllContents();
		while (elements.hasNext()) {
			EObject e = elements.next();
			if (e instanceof Parameter && replacements.containsKey(e)) 
				EcoreUtil.replace(e, 
						EcoreUtil.copy(replacements.get(e)));							
		}		
	}	
}

