package org.ect.codegen.v2.core.gen;

import java.io.PrintStream;

public class Printer {

	//
	// CONSTRUCTORS
	//

	/**
	 * @deprecated Use this class in a static way.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private Printer() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Prints the object <code>object</code> to the stream <code>stream</code>
	 * with an offset of length <code>2*offset</code>.
	 * 
	 * @param object
	 *            The object. Not <code>null</code>.
	 * @param offset
	 *            The nonnegative offset.
	 * @param stream
	 *            The stream. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>offset&lt;0</code>.
	 * @throws NullPointerException
	 *             If <code>object==null</code> or <code>stream==null</code>.
	 */
	public static void print(final Object object, final int offset,
			final PrintStream stream) {

		/* Validate arguments. */
		if (object == null)
			throw new NullPointerException("object");
		if (stream == null)
			throw new NullPointerException("stream");
		if (offset < 0)
			throw new IllegalArgumentException(Integer.toString(offset));

		/* Print. */
		final StringBuilder whitespaceBuilder = new StringBuilder();
		for (int i = 0; i < offset; i++)
			whitespaceBuilder.append("  ");

		final String whitespace = whitespaceBuilder.toString();

		final String string = object.toString();
		final String[] lines = string.split("\\n");
		for (final String s : lines)
			stream.print(whitespace + s);
	}

	/**
	 * Prints the object <code>object</code>.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * 
	 * <p>
	 * <center><code>println(object,0,System.out)</code></center>
	 * </p>
	 * 
	 * @param object
	 *            The object. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 * 
	 * @see #println(Printable, int, PrintStream)
	 * @see System#out
	 */
	public static void println(final Object object) {
		println(object, 0, System.out);
	}

	/**
	 * Invokes: <code>println(object,offset,System.out)</code>.
	 * 
	 * @see #println(Printable, int, PrintStream)
	 * @see System#out
	 */
	public static void println(final Object object, final int offset) {
		println(object, offset, System.out);
	}

	/**
	 * Invokes: <code>println(object,0,stream)</code>.
	 * 
	 * @see #println(Printable, int, PrintStream)
	 */
	public static void println(final Object object, final PrintStream stream) {
		println(object, 0, stream);
	}

	/**
	 * Prints the object <code>object</code> to the stream <code>stream</code>
	 * with an offset of length <code>2*offset</code>.
	 * 
	 * @param object
	 *            The object. Not <code>null</code>.
	 * @param offset
	 *            The nonnegative offset.
	 * @param stream
	 *            The stream. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>offset&lt;0</code>.
	 * @throws NullPointerException
	 *             If <code>object==null</code> or <code>stream==null</code>.
	 */
	public static void println(final Object object, final int offset,
			final PrintStream stream) {

		/* Validate arguments. */
		if (object == null)
			throw new NullPointerException("object");
		if (stream == null)
			throw new NullPointerException("stream");
		if (offset < 0)
			throw new IllegalArgumentException(Integer.toString(offset));

		/* Print. */
		final StringBuilder whitespaceBuilder = new StringBuilder();
		for (int i = 0; i < offset; i++)
			whitespaceBuilder.append("  ");

		final String whitespace = whitespaceBuilder.toString();

		final String string = object.toString();
		final String[] lines = string.split("\\n");
		for (final String s : lines)
			stream.println(whitespace + s);
	}

	/**
	 * Invokes: <code>println(throwable,0,System.err,printStackTrace)</code>.
	 * 
	 * @see #println(Throwable, boolean)
	 * @see System#out
	 */
	public static void println(final Throwable throwable,
			final boolean printStackTrace) {

		println(throwable, 0, System.err, printStackTrace);
	}

	/**
	 * Invokes:
	 * <code>println(throwable,offset,System.err,printStackTrace)</code>.
	 * 
	 * @see #println(Throwable, int, PrintStream, boolean)
	 * @see System#out
	 */
	public static void println(final Throwable throwable, final int offset,
			final boolean printStackTrace) {

		println(throwable, offset, System.err, printStackTrace);
	}

	/**
	 * Invokes: <code>println(throwable,0,stream,printStackTrace)</code>.
	 * 
	 * @see #println(Throwable, int, PrintStream, boolean)
	 */
	public static void println(final Throwable throwable,
			final PrintStream stream, final boolean printStackTrace) {

		println(throwable, 0, stream, printStackTrace);
	}

	/**
	 * Prints the throwable <code>throwable</code> to the stream
	 * <code>stream</code> with an offset of length <code>2*offset</code>.
	 * 
	 * @param throwable
	 *            The throwable; <code>null</code> for an undocumented
	 *            throwable.
	 * @param offset
	 *            The nonnegative offset.
	 * @param stream
	 *            The stream. Not <code>null</code>.
	 * @param printStackTrace
	 *            Flag indicating if this method should print also a stack
	 *            trace.
	 * @throws IllegalArgumentException
	 *             If <code>offset&lt;0</code>.
	 * @throws NullPointerException
	 *             If <code>stream==null</code>.
	 */
	public static void println(Throwable throwable, final int offset,
			final PrintStream stream, final boolean printStackTrace) {

		/* Validate arguments. */
		if (stream == null)
			throw new NullPointerException("null");
		if (offset < 0)
			throw new IllegalArgumentException(Integer.toString(offset));

		/* Print. */
		if (throwable == null)
			throwable = new Exception();

		println("", offset, stream);
		print("[ERROR] ", offset, stream);

		/* Print throwable. */
		Throwable undocumented = null;
		Throwable parent = throwable;
		while (throwable != null) {
			String message = throwable.getMessage();
			if (message == null || message.isEmpty()) {
				message = "[UNDOCUMENTED] An undocumented error occurred.";
				undocumented = throwable;
			}

			println(message.replaceAll("\\n", "\n    "), offset, stream);

			parent = throwable;
			throwable = throwable.getCause();
			if (throwable != null) {
				println("", offset, stream);
				print(" -> ", offset, stream);
			}
		}

		/* Print stack trace. */
		if (printStackTrace) {
			println("\nStack trace:\n", offset, stream);
			parent.printStackTrace(stream);
		}

		if (undocumented != null) {
			System.err.println();
			undocumented.printStackTrace();
		}
	}
}
