package org.ect.codegen.v2.core.rt.java;

import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.ect.codegen.v2.core.rt.java.internal.Continuation;


public class Console implements Runnable {

	//
	// FIELDS
	//

	/**
	 * The message buffer to store messages in before actually printing them to
	 * the console. Printing happens only once each execution iteration (namely
	 * at the end).
	 */
	private final MessageBuffer buffer;

	/**
	 * A map from names to the input ports this console has access to.
	 */
	private final Map<String, InputPort> namesToInputPorts = new HashMap<String, InputPort>();

	/**
	 * A map from names to the output ports this console has access to.
	 */
	private final Map<String, OutputPort> namesToOutputPorts = new HashMap<String, OutputPort>();

	/**
	 * A map from the ports this console has access to to their names.
	 */
	private final Map<Port, String> portsToNames = new LinkedHashMap<Port, String>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a console that has access to the input ports
	 * <code>inputPorts</code> and the output ports <code>outputPorts</code>.
	 * 
	 * @param inputPorts
	 *            The input ports. Not <code>null</code>.
	 * @param outputPorts
	 *            The output ports. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>inPorts==null</code> or <code>outPorts==null</code>.
	 */
	public Console(final Map<String, InputPort> inputPorts,
			final Map<String, OutputPort> outputPorts) {

		if (inputPorts == null || outputPorts == null)
			throw new NullPointerException();

		this.namesToInputPorts.putAll(inputPorts);
		this.namesToOutputPorts.putAll(outputPorts);

		final List<String> portNames = new ArrayList<String>();
		portNames.addAll(inputPorts.keySet());
		portNames.addAll(outputPorts.keySet());
		Collections.sort(portNames);

		for (final String s : portNames)
			this.portsToNames.put(inputPorts.containsKey(s) ? inputPorts.get(s)
					: outputPorts.get(s), s);

		this.buffer = new MessageBuffer();
	}

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
	public void run() {

		/* Create commands. */
		final Map<String, Command> commands = new HashMap<String, Command>();
		commands.put("", new Report());
		commands.put("exit", new Exit());
		commands.put("inspect", new Inspect());
		commands.put("name", new Name());
		commands.put("report", new Report());
		commands.put("take", new Take());
		commands.put("write", new Write());

		/* Welcome user. */
		System.out.println("\nHello.\n");

		/* Enter main program loop. */
		boolean shouldTerminate = false;
		while (!shouldTerminate) {

			/* Request input. */
			System.out.print("> ");

			/* Read input. */
			final Scanner in = new Scanner(System.in);
			final String line = in.nextLine();
			final String[] tokens = line.split(" ");

			/* Process input. */
			final String command = tokens[0];
			final String[] arguments = Arrays.copyOfRange(tokens, 1,
					tokens.length);
			try {
				if (commands.containsKey(command))
					shouldTerminate = commands.get(command)
							.tryInvoke(arguments);
				else
					buffer.addControlMessage("I do not know the command \""
							+ command + "\".");

			} catch (final Command.CommandException e) {
				buffer.addControlMessage("Error! " + e.getMessage());
			}

			/* Write output. */
			System.out.println(buffer.getThenClear());
		}

		/* Exit. */
		System.exit(0);
	}

	//
	// METHODS
	//

	/**
	 * Gets the input port named <code>portName</code> accessible through this
	 * console.
	 * 
	 * @param portName
	 *            The port name. Not <code>null</code>.
	 * @return An input port. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasInputPort(portName)</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 * 
	 * @see #hasInputPort(String)
	 */
	private InputPort getInputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (!hasInputPort(portName))
			throw new IllegalArgumentException();

