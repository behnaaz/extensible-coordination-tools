package org.ect.reo.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ect.reo.Component;
import org.ect.reo.data.ComponentDescriptor;
import org.ect.reo.data.NetworkIdentifier;
import org.ect.reo.templates.BuildXml;
import org.ect.reo.templates.ComponentJava;
import org.ect.reo.util.ReoNames;



public class ClientImplAction extends HttpServlet {
	
	// Page name.
	public static final String PAGE = "clients";
	
	// Libraries.
	public static String LIBS = "/client/lib";
	public static String DOCS = "/client/doc";
	
	// Folders.
	public static String FOLDER_LIB = "lib";
	public static String FOLDER_SRC = "src";
	public static String FOLDER_DOC = "doc";
	
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 1334055234306546345L;
	
	
	// PageHelper.
	private PageHelper helper;
	
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.helper = new PageHelper(config);
	}
	
    
    @SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NetworkIdentifier identifier;
		Component component;
				
		try {
			identifier = NetworkIdentifier.parse(request.getPathInfo());
			component = helper.getWorkspace().getComponent(identifier);
		}
		catch (Throwable t) {
			throw new ServletException("Invalid component id.", t);
		}
		
		// Notify admins.
		if (notifyAdmin(request,component,identifier)==false) {
			throw new ServletException("Please enter correct data.");
		}
		
        // Create a component descriptor.
        ComponentDescriptor descriptor = new ComponentDescriptor(component, helper);

		String name = descriptor.componentName;
		String pack = descriptor.packageName;
		String root = name;
		
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=" + name + ".zip");
		
		// Create ZipFile and forward it.		
		
	    try {
	    	ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
	        InputStream in;
	        
	        // Add root, lib and src folder.	        
	        out.putNextEntry(new ZipEntry(root + "/"));
	        out.closeEntry();
	        out.putNextEntry(new ZipEntry(root + "/" + FOLDER_SRC + "/"));
	        out.closeEntry();
	        out.putNextEntry(new ZipEntry(root + "/" + FOLDER_LIB + "/"));
	        out.closeEntry();
	        out.putNextEntry(new ZipEntry(root + "/" + FOLDER_DOC + "/"));
	        out.closeEntry();
	        
	        // Add libraries and docs.
	        Set<String> libs = getServletContext().getResourcePaths(LIBS + "/");
	        Set<String> docs = getServletContext().getResourcePaths(DOCS + "/");
	        
	        for (String lib : libs) {
		        in = getServletContext().getResourceAsStream(lib);
		        out.putNextEntry(new ZipEntry(root + "/" + FOLDER_LIB + "/" + filename(lib)));
		        transfer(in, out);
	        }

	        for (String doc : docs) {
		        in = getServletContext().getResourceAsStream(doc);
		        out.putNextEntry(new ZipEntry(root + "/" + FOLDER_DOC + "/" + filename(doc)));
		        transfer(in, out);
	        }
	        
	        // Add cwi.ea.runtime library.
	        libs = getServletContext().getResourcePaths("/WEB-INF/lib");
	        for (String lib : libs) {
	        	if (lib.startsWith("/WEB-INF/lib/cwi.ea.runtime_")) {
			        in = getServletContext().getResourceAsStream(lib);
			        out.putNextEntry(new ZipEntry(root + "/" + FOLDER_LIB + "/ea-runtime.jar"));
			        transfer(in, out);
			        break;
	        	}
	        }
	        
	        // Create Java sources.
	        
	        // First create directory structure for the package.
	        StringTokenizer tokenizer = new StringTokenizer(pack, ".");
	        String current = root + "/" + FOLDER_SRC;
	        while (tokenizer.hasMoreTokens()) {
	        	current = current + "/" + tokenizer.nextToken();
		        out.putNextEntry(new ZipEntry(current + "/"));
		        out.closeEntry();
	        }
	        	        
	        // Create Java source.
	        in = new ByteArrayInputStream(new ComponentJava().generate(descriptor).getBytes());
	        out.putNextEntry(new ZipEntry(current + "/" + name + ".java"));
	        transfer(in, out);

	        // Create build.xml.
	        in = new ByteArrayInputStream(new BuildXml().generate(descriptor).getBytes());
	        out.putNextEntry(new ZipEntry(root + "/build.xml"));
	        transfer(in, out);
	        
	        out.close();
	    }
	    catch (IOException e) {
	    	throw new ServletException(e);
	    }
	    
    }
    
    
    private static String filename(String path) {
    	int index = path.lastIndexOf('/');
    	return (index>=0) ? path.substring(index+1) : path;
    }
    
    
	private static void transfer(InputStream in, ZipOutputStream out) throws IOException {

    	// Transfer bytes from the file to the ZIP file
	    byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        
        // Complete the entry
        out.closeEntry();
        in.close();
    }
	
	
	private boolean notifyAdmin(HttpServletRequest request, Component component, NetworkIdentifier identifier) {

		HttpSession session = request.getSession();
		if (adminNotified(request)) return true;

		String name = request.getParameter("name");
		String aff = request.getParameter("affiliation");
		if (name==null || aff==null || name.trim().equals("") || aff.trim().equals("")) return false;
				
		try {
		    // Execute a command with an argument that contains a space
		    String[] command = new String[]{"mail", "-s", "ReoLive download notification", "c.krause@cwi.nl"};		            
		    Process child = Runtime.getRuntime().exec(command);
		    OutputStreamWriter writer = new OutputStreamWriter(child.getOutputStream());
		    writer.write("Name:        " + name + "\n");
		    writer.write("Affiliation: " + aff + "\n");
		    writer.write("Component:   " + ReoNames.getName(component) + " " + identifier + "\n");
		    writer.flush();
		    writer.close();
		    
		} catch (IOException e) {
			System.err.println("Error sending notification: " + e);
		}
				
		session.setAttribute("notifiedAdmin", true);
		
		return true;
		
	}
	
	public static boolean adminNotified(HttpServletRequest request) {
		return request.getSession().getAttribute("notifiedAdmin")!=null;
	}
	
}
