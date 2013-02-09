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
package org.ect.reo.distengine.redrum.deployment;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.colouring._
import org.ect.reo.distengine.redrum._
import org.ect.reo.distengine.redrum.genericprim.{Node => GNode}
import org.ect.reo.distengine.redrum.distributed._
import org.ect.reo.distengine.colouring.primitives.{Node => PNode, NodeCC, RouterNode, RouterNodeCC}
import actors.Actor
import actors.remote.{RemoteActor, TcpService}

//import RemoteActorsHacked._

import scala.actors.Actor
//import scala.actors.AbstractActor
import org.ect.reo.distengine.common.PreparingNewScala._
import scala.actors.Actor._
import scala.actors.remote.TcpService
//import scala.actors.remote.Node
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import collection.mutable.HashMap
import collection.mutable.Set


/**
<p> Actor that executes several (independent) primitives, also actors. </p>

<p> Keeps record of:
<ul> <li> Some ends and primitives/nodes that can still be connected-disconnected; </li>
     <li> Available distributed nodes; <li>
     <li> Some known engines, required to start new connections via distributed
     nodes, but not to keep existing connections. </li>
</ul>
</p>

<p> The actor interface allows to:
<ul> <li> Connect/disconnect primitives to nodes/distributed nodes;</li>
     <li> Connect/disconnect distributed nodes to nodes in other engines;</li>
     <li> Connect/disconnect to other engines.</li>
<ul>
</p>
<p> The partial code with the possible incomming messages is listed below.
<pre>
// ENGINES: connect/disconnect
case Connect(EnginePat,List(name:String,ip:String,port:Int))
case (Disconnect(EnginePat,List(name:String)))

// PRIMITIVE-NODE: connect/disconnect ---> Reply with UpdContent to listeners
case Connect(PrimPat,List(primitivename:PrimID,endname:EndLID,nodehash:Int))
case Disconnect(PrimPat,List(primname:PrimID,endname:EndLID))

// DISTRIBUTED NODES: connect/disconnect ---> No reply
case Connect(NodePat,List(localnode:Int,remotenode:Int,remoteip:String,remoteport:Int))
case Disconnect(NodePat,List(localnode:Int,remotenode:PrimID))

// CREATION OF OBJECTS
case Create(PrimPat,List(primType:String,args:List[Any],tag:Int))      // replies with UpdContent
case Create(NodePat,List(isRouter:Boolean,requesterhash:Int))          // replies with NewNode
case Create(NodePat,List(isRouter:Boolean,port:Int,requesterhash:Int)) // replies with NewNode

// REMOVAL OF OBJECTS: forget they exist, exit actor, and disconnect from nodes
case Remove(PrimPat,prim:String)  // No reply
case Remove(NodePat,nodehash:Int) // No reply

// SUSPENSION/RELEASE
case Suspend(primitive:String)  // replies with UpdContent
case Release(primitive:String)  // replies with UpdContent

// STATUS
case Status()  // replies with String to sender

// List of current primitives
case AskContent()  // replies with UpdContent to sender

// KILL
case Kill()

// LISTENERS
case AddListener()
case RmListener()
</pre>
*/
 
