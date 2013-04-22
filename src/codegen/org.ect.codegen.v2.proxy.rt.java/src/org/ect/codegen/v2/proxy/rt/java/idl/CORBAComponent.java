package org.ect.codegen.v2.proxy.rt.java.idl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ect.codegen.v2.proxy.rt.java.Resource;
import org.ect.codegen.v2.proxy.rt.java.idl.CORBAParser.CORBAParserException;

public class CORBAComponent {

	//
	// FIELDS
	//

	/**
	 * A map from names to IDL interface representations.
	 */
	private final Map<String, CORBAInterface> namesToInterfaces;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs (a representation of) the CORBA component specified in the IDL
	 * resource <code>idlResource</code>.
	 * 
	 * @param idlResource
	 *            The resource. Not <code>null</code>.
	 * @throws CORBAParserException
	 *             If something goes wrong before or while constructing.
	 * @throws NullPointerException
	 *             If <code>idlResource==null</code>.
	 */
	public CORBAComponent(final Resource idlResource)
			throws CORBAParserException {
		if (idlResource == null)
			throw new NullPointerException();

		this.namesToInterfaces = CORBAParser.parse(idlResource);
	}

	//
	// METHODS
	//

	/**
	 * Gets the interface named <code>interfaceName</code>.
	 * 
	 * @param interfaceName
	 *            The name. Not <code>null</code>.
	 * @return An interface. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasInterface(interfaceName)</code>.
	 * @throws NullPointerException
	 *             If <code>interfaceName==null</code>.
	 * 
	 * @see #hasInterface(String)
	 */
	public CORBAInterface getInterface(final String interfaceName) {
		if (interfaceName == null)
			throw new NullPointerException();
		if (!hasInterface(interfaceName))
			throw new IllegalArgumentException();

		return namesToInterfaces.get(interfaceName);
	}

	/**
	 * Gets the names of the interfaces exposed by this component.
	 * 
	 * @return A sorted list. Never <code>null</code>.
	 */
	public List<String> getInterfaceNames() {
		final Set<String> set = namesToInterfaces.keySet();
		final List<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		return list;
	}

	/**
	 * Gets the interfaces exposed by this component.
	 * 
	 * @return A list of services.
	 */
	public Collection<CORBAInterface> getInterfaces() {
		return namesToInterfaces.values();
	}

	/**
	 * Checks if this component has an interface named
	 * <code>interfaceName<code>.
	 * 
	 * @param interfaceName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> if this component has an interface named
	 *         <code>interfaceName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>interfaceName==null</code>.
	 */
	public boolean hasInterface(final String interfaceName) {
		if (interfaceName == null)
			throw new NullPointerException();

		return namesToInterfaces.containsKey(interfaceName);
	}
}
