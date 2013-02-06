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
package org.ect.convert.umlsd2reo.sdmodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * A class that contains the model of a Sequence Diagram.
 * 
 * @author Eccher Alessandro
 */
public class SeqDiagram {

	String name;
	
	boolean includeOperators=false;
	
	LinkedList<SDParticipant> participants=new LinkedList<SDParticipant>();
	LinkedList<SDAction> actions=new LinkedList<SDAction>();
	
	/**
	 * Constructor
	 * 
	 * @param name The name of the diagram.
	 */
	public SeqDiagram(String name){
		this.name=name;
	}
	
	/**
	 * Returns a list of all the Participants in the diagram. 
	 * 
	 * @return a list of all the Participants int he diagram.
	 */
	public List<SDParticipant> getParticipants() {
		return participants;
	}
	
	/**
	 * Add a Participant to the Diagram.
	 * 
	 * @param participantName The name of the new Participant
	 * @return 0 if the operation succeeds, 1 if not.
	 */
	public int addParticipant(String participantName) {
		//check if the name is already used
		Iterator<SDParticipant> iter=participants.iterator();
		while (iter.hasNext()) {
			//if so, return error code
			if ((iter.next()).name.equals(participantName))
				return 1;
		}
		
		//otherway, add it to the list
		this.participants.add(new SDParticipant(participantName));
		
		return 0;
	}	
	
	/**
	 * Returns a list of the Actions in the diagram.
	 * 
	 * @return a list of the Actions in the diagram.
	 */
	public LinkedList<SDAction> getActions() {
		return actions;
	}
	
	/**
	 * Add a syncronous message to the diagram. Sender and receiver must be
	 * different and already included into the diagram.
	 * 
	 * @param msgName the name of the message.
	 * @param senderName the sender of the message.
	 * @param receiverName the receiver of the message.
	 * @return 0 if the operation has positive outcome, 1 if it doesn't.
	 */
	public int addSyncroMsg(String msgName,String senderName,String receiverName) {
		//checking 4 errors: sender and receiver are the same
		if(senderName.equals(receiverName))
			return 1;

		//checking 4 errors: sender or receiver are unknown
		SDParticipant sender=null;
		SDParticipant receiver=null;
		SDParticipant temp;
		
		Iterator<SDParticipant> iter=participants.iterator();
		
		while (iter.hasNext()) {
			//if so, return error code
			temp=iter.next();
			
			if (temp.name.equals(senderName)) {
				sender=temp;
			}
			else if (temp.name.equals(receiverName)) {
				receiver=temp;
			}			
		}
		if (sender==null || receiver==null)
			return 1;
		
		SDSyncroMessage msg=new SDSyncroMessage(sender, receiver);
		msg.setName(msgName);
		this.actions.add(msg);
		return 0;
	}
	
	/**
	 * Add an asyncronous message to the diagram. Sender and receiver must be
	 * different and already included into the diagram.
	 * 
	 * @param msgName the name of the message.
	 * @param senderName the sender of the message.
	 * @param receiverName the receiver of the message.
	 * @return 0 if the operation has positive outcome, 1 if it doesn't.
	 */
	public int addAsyncroMsg(String msgName,String senderName,String receiverName, int msgKind) {
		//checking 4 errors: sender and receiver are the same
		if(senderName.equals(receiverName))
			return 1;

		//checking 4 errors: sender or receiver are unknown
		SDParticipant sender=null;
		SDParticipant receiver=null;
		SDParticipant temp;
		
		Iterator<SDParticipant> iter=participants.iterator();
		
		while (iter.hasNext()) {
			//if so, return error code
			temp=iter.next();
			
			if (temp.name.equals(senderName)) {
				sender=temp;
			}
			else if (temp.name.equals(receiverName)) {
				receiver=temp;
			}			
		}
		if (sender==null || receiver==null)
			return 1;
		
		SDAsyncroMessage msg=new SDAsyncroMessage(sender, receiver);
		msg.setName(msgName);
		msg.setKind(msgKind);
		this.actions.add(msg);
		return 0;
	}
	
	/**
	 * Marks the starting point of a coregion in this diagram. All the messages
	 * that will be added after this method call will belong to this coregion.
	 * The coregion end when a call to addCoregionEnd(String participant) is
	 * performed, with the same participant.
	 * 
	 * @param participant the participant that own the lifeline that contains the coregion. 
	 * @return 0 if the operation has positive outcome, 1 if it doesn't.
	 */
	public int addCoregionStart(String participant){
		
		//checking 4 errors: participant is unknown
		SDParticipant target=null;
		SDParticipant temp;
		
		Iterator<SDParticipant> iter=participants.iterator();
		
		while (iter.hasNext()) {
			//if so, return error code
			temp=iter.next();
			
			if (temp.name.equals(participant)) {
				target=temp;
			}			
		}
		if (target==null)
			return 1;
		
		SDCoregionStart msg=new SDCoregionStart(target);
		this.actions.add(msg);
		
		return 0;
	}
	
