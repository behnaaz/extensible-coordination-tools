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
package org.ect.reo.mcrl2.benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.swt.SWT;
import org.ect.reo.Connector;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2;
import org.ect.reo.mcrl2.util.ToolChain;
import org.ect.reo.prefs.ReoPreferences;
import org.ect.reo.semantics.ReoTextualSemantics;



public abstract class AbstractBenchmark implements IApplication {

	protected boolean stop = false;
	
	protected void printResults(String file, int start, List<Double> depth, List<Double> breadth, List<Double> none) throws IOException {
		
		File data = new File(file);
		BufferedWriter writer = new BufferedWriter(new FileWriter(data));
		int count = Math.max(none.size(), Math.max(depth.size(),breadth.size()));

		writer.write("# size\tnone\tbreadth-first\tdepth-first");
		writer.newLine();

		for (int i=0; i<count; i++) {
			writer.write("" + (i+start));
			if (i<none.size()) writer.write("\t" + (none.get(i)/1000)); else writer.write("\t\t");
			if (i<breadth.size()) writer.write("\t" + (breadth.get(i)/1000)); else writer.write("\t\t");
			if (i<depth.size()) writer.write("\t" + (depth.get(i)/1000)); else writer.write("\t\t");
			writer.newLine();
		}
		writer.close();

	}
	
	protected double benchmarkConnector(Connector connector, int tests) {
		String mcrl2Code = ReoTextualSemantics.computeConnectorSemantics(connector, Reo2MCRL2.SEMANTICS_KEY, new NullProgressMonitor());
		//System.out.println(mcrl2Code);
		File file = generateMCRL2File(mcrl2Code);
		double time = 0;
		for (int i=0; i<tests; i++) {
			time += performBenchmark(file);
			System.out.print(".");
		}
		time = time / tests;
		System.out.println("\nBenchmarks took " + (time / 1000) + " seconds on average (" + tests + " tests performed)\n");
		return time;
	}

	protected File generateMCRL2File(String content) {
		try {
			File mcrl2File = File.createTempFile("reo2mcrl2", ".mcrl2");
			FileWriter writer = new FileWriter(mcrl2File);
			writer.write(content);
			writer.close();
			return mcrl2File;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
	
	
	protected double performBenchmark(File mcrl2File) {
		try {
			String mcrl2 = mcrl2File.getAbsolutePath();
			mcrl2 = mcrl2.substring(0, mcrl2.length()-6);
			
			File lpsFile = new File(mcrl2 + ".lps");
			
			String path = ReoPreferences.getMCRL2Home() + File.separator + "bin" + File.separator;
			String suffix = SWT.getPlatform().startsWith("win") ? ".exe" : "";
			
			ToolChain chain1 = new ToolChain();
			chain1.setSleep(10);
			chain1.setPrintOutput(false);
			chain1.add(path + "mcrl22lps" + suffix, "-D", mcrl2File.getAbsolutePath(), lpsFile.getAbsolutePath());
			
			IStatus status = chain1.execute(new NullProgressMonitor(), true);

			//File ltsFile = new File(mcrl2 + ".aut");
			//ToolChain chain2 = new ToolChain();
			//chain2.add(path + "lps2lts" + suffix, lpsFile.getAbsolutePath(), ltsFile.getAbsolutePath());
			//chain2.add(path + "ltsgraph" + suffix, ltsFile.getAbsolutePath());			
			//chain2.execute(new NullProgressMonitor(), true);

			// Delete temporary files.
			if (status.getSeverity()==IStatus.OK) {
				lpsFile.delete();
			}
			
			return chain1.getExecutionTime();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		stop = true;
	}

}
