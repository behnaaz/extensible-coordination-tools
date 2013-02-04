package org.ect.ea.extensions.clocks;

import java.text.ParseException;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.constraints.CA;


/** This class provides utility functions for TCA and clock extensions. */

public class ClockUtils {

	/** Representation of logical conjunction. */
	public static final String LAND = "&";
	
	/** Representation of logical disjunction. */
	public static final String LOR = "|";
	
	/** Representation of logical negation. */
	public static final String LNOT = "!";
	
	/** Representation of logical equivalence. */
	public static final String LIMPLY = "<->";
	
	/** Representation of less or equal. */
	public static final String LEQ = "<=";
	
	/** Representation of greater or equal. */
	public static final String GEQ = ">=";
	
	/** ID to find extension. */
	public static final String INVARIANT_ID = org.ect.ea.extensions.clocks.StateInvariantProvider.EXTENSION_ID,
		AUTOMATON_CLOCKS_ID = org.ect.ea.extensions.clocks.AutomatonClocksProvider.EXTENSION_ID,
		TRANSITION_UPDATE_ID = org.ect.ea.extensions.clocks.TransitionUpdateProvider.EXTENSION_ID,
		TRANSITION_GUARD_ID = org.ect.ea.extensions.clocks.TransitionGuardProvider.EXTENSION_ID,
		DATA_CONSTRAINT_ID = org.ect.ea.extensions.clocks.TCADataConstraintsProvider.EXTENSION_ID,
		CONSTRAINT_ID = org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider.EXTENSION_ID,
		START_STATE_ID = org.ect.ea.extensions.startstates.StartStateExtensionProvider.EXTENSION_ID,
		AUTOMATON_PORT_NAMES_ID = org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider.EXTENSION_ID,
		TRANSITION_PORT_NAMES_ID = org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider.EXTENSION_ID,
		STATE_MEMORY_ID = org.ect.ea.extensions.statememory.StateMemoryExtensionProvider.EXTENSION_ID;
	
	/** Simplify a clock constraint. Simplifications are: (1) Check whether the 
	 * constraint contains 'true'. In that case, remove that conjuct. 
	 * (2) Remove all parenthesis. Since only conjunction is allowed, there can 
	 * be no ambiguities, so it is safe to remove parenthesis.   
	 * @param cc The clock constraint to be simplified. 
	 * @return A simplified version of <tt>cc</tt>. 
	 */
	public static String simplifyCC(final String cc) {
		//remove all occurences of (, ), blanks and true
		String ret = cc.replace("(", "").replace(")", "").replace(" ", "");
		ret = ret.replace("true&", "").replace("&true", "");
		return ret;
	}
	
	/** Get the invariant of a location. 
	 * @param s The location to get the invariant for
	 * @param getTrueAsEmpty For invariants equal to <tt>true</tt>, whether to return  
	 * the empty String (true) or the String <tt>'true'</tt> (false) 
	 * @return A string representation of the invariant of <tt>s</tt>, or the 
	 * empty string in case the location does not support the invariant 
	 * extension (in case the location does support this extension, but does 
	 * not have an invariant, the method returns <tt>true</tt>).    
	 */
	public static String getInvariant(final State s, final boolean getTrueAsEmpty) {
		
		String ret = "";
		if (s.findExtension(INVARIANT_ID) != null) {
			ret = ((StringExtension) s.findExtension(INVARIANT_ID)).getValue();
			if (getTrueAsEmpty && ret.equals("true")) {
				ret = "";
			}
		}
		return ret;
	}
	
	/** Get the invariant of a location. 
	 * @param s The location to get the invariant for
	 * @return A string representation of the invariant of <tt>s</tt>, or the 
	 * empty string in case the location does not support the invariant 
	 * extension (in case the location does support this extension, but does 
	 * not have an invariant, the method returns <tt>true</tt>).    
	 */
	public static String getInvariant(final State s) {
		
		return getInvariant(s, false); 
	}
	
