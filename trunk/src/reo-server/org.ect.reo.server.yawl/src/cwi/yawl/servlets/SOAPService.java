package cwi.yawl.servlets;

import cwi.reo.services.Service;
import cwi.yawl.spool.MemorySpooler;


public class SOAPService implements Service {
	
	// Spooler to be used.
	private static MemorySpooler SPOOLER = YAWLServlet.SPOOLER;
	
	// Name of the service.
	private String name;
	
	public boolean canCommit() {
		System.out.println("cancommit");
		return true;
	}
	
	public void commit() {
		System.out.println("commit");
	}
	
	public void fail() {
		System.out.println("fail");
	}
	
	public int inPorts() {
		return 1;
	}
	
	public int outPorts() {
		return 1;
	}
	
	public Object read(int port) {		
		System.out.println("read " + port);
		return SPOOLER.read(name);
	}
	
	public boolean write(int port, Object item) {
		System.out.println("write " + port + " " + item);
		SPOOLER.put(name, item);
		return true;
	}

	public void setName(String name) {
		System.out.println("Setting SOAP service name to " + name);
		this.name = name;
	}
	
}
