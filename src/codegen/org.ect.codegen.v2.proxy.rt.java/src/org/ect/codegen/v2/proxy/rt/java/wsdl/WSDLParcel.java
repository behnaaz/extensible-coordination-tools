package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.StringUtils;
import org.ect.codegen.v2.proxy.rt.java.AbstractParcel;
import org.ect.codegen.v2.proxy.rt.java.Constants;


public class WSDLParcel extends AbstractParcel {

	/**
	 * The arguments of the message to send.
	 */
	private Object[] arguments;

	/**
	 * The message name.
	 */
	private final String messageName;

	/**
	 * The local part of the qualified name of the operation this parcel
	 * constitutes.
	 */
	private final String operationName;

	/**
	 * The service targeted by this parcel.
	 */
	private final WSDLService service;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a WSDL parcel containing the item <code>item</code>
	 * <em>associated</em> with the port named <code>portName</code>. Here,
	 * "associated" means that one of the following holds:
	 * <ul>
	 * <li><code>item</code> has been taken from this port somewhere in the
	 * past; or
	 * <li><code>item</code> will be written on this port somewhere in the
	 * future.
	 * </ul>
	 * 
	 * <p>
	 * This constructor assumes <code>portName</code> to take the form: <center>
	 * {@link Constants#PROXY_VERTEX_NAME_PREFIX}{@code ?<operationName>.<messageName>}
	 * </center>
	 * 
	 * @param portName
	 *            The name of the port. Not <code>null</code> or empty.
	 * @param item
	 *            The item. Not <code>null</code>.
	 * @param deliveryTally
	 *            A tally to mark---release a permit---when the parcel to
	 *            construct either is delivered or cannot be delivered.
	 * @param service
	 *            The service targeted by this parcel. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>item==null</code> or
	 *             <code>deliveryTally==null</code> or
	 *             <code>service==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public WSDLParcel(String portName, final Object item,
			final Semaphore deliveryTally, final WSDLService service) {

		super(portName, item, deliveryTally);

		if (service == null)
			throw new NullPointerException();

		/* Get arguments. */
		if (item instanceof Object[]) {
			final Object[] arguments = (Object[]) item;
			this.arguments = new Object[arguments.length];
			System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
		} else
			this.arguments = new Object[] { item };

		/* Remove a possible prefix from $portName. */
		if (portName.startsWith(Constants.PROXY_VERTEX_NAME_PREFIX))
			portName = portName.substring(Constants.PROXY_VERTEX_NAME_PREFIX.length());

		/* Process $portName. */
		final String[] tokens = portName.split("\\.");
		if (tokens.length == 2) {
			this.operationName = tokens[0];
			this.messageName = tokens[1];
			this.service = service;
			return;
		}

		/*
		 * Throw if $portName consists of more than three or less than two
		 * tokens.
		 */
		throw new IllegalArgumentException();
	}

	//
	// METHODS
	//

	/**
	 * Gets the arguments of the message to send.
	 * 
	 * @return A list. Never <code>null</code>.
	 */
	public List<Object> getArguments() {
		return Arrays.asList(arguments);
	}

	/**
	 * Gets the name of the message represented by this parcel.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getMessageName() {
		return messageName;
	}

	/**
	 * Gets the name of the operation involving the message represented by this
	 * parcel.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getOperationName() {
		if (service.hasOperation(StringUtils.capitalize(operationName)))
			return StringUtils.capitalize(operationName);
		else if (service.hasOperation(StringUtils.uncapitalize(operationName)))
			return StringUtils.uncapitalize(operationName);
		else
			throw new RuntimeException();
	}
}
