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
package org.ect.convert.umlsd2reo.reocircuits;

import org.ect.convert.umlsd2reo.sdmodel.*;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import java.util.*;


/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure of a portion of circuit related to 
 * the input/output of a single participant.  
 * 
 * @author Eccher Alessandro
 *
 */ 
public class LifeLineConnector extends SuperConnector {
	
	//this variables are needed to handle eventual coregions
	boolean isCoregion=false;
	LinkedList<Node> coregionNodes;
	Node coregionSourceNode;
	//this variables are needed to handle Async messages that has effect 
	//in 2 times (we need to remember the index of the first occurence to use
	//it at the second).
	ArrayList<SDAsyncroMessage> aSyncMsgList=new ArrayList<SDAsyncroMessage>();
	ArrayList<Integer> aSyncMsgIndxList=new ArrayList<Integer>();
	
	
	//the participant associated to this lifeline
	SDParticipant participant;
		
	//the input/output nodes related to this participant
	Node inputNode;
	Node outputNode;
	
	//the connections to the inputnode
	LinkedList<Filter> inputChannels=new LinkedList<Filter>();
	//the connections to the outputnode
	LinkedList<Sync> outputChannels=new LinkedList<Sync>();
	
	//the nodes that must be linked to the Sequencer
	LinkedList<Node> sequencerNodes=new LinkedList<Node>();
	//the connections to the sequencer
	LinkedList<SyncDrain> sequencerConnections=new LinkedList<SyncDrain>();
	//the nodes that will be connected to the other lifelines in the basecircuit
	//every node has an index too (nodes with the same index will be connected)
	LinkedList<Couple> connectiveNodes=new LinkedList<Couple>();
	
	ChannelsFactory cFactory=new ChannelsFactory();
	
	//the sequencer associated to this lifeline
	Sequencer sequencer;
	
	public LifeLineConnector(SDParticipant participant){
		super(participant.getName());
		
		inputNode=new Node();
		inputNode.setName("input");
		addNode(inputNode);
		
		outputNode=new Node();
		outputNode.setName("output");
		addNode(outputNode);
		
		this.participant=participant;
	}
	
	public void addLostMessage(SDLostMessage msg, int index){
		boolean includesPortName = msg.getName().indexOf(':')>-1;
		//connect inputNode to the sequencerNode 
		Node temp=new Node();
		addNode(temp);
		if (includesPortName)
			temp.setName(getPortName(msg.getName(), 's'));
		sequencerNodes.add(temp);
		
		Filter filt=cFactory.createFilter();
		filt.setNodeOne(inputNode);
	
	    filt.getNodeOne().setName(filt.getNodeOne().getName()+getPortName(msg.getName(), 's'));	
		
		filt.setNodeTwo(temp);
		filt.setExpression(msg.getName());
		addPrimitive(filt);
		inputChannels.add(filt);
		
		//create a Destroyer Connector and link it to the sequencerNode
		Destroyer con=new Destroyer();
		Sync sync=new Sync(temp,con.getSinkNode());
		includeSuperConnector(con);
		
		addPrimitive(sync);		
	}
	
	private String getPortName(String msgname, char c) {
		String res = "";
		int idx1 = msgname.indexOf(':');
		int idx2 = msgname.lastIndexOf(':');
		if (idx1 < 0 || idx1 == msgname.length()-1)
			return "";
		if (c == 's')
			res = msgname.substring(0, idx1);
		else
			res = msgname.substring(idx2+1, msgname.length());
	    return res;
	}

