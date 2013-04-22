package org.ect.codegen.v2.core.descr.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.ect.codegen.v2.core.NamedObjectFactory;

public class JavaIdentifierFactory extends
		NamedObjectFactory<JavaIdentifierFactory.JavaIdentifier> {

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean canConstruct(final String objectName) {
		return true;
	}

	/**
	 * Constructs a Java identifier based on the name <code>objectName</code>:
	 * this method (<em>i</em>) first converts <code>objectName</code> to a
	 * valid Java identifier and (<em>ii</em>) subsequently makes the resulting
	 * Java identifier unique by postfixing it with a number (if necessary).
	 * 
	 * <p>
	 * <em>Inherited documentation:</em>
	 * </p>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected JavaIdentifier newNamedObject(final String objectName) {

		/* Validate arguments. */
		if (objectName == null)
			throw new NullPointerException();
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

		/* Get a fresh identifier name based on $objectName. */
		final String baseIdentifierName = computeJavaIdentifierNameFor(objectName);
		String tentativeIdentifierName = baseIdentifierName;
		int i = 0;
		while (super.hasConstructed(tentativeIdentifierName))
			tentativeIdentifierName = baseIdentifierName + "$" + ++i;

		/* Construct. */
		return new JavaIdentifier(tentativeIdentifierName);
	}

	//
	// CLASSES
	//

	public class JavaIdentifier extends
			NamedObjectFactory<JavaIdentifier>.NamedObject {

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a Java identifier named <code>name</code>.
		 * 
		 * @param name
		 *            The name. Not <code>null</code> or empty.
		 * @throws IllegalArgumentException
		 *             If <code>name.isEmpty()</code> or
		 *             <code>!JavaIdentifierFactory.isValidJavaIdentifier(name)</code>
		 *             .
		 * @throws NullPointerException
		 *             If <code>name==null</code>.
		 * 
		 * @see JavaIdentifierFactory#isJavaIdentifierName(String)
		 * @see String#isEmpty()
		 */
		JavaIdentifier(final String name) {
			super(name);
			if (!JavaIdentifierFactory.isJavaIdentifierName(name))
				throw new IllegalArgumentException(name);
		}
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * A set of Java keywords.
	 */
	private static final Set<String> keywords = new HashSet<String>(
			Arrays.asList(new String[] { "abstract", "assert", "boolean",
					"break", "byte", "case", "catch", "char", "class", "const",
					"continue", "default", "do", "double", "else", "enum",
					"extends", "final", "finally", "float", "for", "if",
					"goto", "implements", "import", "instanceof", "int",
					"interface", "long", "native", "new", "package", "private",
					"protected", "public", "return", "short", "static",
					"strictfp", "super", "switch", "synchronized", "this",
					"throw", "throws", "transient", "try", "void", "volatile",
					"while", "true", "false", "null" }));

	//
	// STATIC - METHODS
	//

	/**
	 * Checks if the string <code>string</code> is a Java keyword.
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @return <code>true</code> if <code>string</code> is a Java keyword;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 */
	public static boolean isJavaKeyword(final String string) {
		if (string == null)
			throw new NullPointerException("string");

		return keywords.contains(string);
	}

	/**
	 * Checks if the string <code>string</code> is a Java identifier name.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * 
	 * <p>
	 * <center><code>string.equals(computeJavaIdentifierNameFor(string))</code>
	 * </center>
	 * </p>
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @return <code>true</code> if <code>string</code> is a valid Java
	 *         identifier; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 * 
	 * @see #computeJavaIdentifierNameFor(String)
	 * @see String#equals(Object)
	 */
	public static boolean isJavaIdentifierName(final String string) {
		if (string == null)
			throw new NullPointerException();

		return string.equals(computeJavaIdentifierNameFor(string));
	}

	/**
	 * Computes a Java identifier name for the string <code>string</code>.
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 */
	public static String computeJavaIdentifierNameFor(final String string) {

		/* Validate arguments. */
		if (string == null)
			throw new NullPointerException("string");

		/* Construct a string builder. */
		final StringBuilder builder = new StringBuilder(string.length());

		/* Append the first character. */
		char ch = string.charAt(0);
		builder.append(Character.isJavaIdentifierStart(ch) ? ch : "_");

		/* Append the remaining characters. */
		for (int i = 1; i < string.length(); i++) {
			ch = string.charAt(i);
			builder.append(Character.isJavaIdentifierPart(ch) ? ch : "_");
		}

		/* Return. */
		final String identifier = builder.toString();
		return (isJavaKeyword(identifier) ? "$" : "") + identifier;
	}
}
