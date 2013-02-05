package cwi.yawl.servlets;

import java.io.File;
import java.io.IOException;

import cwi.yawl.AbstractYAWLService;
import cwi.yawl.ExternalWorkItem;
import cwi.yawl.spool.FileLog;
import cwi.yawl.spool.MemorySpooler;


public class YAWLServlet extends AbstractYAWLService {
	
	// Spooler to exchange the data.
	public static final MemorySpooler SPOOLER;
	
	static {
		SPOOLER = new MemorySpooler();
		try {
			File log = File.createTempFile("ReoAdapters", ".log");
			SPOOLER.setLog(new FileLog(log.getAbsolutePath()));
		} catch (IOException e) {}
	}
	
	
	protected void doMyService(ExternalWorkItem external) {
		String serviceId = external.getTaskID(); 
		SPOOLER.putSync(serviceId, external.getInputElement(0));
	}
	
}