	/**
	 * Marks the end of an existing region.
	 * 
	 * @param participant the participant that own the lifeline that contains the coregion.
	 * @return 0 if the operation has positive outcome, 1 if it doesn't.
	 */
	public int addCoregionEnd(String participant){
		
		//checking 4 errors: participant is unknown
		SDParticipant target=null;
		SDParticipant temp;
		
		Iterator<SDParticipant> iter=participants.iterator();
		
		while (iter.hasNext()) {
			//if so, return error code
			temp=iter.next();
			
			if (temp.name.equals(participant)) {
				target=temp;
			}			
		}
		if (target==null)
			return 1;
		
		SDCoregionEnd msg=new SDCoregionEnd(target);
		this.actions.add(msg);
		
		return 0;
	}
	
	/**
	 * Add a found message to this sequence diagram.
	 * 
	 * @param msgName the name of the message.
	 * @param receiverName the receiver of the message.
	 * @return 0 if the operation has positive outcome, 1 if it doesn't. 
	 */
	public int addFoundMsg(String msgName,String receiverName) {
		
		//checking 4 errors: receiver is unknown
		SDParticipant receiver=null;
		SDParticipant temp;
		
		Iterator<SDParticipant> iter=participants.iterator();
		
		while (iter.hasNext()) {
			//if so, return error code
			temp=iter.next();
			
			if (temp.name.equals(receiverName)) {
				receiver=temp;
			}			
		}
		if (receiver==null)
			return 1;
		
		SDFoundMessage msg=new SDFoundMessage(receiver);
		msg.setName(msgName);
		this.actions.add(msg);
		return 0;
	}
	
	/**
	 * Add a lost message to this sequence diagram.
	 * 
	 * @param msgName the name of the message.
	 * @param senderName the receiver of the message.
	 * @return 0 if the operation has positive outcome, 1 if it doesn't.
	 */
	public int addLostMsg(String msgName,String senderName) {
		
		//checking 4 errors: sender is unknown
		SDParticipant sender=null;
		SDParticipant temp;
		
		Iterator<SDParticipant> iter=participants.iterator();
		
		while (iter.hasNext()) {
			//if so, return error code
			temp=iter.next();
			
			if (temp.name.equals(senderName)) {
				sender=temp;
			}			
		}
		if (sender==null)
			return 1;
		
		SDLostMessage msg=new SDLostMessage(sender);
		msg.setName(msgName);
		this.actions.add(msg);
		return 0;
	}

	/**
	 * Adds an Alternative Operator to this diagram.
	 * 
	 * @param member1 
	 * @param member2
	 * @param guard1
	 * @param guard2
	 * @return
	 */
	public int addAlternative(SeqDiagram member1,SeqDiagram member2,
			String guard1, String guard2) {
		
		this.actions.add(new SDInterOperatorAlt(member1,member2,guard1,guard2));
		includeOperators=true;
		return 0;
	}

	/**
	 * Adds an Option Operator to this diagram.
	 * 
	 * @param member
	 * @return
	 */
	public int addOption(SeqDiagram member) {
		
		this.actions.add(new SDInterOperatorOpt(member));
		includeOperators=true;
		return 0;
	}
	
	/**
	 * Adds a Parallel Operator to this diagram.
	 * 
	 * @param member1
	 * @param member2
	 * @return
	 */
	public int addParallel(SeqDiagram member1, SeqDiagram member2) {
		
		this.actions.add(new SDInterOperatorPar(member1,member2));
		includeOperators=true;
		return 0;
	}
	
	/**
	 * Add Strict Sequencing Operator to this diagram.
	 * 
	 * @param member1
	 * @param member2
	 * @return
	 */
	public int addStrict(SeqDiagram member1, SeqDiagram member2) {
		
		this.actions.add(new SDInterOperatorStrict(member1,member2));
		includeOperators=true;
		return 0;
	}
	
	/**
	 * Adds a Weak Sequencing Operator to this diagram.
	 * 
	 * @param member1
	 * @param member2
	 * @return
	 */
	public int addWeak(SeqDiagram member1, SeqDiagram member2) {
		
		this.actions.add(new SDInterOperatorWeak(member1,member2));
		includeOperators=true;
		return 0;
	}
	
	/**
	 * Adds a Loop Operator to this diagram.
	 * 
	 * @param member
	 * @param guard
	 * @return
	 */
	public int addLoop(SeqDiagram member, String guard) {
		
		this.actions.add(new SDInterOperatorLoop(member,guard));
		includeOperators=true;
		return 0;
	}
	
	/**
	 * Adds a Reference Operator to this diagram.
	 * 
	 * @param member
	 * @return
	 */
	public int addReference(SeqDiagram member) {
		
		this.actions.add(new SDReference(member));
		includeOperators=true;
		return 0;
	}
	
	/**
	 * Return the name of the diagram.
	 * 
	 * @return the name of the diagram.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns true if the diagram include operators, false if it doesn't.
	 * 
	 * @return true if the diagram include operators, false if it doesn't.
	 */
	public boolean includeOperators(){
		return includeOperators;
	}
}
