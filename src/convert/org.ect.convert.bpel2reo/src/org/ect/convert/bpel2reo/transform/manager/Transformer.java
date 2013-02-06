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

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.ect.convert.bpel2reo.Activator;
import org.ect.convert.bpel2reo.transform.Assign;
import org.ect.convert.bpel2reo.transform.Empty;
import org.ect.convert.bpel2reo.transform.Flow;
import org.ect.convert.bpel2reo.transform.Link;
import org.ect.convert.bpel2reo.transform.Process;
import org.ect.convert.bpel2reo.util.ConnectorInfo;
import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.Connector;

public class Transformer {
	ConnectorInfo res = null;
	Map<String, Pair<org.ect.reo.Node, org.ect.reo.Node>> links = new TreeMap<String, Pair<org.ect.reo.Node, org.ect.reo.Node>>();
	Pair <org.ect.reo.Node, org.ect.reo.Node> mainstartend = null;
	
	public ConnectorInfo transform(String filename) {
		try {
			Document doc = parserXML(new File(filename));
			res = parse(doc.getFirstChild(), new ConnectorInfo(new Connector(), null));
			genLink(res.getConnector());
		} catch (Exception error) {
			error.printStackTrace();
		}
		return new ConnectorInfo(res.getConnector(), mainstartend);
	}
	
	private boolean checkName(Node item, String toCompare) {
		return item.getNodeName().replace("bpel:", "").replace("bpws:", "").startsWith(toCompare); }
	
	private String getName(Node item)
	{//????????
		if (item.getAttributes().getNamedItem("name") == null)
		{
			Activator.logger.log(Level.WARNING, " while parsing: " + item.getNodeValue() + " has no name attribute");
			return "";
		}
		return item.getAttributes().getNamedItem("name").getNodeValue();		
	}
	
	private ConnectorInfo parse(Node item, ConnectorInfo result) {//khgoroojie pars be bargashtie transform tabdil she
		   if (checkName(item, "process")) {
			//	result = new ConnectorInfo(new Connector(), null);
				result.getConnector().setName(getName(item));
                Process process = new Process(getName(item));
				NodeList nl = item.getChildNodes();

				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					if (notIgnore(nl.item(i)))  /////////add be seq
					 result = parse(nl.item(i), result); //cant be more than one fixxxxxxxxxxx
				
				process.transform(result.getConnector());
				mainstartend = result.getPair();
				return result;
    	   } 
		   else if (checkName(item, "scope")) {
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					if (notIgnore(nl.item(i))) { /////////add be seq
					 result = parse(nl.item(i), result); //cant be more than one fixxxxxxxxxxx
					}
					return result;
			} 
		   /*else {
				if (checkName(item, "sequence")) {
					Sequence sequence = new Sequence();
					NodeList nl = item.getChildNodes();
					for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
						{
							Connector tmpres = parse(nl.item(i));
							if (tmpres != null)
								sequence.AddPart(null, null);//?????????????tmpres
						}
					sequence.transform(result);
				}*/
			else if (checkName(item, "assign")) {
				result.setPair(new Assign(getName(item)).transform(result.getConnector()));
				checkLink(item, result.getPair());
				///NodeList nl = item.getChildNodes();
				//for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					//result = parse(nl.item(i));
				return result;
			}
				/********************ghadimielse if (checkName(item, "assign")) {
					NodeList nl = item.getChildNodes();
					for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
						result = parse(nl.item(i));
					return result;
				}*/ /*else if (checkName(item, "receive")) {
					////////////new Receive(item.getAttributes().getNamedItem("variable").getNodeValue(), new Variable(), item.getAttributes().getNamedItem("operation").getNodeValue()).transform(cnnector);
					return result;
				} else if (checkName(item, "reply")) {
					return result;
				} else if (checkName(item, "copy")) {
					String fromVariable = null, toVariable = null;
					NodeList nl = item.getChildNodes();
					for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
						 if (checkName(nl.item(i), "from")) 
							fromVariable = nl.item(i).getAttributes().getNamedItem("variable").getNodeValue();
						 else if (checkName(nl.item(i), "to")) 
							toVariable = nl.item(i).getAttributes().getNamedItem("variable").getNodeValue();
					Pair<org.ect.reo.Node, org.ect.reo.Node> paircpy = new Copy(fromVariable, toVariable).transform(result);
					checkLink(item, paircpy);
					return result;
				} */
				else if (checkName(item, "empty")) {
					result.setPair(new Empty(getName(item)).transform(result.getConnector()));
					checkLink(item, result.getPair());
					return result;
				} 
				else if (checkName(item, "extensionActivity")) {////////////////////////////////////////?????????????????
					result.setPair(new Empty(getName(item)+ " extensionActivity").transform(result.getConnector()));
					checkLink(item, result.getPair());
					return result;
				}
				else if (checkName(item, "flow")) {////////////////////////////////////////?????????????????
					Flow flow = new Flow(getName(item));
					NodeList nl = item.getChildNodes();
					for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
						if (!ignore(nl.item(i))) { /////////add be seq
							result =  parse(nl.item(i), result);
							flow.AddPart(result.getPair());
						}
					result.setPair(flow.transform(result.getConnector()));//baghie ham
					checkLink(item, result.getPair());
					return result;
				}
				/*else if (checkName(item, "variables")) {
					NodeList nl = item.getChildNodes();
					for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
						if (checkName(nl.item(i), "variable")) 
							System.out.println("variable name:"
									+ nl.item(i).getAttributes().getNamedItem("name")
									.getNodeValue());					
					return result;
				} else if (checkName(item, "if"))	{
					//bachehasho add kon
						return result;
				}	
				else if (checkName(item, "while"))	{
					//bachehasho add kon
						return result;
				}		*/				
			//}
		else if (!ignore(item))
			Activator.logger.log(Level.WARNING, "Parsing " + item.getNodeName() + " ignored.");   
		return result;
	}

