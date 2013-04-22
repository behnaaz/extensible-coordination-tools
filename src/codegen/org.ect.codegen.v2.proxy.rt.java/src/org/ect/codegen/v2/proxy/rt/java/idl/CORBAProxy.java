package org.ect.codegen.v2.proxy.rt.java.idl;

import org.ect.codegen.v2.core.rt.java.Port;
import org.ect.codegen.v2.proxy.rt.java.AbstractInfrastructure;
import org.ect.codegen.v2.proxy.rt.java.AbstractProxy;
import org.ect.codegen.v2.proxy.rt.java.SimulationAutomaton;


public class CORBAProxy extends AbstractProxy<CORBAParcel, CORBAInfrastructure> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(automaton,infrastructure)</code>.
	 * 
	 * @see AbstractProxy#Proxy(SimulationAutomaton, AbstractInfrastructure, PortImpl)
	 */
	public CORBAProxy(final SimulationAutomaton automaton,
			final CORBAInfrastructure infrastructure, final Port exceptionPort) {
		super(automaton, infrastructure, exceptionPort);
	}
}
