package org.ect.codegen.v2.core.rt.java.examples;

import org.ect.codegen.v2.core.rt.java.OutputPort;

public class Reader implements Runnable {
	//
	// FIELDS
	//

	private final int id;

	private final int pace;

	private final OutputPort port;

	//
	// CONSTRUCTORS
	//

	private Reader(final int id, final OutputPort port, final int pace) {
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
			
			final Object item = port.take();
			System.out.println("Reader-" + id + " took \"" + item + "\".");
		}
	}

	//
	// STATIC
	//

	private static int nReaders = 0;

	public static Reader newReader(final OutputPort port, final int pace) {
		return new Reader(++nReaders, port, pace);
	}

	public static Thread newReaderThread(final OutputPort port, final int pace) {
		return new Thread(newReader(port, pace));
	}
}
