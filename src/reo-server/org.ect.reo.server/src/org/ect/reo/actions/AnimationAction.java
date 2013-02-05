package org.ect.reo.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.ect.reo.data.Network;
import org.ect.reo.data.NetworkIdentifier;



public class AnimationAction extends HttpServlet {
	
	public static final String PAGE = "animations";
	
	// Content type parameter.
	public static final String INDEX = "anim";
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 4634055264306516744L;

	
	// PageHelper.
	private PageHelper helper;
	
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.helper = new PageHelper(config);
	}
	
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NetworkIdentifier identifier;
		Network network;
		int index = 0;
		
		try {
			identifier = NetworkIdentifier.parse(request.getPathInfo());
			network = helper.getWorkspace().getNetwork(identifier);
			index = getAnimationIndex(request);
		}
		catch (Throwable t) {
			throw new ServletException("Invalid animation request.", t);
		}
		
		File swf = network.getSWF(index);
		response.setContentType("application/x-shockwave-flash");
		
		// Forward the file.
		InputStream in = new BufferedInputStream(new FileInputStream(swf));
		OutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		in.close();
		out.close();
				
    }
	
    
    public static int getAnimationIndex(HttpServletRequest request) {
		int index = 0;
    	if (request.getParameter(INDEX)!=null) {
			index = Integer.parseInt(request.getParameter(INDEX));
		}
    	return index;
    }
    
}
