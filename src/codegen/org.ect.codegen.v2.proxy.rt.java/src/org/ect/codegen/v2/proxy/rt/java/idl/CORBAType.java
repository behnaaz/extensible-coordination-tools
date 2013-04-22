package org.ect.codegen.v2.proxy.rt.java.idl;

import org.omg.CORBA.Any;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;

public class CORBAType {

	//
	// CONSTURCTORS
	//

	/**
	 * Hides the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private CORBAType() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Extracts an object of type <code>type</code> from the box
	 * <code>box</code>.
	 * 
	 * @param box
	 *            The box. Not <code>null</code>.
	 * @param type
	 *            The type. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>type</code> does not refer to a basic type, or if
	 *             <code>box</code> contains no value of type <code>type</code>.
	 * @throws NullPointerException
	 *             If <code>box==null</code> or <code>type==null</code>.
	 */
	public static Object extractFrom(final Any box, final TypeCode type) {

		if (box == null || type == null)
			throw new NullPointerException();

		/* Switch over all the basic types. */
		try {
			switch (type.kind().value()) {
			case TCKind._tk_boolean:
				return box.extract_boolean();
			case TCKind._tk_char:
				return box.extract_char();
			case TCKind._tk_double:
				return box.extract_double();
			case TCKind._tk_float:
				return box.extract_float();
			case TCKind._tk_long:
				return box.extract_long();
			case TCKind._tk_longlong:
				return box.extract_longlong();
			case TCKind._tk_octet:
				return box.extract_octet();
			case TCKind._tk_short:
				return box.extract_short();
			case TCKind._tk_string:
				return box.extract_string();
			case TCKind._tk_ulong:
				return box.extract_ulong();
			case TCKind._tk_ulonglong:
				return box.extract_ulonglong();
			case TCKind._tk_ushort:
				return box.extract_ushort();
			case TCKind._tk_wchar:
				return box.extract_wchar();
			case TCKind._tk_wstring:
				return box.extract_wstring();
			default:
				throw new IllegalArgumentException();
			}
		} catch (BAD_OPERATION e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Inserts the object <code>object</code> of type <code>type</code> the box
	 * <code>box</code>.
	 * 
	 * @param box
	 *            The box. Not <code>null</code>.
	 * @param type
	 *            The type. Not <code>null</code>.
	 * @param object
	 *            The object; <code>null</code> means the default value of
	 *            <code>type</code>.
	 * @throws IllegalArgumentException
	 *             If <code>type</code> does not refer to a basic type.
	 * @throws NullPointerException
	 *             If <code>box==null</code> or <code>type==null</code>.
	 */
	public static void insertInto(final Any box, final TypeCode type,
			final Object object) {

		if (box == null || type == null)
			throw new NullPointerException();

		/* Prepare a string. */
		final String string = object instanceof String ? (String) object : null;

		/* Switch over all the basic types. */
		switch (type.kind().value()) {

		case TCKind._tk_boolean:
			if (object instanceof Boolean)
				box.insert_boolean((Boolean) object);
			else if (object instanceof String)
				box.insert_boolean(Boolean.parseBoolean(string));
			else
				box.insert_boolean(false);
			return;

		case TCKind._tk_char:
			if (object instanceof Character)
				box.insert_char((Character) object);
			else if (object instanceof String && !string.isEmpty())
				box.insert_char(string.charAt(0));
			else
				box.insert_char('0');
			return;

		case TCKind._tk_double:
			if (object instanceof Double)
				box.insert_double((Double) object);
			else if (object instanceof String)
				try {
					box.insert_double(Double.parseDouble(string));
				} catch (NumberFormatException e) {
					box.insert_double(0);
				}
			else
				box.insert_double(0);
			return;

		case TCKind._tk_float:
			if (object instanceof Float)
				box.insert_float((Float) object);
			else if (object instanceof String)
				try {
					box.insert_float(Float.parseFloat(string));
				} catch (NumberFormatException e) {
					box.insert_float(0);
				}
			else
				box.insert_float(0);
			return;

		case TCKind._tk_long:
			if (object instanceof Integer)
				box.insert_long((Integer) object);
			else if (object instanceof String)
				try {
					box.insert_long(Integer.parseInt(string));
				} catch (NumberFormatException e) {
					box.insert_long(0);
				}
			else
				box.insert_long(0);
			return;

		case TCKind._tk_longlong:
			if (object instanceof Long)
				box.insert_longlong((Long) object);
			else if (object instanceof String)
				try {
					box.insert_longlong(Long.parseLong(string));
				} catch (NumberFormatException e) {
					box.insert_longlong(0);
				}
			else
				box.insert_longlong(0);
			return;

		case TCKind._tk_octet:
			if (object instanceof Byte)
				box.insert_octet((Byte) object);
			else if (object instanceof String)
				try {
					box.insert_octet(Byte.parseByte(string));
				} catch (NumberFormatException e) {
					box.insert_octet((byte) 0);
				}
			else
				box.insert_octet((byte) 0);
			return;

		case TCKind._tk_short:
			if (object instanceof Short)
				box.insert_short((Short) object);
			else if (object instanceof String)
				try {
					box.insert_short(Short.parseShort(string));
				} catch (NumberFormatException e) {
					box.insert_short((short) 0);
				}
			else
				box.insert_short((short) 0);
			return;

		case TCKind._tk_string:
			if (object instanceof String)
				box.insert_string(string);
			else
				box.insert_string("0");
			return;

		case TCKind._tk_ulong:
			if (object instanceof Integer)
				box.insert_long((Integer) object);
			else if (object instanceof String)
				try {
					box.insert_ulong(Integer.parseInt(string));
				} catch (NumberFormatException e) {
					box.insert_ulong(0);
				}
			else
				box.insert_ulong(0);
			return;

		case TCKind._tk_ulonglong:
			if (object instanceof Long)
				box.insert_longlong((Long) object);
			else if (object instanceof String)
				try {
					box.insert_ulonglong(Long.parseLong(string));
				} catch (NumberFormatException e) {
					box.insert_ulonglong(0);
				}
			else
				box.insert_ulonglong(0);
			return;

		case TCKind._tk_ushort:
			if (object instanceof Short)
				box.insert_short((Short) object);
			else if (object instanceof String)
				try {
					box.insert_ushort(Short.parseShort(string));
				} catch (NumberFormatException e) {
					box.insert_ushort((short) 0);
				}
			else
				box.insert_ushort((short) 0);
			return;

		case TCKind._tk_wchar:
			if (object instanceof Character)
				box.insert_char((Character) object);
			else if (object instanceof String && !string.isEmpty())
				box.insert_char(string.charAt(0));
			else
				box.insert_char('0');
			return;

		case TCKind._tk_wstring:
			if (object instanceof String)
				box.insert_string(string);
			else
				box.insert_string("0");
			return;

		default:
			throw new IllegalArgumentException();
		}
	}
}
