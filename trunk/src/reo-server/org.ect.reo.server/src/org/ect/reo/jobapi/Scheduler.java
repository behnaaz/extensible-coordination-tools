package org.ect.reo.jobapi;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Scheduler {
	
	private Log log;
	private Set<Job> jobs;
	
	public Scheduler() {
		this.log = new HTMLLog(100);
		this.jobs = new LinkedHashSet<Job>();
	}
	
	/**
	 * Create a new job.
	 * @param name Name of the job.
	 * @return The newly created job.
	 */
	public synchronized Job createJob(String name) {
		Job job = new Job(name, log);
		job.logMessage(name);
		jobs.add(job);
		return job;
	}
	
	/**
	 * Run a job.
	 * @param runnable Runnable to be executed.
	 * @param job Job associated to this runnable.
	 * @param refresh Pages to be refreshed when this job is finished.
	 */
	public synchronized void runAsyncJob(String name, final JobRunnable runnable, String... refresh) {
		
		if (runnable==null) return;
		
		final Job job = createJob(name);
		job.setRefresh(refresh);
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					runnable.run(new JobProgress(job), log);
				} catch (Throwable t) {
					job.logError("Error executing job.", t);
				} finally {
					job.finish();
				}
			}
		});
		thread.start();
	}
	
	/**
	 * Remove a job.
	 * @param job Job to be removed.
	 */
	public synchronized void removeJob(Job job) {
		jobs.remove(job);
	}
	
	
	/**
	 * Get all registered jobs.
	 * @return List of jobs.
	 */
	public synchronized Job[] getJobs() {
		Job[] result = new Job[jobs.size()];
		return jobs.toArray(result);
	}
	
	/**
	 * Get all active jobs.
	 * @return List of active jobs.
	 */
	public synchronized Job[] getActiveJobs() {
		List<Job> active = new ArrayList<Job>();
		for (Job job : jobs) {
			if (!job.isFinished()) active.add(job);
		}
		Job[] result = new Job[active.size()];
		return active.toArray(result);
	}
	
	/**
	 * Get all silent jobs.
	 * @return List of silent jobs.
	 */
	public synchronized Job[] getSilentJobs() {
		List<Job> silent = new ArrayList<Job>();
		for (Job job : jobs) {
			if (job.isSilent()) silent.add(job);
		}
		Job[] result = new Job[silent.size()];
		return silent.toArray(result);
	}

	/**
	 * Print the common log of this scheduler.
	 * @return The log.
	 */
	public String printLog() {
		return log.printLog();
	}
	
	/**
	 * Get the log.
	 * @return The log.
	 * @deprecated Logs should be only accessed inside of jobs.
	 */
	public Log getLog() {
		return log;
	}
}
