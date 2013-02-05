package cwi.yawl.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.ObjectInvoker;
import org.codehaus.xfire.transport.http.XFireServlet;


public class SOAPServlet extends XFireServlet {

	// Machine-generated serial id.
	private static final long serialVersionUID = -212028867700153350L;
	
	// Service factory.
	private ObjectServiceFactory factory;
	
	// Registered services.
	private HashMap<String,Service> services = new HashMap<String, Service>();
	
	@Override
	public void init() throws ServletException {
		super.init();
		if (factory==null) {
			factory = new ObjectServiceFactory(getXFire().getTransportManager(), null);
			factory.addIgnoredMethods("java.lang.Comparable");
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateServices(request);
		super.doGet(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateServices(request);
		super.doPost(request, response);
	}
	
	
	private String updateServices(HttpServletRequest request) throws ServletException {
	
		// Find out the name of the requested service.
		String uri = request.getRequestURI();
		String name = uri.substring(uri.lastIndexOf('/')+1);
		if (name.indexOf('?')>=0) {
			name = name.substring(0, name.indexOf('?')-1);
		}
		
		// Create a new service if it wasn't found.
		if (name.length()!=0 && !services.containsKey(name)) {
			Service service = factory.create(cwi.reo.services.Service.class, name, null, null);
			service.setProperty(ObjectInvoker.SERVICE_IMPL_CLASS, SOAPService.class);
			getController().getServiceRegistry().register(service);
			SOAPService executer = (SOAPService) service.getExecutor();
			executer.setName(name);
		}
		
		return name;
	
	}
	
}