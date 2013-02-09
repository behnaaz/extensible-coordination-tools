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
package org.ect.reo.distengine.redrum.distributed;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.colouring._

import scala.actors.Actor
import org.ect.reo.distengine.redrum.{End, Primitive}

//import scala.actors.AbstractActor
import org.ect.reo.distengine.common.PreparingNewScala._
import scala.actors.Actor._
import collection.mutable.{ Map => MutMap }
import collection.mutable.{ Set => MutSet }
import collection.mutable.HashMap
import collection.mutable.HashSet
import collection.immutable.{ Set => ImSet }
//import collection.mutable.Set
import scala.collection.Set
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
//import scala.actors.remote.Node
import org.ect.reo.distengine.redrum.genericprim.{Node => GNode}

//import RemoteActorsHacked._
import scala.Symbol

/**
 * <p>Node with unspecified behaviour, with an open socket to receive
 * messages from other actors. Mix with traits to specify the behaviour.
 * If port number for the socket is 0 (zero), then a valid random number
 * is chosen.</p>
 *
 * <h3> Administration functionality </h3>
 *
 * <p>
 * Extends all administration messages from <code>Node</code>.
 * It also stores the actors of all connected distributed nodes,
 * and provides Connect/Disconnect messages for that.
 * </p>
 *
 * <h3> Connection with other distributed ends</h3>
 * 
 * <p>
 * A <code>DistNode</code> stores a set of identifiers of remote nodes.
 * It also introduces a new special end to the list of known ends, one for
 * each remote node, with the same identifier. The port of these ends is
 * the actor of the remote node.
 * </p>
 *
 * <p>
 * Special attention must be paid to avoid being treated as a normal end:
 * hide the end name from the behaviour when running <code>updateBehaviour</code>;
 * propagate the resulting behaviour properly, ignoring the IOType of the end
 * to the remote node; ...
 * </p>
 *
 * <p> The accepting reconfiguration messages are the same as a normal node, together
 * with the following:
<pre>
// Connect to another distributed node, and request it to connect to this node.
Connect(DNodePat,(othername:EndLID,ip:String, port:Int,localIp:String))
//Connect to another distributed node, without sending any request.
Connect(NodePat,(othername:EndLID,ip:String, port:Int))
// Remove references to another distributed node, and to a set of ends (associated to it)
Disconnect(NodePat,(othernode:EndLID,oldEnds:Set[EndUID]))
// Include the knowledge of other ends belonging to attached distributed nodes.
AddEndFlow(from:PrimID,otherEndFlow:EndFlow)
</pre>
 * 
 */
