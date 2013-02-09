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

  

/** test to check the join of colouring tables. */
object MergeColouringTables extends Application {
  val logger = new ConsoleLogger
  
  val p1 = new Writer("p1",logger,41,List())
  val p2 = new Taker("p2",logger,40,0)

  val nodeBehaviour = new ColouringTable
  val col1 = new Colouring
  col1(p1.end.uid) = Flow
  col1(p2.end.uid) = Flow
  nodeBehaviour += col1
  val col2 = new Colouring
  col2(p1.end.uid) = ToIn
  col2(p2.end.uid) = FromOut
  nodeBehaviour += col2
  val col3 = new Colouring
  col3(p1.end.uid) = FromIn
  col3(p2.end.uid) = ToOut
  nodeBehaviour += col3

  println("nodeBehaviour + p1.ct + p2.ct :\n"+ (nodeBehaviour ++ p1.behaviour.value ++ p2.behaviour.value))
}


/** Just testing the first part of the algorithm, Now using the real CC. */
object FirstPhaseCC extends Application {

  // w --> * ---s--> * --> t
  //       |         |
  //       '->--d--<-'

  val logger = new ConsoleLogger
      
  // primitives
  val w = new Writer("w",logger,41,List())
  val s = new Sync("s",logger)
  val t = new Taker("t",logger,42,0)
  val d = new SyncDrain("d",logger)
  // nodes
  val n1 = Node("n1",logger)
  val n2 = Node("n2",logger)
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w,n1,w.end.name)
  connect(s,n1,"s-i")
  connect(d,n1,"d-l")
  connect(s,n2,"s-o")
  connect(d,n2,"d-r")
  connect(t,n2,t.end.name)

  println("------ starting actors -------")
  
  w.start; //w ! Release("") 
  s.start; s ! Release("")
  t.start; //t ! Release("")
  d.start; d ! Release("")
  n1.start; n1 ! Release("")
  n2.start; n2 ! Release("")
            
  // observe the behaviour we choose
  spawn {
    Thread.sleep(1000)
    println("Behaviour3 w s t d n1 n2:\n"+ (
      s.behaviour.value ++ d.behaviour.value ++ n1.behaviour.value ++ n2.behaviour.value 
    ))
  }

  spawn {
    Thread.sleep(2000)
    w ! Kill()
    s ! Kill()
    t ! Kill()
    d ! Kill()
    n1 ! Kill()
    n2 ! Kill()
  }
  
}


/** A write(i) attached to a sync(p1) attached to a FIFO1(p2) attached to a Read(j).<br/>
 * The src/common/Logger defines the logging functions, that allow to observe what is happening.
 */
object WriteSyncFifoRead extends Application {

  // i--> * --p1--> * --p2--> * -->j

  // NOTE: p2 + j gives empty ct, since both only give reason, and never accept.
    
  val logger = new ConsoleLogger

  // primitives
  val i  = new Writer("Writer",logger,3,List("MSG 1","MSG 2"))  
  //val p1 = new Sync("Sync")
  val p2 = new FIFO1("Fifo",logger,2,None) 
  val j  = new Taker("Reader",logger,1,1)

  // nodes
  val n1 = Node("A",logger)
  val n2 = Node("B",logger)
  // another way of defining nodes, without the constructor object Node
  //val n3 = new Node[CC]("node3") with NodeCC with SeqChartLogger
  
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(i,n1,i.end.name)
  //connect(p1,n1,"Sync-i")
  //connect(p1,n2,"Sync-o")
  connect(p2,n1,"Fifo-i")
  connect(p2,n2,"Fifo-o")
  connect(j,n2,j.end.name)
  
  // Release suspension, so that they stop being in a reconfigurable state.
  //p1 ! Release("")
  p2 ! Release("")
  n1 ! Release("")
  n2 ! Release("")
  //n3 ! Release("")
  i  ! Release("")
  j  ! Release("")

  println("------ starting actors -------")
  //p1.start
  p2.start
  n1.start
  n2.start
  //n3.start
  i.start
  j.start

  spawn {
      Thread.sleep(2000)
      println("#########\nShowing final status and terminating:")
      println(i.status)
      //println(p1.status)
      println(p2.status)
      println(j.status)
      println(n1.status)
      println(n2.status)
      //println(n3.status)
      
      i ! Kill()
      //p1 ! Kill()
      p2 ! Kill()
      j ! Kill()
      n1 ! Kill()
      n2 ! Kill()
      //n3 ! Kill()
  }
}

/** A write(i) connected to a read(j) by both a lossy(los) and a syncdrain(sd).<br/>
 * The src/common/Logger defines the logging functions, that allow to observe what is happening.
 */
object SafeLossy extends Application {

  // i --> * --los--> * --> j
  //       '->--sd--<-'

  val logger = new ConsoleLogger

  // primitives
  val i  = new Writer("i",logger,41,List(1,2)) 
  val los = new LossySync("los",logger)
  val sd = new SyncDrain("sd",logger)  
  val j  = new Taker("j",logger,42,-1) 

