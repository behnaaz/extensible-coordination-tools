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
package org.ect.reo.distengine.redrum.genericprim;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.colouring._

import scala.actors.Actor
import scala.actors.Actor._
import collection.mutable.Map
import collection.mutable.Set
import collection.immutable.{ Set => ImSet }
import collection.{ Set => GenSet }
import org.ect.reo.distengine.redrum.{End, Primitive}


/**
 * Node with unspecified behaviour.
 *
 * Can receive new ends, which it connects and updates the current behaviour.
 * Collects the unique identifiers of each added end, that will be used in the
 * final behaviour description (eg, colouring table). So far the uid is just the
 * reference to the other end.
 * Use the constructor in CoreConstructors to create new nodes, specific
 * for the connector colouring semantics, that defines the <code>updateBeh</code>
 * method.
 * The abstract definitions are: behaviour:Beh; updateBeh(e:EndUID):Unit.
 * 
 */
abstract class Node[Behaviour <: AbstractBehaviour](ident:String,logger:Logger)
extends Primitive[Behaviour](logger) {
  val name = ident
  val ends : Ends = Map() //HashMap[EndLID,End]
  val endflow : EndFlow = Map() // HashMap[EndUID,IOType]
  def updateBeh(end:EndUID)
  def updateStep(x:Step) = {}
  
 /** Search for the source of an end, given a UID.
  * To avoid too much inneficiency, collect all input ends while checking if the given UID is one of the input ends.
  * If not, return the input ends.
  */
  def source (r:Step,end:EndUID) : Either[GenSet[EndUID],Any] = {
    var res : Set[EndUID] = Set()
    for ((uid,io) <- endflow) {
      if (end == uid && io == Input) return Left(Set())
      if (io == Input) {
        if (r hasFlow uid) res += uid//ends(uid.endname)
      }
    }
    /*
    //if (end.iotype == Output) 
    for (e <- ends.values) {
      if (e.uid == end && e.iotype == Input) return Left(new HashSet)
      if (e.iotype == Input)
      { if (r hasFlow (uids(e.name))) res += e }
    }
    */
    return new Left(res)
  }

 /** Add end to <code>ends</code> and update behaviour. */
  def addEnd (end:AbstractEnd,uid:EndUID) = { // add end to "ends" and update behaviour
    logger.log_debug(name,"got new end "+end.name) //trace
    if ((ends contains end.name) || (endflow contains uid))
     logger.log_warning(name,"End already exists!")
     //throw new EndAlreadyExistsException
    else {
     ends(end.name) = end
     endflow(uid) = end.iotype
     //uids(end.name) = uid
     updateBeh(uid)
     logger.log_debug(name,"added end "+end.name)
    }
  }

  /** Remove end from <code>ends</code> and <code>uids</code>, and update behaviour. */
  def removeEnd (uid:EndUID) = {
    logger.log_debug(name,"removing end "+uid.endname) //trace
    if ((!(ends contains uid.endname)) || (!(endflow contains uid)))
     logger.log_warning(name,"End does not exists!")
     //throw new EndDoesNotExistException
    else {
     ends -= uid.endname
     endflow -= uid
     //uids -= endname
     updateBeh(uid)
     logger.log_debug(name,"removed end "+uid.endname)
    }
  }
  
  /**
   * Extends behaviour of reconfiguration with messages:
<pre>
AddEnd(ref:Actor,io:Utils.IOType,from:Utils.EndLID,otherUID:EndUID)
</pre>
 which creates a new end and connects its port to the end it received, updating the behaviour.
 <code>ref</code> is the other primitive where the port will connect, <code>io</code>
 is if the new end is an input or an output end, <code>from</code> is both the name of
 the end in the other primitive and the name of the new end in the node, and
 <code>otherUID</code> is the unique id of the end, to be used in the behaviour description.
<pre>
RemoveEnd(enduid:EndUID) // removes the end, updates behaviour, and asks sender
                         // to remove itself if no more ends are left
RemoveNode() // stops running the thread.
</pre>

   */
  override def gotReconfigMsg(locks:ImSet[String],msg:ReconfigMsg) : Nothing = {
    msg match {
      
      // Reconfig messages:
      // creates a new end and connects its port to the end it received, using the same name
      case AddEnd(ref:AbstractPrimitive[Behaviour],io:Utils.IOType,from :Utils.EndLID,otherUID:EndUID) => {
        //logger.log(name,msg)
        logger.log_rcv(name,"",msg)
        logger.log_debug(name,"adding new end: "+from+" : "+io)
        val newend = new End(from,io,this) {
          override val uid = otherUID
        }
         // resets the logging
          //def write(from:String,msg:String) { 
          //  super.write("("+from+")",msg)
          //}
        /*
        // logging function to each END:
        // are imported from primitive, and changed to print the name with
        // brackets around it, to differ from the name on the primitive
        override def log(from:String,msg:String) =
            super.log(from,msg)
        override def log_debug(from:String,msg:String) =
            super.log_debug(from,msg)
        override def log_rcv(prim:String,end:String,msg:Msg) =
            super.log_rcv(prim,end,msg)
        override def log_toPort(prim:String,end:String,msg:Msg) =
            super.log_toPort(prim,end,msg)
        override def log_warning(fromend:String,msg:String) =
            super.log_warning(fromend,msg)
        override def log_updateStep(from:String) =
            super.log_updateStep(from)
        override def log_initiate(from:String,rank:Int) =
            super.log_initiate(from,rank)
        override def log_propagate(from:String) =
            super.log_propagate(from)
        override def log_commit(fromend:String,table:StringtoOutside:Boolean) =
            super.log_commit(fromend,table,toOutside)
        override def log_gotbehaviour(from:String,beh:String) =
            super.log_gotbehaviour(from,beh)
        override def log_gotdata(from:String) =
            super.log_gotdata(from)
        override def log_sentdata(from:String) =
            super.log_sentdata(from)
        */

        addEnd(newend,otherUID) // add end to ends and update behaviour
        if (ref != null) {
          newend.setPort(ref,otherUID) // set port to primitive where the other end is
        }
        nextInput
      }
      
      case RemoveEnd(enduid:EndUID) => {
        logger.log_rcv(name,"",msg)
        removeEnd(enduid)
        if (ends.isEmpty) {
          sender ! Remove(NodePat,this.hashCode)
          exit()
        }
        else
          nextInput
      }
      case RemoveNode(_) => {
    	logger.log_rcv(name,"",msg)
        exit()
      }
      
      case _ => {
        super.gotReconfigMsg(locks,msg)
      }
    }
  } // gotReconfig
  
  /*
   * Send no flow information to the connector, and return the remaining
   * input and output ends. Needs to be overriden because the step only refers to
   * the UID's, and not the the ends themselves (because is a node).
   */
/*  override def processData(domain:Iterator[EndLID],step:Step) : HashSet[EndLID]= {
                                                   //(HashSet[EndLID],HashSet[EndLID]) =
    // var toAsk : HashSet[EndLID] = new HashSet
  
    val toSend : HashSet[EndLID] = new HashSet
    for (endname <- domain) {
      val end = ends(endname)
      if (hasFlow(step,end)) { // <---- this is the difference! (use of uids)
        if (end.iotype == Input) {
          val ad : CommunicateMsg = AskData(step)
          end.toPort(ad)
          //toAsk += endname
        }
        else // is output
          toSend += endname
      }
      else // there is no flow
        end.toPort(Step(step))
    }
    return toSend//(toAsk,toSend)
  }
*/
  
  /** Overrides node to include its hashcode on the name before printout. */
  override def status : String = {
   var res = "'"+name+"["+hashCode+"]' ("+stage+","+state.toString+") - ends:"
   if (ends.isEmpty)
     res += " Empty"
   else
     for (end <- ends.values) {
       res += "\n    * "
       res += end.status
     }
   // uncomment for include behaviour on status
   //res += (" "+behaviour.value.toString)
   res
  }
}