class Engine//[Beh <: Behaviour]
(ident:String, val ip:String, portt:Int,logger: Logger)
extends Actor {
  val port:Int = if (portt > 0) portt
                 else TcpService.generatePort
  val engineID = this.hashCode
  
  type Beh = CC
  
  //var ends       = new HashMap[EndLID, End                ]
  val primitives = new HashMap[PrimID, AbstractPrimitive[Beh]     ] // no nodes!
  val nodes      = new HashMap[Int, GNode[Beh]          ] // hashcodes of nodes and dnodes (must be consistent!)
  val dnodes     = new HashMap[Int, (DistNode[Beh],Int)] // Only dnodes from this engine!
  val engines    = new HashMap[String, (AbstractActor,String,Int) ]

  var seed :Int  = 0
  val listeners : HashMap[Int,scala.actors.OutputChannel[Any]]  = new HashMap()
  
  private def notifyPrimitive(prim:AbstractPrimitive[Beh],tag:Int) = {
    val msg = UpdContent(compilePrimContent(prim),tag,engineID)
    for (listn <- listeners.values)
      listn ! msg
  }

  private def notifyConnection(prim:AbstractPrimitive[Beh],endhash:Int,nodehash:Int) = {
    var pCont = compilePrimContent(prim)
    def fixCont(cont: EndContent) : EndContent = 
      if (cont.endhash == endhash) EndContent(endhash,nodehash,cont.endname)
      else cont
    pCont = PrimContent(pCont.name,pCont.primhash,pCont.sourceends.map(fixCont),
                        pCont.sinkends.map(fixCont),pCont.state)
    val msg = UpdContent(pCont,0,engineID)
    for (listn <- listeners.values)
      listn ! msg
  }

  private def notifyStatus(prim:AbstractPrimitive[Beh],newstate:PrimState) = {
    var primcont = compilePrimContent(prim)
    val state2 = pruneState(newstate)
    primcont = primcont match
      {case PrimContent(a,b,c,d,state) => PrimContent(a,b,c,d,state2)} 
    val msg = UpdContent(primcont,0,engineID)
    for (listn <- listeners.values)
      listn ! msg
  }
  private def pruneState(state:PrimState) = state match {
    case Committing(_,_,_,_,_) => Committing(0,null,null,null,null)
    case Committed(_,_,_,_) => Committed(0,null,null,null)
    case WaitingData(_,_,_) => Committed(0,null,null,null)
    case x => x
  }

  private def notifyNode(requesterhash:Int,nodehash:Int) = {
    val msg = NewNode(RedrumPair(requesterhash,nodehash))
    for (listn <- listeners.values)
      listn ! msg
  }
  
  private def notifyError(msg: String) = {
    for (listn <- listeners.values)
      listn ! ErrorMsg(msg)
  }

  private def compilePrimContent(prim:AbstractPrimitive[Beh]) : PrimContent = {
    var sourceEnds : List[EndContent] = Nil
    var sinkEnds   : List[EndContent] = Nil
    val state = pruneState (prim.state)

    for (end <- prim.ends.values) {
      val nodehash : Int = if (!end.hasPort) 0
                           else end.optPort.get.hashCode
      if (end.iotype == Input)
        sourceEnds = EndContent(end.hashCode,nodehash,end.name) :: sourceEnds
      else
        sinkEnds =   EndContent(end.hashCode,nodehash,end.name) :: sinkEnds
    }
    PrimContent(prim.name,prim.hashCode,sourceEnds,sinkEnds,state)
  }




  //var reply:Actor = null

  def killl() = { this ! Kill() }
  def status() = { this ! Status() }
  def show(s:String) = println(s)

  def act() : Unit = {

    // set the class loader.
    RemoteActor.classLoader = getClass().getClassLoader()

    alive(port)
    register(Symbol(ident),self)

    loop {
      react {        
        // ENGINES: connect/disconnect
        case Connect(EnginePat,List(name:String,ip:String,port:Int)) => {
          //show("----- received connection eng request!")
          val othereng = select(actors.remote.Node(ip,port), Symbol(name))
          engines(name) = (othereng,ip,port)
        }
        case (Disconnect(EnginePat,List(name:String))) => {
          engines -= name
        }

        // PRIMITIVE-NODE: connect/disconnect
        case Connect(PrimPat,List(primitivename:PrimID,endname:EndLID,nodehash:Int)) => {
          //show("----- received connection prim request!")
          if (!(primitives contains primitivename) ||
              !(nodes      contains nodehash) ||
              !(primitives(primitivename).ends contains endname))
            show("Error connecting primitive to node - "+List(primitivename:PrimID,endname:EndLID,nodehash:Int))
          else {
            val primitive = primitives(primitivename)
            val node = nodes(nodehash)
            val end = primitive.ends(endname)
            val nodeIOtype = end.iotype.dual
            primitive ! SetPort(node,endname,EndUID(node.name,endname))
            node ! AddEnd(primitive,nodeIOtype,endname,end.uid)
            // give suspension power from engine to primitive
            node ! Suspend(primitivename)
            node ! Release(ident)
            notifyConnection(primitive,end.hashCode,nodehash) // PREDICTING THE END ATTACHED TO THE NODE
          }
        }
        case Disconnect(PrimPat,List(primname:PrimID,endname:EndLID)) => {
          val primitive = primitives(primname)
          val end = primitive.ends(endname)
          //val otherend = primitive.ends(endname).otherEnd
          primitive ! SetPort(null, endname, null:EndUID)
          end.toPort(RemoveEnd(EndUID(primname,endname)))
          // remove suspension power from primitive before removing
          // end.toPort(Release(primname))
          notifyPrimitive(primitive,0)
        }

        // DISTRIBUTED NODES: connect/disconnect
        // send the other node a connect signal directly
        case Connect(NodePat,List(localnode:Int,remotenodename:String,remoteip:String,remoteport:Int)) => {
          //show("----- received connection dnode request!")
          nodes.get(localnode) match {
            case Some(node:Actor) =>
              node ! Connect(NodePat,List(remotenodename, remoteip, remoteport,ip))
            case None => notifyError("node not found: "+localnode)
          }
        }
        // forward a request to another engine, after collecting the node name.
        case Connect(NodePat,List(localnode:Int,remotenode:Int,otherip:String,localnodeport:Int,
                                  engport:Int, engname:String)) => {
          //show("----- received connection dnode request!")
          val othereng = select(actors.remote.Node(otherip,engport), Symbol(engname))
          nodes.get(localnode) match {
            case Some(node) =>
              othereng ! Connect(NodePat,List(remotenode, node.name, ip, localnodeport))
            case None => notifyError("node not found: "+localnode)
          }
        }
        case Disconnect(NodePat,List(localnode:Int,remotenode:PrimID)) => {
          //val (node,_) = dnodes(localnode)
          val node = nodes(localnode)
          node ! Disconnect(NodePat,List(localnode,
              node.endflow.keys.filter((x:EndUID) => x.primname==remotenode)))
        }
        
        
        // CREATION OF OBJECTS
        case Create(PrimPat,List(primType:String,args:List[_],tag:Int)) => {
          val id = ident+"_"+seed
          seed += 1
          val primname = primType+"_"+id
          org.ect.reo.distengine.colouring.primitives.PrimitiveFactory.create(primType,primname :: args,logger) match {
            case Some(prim) => {
              println("creation of primitive "+primType+" successfull")
              primitives(primname) = prim
              // give suspension power from "" to engine
              prim ! Suspend(ident)
              prim ! Release("")
              prim.start()
              notifyPrimitive(prim,tag)
              //println("Just created primitive with state "+prim.state)
              //show("Just started prim "+prim)
            }
            case _ => {
              seed -= 1
              notifyError("failed to create primitive "+primType+"("+(primname::args).mkString(",")+")")
            }
          }
        }
        case Create(NodePat,List(isRouter:Boolean,requesterhash:Int)) => {
          val id = ident+"_"+seed
          seed += 1
          val nodename = if (isRouter) "router_"+id else "node_"+id
          // by default, just the name implies creation of CC node, with SeqChartLogger.
          val node = if(isRouter) new GNode[CC](nodename,logger) with RouterNodeCC
                     else         new GNode[CC](nodename,logger) with NodeCC//org.ect.reo.distengine.colouring.primitives.Node(nodename)
          nodes(node.hashCode) = node
          node ! Suspend(ident)
          node ! Release("")
          node.start()
          notifyNode(requesterhash,node.hashCode)
        }
        case Create(NodePat,List(isRouter:Boolean,port:Int,requesterhash:Int)) => {
          val id = ident+"_"+seed
          seed += 1
          val nodename = if (isRouter) "router_"+id else "node_"+id
          val dnode = if(isRouter) new DistNode[Beh](nodename,port,logger) with NodeCC
                      else         new DistNode[Beh](nodename,port,logger) with RouterNodeCC
          dnodes(dnode.hashCode) = (dnode,port)
          nodes(dnode.hashCode)  = dnode
          dnode ! Suspend(ident)
          dnode ! Release("")
          dnode.start()
          notifyNode(requesterhash,dnode.hashCode)
          //show("Just started dnode "+dnode)
        }

        // REMOVAL OF OBJECTS: forget they exist, exit actor, and disconnect from nodes
        // SHOULD BE POSSIBLE TO JUST FORGET!
        case Remove(PrimPat,primitive:String) => { // normal primitive or node
         if (primitives contains primitive) {
           val prim = primitives(primitive)
           
           // release nodes from suspension
           val lock = prim.name
           for (end <- prim.ends.values){
             end.toPort(RemoveEnd(EndUID(prim.name,end.name)))
             end.toPort(Release(lock))
           }
           /* // releasing takes time, so return a prediction of the change...
           prim.state match {
             case Suspended(locks) if (locks.size==1 && (locks contains ident)) =>
               notifyStatus(prim,Idle)
             case Suspended(locks) => notifyStatus(prim,Suspended(locks - ident))
             case x => notifyStatus(prim,x)
           }
           */
           
           // remove trhe primitive
           prim ! RemovePrim()
          // show("Just removed primitive "+primitives(prim).name+".")
              //" Remaining: "+primitives.keys.mkString(",")+".")
           primitives -= primitive
         }
        }
        case Remove(NodePat,nodehash:Int) => {
          if (nodes contains nodehash) {
            nodes(nodehash) ! RemoveNode(scala.collection.immutable.Set())
            val nodename = nodes(nodehash).name
            nodes  -= nodehash
            dnodes -= nodehash
            // show("Just removed node "+nodename+".")
               //" Remaining: "+nodes.keys.mkString(",")+".")
          }
        }
        
        // SUSPENSION/RELEASE
        case Suspend(primitive:String) => {
          if(primitives contains primitive){
            val prim = primitives(primitive)
            //show("asking primitive to suspend")
            prim ! Suspend(ident)
            val lock = prim.name
            for (end <- prim.ends.values)
              end.toPort(Suspend(lock))
            // suspending takes time, so return a prediction of the change...
            prim.state match {
              case Suspended(locks) => notifyStatus(prim,Suspended(locks + lock))
              case Idle => notifyStatus(prim,Suspended(scala.collection.immutable.Set(lock)))
              case x => notifyStatus(prim,x)
            }
          }
          //if(nodes contains primitive)
          //  nodes(primitive) ! Suspend(ident)
        }
        case Release(primitive:String) => {
          if(primitives contains primitive) {
            val prim = primitives(primitive)
            //show("asking prim to release")
            //show("asking primitive (not node) to release! - "+primitive)
            prim ! Release(ident)
            val lock = prim.name
            for (end <- prim.ends.values)
              end.toPort(Release(lock))
            // releasing takes time, so return a prediction of the change...
            prim.state match {
              case Suspended(locks) if (locks.size==1 && (locks contains ident)) =>
                notifyStatus(prim,Idle)
              case Suspended(locks) => notifyStatus(prim,Suspended(locks - ident))
              case x => notifyStatus(prim,x)
            }
          }
          else println("Asked to release unknown primitive/node - "+primitive)
        }

        // STATUS
        case Status() => {
          var res = ""
          res += ("Known engines: '"+engines.keys.mkString(",")+"'\n"+
                  "Active listeners: "+listeners.size+"\n"+
                  "PRIMITIVES: \n")
          for (p <- primitives.values)
            res += (p.status + "\n")
          res += "NODES: \n"
          for (p <- nodes.values)
            res += (p.status + "\n")
          //show("Replying status to Sender.") // : "+res)
          sender ! res
        }
        
        // List of current primitives
        case AskContent() => { // list of primitives
          var prims : List[PrimContent] = Nil
          //var nods  : List[RedrumPair[PrimID,State]] = Nil
          for (p <- (primitives.values)) {
            val prim = compilePrimContent(p)
            prims = prim :: prims
          }
          sender ! Content(prims,engineID)
        }
        

        // KILL
        case Kill() => {
          for (p <- primitives.values){
            p ! Kill()
            //show("kill sent to "+p)
          }
          for (p <- nodes.values)
            p ! Kill()
          exit
        }
        
        // LISTENERS
        case AddListener(hash:Int) => {
          listeners(hash) = sender
        }

        case RmListener(hash:Int) => {
          listeners -= hash
        }

        case unexpected: AnyRef => {
          show("We're getting a signal " +
             unexpected.toString + " of type " + unexpected.getClass)
        }
      }
    }
  }
}
