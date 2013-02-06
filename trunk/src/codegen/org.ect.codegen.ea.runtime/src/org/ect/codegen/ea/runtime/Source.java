package org.ect.codegen.ea.runtime;

import java.util.concurrent.TimeoutException;

/**
 * Represents a port corresponding to a Reo prmitive's SourceEnd 
 * @author maraikar
 * @param <T> type of data accepted by port
 * @see Sink
 */
public interface Source<T> extends Port<T> {
	/**
	 * Read data destructively from port 
	 * @return value read
	 * @throws InterruptedException
	 */
	T take() throws InterruptedException;
	/**
	 * @param timeout in nanoseconds
	 * @return the object taken
	 * @throws InterruptedException
	 */
	T take(long timeout) throws InterruptedException, TimeoutException;
}
