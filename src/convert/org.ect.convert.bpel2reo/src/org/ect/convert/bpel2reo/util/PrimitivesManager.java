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
package org.ect.convert.bpel2reo.util;

import java.util.TreeMap;
import org.ect.reo.*;

public class PrimitivesManager {
	
	public class ComponentName
	{
		public static final String Copy = "Copy";
		public static final String ShiftLossyFIFO = "ShiftLossyFIFO";
		public static final String Variable = "Variable";
		public static final String Receive = "Receive";
		public static final String Invoke = "Invoke";		
		public static final String Wait = "Wait";
		public static final String Link = "Link";
		
	}
	
	static TreeMap<String, Triple<Integer, Integer, String>> info = new TreeMap<String, Triple<Integer,Integer, String>>();
	
	public static void init()
	{//source sink
		info.put(ComponentName.Variable, new Triple<Integer, Integer, String>(1, 1, "/src/reoblocks/Variable.reo"));
		info.put(ComponentName.ShiftLossyFIFO, new Triple<Integer, Integer, String>(1, 1, "/src/reoblocks/ShiftLossyFIFO.reo"));
		info.put(ComponentName.Copy, new Triple<Integer, Integer, String>(2, 2, "/src/reoblocks/Copy.reo"));
		info.put(ComponentName.Receive, new Triple<Integer, Integer, String>(3, 2, "/src/reoblocks/Receive.reo"));
		info.put(ComponentName.Invoke, new Triple<Integer, Integer, String>(2, 3, "/src/reoblocks/Invoke.reo"));
		info.put(ComponentName.Wait, new Triple<Integer, Integer, String>(2, 1, "/src/reoblocks/Wait.reo"));
	}
	
	public Triple<Integer, Integer, String> getSourceSinkSourceEnds(String name)
	{
		return info.get(name);
	}
	
	public static String getResourcePath(String name)
	{
		if (info.isEmpty())
			init();
		return info.get(name).getThird();
	}

	/*public void validate(Module mod, String primitiveName) throws Exception {
		if (!((mod.getComponents().size() == 1) && (mod.getComponents().get(0).getSourceEnds().size() == new PrimitivesManager().getSourceSinkSourceEnds(primitiveName).getFirst()) && (mod.getComponents().get(0).getSourceEnds().size() == new PrimitivesManager().getSourceSinkSourceEnds(primitiveName).getSecond())))
			throw new Exception("Error: " + primitiveName + " component is corrupted!");
	}*/
	
	protected SinkEnd getSinkEnd(int n, Module mod)
	{
		return mod.getComponents().get(0).getSinkEnds().get(n);
	}

	protected SourceEnd getSourceEnd(int n, Module mod)
	{
		return mod.getComponents().get(0).getSourceEnds().get(n);
	}
	//balaiia lazeman fek mikoni?????????/
	public static PrimitiveEnd getEnd(Component cmp, String name)
	{
		for (int i=0; i<cmp.getAllEnds().size(); i++)
		{
			PrimitiveEnd end = cmp.getAllEnds().get(i);
			if (end.getName().compareTo(name) == 0)
				return end;
		}
		return null;
	}

	public static Node getNode(Connector cnn, String name) {
		for (int i=0; i<cnn.getNodes().size(); i++)
		{
			Node node = cnn.getNodes().get(i);
			if (node.getName().compareTo(name) == 0)
				return node;
		}
		return null;
	}
}
