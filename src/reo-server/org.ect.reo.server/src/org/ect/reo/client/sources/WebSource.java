package org.ect.reo.client.sources;

import java.util.concurrent.TimeoutException;

import javax.xml.ws.BindingProvider;

import org.ect.reo.client.sources.jaxws.InterruptedException_Exception;
import org.ect.reo.client.sources.jaxws.SourceEndpoint;
import org.ect.reo.client.sources.jaxws.SourceEndpointService;
import org.ect.reo.client.sources.jaxws.TimeoutException_Exception;

import cwi.ea.runtime.Source;


/**
 * Wrapper for SourceEndpoints. Uses a port ID to instantiate
 * the corresponding web service and forwards I/O operations 
 * to this web service.
 * 
 * @author Christian Koehler
 *
 */
public class WebSource implements Source<String> {
	
	// The wrapped SourceEndpoint instance.
	protected SourceEndpoint endpoint;
	
	/**
	 * Default constructor. 
	 * @param url URL of the SourceEndpoint web service including the port ID.
	 */
	public WebSource(String url) {
		
		// Create SourceEndpoint instance.
		SourceEndpointService service = new SourceEndpointService();
		endpoint = service.getSourceEndpointPort();
		
		// Update the request context with the new URL.
		((BindingProvider) endpoint).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Sink#take(long)
	 */
	// @Override
	public String take(long nanos) throws InterruptedException, TimeoutException {
		try {
			return endpoint.take(nanos);
		}
		catch (InterruptedException_Exception e) {
			throw new InterruptedException(e.getMessage() );
		}
		catch (TimeoutException_Exception e) {
			throw new TimeoutException( e.getMessage() );
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see cwi.ea.runtime.Sink#take()
	 */
	// @Override
	public String take() throws InterruptedException {
		try {
			return take(-1);
		} catch (TimeoutException e) {
			e.printStackTrace();
			return null;
		}
	}

}
