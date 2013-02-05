package org.ect.reo.jobapi;


public class Job implements Log {
	
	public final int MAX_PROGRESS = 100;
	
	private String name;
	private String[] refresh;
	
	private boolean canceled;
	private boolean finished;
	private boolean silent;
	private int progress;
	
	private Log log;
	private String alert;
	
	public Job(String name, Log log) {
		this.name = name;
		this.refresh = new String[0];
		this.finished = false;
		this.canceled = false;
		this.silent = false;
		this.progress = 0;
		this.log = log;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getRefresh() {
		return refresh;
	}
	
	public void setRefresh(String... refresh) {
		this.refresh = refresh==null ? new String[0] : refresh;		
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void finish() {
		this.finished = true;
	}
	
	public void finishWithError(String msg, Throwable t) {
		logError(msg, t);
		setAlert(msg);
		finish();
	}
	
	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		this.canceled = true;
	}
	
	public boolean isSilent() {
		return silent;
	}
	
	public void setSilent(boolean silent) {
		this.silent = silent;
	}
	
	public String getAlert() {
		return alert;
	}
	
	public void setAlert(String alert) {
		this.alert = alert;
	}
	
	public int getProgress() {
		if (finished) return 100;
		else return progress;
	}
	
	public void setProgress(int progress) {
		if (progress > MAX_PROGRESS) progress = MAX_PROGRESS;
		if (progress > this.progress) {
			this.progress = progress;
		}
	}
	
	public void logMessage(String msg) {
		if (log!=null) log.logMessage(msg);
	}
	
	public void logError(String msg, Throwable t) {
		if (log!=null) log.logError(msg, t);
	}
		
	public String printLog() {
		if (log!=null) return log.printLog();
		else return null;
	}
	
}
