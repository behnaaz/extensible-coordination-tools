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
package org.ect.codegen.ea.ui;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Property;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.util.PropertyHelper;

public class MainTemplate {
	private StringTemplateGroup wiringGroup;
	
	private String className = "Coordinator", packageName = "connector";
	private StringTemplate wiring, top;
	private Network net;
	private List<StringTemplate> compTemplates;
	private Set<String> imports;
	
	public  MainTemplate(Connector connector, StringTemplateGroup mainGroup)  {
		this.wiringGroup = mainGroup;
		
		net = new Network(connector);
		net.update();
		
		top = mainGroup.getInstanceOf("top");
		wiring = mainGroup.getInstanceOf("main");		
	}
	
	public MainTemplate inPackage(String packageName) {
		this.packageName = packageName;
		return this;
	}
	
	public MainTemplate withCoordinator(String className) {
		this.className = className;
		return this;
	}

	public MainTemplate withPorts(List<String> ports) {
		wiring.setAttribute("ports", ports);
		return this;
	}
	
	private void attachComponents(Collection<Component> components) {
		compTemplates = new ArrayList<StringTemplate>();
		imports = new HashSet<String>();
		int r=0,w=0,g=0;
		
		for (Component c: components) {
			StringTemplate st = wiringGroup.getInstanceOf("component");
			compTemplates.add(st);

			Map<String, String> args = new LinkedHashMap<String, String>();
			st.setAttribute("args", args);
			
			if (c instanceof Reader) {
				st.setAttribute("id", "reader"+ r);
				st.setAttribute("class", "BasicReader");
				
				args.put("Name", "\"reader#"+ r++ +"\"");			
				imports.add("org.ect.codegen.ea.runtime.components.BasicReader");
			} else if (c instanceof Writer) {
				st.setAttribute("id", "writer"+ w);
				st.setAttribute("class", "BasicWriter");
				args.put("Name", "\"writer#"+ w++ +"\"");
				imports.add("org.ect.codegen.ea.runtime.components.BasicWriter");
			} else {
				st.setAttribute("id", "component"+ g++);
				st.setAttribute("class", c.getName());
//				for (Property p : c.getProperties())
//					args.put(p.getKey(), p.getValue());
				String packageName = PropertyHelper.getFirstValue(c, "package");				
				imports.add(packageName);
			}
			List<Property> prop;
			Property req;
			if (!(prop=c.getProperties()).isEmpty() && 
					(req=prop.get(0)).getKey().equals("requests"))
				args.put("Count", req.getValue());
			
			for (SinkEnd s: c.getSinkEnds())
				args.put("SinkPorts", node2PortName(s.getNode()));
			for (SourceEnd s: c.getSourceEnds())
				args.put("SourcePorts", node2PortName(s.getNode()));
		}
	}
	
	@Override
	public String toString()  {
		attachComponents(net.getComponents());
		wiring.setAttribute("coordinator", this.className);
		wiring.setAttribute("components", compTemplates);
		top.setAttribute("main", wiring);
		top.setAttribute("imports", imports);
		top.setAttribute("package", this.packageName);
		
		return top.toString();
	}
	/*
	public static void main(String[] args) {
		Connector con = XMIWrangler.loadReo("/tmp/valve.reo").getConnectors().get(0);
		List<String> ports = {"A"};
		new MainTemplate(con).inPackage("foo").withPorts(ports).toString();
	}
	*/
}
