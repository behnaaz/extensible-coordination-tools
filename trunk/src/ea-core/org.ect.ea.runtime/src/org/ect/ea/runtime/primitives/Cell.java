package org.ect.ea.runtime.primitives;

import static org.ect.ea.runtime.TransactionalIO.IOState.BUSYTXN;
import static org.ect.ea.runtime.TransactionalIO.IOState.HASDATA;
import static org.ect.ea.runtime.TransactionalIO.IOState.WANTSDATA;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.ect.ea.runtime.TransactionalIO;


public final class Cell implements Serializable, TransactionalIO {
	private static final long serialVersionUID = 1L;
	
	protected final Lock txn = new ReentrantLock();
	protected final Set<IOState> ioState =  
		Collections.synchronizedSet(EnumSet.noneOf(IOState.class));

	protected final String name;
	volatile Object buffer;
	
	public Cell(String name) {
		this(name, null);
		ioState.add(WANTSDATA);
	}
	
	public Cell(String name, Object initValue) {
		this.name = name;
		buffer = initValue;
		ioState.add(initValue==null ? WANTSDATA:HASDATA);
	}
	
	public Object engineTake() {
		assert buffer!=null ;
		
		Object tmp = buffer;
		ioState.remove(HASDATA);
		
		return tmp;
	}
	
	public void engineWrite(Object o) {
		buffer=o;
		ioState.add(HASDATA);
	}
		
	public void beginTxn() {
		txn.lock();
		ioState.add(BUSYTXN);
	}

	public void endTxn() {
		if (ioState.contains(HASDATA)) {
			buffer=null;
			ioState.remove(HASDATA);
		}

		ioState.remove(BUSYTXN);
		txn.unlock();
	}
	public Set<IOState> getState() {
		return ioState;  
	}

	public String toString() {
		return name;
	}
}