abstract class DistNode[Behaviour <: AbstractBehaviour](ident:String,var localPort:Int,logger:Logger)
extends GNode[Behaviour](ident,logger) {
  val remoteNodes = new HashMap[EndLID, (RemoteEnd[Behaviour],MutSet[EndUID])]
  //val remoteEnds  = new HashMap[EndLID,collection.mutable.Set[EndUID]]
  
  /** Return general information about the node. */
  override def status : String = {
    var res = "'"+name+"'@"+localPort+" ("+stage+","+state.toString+") - ends:"
    if (ends.isEmpty) res += " Empty"
    else {
      for (end <- ends.values) {
        res += "\n    * "
        res += end.status
      }
    }
    return res
  }
  
 /** New definition deserializes all byte arrays, and to
  * recode EndUID.
  */
  override def react(f : PartialFunction[Any, Unit]) : Nothing = {
    super.react {
      new PartialFunction[Any,Unit] {
       
      def apply(a : Any) = f(fix(a))
      def isDefinedAt(a : Any) = f.isDefinedAt(a)
      
      def fix(m:Any) : Any = {
        m match {
         case (x:Any,DataStep(d:Any,step:Colouring)) =>
           return (x,DataStep(d,fix_col(step))) 
         case (x:Any,SingleStep(step:Colouring)) =>
           return (x,SingleStep(fix_col(step))) 
         case (x:Any,AskData(step:Colouring)) =>
           return (x,AskData(fix_col(step)))
         case (x:Any,Table(step:ColouringTable)) =>
           return (x,Table(fix_ct(step)))
         case m => {
           logger.log_debug(name,"No colouring table found in msg")
           return m 
         }
      }}
      def fix_ct(ct:ColouringTable) : ColouringTable = {
        val newct = new ColouringTable
        for (col <- ct)
          newct += fix_col(col)
        logger.log_debug(name,"Fixed incomming colouring table!")
        return newct
      }
      def fix_col(col:Colouring) : Colouring= {
        //val newct = new ColouringTable
        //for (col <- ct) {
          val newcol = new Colouring
          for ((key,value) <- col)
            newcol(key) = value
          //newct += newcol
        //}
        logger.log_debug(name,"Fixed incomming colouring!")
        return newcol
      }
    }}
  }
  
  override def act = {
    // set the class loader.
    RemoteActor.classLoader = getClass().getClassLoader()

    if (localPort==0)
      localPort = scala.actors.remote.TcpService.generatePort
      
    alive(localPort)
    register(Symbol(ident),self)

    logger.log_debug(name,"Starting distributed node "+ident+":"+localPort)
    
    nextInput
  } // act
  
  /**
   * Searches for a remote node and creates a link (Actor) to it.
   * Assumes the current node is suspended.
   *
   * @param othername is the name of the other distributed node, and it
   *        is also used as the end name representing the node.
   * @param ip is the ip of the remote proxy.
   * @param port is the port of the remote proxy.
   */
  def connect(othername:EndLID,ip:String, port:Int, localip:Option[String]) : Nothing = {
    logger.log_debug(name,"trying to connect")
    assume(state match {case Suspended(_) => true; case _ => false},
        "DistNode must be suspended to connect to another DistNode.")
    // 1 - check if endname/remotenode already exist
    if (ends contains othername)
      throw new EndAlreadyExistsException
    // 2 - get actor
    //  remoteNodes(othername) = select(scala.actors.remote.Node(ip, port),Symbol(othername))
    val otherDNode = select(scala.actors.remote.Node(ip, port),Symbol(othername))
    // 3 - create end
    val newend = new RemoteEnd(othername, this, otherDNode)
    val uid = EndUID(name,othername)
    // 4 - Connect other node to this node
    if (localip != None)
      otherDNode ! Connect(NodePat,List(name, localip.get, localPort))
    // 5 - send direction of local ends (SendFlow)
    otherDNode ! AddEndFlow(name,endflow)
    // 6 - update state information
    ends(othername) = newend
    //endflow(uid) = Input // endflow with less information than ends!
    remoteNodes(othername) = (newend,new HashSet)
    // 7 - wait for endflow reply
    react {
      case AddEndFlow(from:PrimID,otherEndFlow:EndFlow) => {
        logger.log_debug(name,"Received reply of endflow information!")
        getEndFlow(from,otherEndFlow)
        nextInput
      }
    }
    // 8 - update behaviour -- unreachable after forcing the wait for endflow
    //updateBeh(uid)
    //suspensionMode

    /*
    // 5 - wait for reply/release/status
    logger.log(name,"about to loop, waiting for reply")
    loop { reactRemote { // HACKED
      // 6a - get reply
      case AddEndFlow(otherendflow) => {
        logger.log(name,"Got otherendflow. adding to state!")
        // 7a - get reply and send to the other dnodes
        for (dnode <- remoteNodes.values)
          dnode.toPort(AddEndFlow(otherendflow))
        // 8a - merge endflow and continue
        endflow ++= otherendflow
        // 9a - add actor and remotenode info into state
        ends(othername) = newend
        //endflow(uid) = Input // endflow with less information than ends!
        remoteNodes(othername) = newend
        // 10a - update node behaviour
        updateBeh(uid)
        logger.log(name,"updated behaviour. Suspending again.")
        // 11a - exit loop
        suspensionMode
      }
      // 6b - release suspension
      case Release(id:EndLID) => {
        logger.log(name,"Got release. trying to exit suspension (canceling connect if success!)")
        // 7b - check if it stops being suspended, and cancel connect if so.
        if (release(id)) {
          logger.log(name,"connecting to remote node canceled because suspension was released("+id+")")
          nextInput
        }
      }
      
      // 6c - show current status
      case Status(show:(String => Unit)) => { 
        show(status ++ " - waiting EndFlow information from node "+
          othername+"@"+ip+":"+port+".")
        logger.log(name,status)
      }
    }}
    
    println("Cant't happen.")
    exit // unreachable code, because of the loop in step 5.
    */
  }

 /** Removes all references to a given distributed node, and ignores a given
  * set of ends for its behaviour. Probably enduids need to be recoded, because of
  * wrong hashcodes for remote enduids
  * @param othernode name of remote node to be ignored.
  * @param enduids set of ends (EndUID) that no longer should make part of the
  * resulting behaviour.
  */
  def disconnect(othernode:EndLID, enduids:Set[EndUID]) {
    ends -= othernode
    //endflow -= EndUID(name,othernode)
    // remove from endflow: the ends it received from otherend + extra argument + otherend
    var toRemove:MutSet[EndUID] = remoteNodes(othernode)._2
    toRemove ++= enduids
    toRemove += EndUID(name,othernode)
    remoteNodes -= othernode

    var update : Boolean = false
    for (enduid <- endflow.keys) {
      if (toRemove contains enduid) {
        endflow -= enduid
        update = true
      }
    }
    if (update) updateBeh(EndUID())
  }

  override def killPrim : Nothing = {
    for ((remoteEnd,_) <- remoteNodes.values) {
      remoteEnd.toPort(Disconnect(NodePat,List(name,Set())))
    }
    super.killPrim
  }
  
 /** Removes all references to all distributed nodes, and connects them.
  *  Not in use yet. So far it is necessary to use the method
  *  <code>disconnect(othernode:EndLID, enduids:Set[EndUID])</code> instead.
  */
  /** Send a disconnect signal to each of the connected distributed nodes.
   *  Does not disconnect in the local end (use 
   *  <code>disconnect(othernode:EndLID, enduids:Set[EndUID])</code> instead.)
   */
  def sendDisconnectRequests(enduids:Set[EndUID]) {
    for ((remoteend,_) <- remoteNodes.values) {
      remoteend toPort Disconnect(NodePat, List(name,enduids))
    }
  }
 
 /** Adds a new set of ends to the node state, updates the behaviour
  * accordingly, and propagates if new ends are introduced.
  */
  def getEndFlow(from:PrimID,otherEndFlow:EndFlow) {
    //assert(remoteNodes contains from,"Sender of endflow ("+from+") is not a known remote node!")
    logger.log_debug(name,"Got new endflow '"+otherEndFlow+"' from '"+from+"'")
    val primitives : HashSet[EndLID] = new HashSet
    var newBeh : Boolean = false
    for ((enduid,value) <- otherEndFlow) {
      //primitives += enduid.primname ///////// WILL NOT WORK NOW, WITH UID ONLY REFERING TO PRIM!
      if (!(endflow contains enduid))    newBeh = true
      else if (endflow(enduid) != value) newBeh = true
      // recode hash codes! (instead of endflow ++= otherEndFlow)
      endflow(enduid) = value
      if (remoteNodes contains from) {
        logger.log(name,"adding '"+enduid+"' to ends of remote node '"+from+"'")
        remoteNodes(from)._2 += enduid
      }
    }
    
    if (newBeh) {
      logger.log_debug(name,"NEW ENDFLOW IN NODE!")
      for((otherNode,remoteEnd) <- remoteNodes) {
        //if(!(primitives contains (remoteEnd.name)))
        if (otherNode != from)
          remoteEnd._1.toPort(AddEndFlow(name,otherEndFlow))
      }
      updateBeh(EndUID())
    }
  }
  
 /**
  * Checks of there is data flow in a end, for a given step.
  * Needs to be overriden because the step only refers to
  * the UID's, which must be the same as in the ends, and remote ends
  * are not in the step. Therefore, we assume there is flow (need to communicate
  * to the other node the data) if there is flow in one of the ends
  * of the node, local or not.
  */
  override def hasFlow(step:Step,end:AbstractEnd) : Boolean = {
    /*
    val uid : EndUID = endflow.keys.find(EndUID(end.name).==) match {
       // to use equality instead of hash comparison, to forget primitive's name
      case Some(euid) => euid
      case None       => null
    }
    
    if (null == uid) {
      assert(remoteNodes contains end.name, "End '"+end+"' not a local or remote end of '"
        +name+"', when dealing with incomming behaviour step.")
        
      // for all node ends, check if there is flow
      for(enduid <- endflow.keys)
        if (step hasFlow enduid) return true
      return false
    }
    */
      
    if (remoteNodes contains end.name) {
      return remoteNodes(end.name)._2.exists(x => step hasFlow x) 
      // for all node ends, check if there is flow
      //for(enduid <- endflow.keys)
      //  if (step hasFlow enduid) return true
      //return false

    }
    else
      return (step hasFlow end.uid)
  }

  /**
   * Override addition of ends to propagate the changes of behaviour to remote nodes.
   */
  override def addEnd (end:AbstractEnd,uid:EndUID) = {
    super.addEnd(end,uid)
    for ((remoteend,uids) <- remoteNodes.values) {
      val endFlow : EndFlow = MutMap()
      endFlow(end.uid) = end.iotype
      remoteend.toPort(AddEndFlow(name,endFlow))
    }
  }

 
  /**
   * Extends behaviour of reconfiguration with messages:
   * <ul>
   *  <li>
   *   <code>Connect(NodePat,(othername:String,ip:String, port:Int))</code>
   *   which creates a new end and connects its port to the actor of the given remote node,
   *   <bold>without</bold> updating the behaviour (unlike the addition of a normal end).
   *  </li>
   *  <li>
   *   <code>Disconnect(NodePat,(othername:String,oldEnds:Set[EndUID]))</code>
   *   which removes all references to a given distributed node, and updates the
   *   behaviour of the node.
   *  </li>
   *  <li>
   *   <code>AddEndFlow(otherEndFlow:EndFlow)</code>
       which adds a new set of ends to the node state, updates the behaviour
   *   accordingly, and propagates if new ends are introduced.
   *  </li>
   * </ul
   */
  override def gotReconfigMsg(locks:ImSet[String],msg:ReconfigMsg) : Nothing = {
    msg match {
      
      // Reconfig messages:
      // creates a new end and connects its port to the end it received, using the same name
      case Connect(NodePat,List(othername:EndLID,ip:String, port:Int,localIp:String)) => {
        logger.log(name,"adding new remote node(1): "+othername+"@"+ip+":"+port)
        connect(othername,ip, port, Some(localIp))
        // suspensionMode or nextInput, while waiting for reply with EndFLow
      }
      case Connect(NodePat,List(othername:EndLID,ip:String, port:Int)) => {
        logger.log(name,"adding new remote node(2): "+othername+"@"+ip+":"+port)
        connect(othername,ip, port, None)
        // suspensionMode or nextInput, while waiting for reply with EndFLow
      }
      
      case Disconnect(NodePat,List(othernode:EndLID,oldEnds:Set[EndUID])) => {
        logger.log(name,"disconnecting remote node: "+othernode)
        disconnect(othernode,oldEnds)
        nextInput
      }
      
      case AddEndFlow(from:PrimID,otherEndFlow:EndFlow) => {
        logger.log(name,"Received more endflow information!")
        getEndFlow(from,otherEndFlow)
        nextInput
      }
      
      case RemoveNode(enduids:Set[EndUID]) => {
        logger.log_debug(name,"Sending a disconnect signal, asking to forget: "+enduids.mkString(","))
        sendDisconnectRequests(enduids)
        exit()
      }

      case _ => {
        super.gotReconfigMsg(locks,msg)
      }
    }
  } // gotReconfig

 /**
  * Override function used after a data message is received to update direction
  * of the end connecting to remote nodes, since it depends on the data flow
  * of the current step.
  */
  override def gotCommunicateMsg(endname:EndLID,msg:CommunicateMsg) : Nothing = {
    def updateDirection(step:Step) : Unit = {
      logger.log_debug(name,"updating direction of data flow for remote nodes attached this node.")
      for((remoteEnd,enduids) <- remoteNodes.values) {
        logger.log_debug(name,"Updating direction of reference to node '"+remoteEnd+"'")
        // End to a remote node is by default an output, unless there
        // is an input node with data flowing on the other side!
        if (enduids.exists(x => (endflow(x)==Input) && (step hasFlow x))) {
          logger.log_debug(name,"Found input end in '"+enduids+"' with flow,\n associated to remote node '"+remoteEnd+"'") 
          remoteEnd.iotype = Input
        }
        else {
          logger.log_debug(name,"NOT found input end in '"+enduids+"' with flow,\n associated to remote node '"+remoteEnd+"'") 
          remoteEnd.iotype = Output
        }
      }
    }
    
    state match {
      case Committed(_,_,_,_) => {
        msg match {
          case DataStep(_:Any,step:Step) => updateDirection(step)
          case SingleStep(step:Step)     => updateDirection(step)
          case AskData(step:Step)        => updateDirection(step)
          case ReplyData(_:Any)          => {}
        }
      }
      //case WaitingData(_,_,_) => {}
      case _ => {}
    }
    
    super.gotCommunicateMsg(endname,msg)
  }
  
  
  /*
  override def gotEndMessage(to:String,msg:CommitMsg) = {
    super.gotEndMessage(to,msg)
    logger.log(name,"New status after end message:\n- "+status)
  }
  */

 /** <p>Overriden to replace the endname of the source by the name of the
  * remote node corresponding to it.</p>
  * <p>Search for the source of an end, given a UID.
  * To avoid too much inneficiency, collect all input ends while checking if the given UID is one of the input ends.
  * If not, return the input ends. </p> 
  */
  override def source (r:Step,end:EndUID) : Either[Set[EndUID],Any] = {
    var res : HashSet[EndUID] = new HashSet
    for ((uid,io) <- endflow) {
      if (end == uid && io == Input) return Left(new HashSet)
      if (io == Input) {
        if (r hasFlow uid) {
          if (!(ends contains uid.endname)) return Left(HashSet(findRemoteNode(uid))) 
          else res += uid//ends(uid.endname)
        }
      }
    }
    
    def findRemoteNode(end:EndUID) : EndUID = {
      logger.log_debug(name,"Looking for remote node with end '"+end+"'")
      for ((dnode,remoteEnds) <- remoteNodes)
        if (remoteEnds._2 contains end) return remoteEnds._1.uid
      throw new Exception("When searching for source of end '"+end+
        "'. '"+end+"' is not in the local end set neither in the ends of remote nodes.")
    }
    
   return new Left(res)
  }


}

 
/**
 * Special end that describes a connection in a distributed node to
 * another distributed node.
 */
