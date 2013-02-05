package org.ect.reo.jobapi;

import org.eclipse.core.runtime.IProgressMonitor;

public interface JobRunnable {
	
	public void run(IProgressMonitor monitor, Log log);
	
}
