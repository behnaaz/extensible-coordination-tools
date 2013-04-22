package org.ect.codegen.v2.proxy.rt.java;

public class Constants {

	//
	// CONSTRUCTOR
	//

	/**
	 * Deprecates the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private Constants() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC
	//

	/**
	 * The name of the input vertex representing the button.
	 */
	public static final String BUTTON_INPUT_VERTEX_NAME = "ButtonInput";

	/**
	 * The name of the output vertex to which data items, dispatched on the
	 * input vertex named {@link #BUTTON_INPUT_VERTEX_NAME}, flow. No one should
	 * ever perform operations on this output vertex; it merely exists to
	 * express the direction of flow at the input vertex named
	 * {@link #BUTTON_OUTPUT_VERTEX_NAME}.
	 */
	public static final String BUTTON_OUTPUT_VERTEX_NAME = "ButtonOutput";

	/**
	 * The name of the output vertex dispensing exceptions thrown by a proxy.
	 */
	public static final String EXCEPTION_VERTEX_NAME = "Exception";

	/**
	 * The prefix of the names of the vertices on the side of the proxy.
	 */
	public static final String PROXY_VERTEX_NAME_PREFIX = "Proxy";

	/**
	 * The suffix of the names of the vertices on the side of the proxy for
	 * return values of operations.
	 */
	public static final String PROXY_VERTEX_NAME_RESULT_SUFFIX = "Result";

	/**
	 * The prefix of the class name of a party.
	 */
	public static final String PARTY_CLASS_NAME_PREFIX = "ProxyFor";

	/**
	 * The prefix of the class name of a connector representing a simulation
	 * automaton.
	 */
	public static final String SIM_AUT_CLASS_NAME_PREFIX = "ConnectorFor";

	/**
	 * The prefix of the intermediate states used by the proxy to wait until
	 * someone pushes the button.
	 */
	public static final String INTERMEDIATE_STATE_NAME_PREFIX = "PRE$";
}
