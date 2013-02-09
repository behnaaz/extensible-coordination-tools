/*
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.ect.reo.distengine.common;

import scala.actors.Actor

//////////////
// MESSAGES //
//////////////
//  -- were previously replaced by TupleN("R/T/B/S",arg1,..,argN)
// Usually,  Node: E=EndID ;  Primitive - E=End
/**
* Messages passed between actors, or between primitives and ends.
* When a message constructor ends in <code>P</code>,
* then it is send via methods between primitives and their ends, locally.
* For this reason, it is only important to have immutable messages (without references
* and state) for the messages shared among actors.
*/ 
abstract class Msg

/** Messages responsable for evolving the state of each end in the <b>commitment</b> stage. */
sealed abstract class CommitMsg extends Msg

case class Request(rank:Int)                        extends CommitMsg 
case class StrongerReq(rank:Int)                    extends CommitMsg
case class RequestP(rank:Int, from:AbstractEnd)     extends CommitMsg 
case class Table(t:BehaviourRep[StepRep])           extends CommitMsg
case class TableP(t:BehaviourRep[StepRep], from:AbstractEnd) extends CommitMsg
case class Busy(rank:Int)                           extends CommitMsg
                 
                       
/** Messages responsable for evolving the state of each end in the <b>communication</b> stage. */
sealed abstract class CommunicateMsg extends Msg
case class DataStep(d:Any,step:StepRep)             extends CommunicateMsg
case class SingleStep(step:StepRep)                 extends CommunicateMsg
case class AskData(step:StepRep)                    extends CommunicateMsg
case class ReplyData(d:Any)                         extends CommunicateMsg

/** Special message to suspend a primitive*/
case class Suspend(from:Utils.EndLID)               extends Msg


/** Messages to manage the life cycle and observations of actors. */
abstract class AdminMsg extends Msg
case class Kill()                                           extends AdminMsg
case class Status()                                         extends AdminMsg
case class ResetP()                                         extends AdminMsg
case class Reset()/*(primname:Utils.PrimID)*/               extends AdminMsg
case class Abort()											extends AdminMsg


/** Messages sent during reconfiguration stage of the connector.
*   They apply to primitives and engines. */
abstract class ReconfigMsg extends Msg
case class Release(lock:String)                 extends ReconfigMsg // Sent to primitives
case class AddEnd(otherPrim:AbstractPrimitive[AbstractBehaviour],io:Utils.IOType,from:Utils.EndLID,to:EndUID)
                                                extends ReconfigMsg // Sent to Nodes. Create new end, optional reply port, iotype
case class RemoveEnd(endname:EndUID)            extends ReconfigMsg // Sent to Nodes. Remove end
case class RemovePrim()                         extends ReconfigMsg // Sent to Primitives. Ask nodes to remove ends and exits.
case class RemoveNode(enduids:Set[EndUID])      extends ReconfigMsg // Sent to Noded. Just exits node.
case class SetPort(otherPort:Utils.Port,from:Utils.EndLID,to:EndUID)
                                                extends ReconfigMsg // Sent to Primitives. Set the port of an end to a given prim
case class AddEndFlow(from:Utils.PrimID,endflow:Utils.EndFlow)
                                                extends ReconfigMsg // Sent to DistNodes.
                                                           
case class Connect(p:Pattern,info:List[Any])    extends ReconfigMsg // Sent to engines and distnodes
case class Disconnect(p:Pattern,info:List[Any]) extends ReconfigMsg // sent to engines and distnodes

case class Create(p:Pattern,info:List[Any])     extends ReconfigMsg // Sent to engines.
case class Remove(p:Pattern,info:Any)           extends ReconfigMsg // Sent to engines.


/** Messages between the engine and the engine manager. */
abstract class EngineMsg extends Msg
case class AskContent()                                     extends EngineMsg
case class Content(cont:List[PrimContent],sender:Int)       extends EngineMsg
case class UpdContent(prim:PrimContent,tag:Int,sender:Int)  extends EngineMsg
case class NewNode(node:RedrumPair[Int,Int])                extends EngineMsg
case class AddListener(hash:Int)                            extends EngineMsg
case class RmListener(hash:Int)                             extends EngineMsg
case class ErrorMsg(msg:String)	                            extends EngineMsg
                  
///** Specific messages sent to and from the engine. Not in used so far. */
//abstract class EngineMsg extends Msg

/** Patterns to represent engines, primitives, and nodes. */
abstract class Pattern extends Msg
case object EnginePat  extends Pattern
case object PrimPat    extends Pattern
case object NodePat    extends Pattern

/** Reply from a primitive, with the hashcode of a given end, with its name and associated node. */
case class EndContent(endhash:Int,nodehash:Int,endname:String)  extends Msg
/** Reply from a primitive, with the hashcode of a given primitive, with associated information. */
case class PrimContent(name:String,primhash:Int,sourceends:List[EndContent],
                         sinkends:List[EndContent],state:PrimState) extends Msg

/** A hand made pair, because of serialization problems. Works, but lists can also be used.*/
case class RedrumPair[+A,+B](a:A,b:B) {
  def _1 = a
  def _2 = b
}
/** A hand made triple, because of serialization problems. Works, but lists can also be used.*/
case class RedrumTriple[+A,+B,+C](a:A,b:B,c:C) {
  def _1 = a
  def _2 = b
  def _3 = c
}
