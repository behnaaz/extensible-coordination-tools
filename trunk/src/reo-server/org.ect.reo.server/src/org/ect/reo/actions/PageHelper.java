package org.ect.reo.actions;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.eclipse.emf.ecore.EObject;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.data.Application;
import org.ect.reo.data.NetworkIdentifier;
import org.ect.reo.data.Workspace;
import org.ect.reo.jobapi.Scheduler;



public class PageHelper {
	
	// Page names.
	public static final String PAGE_NETWORKS = "networks";
	public static final String PAGE_NETWORK = "network";
	public static final String PAGE_COMPONENTS = "components";
	public static final String PAGE_CONNECTORS = "connectors";
	public static final String PAGE_RULES = "rules";
	public static final String PAGE_LOG = "log";
	
	// Application attributes.
	public static final String ATTR_SCHEDULER = "scheduler";
	public static final String ATTR_WORKSPACE = "workspace";
		
	// Servlet configuration and context.
	private ServletConfig config;
	private ServletContext context;
	
	
	/**
	 * Default constructor.
	 * @param config ServletConfig.
	 */
    public PageHelper(ServletContext context) {
    	
    	this.context = context;
    	
    	// Initialize scheduler and workspace.
    	if (getScheduler()==null || getWorkspace()==null) {
    		
    		Application application = new Application(context);
    		context.setAttribute(ATTR_SCHEDULER, application.getScheduler());
    		context.setAttribute(ATTR_WORKSPACE, application.getWorkspace());
    	}
    	    	
	}
    
    
	/**
	 * Another constructor.
	 * @param config ServletConfig.
	 */
    public PageHelper(ServletConfig config) {
    	this(config.getServletContext());
    	this.config= config;
	}

    /**
     * Returns the application name, e.g. "reo-live".
     * @return The application name.
     */
    public String getApplicationName() {
    	return getServletContext().getInitParameter("application");
    }

    /**
     * Returns the display name, e.g. "ReoLive!".
     * @return The display name.
     */
    public String getDisplayName() {
    	return getServletContext().getServletContextName();
    }
    
    /**
     * Returns the URL of the Reo Trac website. Should be
     * something like <i>http://reo.project.cwi.nl/cgi-bin/trac.cgi/reo</i>.
     * @return The Reo Trac website URL.
     */
    public String getTracHome() {
    	return getServletContext().getInitParameter("trac");
    }
    
    /**
     * Returns the name of the current page, e.g. "NetworkList", "ConnectorList" etc.
     * This should correspond to a JSP in the <i>pages</i> folder.
     * @return The current page name.
     */
    public String getPageName() {
    	return getServletConfig().getInitParameter("page");
    }
    
    /**
     * Get the title of the current page, e.g. "Network List", "Connector List" etc.
     * @return The title of the current page.
     */
    public String getTitle() {
    	return getServletConfig().getInitParameter("title");
    }
    
    
    // *** Application attributes. *** //
    
    public Scheduler getScheduler() {
    	return (Scheduler) getServletContext().getAttribute(ATTR_SCHEDULER);
    }
    
    public Workspace getWorkspace() {
    	return (Workspace) getServletContext().getAttribute(ATTR_WORKSPACE);
    }
    
    public ServletConfig getServletConfig() {
    	return config;
    }
    
    public ServletContext getServletContext() {
    	return context;
    }
    
    
    /**
	 * Helper method. Parses an integer index of a parameter.
	 */
	public static int parseIntIndex(String param, String name) {
		try {
			MessageFormat format = new MessageFormat(name + "[{0,number,integer}]");
			Object[] parsed = format.parse(param);
			return ((Long) parsed[0]).intValue();
		} catch (ParseException e) {
			return -1;
		}		
	}
	
	/**
	 * Helper method. Print an error message.
	 * @param message Error message.
	 */
	public static String errorMessage(String message, Throwable t) {
		StringBuffer out = new StringBuffer();
		out.append("<div class=\"system-message\">\n");
		out.append("<b>" + message + "</b><br>");
		if (t!=null) {
			out.append("<pre>\n");
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			t.printStackTrace(new PrintWriter(buffer));
			out.append(buffer.toString());
			out.append("</pre>\n");
		}
		out.append("</div>\n");
		return out.toString();
	}
	
	
	/**
	 * Helper method. Formats a network identifier as a hyper-link to
	 * the corresponding page that contains the details for this element.
	 * @param identifier Network identifier.
	 * @return The link as a simple string.
	 */
    public String elementLink(NetworkIdentifier identifier) {
    	
    	String name = getWorkspace().getResolvedName(identifier);
    	String servlet = null;
    	
    	EObject target = getWorkspace().resolve(identifier);
    	if (target instanceof Network) servlet = PAGE_NETWORKS;
    	if (target instanceof Component) servlet = PAGE_COMPONENTS;
    	if (target instanceof Connector) servlet = PAGE_CONNECTORS;
    	
    	if (servlet!=null) {
    		String app = getApplicationName();
    		String link = '/' + app + '/' + servlet+'/' + identifier;
        	return "<a href=\"" + link + "\">" + name + "</a>";
    	} else {
    		return name;
    	}
    	
    }

}
