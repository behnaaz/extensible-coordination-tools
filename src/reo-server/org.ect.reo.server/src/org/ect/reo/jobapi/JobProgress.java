package org.ect.reo.jobapi;

import org.eclipse.core.runtime.IProgressMonitor;

public class JobProgress implements IProgressMonitor {
	
	private Job job;
	private int units;
	private double worked;
	
	public JobProgress(Job job) {
		this.job = job;
	}
	
	public void beginTask(String name, int units) {
		this.units = units;
		this.worked = 0;
		//this.job.logMessage(name);
	}
	
	public void done() {
		job.setProgress(100);
		job.finish();
	}
	
	public void internalWorked(double i) {
		double delta = i / ((double) units) * 100.0;
		worked += delta;
		job.setProgress((int) worked);
	}
	
	public boolean isCanceled() {
		return job.isCanceled();
	}
	
	public void setCanceled(boolean canceled) {
		if (canceled) job.cancel();
	}
	
	public void setTaskName(String name) {
		job.logMessage(name);
	}
	
	public void subTask(String name) {
		job.logMessage(name);
	}
	
	public void worked(int i) {
		internalWorked(i);
	}

}