		return namesToInputPorts.get(portName);
	}

	/**
	 * Gets the output port named <code>portName</code> accessible through this
	 * console.
	 * 
	 * @param portName
	 *            The port name. Not <code>null</code>.
	 * @return An output port. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasOutputPort(portName)</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 * 
	 * @see #hasOutputPort(String)
	 */
	private OutputPort getOutputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (!hasOutputPort(portName))
			throw new IllegalArgumentException();

		return namesToOutputPorts.get(portName);
	}

	/**
	 * Checks if this console has access to an input port named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The port name. Not <code>null</code>.
	 * @return <code>true</code> if this console has access to an input port
	 *         named <code>portName</code>; <code>false</code> otherwise.
	 */
	private boolean hasInputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();

		return namesToInputPorts.containsKey(portName);
	}

	/**
	 * Checks if this console has access to an output port named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The port name. Not <code>null</code>.
	 * @return <code>true</code> if this console has access to an output port
	 *         named <code>portName</code>; <code>false</code> otherwise.
	 */
	private boolean hasOutputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();

		return namesToOutputPorts.containsKey(portName);
	}

	//
	// CLASSES
	//

	private abstract class Command {

		//
		// CONSTRUCTORS
		//

		/**
		 * Tries to invoke this command, throwing an exception upon failure.
		 * 
		 * @param arguments
		 *            The arguments of this command. Not <code>null</code>.
		 * @return <code>true</code> if the console running this command should
		 *         terminate afterwards; <code>false</code> otherwise.
		 * @throws CommandException
		 *             If something goes wrong while invoking.
		 * @throws NullPointerException
		 *             If <code>arguments==null</code> or
		 *             <code>arguments[i]==null</code> for some <code>i</code>.
		 */
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			if (arguments == null)
				throw new NullPointerException();
			for (final String s : arguments)
				if (s == null)
					throw new NullPointerException();

			return false;
		}

		//
		// METHODS
		//

		/**
		 * Tries to extract an item text from the arguments
		 * <code>arguments</code>, throwing an exception upon failure.
		 * 
		 * @param arguments
		 *            The arguments. Not <code>null</code>.
		 * @param index1
		 *            The index in <code>arguments</code> at which the item text
		 *            starts (inclusive).
		 * @param index2
		 *            The index in <code>arguments</code> at which the item text
		 *            stops (exclusive).
		 * @return A nonempty string. Never <code>null</code>.
		 * @throws CommandException
		 *             If something goes wrong while extracting.
		 * @throws IllegalArgumentException
		 *             If <code>index1&lt;0</code> or
		 *             <code>index2&gt;arguments.length</code> or
		 *             <code>index2&lt;index1</code>.
		 * @throws NullPointerException
		 *             If <code>arguments==null</code>.
		 */
		protected String tryExtractItemTextFromArguments(
				final String[] arguments, final int index1, int index2)
				throws CommandException {

			if (arguments == null)
				throw new NullPointerException();
			if (index1 < 0 || index2 > arguments.length || index2 < index1)
				throw new IllegalArgumentException();

			final StringBuilder builder = new StringBuilder();
			for (int i = index1; i < index2; i++)
				builder.append(arguments[i]).append(" ");

			if (builder.length() == 0)
				throw new CommandException(
						"Please specify an item text (as arguments starting from position "
								+ index1 + ").");

			return builder.toString().toString().trim();
		}

		/**
		 * Tries to extract a port from the arguments <code>arguments</code>,
		 * throwing an exception upon failure.
		 * 
		 * @param arguments
		 *            The arguments. Not <code>null</code>.
		 * @param index
		 * @return A port. Never <code>null</code>.
		 * @throws CommandException
		 *             If something goes wrong while extracting.
		 * @throws IllegalArgumentException
		 *             If <code>index&lt;0</code>.
		 * @throws NullPointerException
		 *             If <code>arguments==null</code>.
		 */
		protected Port tryExtractPortFromArguments(
				final String[] arguments, final int index)
				throws CommandException {

			if (arguments == null)
				throw new NullPointerException();
			if (index < 0)
				throw new IllegalArgumentException();

			/* Instantiate $portText. */
			if (arguments.length <= index)
				throw new CommandException(
						"Please specify a port name or reference (as an argument at position "
								+ (index + 1) + ").");

			final String portText = arguments[index];

			/* Interpret $portText as a reference. */
			try {
				final Iterator<Port> iterator = portsToNames.keySet()
						.iterator();
				for (int i = 0; i < Integer.parseInt(portText); i++)
					if (iterator.hasNext())
						iterator.next();

				if (iterator.hasNext())
					return iterator.next();

				throw new CommandException(
						"I failed to parse the argument at position "
								+ (index + 1)
								+ " to a port: I failed to dereference the provided reference.");
			}

			/* Interpret $portText as a name. */
			catch (final NumberFormatException e) {
				if (!hasInputPort(portText) && !hasOutputPort(portText))
					throw new CommandException(
							"I failed to parse the argument at position "
									+ (index + 1)
									+ " to a port: I do not have access to a port named \""
									+ portText + "\".");

				return hasInputPort(portText) ? getInputPort(portText)
						: getOutputPort(portText);
			}
		}

		//
		// EXCEPTIONS
		//

		class CommandException extends Exception {
			private static final long serialVersionUID = 1L;

			private CommandException(final String message) {
				super(message);
			}
		}
	}

	private class Exit extends Command {

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			super.tryInvoke(arguments);
			Console.this.buffer.addControlMessage("Bye.");
			return true;
		}
	}

	private class Inspect extends Command {

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			super.tryInvoke(arguments);

			/* Extract the port to inspect. */
			final Port port = tryExtractPortFromArguments(arguments, 0);
			final org.ect.codegen.v2.core.rt.java.internal.Take[] takes = port
					.getTakes();

			final org.ect.codegen.v2.core.rt.java.internal.Write[] writes = port
					.getWrites();

			final StringBuilder builder = new StringBuilder()
					.append("The port named \"").append(portsToNames.get(port))
					.append("\" has ")
					.append(takes.length == 0 ? "no" : takes.length)
					.append(" pending take operation")
					.append((takes.length == 1 ? "" : "s")).append(" and ")
					.append(writes.length == 0 ? "no" : "the following")
					.append(" pending write operation")
					.append(writes.length == 1 ? "" : "s")
					.append(writes.length == 0 ? "." : ":\n");

			for (org.ect.codegen.v2.core.rt.java.internal.Write w : writes)
				builder.append("\n  - " + Item.convertToString(w.getItem()));

			Console.this.buffer.addControlMessage(builder.toString());
			return false;
		}
	}

	private class Name extends Command {

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			super.tryInvoke(arguments);

			final StringBuilder builder = new StringBuilder();
			final Iterator<String> iterator = Console.this.portsToNames
					.values().iterator();
			switch (Console.this.portsToNames.size()) {
			case 0:
				builder.append("I do not have access to any ports.");
				break;
			case 1:
				builder.append("I have access to the port named \"")
						.append(iterator.next()).append("\" [0].");
				break;
			case 2:
				builder.append("I have access to the ports named \"")
						.append(iterator.next()).append("\" [0] and \"")
						.append(iterator.next()).append("\" [1].");
				break;
			default:
				builder.append("I have access to the following ports:\n - \"")
						.append(iterator.next()).append("\" [0]");
				int index = 1;
				while (iterator.hasNext())
					builder.append("\n - \"").append(iterator.next())
							.append("\" [").append(index++).append("]");
			}

			Console.this.buffer.addControlMessage(builder.toString());
			return false;
		}
	}

	private class Report extends Command {

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			super.tryInvoke(arguments);
			return false;
		}
	}

	private class Take extends Command {

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			super.tryInvoke(arguments);

			/* Extract the output port to take from. */
			final Port port = tryExtractPortFromArguments(arguments, 0);
			final String portName = Console.this.portsToNames.get(port);

			if (!(port instanceof OutputPort))
				throw new CommandException("The port named \"" + portName
						+ "\" is not an output port.");

			/* Take from $port. */
			Console.this.buffer
					.addControlMessage("I will attempt to take an item from the port named \""
							+ portName + "\".");

			Ports.asyncTake(port, new Continuation() {
				@Override
				public void run(final Object item) {
					Console.this.buffer.addEventMessage("I took the item "
							+ Item.convertToString(item)
							+ " from the port named \"" + portName + "\".");
				}
			});

			return false;
		}
	}

	private class Write extends Command {

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected boolean tryInvoke(final String[] arguments)
				throws CommandException {

			super.tryInvoke(arguments);

			/* Extract the input port to write to. */
			final Port port = tryExtractPortFromArguments(arguments, 0);
			final String portName = Console.this.portsToNames.get(port);

			if (!(port instanceof InputPort))
				throw new CommandException("The port named \"" + portName
						+ "\" is not an input port.");

			/* Extract the item to write. */
			final String itemText = tryExtractItemTextFromArguments(arguments,
					1, arguments.length);

			if (!Item.canConvertToObject(itemText))
				throw new CommandException(
						"I failed to parse the arguments starting from position 2 to an item.");

			final Object item = Item.convertToObject(itemText);

			/* Write $item to $port. */
			Console.this.buffer.addControlMessage("I will attempt to write "
					+ itemText + " to the port named \"" + portName + "\".");

			Ports.asyncWrite(port, item, new Continuation() {
				@Override
				public void run(final Object item) {
					Console.this.buffer.addEventMessage("I wrote the item "
							+ Item.convertToString(item)
							+ " to the port named \"" + portName + "\".");
				}
			});

			return false;
		}
	}
}

