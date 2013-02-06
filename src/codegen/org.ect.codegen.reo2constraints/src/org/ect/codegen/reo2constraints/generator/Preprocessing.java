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
package org.ect.codegen.reo2constraints.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;
import org.ect.reo.util.PrimitiveEndNames;

public class Preprocessing {
	
	String problems = "";
	private boolean contextSensitive = false;
	private boolean prioritySensitive = false;
	private boolean dataSensitive = false;
	private boolean timeSensitive = false;
	private PrimitiveEndNames endnames = new PrimitiveEndNames();
	private Set<PrimitiveEnd> prioritized = new HashSet<PrimitiveEnd>();
	Set<Connectable> visited = new HashSet<Connectable>();
	
	public String getProblems() {
		return problems;
	}

	int cnt = 1;
	private HashMap<String, String> filterEcoreIdNewId = new HashMap<String, String>();
	private HashMap<String, String> filterIdCond = new HashMap<String, String>();
	private List<String> boundaryNodes = new ArrayList<String>();
	private List<String> nonBoundaryNodes = new ArrayList<String>();
	ArrayList<String> FIFOIds = new ArrayList<String>();
	ArrayList<GroupedChannels> groupedFIFOs = new ArrayList<GroupedChannels>();
	ArrayList<String> TimerIds = new ArrayList<String>();
	private State initialState = null;
	HashMap<String, String> endId_newName = null;
	String reduceVarPrefix = "";
	private List<PrioritySync> prioritySyncs = new ArrayList<PrioritySync>();
	
	public Preprocessing(Module mod, String reduceVarPrefix) {
		this.reduceVarPrefix = reduceVarPrefix;
		this.endId_newName = nameNullEnds(mod);		
		preProcessing(mod);
		checkBoundaryNodes(mod);
		for (PrioritySync p : prioritySyncs){
			visited.clear();
			propagate(p); 
		}
		System.out.println("Prioritied ends: "+prioritized.toString());
	}
	
	private void propagate(Primitive p) {
		if (p==null||(!(p instanceof Channel))||visited.contains(p))
			return;
		visited.add(p);
		//Do propagate backwards
		if (!(p instanceof BlockingSinkSync || p instanceof BlockingSync || p instanceof FIFO)){
			for (SourceEnd source : p.getSourceEnds()){
				prioritized.add(source);
				Node n = source.getNode();
				//Prioritize the sinks writting to the source end of input
				for (SinkEnd ske : n.getSinkEnds()){
					prioritized.add(ske);
					Primitive nxtp = ske.getPrimitive();
					if (!(nxtp instanceof BlockingSinkSync || nxtp instanceof BlockingSync  || p instanceof FIFO)){
						propagate(nxtp);
					}
				}
				//Prioritize the sources reading from the same place as the source end of input
				if (n.getType() != NodeType.ROUTE){
						for (SourceEnd c : n.getSourceEnds()){
							if (c != source){
								prioritized.add(c);
								Primitive nxtp = c.getPrimitive(); 
								if (!(nxtp instanceof BlockingSourceSync || nxtp instanceof BlockingSync  || p instanceof FIFO)){
									propagate(nxtp);
								}
							}
						}
							
					}
			}
			//Do propagate forwards
			if (!(p instanceof BlockingSourceSync || p instanceof BlockingSync  || p instanceof FIFO)){
				for (SinkEnd sink : p.getSinkEnds()){
					prioritized.add(sink);
					Node n = sink.getNode();
					//Prioritize the sources reading from the sink end of input
					for (SourceEnd src : n.getSourceEnds()){
						prioritized.add(src);
						Primitive nxtp = src.getPrimitive();
						if (!(nxtp instanceof BlockingSourceSync || nxtp instanceof BlockingSync  || p instanceof FIFO)){
							propagate(nxtp);
						}
					}
					//If the node is JOIN, prioritize the sinks writting to same place as the sink end of input
					if (n.getType() == NodeType.JOIN){
							for (SinkEnd k : n.getSinkEnds()){
								if (k != sink){
									prioritized.add(k);
									Primitive nxtp = k.getPrimitive(); 
									if (!(nxtp instanceof BlockingSinkSync || nxtp instanceof BlockingSync  || p instanceof FIFO)){
										propagate(nxtp);
									}
								}
							}								
						}
				}
			}		
		}		
	}

