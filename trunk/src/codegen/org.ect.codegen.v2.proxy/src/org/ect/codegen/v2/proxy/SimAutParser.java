package org.ect.codegen.v2.proxy;

import static org.ect.codegen.v2.proxy.rt.java.Constants.BUTTON_INPUT_VERTEX_NAME;
import static org.ect.codegen.v2.proxy.rt.java.Constants.BUTTON_OUTPUT_VERTEX_NAME;
import static org.ect.codegen.v2.proxy.rt.java.Constants.EXCEPTION_VERTEX_NAME;
import static org.ect.codegen.v2.proxy.rt.java.Constants.INTERMEDIATE_STATE_NAME_PREFIX;
import static org.ect.codegen.v2.proxy.rt.java.Constants.PROXY_VERTEX_NAME_PREFIX;
import static org.ect.codegen.v2.proxy.rt.java.Constants.SIM_AUT_CLASS_NAME_PREFIX;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.proxy.rt.java.Constants;


public class SimAutParser {

	/**
	 * The number of the line most recently read.
	 */
	private int lineNumber = 0;

	/**
	 * The resource containing the simulation automaton specification(s) to
	 * parse.
	 */
	private final SimAutResource resource;

	/**
	 * A reader for the simulation automata specifications in the resource
	 * {@link #resource}.
	 */
	private final BufferedReader reader;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a parser for the simulation automaton specification(s) in the
	 * resource <code>resource</code>.
	 * 
	 * @param resource
	 *            The resource. Not <code>null</code> or empty.
	 * @throws NullPointerException
	 *             If <code>resource==null</code>.
	 */
	private SimAutParser(final SimAutResource resource) {
		if (resource == null)
			throw new NullPointerException();

		this.resource = resource;
		this.reader = new BufferedReader(new InputStreamReader(
				resource.newInputStream()));
	}

	//
	// METHODS
	//

