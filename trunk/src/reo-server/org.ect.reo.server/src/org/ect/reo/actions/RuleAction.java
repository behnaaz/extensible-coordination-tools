package org.ect.reo.actions;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RuleAction  extends HttpServlet {
	
	// Action field name.
	public static final String ACTION = "rulesAction";
	
	// Rules field name.
	public static final String RULE = "rule";
	
	// Rule actions.
	public static final String REMOVE = "remove";
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 2102636230992746384L;
	
	// PageHelper.
	//private PageHelper helper;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//this.helper = new PageHelper(config);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String action = (String) request.getParameter(ACTION);
		if (action==null) return;
		/*		
		Workspace workspace = helper.getWorkspace();
		List<ReconfigurationRule> selected = getSelectedRules(request);
		if (selected.isEmpty()) return;
		
		for (ReconfigurationRule rule : selected) {
			
			Job job = null;
			if (action.equals(REMOVE)) {
				job = helper.getScheduler().createJob("Removing reconfiguration rule " + rule.getName() + ".");
				workspace.getReconfigurationRules().remove(rule);
			}
						
			else {
				System.err.println("Illegal rule action: '" + action + "'.");
			}
			
			if (job!=null) {
				job.setRefresh(PageHelper.PAGE_RULES);
				job.finish();
			}
			
		}
		*/
		
    }
	
	
	
	/**
	 * Get the rules selected in the rule list.
	 * @param request Request.
	 * @return List of rules.
	 */
	/*protected List<ReconfigurationRule> getSelectedRules(HttpServletRequest request) {
		
		List<ReconfigurationRule> selected = new ArrayList<ReconfigurationRule>();
		Enumeration<?> params = request.getParameterNames();
		
		while (params.hasMoreElements()) {
			String param = (String) params.nextElement();
			int id = PageHelper.parseIntIndex(param, RULE);
			if (id>=0) {
				boolean enabled = Boolean.parseBoolean(request.getParameter(param));
				ReconfigurationRule rule = helper.getWorkspace().getReconfigurationRules().get(id);
				if (enabled && rule!=null) selected.add(rule);
			}
		}
		return selected;
	}
	*/	
}
