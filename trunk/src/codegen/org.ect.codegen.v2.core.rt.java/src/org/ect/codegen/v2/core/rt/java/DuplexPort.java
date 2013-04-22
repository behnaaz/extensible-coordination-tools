package org.ect.codegen.v2.core.rt.java;

import java.util.concurrent.TimeoutException;

public interface DuplexPort extends InputPort, OutputPort {
}

class DuplexPortImpl extends PortImpl implements DuplexPort {

	@Deprecated
	@Override
	public void write(Object item) {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public void write(Object item, long timeout) throws TimeoutException {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Object take() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	@Override
	public Object take(long timeout) throws TimeoutException {
		throw new UnsupportedOperationException();
	}
}