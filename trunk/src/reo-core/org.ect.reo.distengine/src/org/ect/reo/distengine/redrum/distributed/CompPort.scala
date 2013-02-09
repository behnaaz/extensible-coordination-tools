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

import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.common._
import org.ect.reo.distengine.sat.SAT
import org.ect.reo.distengine.sat.primitives.{Sink, Source, NodeSAT}
import org.ect.reo.distengine.redrum.genericprim.Initiator
import org.ect.reo.distengine.redrum.IListenerEndOfStep

//import org.ect.reo.distengine.colouring.CC
//import org.ect.reo.distengine.colouring.primitives.{Source, Sink, NodeCC}
import scala.actors.Actor
import scala.actors.remote.TcpService
//import scala.actors.remote.RemoteActor.select


/** Port of a component. Has 2 hidden objects: (1) a special primitive that
 * interacts with the component (CompIn or CompOut), and starts the algorithm
 * when necessary, and (2) a distributed node, with a port (number) visible
 * to the outside.
 * @see CompInPort
 * @see CompOutPort */
abstract class CompPort(name:PrimID,logger:Logger,rank:Int, othername:String,
                        otherip:String, otherport:Int, thisip:String) {
  protected val iotype:IOType
  val end:AbstractEnd
  
  //private val name:String = compport.name
  protected val dnodename:String = name+"-node"//"Init_"+rank+"-node"
      
  private val thisport = TcpService.generatePort

//  protected val compport : genericprim.Initiator[CC]
//  protected val dnode:DistNode[CC] = new DistNode[CC](dnodename,thisport,logger) with NodeCC
  protected val compport : Initiator[SAT]
  protected val dnode:DistNode[SAT] = new DistNode[SAT](dnodename,thisport,logger) with NodeSAT

  //private val otherdnode:Actor = select(actors.remote.Node(otherip,otherport), Symbol(othername))
    
  // Manage suspension/release of both node and component primitive
  /** Send a suspend signal to both primitive and node.*/
  def suspend() : Unit = {
    compport ! Suspend("")//Suspend(compport.name)
    dnode    ! Suspend("")//Suspend(compport.name)
  }
  /** Send a release suspension signal to both primitive and node.*/
  def release() : Unit = {
    compport ! Release("")//Release(compport.name)
    dnode    ! Release("")//Release(compport.name)
  }
  
  def stop() : Unit = {
    println("Asked to stop component port -  "+compport.name)
    println("sending to dnode -  "+dnode.name+" - "+dnode.status)
    //dnode ! Disconnect(NodePat,(othername,Set(end.uid)))
    compport ! RemovePrim()
    dnode    ! RemoveNode(Set(end.uid))
  }
  
  /** Returns the current status of the primitive Initiator and the node DistNode.*/
  def status() : String = {
    "Status of prim and node:\n"+compport.status+"\n"+dnode.status
  }
  
  /** Add a listener to be notified everytime a step ends. */
  def addListener(lst:IListenerEndOfStep) = compport.addListener(lst)
  /** Remove a listener from being notified everytime a step ends. */
  def rmListener(lst:IListenerEndOfStep) = compport.rmListener(lst)
}

/** Special case of a CompPort for input ports, such as the ones used by readers. */
class SourcePort(name:PrimID,logger:Logger,rank:Int, othername:String,
                 otherip:String, otherport:Int, thisip:String)
extends CompPort(name,logger,rank,othername,otherip,otherport,thisip) {
  
  /** CompIn primitive, which acts as the bridge to the reo connector. */
  private val sourceport:Source = new Source(name,new NullLogger,rank)
  protected val iotype = Input
  protected val compport = sourceport
  
  def lastElem()     = sourceport.bag.last
  def getBag()       = sourceport.getBag.toArray
  def emptyBag()     = sourceport.emptyBag()
  def getMore(n:Int) = sourceport.getMore(n)
  
  compport.start
  dnode.start

  // connect component's port to distributed node
  override val end = compport.end
  private val nodeIOtype = end.iotype.dual
  compport ! SetPort(dnode,end.name,EndUID(dnodename,end.name))
  dnode ! AddEnd(compport,nodeIOtype,end.name,end.uid)

  // connect distributed nodes
  dnode ! Connect(NodePat,List(othername, otherip, otherport, thisip))
}

/** Special case of a CompPort for sink ports, such as the ones used by writers. */
class SinkPort(name:PrimID,logger:Logger,rank:Int, othername:String,
               otherip:String, otherport:Int, thisip:String)
extends CompPort(name,logger,rank,othername,otherip,otherport,thisip) {  
  /** CompOut primitive, which acts as the bridge to the reo connector. */
  private val sinkport:Sink = new Sink(name,new NullLogger,rank,{})
  protected val iotype = Output
  protected val compport = sinkport
  
  compport.start
  dnode.start

  def addDatum(a:Any) = sinkport addData List(a)

  // connect component's port to distributed node
  override val end = compport.end
  private val nodeIOtype = end.iotype.dual
  compport ! SetPort(dnode,end.name,EndUID(dnodename,end.name))
  dnode ! AddEnd(compport,nodeIOtype,end.name,end.uid)

  // connect distributed nodes
  dnode ! Connect(NodePat,List(othername, otherip, otherport, thisip))
}
