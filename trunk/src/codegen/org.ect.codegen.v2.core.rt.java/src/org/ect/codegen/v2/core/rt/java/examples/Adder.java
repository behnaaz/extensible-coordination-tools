package org.ect.codegen.v2.core.rt.java.examples;

import org.ect.codegen.v2.core.rt.java.InputPort;
import org.ect.codegen.v2.core.rt.java.OutputPort;

public class Adder implements Runnable {

	//
	// FIELDS
	//

	final OutputPort lhsPort;

	final OutputPort rhsPort;

	final InputPort resultPort;

	//
	// CONSTRUCTORS
	//

	private Adder(final OutputPort lhsPort, final OutputPort rhsPort,
			final InputPort resultPort) {

		this.lhsPort = lhsPort;
		this.rhsPort = rhsPort;
		this.resultPort = resultPort;
	}

	//
	// METHODS
	//

	@Override
	public void run() {
		while (true) {
			int lhs = (Integer) lhsPort.take();
			int rhs = (Integer) rhsPort.take();
			resultPort.write(lhs + rhs);
		}
	}

	//
	// STATIC
	//

	public static Adder newAdder(final OutputPort lhsPort,
			final OutputPort rhsPort, final InputPort resultPort) {
		return new Adder(lhsPort, rhsPort, resultPort);
	}

	public static Thread newAdderThread(final OutputPort lhsPort,
			final OutputPort rhsPort, final InputPort resultPort) {
		return new Thread(newAdder(lhsPort, rhsPort, resultPort));
	}
}
