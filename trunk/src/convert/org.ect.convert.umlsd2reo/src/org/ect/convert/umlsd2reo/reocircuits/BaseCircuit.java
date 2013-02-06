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

import org.ect.convert.umlsd2reo.sdmodel.SDAction;
import org.ect.convert.umlsd2reo.sdmodel.SDAsyncroMessage;
import org.ect.convert.umlsd2reo.sdmodel.SDCoregionEnd;
import org.ect.convert.umlsd2reo.sdmodel.SDCoregionStart;
import org.ect.convert.umlsd2reo.sdmodel.SDFoundMessage;
import org.ect.convert.umlsd2reo.sdmodel.SDLostMessage;
import org.ect.convert.umlsd2reo.sdmodel.SDMessage;
import org.ect.convert.umlsd2reo.sdmodel.SDParticipant;
import org.ect.convert.umlsd2reo.sdmodel.SDSyncroMessage;
import org.ect.convert.umlsd2reo.sdmodel.SeqDiagram;
import org.ect.reo.*;
import org.ect.reo.channels.*;

import java.util.*;


/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure for a circuit that represent SD interactions
 * with no occurrences of operators.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class BaseCircuit extends ActivableCircuit {
	
	int numPArticipants=0;
	//LinkedList<Triple> participantsData=new LinkedList<Triple>();
	
	LinkedList<LifeLineConnector> lifeLineConnectors=new LinkedList<LifeLineConnector>();
		
	public BaseCircuit (SeqDiagram sd){
		super(sd.getName());
		Node firstSequenceNode=null;
		Node lastSequenceNode=null;
		
		//extracting Participant list
		List<SDParticipant> participants=sd.getParticipants();
		Iterator<SDParticipant> partIter=participants.iterator();
		numPArticipants=participants.size();
		
		LinkedList<SDAction> actions=sd.getActions();
		Iterator<SDAction> actIter;
		
		int actionCounter=0;
		SDAction action;
		
		int firstMessageIndex=Integer.MAX_VALUE;
		int lastMessageIndex=Integer.MIN_VALUE;
		LifeLineConnector firstLifeCon=null;
		LifeLineConnector lastLifeCon=null;
		
		//for each participant
		while (partIter.hasNext()) {
			SDParticipant participant=(SDParticipant)partIter.next();
			
			LifeLineConnector life=new LifeLineConnector(participant);
			
			//store participants data
			/*
			Triple t=new Triple(participant.getName(),
					life.getInputNode(),
					life.getOutputNode());
			participantsData.add(t);
			*/
			
			actionCounter=0;			
			//process the action
			actIter=actions.iterator();
			while (actIter.hasNext()) {
				action=actIter.next();
				actionCounter++;
				
				//if the action is related to this participant
				if (action.isRelatedTo(participant)) {
					
					if (action instanceof SDMessage) {
						//checks to keep trace of the first and
						//last participant that send messages
						if (
								(firstMessageIndex>actionCounter &&
								participant.equals(((SDMessage)action).getSender()))
								||
								(firstMessageIndex>actionCounter &&
								action instanceof SDFoundMessage &&
								participant.equals(((SDMessage)action).getReceiver()))	
						) {
							firstMessageIndex=actionCounter;
							
							firstLifeCon=life;
						}
						else if (
								(lastMessageIndex<actionCounter &&
								participant.equals(((SDMessage)action).getReceiver()))
								||
								(firstMessageIndex>actionCounter &&
								action instanceof SDLostMessage &&
								participant.equals(((SDMessage)action).getSender()))								
						) {
							lastMessageIndex=actionCounter;
							
							lastLifeCon=life;				
						} 
						
						if (action instanceof SDSyncroMessage) {
							SDSyncroMessage mes=(SDSyncroMessage) action;
							
							life.addSyncroMessage(mes, actionCounter);
						}
						else if (action instanceof SDAsyncroMessage) {
							SDAsyncroMessage mes=(SDAsyncroMessage) action;
							
							life.addAsyncroMessage(mes, actionCounter);
						}
						else if (action instanceof SDLostMessage) {
							SDLostMessage mes=(SDLostMessage) action;
							
							life.addLostMessage(mes, actionCounter);
						}
						else if (action instanceof SDFoundMessage) {
							SDFoundMessage mes=(SDFoundMessage) action;
							
							life.addFoundMessage(mes, actionCounter);
						}
					}
					else if (action instanceof SDCoregionStart) {
												
						life.defineCoregionStart(actionCounter);
					}
					else if (action instanceof SDCoregionEnd) {					
						life.defineCoregionEnd(actionCounter);
					}
				}				
			}
			
			//now that the connector has loaded all the information
			//we say him to complete itself with a sequencer
			life.sequentialize();
			lifeLineConnectors.add(life);
			
		}
		
		//memorizing first and last LifeLine connectors node
		//between all the lifelines
		firstSequenceNode=firstLifeCon.getFirstSequencerNode();
		lastSequenceNode=lastLifeCon.getLastSequencerNode();
		
		//now we add all the lifeLine connectors to the final resulting
		//connector: to do so first we have to connect each couple of
		//connectiveNodes that share the same index.
		Node first=null;
		Node second=null;
		boolean firstIsReceiver=false;
				
		boolean flag1=true;
		boolean flag2=true;
		
		LifeLineConnector con;
		for (int i=1;i<=actionCounter;i++) {
			
			//browse all lifelineConnectors
			Iterator<LifeLineConnector> iter=lifeLineConnectors.iterator();
			while (iter.hasNext() && flag1) {
				con=(LifeLineConnector)iter.next();
				first=con.getConnectiveNode(i);
				
				//if we found the one node of the couple
				if (first!=null) {
					//stop cycling for the first member 
					flag1=false;
					//determine channel direction
					firstIsReceiver=con.isConnectiveNodeReceiver(i);
					
					//but look for the second member of the couple
					//into the other connectors
					while (iter.hasNext() && flag2) {
						con=(LifeLineConnector)iter.next();
						second=con.getConnectiveNode(i);
						
						//if we found the second node of the couple
						if (second!=null) {
							
							//link the 2 lifeLineConnectors with
							//syncro channel between the nodes
							Sync sync;
							if (firstIsReceiver) {
								sync=new Sync(second,first);
								addPrimitive(sync);
							} else {
								sync=new Sync(first,second);
								addPrimitive(sync);								
							}
						}
						//if no node were founded, nothing happens
					}
					
				}
				//if no node were founded, nothing happens
			}
			//reset variables for next index search
			flag1=true;
			flag2=true;
			second=null;
			first=null;
		}
		
		//complete the circuit with the activation line
				
		Node temp=new Node();
		addNode(temp);
		FIFO fifo=new FIFO(activationInput,temp);
		addPrimitive(fifo);
		
		Node temp2=new Node();
		addNode(temp2);
		Sync sync=new Sync(temp,temp2);
		addPrimitive(sync);
		
		//link this circuit first sequencer node to the external line
		SyncDrain syncDrain=new SyncDrain(temp2,firstSequenceNode);
		addPrimitive(syncDrain);
		
		temp=new Node();
		addNode(temp);
		fifo=new FIFO(temp2,temp);
		addPrimitive(fifo);
		
		//link this circuit last sequencer node to the external line
		syncDrain=new SyncDrain(temp,lastSequenceNode);
		addPrimitive(syncDrain);
		
		temp2=new Node();
		addNode(temp2);
		sync=new Sync(temp,temp2);
		addPrimitive(sync);
		
		fifo=new FIFO(temp2,activationOutput);
		addPrimitive(fifo);		
	
		Iterator<LifeLineConnector> iter=lifeLineConnectors.iterator();
		while (iter.hasNext()) {
			includeSuperConnector(iter.next());
		}
	}	
	
	public List<SDParticipant> getParticipants(){
		Iterator<LifeLineConnector> iter=lifeLineConnectors.iterator();
		LinkedList<SDParticipant> result=new LinkedList<SDParticipant>();
		
		while(iter.hasNext()) {
			result.add(iter.next().getParticipant());
		}
		return result;
	}

	public LifeLineConnector getParticipantLifeLifeConnector(SDParticipant participant) {
		Iterator<LifeLineConnector> iter=lifeLineConnectors.iterator();
		
		while(iter.hasNext()) {
			LifeLineConnector c=iter.next();
			if (c.getParticipant().equals(participant))
				return c; 
		}
		return null;
	}	

}
/*
class Triple {
	String name;
	Node sink;
	Node source;
	
	Triple(String name, Node input, Node output){
		this.name=name;
		this.sink=input;
		this.source=output;
	}

	public String getName() {
		return name;
	}

	public Node getInput() {
		return sink;
	}

	public Node getOutput() {
		return source;
	}
}
*/