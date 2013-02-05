package cwi.reo.services;


public interface Service {
	
	/**
	 * Write a data-item to the given port. The write operations
	 * are always executed before the read operations. The port
	 * id refers to an In-Port.
	 * 
	 * @param port Port where the item should be written to.
	 * @param item Data-item to be written.
	 * @result <code>True</code> if the write operation was successful.
	 */
	public boolean write(int port, Object item);
	
	
	/**
	 * Read a data-item from the given port. This read must 
	 * be non-destructive. Read operations are always executed
	 * after the write operations. The port id refers to an Out-Port.
	 * 
	 * @param port Port where the item should be read from.
	 * @return The data-item or <code>null</code> if there is no data available.
	 */
	public Object read(int port);
	
	
	/**
	 * Returns the number of In-Ports of this Service.
	 * All write operations will be performed on a port
	 * that has an id that is greater or equal zero and
	 * lower than {@link #inPorts()}.
	 * 
	 * @return The number of In-Ports of this Service.
	 */
	public int inPorts();
	
	
	/**
	 * Returns the number of Out-Ports of this Service.
	 * All read operations will be performed on a port
	 * that has an id that is greater or equal zero and
	 * lower than {@link #outPorts()}.
	 * 
	 * @return The number of Out 
	 */
	public int outPorts();
	
	
	/**
	 * After all write and read operations are performed,
	 * {@link #canCommit()} is called to check whether the
	 * performed IO operations are valid and all other
	 * parameters allow a commitment of the transactions.
	 */
	public boolean canCommit();
	
	
	/**
	 * Commit the current transactions. All buffers should
	 * be emptied and the internal state might be changed.
	 */
	public void commit();
	
	
	/**
	 * Fail the current transactions. All buffered data-items
	 * should be discarded and the internal state might be changed.
	 */
	public void fail();

}
