package org.ect.ea.runtime;

import java.util.Set;

/**
 * Interal (engine-side) interface for a port
 * @author maraikar
 *
 */
public interface TransactionalIO {
	enum IOState {
		WANTSDATA, HASDATA, BUSYTXN;
	}
	/**
	 * @return set of currently active states
	 */
	Set<IOState> getState();

	
	/**
	 * Try to be a transaction to prepare for reads and/or writes. 
	 */
	void beginTxn();

	/**
	 * Conclude a transaction. 
	 */
	void endTxn();
	
	/**
	 * Abort a transaction. 
	 */
//	void abortTxn();
	
	/**
	 * Remove data from port or memory. Engine must call @{beginTxn} first
	 * @return data
	 */	
	Object engineTake();
	
	/**
	 * Write data to port or memory. Engine must call @{beginTxn} first
	 * @param o Object to be written
	 */
	void engineWrite(Object o);
}