	private boolean ignore(Node item) {
		return checkName(item, "#text") ||  checkName(item, "links") || checkName(item, "documentation") || checkName(item, "extensions");
	}
	
	private boolean notIgnore(Node item) {
		return checkName(item, "process") || checkName(item, "flow") || checkName(item, "scope") || checkName(item, "empty");///   ????
	}
	private void checkLink(Node root, Pair<org.ect.reo.Node, org.ect.reo.Node> pairlink) {
		for (int j = 0; j < root.getChildNodes().getLength(); j++)
		{
			Node item = root.getChildNodes().item(j);
			if (checkName(item, "sources") || checkName(item, "targets"))
			{
				NodeList nl = item.getChildNodes();
				for (int i = 0, cnt = nl.getLength(); i < cnt; i++) 
					if (checkName(nl.item(i), "source") || checkName(nl.item(i), "target")) 
					{	
						String name = nl.item(i).getAttributes().getNamedItem("linkName").getNodeValue();
						Activator.logger.log(Level.INFO, "Parsing: Link named '" + name + "' " + (checkName(nl.item(i), "source")?(" : source " + pairlink.getSecond().getName()):(" : target " + pairlink.getFirst().getName())));
						Pair<org.ect.reo.Node, org.ect.reo.Node> pair = links.get(name);
						if (pair == null)
							links.put(name, (checkName(nl.item(i), "source")?new Pair<org.ect.reo.Node, org.ect.reo.Node>(pairlink.getSecond(), null):new Pair<org.ect.reo.Node, org.ect.reo.Node>(null, pairlink.getFirst())));
						else
							links.put(name, (checkName(nl.item(i), "source")?new Pair<org.ect.reo.Node, org.ect.reo.Node>(pairlink.getSecond(), pair.getSecond()):new Pair<org.ect.reo.Node, org.ect.reo.Node>(pair.getFirst(), pairlink.getFirst())));
					}
			}
		}
		/* if (checkName(item, "link"))	{
			String name = item.getAttributes().getNamedItem("name").getNodeValue();
			if (!links.containsKey(name))
				links.put(name, new Pair<org.ect.reo.Node, org.ect.reo.Node>(null, null));
			System.out.println("link name"+name);
		}	*/
	}

	private void genLink(Connector cnn) {
		for(Map.Entry<String, Pair<org.ect.reo.Node, org.ect.reo.Node>> entry : links.entrySet()) {
			  String key = entry.getKey();
			  Pair<org.ect.reo.Node, org.ect.reo.Node> value = entry.getValue();
			  new Link(key, value).transform(cnn);
		}
	}

	public Document parserXML(File file) throws SAXException, IOException,
	ParserConfigurationException {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
				file);		
	}
}
