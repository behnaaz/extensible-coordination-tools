package org.ect.codegen.ea.runtime.primitives;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.ect.codegen.ea.runtime.TransactionalIO;

import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.BUSYTXN;

/**
 * If a constant is read it returns its value. It can't be written to.
 * @author maraikar
 *
 */
public class Constant<T> implements Serializable, TransactionalIO {
	private static final long serialVersionUID = 1L;
	protected final Lock txn = new ReentrantLock();
	protected final Set<IOState> ioState =  
		Collections.synchronizedSet(EnumSet.noneOf(IOState.class));

	protected final T literal;

	public Constant(T literal) {
		this.literal = literal;
		ioState.add(IOState.HASDATA);
	}
	
	public boolean isReadable() {
		return true;
	}

	public boolean isWritable() {
		return false;
	}

	@Override
	public String toString() {
		return literal.toString();
	}

	public Object engineTake() {
		return literal;
	}

	public void engineWrite(Object o) {
		throw new IllegalAccessError(getClass() + " cannot be written to");
	}

	public void beginTxn() {
		txn.lock();
		ioState.add(BUSYTXN);
	}

	public void endTxn() {
		ioState.remove(BUSYTXN);
		txn.unlock();
	}

	public Set<IOState> getState() {
		return Collections.unmodifiableSet(ioState);
	}
}