	private HashMap<String, String> nameNullEnds(Module mod) {
		List<Primitive> primitives = mod.getAllPrimitives();
		HashMap<String, String> idnewnames = new HashMap<String, String>();
		for (Primitive r : primitives){
			for (PrimitiveEnd pend : r.getAllEnds()){
				if (pend.getName()==null || pend.getName().length()==0){
					String pid = EcoreUtil.getIdentification(pend);
					if (!idnewnames.containsKey(pid)) {
						String newname =  endnames.generate(pend);
						idnewnames.put(pid, newname);
						Reo.logInfo("New name for the end "+ pid + " : "+ newname);
					}
				}
			}
		}
		return idnewnames;
	}
	
	public String getNotNullName(PrimitiveEnd end){
		if (end.getName() != null && end.getName().length() > 0)
			return end.getName();
		String buffered = endId_newName.get(EcoreUtil.getIdentification(end));
		if (buffered == null || buffered.length() == 0)
			System.err.println(end+" has no buffered name");
		return buffered;
	}
	
	public ArrayList<String> getTimerIds() {
		return TimerIds;
	}

	public ArrayList<String> getFIFOIds() {
		return FIFOIds;
	}
	
	public HashMap<String, String> getCondId() {
		return filterIdCond;
	}

	public HashMap<String, String> getFilterIdConditions() {
		return filterIdCond;
	}

	private String preProcessing(Module module) {
		// Check if all the ends have valid names
		String timersstate = "";
		String fifosstate = "";
		ArrayList<String> names = new ArrayList<String>();
		String red = "";
		String res = "";
		for (Node node : module.getAllNodes()) {
			boolean boundary = false;
			for (PrimitiveEnd end : node.getAllEnds()) {
				if (end.getName() == null || end.getName().trim().length() == 0
						|| !Character.isLetter(end.getName().charAt(0)))
					res += " " + node.getName();
				else
					for (int i = 0; i < end.getName().length(); i++) {
						if (!Character.isLetterOrDigit(end.getName().charAt(i)))
							res += " " + node.getName();
					}
				// check if the node is boundary
				if ((end.getPrimitive() instanceof Reader)
						|| (end.getPrimitive() instanceof Writer))
					boundary = true;
			}
			// add the boundary and nonboundary nodes to the proper list
			if (boundary) {
				boundaryNodes.add(node.getName());
			} else {
				nonBoundaryNodes.add(node.getName());
			}
		}
		groupedFIFOs = partitionMemoryfulChannels(nonBoundaryNodes, module);
		for (Primitive prim : module.getAllPrimitives()) {
			for (PrimitiveEnd end : prim.getAllEnds()) {
				if (end.getName() == null || end.getName().trim().length() == 0
						|| !Character.isLetter(end.getName().charAt(0)))
					res += " " + prim.getName();
				else
					for (int i = 0; i < end.getName().length(); i++) {
						if (!Character.isLetterOrDigit(end.getName().charAt(i)))
							res += " " + prim.getName();
						else {
							if (names.contains(prim.getName())) {
								red += ((red.compareTo("") != 0) ? ", " : "")
										+ end.getName();
							} else
								names.add(end.getName());//TODO saename
						}
					}
				// Get the extra info from the network
				if (prim instanceof PrioritySync){
					if (!prioritySyncs.contains(prim))
						prioritySyncs.add((PrioritySync) prim);
					prioritySensitive = true;
					contextSensitive = true;
				}
				else if (prim instanceof LossySync){
					this.contextSensitive = true;
				}
				else if (prim instanceof Filter) {
					this.dataSensitive = true;
					Filter f = (Filter) prim;
					generateIdAndSave(f);
				} else if (prim instanceof Timer) {
					this.timeSensitive = true;
					String id = EcoreUtil.getIdentification(prim);
					if (!TimerIds.contains(id)) {
						TimerIds.add(id);
						timersstate += "0";
					}
				} else if (prim instanceof FIFO) {
					String id = EcoreUtil.getIdentification(prim);
					if (!FIFOIds.contains(id)) {
						FIFOIds.add(id);
						fifosstate += "0";
					}			 
					
					
					
					//TODO OOOO
					HashSet<String> idset = new HashSet<String>();
					idset.add(id);
					if (indexOf(groupedFIFOs, id)<0) {
						groupedFIFOs.add(new GroupedChannels(prim.getSourceEnd(0), prim.getSinkEnd(0), idset, ((FIFO)prim).isFull()?1:0));						
					}
				}
			}
		}		
		if (red.length() > 0)
			res += "\n These elements have redundant names:" + red + "\n";
		problems += res;
		
		initialState = new State(StateManager.encode(groupedFIFOs), fifosstate, timersstate);
		return res;
	}

	

