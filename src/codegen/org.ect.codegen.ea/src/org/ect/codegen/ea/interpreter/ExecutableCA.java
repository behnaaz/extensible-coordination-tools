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
package org.ect.codegen.ea.interpreter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.ect.codegen.ea.runtime.ReoEngine;
import org.ect.codegen.ea.runtime.TransactionalIO;

public class ExecutableCA<T extends TransactionalIO> implements Serializable, ReoEngine<T> {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String name;
	Map<String, T> ports;
	private Collection<XState> states = new HashSet<XState>();
		
	public ExecutableCA(String name) {
		this(name, new HashMap<String, T>());		
	}
	
	public ExecutableCA(String name, Map<String, T> ports) {
		this.name = name;
		this.ports = ports;
	}
	
	public void addState(XState ss) {
		states.add(ss);
	}
	
	public void addPort(T port) {
		ports.put(port.toString(), port );
	}
	
	public T createPort(String name, Class<T> portType) throws Exception {
		T port = (T) portType.getConstructor(String.class).newInstance(name);
		ports.put(name, port );
		return port;
	}

	public T getPort(String portName) {
		return ports.get(portName);
	}
	
	public Map<String, T> getPorts() {
		return Collections.unmodifiableMap(ports);
	}
	
	/***
	 * Launches engine and component threads. Once all components terminate kills the engine. 
	 * @param components
	 */
	@SuppressWarnings("unchecked")
	public void launch(Object... components) {
		Collection<Runnable> traces = getTraces();
		ExecutorService  executor = Executors.newFixedThreadPool(traces.size()+components.length);
		for (Runnable r: traces)
			executor.execute(r);

		logger.info("Launching component threads");
		CompletionService ecs = new ExecutorCompletionService(executor);
		for (Object component: components) 
			if (component instanceof Runnable)
				ecs.submit((Runnable)component, Boolean.TRUE);
			else if (component instanceof Callable)
				ecs.submit((Callable)component);

		try {
			for (int i=0; i<components.length; i++)
				ecs.take();
		} catch (InterruptedException e) {
//			e.printStackTrace();
		} finally { 
		//just kill everything remaining either during normal completion or if aborted 
			executor.shutdownNow();
		}
		logger.info("All threads done!");
	}	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append('"').append(name).append('"')
		.append(ports.keySet()).append("\n");

		for (XState s: states)
			sb.append(s).append("\n")
				.append(s.getTransitions()).append("\n");		
			
		return sb.toString();
	}
	
	public List<Runnable> getTraces() {
		List<Runnable> traces = new ArrayList<Runnable>();
		for (XState s: states)
			if (s.isStart()) 		
				traces.add(
					new Trace(s, (Collection<Observable>) ports.values())
				);		
		return traces;
	}	
}
