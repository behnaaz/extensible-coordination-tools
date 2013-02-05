package cwi.yawl.spool;


public interface Spooler {
	
	/**
	 * Write a data-item to a port. If there was already an
	 * item at this port, it is overwritten. Subclasses can
	 * additionally add methods that wait until the port is free. 
	 */
	public void put(String portId, Object item);

	/**
	 * Take a data-item from a port. If no data-item is available
	 * <code>null</code> is returned. After a successful take,
	 * the data-item is no longer available at the port.
	 */
	public Object take(String portId);
	
	/**
	 * Read a data-item from a port without destroying it. 
	 * If no data-item is available <code>null</code> is returned. 
	 * After a successful take, the data-item is still available 
	 * at the port.
	 */
	public Object read(String portId);
	
		
	/**
	 * Get the Log for this spooler.
	 */
	public Log getLog();

	/**
	 * Set the Log for this spooler.
	 */
	public void setLog(Log log);
	
}
