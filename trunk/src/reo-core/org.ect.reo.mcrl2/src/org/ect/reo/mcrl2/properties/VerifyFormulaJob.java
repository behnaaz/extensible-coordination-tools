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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.ect.reo.mcrl2.util.ToolChain;
import org.ect.reo.prefs.ReoPreferences;


/**
 * @generated NOT
 * @author Christian Krause
 *
 */
public class VerifyFormulaJob extends Job {
	
	public static final String NAME = "Verify Formula";
	
	// String properties.
	private String formula, mcrl2, message;
	
	// Shell to be used.
	private Shell shell;
	
	// Use CADP?
	private boolean useCADP = false;
	
	/**
	 * Default constructor.
	 * @param mcrl2 mCRL2 code.
	 * @param formula Formula to be checked.
	 * @param shell Shell.
	 */
	public VerifyFormulaJob(String mcrl2, String formula, Shell shell) {
		super(NAME);
		this.mcrl2 = mcrl2;
		this.formula = formula;
		this.shell = shell;
		setUser(true);
	}	
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		monitor.beginTask("Processing mCRL2 specification", 10);
		IStatus status = Status.OK_STATUS;
		try {
			
			// Write mCRL2 specification to a file.
			File mcrl2File = File.createTempFile("reo2mcrl2", ".mcrl2");
			FileWriter writer = new FileWriter(mcrl2File);
			writer.write(mcrl2);
			writer.close();
			
			String mcrl2 = mcrl2File.getAbsolutePath();
			mcrl2 = mcrl2.substring(0, mcrl2.length()-6);
			
			// Write formula to a file.
			File formulaFile = new File(mcrl2 + (useCADP ? ".mcl" : ".mcf"));
			writer = new FileWriter(formulaFile);
			writer.write(formula + "\n");
			writer.close();
			
			File lpsFile = new File(mcrl2 + ".lps");
			File lpsFile2 = new File(mcrl2 + "min.lps");
			File pbesFile = new File(mcrl2 + ".pbes");
			File autFile = new File(mcrl2 + ".aut");
			File bcgFile = new File(mcrl2 + ".bcg");
			
			String path = ReoPreferences.getMCRL2Home();
			if (path==null) path = ""; else path = path.trim();
			if (path.length()>0) {
				path = path + File.separator + "bin" + File.separator;				
			}
			String suffix = isWindows() ? ".exe" : "";
			monitor.worked(1);
			
			ToolChain chain = new ToolChain();
			chain.add(path + "mcrl22lps" + suffix, "-vD", mcrl2File.getAbsolutePath(), lpsFile.getAbsolutePath());
			chain.add(path + "lpssumelm" + suffix, lpsFile.getAbsolutePath(), lpsFile2.getAbsolutePath());			
			
			// Call the evaluator tool.
			if (useCADP) {
				try {
					File cadpBin = getCADPBin();
					chain.add(path + "lps2lts" + suffix, lpsFile2.getAbsolutePath(), autFile.getAbsolutePath());
					chain.add(cadpBin.getAbsolutePath() + File.separator + "bcg_io" + suffix,
							autFile.getAbsolutePath(), bcgFile.getAbsolutePath());
					
					String frm = formulaFile.getAbsolutePath();
					String bcg = bcgFile.getAbsolutePath();
					if (isWindows()) {
						if (frm.charAt(1)==':') frm = frm.substring(2);
						if (bcg.charAt(1)==':') bcg = bcg.substring(2);
						frm = frm.replace('\\', '/');
						bcg = bcg.replace('\\', '/');
						chain.add(cadpBin.getAbsolutePath() + File.separator + "evaluator" + suffix, frm, bcg);
					} else {
						chain.add(cadpBin.getParent() + File.separator + "com" + File.separator + "bcg_open" + suffix, bcg, "evaluator", frm);
					}
					
				} catch (FileNotFoundException e) {
					return new Status(IStatus.ERROR, "org.ect.reo.mcrl2", "Error using CADP.", e);
				}
			} else {
				chain.add(path + "lps2pbes" + suffix, "--formula=" + formulaFile.getAbsolutePath(), lpsFile2.getAbsolutePath(), pbesFile.getAbsolutePath());
				chain.add(path + "pbes2bool" + suffix, pbesFile.getAbsolutePath());				
			}
			
			// Now execute the tool chain...
			status = chain.execute(new SubProgressMonitor(monitor,9), true);
			message = chain.getLastOutput();
			final boolean result;
			
			// Evaluate the output.
			if (useCADP) {
				result = message.contains("TRUE");
				message = "CADP evaluated the formula to " + result;
			} else {
				String interesting = "The solution for the initial variable of the pbes is";
				if (message.indexOf(interesting)>0) message = message.substring(message.indexOf(interesting));
				result = message.trim().endsWith("true");				
			}
			
			// Print message.
			if (status.getSeverity()==IStatus.OK && shell!=null) {
				shell.getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (result) {
							MessageDialog.openInformation(shell, NAME, message);
						} else {
							MessageDialog.openWarning(shell, NAME, message);							
						}
					}
				});
			}
			
			// Delete temporary files.
			if (status.getSeverity()==IStatus.OK) {
				mcrl2File.delete();
				formulaFile.delete();
				pbesFile.delete();
				lpsFile.delete();
				lpsFile2.delete();
				autFile.delete();
				bcgFile.delete();
			}
			
		} catch (Exception e) {
			monitor.done();
			return new Status(IStatus.ERROR, "org.ect.reo.mcrl2", "Error checking formula.", e);
		}
		monitor.done();
		return status;
		
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setUseCADP(boolean useCADP) {
		this.useCADP = useCADP;
	}
	
	/**
	 * Get the CADP 'bin.*' directory.
	 * @return The directory.
	 */
	public static File getCADPBin() throws FileNotFoundException {
		String path = System.getenv("CADP");
		if (path==null) {
			throw new FileNotFoundException("CADP environment variable not set.");
		}
		File cadp = new File(path);
		if (!cadp.isDirectory()) {
			throw new FileNotFoundException("CADP home not found. Check the CADP environment variable.");
		}
		File[] bin = cadp.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith("bin");
			}
		});
		if (bin.length==0) {
			throw new FileNotFoundException("Cannot find 'bin' directory in CADP home.");
		}
		return bin[0];
	}
	
	private boolean isWindows() {
		return SWT.getPlatform().startsWith("win");
	}
	
}
