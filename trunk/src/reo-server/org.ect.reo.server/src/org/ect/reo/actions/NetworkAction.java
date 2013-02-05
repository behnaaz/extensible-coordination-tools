package org.ect.reo.actions;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ect.reo.data.Network;
import org.ect.reo.data.Workspace;
import org.ect.reo.jobapi.Job;



public class NetworkAction extends HttpServlet {
	
	// Action field name.
	public static final String ACTION = "networksAction";
	
	// Networks field name.
	public static final String NETWORK = "network";
	
	// Network actions.
	public static final String START = "start";
	public static final String STOP = "stop";
	public static final String REMOVE = "remove";
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 3206686230992746384L;
	
	// PageHelper.
	private PageHelper helper;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.helper = new PageHelper(config);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String action = (String) request.getParameter(ACTION);
		if (action==null) return;
				
		Workspace workspace = helper.getWorkspace();
		List<Network> selected = getSelectedNetworks(request);
		if (selected.isEmpty()) return;
		
		for (Network network : selected) {
			
			int id = workspace.getId(network);
			
			Job job = null;
			if (action.equals(REMOVE)) {
				job = helper.getScheduler().createJob("Removing network " + id + ".");
				workspace.removeNetwork(network);
			}
			
			else if (action.equals(START)) {
				job = helper.getScheduler().createJob("Starting network " + id + "...");
				
				Job run = helper.getScheduler().createJob("Running network " + id + "...");
				run.setSilent(true);
				
				network.getCoordinatorThread().start(run);
			}
			
			else if (action.equals(STOP)) {
				job = helper.getScheduler().createJob("Stopping network " + id + ".");
				network.getCoordinatorThread().stop();
			}
			
			else {
				System.err.println("Illegal network action: '" + action + "'.");
			}
			
			if (job!=null) {
				job.setRefresh(PageHelper.PAGE_NETWORKS);
				job.finish();
			}
			
		}
		
    }
	
	
	
	/**
	 * Get the networks selected in the network list.
	 * @param request Request.
	 * @return List of networks.
	 */
	protected List<Network> getSelectedNetworks(HttpServletRequest request) {
		
		List<Network> selected = new Vector<Network>();
		Enumeration<?> params = request.getParameterNames();
		
		while (params.hasMoreElements()) {
			String param = (String) params.nextElement();
			int id = PageHelper.parseIntIndex(param, NETWORK);
			if (id>=0) {
				boolean enabled = Boolean.parseBoolean(request.getParameter(param));
				Network network = helper.getWorkspace().getNetwork(id);
				if (enabled && network!=null) selected.add(network);
			}
		}
		return selected;
	}
	
}
