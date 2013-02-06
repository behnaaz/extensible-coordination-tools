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

import java.util.Iterator;
import java.util.List;


import org.ect.convert.umlsd2reo.sdmodel.SDParticipant;
import org.ect.convert.umlsd2reo.sdmodel.SeqDiagram;
import org.ect.reo.*;
import org.ect.reo.channels.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * Provides the structure for an Weak Sequencing Execution Circuit.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class WeakSequencingCircuit extends ActivableCircuit {
	BaseCircuit member1;
	BaseCircuit member2;
	
	WeakSequencingCircuit (SeqDiagram firstMember, SeqDiagram secondMember) {
		super("Weak sequencing");
		
		member1=new BaseCircuit(firstMember);
		member2=new BaseCircuit(secondMember);
		
		includeSuperConnector(member1);
		includeSuperConnector(member2);
		
		//now create the structure of this connector
		Node temp=new Node();
		addNode(temp);
		
		FIFO fifo=new FIFO(activationInput,temp);
		addPrimitive(fifo);
		
		Sync sync=new Sync(temp,member1.getActivationInput());		
		addPrimitive(sync);
		
		sync=new Sync(temp,member2.getActivationInput());		
		addPrimitive(sync);
		
		temp=new Node();
		addNode(temp);
		
		LossySync lossySync=new LossySync(member1.getActivationOutput(),temp);		
		addPrimitive(lossySync);
		
		lossySync=new LossySync(member2.getActivationOutput(),temp);		
		addPrimitive(lossySync);
		
		SyncDrain syncDrain=new SyncDrain(member1.getActivationOutput(),member2.getActivationOutput());
		addPrimitive(syncDrain);
		
		fifo=new FIFO(temp,activationOutput);
		addPrimitive(fifo);
		
		//now we have to check if the basic circuits share some participants
		//if so, it's necessary to merge the input and output node of the participant
		//into the 2 circuits (since it's the same participant, it's a nonsense to have
		//2 input nodes and 2 output nodes for it).
		Iterator<SDParticipant> i1=member1.getParticipants().iterator();
		List<SDParticipant> l2=member2.getParticipants();
		boolean flag=true;
		while (i1.hasNext()) {
			SDParticipant p1=i1.next();
			
			Iterator<SDParticipant> i2=l2.iterator();
			
			while(i2.hasNext() && flag) {
				SDParticipant p2=i2.next();
				
				//if a participant is shared, proceed to merge nodes
				if (p1.equals(p2)) {
					
					//creating new nodes
					Node sharedInputNode=new Node();
					sharedInputNode.setName(p1.getName()+" input node");
					addNode(sharedInputNode);
					Node sharedOutputNode=new Node();
					sharedOutputNode.setName(p1.getName()+" output node");
					addNode(sharedOutputNode);
					
					LifeLineConnector lifeLine1=member1.getParticipantLifeLifeConnector(p1);
					LifeLineConnector lifeLine2=member2.getParticipantLifeLifeConnector(p2);
					
					//redirecting all input channels to the shared inputnode
					List<Filter> inputChannelList1=lifeLine1.getInputChannels();
					List<Filter> inputChannelList2=lifeLine2.getInputChannels();
					
					Iterator<Filter> inputChannelIter1=inputChannelList1.iterator();
					Iterator<Filter> inputChannelIter2;
					
					//remember that if there are filters filtering the same message in 
					//both the lifelines, we have to merge them
					Filter filter;
					Filter filter2;
					boolean flag2=true;
					while(inputChannelIter1.hasNext()){
						 filter=inputChannelIter1.next();
						 
						 inputChannelIter2=inputChannelList2.iterator();
						 while (inputChannelIter2.hasNext()){
							 filter2=inputChannelIter2.next();
							 
							 //we have to merge the filters
							 if (filter2.getExpression().equals(filter.getExpression())
									 && flag2) {
								 
								 ExclusiveRouter excl=new ExclusiveRouter(2);
								 Filter newFilter=(new ChannelsFactory()).createFilter();
								 newFilter.setExpression(filter.getExpression());
								 newFilter.setNodeOne(sharedInputNode);
								 newFilter.setNodeOne(excl.getInput());
								 addPrimitive(newFilter);
								 
								 Sync syncro=new Sync(excl.getOutput(1),filter.getNodeTwo());
								 addPrimitive(syncro);
								 syncro=new Sync(excl.getOutput(2),filter2.getNodeTwo());
								 addPrimitive(syncro);
								 //all connections are made now, but we have to remove
								 //the replaced filters form the model
								 Connector connector = (Connector) filter.eContainer();
								 connector.removePrimitive(filter);
								 connector.removePrimitive(filter2);
								 
								 //finally, we remove also the filter from the list of the
								 //second connector (because we have already replaced it)
								 inputChannelList2.remove(filter2);
								 flag2=false;
							 }
						 }
						 //if we did not found a twin filter in the second connector,
						 //we just have to modify the existing filter 
						 if(flag2) {
							 filter.setNodeOne(sharedInputNode);
//TODO							 filter.setConnector(this.representedConnector);
							 Connector connector = (Connector) filter.eContainer();
							 connector.getPrimitives().add(filter);
						 }
						 
						 flag2=true;
					}
					inputChannelIter2=inputChannelList2.iterator();
					while(inputChannelIter2.hasNext()){
						 filter=inputChannelIter2.next();
						 
						 filter.setNodeOne(sharedInputNode);
						//TODO						 filter.setConnector(this.representedConnector);
						 Connector connector = (Connector) filter.eContainer();
						 connector.getPrimitives().add(filter);
					}
					
					//redirecting all output channels to the shared outputnode
					List<Sync> outputChannelList=lifeLine1.getOutputChannels();
					outputChannelList.addAll(lifeLine2.getOutputChannels());
					Sync syncroOutput;
					Iterator<Sync> outPutChannelIterator=outputChannelList.iterator();
					while (outPutChannelIterator.hasNext()) {
						syncroOutput=outPutChannelIterator.next();
						
						syncroOutput.setNodeTwo(sharedOutputNode);
					}					
					
					//now we have to update the connectors input/output Node pointers,
					
					lifeLine1.replaceInputNode(sharedInputNode);
					lifeLine2.replaceInputNode(sharedInputNode);
					lifeLine1.replaceOutputNode(sharedOutputNode);
					lifeLine2.replaceOutputNode(sharedOutputNode);					
					
					flag=false;	
					
					
					
					//and now, we proceed by replacing the 2 sequencers of the lifeLines
					//with a single shared one.
					int cardinality1=lifeLine1.numberOfExchangedMessages();
					int cardinality2=lifeLine2.numberOfExchangedMessages();
					int totalCardinality=cardinality1+cardinality2;
					Sequencer sharedSequencer=new Sequencer(totalCardinality);
					includeSuperConnector(sharedSequencer);
					
					lifeLine1.desequentialize();
					lifeLine2.desequentialize();
					
					for (int i=0;i<totalCardinality;i++) {
						if(i<cardinality1)
							lifeLine1.setSequencerConnection(i+1, sharedSequencer.getPin(i+1));
						else
							lifeLine2.setSequencerConnection(i-cardinality1+1,
									sharedSequencer.getPin(i+1));						
					}					
				}
			}
			flag=true;
		}
	}
}
