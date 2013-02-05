package org.ect.reo.actions;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
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

import cwi.ea.automata.Automaton;
import cwi.ea.util.cacompat.CA;


public class Reo2EaAction extends HttpServlet {
		
	public static final String PAGE = "reo2ea";
	
	public static final String TYPE = "type";
	public static final String EA = "ea";
	public static final String CAT = "cat";
	
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
		
		try {
			identifier = NetworkIdentifier.parse(request.getPathInfo());
			network = helper.getWorkspace().getNetwork(identifier);
		}
		catch (Throwable t) {
			throw new ServletException("Invalid network identifier.", t);
		}
		
		InputStream in = null; 
    	String type = request.getParameter(TYPE);
    	
    	if (EA.equals(type)) {
    		File eaFile = network.getEAFile();
    		in = new BufferedInputStream(new FileInputStream(eaFile));
    		response.setContentType("application/xml");
    	}
    	else if (CAT.equals(type)) {
    		response.setContentType("text/plain");
    		in = ea2cat(network.getAutomaton());
    	}
		
		// Forward the file.
		if (in!=null) {
			OutputStream out = response.getOutputStream();
			IOUtils.copy(in, out);
			in.close();
			out.close();
		}
		
    }
	
    
    /**
     * Generate string representation of an automaton in the *.cat format.
     */
    public static InputStream ea2cat(Automaton automaton) {
    	String cat = automaton instanceof CA ? automaton.toString() : new CA(automaton).toString();
    	byte[] byteArray = cat.getBytes();
    	return new ByteArrayInputStream(byteArray); 
    }
}
