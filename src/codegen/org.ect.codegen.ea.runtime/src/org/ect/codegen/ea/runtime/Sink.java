package org.ect.codegen.ea.runtime;

import java.util.concurrent.TimeoutException;

/**
 * Represents a port corresponding to a Reo prmitive's SinkEnd.
 * @author maraikar
 * @param <T> type of data accepted by port
 * @see Source
 */
public interface Sink<T> extends Port<T> {
	/**
	 * Write data to port or memory
	 * @param o Object to be written
	 * @throws InterruptedException
	 */
	void write(T data) throws InterruptedException;
	/**
	 * @param o the object to be written
	 * @param timeout in nanoseconds	 * @throws InterruptedException
	 */
	void write(T o, long timeout) throws InterruptedException, TimeoutException;
}
