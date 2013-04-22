package org.ect.codegen.v2.core.rt.java.examples;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(serviceName = "incrementService", portName = "incrementEndpoint")
public class IncService {

	//
	// FIELDS
	//

	private int increment = 1;

	//
	// METHODS
	//

	@WebMethod(operationName="increment")
	public int inc(@WebParam(name = "value") final int value) {
		System.out.println("inc(" + value + ")=" + (value + increment));
		return value + increment;
	}

	@WebMethod(operationName="setIncrement")
	public void setInc(@WebParam(name = "value") final int value) {
		System.out.println("setInc(" + value + ")");
		this.increment = value;
	}

	//
	// MAIN
	//

	public static void main(final String[] args) {
		Logger.getLogger("javax.enterprise.resource.webservices.jaxws.server")
				.setLevel(Level.OFF);

		final String serviceName = "incrementService";
		final String address = "http://localhost:8080/" + serviceName;
		Endpoint.publish(address, new IncService());
		System.out.println(address);
	}
}
