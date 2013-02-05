package cwi.yawl.spool;


// TODO: We need semaphores here!!!

public abstract class AbstractSpooler implements Spooler {
	
	// Default sleep time in milliseconds.
	public static final int SLEEP = 500;
	
	// Log to be used.
	protected Log log;
	
	/**
	 * Check whether there is a data-item available
	 * at a given port.
	 */
	public boolean available(String portId) {
		return read(portId)!=null;
	}
	
	/**
	 * Synchronously put a data-item to the given port.
	 * This waits until the port is free, offers the item
	 * and waits until it is taken by someone.
	 */
	public void putSync(String portId, Object item) {
		
		if (available(portId)) {
			logMessage("Waiting for empty port '" + portId + "'.");
		}
		
		while (available(portId)) {
			sleep();
		}
		
		logMessage("Offering item at port '" + portId + "'.");
		put(portId, item);

		while (available(portId)) {
			sleep();
		}

	}
	
	
	public Object takeSync(String portId) {

		if (!available(portId)) {
			logMessage("Waiting for data-item on port '" + portId + "'.");
		}
		
		while (!available(portId)) {
			sleep();
		}
		
		logMessage("Taking item from port '" + portId + "'.");
		Object item = take(portId);
		
		return item;
		
	}
	
	
	/* ----- Sleep ----- */
	
	protected void sleep() {
		try {
			Thread.sleep(SLEEP);
		} catch (InterruptedException e) {}
	}
	
	
	/* ----- Logging ------ */
	
	protected void logMessage(String message) {
		if (log!=null) getLog().logMessage(message);
	}

	protected void logException(Throwable e) {
		if (log!=null) getLog().logException(e);
	}
		
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}

}