  // nodes
  val n1 = new Node[CC]("node1",logger) with NodeCC
  val n2 = new Node[CC]("node2",logger) with NodeCC
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
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

/** A write(i) connected to a read(j) by sync channel(s).<br/>
 * The src/common/Logger defines the logging functions, that allow to observe what is happening.
 */
object TestSync extends Application {

  // i --> * --s--> * --> j

  val logger = new ConsoleLogger

  // primitives
  val i  = new Writer("i",logger,41,List(1,2))   //Debugger
  val s = new Sync("s",logger) 	    		  //Debugger
  val j  = new Taker("j",logger,42,-1)           //Debugger

  // nodes
  //val n1 = Node("node1",logger)
  val n1 = new Node[CC]("node1",logger) with NodeCC  //Debugger
  val n2 = new Node[CC]("node2",logger) with NodeCC  //Debugger
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(i,n1,i.end.name)
  connect(s,n1,"s-i")
  connect(s,n2,"s-o")
  connect(j,n2,j.end.name)
  
  // Release suspension, so that they stop being in a reconfigurable state.
  s  ! Release("")
  n1 ! Release("")
  n2 ! Release("")
  i  ! Release("")
  j  ! Release("")

  println("------ starting actors -------")
  s.start
  n1.start
  n2.start
  i.start
  j.start

  spawn {
      Thread.sleep(2000)
      println("#########\nShowing final status and terminating:")
      println(i.status)
      println(s.status)
      println(j.status)
      println(n1.status)
      println(n2.status)
      
      i  ! Kill()
      s  ! Kill()
      j  ! Kill()
      n1 ! Kill()
      n2 ! Kill()
  }
}

//////////////////////////////////////////////
/////////  Exclusive router connector ////////
//////////////////////////////////////////////

object ExRouter extends Application {

  /*
            ,--l1--> n2 --> t1
           /         |s1
   w ->  n1 ->-sd-<- n3
           \         |s2
            `--l2--> n4 --> t2
  */

  println("creating logger")
  val logger = new ConsoleLogger
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
  val n3 = new Node[CC]("node3",logger) with NodeCC
  val n4 = Node("node4",logger)
  
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w,n1,w.end.name)
  connect(l1,n1,"l1-i")
  connect(l2,n1,"l2-i")
  connect(sd,n1,"sd-l")
  connect(l1,n2,"l1-o")
  connect(s1,n2,"s1-i")
  connect(t1,n2,t1.end.name)
  connect(l2,n4,"l2-o")
  connect(s2,n4,"s2-i")
  connect(t2,n4,t2.end.name)
  connect(sd,n3,"sd-r")
  connect(s1,n3,"s1-o")
  connect(s2,n3,"s2-o")
  
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
  t2 ! Release("")

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

  /*
  spawn {
    Thread.sleep(1000)
    println("Behaviour3 w n3 sd s2 n4 n1 l2:\n"+ (
      w.behaviour.value ++ n3.behaviour.value ++ sd.behaviour.value ++ s2.behaviour.value ++ n4.behaviour.value ++ n1.behaviour.value ++ l2.behaviour.value 
    ))
    println("Behaviour3 n3 sd s2 n4 n1 l2:\n"+ (
      n3.behaviour.value ++ sd.behaviour.value ++ s2.behaviour.value ++ n4.behaviour.value ++ n1.behaviour.value ++ l2.behaviour.value 
    ))
    println("Behaviour3 n1/w:\n"+ 
      n1.behaviour.value.toString + w.behaviour.value.toString
    )
  }
  */
  
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

object Ordering extends Application {

  /*
   w1 ->  n1 \l1
         sd|   --> n3 -> t
   w2 ->  n2 / f1
  */

  val logger = new ConsoleLogger

  // primitives ---> only logging IO operators and boundary nodes.
  val f1 = new FIFO1("f1",logger,21,None)
  val sd = new SyncDrain("sd",logger)
  val l1 = new LossySync("l1",logger)
  val w1 = new Writer("w1",logger,39,List(3)) // send element(s) 3
  val w2 = new Writer("w2",logger,41,List(4)) // send element(s) 4
  val t  = new Taker("t",logger,42,2) // unbounded receives

  // nodes (Easy constructor with SeqChartLogger: Node(name))
  val n1 = Node("node1",logger)
  val n2 = Node("node2",logger) 
  val n3 = new Node[CC]("node3",logger) with NodeCC
  
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w1,n1,w1.end.name)
  connect(w2,n2,w2.end.name)
  connect(l1,n1,"l1-i")
  connect(sd,n1,"sd-l")
  connect(f1,n2,"f1-i")
  connect(sd,n2,"sd-r")
  connect(l1,n3,"l1-o")
  connect(f1,n3,"f1-o")
  connect(t,n3,t.end.name)
  
  // release primitives from suspension
  l1 ! Release("")
  f1 ! Release("")
  sd ! Release("")
  n1 ! Release("")
  n2 ! Release("")
  n3 ! Release("")
  w1 ! Release("")
  w2 ! Release("")
  t  ! Release("")

