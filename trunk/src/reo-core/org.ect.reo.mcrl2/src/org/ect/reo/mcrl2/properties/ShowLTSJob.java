/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.reo.mcrl2.properties;

import java.io.File;
import java.io.FileWriter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.ect.reo.mcrl2.util.ToolChain;
import org.ect.reo.prefs.ReoPreferences;


/**
 * @generated NOT
 * @author Christian Krause
 *
 */
public class ShowLTSJob extends Job {

	private String data;

	public ShowLTSJob(String data) {
		super("Show LTS");
		this.data = data;
		setUser(true);
	}	
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		monitor.beginTask("Processing mCRL2 specification", 10);
		IStatus status = Status.OK_STATUS;
		try {
			File mcrl2File = File.createTempFile("reo2mcrl2", ".mcrl2");
			FileWriter writer = new FileWriter(mcrl2File);
			writer.write(data);
			writer.close();
			
			String mcrl2 = mcrl2File.getAbsolutePath();
			mcrl2 = mcrl2.substring(0, mcrl2.length()-6);
			
			File lpsFile = new File(mcrl2 + ".lps");
			File lpsFile2 = new File(mcrl2 + "min.lps");
			File ltsFile = new File(mcrl2 + ".aut");
			File ltsFile2 = new File(mcrl2 + "min.aut");
			
			String path = ReoPreferences.getMCRL2Home();
			if (path==null) path = ""; else path = path.trim();
			if (path.length()>0) {
				path = path + File.separator + "bin" + File.separator;				
			}
			String suffix = SWT.getPlatform().startsWith("win") ? ".exe" : "";
			monitor.worked(1);
			
			ToolChain chain = new ToolChain();
			chain.add(path + "mcrl22lps" + suffix, "-vD", mcrl2File.getAbsolutePath(), lpsFile.getAbsolutePath());
			chain.add(path + "lpssumelm" + suffix, lpsFile.getAbsolutePath(), lpsFile2.getAbsolutePath());
			chain.add(path + "lps2lts" + suffix, lpsFile2.getAbsolutePath(), ltsFile.getAbsolutePath());
			chain.add(path + "ltsconvert" + suffix, "-v", "--equivalence=branching-bisim", ltsFile.getAbsolutePath(), ltsFile2.getAbsolutePath());
			chain.add(path + "ltsgraph" + suffix, ltsFile2.getAbsolutePath());
			
			status = chain.execute(new SubProgressMonitor(monitor,9), false);
			
			// Delete temporary files.
			if (status.getSeverity()==IStatus.OK) {
			///	mcrl2File.delete();
			//	lpsFile.delete();
			//	lpsFile2.delete();
			//TODO	ltsFile.delete();
			}
			
		} catch (Exception e) {
			monitor.done();
			return new Status(IStatus.ERROR, "org.ect.reo.mcrl2", "Error while converting or opening mCRL2 file.", e);
		}
		monitor.done();
		return status;
	}

}
