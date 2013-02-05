package org.ect.reo.data;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.actions.Reo2EaAction;
import org.ect.reo.jobapi.Job;

import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.runtime.TransactionalIO;
import org.ect.ea.runtime.interpreter.ExecutableCA;
import org.ect.ea.runtime.interpreter.XCADriver;


public class CoordinatorThread<T> {
	
	// Coordinator status enum.
	public static enum Status {
		RUNNING, STOPPED;
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	// Status of the coordinator.
	private Status status = Status.STOPPED;
	
	// Ports.
	private Map<String,TransactionalIO> ports;
	private Map<String,PortWrapper<T>> wrappers;
	
	// Thread where the coordinator is executed in.
	private Thread thread;
	
	// Network to be executed.
	private Network network;
	
	// Coordinator job.
	private Job job;
	
	
	/**
	 * Default constructor.
	 * @param network The network to be executed.
	 */
	public CoordinatorThread(Network network) {
		this.network = network;
		this.ports = new HashMap<String,TransactionalIO>();
		this.wrappers = new HashMap<String,PortWrapper<T>>();
	}
	
	
	/**
	 * Start the coordinator thread.
	 */
	public synchronized void start(Job job) {
		
		// Check if thread is already running.
		if (status==Status.RUNNING) return;
		this.job = job;
		
		// Initialize ports.
		ports.clear();
		Automaton automaton = network.getAutomaton();
		AutomatonPortNames portNames = CA.getPortNames(automaton);
		
		for (String name : portNames.getValues()) {
			PortWrapper<T> wrapper = new PortWrapper<T>(name);
			wrappers.put(name, wrapper);
			ports.put(name, wrapper.getPort());
		}
		
		final File catFile;
		final ExecutableCA<?> xca;
		
		try {
			// Save CAT version of the automaton to a temporary file.
			catFile = File.createTempFile("coordinator", ".cat");
			OutputStream out = new BufferedOutputStream(new FileOutputStream(catFile));
			InputStream in = Reo2EaAction.ea2cat(automaton);
			IOUtils.copy(in, out);
			in.close();
			out.close();
			
			// Load the CAT file.
			xca = XCADriver.loadFile(catFile.getAbsolutePath(), ports);
		}
		catch (Throwable t) {
			job.logError("Error starting coordinator.", t);
			job.finish();
			return;
		}
		
		final List<?> traces = xca.getTraces();
		if (traces==null || traces.isEmpty()) {
			job.logError("No executable coordinator trace found.", null);
			job.finish();
			return;
		}
		
		// Create runnable.
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				// Starting.
				status = Status.RUNNING;
				
				// Choose a start state.
				int index = (int) (Math.random() * (traces.size()-1));
				Runnable trace = (Runnable) traces.get(index);
				
				// Run the trace.
				trace.run();
				
				// Done.
				reset();
				catFile.delete();
			}
			
		};
		
		// Create and run thread.
		thread = new Thread(runnable);
		thread.start();
		
	}
	
	
	private void reset() {
		status = Status.STOPPED;
		if (job!=null) job.finish();
		wrappers.clear();
		ports.clear();
		thread = null;
		job = null;
	}
	
	
	public synchronized void stop() {
		
		if (status==Status.STOPPED) return;
		
		// Interrupt the ports.
		for (PortWrapper<T> wrapper : wrappers.values()) {
			wrapper.interrupt();
		}
		
		// Interrupt coordinator.
		thread.interrupt();
		
		reset();
	}
	
	
	public synchronized void silentlyInterrupt() {
		
		if (status==Status.STOPPED) return;
		
		// Silently interrupt the ports.
		for (PortWrapper<T> wrapper : wrappers.values()) {
			wrapper.silentlyInterrupt();
		}
		// We do not stop the coordinator.
	}

	
	
	public Status getStatus() {
		return status;
	}
	
	public PortWrapper<T> getPort(PrimitiveEnd end) {
		String name = network.getPortName(end);
		return wrappers.get(name);
	}
		
	public Job getJob() {
		return job;
	}
		
}