	/**
	 * Gets the number of the line most recently read.
	 * 
	 * @return A positive number.
	 */
	public final int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Reads the next non-empty line.
	 * 
	 * @return A non-empty string; <code>null</code> if this parser has no more
	 *         lines to parse.
	 * @throws SimAutParserException
	 *             If something goes wrong while reading.
	 */
	public final String readNonemptyLine() throws SimAutParserException {
		try {
			String line;
			while (true) {
				lineNumber++;
				line = reader.readLine();
				line = line == null ? null : line.trim();
				if (line == null || !line.isEmpty())
					break;
			}

			return line;
		} catch (final Exception e) {
			throw new SimAutParserException(
					SimAutParserException.READ_NONEMPTY_LINE(this), e);
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "("
				+ resource.getCroppedResourceText() + ")";
	}

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Checks if the string <code>string</code> is a
	 * <em>genuine vertex name</em>. In that case, it is the name of a vertex
	 * not added for control purposes.
	 * 
	 * @param string
	 *            The name. Not <code>null</code> or empty.
	 * @return <code>true</code> if <code>string</code> is a genuine vertex
	 *         name; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public static boolean checkIsGenuineVertexName(final String string) {
		if (string == null)
			throw new NullPointerException();

		if (string.isEmpty() || string.startsWith(PROXY_VERTEX_NAME_PREFIX)
				|| string.equals(BUTTON_INPUT_VERTEX_NAME)
				|| string.equals(BUTTON_OUTPUT_VERTEX_NAME)
				|| string.equals(EXCEPTION_VERTEX_NAME))
			return false;

		return true;
	}

	/**
	 * Parses a number of lines, collectively specifying a simulation automaton,
	 * to the connector representing that simulation automaton.
	 * 
	 * @param offset
	 *            A positive offset for referring to line numbers.
	 * @param line1
	 *            The name of the simulation automaton specification. Not
	 *            <code>null</code> or empty.
	 * @param line2
	 *            The space-separated names of the input vertices. Not
	 *            <code>null</code>.
	 * @param line3
	 *            The space-separated names of the output vertices. Not
	 *            <code>null</code>.
	 * @param line4
	 *            The initial state. Not <code>null</code> or empty.
	 * @param line5AndFurther
	 *            The transitions. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>offset&lt;0</code> or <code>line1.isEmpty()</code>
	 *             or <code>line4.isEmpty()</code> or
	 *             <code>line5AndFurther[i].isEmpty()</code> for some
	 *             <code>i</code>.
	 * @throws NullPointerException
	 *             If <code>line1==null</code> or <code>line2==null</code> or
	 *             <code>line3==null</code> or <code>line4==null</code> or
	 *             <code>line5AndFurther==null</code>.
	 * @throws SimAutParserException
	 *             If something goes wrong while parsing.
	 * 
	 * @see String#isEmpty()
	 */
	public static Connector<DefaultConstraintAutomaton> parse(final int offset,
			final String line1, final String line2, final String line3,
			final String line4, final String... line5AndFurther)
			throws SimAutParserException {

		if (line1 == null || line2 == null || line3 == null || line4 == null
				|| line5AndFurther == null)
			throw new NullPointerException();
		if (offset < 0 || line1.isEmpty() || line4.isEmpty())
			throw new IllegalArgumentException();
		for (final String s : line5AndFurther)
			if (s.isEmpty())
				throw new IllegalArgumentException();

		try {
			/* Construct an empty structure and an empty automaton. */
			final String connectorName = SIM_AUT_CLASS_NAME_PREFIX
					+ StringUtils.capitalize(line1);

			final DefaultConstraintAutomaton automaton = new DefaultConstraintAutomaton();
			final Connector<DefaultConstraintAutomaton> connector = Connector
					.newInstance(connectorName, automaton);

			/* Add button vertices to $structure. */
			final Set<String> proxyVertexNames = new HashSet<String>();
			proxyVertexNames.add(BUTTON_INPUT_VERTEX_NAME);
			proxyVertexNames.add(BUTTON_OUTPUT_VERTEX_NAME);
			connector.addVertex(BUTTON_INPUT_VERTEX_NAME);
			connector.addVertex(BUTTON_OUTPUT_VERTEX_NAME);
			connector.addEdge(BUTTON_INPUT_VERTEX_NAME,
					BUTTON_OUTPUT_VERTEX_NAME);

			/* Add input vertices to $structure. */
			final Set<String> inputVertexNames = new HashSet<String>();
			if (!line2.isEmpty()) {
				final String[] inputVertices = line2.split(" ");
				for (final String s : inputVertices) {
					final String from = StringUtils.capitalize(s);
					final String to = PROXY_VERTEX_NAME_PREFIX + from;
					validateAsVertexName(from);
					inputVertexNames.add(from);
					proxyVertexNames.add(to);
					connector.addVertex(from);
					connector.addVertex(to);
					connector.addEdge(from, to);
				}
			}

			/* Add output vertices to $structure. */
			final Set<String> outputVertexNames = new HashSet<String>();
			if (!line3.isEmpty()) {
				final String[] outputVertices = line3.split(" ");
				for (final String s : outputVertices) {
					final String to = StringUtils.capitalize(s);
					final String from = PROXY_VERTEX_NAME_PREFIX + to;
					validateAsVertexName(to);
					if (inputVertexNames.contains(to))
						throw new SimAutParserException(
								SimAutParserException.PARSE(SimAutParser.class,
										offset, line1, line2, line3, line4,
										line5AndFurther),
								"The vertex named \""
										+ to
										+ "\" is both an input and an output vertex.");

					outputVertexNames.add(to);
					proxyVertexNames.add(from);
					connector.addVertex(to);
					connector.addVertex(from);
					connector.addEdge(from, to);
				}
			}

			/* Add an initial state to $automaton. */
			final String initialState = line4.toUpperCase();
			automaton.addState(initialState, true);

			/* Add transitions to $automaton. */
			for (int i = 0; i < line5AndFurther.length; i++) {
				final String transition = line5AndFurther[i];
				final int lineNumber = offset + i;

				final String[] tokens = transition.split(" ");

				/* Perform a sanity check. */
				if (tokens.length < 3)
					throw new SimAutParserException(
							SimAutParserException.PARSE(SimAutParser.class,
									offset, line1, line2, line3, line4,
									line5AndFurther),
							"A transition specification must have a source, a target, and at least one vertex name (at "
									+ lineNumber + ").");
				else {
					/* Parse line. */
					final String sourceName = tokens[0].toUpperCase();
					final String targetName = tokens[tokens.length - 1]
							.toUpperCase();
					final String preTargetName = INTERMEDIATE_STATE_NAME_PREFIX
							+ targetName;

					validateAsStateName(sourceName);
					validateAsStateName(targetName);

					final String[] observableVertexNames = new String[tokens.length - 2];
					final String[] vertexNames = new String[observableVertexNames.length * 2];
					for (int j = 0; j < observableVertexNames.length; j++) {
						final String vertexName = StringUtils
								.capitalize(tokens[j + 1]);

						if (!automaton.hasVertex(vertexName))
							throw new SimAutParserException(
									SimAutParserException.PARSE(
											SimAutParser.class, offset, line1,
											line2, line3, line4,
											line5AndFurther),
									"The vertex named \""
											+ vertexName
											+ "\" is not declared as an input or output vertex (at line "
											+ lineNumber + ")");

						observableVertexNames[j] = vertexName;
						vertexNames[2 * j] = vertexName;
						vertexNames[2 * j + 1] = PROXY_VERTEX_NAME_PREFIX
								+ vertexName;
					}

					/* Add states. */
					if (!automaton.hasState(sourceName))
						automaton.addState(sourceName, false);

					if (!automaton.hasState(targetName))
						automaton.addState(targetName, false);

					if (!automaton.hasState(preTargetName))
						automaton.addState(preTargetName, false);

					/* Compose constraint text. */
					final StringBuilder builder = new StringBuilder();
					for (final String s : observableVertexNames)
						builder.append("$").append(s).append(" == $")
								.append(PROXY_VERTEX_NAME_PREFIX).append(s)
								.append(" && ");
					final String constraintText = builder.substring(0,
							builder.length() - 4);

					/* Add transitions. */
					automaton.addOrGetTransition(sourceName, preTargetName,
							Arrays.asList(vertexNames), constraintText);
					automaton
							.addOrGetTransition(
									preTargetName,
									targetName,
									Arrays.asList(new String[] { BUTTON_INPUT_VERTEX_NAME }),
									"true");
				}
			}

			/* Return. */			
			return connector;

		} catch (final Exception e) {
			if (e instanceof SimAutParserException)
				throw (SimAutParserException) e;

			throw new SimAutParserException(SimAutParserException.PARSE(
					SimAutParser.class, offset, line1, line2, line3, line4,
					line5AndFurther), e);
		}
	}

	/**
	 * Parses the simulation automaton specification named
	 * <code>simAutName</code> in the resource <code>simAutResource</code> to a
	 * connector named " {@link Constants#SIM_AUT_CLASS_NAME_PREFIX}
	 * <code>&lt;name&gt;</code>" representing that simulation automaton.
	 * 
	 * @param simAutName
	 *            The name of the specification. Not <code>null</code>.
	 * @param simAutResource
	 *            The resource. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws SimAutParserException
	 *             If something goes wrong before or while parsing.
	 * @throws NullPointerException
	 *             If <code>simAutName==null</code> or
	 *             <code>simAutResource==null</code>.
	 * 
	 * @see Constants#SIM_AUT_CLASS_NAME_PREFIX
	 */
	public static Connector<DefaultConstraintAutomaton> parse(
			final String simAutName, final SimAutResource simAutResource)
			throws SimAutParserException {

		if (simAutName == null || simAutResource == null)
			throw new NullPointerException();

		try {
			/* Construct a parser. */
			final SimAutParser parser = new SimAutParser(simAutResource);

			/* Declare auxiliary variables. */
			int offset = 0;
			String inputVertexNames = null;
			String outputVertexNames = null;
			String initialState = null;
			final List<String> transitions = new ArrayList<String>();

			/* Parse lines. */
			boolean isParsing = false;
			String line;
			while ((line = parser.readNonemptyLine()) != null) {

				/* Continue parsing. */
				if (isParsing) {
					offset = offset == 0 ? parser.getLineNumber() : offset;
					if (line.equals("end")) {
						final String[] array = new String[transitions.size()];
						transitions.toArray(array);
						return parse(offset, simAutName, inputVertexNames,
								outputVertexNames, initialState, array);
					} else
						transitions.add(line);
				}

				/* Start parsing. */
				else if (line.startsWith("begin")) {
					String[] tokens = line.split(" ");

					if (tokens.length == 2) {
						if (tokens[1].equals(simAutName)) {
							isParsing = true;

							/* Get the input vertices. */
							line = parser.readNonemptyLine();
							if (line == null
									|| (tokens = line.split(" ")).length < 1
									|| !tokens[0].equals("input:"))
								throw new SimAutParserException(
										SimAutParserException.PARSE(
												SimAutParser.class, simAutName,
												simAutResource),
										"Expected a list of input vertex names, formatted as \"input: <name1> <name2> ...\" (at line "
												+ parser.getLineNumber() + ").");

							inputVertexNames = line.substring(6).trim();

							/* Get the output vertices. */
							line = parser.readNonemptyLine();
							if (line == null
									|| (tokens = line.split(" ")).length < 1
									|| !tokens[0].equals("output:"))
								throw new SimAutParserException(
										SimAutParserException.PARSE(
												SimAutParser.class, simAutName,
												simAutResource),
										"Expected a list of output vertex names, formatted as \"output: <name1> <name2> ...\" (at line "
												+ parser.getLineNumber() + ").");

							outputVertexNames = line.substring(7).trim();

							/* Get an initial state. */
							line = parser.readNonemptyLine();
							if (line == null
									|| (tokens = line.split(" ")).length < 2
									|| !tokens[0].equals("init:"))
								throw new SimAutParserException(
										SimAutParserException.PARSE(
												SimAutParser.class, simAutName,
												simAutResource),
										"Expected an initial state, formatted as \"init: <name>\" (at line "
												+ parser.getLineNumber() + ").");

							initialState = tokens[1];
						}
					} else
						throw new SimAutParserException(
								SimAutParserException.PARSE(SimAutParser.class,
										simAutName, simAutResource),
								"Expected a name (at line "
										+ parser.getLineNumber() + ").");
				}
			}

			/* Throw if this method has not returned at this point. */
			throw new SimAutParserException(SimAutParserException.PARSE(
					SimAutParser.class, simAutName, simAutResource),
					"The simulation automaton resource \""
							+ simAutResource.getCroppedResourceText()
							+ "\" does not contain a specification named \""
							+ simAutName + "\".");

		} catch (final Exception e) {
			if (e instanceof SimAutParserException)
				throw (SimAutParserException) e;

			throw new SimAutParserException(SimAutParserException.PARSE(
					SimAutParser.class, simAutName, simAutResource), e);
		}
	}

	/**
	 * Validates the string <code>string</code> as a state name. If validation
	 * succeeds, <code>string</code> is not empty and does not start with
	 * {@link org.ect.codegen.v2.proxy.rt.java.Constants#STATE_NAME_PREFIX}.
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>stateName==null</code>.
	 * @throws SimAutParserException
	 *             If something goes wrong while validating.
	 */
	public static void validateAsStateName(final String string)
			throws SimAutParserException {

		if (string == null)
			throw new NullPointerException();

		if (string.isEmpty())
			throw new SimAutParserException(
					SimAutParserException.VALIDATE_AS_STATE_NAME(
							SimAutParser.class, string),
					"State names cannot be empty.");

		if (string.startsWith(INTERMEDIATE_STATE_NAME_PREFIX))
			throw new SimAutParserException(
					SimAutParserException.VALIDATE_AS_STATE_NAME(
							SimAutParser.class, string),
					"State names cannot start with \""
							+ INTERMEDIATE_STATE_NAME_PREFIX + "\".");
	}

	/**
	 * Validates the string <code>string</code> as a vertex name. If validation
	 * succeeds, <code>string</code>:
	 * <ul>
	 * <li>is not empty;
	 * <li>does not start with {@link Constants#PROXY_VERTEX_NAME_PREFIX}; and
	 * <li>does not equal {@link Constants#BUTTON_INPUT_VERTEX_NAME} or
	 * {@link Constants#BUTTON_OUTPUT_VERTEX_NAME} or
	 * {@link Constants#EXCEPTION_VERTEX_NAME}.
	 * </ul>
	 * 
	 * @param string
	 *            The string. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 * @throws SimAutParserException
	 *             If something goes wrong while validating.
	 */
	public static void validateAsVertexName(final String string)
			throws SimAutParserException {

		if (string == null)
			throw new NullPointerException();

		if (string.isEmpty())
			throw new SimAutParserException(
					SimAutParserException.VALIDATE_AS_STATE_NAME(
							SimAutParser.class, string),
					"Vertex names cannot be empty.");

		if (string.startsWith(PROXY_VERTEX_NAME_PREFIX))
			throw new SimAutParserException(
					SimAutParserException.VALIDATE_AS_STATE_NAME(
							SimAutParser.class, string),
					"Vertex names cannot start with \""
							+ PROXY_VERTEX_NAME_PREFIX + "\".");

		if (string.equals(BUTTON_INPUT_VERTEX_NAME)
				|| string.equals(BUTTON_OUTPUT_VERTEX_NAME)
				|| string.equals(EXCEPTION_VERTEX_NAME))
			throw new SimAutParserException(
					SimAutParserException.VALIDATE_AS_STATE_NAME(
							SimAutParser.class, string), "The vertex names \""
							+ BUTTON_INPUT_VERTEX_NAME + "\", \""
							+ BUTTON_OUTPUT_VERTEX_NAME + "\", and \""
							+ EXCEPTION_VERTEX_NAME
							+ "\" have a distinguished status.");
	}

	//
	// EXCEPTIONS
	//

	public static class SimAutParserException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected SimAutParserException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		protected SimAutParserException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String PARSE(final Class<?> clazz, final int offset,
				final String line1, final String line2, final String line3,
				final String line4, final String... line5AndFurther) {

			return "The class \""
					+ clazz.getCanonicalName()
					+ "\" failed to parse the simulation automaton specification named \""
					+ line1 + "\".";
		}

		protected static String PARSE(final Class<?> clazz, final String name,
				final SimAutResource resource) {

			return "The class \""
					+ clazz.getCanonicalName()
					+ "\" failed to parse the simulation automaton specification named \""
					+ name + "\" from the resource \""
					+ resource.getCroppedResourceText() + "\".";
		}

		protected static String READ_NONEMPTY_LINE(final SimAutParser thiz) {
			return "The simulation automaton parser \"" + thiz
					+ "\" failed to read the next nonempty line.";
		}

		protected static String VALIDATE_AS_STATE_NAME(final Class<?> clazz,
				final String string) {

			return "The class \"" + clazz.getCanonicalName()
					+ "\" failed to validate the string \"" + string
					+ "\" as a vertex name.";
		}

		protected static String VALIDATE_AS_VERTEX_NAME(final Class<?> clazz,
				final String string) {

			return "The class \"" + clazz.getCanonicalName()
					+ "\" failed to validate the string \"" + string
					+ "\" as a vertex name.";
		}
	}
}
