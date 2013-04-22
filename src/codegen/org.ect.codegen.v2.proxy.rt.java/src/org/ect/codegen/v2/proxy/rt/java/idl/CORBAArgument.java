package org.ect.codegen.v2.proxy.rt.java.idl;

import org.apache.axis2.corba.idl.types.Member;
import org.omg.CORBA.TypeCode;

public class CORBAArgument {

	//
	// FIELDS
	//

	/**
	 * The name of this argument.
	 */
	private final String name;

	/**
	 * The type of this argument.
	 */
	private final TypeCode type;

	//
	// CONSTRUCTORS
	//

	/**
	 * Construct an argument based on the member <code>member</code>.
	 * 
	 * @param member
	 *            The member. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>member==null</code> or
	 *             <code>member.getName()==null</code> or
	 *             <code>member.getDataType()==null</code> or
	 *             <code>member.getDataType().getTypeCode()==null</code>.
	 * 
	 * @see Member#getName()
	 * @see Member#getDataType()
	 * @see DataType#getTypeCode()
	 */
	public CORBAArgument(final Member member) {
		if (member == null || member.getName() == null
				|| member.getDataType() == null
				|| member.getDataType().getTypeCode() == null)
			throw new NullPointerException();

		this.name = member.getName();
		this.type = member.getDataType().getTypeCode();
	}

	//
	// METHODS
	//

	/**
	 * Gets the name of this argument.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the type of this argument.
	 * 
	 * @return A type code. Never <code>null</code>.
	 */
	public TypeCode getType() {
		return type;
	}
}
