package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis2.description.AxisService;
import org.ect.codegen.v2.proxy.rt.java.Resource;
import org.ect.codegen.v2.proxy.rt.java.wsdl.WSDLParser.WSDLParserException;

public class WSDLServices {

	/**
	 * A nonempty map from names to WSDL services.
	 */
	private final Map<String, AxisService> namesToInterfaces;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs (a representation of) the WSDL services specified in the WSDL
	 * resource <code>wsdlResource</code>.
	 * 
	 * @param wsdlResource
	 *            The resource. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>wsdlResource==null</code>.
	 * @throws WSDLParserException
	 *             If something goes wrong before or while parsing.
	 */
	public WSDLServices(final Resource wsdlResource) throws WSDLParserException {
		if (wsdlResource == null)
			throw new NullPointerException();

		this.namesToInterfaces = WSDLParser.parseWSDL(wsdlResource);
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Checks if this collection of services contains an interface named
	 * <code>interfaceName</code>.
	 * 
	 * @param interfaceName
	 *            The name of the interface. Not <code>null</code> or empty.
	 * @return <code>true</code> if this collection of services contains an
	 *         interface named <code>interfaceName</code>; <code>false</code>
	 *         otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>interfaceName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>interfaceName==null</code>.
	 */
	public boolean hasInterface(final String interfaceName) {
		if (interfaceName == null)
			throw new NullPointerException();
		if (interfaceName.isEmpty())
			throw new IllegalArgumentException();

		return namesToInterfaces.containsKey(interfaceName);
	}

	/**
	 * Gets the interface named <code>interfaceName</code> from this collection
	 * of services.
	 * 
	 * @param interfaceName
	 *            The name of the interface. Not <code>null</code> or empty.
	 * @return <code>true</code> if this collection of services contains an
	 *         interface named <code>interfaceName</code>; <code>false</code>
	 *         otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>interfaceName.isEmpty()</code> or
	 *             <code>!hasInterface(interfaceName)</code>.
	 * @throws NullPointerException
	 *             If <code>interfaceName==null</code>.
	 * 
	 * @see #hasInterface(String)
	 */
	public WSDLService getInterface(final String interfaceName) {
		if (interfaceName == null)
			throw new NullPointerException();
		if (!hasInterface(interfaceName))
			throw new IllegalArgumentException();

		final AxisService service = namesToInterfaces.get(interfaceName);
		return new WSDLService(service);
	}

	/**
	 * Gets the interfaces exposed by this collection of services.
	 * 
	 * @return A list of services.
	 */
	public Collection<WSDLService> getInterfaces() {
		final Collection<WSDLService> collection = new ArrayList<WSDLService>();
		for (final AxisService s : namesToInterfaces.values())
			collection.add(new WSDLService(s));
		return collection;
	}

	/**
	 * Gets the names of the interfaces exposed by this collection of services.
	 * 
	 * @return A sorted list. Never <code>null</code>.
	 */
	public List<String> getInterfaceNames() {
		final Set<String> set = namesToInterfaces.keySet();
		final List<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		return list;
	}
}