  println("------ starting actors -------")
  l1.start
  f1.start
  sd.start
  w1.start
  w2.start
  t.start

  n1.start
  n2.start
  n3.start
  
  
  spawn {
      Thread.sleep(2000)
      println("#########\nStatus in the end:")
      println(l1.status) ;  l1 ! Kill()
      println(f1.status) ;  f1 ! Kill()
      println(sd.status) ;  sd ! Kill()
      println(w1.status) ;  w1 ! Kill()
      println(w2.status) ;  w2 ! Kill()
      println(t.status)  ;  t  ! Kill()
      println(n1.status) ;  n1 ! Kill()
      println(n2.status) ;  n2 ! Kill()
      println(n3.status) ;  n3 ! Kill()
  }
}

object Discriminator extends Application {

  /*
   w1--------> n1 ---f1-->
               |l1
   w2-----> n2 +-----f2-->
            |l2|
   w3--> n3 +--+-----f3-->
         |l3|  |
          \/  /
            \/
            n5 -----f5--> n6 ->t1
  */

  val logger = new ConsoleLogger

  // primitives ---> only logging IO operators and boundary nodes.
  val f1 = new FIFO1("f1",logger,21,None)
  val f2 = new FIFO1("f2",logger,52,None)
  val f3 = new FIFO1("f3",logger,23,None)
  val f5 = new FIFO1("f5",logger,25,None)
  val l1 = new LossySync("l1",logger)
  val l2 = new LossySync("l2",logger)
  val l3 = new LossySync("l3",logger)
  val w1  = new Writer("w1",logger,39,List(3,4)) // send elements 3 and 4
  val w2  = new Writer("w2",logger,40,List(5,6)) // send elements 3 and 4
  val w3  = new Writer("w3",logger,41,List(7,8)) // send elements 3 and 4
  val t1 = new Taker("t1",logger,42,1) // unbounded receives

  // nodes (Easy constructor with SeqChartLogger: Node(name))
  val n1 = Node("node1",logger)
  val n2 = Node("node2",logger) 
  val n3 = Node("node3",logger) 
  val n5 = new Node[CC]("node5",logger) with NodeCC
  val n6 = Node("node6",logger)
  
  
  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[CC],nd:Node[CC],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  connect(w1,n1,w1.end.name)
  connect(l1,n1,"l1-i")
  connect(f1,n1,"f1-i")
  connect(w2,n2,w2.end.name)
  connect(l2,n2,"l2-i")
  connect(f2,n2,"f2-i")
  connect(w3,n3,w3.end.name)
  connect(l3,n3,"l3-i")
  connect(f3,n3,"f3-i")
  connect(l1,n5,"l1-o")
  connect(l2,n5,"l2-o")
  connect(l3,n5,"l3-o")
  connect(f5,n5,"f5-i")
  connect(f5,n6,"f5-o")
  connect(t1,n6,t1.end.name)
  
  // release primitives from suspension
  w1 ! Release("")
  w2 ! Release("")
  w3 ! Release("")
  t1 ! Release("")
  f1 ! Release("")
  f2 ! Release("")
  f3 ! Release("")
  f5 ! Release("")
  l1 ! Release("")
  l2 ! Release("")
  l3 ! Release("")
  n1 ! Release("")
  n2 ! Release("")
  n3 ! Release("")
  n5 ! Release("")
  n6 ! Release("")

  println("------ starting actors -------")
  w1.start
  w2.start
  w3.start
  t1.start
  f1.start
  f2.start
  f3.start
  f5.start
  l1.start
  l2.start
  l3.start
 
  n1.start
  n2.start
  n3.start
  n5.start
  n6.start
 
  spawn {
      Thread.sleep(2000)
      println("#########\nStatus in the end:")
      println(w1.status) ;  w1 ! Kill()
      println(w2.status) ;  w2 ! Kill()
      println(w3.status) ;  w3 ! Kill()
      println(t1.status) ;  t1 ! Kill()
      println(f1.status) ;  f1 ! Kill()
      println(f2.status) ;  f2 ! Kill()
      println(f5.status) ;  f5 ! Kill()
      println(l1.status) ;  l1 ! Kill()
      println(l2.status) ;  l2 ! Kill()
      println(l3.status) ;  l3 ! Kill()
      println(n1.status) ;  n1 ! Kill()
      println(n2.status) ;  n2 ! Kill()
      println(n3.status) ;  n3 ! Kill()
      println(n5.status) ;  n5 ! Kill()
      println(n6.status) ;  n6 ! Kill()
  }
}


////////////////////////
/*
 * DEPRECATED PART:
 * For bigger connectors, the best way is to use the ectinteraction.Sandbox
 * application, which reads connectors from the file reoconnectors/input.reo.
 * To edit this file the Eclipse Coordination Tools are required
 * (www.cwi.nl/~khoeler/ect). The application deploys and executes the connectors
 * in local engine. Nor generics nore merger nodes are implemented.
 */
