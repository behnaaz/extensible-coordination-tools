package org.ect.ea.runtime.primitives;

import static org.ect.ea.runtime.TransactionalIO.IOState.BUSYTXN;
import static org.ect.ea.runtime.TransactionalIO.IOState.HASDATA;
import static org.ect.ea.runtime.TransactionalIO.IOState.WANTSDATA;

import java.util.concurrent.TimeoutException;

import org.ect.ea.runtime.Port;
import org.ect.ea.runtime.Sink;
import org.ect.ea.runtime.Source;


public final class TimeoutPort<T> extends BlockingPort<T> 
implements Source<T>, Sink<T> {
	private static final long serialVersionUID = 1L;
	
	//XXX:This should go away
	public TimeoutPort(String name) {
		super(name, null);
	}
	
	public TimeoutPort(String name, Class<? extends Port> type) {
		super(name, type);
	}
	
	public T take(long timeout) throws InterruptedException, TimeoutException {
		try {
			mutex.lockInterruptibly();
			txn.lockInterruptibly();
			
			assert ioState.isEmpty();
			ioState.add(WANTSDATA);
			
			setChanged();
			notifyObservers(ioState);

			while (!ioState.isEmpty())
				if(timeout > 0)
					timeout = component.awaitNanos(timeout);
				else {
					assert !ioState.contains(BUSYTXN);
					
					ioState.clear();
					setChanged();
					notifyObservers(ioState);
					
					throw new TimeoutException();
				}
			//return successfully
			T tmp = fleeting;
			fleeting = null;
			return tmp;
		} finally {
			txn.unlock();
			mutex.unlock();
		}
	}
	
	public void write(T o, long timeout) throws InterruptedException, TimeoutException {
		try {
			mutex.lockInterruptibly();
			txn.lockInterruptibly();

			assert ioState.isEmpty();
			fleeting=o;
			ioState.add(HASDATA);

			setChanged();
			notifyObservers(ioState);

			while (!ioState.isEmpty())
				if(timeout > 0)
					timeout = component.awaitNanos(timeout);
				else {
					assert !ioState.contains(BUSYTXN);
					
					ioState.clear();
					setChanged();
					notifyObservers(ioState);
					
					throw new TimeoutException();
				} 
			//return successfully
		} finally {
			txn.unlock();
			mutex.unlock();
		}
	}
}