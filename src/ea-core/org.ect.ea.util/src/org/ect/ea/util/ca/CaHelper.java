package org.ect.ea.util.ca;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.ea.extensions.statememory.StateMemory;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;
import org.ect.ea.util.text.serialize.CatPrinter;


public final class CaHelper {
	static final String 	// Extension IDs used for constraint automata (with memory)
		AUTOMATON_PORTNAMES = AutomatonPortNamesProvider.EXTENSION_ID,
		TRANSITION_PORTNAMES = TransitionPortNamesProvider.EXTENSION_ID,
		START_STATES = StartStateExtensionProvider.EXTENSION_ID,
		STATE_MEMORY = StateMemoryExtensionProvider.EXTENSION_ID,
		CONSTRAINTS = ConstraintExtensionProvider.EXTENSION_ID;

	private Automaton automaton;
	private int sno;
	
	public CaHelper(String name) {
		automaton = new Automaton(name);
		automaton.useExtensionType(START_STATES);
		automaton.useExtensionType(STATE_MEMORY);
		automaton.useExtensionType(CONSTRAINTS);
		automaton.useExtensionType(AUTOMATON_PORTNAMES);
		automaton.useExtensionType(TRANSITION_PORTNAMES);
		AutomatonPortNames ports = new AutomatonPortNames();
		automaton.updateExtension(ports);
	}

	public CaHelper(String name, Collection<String> portNames) {
		this(name);
		AutomatonPortNames ports = (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORTNAMES);
		if (portNames!=null)
			for (String p: portNames)
				ports.getValues().add(p);
	}
	
	public CaHelper(String name, Collection<String> in, Collection<String>  out) {
		this(name);
		AutomatonPortNames ports = (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORTNAMES);
		if (in!=null)
			for (String p: in)
				ports.getInPorts().add(p);
		
		if (out!=null)
			for (String p: out)
				ports.getOutPorts().add(p);
	}
	
	public State createState() {
		return createState("s"+sno++, false, Collections.<String, String>emptyMap());
	}
	
	public State createState(String name, boolean isStart, Map<String, String> memory) {
		State s = new State(name==null? "s"+sno++ : name);
		automaton.getStates().add(s);

		BooleanExtension ss = new BooleanExtension(isStart);
		ss.setId(START_STATES);
		s.updateExtension(ss);

		if (memory==null)
			return s;
		
		StateMemory mem = new StateMemory();
		s.updateExtension(mem);


		EMap<String,String> init = ((StateMemory) s.findExtension(STATE_MEMORY)).getInitializations();
		for (Map.Entry<String, String> m: memory.entrySet()) {
			addUnique(m.getKey(), mem);
			init.put(m.getKey(),m.getValue());
		}
		
		return s;
	}

	public Transition createTransition(State from, State to, Collection<String> ports, Constraint con) {
		Transition t = new Transition(from, to);		
		getAutomaton().getTransitions().add(t);
		
		if (ports!=null)
			addPorts(t, ports);		
		if (con!=null)
			addCon(t, con);
		
		return t;
	}

	public Transition createTransition(State from, State to, Constraint con) {
		return createTransition(from, to, Collections.<String>emptyList(), con);
	}
	
	public Transition createTransition(State from, State to, Collection<String> ports) {
		return createTransition(from, to, ports, null);
	}
	
	private Transition addCon(Transition t, Constraint con) {
		t.updateExtension(con);
		
		StringListExtension ports = (TransitionPortNames) t.findExtension(TRANSITION_PORTNAMES),
		fMem  = (StateMemory)t.getSource().findExtension(STATE_MEMORY),
		tMem  = (StateMemory)t.getTarget().findExtension(STATE_MEMORY);
		
		for (Element e: con.getAllElements()) { 
			String name = e.getValue();
			switch (e.getType()) {
			case IDENTIFIER:
				addUnique(name, ports);
				break;
			case SOURCE_MEMORY:
				addUnique(name, fMem);
				break;				
			case TARGET_MEMORY:
				addUnique(name, tMem);
				break;
			default:
				break;
			}		
		}
		return t;
	}
	
	private Transition addPorts(Transition t, Collection<String> ports) {
		TransitionPortNames tp = new TransitionPortNames();
		t.updateExtension(tp);
		for (String p:ports)
			addUnique(p, tp);
		
		return t;		
	}
	
	private boolean addUnique(String s, StringListExtension c) {
		if (!c.getValues().contains(s))
			return c.getValues().add(s);
		
		return false;
	}
	
	public void validatePorts() {
		AutomatonPortNames ap = (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORTNAMES);
		for (Transition t : automaton.getTransitions()) {
			TransitionPortNames ports = (TransitionPortNames) t.findExtension(TRANSITION_PORTNAMES);
			for (String p : ports.getValues()) {
				if (!(ap.getInPorts().contains(p) || ap.getOutPorts().contains(p)))
					addUnique(p, ap);
			}
		}
	}
	
	public Automaton getAutomaton() {
		return automaton;
	}
	
	
  	public static void main(String[] args) throws Exception {
		CaHelper helper = new CaHelper("foo");
		Map<String,String> m = new HashMap<String,String>();
		m.put("y", "0");
		State s0 = helper.createState("s0", true, m);
		State s1 = helper.createState();
		Constraint parse = ConstraintParserHelper.parse("$t.x=A");
		helper.createTransition(s0, s1, parse);
		helper.createTransition(s1, s0, ConstraintParserHelper.parse("B=$s.x"));
		
		System.out.println(
				new CatPrinter().visit(helper.getAutomaton()));
		
//		helper.serialize(System.err);
	}
}
