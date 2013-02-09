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
package org.ect.reo.distengine.sandbox

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.redrum._
import org.ect.reo.distengine.redrum.genericprim._
import org.ect.reo.distengine.sat._
import org.ect.reo.distengine.sat.primitives._
import scala.concurrent.ops._


object RunSAT extends App {
  val logger = new ConsoleLogger {
    override def parseMsg(msg:Msg) = {
    msg match {
      case Table(t) => "BD: "+t.deepToString
      case m => super.parseMsg(msg)
    }
  }

  }
  val w = new Writer("wrt",logger,10,List(1))
  val ch = new LossySync("f",logger)
//  val ch = new FIFO1("f",logger,11,None)
  val r = new Taker("r",logger,9,3)
  
  val n1 = Node("n1",logger)
  val n2 = Node("n2",logger)
  
    //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w,n1,w.end.name)
  connect(ch,n1,"f-i")
  connect(ch,n2,"f-o")
  connect(r,n2,r.end.name)
  
  n1 ! Release("")
  n2 ! Release("")
  ch ! Release("")
  w  ! Release("")
  r  ! Release("")

  println("------ starting actors -------")
  //p1.start
  n1.start
  n2.start
  ch.start
  w.start
  r.start

  spawn {
      Thread.sleep(2000)
      println("#########\nShowing final status and terminating:")
      println(ch.status)
      println(w.status)
      println(r.status)
      println(n1.status)
      println(n2.status)
      
      n1 ! Kill()
      n2 ! Kill()
      ch ! Kill()
      w ! Kill()
      r ! Kill()
  }
}


object ReliableLossy extends Application {

  // i --> * --los--> * --> j
  //       '->--sd--<-'

  val logger = new ConsoleLogger {
    override def parseMsg(msg:Msg) = {
    	msg match {
    	case Table(t) => "BD: "+t.deepToString
    	case m => super.parseMsg(msg)
    	}
    }
  }
          
  // primitives
  val i  = new Writer("i",logger,1,List("a","b")) 
  val los = new LossySync("los",logger)
  val sd = new SyncDrain("sd",logger)  
  val j  = new Taker("j",logger,2,1) 

  // nodes
  val n1 = new Node[SAT]("n1",logger) with NodeSAT
  val n2 = new Node[SAT]("n2",logger) with NodeSAT
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(i,n1,i.end.name)
  connect(los,n1,"los-i")
  connect(los,n2,"los-o")
  connect(sd,n1,"sd-l")
  connect(sd,n2,"sd-r")
  connect(j,n2,j.end.name)
  
  
  // Release suspension, so that they stop being in a reconfigurable state.
  los! Release("")
  sd ! Release("")
  n1 ! Release("")
  n2 ! Release("")
  i  ! Release("")
  j  ! Release("")

  println("------ starting actors -------")
  los.start
  sd.start
  n1.start
  n2.start
  i.start
  j.start

  spawn {
      Thread.sleep(2000)
      println("#########\nShowing final status and terminating:")
      println(i.status)
      println(los.status)
      println(sd.status)
      println(j.status)
      println(n1.status)
      println(n2.status)
      
      i  ! Kill()
      los! Kill()
      sd ! Kill()
      j  ! Kill()
      n1 ! Kill()
      n2 ! Kill()
  }
}


object RunNodeSAT extends Application {

  /*
              ,--> t1
   w1 -,     / 
        -> n1 ---> t2
   w2 -«     \ 
              `--> t3
  */

  println("creating logger")
  val logger = new ConsoleLogger
  println("created.")

  // primitives ---> only logging IO operators and boundary nodes.
  val w1  = new Writer("w1",logger,39,List("a","b")) // send elements a and b
                                        with TaggedData {var tag = "BLUE"}
  val w2  = new Writer("w2",logger,40,List("a")) // send elements a and b
                                        with TaggedData {var tag = "RED"}
  val t1 = new Taker("t1",logger,41,1) // unbounded receives
  val t2 = new Taker("t2",logger,36,1) // unbounded receives
  val t3 = new Taker("t3",logger,35,1) // unbounded receives

  // nodes (Easy constructor with SeqChartLogger: Node(name))
  val n1 = new Node[SAT]("node3",logger) with RouterNodeSAT
  
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w1,n1,w1.end.name)
  connect(w2,n1,w2.end.name)
  connect(t1,n1,t1.end.name)
  connect(t2,n1,t2.end.name)
  connect(t3,n1,t3.end.name)
  
  // release primitives from suspension
  n1 ! Release("")
  w1 ! Release("")
  w2 ! Release("")
  t1 ! Release("")
  t2 ! Release("")
  t3 ! Release("")

  println("------ sstarting actors -------")
  w1.start
  w2.start
  t1.start
  t2.start
  t3.start

  n1.start
  
  spawn {
      Thread.sleep(2000)
      println("#########\nStatus in the end:")
      println(w1.status) ;  w1 ! Kill()
      println(w2.status) ;  w2 ! Kill()
      println(t1.status) ;  t1 ! Kill()
      println(t2.status) ;  t2 ! Kill()
      println(t3.status) ;  t3 ! Kill()
      println(n1.status) ;  n1 ! Kill()
  }
}

object ExRouterSAT extends Application {

