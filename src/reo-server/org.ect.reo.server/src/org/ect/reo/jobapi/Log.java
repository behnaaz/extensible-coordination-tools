package org.ect.reo.jobapi;

public interface Log {
	
	/**
	 * Log a message.
	 */
	public abstract void logMessage(String msg);
	
	/**
	 * Log an error.
	 */
	public abstract void logError(String msg, Throwable t);
	
	/**
	 * Print the logged messages and errors.
	 */
	public abstract String printLog();
	
}