	public void addFoundMessage(SDFoundMessage msg, int index){
		boolean includesPortName = msg.getName().indexOf(':')>-1;
		//connect outputNode to the sequencerNode 
		Node temp=new Node();
		addNode(temp);
		if (includesPortName)
			temp.setName(getPortName(msg.getName(), 's'));
		sequencerNodes.add(temp);
		
		Sync sync=new Sync(temp,outputNode);
		addPrimitive(sync);
		outputChannels.add(sync);
		
		//create a Producer Connector and link it to the sequencerNode
		Producer con=new Producer(msg.getName());
		sync=new Sync(con.getSourceNode(),temp);
		includeSuperConnector(con);
		
		addPrimitive(sync);		
	}
	
	
	public void addSyncroMessage(SDSyncroMessage msg, int index){
		boolean includesPortName = msg.getName().indexOf(':')>-1;
		
		//Sender Case
		if ((msg.getSender()).equals(participant)) {
			//connect inputNode to SequencerNode with a filter
			//for the message
			Node temp=new Node();
			addNode(temp);
			if (includesPortName)
				temp.setName(getPortName(msg.getName(), 's'));
			sequencerNodes.add(temp);
			
			Filter filt=cFactory.createFilter();
			filt.setNodeOne(inputNode);
			filt.setNodeTwo(temp);
			filt.setExpression(msg.getName());
			addPrimitive(filt);
			inputChannels.add(filt);
			
			//the connectiveNode is the same of the sequencerNode
			connectiveNodes.add(new Couple(temp,index,false));			
		}
		//Receiver Case
		else {
			
			//just connect outputNode to the SequencerNode
			Node temp=new Node();
			addNode(temp);
			if (includesPortName)
				temp.setName(getPortName(msg.getName(), 't'));
			sequencerNodes.add(temp);
			
			Sync sync=new Sync(temp,outputNode);
			addPrimitive(sync);
			outputChannels.add(sync);
			
			//the connectiveNode is the same of the sequencerNode
			connectiveNodes.add(new Couple(temp,index,true));
		}		
	}
	
	public void defineCoregionStart(int index) {
		isCoregion=true;
		coregionNodes=new LinkedList<Node>();
		coregionSourceNode=new Node();
		addNode(coregionSourceNode);
	}
	
	public void defineCoregionEnd(int index) {
		isCoregion=false;
		
		int numMsg=coregionNodes.size();
		
		//create a exr to attach every incoming msg in the
		//coregion
		ExclusiveRouter exr=new ExclusiveRouter(numMsg);
		includeSuperConnector(exr);
		
		addPrimitive(new Sync(coregionSourceNode,exr.getInput()));
		
		for(int i=0;i<numMsg;i++) {
			
			addPrimitive(new Sync(
					exr.getOutput(i+1),coregionNodes.get(i)));
			
		}
	}
	
	public void addAsyncroMessage(SDAsyncroMessage msg, int index){
		boolean includesPortName = msg.getName().indexOf(':')>-1;	
		Node temp;
		
		//Sender Case
		if (msg.getSender().equals(this.participant) 
				&& !msg.isDeliveredMessage()) {
			//connect inputNode to SequencerNode with a filter
			//for the message
			temp=new Node();
			addNode(temp);
			sequencerNodes.add(temp);
			
			Filter filt=cFactory.createFilter();
			filt.setNodeTwo(temp);
			filt.setExpression(msg.getName());			
			addPrimitive(filt);
			inputChannels.add(filt);
			
			//the connectiveNode is located after a FIFO
			Node link=new Node();
			addNode(link);
			FIFO fifo=new FIFO(temp,link);
			addPrimitive(fifo);
			connectiveNodes.add(new Couple(link,index,false));
		}
		//Receiver Case
		else if (msg.getReceiver().equals(this.participant) 
				&& !msg.isSentMessage()) {
			int ind=index;
			
			//check: if this is not an instant msg then we have to
			//recover its first index in order to make his connection
			//node have the same index of the other other half of the
			//message
			if (msg.isDeliveredMessage()) {
				
				Iterator<SDAsyncroMessage> iter=aSyncMsgList.iterator();
				int i=0;
				while (iter.hasNext()) {
					SDAsyncroMessage amsg=iter.next();
					
					if (amsg.getName().equals(msg.getName()) &&
						amsg.getSender().equals(msg.getSender()) &&
						amsg.getReceiver().equals(msg.getReceiver()))
					{
						ind=(aSyncMsgIndxList.get(i)).intValue();
					}
					i++;
				}				
			}
			
			
			//just connect outputNode to the SequencerNode
			temp=new Node();
			addNode(temp);
			sequencerNodes.add(temp);
			
			Sync sync=new Sync(temp,outputNode);
			addPrimitive(sync);
			outputChannels.add(sync);
			
			if (isCoregion) {				
				coregionNodes.add(temp);
				
				//if this message is in a coregion, the connectiveNode
				//is shared with the other received message
				connectiveNodes.add(new Couple(coregionSourceNode,index,true));
			} else {
				//the connectiveNode is the same of the sequencerNode
				connectiveNodes.add(new Couple(temp,ind,true));
			}
		} 
		//case needed to sincronize sending and receiving
		else if (msg.getReceiver().equals(this.participant) 
				&& msg.isSentMessage()) {
			
			//we save this sent message in order to use its index
			//when it will be received.			
			aSyncMsgList.add(msg);
			aSyncMsgIndxList.add(new Integer(index));
		}		
	}
	
	
	public void sequentialize(){
		
		int seqDim=sequencerNodes.size();
		if(seqDim>0) {	
			sequencer=new Sequencer(seqDim);
			
			includeSuperConnector(sequencer);
			SyncDrain syncDrain;
			//link it to the sequencerNodes
			for (int i=0;i<seqDim;i++){
				syncDrain=new SyncDrain(sequencer.getPin(i+1),sequencerNodes.get(i));
				addPrimitive(syncDrain);
				sequencerConnections.add(syncDrain);
			}
		}
	}
	
