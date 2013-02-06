package org.ect.codegen.ea.runtime.conditions;

import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.HASDATA;
import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.WANTSDATA;

import java.util.Collection;
import java.util.Set;

import org.ect.codegen.ea.runtime.TransCon;
import org.ect.codegen.ea.runtime.TransactionalIO;
import org.ect.codegen.ea.runtime.TransactionalIO.IOState;


public class PortSet implements TransCon{
	private TransactionalIO[] ports;
	
	public PortSet(Collection<TransactionalIO> ports) {
		this.ports = ports.toArray(this.ports);
	}
	
	public PortSet(TransactionalIO... ports) {
		this.ports = ports;
	}
	
	public boolean execute() {
		for (TransactionalIO p: ports) {
			Set<IOState> ioState = p.getState();
			if ( !(ioState.contains(HASDATA) || ioState.contains(WANTSDATA)) ) {
				return false;						
			}
		}		

		return true;
	}
	
	public void lock() {
		for (TransactionalIO p: ports) 
			p.beginTxn();
	}

	public void unlock() {
		for (TransactionalIO p: ports) 
			p.endTxn();
	}	
}
