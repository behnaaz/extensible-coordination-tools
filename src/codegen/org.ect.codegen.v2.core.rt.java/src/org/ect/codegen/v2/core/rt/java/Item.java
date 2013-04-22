package org.ect.codegen.v2.core.rt.java;

import java.util.ArrayList;
import java.util.List;

public class Item {

	//
	// CONSTRUCTORS
	//

	/**
	 * Deprecates the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 * 
	 * @deprecated Use this class in a static way.
	 */
	@Deprecated
	private Item() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Checks if this class can convert the string <code>string</code> to an
	 * object.
	 * 
	 * <p>
	 * TODO: Document exactly which strings can be converted.
	 * </p>
	 * 
	 * @return <code>true</code> if this class can convert <code>string</code>
	 *         to an object; <code>false</code> otherwise.
	 */
	public static boolean canConvertToObject(final String string) {
		if (string == null)
			throw new NullPointerException();

		try {
			convertToObject(string);
			return true;
		} catch (final IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Converts the item <code>item</code> to a string.
	 * 
	 * @param item
	 *            The item. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>item==null</code>.
	 */
	public static String convertToString(final Object item) {
		if (item == null)
			new NullPointerException();

		if (item instanceof Object[]) {
			final StringBuilder builder = new StringBuilder();
			builder.append("[");
			for (final Object o : ((Object[]) item))
				builder.append(convertToString(o)).append(", ");

			if (builder.length() > 1)
				builder.setLength(builder.length() - 2);

			builder.append("]");
			return builder.toString();
		} else
			return (item instanceof String ? "\"" : "") + item.toString()
					+ (item instanceof String ? "\"" : "") + ":"
					+ item.getClass().getSimpleName();
	}

	/**
	 * Converts the string <code>string</code> to an object.
	 * 
	 * <p>
	 * TODO: Extend / cleanup this method (e.g., use curly bracket notation for
	 * arrays, add a "Java interpreter").
	 * </p>
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @return An object.
	 * @throws IllegalArgumentException
	 *             If <code>!canConvertToObject()</code>.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 */
	public static Object convertToObject(final String string) {
		if (string == null)
			throw new NullPointerException();

		if (string.equals("null"))
			return null;

		final String[] tokens = string.split(" ");
		final List<Object> list = new ArrayList<Object>();

		final int nTokens = tokens.length;
		final boolean isArray = tokens[0].startsWith("[")
				&& tokens[tokens.length - 1].endsWith("]");

		if (isArray) {
			tokens[0] = tokens[0].substring(1);
			tokens[nTokens - 1] = tokens[nTokens - 1].substring(0,
					tokens[nTokens - 1].length() - 1);
		}

		final StringBuilder builder = new StringBuilder();
		for (final String s : tokens) {
			if (s.isEmpty())
				continue;

			/* Start building a string if $s starts with a double quote. */
			if (builder.length() == 0 && s.startsWith("\""))
				if (s.endsWith("\"")) {
					list.add(s.substring(1, s.length() - 1));
				} else
					builder.append(s.substring(1));

			/* Parse $s and add it to the list otherwise. */
			else if (builder.length() == 0 && !s.startsWith("\"")) {

				/* Attempt to parse $itemText as an integer. */
				try {
					list.add(Integer.parseInt(s));
					if (isArray)
						continue;
					else
						break;
				} catch (final NumberFormatException e) {
				}

				/* Attempt to parse $itemText as a float. */
				try {
					list.add(Float.parseFloat(s));
					if (isArray)
						continue;
					else
						break;
				} catch (final NumberFormatException e) {
				}

				/* Attempt to parse $itemText as a boolean. */
				if (s.length() == 4 && s.toLowerCase().equals("true")) {
					list.add(true);
					if (isArray)
						continue;
					else
						break;
				}
				if (s.length() == 5 && s.toLowerCase().equals("false")) {
					list.add(false);
					if (isArray)
						continue;
					else
						break;
				}

				/* Attempt to parse $itemText as null. */
				if (s.length() == 4 && s.equals("null")) {
					list.add(null);
					if (isArray)
						continue;
					else
						break;
				}

				throw new IllegalArgumentException();
			}

			/* Stop building a string if $s ends with a double quote. */
			else if (s.endsWith("\"")) {
				builder.append(" " + s.substring(0, s.length() - 1));
				list.add(builder.toString());
				builder.setLength(0);
			}

			/* Continue building a string. */
			else
				builder.append(" " + s);
		}

		if (builder.length() > 0)
			throw new IllegalArgumentException();

		if (!list.isEmpty() && !isArray)
			return list.get(0);
		else
			return list.toArray();
	}
}
