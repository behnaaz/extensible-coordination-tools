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

import java.util.HashMap;
import java.util.Map;

import org.ect.codegen.ea.components.BasicReader;
import org.ect.codegen.ea.components.BasicWriter;
import org.ect.codegen.ea.interpreter.ExecutableCA;
import org.ect.codegen.ea.interpreter.XCADriver;
import org.ect.codegen.ea.runtime.Sink;
import org.ect.codegen.ea.runtime.Source;
import org.ect.codegen.ea.runtime.TransactionalIO;
import org.ect.codegen.ea.runtime.primitives.TimeoutPort;

public class Main {
	private static Map<String, TransactionalIO> ports = new HashMap<String, TransactionalIO>();
	public static void main(String[] args) throws Exception {
		String[] portNames = {"A", "B", "J", "K"};
		
		for (String pn: portNames)
			ports.put(pn, new TimeoutPort(pn));

		Runnable[] components = new Runnable[] {
				new BasicReader().withSourcePorts((Source)ports.get("B")),
				new BasicWriter().withSinkPorts((Sink)ports.get("A")),
				new Gate((Sink) ports.get("J"),(Sink) ports.get("K"))
		};
		
		String TESTFILE = "testdata/valve.cat";
		ExecutableCA xca = XCADriver.loadFile(TESTFILE, ports);
		System.out.println(xca);
		xca.launch(components);
	}
}
