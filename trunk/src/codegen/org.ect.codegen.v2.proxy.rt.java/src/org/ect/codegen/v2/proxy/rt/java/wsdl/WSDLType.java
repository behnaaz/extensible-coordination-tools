package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaType;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDuration;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlGDay;
import org.apache.xmlbeans.XmlGMonth;
import org.apache.xmlbeans.XmlGMonthDay;
import org.apache.xmlbeans.XmlGYear;
import org.apache.xmlbeans.XmlGYearMonth;
import org.apache.xmlbeans.XmlTime;

public enum WSDLType {

	/* Basic primitive types. */
	ANY_U_R_I, BASE64_BINARY, BOOLEAN, DECIMAL, DOUBLE, FLOAT, HEX_BINARY, _N_O_T_A_T_I_O_N, _Q_NAME, STRING,

	/* Calendar primitive types */
	DATE, DATE_TIME, DURATION, G_DAY, G_MONTH, G_MONTH_DAY, G_YEAR, G_YEAR_MONTH, TIME,

	/* Decimal derived types. */
	BYTE, INT, INTEGER, LONG, NEGATIVE_INTEGER, NON_NEGATIVE_INTEGER, NON_POSITIVE_INTEGER, POSITIVE_INTEGER, SHORT, UNSIGNED_BYTE, UNSIGNED_INT, UNSIGNED_LONG, UNSIGNED_SHORT;

	//
	// METHODS
	//

	public String convertToString(Object object) {
		if (object instanceof String)
			object = parseToObject((String) object);

		final String text;

		/* Get the text to set. */
		switch (this) {
		case _N_O_T_A_T_I_O_N:
		case _Q_NAME:
			text = object instanceof QName ? ((QName) object).getPrefix() + ":"
					+ ((QName) object).getLocalPart() : "po:USAddress";
			break;

		case ANY_U_R_I:
			text = object instanceof URI ? object.toString()
					: "http://www.example.com/";
			break;

		case BASE64_BINARY:
			if (object instanceof Byte[])
				text = new String(Base64.encodeBase64(cast((Byte[]) object)));
			else if (object instanceof byte[])
				text = new String(Base64.encodeBase64((byte[]) object));
			else
				text = "GpM7";
			break;

		case BOOLEAN:
			text = object instanceof Boolean ? object.toString() : "false";
			break;

		case BYTE:
			text = object instanceof Byte ? object.toString() : "0";
			break;

		case DATE:
			if (object instanceof XmlDate)
				text = ((XmlDate) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.DATE))
				text = object.toString();
			else
				text = "1999-05-31";
			break;

		case DATE_TIME:
			if (object instanceof XmlDateTime)
				text = ((XmlDateTime) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.DATETIME))
				text = object.toString();
			else
				text = "1999-05-31T13:20:00.000-05:00";
			break;

		case DECIMAL:
			text = object instanceof BigDecimal ? object.toString() : "0";
			break;

		case DOUBLE:
			text = object instanceof Double ? object.toString() : "0";
			break;

		case DURATION:
			if (object instanceof XmlDuration)
				text = ((XmlDuration) object).getStringValue();
			else if (object instanceof Duration)
				text = object.toString();
			else
				text = "P1Y2M3DT10H30M12.3S";
			break;

		case FLOAT:
			text = object instanceof Float ? object.toString() : "0";
			break;

