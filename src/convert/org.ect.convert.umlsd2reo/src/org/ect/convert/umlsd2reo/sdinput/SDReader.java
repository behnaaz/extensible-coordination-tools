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
package org.ect.convert.umlsd2reo.sdinput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.ect.convert.umlsd2reo.sdmodel.*;



//assumption: we have one "tag" per line. No tags on multiple lines.
//assumption: every word is separated by a space ' ' or tab '\t'

//assumption: when defining a SD, we chose between a simple or composed one
//	a simple SD contains messages and participants, a composed one contains only
//	operators that refers to previously defined simple SD.

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * A simple parser that generate a set of sequence diagrams models from an 
 * input file.
 * 
 * @author Eccher Alessandro
 * 
 */
public class SDReader {
	
	SeqDiagramSet model=null;
	
	BufferedReader document;
	int row=-1;
	
	/*public SDReader(String fileName) throws FileNotFoundException {
		document=new BufferedReader(new FileReader(fileName));		
	}*/
    
    public SDReader(InputStreamReader in) {
		document=new BufferedReader(in);		
	}
    
	public void process() {
		
		SeqDiagram currentDiagram=null;
		
		model=new SeqDiagramSet();
		
		try {
			String line;
			
			StringTokenizer toker;			
			String token=null;
			
			//keeps trace of the state of the reading:
			// -1 we are not reading anything
			// 0 reading a basic SD
			// 1 reading participants
			// 2 participants already read
			// 3 reading actions
			// 4 ready to close
			// 5 reading a composed SD
			// 6 reading operators
			int state=-1;
			
			//row counter
			row=0;
			
			//we read all the document now
			while ((line = document.readLine()) != null){
				
				row++;
				toker=new StringTokenizer(line);
				//processing the document line per line
				
				//if the line is not empty
				if (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//now discriminate between tags
					
					//a new Sequence diagram starts
					if (token.equals("SD") ) {
						//if this token is out of place
						if (state!= -1)	{
							printError();							
						}
						
						String sdName=null;
						String type=null;
						
						//reading arguments now
						while (toker.hasMoreTokens()) {
							token=toker.nextToken();
							
							//reading name
							if (token.equals("name")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								sdName=unWrap(token);
								if (sdName==null)
									printError();
							}
							
							//reading name
							if (token.equals("type")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								type=unWrap(token);
								if (type==null)
									printError();								
							}
						}
						
						if (sdName==null || type==null){
							
							printError();
						}
						if (type.equals("simple")){
							state=0;
						}
						else if (type.equals("composed")){ 
							state=5;
						}
						
						//now is time to instantiate a diagram
						currentDiagram=new SeqDiagram(sdName);
					}
					
					//a sequence diagram ends
					if (token.equals("/SD") ) {
						//if this token is out of place
						if (state!= 4)	{
							printError();							
						}
					
						//proceed to save this diagram into the
						//list
						model.addDiagram(currentDiagram);						
						currentDiagram=null;
						state=-1;
					}
					
					//we enter into the Participant declaration space
					if (token.equals("SD.PARTICIPANTS") ) {
					
						//if this token is out of place
						if (state!= 0)	{
							printError();							
						}
						state=1;
					}
					
					//we exit the Participant declaration space
					if (token.equals("/SD.PARTICIPANTS") ) {
						//if this token is out of place
						if (state!= 1)	{
							printError();							
						}
						state=2;
					}
					
					//we read a participant
					if (token.equals("PARTICIPANT") ) {
						
						//if this token is out of place
						if (state!= 1)	{
							printError();							
						}
						
						String partName=null;
						
						//reading arguments now
						while (toker.hasMoreTokens()) {
							token=toker.nextToken();
							
							//reading name
							if (token.equals("name")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								partName=unWrap(token);
								if (partName==null)
									printError();								
							}							
						}			
						
						
						
					//adding a participant to the current diagram
					//handling eventual errors
					if (currentDiagram.addParticipant(partName)==1)
						
						printError();
					}
					
					//we enter into the Action declaration space
					if (token.equals("SD.ACTIONS") ) {
						//if this token is out of place
						if (state!= 2)	{
							printError();							
						}
						state=3;
					}
					
					//we exit the Action declaration space
					if (token.equals("/SD.ACTIONS") ) {
						//if this token is out of place
						if (state!= 3)	{
							printError();							
						}
						state=4;
					}
					
					//we read a syncronized message
					if (token.equals("MESSAGE.SYNCRONIZED") ) {
						//if this token is out of place
						if (state!= 3)	{
							printError();							
						}
						
						String msgName=null;
						String sender=null;
						String receiver=null;
						
						//reading arguments now
						while (toker.hasMoreTokens()) {							
							token=toker.nextToken();
							
							//reading name
							if (token.equals("name")) {								
								toker.nextToken();
								token=toker.nextToken();
																
								msgName=unWrap(token);
								
								if (msgName==null)									
									printError();								
							}
							
							//reading sender
							else if (token.equals("sender")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								sender=unWrap(token);
								
								if (sender==null)									
									printError();								
							}
							
							//reading receiver
							else if (token.equals("receiver")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								receiver=unWrap(token);
								if (receiver==null)									
									printError();								
							}
							
						}
					
						//if we didn't read an argument, raise an error
						if (msgName==null || sender==null || receiver==null) {
							
							printError();
						}
					//adding the message to the current diagram
					//handling eventual errors
					if (currentDiagram.addSyncroMsg(msgName,sender,receiver)==1)
						printError();
					}
					
					//we read an asyncronized message
					if (token.equals("MESSAGE.ASYNCRONIZED") ) {
						//if this token is out of place
						if (state!= 3)	{
							printError();							
						}
						
						String msgName=null;
						String sender=null;
						String receiver=null;
						int kind=SDAsyncroMessage.INSTANTDELIVERY;
						
						//reading arguments now
						while (toker.hasMoreTokens()) {
							token=toker.nextToken();
							
							
							
							//reading name
							if (token.equals("name")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								msgName=unWrap(token);
								if (msgName==null)
									printError();								
							}
							
							//reading sender
							if (token.equals("sender")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								sender=unWrap(token);
								if (sender==null)
									printError();								
							}
							
							//reading receiver
							if (token.equals("receiver")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								receiver=unWrap(token);
								if (receiver==null)
									printError();								
							}
							
							//reading state
							if (token.equals("state")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								String string=unWrap(token);
								if (receiver==null)
									printError();
								
								if (string.equals("sent"))
									kind=SDAsyncroMessage.SENT;
								else if (string.equals("received"))
									kind=SDAsyncroMessage.RECEIVED;
								else if (string.equals("instant"))
									;
							}
						}
					
						//if we didn't read an argument, raise an error
						if (msgName==null || sender==null || receiver==null)
							printError();
					
						//adding the message to the current diagram
						//handling eventual errors
						if (currentDiagram.addAsyncroMsg(msgName,sender,receiver,kind)==1)
							printError();
						}
					
					//we read a found message
					if (token.equals("MESSAGE.FOUND") ) {
						//if this token is out of place
						if (state!= 3)	{
							printError();							
						}
						
						String msgName=null;
						String receiver=null;
						
						//reading arguments now
						while (toker.hasMoreTokens()) {
							token=toker.nextToken();
							
							//reading name
							if (token.equals("name")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								msgName=unWrap(token);
								if (msgName==null)
									printError();								
							}
							
							//reading receiver
							if (token.equals("receiver")) {
								toker.nextToken();
								token=toker.nextToken();		
								
								receiver=unWrap(token);
								if (receiver==null)
									printError();								
							}
						}
					
						//if we didn't read an argument, raise an error
						if (msgName==null || receiver==null)
							printError();
					
						//adding the message to the current diagram
						//handling eventual errors
						if (currentDiagram.addFoundMsg(msgName,receiver)==1)
							printError();
					}
					
			
					//we read a lost message
					if (token.equals("MESSAGE.LOST") ) {
						
					//if this token is out of place
					if (state!= 3)	{
						printError();							
					}
					
					String msgName=null;
					String sender=null;
					
					//reading arguments now
					while (toker.hasMoreTokens()) {
						token=toker.nextToken();
						
						//reading name
						if (token.equals("name")) {
							toker.nextToken();
							token=toker.nextToken();		
							
							msgName=unWrap(token);
							if (msgName==null)
								printError();								
						}
						
						//reading sender
						if (token.equals("sender")) {
							toker.nextToken();
							token=toker.nextToken();		
							
							sender=unWrap(token);
							if (sender==null)
								printError();								
						}
					}
				
					//if we didn't read an argument, raise an error
					if (msgName==null || sender==null)
						printError();
				
					//adding the message to the current diagram
					//handling eventual errors
					if (currentDiagram.addLostMsg(msgName,sender)==1)
						printError();
					}
								
				
				//we read the begin of a coregion message
				if (token.equals("COREGION.START") ) {
				//if this token is out of place
				if (state!= 3)	{
					printError();							
				}
				
				String participant=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading name
					if (token.equals("participant")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						participant=unWrap(token);
						if (participant==null)
							printError();								
					}					
				}
			
				//if we didn't read an argument, raise an error
				if (participant==null)
					printError();
			
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addCoregionStart(participant)==1)
					printError();
				}			
			
				
				
				//we read the begin of a coregion message
				if (token.equals("COREGION.END") ) {
				//if this token is out of place
				if (state!= 3)	{
					printError();							
				}
				
				String participant=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading name
					if (token.equals("participant")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						participant=unWrap(token);
						if (participant==null)
							printError();								
					}					
				}
			
				//if we didn't read an argument, raise an error
				if (participant==null)
					printError();
			
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addCoregionEnd(participant)==1)
					printError();
				}
				
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.ALTERNATIVE") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member1=null;
				String member2=null;
				String guard1=null;
				String guard2=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member1")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member1=unWrap(token);
						if (member1==null)
							printError();								
					}
					
					//reading member2
					if (token.equals("member2")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member2=unWrap(token);
						if (member1==null)
							printError();								
					}
					
					//reading condition1
					if (token.equals("guard1")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						guard1=unWrap(token);
						if (guard1==null)
							printError();								
					}
					
					//reading member2
					if (token.equals("guard2")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						guard2=unWrap(token);
						if (guard2==null)
							printError();								
					}
					
				}
			
				//if we didn't read an argument, raise an error
				if (member1==null || member2==null || guard1==null|| guard2==null)
					printError();
				
				SeqDiagram d1=model.getDiagram(member1);
				SeqDiagram d2=model.getDiagram(member2);
				
				//if the members are not already defined, raise an error
				if (d1==null || d2==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addAlternative(d1, d2, guard1, guard2)==1)
					printError();
				}
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.OPTION") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member=unWrap(token);
						if (member==null)
							printError();								
					}		
				}
			
				//if we didn't read an argument, raise an error
				if (member==null)
					printError();
				
				SeqDiagram d=model.getDiagram(member);				
				
				//if the members are not already defined, raise an error
				if (d==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addOption(d)==1)
					printError();
				}
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.PARALLEL") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member1=null;
				String member2=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member1")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member1=unWrap(token);
						if (member1==null)
							printError();								
					}
					
					//reading member2
					if (token.equals("member2")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member2=unWrap(token);
						if (member1==null)
							printError();								
					}							
				}
			
				//if we didn't read an argument, raise an error
				if (member1==null || member2==null)
					printError();
				
				SeqDiagram d1=model.getDiagram(member1);
				SeqDiagram d2=model.getDiagram(member2);
				
				//if the members are not already defined, raise an error
				if (d1==null || d2==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addParallel(d1, d2)==1)
					printError();
				}
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.STRICT") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member1=null;
				String member2=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member1")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member1=unWrap(token);
						if (member1==null)
							printError();								
					}
					
					//reading member2
					if (token.equals("member2")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member2=unWrap(token);
						if (member1==null)
							printError();								
					}					
				}
			
				//if we didn't read an argument, raise an error
				if (member1==null || member2==null)
					printError();
				
				SeqDiagram d1=model.getDiagram(member1);
				SeqDiagram d2=model.getDiagram(member2);
				
				//if the members are not already defined, raise an error
				if (d1==null || d2==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addStrict(d1, d2)==1)
					printError();
				}
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.WEAK") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member1=null;
				String member2=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member1")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member1=unWrap(token);
						if (member1==null)
							printError();								
					}
					
					//reading member2
					if (token.equals("member2")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member2=unWrap(token);
						if (member1==null)
							printError();								
					}
				}
			
				//if we didn't read an argument, raise an error
				if (member1==null || member2==null)
					printError();
				
				SeqDiagram d1=model.getDiagram(member1);
				SeqDiagram d2=model.getDiagram(member2);
				
				//if the members are not already defined, raise an error
				if (d1==null || d2==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addWeak(d1, d2)==1)
					printError();
				}
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.LOOP") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member=null;
				String guard=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member=unWrap(token);
						if (member==null)
							printError();								
					}
					//reading condition1
					if (token.equals("guard")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						guard=unWrap(token);
						if (guard==null)
							printError();								
					}		
				}
			
				//if we didn't read an argument, raise an error
				if (member==null|| guard==null)
					printError();
				
				SeqDiagram d1=model.getDiagram(member);
				
				//if the members are not already defined, raise an error
				if (d1==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addLoop(d1, guard)==1)
					printError();
				}
				
				//we read the begin of an alternative operator
				if (token.equals("OPERATOR.REFERENCE") ) {
				//if this token is out of place
				if (state!= 6)	{
					printError();							
				}
				
				String member=null;
				
				//reading arguments now
				while (toker.hasMoreTokens()) {
					token=toker.nextToken();
					
					//reading member1
					if (token.equals("member")) {
						toker.nextToken();
						token=toker.nextToken();		
						
						member=unWrap(token);
						if (member==null)
							printError();								
					}		
				}
			
				//if we didn't read an argument, raise an error
				if (member==null)
					printError();
				
				SeqDiagram d1=model.getDiagram(member);
				
				//if the members are not already defined, raise an error
				if (d1==null)
					printError();				
				
				//adding the message to the current diagram
				//handling eventual errors
				if (currentDiagram.addReference(d1)==1)
					printError();
				}
				
				//we enter into the Participant declaration space
				else if (token.equals("SD.OPERATORS") ) {
					//if this token is out of place
					if (state!= 5)	{
						printError();							
					}
					state=6;
				}
				
				//we exit the Participant declaration space
				else if (token.equals("/SD.OPERATORS") ) {
					//if this token is out of place
					if (state!= 6)	{
						printError();							
					}
					state=4;
				}
				}
				
			}
		}
		catch (IOException e) {
			System.err.println("IO Error into SDReader.class/nImpossible to continue, program terminated.");
			e.printStackTrace();
			System.exit(0);
		}	
		
	}
	
	private void printError() {
		System.out.println("Error in input file at line: "+row);
		System.exit(0);
	}
	
	private String unWrap(String token) {
		int lenght=token.length();
		
		if (token.charAt(0)=='"' || token.charAt(lenght)=='"')
			return token.substring(1, lenght-1);
		else 
			return null;		
	}

	public SeqDiagramSet getModel() {
		return model;
	}
	
}