class RemoteEnd[Behaviour<:AbstractBehaviour](name:String, parentNode:Primitive[Behaviour],
                                otherDNode:Port)
extends End[Behaviour](name,Input,parentNode) {
  private val logger = primitive.logger
  //override protected var optPortPair : Option[(Port,EndUID)] =
  //  Some((otherDNode,EndUID(primname,name)))
  logger.log(name,"Setting the info about the other end to "+EndUID(name,primname))
  //setPort(otherDNode,EndUID(primname,name))
  setPort(otherDNode,EndUID(name,primname))

  // 'name' = name of the other distributed node!
  //otherEnd = EndUID(name,primname)
  // changed the order because it makes sense, but not tested! (maybe there was a reason)
    
  override def toPort(msg:Msg) {
    super.toPort(msg)
    // copy paste, with changes to the code,.. not a good idea in general
    //logger.log_toPort(primname,name,msg)
    logger.log(name,"Sending REMOTE message!")
    //msg match {
    //  case m:CommitMsg      => { if (port != null) port ! (otherEnd.endname,m) }
    //  case m:CommunicateMsg => { if (port != null) port ! (otherEnd.endname,m) }
    //  case _                => { if (port != null) port ! msg }
    //  // logger.log_debug(name,"sending non-evol msg - "+msg+" :: "+msg.getClass)
    //}
  }
  
  override def status : String = {
    "'"+name+ //"' - "+state+//.deepToString+ // comment deepToString to clean status
    " | Alias to node '"+optOtherEnd.get+"'"
  }
}