	private ArrayList<GroupedChannels> partitionMemoryfulChannels(List<String> internalNodes, Module module) {
		Node n = null;
		Map<String, Boolean> nodeVisited = initializeNodeVisited(internalNodes, module);
		while ((n = getNextNode(nodeVisited, n, module))!=null){
			int srcFIFO = indexOf(groupedFIFOs, EcoreUtil.getIdentification(n.getSourceEnds().get(0).getPrimitive()));		
			int snkFIFO = indexOf(groupedFIFOs, EcoreUtil.getIdentification(n.getSinkEnds().get(0).getPrimitive()));
			if (srcFIFO < 0 && snkFIFO < 0){
				//add both in one group
				Set<String> temp = new HashSet<String>();
				//add ids
				temp.add(EcoreUtil.getIdentification(n.getSourceEnds().get(0).getPrimitive()));
				temp.add(EcoreUtil.getIdentification(n.getSinkEnds().get(0).getPrimitive()));
				//calculate the state value
				int fifosval = ((((FIFO)n.getSourceEnds().get(0).getPrimitive()).isFull())?1:0)+((((FIFO)n.getSinkEnds().get(0).getPrimitive()).isFull())?1:0);
				//src is the source of the source FIFO and snk is the sink of sink FIFO!
				groupedFIFOs.add(new GroupedChannels(n.getSourceEnds().get(0).getPrimitive().getSourceEnd(0), 
						n.getSinkEnds().get(0).getPrimitive().getSinkEnd(0), temp, fifosval));				
			}
			else if (srcFIFO < 0 && snkFIFO > -1) {
				//only add the source FIFO to the existing group
				FIFO fifo = (FIFO)n.getSourceEnds().get(0).getPrimitive();
				//retrive the snk entry for update
				GroupedChannels temp = groupedFIFOs.get(snkFIFO);
				//add id
				temp.getChannelIds().add(EcoreUtil.getIdentification(fifo));
				//update src
				temp.setSource(fifo.getSourceEnd());
				//update initial state
				int fifoval = ((fifo.isFull())?1:0);
				temp.setState(temp.getState()+fifoval);
				groupedFIFOs.set(snkFIFO, temp);				
			}
			else if (srcFIFO > -1 && snkFIFO < 0) {
				//only add the sink FIFO the the existing group
				FIFO fifo = (FIFO)n.getSinkEnds().get(0).getPrimitive();
				//retrive the src entry for update
				GroupedChannels temp = groupedFIFOs.get(srcFIFO);
				//add id
				temp.getChannelIds().add(EcoreUtil.getIdentification(fifo));
				//update snk
				temp.setSink(fifo.getSinkEnd());
				//update the initial value
				int fifoval = ((fifo.isFull())?1:0);
				temp.setState(temp.getState()+fifoval);
				//update the existing entry
				groupedFIFOs.set(srcFIFO, temp);				
			}
			else if (srcFIFO > -1 && snkFIFO > -1 && srcFIFO != snkFIFO){
				//if the fifos exist in different groups, then merge them
				//merge ids
				Set<String> ids = groupedFIFOs.get(srcFIFO).getChannelIds();
				ids.addAll(groupedFIFOs.get(snkFIFO).getChannelIds());
				//update the initial value
				int fifoval = groupedFIFOs.get(srcFIFO).getState()+groupedFIFOs.get(snkFIFO).getState();
				//source is the source of sourceFIFO and sink is sink of the sinkFIFO
				groupedFIFOs.set(snkFIFO, new GroupedChannels(groupedFIFOs.get(srcFIFO).getSourceEnd(), groupedFIFOs.get(snkFIFO).getSinkEnd(), ids, fifoval));
				//remove one
				groupedFIFOs.remove(srcFIFO);
			}
			nodeVisited.put(EcoreUtil.getIdentification(n), true);
		}
		return groupedFIFOs;
	}

