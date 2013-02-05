package org.ect.reo.service.sinks;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.actions.PageHelper;
import org.ect.reo.data.CoordinatorThread;
import org.ect.reo.data.Network;
import org.ect.reo.data.NetworkIdentifier;
import org.ect.reo.data.PortWrapper.SilentInterruptedException;

import org.ect.ea.runtime.Sink;


@WebService(targetNamespace = "http://reo.project.cwi.nl/live/sinks")
public class SinkEndpoint {
		
	@Resource 
	WebServiceContext wsContext;
	
	/**
	 * Write data to this sink end.
	 * @param data Data to be written.
	 * @param nanos Timeout.
	 * @throws InterruptedException On interrupts.
	 * @throws TimeoutException On timeouts.
	 */
	public void write(String data, long nanos) throws InterruptedException, TimeoutException {
		
		PageHelper helper = new PageHelper(getServletContext());
		String pathInfo = getPathInfo();
				
		// Parse and resolve the network identifier.
		NetworkIdentifier identifier = null;
		Network network = null;
		PrimitiveEnd end = null;
		
		while (true) {
			
			try {
				identifier = NetworkIdentifier.parse(pathInfo);
				network = helper.getWorkspace().getNetwork(identifier);
				end = helper.getWorkspace().getPrimitiveEnd(identifier);
				assert network!=null && end!=null;
			}
			catch (Throwable e) {
				throw new InterruptedException("Invalid port '" + pathInfo + "'.");
			}
			
			// Get the coordinator.
			CoordinatorThread<String> thread = network.getCoordinatorThread();
			if (thread==null || thread.getJob()==null || thread.getPort(end)==null) {
				throw new InterruptedException("Network not running.");
			}
			
			// Perform the write operation.
			Sink<String> port = thread.getPort(end);
		
			try {
				if (nanos>=0) {
					port.write(data, nanos);
				} else {
					port.write(data);
				}
			} catch (SilentInterruptedException e) {
				// Start again.
				continue;
			}
			// If there was no silent interrupt we are done now..
			return;
		}
	}
	
	
	@WebMethod (exclude=true)
	private ServletContext getServletContext() {
		return (ServletContext) wsContext.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
	}
	
	@WebMethod (exclude=true)
	private String getPathInfo() {
		return (String) wsContext.getMessageContext().get(MessageContext.PATH_INFO);
	}

}
