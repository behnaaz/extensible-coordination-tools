package org.ect.codegen.ea.runtime.primitives;

import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.BUSYTXN;
import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.HASDATA;
import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.WANTSDATA;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.ect.codegen.ea.runtime.Port;
import org.ect.codegen.ea.runtime.TransactionalIO;


/**
 * An implementation of a zero-length blocking queue.
 * i.e. There must be reader before a writer is unblocked.
 * @author maraikar
 *
 */
class BlockingPort<T> extends Observable 
implements TransactionalIO, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected final Lock txn = new ReentrantLock(),
						mutex = new ReentrantLock();
	protected final Condition component = txn.newCondition();
	protected final Set<IOState> ioState =  
		Collections.synchronizedSet(EnumSet.noneOf(IOState.class));

	protected volatile T fleeting;
	private final String name;
	private Class<? extends Port> type;
	
	public BlockingPort(String name, Class<? extends Port> type) {
		this.name = name;
		this.type = type;
	}
	
	public T take() throws InterruptedException {
//		assert type==Source.class;
		try {
			mutex.lockInterruptibly();
			txn.lockInterruptibly();
			
			assert ioState.isEmpty();
			ioState.add(WANTSDATA);

			setChanged();
			notifyObservers(ioState);

			while (!ioState.isEmpty())
				component.await();
			
			T tmp = fleeting;
			fleeting = null;

			return tmp;
		} finally {
			txn.unlock();
			mutex.unlock();
		}
	}
	
	public void write(T value) throws InterruptedException {
//		assert type==Sink.class;
		try {
			mutex.lockInterruptibly();
			txn.lockInterruptibly();
			
			assert ioState.isEmpty() && fleeting==null;
			fleeting=value;
			ioState.add(HASDATA);

			setChanged();
			notifyObservers(ioState);

			while (!ioState.isEmpty())
				component.await();
		} finally {
			txn.unlock();
			mutex.unlock();
		}
	}
	
	public void beginTxn() {
		txn.lock();		
		ioState.add(BUSYTXN);
	}
	
	public Object engineTake() {
		assert ioState.contains(BUSYTXN) && ioState.contains(HASDATA) && fleeting!=null;
		return fleeting;
	}

	@SuppressWarnings("unchecked")
	public void engineWrite(Object value) {
		assert ioState.contains(BUSYTXN) && ioState.contains(WANTSDATA) && fleeting==null;
		fleeting=(T) value;
	}
	
	public void endTxn() {
		if (ioState.contains(HASDATA))
			fleeting=null;

		ioState.clear();
		component.signal();
		txn.unlock();
	}

	public Set<IOState> getState() {
		return Collections.unmodifiableSet(ioState);
	}

	@Override public String toString() {
		return name;
	}
}
