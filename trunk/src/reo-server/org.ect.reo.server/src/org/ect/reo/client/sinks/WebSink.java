package org.ect.reo.client.sinks;

import java.util.concurrent.TimeoutException;

import javax.xml.ws.BindingProvider;

import org.ect.reo.client.sinks.jaxws.InterruptedException_Exception;
import org.ect.reo.client.sinks.jaxws.SinkEndpoint;
import org.ect.reo.client.sinks.jaxws.SinkEndpointService;
import org.ect.reo.client.sinks.jaxws.TimeoutException_Exception;

import cwi.ea.runtime.Sink;


/**
 * Wrapper for SinkEndpoint ports. Uses a port ID to instantiate
 * the corresponding web service and forwards I/O operations 
 * to this web service.
 * 
 * @author Christian Koehler
 *
 */
public class WebSink implements Sink<String> {
	
	// The wrapped SinkEndpoint instance.
	protected SinkEndpoint endpoint;
	
	/**
	 * Default constructor.
	 * @param url URL of SinkEndpoint web service including the port ID.
	 */
	public WebSink(String url) {
		
		// Create SinkEndpoint instance.
		SinkEndpointService service = new SinkEndpointService();
		endpoint = service.getSinkEndpointPort();
		
		// Update the request context with the new URL.
		((BindingProvider) endpoint).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Source#write(java.lang.Object, long)
	 */
	//@Override
	public void write(String data, long nanos) throws InterruptedException, TimeoutException {
		try {
			endpoint.write(data, nanos);
		}
		catch (InterruptedException_Exception e) {
			throw new InterruptedException( e.getMessage() );
		}
		catch (TimeoutException_Exception e) {
			throw new TimeoutException( e.getMessage() );
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Source#write(java.lang.Object)
	 */
	//@Override
	public void write(String data) throws InterruptedException {
		try {
			write(data, -1);
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
		
}
