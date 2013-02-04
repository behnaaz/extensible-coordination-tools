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
import org.eclipse.swt.SWT;
import org.ect.reo.mcrl2.util.ToolChain;
import org.ect.reo.prefs.ReoPreferences;

/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class SimulateJob extends Job {

    private String data;
    private boolean useCADP;
    @SuppressWarnings("unused")
	private String formula;
   
    public SimulateJob(String data, String formula) {
        super("Simulate");
        this.data = data;
        this.formula = formula;
        setUser(true);
    }
   
    public void setUseCADP(boolean useCADP) {
        this.useCADP = useCADP;
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
            File autFile = new File(mcrl2 + ".aut");
            File bcgFile = new File(mcrl2 + ".bcg");
           
           
           
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
           
            // Call the evaluator tool.
            if (useCADP) {
                try {
                    File cadpBin = getCADPBin();
                    chain.add(path + "lps2lts" + suffix, lpsFile2.getAbsolutePath(), autFile.getAbsolutePath());
                    chain.add(cadpBin.getAbsolutePath() + File.separator + "bcg_io" + suffix,
                            autFile.getAbsolutePath(), bcgFile.getAbsolutePath());
                   
                    String bcg = bcgFile.getAbsolutePath();
                    if (isWindows()) {
                        if (bcg.charAt(1)==':') bcg = bcg.substring(2);
                        bcg = bcg.replace('\\', '/');
                        chain.add(cadpBin.getAbsolutePath() + File.separator + "ocis" + suffix, bcg);
                    } else {
                        chain.add(cadpBin.getParent() + File.separator + "com" + File.separator + "bcg_open" + suffix, bcg, "ocis");
                    }
                   
                } catch (FileNotFoundException e) {
                    return new Status(IStatus.ERROR, "org.ect.reo.mcrl2", "Error using CADP.", e);
                }
            }
            else {
                chain.add(path + ((isWindows())?"lpsxsim":"lpssim") + suffix, lpsFile2.getAbsolutePath());
            }
           
            status = chain.execute(new SubProgressMonitor(monitor,9), false);
            // Delete temporary files.
            if (status.getSeverity()==IStatus.OK) {
                mcrl2File.delete();
                lpsFile.delete();
                autFile.delete();
            }
           
        } catch (Exception e) {
            monitor.done();
            return new Status(IStatus.ERROR, "org.ect.reo.mcrl2", "Error while converting or opening mCRL2 file.", e);
        }
        monitor.done();
        return status;
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