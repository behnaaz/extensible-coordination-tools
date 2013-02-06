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
package org.ect.convert.bpel2reo.transform.manager;

import java.util.Map;
import java.util.TreeMap;

import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.Connector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Parser {

	public Document doc;
	public Connector cnnector;
	public Map<String, Pair<Node, Node>> links;

	public Parser(Document doc) {
		this.doc = doc;
		this.links = new TreeMap<String, Pair<Node, Node>>();
	}

	@SuppressWarnings("unused")
	private boolean checkName(Node item, String toCompare)
	{
		System.out.println("[" + item + "]");
		return item.getNodeName().replace("bpel:", "").replace("bpws:", "").startsWith(toCompare);		
	}

	/*
	public Pair<cwi.reo.Node, cwi.reo.Node> parse(Node item, Connector cnnector) {//khgoroojie pars be bargashtie transform tabdil she
		Pair<cwi.reo.Node, cwi.reo.Node> tmp = null;
		if (checkName(item, "process")) {
			cnnector = new Connector();
			new Process().transform(cnnector);
			NodeList nl = item.getChildNodes();
			for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
				tmp = parse(nl.item(i), cnnector);
			//???????????????????????????
			return tmp;
		} else {
			if (checkName(item, "sequence")) {
				Sequence sequence = new Sequence();
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					{
						tmp = parse(nl.item(i), cnnector);
						if (tmp != null)
							sequence.AddPart(tmp.getFirst(), tmp.getSecond());
					}
				Pair<cwi.reo.Node, cwi.reo.Node> res = sequence.transform(cnnector);
			//	cnnector.setStart(res.getFirst());
			//	cnnector.setEnd(res.getSecond());
				return res;
			}
			else if (checkName(item, "assign")) {
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
				{
					tmp = parse(nl.item(i), cnnector);
				}
				return tmp;
			} else if (checkName(item, "receive")) {
				////////////new Receive(item.getAttributes().getNamedItem("variable").getNodeValue(), new Variable(), item.getAttributes().getNamedItem("operation").getNodeValue()).transform(cnnector);
				return tmp;
			} else if (checkName(item, "reply")) {
				return tmp;
			} else if (checkName(item, "copy")) {
				String fromVariable = null, toVariable = null;
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					 if (checkName(nl.item(i), "from")) 
						fromVariable = nl.item(i).getAttributes().getNamedItem("variable").getNodeValue();
					 else if (checkName(nl.item(i), "to")) 
						toVariable = nl.item(i).getAttributes().getNamedItem("variable").getNodeValue();
				Pair<cwi.reo.Node, cwi.reo.Node> res = new Copy(fromVariable, toVariable).transform(cnnector);
			//	cnnector.setStart(res.getFirst());
			//	cnnector.setEnd(res.getSecond());
				return tmp;
			} else if (checkName(item, "variables")) {
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					if (checkName(nl.item(i), "variable")) 
						System.out.println("variable name:"
								+ nl.item(i).getAttributes().getNamedItem("name")
								.getNodeValue());					
				return tmp;
			} else if (checkName(item, "if"))	{
				//bachehasho add kon
					return tmp;
			}	
			else if (checkName(item, "while"))	{
				//bachehasho add kon
					return tmp;
			}	
			else if (checkName(item, "link"))	{
				String name = item.getAttributes().getNamedItem("name").getNodeValue();
				if (!links.containsKey(name))
					links.put(name, new Pair<Node, Node>(null, null));
				System.out.println("link name"+name);
			}	
			else if (checkName(item, "sources") || checkName(item, "targets"))
			{
				String name = item.getAttributes().getNamedItem("linkName").getNodeValue();
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					if (checkName(nl.item(i), "source") || checkName(nl.item(i), "target")) 
					{	
						System.out.println("link name:"	+ name);
						Pair<Node, Node> pair = links.get(name);
						if (pair == null)
							links.put(name, (checkName(nl.item(i), "source")?new Pair<Node, Node>(nl.item(i), null):new Pair<Node, Node>(null, nl.item(i))));
						else
							{
								links.put(name, (checkName(nl.item(i), "source")?new Pair<Node, Node>(nl.item(i), pair.getSecond()):new Pair<Node, Node>(pair.getFirst(), nl.item(i))));
								return Link(cnnector, name, links.get(name));
							}
					}
				return tmp;
			}				
		}
	return tmp;
}
	 */
	@SuppressWarnings("unused")
	private Pair<org.ect.reo.Node, org.ect.reo.Node> Link(Connector cnnector2, String name,	Pair<Node, Node> pair) {
		// TODO Auto-generated method stub
		return null;
	}	

}