class MessageBuffer {

	//
	// FIELDS
	//

	/**
	 * Buffer for the control messages added since the last invocation of
	 * {@link #getThenClear()}.
	 */
	private final List<Entry<Long, String>> controlBuffer = new ArrayList<Entry<Long, String>>();

	/**
	 * Buffer for the event messages added since the last invocation of
	 * {@link #getThenClear()}.
	 */
	private final List<Entry<Long, String>> eventBuffer = new ArrayList<Entry<Long, String>>();

	//
	// METHODS
	//

	/**
	 * Adds the control message <code>message</code> to this buffer.
	 * 
	 * @param message
	 *            The message. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>message==null</code>.
	 */
	public void addControlMessage(final String message) {
		if (message == null)
			throw new NullPointerException();

		final long time = System.currentTimeMillis();
		synchronized (controlBuffer) {
			controlBuffer.add(new SimpleEntry<Long, String>(time, message));
		}
	}

	/**
	 * Adds the event message <code>message</code> to this buffer.
	 * 
	 * @param message
	 *            The message. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>message==null</code>.
	 */
	public void addEventMessage(final String message) {
		if (message == null)
			throw new NullPointerException();

		final long time = System.currentTimeMillis();
		synchronized (eventBuffer) {
			eventBuffer.add(new SimpleEntry<Long, String>(time, message));
		}
	}

	/**
	 * Gets the buffered messages as a formatted string, then clears this
	 * message buffer.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getThenClear() {
		final StringBuilder builder = new StringBuilder();

		synchronized (controlBuffer) {
			for (final Entry<Long, String> e : controlBuffer)
				builder.append("\n").append(e.getValue()).append("\n");

			controlBuffer.clear();
		}

		synchronized (eventBuffer) {
			if (builder.length() > 0 && eventBuffer.isEmpty())
				return builder.toString();

			builder.append("\n")
					.append(eventBuffer.isEmpty() ? "No" : "The following")
					.append(" event")
					.append(eventBuffer.size() == 1 ? "" : "s")
					.append(" occurred since our last interaction")
					.append(eventBuffer.isEmpty() ? "." : ":\n");

			for (final Entry<Long, String> e : eventBuffer)
				builder.append("\n  - [")
						.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
								.format(new Date(e.getKey()))).append("] ")
						.append(e.getValue());

			eventBuffer.clear();
		}

		return builder.append("\n").toString();
	}
}
