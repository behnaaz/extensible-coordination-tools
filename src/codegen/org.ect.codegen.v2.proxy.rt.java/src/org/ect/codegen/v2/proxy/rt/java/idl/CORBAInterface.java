package org.ect.codegen.v2.proxy.rt.java.idl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.axis2.corba.idl.types.Interface;
import org.apache.axis2.corba.idl.types.Member;
import org.apache.axis2.corba.idl.types.Operation;
import org.ect.codegen.v2.proxy.rt.java.NamedObject;
import org.omg.CORBA.TypeCode;

public class CORBAInterface extends NamedObject {

	/**
	 * The name of this interface.
	 */
	private final String interfaceName;

	/**
	 * The Axis2 representation of this interface.
	 */
	private final Interface interfaze;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an interface representation based on the Axis2 interface
	 * representation <code>interfaze</code>.
	 * 
	 * @param interfaceName
	 *            The name of the interface. Not <code>null</code>.
	 * @param interfaze
	 *            The Axis2 interface representation. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>interfaceName==null</code> or
	 *             <code>interfaze==null</code>.
	 */
	public CORBAInterface(final String interfaceName, final Interface interfaze) {
		super(interfaceName);
		if (interfaceName == null || interfaze == null)
			throw new NullPointerException();

		this.interfaceName = interfaceName;
		this.interfaze = interfaze;
	}

	//
	// METHODS
	//

	/**
	 * Checks if the operation named <code>operationName</code> has a return
	 * type.
	 * 
	 * @param operationName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> if the operation named
	 *         <code>operationName</code> has a return type; <code>false</code>
	 *         otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>!hasOperation(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #hasOperation(String)
	 */
	public boolean checkReturnTypeOf(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName))
			throw new IllegalArgumentException();

		final Operation operation = getOperation(operationName);
		return operation.getReturnType() != null;
	}

	/**
	 * Gets the name of this interface.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * Gets the the arguments of the operation named <code>operationName</code>.
	 * 
	 * @param operationName
	 *            The name. Not <code>null</code>.
	 * @return A list of arguments. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasOperation(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #hasOperation(String)
	 */
	public List<CORBAArgument> getArguments(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName))
			throw new IllegalArgumentException();

		final List<CORBAArgument> arguments = new ArrayList<CORBAArgument>();

		final Operation operation = getOperation(operationName);
		for (final Object p : operation.getParams())
			if (p instanceof Member)
				try {
					arguments.add(new CORBAArgument((Member) p));
				} catch (NullPointerException e) {
					continue;
				}

		return arguments;
	}

	/**
	 * Gets the operation named <code>operationName</code>.
	 * 
	 * @param operationName
	 *            The name. Not <code>null</code>.
	 * @return An operation. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasOperation(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #hasOperation(String)
	 */
	public Operation getOperation(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (operationName.isEmpty())
			throw new IllegalArgumentException();

		final String lcOperationName = operationName.toLowerCase();
		for (final Operation o : interfaze.getOperations())
			if (lcOperationName.equals(o.getName().toLowerCase()))
				return o;

		throw new IllegalArgumentException();
	}

	/**
	 * Gets the names of the operations exposed by this interface.
	 * 
	 * @return A sorted list. Never <code>null</code>.
	 */
	public List<String> getOperationNames() {
		final Set<String> set = new HashSet<String>();

		for (final Operation o : interfaze.getOperations())
			set.add(o.getName());

		final List<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		return list;
	}

	/**
	 * Gets the type of the return value of the operation named
	 * <code>operationName</code>.
	 * 
	 * @param operationName
	 *            The name. Not <code>null</code>.
	 * @return A type code. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!checkReturnTypeOf(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #checkReturnTypeOf(String)
	 */
	public TypeCode getReturnTypeOf(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (!checkReturnTypeOf(operationName))
			throw new IllegalArgumentException();

		final Operation operation = getOperation(operationName);
		return operation.getReturnType().getTypeCode();
	}

	/**
	 * Checks if this interface has an operation named
	 * <code>operationName</code>.
	 * 
	 * @param operationName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> if this component has an operation named
	 *         <code>operationName</code>; <code>false</code> otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public boolean hasOperation(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (operationName.isEmpty())
			throw new IllegalArgumentException();

		final String lcOperationName = operationName.toLowerCase();
		for (final Operation o : interfaze.getOperations())
			if (lcOperationName.equals(o.getName().toLowerCase()))
				return true;

		return false;
	}

	/**
	 * Constructs a CORBA object (representation) named <code>name</code>,
	 * implementing this interface, accessible through the ORB hosted at the
	 * location <code>host</code> behind port <code>port</code>.
	 * 
	 * @param name
	 *            The name. Not <code>null</code> or empty.
	 * @param host
	 *            The location. Not <code>null</code> or empty.
	 * @param port
	 *            The nonnegative port.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>host.isEmpty()</code> or <code>name.isEmpty()</code>
	 *             or <code>port&lt;0</code>.
	 * @throws NullPointerException
	 *             If <code>host==null</code> or <code>name==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public CORBAObject newObject(final String name, final String host,
			final int port) {

		return new CORBAObject(name, host, port);
	}

	//
	// CLASSES
	//

	public class CORBAObject extends CORBAInterface {

		//
		// FIELDS
		//

		/**
		 * The host of the ORB providing access to this CORBA object.
		 */
		private final String host;

		/**
		 * The name of this CORBA object.
		 */
		private final String name;

		/**
		 * The port of the ORB providing access to this CORBA object.
		 */
		private final int port;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a CORBA object (representation) named <code>name</code>,
		 * accessible through the ORB hosted at the location <code>host</code>
		 * behind port <code>port</code>.
		 * 
		 * @param name
		 *            The name. Not <code>null</code> or empty.
		 * @param host
		 *            The location. Not <code>null</code> or empty.
		 * @param port
		 *            The nonnegative port.
		 * @throws IllegalArgumentException
		 *             If <code>host.isEmpty()</code> or
		 *             <code>name.isEmpty()</code> or <code>port&lt;0</code>.
		 * @throws NullPointerException
		 *             If <code>host==null</code> or <code>name==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		private CORBAObject(final String name, final String host, final int port) {
			super(CORBAInterface.this.interfaceName,
					CORBAInterface.this.interfaze);

			if (host == null || name == null)
				throw new NullPointerException();
			if (host.isEmpty() || name.isEmpty() || port < 0)
				throw new IllegalArgumentException();

			this.host = host;
			this.name = name;
			this.port = port;
		}

		//
		// METHODS
		//

		/**
		 * Gets the host of the ORB providing access to this CORBA object.
		 * 
		 * @return A string. Never <code>null</code>.
		 */
		public String getHost() {
			return host;
		}

		/**
		 * Gets the name of this CORBA object.
		 * 
		 * @return A string. Never <code>null</code>.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Gets the port of the ORB providing access to this CORBA object.
		 * 
		 * @return A nonnegative integer.
		 */
		public int getPort() {
			return port;
		}

	}
}