	private Map<String, Boolean> initializeNodeVisited(List<String> internalNodes, Module module) {
		Map<String, Boolean> nodeVisited = new HashMap<String, Boolean>();
		for (Node n : module.getAllNodes()){
			if (internalNodes.contains(n.getName()) && n.getSinkEnds().size()==1 && n.getSourceEnds().size()==1 && 
				n.getSourceEnds().get(0).getPrimitive() instanceof FIFO && n.getSinkEnds().get(0).getPrimitive() instanceof FIFO){
					nodeVisited.put(EcoreUtil.getIdentification(n), false);
			}
		}
		return nodeVisited;
	}

	private Node getNextNode(Map<String, Boolean> nodeVisited, Node previousNode, Module module) {
		for (Node n : module.getAllNodes()){
			if (nodeVisited.containsKey(EcoreUtil.getIdentification(n)) && !nodeVisited.get(EcoreUtil.getIdentification(n))){
				return n;
			}
		}
		return null;
	}

	public int indexOf(ArrayList<GroupedChannels> groupedfifos, String id) {
		for (GroupedChannels set : groupedfifos) {
			if (set.getChannelIds().contains(id))
				return groupedfifos.indexOf(set);
		}
		return -1;
	}

	// Validate: Checks if non mixed nodes are connected to a Reader or Writer
	boolean checkBoundaryNodes(Module mod) {
		for (Node node : mod.getAllNodes()) {
			if (node.isSourceNode()) {
				int sinksno = node.getSinkEnds().size();
				if (sinksno == 1) {
					SinkEnd sinkend = node.getSinkEnds().get(sinksno - 1);
					if (!(sinkend.getPrimitive() instanceof Writer)) {
						problems += "Error: the sourcenode " + node
								+ " is not connected to a Writer!!!\n";
						return false;
					}
				} else {
					problems += "Error: the sourcenode " + node
							+ " is not connected to a Writer!!!\n";
					return false;
				}
			} else if (node.isSinkNode()) {
				int sourcesno = node.getSourceEnds().size();
				if (sourcesno == 1) {
					SourceEnd sourceend = node.getSourceEnds().get(
							sourcesno - 1);
					if (!(sourceend.getPrimitive() instanceof Reader)) {
						problems += "Error: the sinknode " + node
								+ " is not connected to a Reader!!!\n";
						return false;
					}
				} else {
					problems += "Error: the sinknode " + node
							+ " is not connected to a Reader!!!\n";
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Assign an id to the expression and add it to the list.
	 * 
	 * @param Condition
	 * @return The name of the constraint
	 */
	private String generateIdAndSave(Filter f) {
		String id = EcoreUtil.getIdentification((EObject) f);
		if (filterEcoreIdNewId.containsKey(id))
			return filterEcoreIdNewId.get(id);
		String newId = reduceVarPrefix + cnt; // TODO???
		filterEcoreIdNewId.put(id, newId);
		filterIdCond.put(newId, f.getExpression());
		cnt++;
		return newId;
	}

	public String getExpressionId(Filter f) {
		return filterEcoreIdNewId.get(EcoreUtil.getIdentification((EObject) f));
	}

	public String getFilterExpressionId(String id) {
		return filterIdCond.get(id);// TODO tekrari????
	}

	public State getInitialState() {
		return initialState;
	}
	
	public boolean isContextSensitive() {
		return contextSensitive;
	}

	public boolean isDataSensitive() {
		return dataSensitive;
	}

	public boolean isPrioritySensitive() {
		return prioritySensitive;
	}

	public boolean isTimeSensitive() {
		return timeSensitive;
	}

	public List<PrioritySync> getPrioritySyncs() {		
		return prioritySyncs ;
	}

	public Set<PrimitiveEnd> getPriotizedEnds() {
		return prioritized;
	}
}
