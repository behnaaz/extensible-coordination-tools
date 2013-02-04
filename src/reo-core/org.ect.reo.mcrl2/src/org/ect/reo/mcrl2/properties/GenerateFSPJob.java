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

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.ect.reo.aut2fsp.AUT2FSP;
import org.ect.reo.mcrl2.hideassistant.HideActionAssistant;
import org.ect.reo.mcrl2.util.ToolChain;
import org.ect.reo.prefs.ReoPreferences;

/**
 * @generated NOT
 * @author Behnaz Changizi
 */
public class GenerateFSPJob extends Job {
	private String data;
	@SuppressWarnings("unused")
	private String formula;

	/**
	 * Shows a dialog to check the actions to be hidden in the mCRL2
	 * specification and saves the result of hide in the local variable data.
	 * 
	 * @param data
	 *            the original mCRL2 specification
	 * @param formula
	 *            the formulae to be check on the specification
	 */
	public GenerateFSPJob(String data, String formula) {
		super("Generate FPS");
		// Show Dialog to choose actions to hide (Unhiding the actions which are
		// by defualt hidden)
		HideActionAssistant haa = new HideActionAssistant(data);
		data = haa.getHideResult();
		this.data = data;
		this.formula = formula;
		setUser(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		File mcrl2File = null;
		File lpsFile = null;
		File lpsFile2 = null;
		File autFile = null;
		monitor.beginTask("Generating FPS specification", 10);

		IStatus status = Status.OK_STATUS;
		try {
			mcrl2File = File.createTempFile("reo2mcrl2Refined", ".mcrl2");
			FileWriter writer = new FileWriter(mcrl2File);
			writer.write(data);
			writer.close();

			String mcrl2 = mcrl2File.getAbsolutePath();
			mcrl2 = mcrl2.substring(0, mcrl2.length() - 6);

			lpsFile = new File(mcrl2 + ".lps");
			lpsFile2 = new File(mcrl2 + "min.lps");
			autFile = new File(mcrl2 + ".aut");

			String path = ReoPreferences.getMCRL2Home();
			if (path == null)
				path = "";
			else
				path = path.trim();
			if (path.length() > 0) {
				path = path + File.separator + "bin" + File.separator;
			}
			String suffix = SWT.getPlatform().startsWith("win") ? ".exe" : "";
			monitor.worked(1);

			ToolChain chain = new ToolChain();
			chain.add(path + "mcrl22lps" + suffix, "-vD",
					mcrl2File.getAbsolutePath(), lpsFile.getAbsolutePath());
			chain.add(path + "lpssumelm" + suffix, lpsFile.getAbsolutePath(),
					lpsFile2.getAbsolutePath());

			chain.add(path + "lps2lts" + suffix, lpsFile2.getAbsolutePath(),
					autFile.getAbsolutePath());

			status = chain.execute(new SubProgressMonitor(monitor, 5), true);

		} catch (Exception e) {
			monitor.done();
			return new Status(IStatus.ERROR, "org.ect.reo.mcrl2",
					"Error while converting or opening mCRL2 file.", e);
		}
		monitor.done();

		if (status.getSeverity() == IStatus.OK) {
			generateFSP(autFile);
			// Delete temporary files.
			mcrl2File.delete();
			lpsFile.delete();
			// autFile.delete();
		}
		return status;
	}

	@SuppressWarnings("deprecation")
	private void generateFSP(File autFile) {
		FileWriter writer;
		FileInputStream fis;
		try {
			fis = new FileInputStream(autFile);

			// Here BufferedInputStream is added for fast reading.
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);

			try {
				// dis.available() returns 0 if the file does not have more
				// lines.
				while (dis.available() != 0) {

					// this statement reads the line from the file and print it
					// to
					// the console.
					System.out.println(dis.readLine());
				}
				dis.close();
			} catch (NullPointerException ex) {
			}
			// Convert to FSP for analyzing with LTSA
			AUT2FSP aut2fsp = new AUT2FSP();
			ArrayList<String> fsp = aut2fsp.convert(autFile.getAbsolutePath());
			String fspdesc = "";
			for (String s : fsp)
				fspdesc += s;

			// TODO:to de removed
			File fspFile = File.createTempFile("reo2mcrl2fsp", ".lts");
			writer = new FileWriter(fspFile);
			writer.write(fspdesc);
			System.out.println("Generated fsp from: "
					+ autFile.getAbsolutePath() + ":" + fspdesc);
			System.out.println("FSP specification: " + fspdesc);

			writer.close();

			// Hide action not appeared in the formula (the actions which are by
			// default nonhidden)
			//FSPPropertyBasedReducer pbr = new FSPPropertyBasedReducer();
			String redfspdesc = fspdesc;// ///////TODO
										// pbr.hideOtherActions(fspdesc,
										// this.formula);

			File redfspFile = File
					.createTempFile("reo2mcrl2fspReduced", ".lts");
			writer = new FileWriter(redfspFile);
			writer.write(redfspdesc);
			System.out.println("Generated REDUCED fsp from: "
					+ autFile.getAbsolutePath() + ":" + fspdesc);
			System.out.println("FSP REDUCED specification: " + redfspFile);

			writer.close();

			// Invoking LTSA
			String command = "java -jar " + ReoPreferences.getLTSA() + " "
					+ redfspFile.getAbsolutePath();
			System.out.println("Invoking " + command);

			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
