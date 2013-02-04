package org.ect.ea.extensions.portnames.providers;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.extensions.portnames.IntentionalPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNames;



public class PortNamesProductConditions {
	
	protected State source = new State();
	protected State target = new State();
	protected DelayInformation DI = new DelayInformation();
	
	public static boolean canJoin(Transition t1, Transition t2) {
		
		// The port names of the transitions.
		HashSet<String> n1 = getPortNames(t1);
		HashSet<String> n2 = getPortNames(t2);

		// Get the port names of the automata.
		HashSet<String> N1 = getPortNames(t1.getAutomaton());
		HashSet<String> N2 = getPortNames(t2.getAutomaton());
		
		N1.retainAll(n2);
		N2.retainAll(n1);
		
		return (N1.equals(N2));
		
	}
    	
	/**================================relevant to QIA product====================================================*/
	/**
	 * canIJoin : Checking if t1 and t2 are combined in an interleaving way
	 * 	1) Each event of t1 and t2 are independent.
	 *  2) Composed result should have a single event.     
	 */
	public static boolean canIJoin(Transition t1, Transition t2)
	{
		List<String> newMixed = new Vector<String>();
		boolean independent = true; 
		
		newMixed = getNewMixed(t1,t2);
				
		// Check if t1 and t2 are independent or not. 
		if(!newMixed.isEmpty()){
			IntentionalPortNames Mports1 = (IntentionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			IntentionalPortNames Mports2 = (IntentionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			
			for(String a : Mports1.getFirings()){
				if(newMixed.contains(a))	independent = false;
			}
			for(String b : Mports2.getFirings()){
				if(newMixed.contains(b))	independent = false;
			}
			for(String c: Mports1.getRequests()){
				if(newMixed.contains(c))	independent = false;
			}
			for(String d : Mports2.getRequests()){
				if(newMixed.contains(d))	independent = false;
			}
		}
						
		// Firings
		HashSet<String> n1 = getfirings(t1);
		HashSet<String> n2 = getfirings(t2);
		
		// Requests
		HashSet<String> m1 = getrequests(t1);
		HashSet<String> m2 = getrequests(t2);

		// Check if there is the occurrence of multiple events
		boolean nonMultiEvents = true;
		if(!n1.isEmpty() && !n2.isEmpty()){
			HashSet<String> Aports1 = getPortNames(t1.getAutomaton());
			HashSet<String> Aports2 = getPortNames(t2.getAutomaton());
			Aports1.retainAll(n2);
			Aports2.retainAll(n1);
			
			if(!Aports1.equals(Aports2))	nonMultiEvents = false;
		}
		else if(!m1.isEmpty() && !m2.isEmpty() && !m1.equals(m2))	nonMultiEvents = false;
		else if(m1.isEmpty() && !n1.isEmpty() && !m2.isEmpty() && n2.isEmpty())	nonMultiEvents = false;
		else if(!m1.isEmpty() && n1.isEmpty() && m2.isEmpty() && !n2.isEmpty()) nonMultiEvents = false;
				
		/*if(!m1.isEmpty() && !m2.isEmpty() && !m1.equals(m2)){	// transitions of two different arrivals ex. {A}|{} & {B}|{} 			
            nonMultiEvents = false;			            							    
		}
		if(!m1.isEmpty() && n1.isEmpty() && m2.isEmpty() && !n2.isEmpty()) 	// transitions of a data arrival and a data-flow
			nonMultiEvents = false;											// ex. {A}|{} & {}|{B}  
		if(m1.isEmpty() && !n1.isEmpty() && !m2.isEmpty() && n2.isEmpty())	
			nonMultiEvents = false;											// ex. {}|{A} & {B}|{}
*/		// When {A}|{} (or {}|{A} or {A}|{B}) & {}|{} are checked its independence, 
		// the automatonPort of {}|{} should be checked, if it has common ports with {A}|{} (or {}|{A} or {A}|{B})
		boolean interleaving = true;
		HashSet<String> Comparing = getPortNames(t2.getAutomaton());
		for(String a : m1)
			if(Comparing.contains(a))	interleaving = false;
		for(String b : n1)
			if(Comparing.contains(b))	interleaving = false;
		
		return (independent && nonMultiEvents && interleaving);		
	}
	
	/**
	 * includesMixedNodes : Checking that firing sets includes their request sets (mixed nodes), 
	 * i.e., their mixed nodes are released as their firing occur. 
	 * This function is called from IEAProduct to select correct pair of a transition and its port name set,
	 * because a combined transition has more than one port name sets 
	 */
	public static boolean includesMixedNodes(Transition t1, Transition t2, Transition ports1, Transition ports2){
		boolean result = false;
		IntentionalPortNames n1 = (IntentionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		IntentionalPortNames n2 = (IntentionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		
		// f1, f2 : firing sets
		// N1, N2 : automatonPorts 
		// common : mixed node set 
		HashSet<String> common = new HashSet<String> (n1.getRequests());
		common.addAll(n2.getRequests());
		HashSet<String> f1 = new HashSet<String>(n1.getFirings());
		HashSet<String> f2 = new HashSet<String>(n2.getFirings());
		HashSet<String> N1 = getPortNames(ports1.getAutomaton());
		HashSet<String> N2 = getPortNames(ports2.getAutomaton());
			
		// N1 /\ f2 &  N2 /\ f1
		N1.retainAll(f2);
		N2.retainAll(f1);
			
		//union of f1 and f2		
		HashSet<String> unionf1f2 = new HashSet<String>(f1); 
		unionf1f2.addAll(f2);
			
		if(N1.equals(N2) && unionf1f2.containsAll(common)){
			result = true;
		}
		
		return result;
	}

	/**
	 * canMJoin : Checking if t1 and t2 are able to be synchronized or not.
	 */
	protected static List<State> sources = new Vector<State>();
	protected static List<State> targets = new Vector<State>();
	public static List<State> getSources(){
		return sources;
	}
	public static List<State> getTargets(){
		return targets;
	}
	public static EList<IntentionalPortNames> canMJoin(Transition t1, Transition t2)
	{
		List<String> mixed = getNewMixed(t1, t2);
		
		boolean MT1 = false;
		boolean MT2 = false;
		EList<IntentionalPortNames> transitions = new BasicEList<IntentionalPortNames>();
		for(String a : ((IntentionalPortNames)t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID)).getRequests()){
			if(mixed.contains(a))	MT1 = true;
		}
		for(String c : ((IntentionalPortNames)t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID)).getRequests()){
			if(mixed.contains(c))	MT2 = true;
		}
		
		if(!mixed.isEmpty() && MT1 && MT2){ // When t1 & t2 have mixed nodes in their port names,
			// relevant transitions shrinks into a single transition which has relevant mixed nodes 
			// and their firings together
			CollectingMixed c1 = new CollectingMixed(t1, mixed);
			CollectingMixed c2 = new CollectingMixed(t2, mixed);
						
			if(c1.getCollectable() && c2.getCollectable()){
				List<Transition> CList1 = c1.getCollected();
				List<Transition> CList2 = c2.getCollected();
				for(Transition temp1 : CList1){
					IntentionalPortNames n1 = (IntentionalPortNames) temp1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					for(Transition temp2 : CList2){
						IntentionalPortNames n2 = (IntentionalPortNames) temp2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
																		
						// f1, f2 : firing sets 
						// N1, N2 : AutomatonPort sets 
						// common : mixed node set
						HashSet<String> common = new HashSet<String> (n1.getRequests());
						common.addAll(n2.getRequests());
						HashSet<String> f1 = new HashSet<String>(n1.getFirings());
						HashSet<String> f2 = new HashSet<String>(n2.getFirings());
						HashSet<String> N1 = getPortNames(t1.getAutomaton());
						HashSet<String> N2 = getPortNames(t2.getAutomaton());
							
						// Update N2=f1 /\ N2 , N1=f2 /\ N1
						N1.retainAll(f2);
						N2.retainAll(f1);
							
						//Union of f1 and f2		
						HashSet<String> unionf1f2 = new HashSet<String>(n1.getFirings()); 
						unionf1f2.addAll(f2);
							
						if(N1.equals(N2) && unionf1f2.containsAll(common)){
							transitions.add(n1);
							transitions.add(n2);
							sources.add(temp1.getSource());
							sources.add(temp2.getSource());
							targets.add(temp1.getTarget());
							targets.add(temp2.getTarget());
						}
					
					}
				}		
				
			}
		}		
		return transitions;
	}

	/**
	 * getNewMixed : Returning mixed nodes of the automata of t1 and t2 
	 */
	public static List<String> getNewMixed(Transition t1, Transition t2){
		List<String> mixed = new Vector<String>();
		HashSet<String> ports1 = getPortNames(t1.getAutomaton());
		HashSet<String> ports2 = getPortNames(t2.getAutomaton());
		ports1.retainAll(ports2);
		
		for(String a : ports1)	mixed.add(a);
		
		return mixed;
	}	
	/**================================relevant to QIA product============================================*/
	
	public State getSource(){
		return source;
	}
	
	public State getTarget(){
		return target;
	}
	
	public DelayInformation getDelayInformation(){
		return DI;
	}
	
	public void setSource(State s){
		source = s;
	}
	
	public void setTarget(State s){
		target = s;
	}
	
	public void setDelayInformation(DelayInformation value){
		DI = value;
	}
	
	public static HashSet<String> getPortNames(Transition owner) {
		String id = TransitionPortNamesProvider.EXTENSION_ID;
		return new HashSet<String>(((TransitionPortNames) owner.findExtension(id)).getValues());	
	}

	
	public static HashSet<String> getPortNames(Automaton owner) {
		String id = AutomatonPortNamesProvider.EXTENSION_ID;
		return new HashSet<String>(((AutomatonPortNames) owner.findExtension(id)).getValues());	
	}
	
	
	public static HashSet<String> getrequests(Transition owner)
	{
		String id = IntensionalPortNamesProvider.EXTENSION_ID;
		return new HashSet<String> (((IntentionalPortNames) owner.findExtension(id)).getRequests());
	}

	public static HashSet<String> getfirings(Transition owner)
	{
		String id = IntensionalPortNamesProvider.EXTENSION_ID;
		return new HashSet<String> (((IntentionalPortNames) owner.findExtension(id)).getFirings());
	}
}
