package org.ect.reo.service.sources;

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

import org.ect.ea.runtime.Source;


@WebService(targetNamespace = "http://reo.project.cwi.nl/live/sources")
public class SourceEndpoint {
	
	@Resource 
	WebServiceContext wsContext;
	
	/**
	 * Take data from this source end.
	 * @param nanos Timeout.
	 * @throws InterruptedException On interrupts.
	 * @throws TimeoutException On timeouts.
	 * @return Data taken from this port.
	 */
	public String take(long nanos) throws InterruptedException, TimeoutException {
		
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
			
			// Perform the take operation.
			Source<String> port = thread.getPort(end);		
			String data = null;
			
			try {
				if (nanos>=0) {
					data = port.take(nanos);
				} else {
					data = port.take();
				}
			} catch (SilentInterruptedException e) {
				// Start again.
				continue;
			}
			// If there was no silent interrupt and we read data, we are done now.
			if (data!=null) return data;
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