		case G_DAY:
			if (object instanceof XmlGDay)
				text = ((XmlGDay) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GDAY))
				text = object.toString();
			else
				text = "---31";
			break;

		case G_MONTH:
			if (object instanceof XmlGMonth)
				text = ((XmlGMonth) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GMONTH))
				text = object.toString();
			else
				text = "--05";
			break;

		case G_MONTH_DAY:
			if (object instanceof XmlGMonthDay)
				text = ((XmlGMonthDay) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GMONTHDAY))
				text = object.toString();
			else
				text = "--05-31";
			break;

		case G_YEAR:
			if (object instanceof XmlGYear)
				text = ((XmlGYear) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GYEAR))
				text = object.toString();
			else
				text = "1999";
			break;

		case G_YEAR_MONTH:
			if (object instanceof XmlGYearMonth)
				text = ((XmlGYearMonth) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GYEARMONTH))
				text = object.toString();
			else
				text = "1999-02";
			break;

		case HEX_BINARY:
			if (object instanceof Byte[])
				text = new String(Hex.encodeHex(cast((Byte[]) object)));
			else if (object instanceof byte[])
				text = new String(Hex.encodeHex((byte[]) object));
			else
				text = "0FB7";
			break;

		case INT:
		case UNSIGNED_SHORT:
			text = object instanceof Integer ? object.toString() : "0";
			break;

		case INTEGER:
		case NON_NEGATIVE_INTEGER:
		case NON_POSITIVE_INTEGER:
		case UNSIGNED_LONG:
			text = object instanceof BigInteger ? object.toString() : "0";
			break;

		case NEGATIVE_INTEGER:
			text = object instanceof BigInteger ? object.toString() : "-1";
			break;

		case POSITIVE_INTEGER:
			text = object instanceof BigInteger ? object.toString() : "1";
			break;

		case LONG:
		case UNSIGNED_INT:
			text = object instanceof Long ? object.toString() : "0";
			break;

		case SHORT:
		case UNSIGNED_BYTE:
			text = object instanceof Short ? object.toString() : "0";
			break;

		case STRING:
			// text = object instanceof String ? object.toString()
			// : "Confirm this is electric";
			text = object.toString();
			break;

		case TIME:
			if (object instanceof XmlTime)
				text = ((XmlTime) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.TIME))
				text = object.toString();
			else
				text = "13:20:00.000";
			break;

		default:
			throw new IllegalStateException();
		}

		return text;
	}

	/**
	 * Parses the string <code>string</code> formatted according to this type to
	 * an object. If <code>string</code> is not well-formatted, this method uses
	 * a default value from <a
	 * href="http://www.w3.org/TR/xmlschema-0/#simpleTypesTable">this table</a>.
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @return An object. Never <code>null</code>
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 */
	public Object parseToObject(final String string) {
		if (string == null)
			throw new NullPointerException();

		switch (this) {
		case _N_O_T_A_T_I_O_N:
		case _Q_NAME:
			final String[] tokens = string.split(":");
			if (tokens.length == 1)
				return new QName(tokens[0]);
			else if (tokens.length == 2)
				return new QName(null, tokens[0], tokens[1]);
			else
				return new QName(null, "po", "USAddress");

		case ANY_U_R_I:
			try {
				return new URI(string);
			} catch (URISyntaxException e) {
				try {
					return new URI("http://www.example.org");
				} catch (URISyntaxException ee) {
					throw new RuntimeException();
				}
			}

		case BASE64_BINARY:
			return Base64.decodeBase64(string.getBytes());

		case BOOLEAN:
			return string.equals("true") || string.equals("1");

		case BYTE:
			try {
				return Byte.parseByte(string);
			} catch (NumberFormatException e) {
				return (byte) 0;
			}

		case DATE:
			try {
				return XmlDate.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlDate.Factory.parse("1999-05-31");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case DATE_TIME:
			try {
				return XmlDateTime.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlDateTime.Factory
							.parse("1999-05-31T13:20:00.000-05:00");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case DECIMAL:
			try {
				return new BigDecimal(string);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}

		case DOUBLE:
			try {
				return Double.parseDouble(string);
			} catch (NumberFormatException e) {
				return (double) 0;
			}

		case DURATION:
			try {
				return XmlDuration.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlDuration.Factory.parse("P1Y2M3DT10H30M12.3S");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case FLOAT:
			try {
				return Float.parseFloat(string);
			} catch (NumberFormatException e) {
				return (float) 0;
			}

		case G_DAY:
			try {
				return XmlGDay.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlGDay.Factory.parse("---31");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case G_MONTH:
			try {
				return XmlGMonth.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlGMonth.Factory.parse("--05");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case G_MONTH_DAY:
			try {
				return XmlGMonthDay.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlGMonthDay.Factory.parse("--05-31");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case G_YEAR:
			try {
				return XmlGYear.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlGYear.Factory.parse("1999");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case G_YEAR_MONTH:
			try {
				return XmlGYearMonth.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlGYearMonth.Factory.parse("1999-02");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		case HEX_BINARY:
			try {
				return Hex.decodeHex(string.toCharArray());
			} catch (DecoderException e) {
				throw new RuntimeException();
			}

		case INT:
		case UNSIGNED_SHORT:
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException e) {
				return 0;
			}

		case INTEGER:
		case NON_NEGATIVE_INTEGER:
		case NON_POSITIVE_INTEGER:
		case UNSIGNED_LONG:
			try {
				return new BigInteger(string);
			} catch (NumberFormatException e) {
				return BigInteger.ZERO;
			}

		case NEGATIVE_INTEGER:
			try {
				return new BigInteger(string);
			} catch (NumberFormatException e) {
				return BigInteger.valueOf(-1);
			}

		case POSITIVE_INTEGER:
			try {
				return new BigInteger(string);
			} catch (NumberFormatException e) {
				return BigInteger.valueOf(1);
			}

		case LONG:
		case UNSIGNED_INT:
			try {
				return Long.parseLong(string);
			} catch (NumberFormatException e) {
				return (long) 0;
			}

		case SHORT:
		case UNSIGNED_BYTE:
			try {
				return Short.parseShort(string);
			} catch (NumberFormatException e) {
				return (short) 0;
			}

		case STRING:
			return string;

		case TIME:
			try {
				return XmlTime.Factory.parse(string);
			} catch (XmlException e) {
				try {
					return XmlTime.Factory.parse("13:20:00.000");
				} catch (XmlException ee) {
					throw new RuntimeException();
				}
			}

		default:
			throw new IllegalStateException();
		}
	}

	/**
	 * Sets the text of the element <code>element</code> with a string
	 * representation of the object <code>object</code> formatted according to
	 * this type. If this method cannot format <code>object</code>, it uses a
	 * default value from <a
	 * href="http://www.w3.org/TR/xmlschema-0/#simpleTypesTable">this table</a>.
	 * 
	 * @param element
	 *            The element. Not <code>null</code>.
	 * @param object
	 *            The object.
	 * @throws NullPointerException
	 *             If <code>element==null</code>.
	 */
	public void setTextOf(final OMElement element, Object object) {
		if (element == null)
			throw new NullPointerException();

		if (object instanceof String)
			object = parseToObject((String) object);

		final String text;

		/* Get the text to set. */
		switch (this) {
		case _N_O_T_A_T_I_O_N:
		case _Q_NAME:
			text = object instanceof QName ? ((QName) object).getPrefix() + ":"
					+ ((QName) object).getLocalPart() : "po:USAddress";
			break;

		case ANY_U_R_I:
			text = object instanceof URI ? object.toString()
					: "http://www.example.com/";
			break;

		case BASE64_BINARY:
			if (object instanceof Byte[])
				text = new String(Base64.encodeBase64(cast((Byte[]) object)));
			else if (object instanceof byte[])
				text = new String(Base64.encodeBase64((byte[]) object));
			else
				text = "GpM7";
			break;

		case BOOLEAN:
			text = object instanceof Boolean ? object.toString() : "false";
			break;

		case BYTE:
			text = object instanceof Byte ? object.toString() : "0";
			break;

		case DATE:
			if (object instanceof XmlDate)
				text = ((XmlDate) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.DATE))
				text = object.toString();
			else
				text = "1999-05-31";
			break;

		case DATE_TIME:
			if (object instanceof XmlDateTime)
				text = ((XmlDateTime) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.DATETIME))
				text = object.toString();
			else
				text = "1999-05-31T13:20:00.000-05:00";
			break;

		case DECIMAL:
			text = object instanceof BigDecimal ? object.toString() : "0";
			break;

		case DOUBLE:
			text = object instanceof Double ? object.toString() : "0";
			break;

		case DURATION:
			if (object instanceof XmlDuration)
				text = ((XmlDuration) object).getStringValue();
			else if (object instanceof Duration)
				text = object.toString();
			else
				text = "P1Y2M3DT10H30M12.3S";
			break;

		case FLOAT:
			text = object instanceof Float ? object.toString() : "0";
			break;

		case G_DAY:
			if (object instanceof XmlGDay)
				text = ((XmlGDay) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GDAY))
				text = object.toString();
			else
				text = "---31";
			break;

		case G_MONTH:
			if (object instanceof XmlGMonth)
				text = ((XmlGMonth) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GMONTH))
				text = object.toString();
			else
				text = "--05";
			break;

		case G_MONTH_DAY:
			if (object instanceof XmlGMonthDay)
				text = ((XmlGMonthDay) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GMONTHDAY))
				text = object.toString();
			else
				text = "--05-31";
			break;

		case G_YEAR:
			if (object instanceof XmlGYear)
				text = ((XmlGYear) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GYEAR))
				text = object.toString();
			else
				text = "1999";
			break;

		case G_YEAR_MONTH:
			if (object instanceof XmlGYearMonth)
				text = ((XmlGYearMonth) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.GYEARMONTH))
				text = object.toString();
			else
				text = "1999-02";
			break;

		case HEX_BINARY:
			if (object instanceof Byte[])
				text = new String(Hex.encodeHex(cast((Byte[]) object)));
			else if (object instanceof byte[])
				text = new String(Hex.encodeHex((byte[]) object));
			else
				text = "0FB7";
			break;

		case INT:
		case UNSIGNED_SHORT:
			text = object instanceof Integer ? object.toString() : "0";
			break;

		case INTEGER:
		case NON_NEGATIVE_INTEGER:
		case NON_POSITIVE_INTEGER:
		case UNSIGNED_LONG:
			text = object instanceof BigInteger ? object.toString() : "0";
			break;

		case NEGATIVE_INTEGER:
			text = object instanceof BigInteger ? object.toString() : "-1";
			break;

		case POSITIVE_INTEGER:
			text = object instanceof BigInteger ? object.toString() : "1";
			break;

		case LONG:
		case UNSIGNED_INT:
			text = object instanceof Long ? object.toString() : "0";
			break;

		case SHORT:
		case UNSIGNED_BYTE:
			text = object instanceof Short ? object.toString() : "0";
			break;

		case STRING:
			text = object instanceof String ? object.toString()
					: "Confirm this is electric";
			break;

		case TIME:
			if (object instanceof XmlTime)
				text = ((XmlTime) object).getStringValue();
			else if (object instanceof XMLGregorianCalendar
					&& ((XMLGregorianCalendar) object).getXMLSchemaType()
							.equals(DatatypeConstants.TIME))
				text = object.toString();
			else
				text = "13:20:00.000";
			break;

		default:
			throw new IllegalStateException();
		}

		/* Set the text to set. */
		element.setText(text);
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * A map from schema type names to types.
	 */
	private static Map<String, WSDLType> schemaTypeNamesToTypes = new HashMap<String, WSDLType>();

	//
	// STATIC - INITIALIZERS
	//

	/* Initialize $schemaTypeNamesToTypes. */
	static {
		for (final WSDLType t : values()) {
			final String name = t.name();

			/* Convert the string representation of $t to a schema type name. */
			final StringBuilder builder = new StringBuilder();
			for (int i = 0; i < name.length(); i++)
				if (i < name.length() - 1 && name.charAt(i) == '_')
					builder.append(name.charAt(++i));
				else
					builder.append(Character.toLowerCase(name.charAt(i)));

			schemaTypeNamesToTypes.put(builder.toString(), t);
		}
	}

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Gets the schema type name of the schema element <code>element</code>.
	 * 
	 * @param element
	 *            The element. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasSchemaTypeName(element)</code>.
	 * @throws NullPointerException
	 *             If <code>element==null</code>>
	 * 
	 * @see #hasSchemaTypeName(XmlSchemaElement)
	 */
	public static String getSchemaTypeNameOf(final XmlSchemaElement element) {
		if (element == null)
			throw new NullPointerException();
		if (!hasSchemaTypeName(element))
			throw new IllegalArgumentException();

		try {
			return element.getSchemaType().getName();
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets the type of the schema element <code>element</code>.
	 * 
	 * @param element
	 *            The element. Not <code>null</code>.
	 * @return A type. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasSupportedSchemaTypeName(element)</code>.
	 * 
	 * @see #hasSupportedSchemaTypeName(XmlSchemaElement)
	 */
	public static WSDLType getTypeOf(final XmlSchemaElement element) {
		if (element == null)
			throw new NullPointerException();
		if (!hasSupportedSchemaTypeName(element))
			throw new IllegalArgumentException();

		try {
			final String schemaTypeName = getSchemaTypeNameOf(element);
			return parseToType(schemaTypeName);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks if the schema element <code>element</code> has a schema type name.
	 * 
	 * @param element
	 *            The element. Not <code>null</code>.
	 * @return <code>true</code> if <code>element</code> has a schema type name;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>element==null</code>.
	 */
	public static boolean hasSchemaTypeName(final XmlSchemaElement element) {
		if (element == null)
			throw new NullPointerException();

		try {
			final XmlSchemaType type = element.getSchemaType();
			if (type == null)
				return false;

			final String name = type.getName();
			if (name == null)
				return false;

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the schema element <code>element</code> has a schema supported
	 * type name.
	 * 
	 * @param element
	 *            The element. Not <code>null</code>.
	 * @return <code>true</code> if <code>element</code> has a supported schema
	 *         type name; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>element==null</code>.
	 */
	public static boolean hasSupportedSchemaTypeName(
			final XmlSchemaElement element) {

		if (element == null)
			throw new NullPointerException();

		try {
			if (!hasSchemaTypeName(element))
				return false;

			return isSupportedSchemaTypeName(getSchemaTypeNameOf(element));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the schema type name <code>schemaTypeName</code> is supported
	 * by this class.
	 * 
	 * @param schemaTypeName
	 *            The (nonqualified) schema type name . Not <code>null</code>.
	 * @return <code>true</code> if <code>schemaTypeName</code> is supported by
	 *         this class; <code>false</code> otherwise.
	 */
	public static boolean isSupportedSchemaTypeName(final String schemaTypeName) {
		if (schemaTypeName == null)
			throw new NullPointerException();

		return schemaTypeNamesToTypes.containsKey(schemaTypeName);
	}

	/**
	 * Parses the schema type name <code>schemaTypeName</code> to a type.
	 * 
	 * @param schemaTypeName
	 *            The name. Not <code>null</code> or empty.
	 * @throws IllegalArgumentException
	 *             If <code>!isSupportedSchemaTypeName(string)</code> .
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 * 
	 * @see #isSupportedSchemaTypeName(String)
	 */
	public static WSDLType parseToType(final String schemaTypeName) {
		if (schemaTypeName == null)
			throw new NullPointerException();
		if (!isSupportedSchemaTypeName(schemaTypeName))
			throw new IllegalArgumentException();

		return schemaTypeNamesToTypes.get(schemaTypeName);
	}

	//
	// STATIC - METHODS - PRIVATE
	//

	/**
	 * Casts the object array of bytes <code>bytes</code> to a primitive array
	 * of bytes.
	 * 
	 * @param bytes
	 *            The object array. Not <code>null</code>.
	 * @return A primitive array. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>bytes==null</code>, or if
	 *             <code>bytes[i]==null</code> for some <code>i</code>.
	 */
	private static byte[] cast(final Byte[] bytes) {
		if (bytes == null)
			throw new NullPointerException();
		for (final Byte b : bytes)
			if (b == null)
				throw new NullPointerException();

		final byte[] array = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++)
			array[i] = bytes[i];

		return array;
	}

	// /**
	// * Gets the default Gregorian calendar for the type with the qualified
	// name
	// * <code>qType</code>.
	// *
	// * @param qType
	// * One of the following: {@link DatatypeConstants#DATETIME},
	// * {@link DatatypeConstants#TIME}, {@link DatatypeConstants#DATE}
	// * , {@link DatatypeConstants#GYEARMONTH},
	// * {@link DatatypeConstants#GYEAR},
	// * {@link DatatypeConstants#GMONTHDAY},
	// * {@link DatatypeConstants#GDAY}, or
	// * {@link DatatypeConstants#GMONTH}.
	// * @return A calendar. Never <code>null</code>.
	// * @throws NullPointerException
	// * if <code>qType==null</code>.
	// * @throws IllegalArgumentException
	// * If <code>qType!=DatatypeConstants.DATETIME</code> and
	// * <code>qType!=DatatypeConstants.TIME</code> and
	// * <code>qType!=DatatypeConstants.DATE</code> and
	// * <code>qType!=DatatypeConstants.GYEARMONTH</code> and
	// * <code>qType!=DatatypeConstants.GYEAR</code> and
	// * <code>qType!=DatatypeConstants.GMONTHDAY</code> and
	// * <code>qType!=DatatypeConstants.GDAY</code> and
	// * <code>qType!=DatatypeConstants.GMONTH</code>.
	// */
	// private static XMLGregorianCalendar getDefaultXMLGregorianCalendarFor(
	// final QName qType) {
	//
	// if (qType == null)
	// throw new NullPointerException();
	//
	// final DatatypeFactory factory;
	// try {
	// factory = DatatypeFactory.newInstance();
	// } catch (DatatypeConfigurationException e) {
	// throw new RuntimeException();
	// }
	//
	// if (qType == DatatypeConstants.DATETIME)
	// return factory.newXMLGregorianCalendar(0, 1, 1, 0, 0, 0, 0, 0);
	// else if (qType == DatatypeConstants.TIME)
	// return factory.newXMLGregorianCalendarTime(0, 0, 0, 0);
	// else if (qType == DatatypeConstants.DATE)
	// return factory.newXMLGregorianCalendarDate(0, 1, 1, 0);
	// else if (qType == DatatypeConstants.GYEARMONTH)
	// return factory.newXMLGregorianCalendarDate(0, 1,
	// DatatypeConstants.FIELD_UNDEFINED, 0);
	// else if (qType == DatatypeConstants.GYEAR)
	// return factory.newXMLGregorianCalendarDate(0,
	// DatatypeConstants.FIELD_UNDEFINED,
	// DatatypeConstants.FIELD_UNDEFINED, 0);
	// else if (qType == DatatypeConstants.GMONTHDAY)
	// return factory.newXMLGregorianCalendarDate(
	// DatatypeConstants.FIELD_UNDEFINED, 1, 1, 0);
	// else if (qType == DatatypeConstants.GDAY)
	// return factory.newXMLGregorianCalendarDate(
	// DatatypeConstants.FIELD_UNDEFINED,
	// DatatypeConstants.FIELD_UNDEFINED, 1, 0);
	// else if (qType == DatatypeConstants.GMONTH)
	// return factory.newXMLGregorianCalendarDate(
	// DatatypeConstants.FIELD_UNDEFINED, 1,
	// DatatypeConstants.FIELD_UNDEFINED, 0);
	// else
	// throw new IllegalArgumentException();
	// }
}
