package org.ect.reo.actions;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import cwi.reo.reconfiguration.ReconfigurationEngine;
//import cwi.reo.reconfiguration.ReconfigurationMap;
//import cwi.reo.reconfiguration.ReconfigurationMapFinder;
//import cwi.reo.reconfiguration.ReconfigurationRule;
//import cwi.reo.reconfiguration.util.ReconfigurationException;

public class ReconfigurationAction extends HttpServlet {
	
	// Action field name.
	public static final String ACTION = "reconfAction";
	
	// Network field name.
	public static final String NETWORK = "network";

	// Rule field name.
	public static final String RULE = "rule";
	
	// Reconfiguration actions.
	public static final String APPLY = "apply";
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 6736186230492776388L;
	
	// PageHelper.
	//private PageHelper helper;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//this.helper = new PageHelper(config);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*
		Workspace workspace = helper.getWorkspace();
		
		String action = request.getParameter(ACTION);
		NetworkIdentifier networkID;
		int ruleID;
		
		try {
			networkID = NetworkIdentifier.parse((request.getParameter(NETWORK)));
			ruleID = Integer.parseInt(request.getParameter(RULE));
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}

		ReconfigurationRule rule = workspace.getReconfigurationRules().get(ruleID);
		Network network = workspace.getNetwork(networkID);

		if (action==null || rule==null || network==null) return;
		
		if (action.equals(APPLY)) {
			Job job = helper.getScheduler().createJob("Applying rule " + rule.getName() + " to Network " + networkID + ".");
			job.setRefresh(PageHelper.PAGE_NETWORK);
			
			// Do the reconfiguration.
			boolean ok = applyRule(rule, network, job);
			
			if (!ok) {
				job.logError("Error performing reconfiguration.", null);
				job.finish();
				return;
			}
			
			network.getReconfigurationHistory().add(rule);
			
			// Reinitialize the network.
			CoordinatorThread<String> oldCoordinator = network.getCoordinatorThread();
			network.init(new JobProgress(job));
			
			// Now silently interrupt the old coordinator and start the new coordinator.
			if (oldCoordinator!=null && oldCoordinator.getStatus()==Status.RUNNING) {
				oldCoordinator.silentlyInterrupt();
				network.getCoordinatorThread().start( oldCoordinator.getJob() );
			}
			
			job.finish();
		}
		else {
			System.err.println("Illegal network action: '" + action + "'.");
		}
		*/
    }

	/*
	private boolean applyRule(ReconfigurationRule rule, Network network, Job job) {
		
		Module target = network.getConnectors().get(0).getModule();
		List<ReconfigurationMap> matches = ReconfigurationMapFinder.find(rule.getLHS(), target, true);
		
		if (matches.isEmpty()) {
			job.logError("No match found for rule " + rule.getName() + ".", null);
			return false;
		}
		
		for (ReconfigurationMap match : matches) {
			Network matched = new Network(match.getConnectors().values());
			if (EcoreUtil.equals(network, matched)) {
				
				ReconfigurationEngine engine = new ReconfigurationEngine();
				engine.setUpdateViews(false);
				
				try {
					engine.applyRuleInPlace(rule, match);
				} catch (ReconfigurationException e) {
					job.logError("Reconfiguration error", e);
					return false;
				}
				
				return true;
				
			}
			
		}
		
		job.logError("No match found inside of the network.", null);
		return false;
	}
	*/
}




