package org.ect.codegen.v2.proxy.rt.java.wsdl;

import org.ect.codegen.v2.core.rt.java.Port;
import org.ect.codegen.v2.proxy.rt.java.AbstractInfrastructure;
import org.ect.codegen.v2.proxy.rt.java.AbstractProxy;
import org.ect.codegen.v2.proxy.rt.java.SimulationAutomaton;


public abstract class WSDLProxy extends
		AbstractProxy<WSDLParcel, WSDLInfrastructure> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(automaton,infrastructure)</code>.
	 * 
	 * @see AbstractProxy#Proxy(SimulationAutomaton, AbstractInfrastructure,
	 *      PortImpl)
	 */
	public WSDLProxy(final SimulationAutomaton automaton,
			final WSDLInfrastructure infrastructure, final Port exceptionPort) {
		super(automaton, infrastructure, exceptionPort);
	}
}