  /*
            ,--l1--> n2 --> t1
           /         |s1
   w ->  n1 ->-sd-<- n3
           \         |s2
            `--l2--> n4 --> t2
  */

  println("creating logger")
  val logger = new ConsoleLogger {
    override def parseMsg(msg:Msg) = {
    	msg match {
    		case Table(t) => "BD: "+t.deepToString
    		case m => super.parseMsg(msg)
    	}
    }

  }
  println("created.")

  // primitives ---> only logging IO operators and boundary nodes.
  val s1 = new Sync("s1",logger)           
  val s2 = new Sync("s2",logger)           
  val sd = new SyncDrain("sd",logger)      
  val l1 = new LossySync("l1",logger)      
  val l2 = new LossySync("l2",logger)      
  val w  = new Writer("w",logger,39,List(3,4)) // send elements 3 and 4
                                        with TaggedData {var tag = "BLUE"}
  val t1 = new Taker("t1",logger,41,1) // unbounded receives
  val t2 = new Taker("t2",logger,36,1) // unbounded receives

  // nodes (Easy constructor with SeqChartLogger: Node(name))
  val n1 = Node("node1",logger)
  val n2 = Node("node2",logger) 
  val n3 = new Node[SAT]("node3",logger) with NodeSAT
  val n4 = Node("node4",logger)
  
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w,n1,w.end.name)
  connect(l1,n1,"l1-i")
//  connect(l2,n1,"l2-i")
  connect(sd,n1,"sd-l")
  connect(l1,n2,"l1-o")
//  connect(s1,n2,"s1-i")
  connect(t1,n2,t1.end.name)
//  connect(l2,n4,"l2-o")
//  connect(s2,n4,"s2-i")
//  connect(t2,n4,t2.end.name)
  connect(sd,n2,"sd-r")
//  connect(sd,n3,"sd-r")
//  connect(s1,n3,"s1-o")
//  connect(s2,n3,"s2-o")
  
  // release primitives from suspension
  s1 ! Release("")
  s2 ! Release("")
  sd ! Release("")
  l1 ! Release("")
  l2 ! Release("")
  n1 ! Release("")
  n2 ! Release("")
  n3 ! Release("")
  n4 ! Release("")
  w  ! Release("")
  t1 ! Release("")
//  t2 ! Release("")

  println("------ sstarting actors -------")
  s1.start
  s2.start
  sd.start
  l1.start
  l2.start
  w.start
  t1.start
  t2.start

  n1.start
  n2.start
  n3.start
  n4.start
  
  spawn {
      Thread.sleep(2000)
      println("#########\nStatus in the end:")
      println(s1.status) ;  s1 ! Kill()
      println(s2.status) ;  s2 ! Kill()
      println(sd.status) ;  sd ! Kill()
      println(l1.status) ;  l1 ! Kill()
      println(l2.status) ;  l2 ! Kill()
      println(w.status)  ;  w  ! Kill()
      println(t1.status) ;  t1 ! Kill()
      println(t2.status) ;  t2 ! Kill()
      println(n1.status) ;  n1 ! Kill()
      println(n2.status) ;  n2 ! Kill()
      println(n3.status) ;  n3 ! Kill()
      println(n4.status) ;  n4 ! Kill()
  }
}

/** A write(i) attached to 'n' Read(j).<br/>
 * The src/common/Logger defines the logging functions, that allow to observe what is happening.
 */
object BigXORSAT extends Application {

  val n = 30 // 300 -> 5 sec
  val msgs = 3
  val time = 20
  var count=0
  val log = false
  val logger = new ConsoleLogger {
    override def log_gotdata(f:String) = {
          count+=1
          if (count == msgs) println("last msg received")
//          println("updating counter: "+count)
        }
    override def log_toPort(prim:String,end:String,to:EndUID,msg:Msg) = {
      println("sending msg to port...")
    }
    override def parseMsg(msg:Msg) = {
    	msg match {
    		case Table(t) => "BD: "+t.deepToString
    		case m => super.parseMsg(msg)
    	}
    }
  }
  val nullLog = new NullLogger {
    override def log_gotdata(f:String) = {
    	count+=1
    	if (count == msgs) println("last msg received")
//    	println("updating  counter: "+count)
    }
  }

  
  // i--> * --f1--> * ... * --p200--> * -->j

  // NOTE: p2 + j gives empty ct, since both only give reason, and never accept.

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val readers = new Array[Taker](n)
    
  // primitives
  var w:Writer = null
  var node:Node[SAT] = null
  if (log) {
    w  = new Writer("Wr",logger,n+1,List.range(0,msgs))
    node = new Node[SAT]("n0",logger) with RouterNodeSAT
  }
  else {
    w  = new Writer("Wr",nullLog,n+1,List.range(0,msgs)) // with ConsoleLogger
    node = new Node[SAT]("n0",nullLog) with RouterNodeSAT // with ConsoleLogger
  }
  connect(w,node,w.end.name)
  
  
  // create and connect all readers to node
  if (log)
    for (i <- Array.range(0,n)) {
      readers(i) = new Taker("R"+i,logger,i,2)
      connect(readers(i),node,readers(i).end.name)
    }
  else
    for (i <- Array.range(0,n)) {
      readers(i) = new Taker("R"+i,nullLog,i,1)
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