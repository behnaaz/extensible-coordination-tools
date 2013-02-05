package org.ect.reo.actions;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ect.reo.Component;
import org.ect.reo.data.Network;
import org.ect.reo.data.Workspace;
import org.ect.reo.data.CoordinatorThread.Status;
import org.ect.reo.jobapi.Job;



public class ChangeNetworkIDAction  extends HttpServlet {
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 2104686230992246384L;
	
	// PageHelper.
	private PageHelper helper;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.helper = new PageHelper(config);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		Job job = helper.getScheduler().createJob("Changing network IDs...");
		job.setRefresh(PageHelper.PAGE_NETWORKS);
		
		int fromID = 0, toID = 0;
		
		try {
			fromID = Integer.parseInt((String) request.getParameter("from"));
			toID = Integer.parseInt((String) request.getParameter("to"));
		} catch (Throwable t) {
			job.finishWithError("Illegal network IDs.", t);
			return;
		}
		
		Workspace workspace = helper.getWorkspace();
		Network from = workspace.getNetwork(fromID);
		Network to = workspace.getNetwork(toID);
		
		if (from==null) {
			job.finishWithError("Unknown network ID: " + fromID, null);
			return;
		}
		
		if (to!=null) {
			
			boolean compatible = true;
			
			// Check if they have the same number of components.
			if (from.getComponents().size()!=to.getComponents().size()) {
				compatible = false;
			}
			// Check the ports of the components.
			if (compatible) {
				for (int i=0; i<from.getComponents().size(); i++) {
					Component c1 = from.getComponents().get(i);
					Component c2 = to.getComponents().get(i);
					if (c1.getSourceEnds().size()!=c2.getSourceEnds().size()) compatible = false;
					if (c1.getSinkEnds().size()!=c2.getSinkEnds().size()) compatible = false;
				}
			}
			
			if (!compatible) {
				job.finishWithError(from + " and " + to + " are not compatible.", null);
				return;
			}
			
			if (from.getCoordinatorThread().getStatus()!=to.getCoordinatorThread().getStatus()) {
				job.finishWithError(from + " and " + to + " must have the same status.", null);
				return;
			}
			
			// Ok, here we go.
			
			// Switch the IDs first.
			workspace.setNetworkId(from, toID);
			workspace.setNetworkId(to, fromID);
			
			// Now silently interrupt both coordinators.
			if (from.getCoordinatorThread().getStatus()==Status.RUNNING) {
				from.getCoordinatorThread().silentlyInterrupt();
				to.getCoordinatorThread().silentlyInterrupt();
			}
			
			// That's it.
			
		} else {
			
			// Just change the ID.
			workspace.setNetworkId(from, toID);
		}
		
		job.finish();
		
    }
	
}
