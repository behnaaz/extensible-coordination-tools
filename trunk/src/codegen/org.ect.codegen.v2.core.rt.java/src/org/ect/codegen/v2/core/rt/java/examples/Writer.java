package org.ect.codegen.v2.core.rt.java.examples;

import org.ect.codegen.v2.core.rt.java.InputPort;

public class Writer implements Runnable {

	//
	// FIELDS
	//

	private final int id;

	private final int pace;

	private final InputPort port;

	private int nMessages = 0;

	//
	// CONSTRUCTORS
	//

	private Writer(final int id, final InputPort port, final int pace) {
		this.id = id;
		this.pace = pace;
		this.port = port;
	}

	//
	// METHODS
	//

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(pace * 1000);
			} catch (final Exception e) {
				continue;
			}
			
			final String item = "Message-" + ++nMessages + "." + id;
			port.write(item);
			System.out.println("Writer-" + id + " wrote \"" + item + "\".");
		}
	}

	//
	// STATIC
	//

	private static int nWriters = 0;

	public static Writer newWriter(final InputPort port, final int pace) {
		return new Writer(++nWriters, port, pace);
	}

	public static Thread newWriterThread(final InputPort port, final int pace) {
		return new Thread(newWriter(port, pace));
	}
}
