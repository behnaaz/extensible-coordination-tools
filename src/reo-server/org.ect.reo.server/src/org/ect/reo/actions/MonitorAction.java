package org.ect.reo.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ect.reo.jobapi.Job;
import org.ect.reo.jobapi.Scheduler;



public class MonitorAction extends HttpServlet {
	
	// Machine-generated serial id.
	private static final long serialVersionUID = 6779719689452510600L;
	
	public static final String START = "startMonitor";
	public static final String STOP = "stopMonitor";
	
	// PageHelper.
	private PageHelper helper;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.helper = new PageHelper(config);
	}
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.addHeader("Pragma", "no-cache");
		
		Scheduler scheduler = helper.getScheduler();
		PrintWriter out = response.getWriter();
				
		for (Job job : scheduler.getActiveJobs()) {
			
			if (job.isSilent()) continue;
			
			out.println("<div class=\"progresscontainer\">");
			out.println("<div class=\"progressbar\" style=\"width:" + job.getProgress() + "%\"></div> ");
			out.println("</div>");
			
			out.println("<div>" + job.getName() + "... (" + job.getProgress() + "%" + (job.isCanceled() ? ", canceled" : "") + ")</div>");
		}
		
		out.println("<script>");
		
		boolean stopUpdate = true;
		
		for (Job job : scheduler.getJobs()) {
			
			// Check for alerts
			if (job.getAlert()!=null) {
				out.println("alert(\"" + job.getAlert() + "\");");
				job.setAlert(null);
			}

			// Refresh pages if required.
			if (job.isFinished()) {
				for (String page : job.getRefresh()) {
					out.println(refresh(page));
				}
			} else {
				if (!job.isSilent()) stopUpdate = false;
			}
		}
		
		// The log is always refreshed.
		out.println(refresh(PageHelper.PAGE_LOG));
		
		// Stop the update if all jobs are either finished or silent.
		if (stopUpdate) {
			// The log should be continuously updated.
			out.println("if (page!='" + PageHelper.PAGE_LOG + "') { " + function(STOP) + "}");
		}
		
		out.println("</script>");
		
        // Remove the finished jobs.
        for (Job job : scheduler.getJobs()) {
        	if (job.isFinished()) scheduler.removeJob(job);
        }
        
	}
	
	private String function(String name) {
		return name + "();";
	}
	
	private String refresh(String page) {
		return "if (page=='" + page + "') {\n" + 
				function("refresh" + page.substring(0,1).toUpperCase() + page.substring(1) ) + 
				"}\n";
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
