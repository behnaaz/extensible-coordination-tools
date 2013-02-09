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
package org.ect.reo.distengine.sandbox;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.redrum.genericprim.Node
import org.ect.reo.distengine.colouring._
import org.ect.reo.distengine.colouring.primitives._

import scala.actors.Actor._
import scala.concurrent.ops._


/** A write(i) attached to a sequence of FIFO1 (F0,F1,F2,...) attached to a Read(j).<br/>
 * The src/common/Logger defines the logging functions, that allow to observe what is happening.
 */
object LotsOfFifos extends Application {

  val nfifos = 200
  val time = 6
  val log = false
  val logger = new ConsoleLogger
  val nullLog = new NullLogger
  
  // i--> * --f1--> * ... * --p200--> * -->j

  // NOTE: p2 + j gives empty ct, since both only give reason, and never accept.

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val fifos = new Array[FIFO1](nfifos)
  val nodes = new Array[Node[CC]](nfifos+1)
    
  // primitives
  val i  = new Writer("Writer",logger,-1,List("MSG 1","MSG 2","MSG 3","MSG 4")) 
  
  nodes(0) = new Node[CC]("n0",logger) with NodeCC
  connect(i,nodes(0),i.end.name)
  
  // create and connect 200 fifos and nodes
   if (log)
   for (i <- Array.range(0,nfifos)) {
      fifos(i) = new FIFO1("F"+i,logger,i,None)
      connect(fifos(i),nodes(i),"F"+i+"-i")
      nodes(i+1) =  new  Node[CC]("n"+(i+1),logger) with NodeCC
      //println("defined node "+(i+1))
      connect(fifos(i),nodes(i+1),"F"+i+"-o")
   }
   else
   for (i <- Array.range(0,nfifos)) {
     fifos(i) = new FIFO1("F"+i,nullLog,i,None) // with SeqChartLogger
     connect(fifos(i),nodes(i),"F"+i+"-i")
     nodes(i+1) =  new  Node[CC]("n"+(i+1),nullLog) with NodeCC // with SeqChartLogger
     //println("defined node "+(i+1))
     connect(fifos(i),nodes(i+1),"F"+i+"-o")
   }

  
  val j  = new Taker("Reader",logger,nfifos,-1)
  //println("getting node "+nfifos)
  connect(j,nodes(nfifos),j.end.name)

  println("------ starting actors -------")
  i.start
  j.start
  
  for(fifo <- fifos) fifo.start
  for(node <- nodes) node.start  
  
  println("---- set... ----")
  Thread.sleep(2000)
  println("---- Releasing! ----")
  
  // Release suspension, so that they stop being in a reconfigurable state.
  i ! Release("")
  j ! Release("")

  for(fifo <- fifos) fifo ! Release("")
  for(node <- nodes) node ! Release("")

  spawn {
    for (i <- Array.range(1,time)) {
      Thread.sleep(1000) // 1 seconds
      println("---------------- "+i+" sec ----------------")
    }

    println("#########\nTerminating:")

    /*
    println(j.status)
    println(j.status)
    for(fifo <- fifos) println(fifo.status)
    for(node <- nodes) println(node.status)
    */
    
    i ! Kill()
    j ! Kill()
    for(fifo <- fifos) fifo ! Kill()
    for(node <- nodes) node ! Kill()

  }

}



/** A write(i) attached to 'n' Read(j).<br/>
 * The src/common/Logger defines the logging functions, that allow to observe what is happening.
 */
object BigXOR extends Application {

  val n = 14
  val msgs = 3
  val time = 20
  var count=0
  val log = false
  val logger = new ConsoleLogger {
    override def log_gotdata(f:String) = {
          count+=1
          if (count == msgs) println("last msg received")
          //println(""+i+") updating counter: "+count)
        }
  }
  val nullLog = new NullLogger {
    override def log_gotdata(f:String) = {
          count+=1
          if (count == msgs) println("last msg received")
          //println(""+i+") updating counter: "+count)
        }
  }

  
  // i--> * --f1--> * ... * --p200--> * -->j

  // NOTE: p2 + j gives empty ct, since both only give reason, and never accept.

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val readers = new Array[Taker](n)
    
  // primitives
  var w:Writer = null
  var node:Node[CC] = null
  if (log) {
    w  = new Writer("Writer",logger,n+1,List.range(0,msgs))
    node = new Node[CC]("n0",logger) with RouterNodeCC
  }
  else {
    w  = new Writer("Writer",nullLog,n+1,List.range(0,msgs)) // with SeqChartLogger
    node = new Node[CC]("n0",nullLog) with RouterNodeCC // with SeqChartLogger
  }
  connect(w,node,w.end.name)
  
  
  // create and connect all readers to node
  if (log)
    for (i <- Array.range(0,n)) {
      readers(i) = new Taker("R"+i,logger,i,2) {
        // use only if FLIP RULE NOT APPLIED in nodes
        val col4 = new Colouring
        col4(uid) = FromIn
        noFlow += col4

        /*
        override def log_gotdata(f:String) = {
          count+=1
          if (count == msgs) println("last msg received")
          //println(""+i+") updating counter: "+count)
        }
        */

      }
      
      connect(readers(i),node,readers(i).end.name)
    }
  else
    for (i <- Array.range(0,n)) {
      readers(i) = new Taker("R"+i,nullLog,i,1) {
        // use only if FLIP RULE NOT APPLIED in nodes
        val col4 = new Colouring
        col4(uid) = FromIn
        noFlow += col4

        /*
        override def log_gotdata(f:String) = {
          count+=1
          if (count == msgs) println("last msg received")
          println(""+i+") updating counter: "+count)
        }
        */
      }
      connect(readers(i),node,readers(i).end.name)
    }

  println("------ starting actors -------")
  w.start
  node.start
  
  for(reader <- readers) reader.start
  
  println("---- set... ----")
  Thread.sleep(2000)
  println("---- Releasing! ----")  

  // Release suspension, so that they stop being in a reconfigurable state.
  w ! Release("")
  node ! Release("")

  for(reader<- readers) reader ! Release("")

  spawn {
    for (i <- Array.range(1,time)) {
      Thread.sleep(1000) // 1 seconds
      println("---------------- "+i+" sec ----------------")
    }

    println("#########\nTerminating:")

    /*
    println(j.status)
    println(j.status)
    for(fifo <- fifos) println(fifo.status)
    for(node <- nodes) println(node.status)
    */
    
    w ! Kill()
    node ! Kill()
    for(reader <- readers) reader ! Kill()
  }

}
