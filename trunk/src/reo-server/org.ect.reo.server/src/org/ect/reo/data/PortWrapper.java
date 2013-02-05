package org.ect.reo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.ect.ea.runtime.Sink;
import org.ect.ea.runtime.Source;
import org.ect.ea.runtime.TransactionalIO;
import org.ect.ea.runtime.primitives.TimeoutPort;


public class PortWrapper<T> implements Source<T>, Sink<T> {
	
	// Exception for silent interrupts.
	public static class SilentInterruptedException extends InterruptedException {
		private static final long serialVersionUID = 1L;
	};
	
	/*
	 * Wrapper for the results of I/O operations.
	 */
	class Result {
		T data;
		InterruptedException interrupted;
		TimeoutException timeout;
	}
	
	// The real port.
	private TimeoutPort<T> port; 
	
	// List of threads accessing the port.
	private List<Thread> threads;
	
	// Flag indicating whether there was a silent interrupt.
	private boolean silentInterrupt = false;
	
	
	/**
	 * Default constructor.
	 * @param name Name of the port.
	 */
	public PortWrapper(String name) {
		this.port = new TimeoutPort<T>(name);
		this.threads = new ArrayList<Thread>(4);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Source#take()
	 */
	public T take() throws InterruptedException {
		
		final Result result = new Result();
		final Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					result.data = port.take();
				} catch (InterruptedException e) {
					result.interrupted = silentInterrupt ? new SilentInterruptedException() : e;
				}
			}
		});
		
		addThread(thread);
		thread.start();
		thread.join();
		removeThread(thread);
		
		if (result.interrupted!=null) {
			throw result.interrupted;
		}
				
		return result.data;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Source#take(long)
	 */
	public T take(final long timeout) throws InterruptedException, TimeoutException {
		
		final Result result = new Result();
		final Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					result.data = port.take(timeout);
				} catch (InterruptedException e) {
					result.interrupted = silentInterrupt ? new SilentInterruptedException() : e;
				} catch (TimeoutException e) {
					result.timeout = e;
				}
			}
		});
		
		addThread(thread);
		thread.start();
		thread.join();
		removeThread(thread);
		
		if (result.interrupted!=null) {
			throw result.interrupted;
		}
		if (result.timeout!=null) {
			throw result.timeout;
		}
		
		return result.data;
	}
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Sink#write(java.lang.Object)
	 */
	public void write(final T data) throws InterruptedException {
		
		final Result result = new Result();
		final Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					port.write(data);
				} catch (InterruptedException e) {
					result.interrupted = silentInterrupt ? new SilentInterruptedException() : e;
				}
			}
		});
		
		addThread(thread);
		thread.start();
		thread.join();
		removeThread(thread);
		
		if (result.interrupted!=null) {
			throw result.interrupted;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Sink#write(java.lang.Object, long)
	 */
	public void write(final T data, final long timeout) throws InterruptedException, TimeoutException {

		final Result result = new Result();
		final Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					port.write(data, timeout);
				} catch (InterruptedException e) {
					result.interrupted = silentInterrupt ? new SilentInterruptedException() : e;
				} catch (TimeoutException e) {
					result.timeout = e;
				}
			}
		});
		
		addThread(thread);
		thread.start();
		thread.join();
		removeThread(thread);
		
		if (result.interrupted!=null) {
			throw result.interrupted;
		}
		if (result.timeout!=null) {
			throw result.timeout;
		}
	}
	
	
	
	private synchronized boolean addThread(Thread thread) {
		return threads.add(thread);
	}
	
	private synchronized boolean removeThread(Thread thread) {
		return threads.remove(thread);
	}
	
	/**
	 * Interrupt the pending I/O operation.
	 */
	public synchronized void interrupt() {
		for (Thread thread : threads) {
			thread.interrupt();
		}
	}
	
	/**
	 * Silently interrupt pending I/O operation.
	 */
	public synchronized void silentlyInterrupt() {
		silentInterrupt = true;
		for (Thread thread : threads) {
			thread.interrupt();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {}
		}			
		
		silentInterrupt = false;
	}

	public TransactionalIO getPort() {
		return port;
	}
}