	public Node getConnectiveNode(int index){
		Iterator<Couple> iter=connectiveNodes.iterator();
		
		while (iter.hasNext()) {
			Couple c=iter.next();
			
			if (c.getIndex()==index) {
				return c.getNode();
			}
		}
		
		return null;
	}
	
	public boolean isConnectiveNodeReceiver(int index){
		Iterator<Couple> iter=connectiveNodes.iterator();
		
		while (iter.hasNext()) {
			Couple c=iter.next();
			
			if (c.getIndex()==index) {
				return c.isReceiver();
			}
		}
		
		System.err.println("Error!!");
		System.exit(0);
		return false;
	}

	public Node getInputNode() {
		return inputNode;
	}

	public void replaceInputNode(Node newNode) {
		this.removeNode(inputNode);
		inputNode=newNode;
	}
	
	public Node getOutputNode() {
		return outputNode;
	}

	public void replaceOutputNode(Node newNode) {
		this.removeNode(outputNode);
		outputNode=newNode;
	}
	
	public LinkedList<Node> getSequencerNodes() {
		return sequencerNodes;
	}
	
	public int getSequencerCardinality() {
		return sequencerNodes.size();
	}
	
	public void setSequencerConnection(int index, Node newSequencerNode) {
		SyncDrain syncDrain=new SyncDrain(sequencerNodes.get(index-1),newSequencerNode);
		addPrimitive(syncDrain);
		this.sequencerConnections.add(syncDrain);
	}
	
	public void desequentialize(){
		int seqDim=sequencerNodes.size();
		if(seqDim>0) {	
			for (int i=0;i<seqDim;i++){
				this.removePrimitive(sequencerConnections.get(i));
			}
			sequencerConnections=new LinkedList<SyncDrain>();
			
			removeIncludedSuperConnector(sequencer);
			sequencer=null;
		}
	}
	
	public Node getSequencerNode(int index) {
		return sequencerNodes.get(index);
	}
	
	public Node getFirstSequencerNode() {
		return sequencerNodes.getFirst();
	}
	
	public Node getLastSequencerNode() {
		return sequencerNodes.getLast();
	}
	
	public int numberOfExchangedMessages() {
		return sequencerNodes.size();
	}

	public SDParticipant getParticipant() {
		return participant;
	}

	public LinkedList<Filter> getInputChannels() {
		return new LinkedList<Filter>(inputChannels);
	}

	public LinkedList<Sync> getOutputChannels() {
		return new LinkedList<Sync>(outputChannels);
	}
	
	
}

final class Couple {
	
	Node node;
	int index;
	boolean input;
	
	Couple(Node node,int index,boolean input) {
		this.node=node;
		this.index=index;
		this.input=input;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Node getNode() {
		return node;
	}
	public boolean isReceiver(){
		return input;
	}

}