	/** Get the clocks of an automaton. 
	 * @param automaton The automaton to get the clocks from
	 * @return An EList containing string representations of the clocks of the 
	 * automaton, or null in case something goes wrong parsing the list of 
	 * clocks. The resulting list will also be empty in case the automaton does
	 * not support the clocks extension. */
	public static EList<String> getClocks(Automaton automaton) {
		
		EList<String> ret = new BasicEList<String>();
		try {
			IExtension extension = automaton.findExtension(AUTOMATON_CLOCKS_ID); 
			if (extension != null) {
				ret = StringListExtension.parse(extension.toString().replace("{","").replace("}","")).getValues();
			}
		} catch (ParseException pe) {
			ret = null;
		}
		return ret;
	}
	
	/** Get the clocks updated by a transition. Clocks which keep their value 
	 * but for some reason are explicitely mentioned in the update (x=x) are 
	 * removed.  
	 * @param transition The transition to get the clocks from
	 * @return An EList containing string representations of the clocks updated  
	 * by the transition, or null in case something goes wrong parsing the list 
	 * of clocks. The resulting list will also be empty in case the transition does
	 * not support the clocks update extension. */
	public static EList<String> getUpdatedClocks(Transition transition) {
		
		EList<String> temp = ClockUtils.getUpdates(transition);
		EList<String> ret = new BasicEList<String>();
		for (String s: temp) {
			String[] values = s.split("=");
			if (values[0].trim().equals(values[1].trim())) {
				//do nothing
			} else {
				ret.add(values[0].trim());
			}
		}
		return ret;
	}
	
	/** Get the updates of a transition. 	 
	 * @param transition The transition to get the clocks from
	 * @return An EList containing string representations of the updates of the   
	 * transition (in the form x=y or x=n), or null in case something goes 
	 * wrong while parsing the update. The resulting list will be empty in 
	 * case the transition does not support the clocks update extension.*/
	public static EList<String> getUpdates(Transition transition) {
		
		EList<String> ret = new BasicEList<String>();
		try {
			IExtension extension = transition.findExtension(TRANSITION_UPDATE_ID); 
			if (extension != null) {
				ret = StringListExtension.parse(extension.toString()).getValues();
			}
		} catch (ParseException pe) {
			ret = null;
		}
		return ret;
	}
	
	/** Get the value to which a clock is updated by a transition. 
	 * @param clock The clock for which to find the update.
	 * @param transition The transition from which to get the update. 
	 * @return A string representation of the value to which the clock is 
	 * updated, or the empty string if the transition does not contain an 
	 * update for the clocks. */
	public static String getUpdate(String clock, Transition transition) {
		
		EList<String> updates = ClockUtils.getUpdates(transition);
		for (String upd: updates) {
			if (upd.startsWith(clock)) {
				return upd.split("=")[1];
			}
		}
		return "";		
	}
	
	/** Get the clock guard of a transition. 
	 * @param transition The transition to get the guard for
	 * @return A string representation of the guard of the transition, or the 
	 * empty string in case the transition does not support the guard 
	 * extension (in case the transition does support this extension, but does 
	 * not have a guard, the method returns <tt>true</tt>).    
	 */
	public static String getGuard(Transition transition) {
		
		String ret = "";
		if (transition.findExtension(TRANSITION_GUARD_ID) != null) {
			ret = ((StringExtension) transition.findExtension(TRANSITION_GUARD_ID)).getValue();
		}
		return ret;
	}
	
	/** Whether the transition is timed, that means whether it supports
	 * the any clock extension.
	 * @param t Transition to be checked. 
	 * @return true in case the transition supports either the update extension
	 * or the guard extension. */
	public static boolean isTimed(final Transition t) { 
		
		IExtension extension = t.findExtension(TRANSITION_UPDATE_ID);
		if (extension == null) {
			extension = t.findExtension(TRANSITION_GUARD_ID);
		}
		
		if (extension == null) {
			return false;
		} else {
			return true;
		}
	}

	/** Whether a transition is visible or not. 
	 * @param t Transition to check. 
	 * @return True in case the node set of the transition is not empty, false
	 * otherwise. 
	 * @note Though the distinction visible/invisible is made in TCA only, this
	 * method also covers CA, since the only criterion is whether the node set 
	 * is empty or not. 
	 * @note This method works correctly for memory cells, i.e., where invisible
	 * transitions may have a data constraint. The reason is again that the 
	 * distinction is based on the port set. */
	public static boolean isVisibleTransition(final Transition t) {
		
		if (CA.getPortNames(t).getValues().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
