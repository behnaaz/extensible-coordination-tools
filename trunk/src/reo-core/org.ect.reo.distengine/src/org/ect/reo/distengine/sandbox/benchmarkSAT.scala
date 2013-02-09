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


object FifonSAT extends Application {
  val waittime = 5
  val times = 10
  // 2000 in 10 sec!
  print("size\tbuildTime\tsendTime")
  for (xxx <- 1 until 2302 by 200)
	  for (yyy <- 0 until times) {
		  print("\n"+xxx+"\t")
		  try {
			  new FifosSAT(xxx,waittime-2)
		  } catch {
		  case _ => println("error...")
		  }
		  Thread.sleep(1000*waittime) // 'time' seconds
	  }
  println("done")
}

class FifosSAT(val nfifos: Int, val time: Int) {//extends Application {

//  val nfifos = 500
//  val time = 10
  var count = 0
  val nchanges = (nfifos*4)+4
  var start: Long = java.lang.System.currentTimeMillis()     

  val counterlogger = new NullLogger {
    override def log_gotdata(f:String) = {
          synchronized(count+=1)
          if (count == 4)
            print(""+(java.lang.System.currentTimeMillis()-start)+
              "\t")
//          println("updating counter: "+count)
    }
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
        
    	  }
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
     case _ => {}
    }
  }
  
  val nulllogger = new NullLogger {
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
        
    	  }
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
     case _ => {}
    }
  }

  
  // i--> * --f1--> * ... * --p200--> * -->j

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val fifos = new Array[FIFO1](nfifos)
  val nodes = new Array[Node[SAT]](nfifos+1)
    
  // primitives
  val i  = new Writer("Writer",nulllogger,-1,List("a","b","c","d")) 
  
  nodes(0) = new Node[SAT]("n0",nulllogger) with NodeSAT
  connect(i,nodes(0),i.end.name)
  
  // create and connect 200 fifos and nodes
   for (i <- Array.range(0,nfifos)) {
      fifos(i) = new FIFO1("F"+i,nulllogger,i,None)
      connect(fifos(i),nodes(i),"F"+i+"-i")
      nodes(i+1) =  new  Node[SAT]("n"+(i+1),nulllogger) with NodeSAT
      //println("defined node "+(i+1))
      connect(fifos(i),nodes(i+1),"F"+i+"-o")
   }

  
  val j  = new Taker("Reader",counterlogger,nfifos,-1)
  connect(j,nodes(nfifos),j.end.name)

//  println("------ starting actors -------")
  i.start
  j.start
  
  for(fifo <- fifos) fifo.start
  for(node <- nodes) node.start  
  
//  println("---- set... ----")
  Thread.sleep(1000)
  print("->\t")
  start = java.lang.System.currentTimeMillis()  

  
  // Release suspension, so that they stop being in a reconfigurable state.
  i ! Release("")
  j ! Release("")

  for(fifo <- fifos) fifo ! Release("")
  for(node <- nodes) node ! Release("")

  spawn {
//    for (i <- Array.range(1,time)) {
//      Thread.sleep(1000) // 1 seconds
//      println("---------------- "+i+" sec ----------------")
//    }
	Thread.sleep(1000*time) // 'time' seconds

//    println("#########\nTerminating:")
    print("-")

    i ! Kill()
    j ! Kill()
    for(fifo <- fifos) fifo ! Kill()
    for(node <- nodes) node ! Kill()

  }

}


////////////////////////////
////////////////////////////

object SyncnSAT extends Application {
  val waittime = 5
  val times = 10
  // 2000 in 10 sec!
  print("size\tbuildTime\tsendTime")
  for (xxx <- 1 until 1002 by 100)
	  for (yyy <- 0 until times) {
		  print("\n"+xxx+"\t")
		  try {
			  new SyncsSAT(xxx,waittime-2)
		  } catch {
		  case _ => println("error...")
		  }
		  Thread.sleep(1000*waittime) // 'time' seconds
	  }
  println("done")
}

class SyncsSAT(val nfifos: Int, val time: Int) {//extends Application {

  var count = 0
  val nchanges = (nfifos*4)+4
  var start: Long = java.lang.System.currentTimeMillis()     

  val counterlogger = new NullLogger {
    override def log_gotdata(f:String) = {
          synchronized(count+=1)
          if (count == 4)
            print(""+(java.lang.System.currentTimeMillis()-start)+
              "\t")
//          println("updating counter: "+count)
    }
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
        
    	  }
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
     case _ => {}
    }
  }
  
  val nulllogger = new NullLogger {
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
        
    	  }
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
     case _ => {}
    }
  }

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val fifos = new Array[Sync](nfifos)
  val nodes = new Array[Node[SAT]](nfifos+1)
    
  // primitives
  val i  = new Writer("Writer",nulllogger,-1,List("a","b","c","d")) 
  
  nodes(0) = new Node[SAT]("n0",nulllogger) with NodeSAT
  connect(i,nodes(0),i.end.name)
  
  // create and connect 200 fifos and nodes
   for (i <- Array.range(0,nfifos)) {
      fifos(i) = new Sync("F"+i,nulllogger)
      connect(fifos(i),nodes(i),"F"+i+"-i")
      nodes(i+1) =  new  Node[SAT]("n"+(i+1),nulllogger) with NodeSAT
      //println("defined node "+(i+1))
      connect(fifos(i),nodes(i+1),"F"+i+"-o")
   }

  
  val j  = new Taker("Reader",counterlogger,nfifos,-1)
  connect(j,nodes(nfifos),j.end.name)

//  println("------ starting actors -------")
  i.start
  j.start
  
  for(fifo <- fifos) fifo.start
  for(node <- nodes) node.start  
  
//  println("---- set... ----")
  Thread.sleep(1000)
  print("->\t")
  start = java.lang.System.currentTimeMillis()  

  
  // Release suspension, so that they stop being in a reconfigurable state.
  i ! Release("")
  j ! Release("")

  for(fifo <- fifos) fifo ! Release("")
  for(node <- nodes) node ! Release("")

  spawn {
//    for (i <- Array.range(1,time)) {
//      Thread.sleep(1000) // 1 seconds
//      println("---------------- "+i+" sec ----------------")
//    }
	Thread.sleep(1000*time) // 'time' seconds

//    println("#########\nTerminating:")
    print("-")

    i ! Kill()
    j ! Kill()
    for(fifo <- fifos) fifo ! Kill()
    for(node <- nodes) node ! Kill()

  }

}



////////////////////////////
////////////////////////////

object SequencernSAT extends Application {
  val waittime = 5
  val times = 10
  // 2000 in 10 sec!
  print("size\tbuildTime\tsendTime")
  for (xxx <- 2 until 2603 by 260) {
  	  val size = if (xxx>2) xxx-2 else xxx 
	  for (yyy <- 0 until times) {
		  print("\n"+size+"\t")
		  new SequenceSAT(size,waittime-2)
		  Thread.sleep(1000*waittime) // 'time' seconds
	  }
  }
  println("done")
}

class SequenceSAT(val size: Int, val time: Int) {//extends Application {
  assert(size>1, "Size of the sequence must be at least 1.")
  var count = 0
  val nchanges = size*12
  var start: Long = java.lang.System.currentTimeMillis()     

//  val counterlogger = new ConsoleLogger {
  val counterlogger = new NullLogger {
    override def log_gotdata(f:String) = {
          synchronized(count+=1)
          if (count == size)
            print(""+(java.lang.System.currentTimeMillis()-start)+
              "\t")
//          println("received: "+count)
    }
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
//    	  else println("changes: "+count)
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
//    	  else println("changes: "+count)
    	case m => {} // super.log_rcv(name,to,msg)
    }
  }
  
//  val nulllogger = new ConsoleLogger {
  val nulllogger = new NullLogger {
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")        
    	  }
//    	  else println("changes: "+count)
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
//    	  else println("changes: "+count)
    	case m => {} // super.log_rcv(name,to,msg)
    }
  }

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val nodes1 = new Array[Node[SAT] with NodeSAT](size-1)
  val nodes2 = new Array[Node[SAT]](size-1)
  val fifos = new Array[FIFO1](size-1)
  val sdrains = new Array[SyncDrain](size-1)
  val wrs = new Array[Writer](size-1)
  val rds = new Array[Taker](size-1)

  // initial primitives
  val node1 = new Node[SAT]("n0",nulllogger) with NodeSAT
  val node2 = new Node[SAT]("m0",nulllogger) with NodeSAT
  val sdrain = new SyncDrain("s0",nulllogger)
  val fifo  = new FIFO1("f0",nulllogger,1,Some("")) 
  val wr  = new Writer("w0",nulllogger,2,List(0)) 
  val rd  = new Taker("r0",counterlogger,0,1)
  var prev: Node[SAT] with NodeSAT = node1

  connect(wr,node2,wr.end.name)
  connect(rd,node2,rd.end.name)
  connect(fifo,node1,"f0-o")
  connect(sdrain,node1,"s0-l")
  connect(sdrain,node2,"s0-r")
  
  // remaining primitives
  for (i <- 0 until (size-1)) {
	  nodes1(i)  = new Node[SAT]("n"+(i+1),nulllogger) with NodeSAT
	  nodes2(i)  = new Node[SAT]("m"+(i+1),nulllogger) with NodeSAT
	  sdrains(i) = new SyncDrain("s"+(i+1),nulllogger)
	  fifos(i)   = new FIFO1("f"+(i+1),nulllogger,(3*i)+4,None)
	  wrs(i)  	 = new Writer("w"+(i+1),nulllogger,(3*i)+5,List(i+1))
	  rds(i) 	 = new Taker("r"+(i+1),counterlogger,(3*i)+3,1)
   
	  connect(fifos(i),prev,"f"+(i+1)+"-i")
	  connect(fifos(i),nodes1(i),"f"+(i+1)+"-o")
	  connect(wrs(i),nodes2(i),wrs(i).end.name)
	  connect(rds(i),nodes2(i),rds(i).end.name)
	  connect(sdrains(i),nodes1(i),"s"+(i+1)+"-l")
	  connect(sdrains(i),nodes2(i),"s"+(i+1)+"-r")
	  prev = nodes1(i)
  }
  
  // close the loop of fifos
  connect(fifo,prev,"f0-i")
  
//  println("------ starting actors -------")
  wr.start
  rd.start
  fifo.start
  sdrain.start
  node1.start
  node2.start
  
  for(p <- wrs)		p.start
  for(p <- rds)		p.start
  for(p <- sdrains)	p.start
  for(p <- fifos)	p.start
  for(p <- nodes1)	p.start
  for(p <- nodes2)	p.start

//  println("---- set... ----")
  Thread.sleep(1000)
  print("->\t")
  count=0
  start = java.lang.System.currentTimeMillis()  

  
  // Release suspension, so that they stop being in a reconfigurable state.
  wr ! Release("")
  rd ! Release("")
  fifo ! Release("")
  sdrain ! Release("")
  node1 ! Release("")
  node2 ! Release("")

  for(p <- (wrs++rds++fifos++sdrains++nodes1++nodes2))
    p ! Release("")

  spawn {
	Thread.sleep(1000*time) // 'time' seconds

    print("--")

//  for(p <- (Array(node1)++nodes1))
//    println(""+p.status)
  
  for(p <- (Array(wr,rd,fifo,sdrain,node1,node2)++
              wrs++rds++fifos++sdrains++nodes1++nodes2))
    p ! Kill()
  }

}




////////////////////////////
////////////////////////////

object DiscriminatornSAT extends Application {
  val waittime = 6
  val times = 10
  // 2000 in 10 sec!
  print("size\tbuildTime\tsendTime")
  for (xxx <- 1 until 362 by 36) {
  	  val size = if (xxx>1) xxx-1 else xxx 
	  for (yyy <- 0 until times) {
		  print("\n"+size+"\t")
		  new DiscriminateSAT(size,waittime-3)
		  Thread.sleep(1000*waittime) // 'time' seconds
	  }
  }
  println("done")
}

class DiscriminateSAT(val size: Int, val time: Int) {//extends Application {
  var count = 0
  val nchanges = size*14+10
  var start: Long = java.lang.System.currentTimeMillis()     

//  val counterlogger = new ConsoleLogger {
  val counterlogger = new NullLogger {
    override def log_sentdata(f:String) = {
          synchronized(count+=1)
          if (count == 1)
            print(""+(java.lang.System.currentTimeMillis()-start)+
              "\t")
//          println("received: "+count)
    }
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
//    	  else println("changes: "+count)
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
//    	  else println("changes: "+count)
    	case m => {} // super.log_rcv(name,to,msg)
    }
  }
  
//  val nulllogger = new ConsoleLogger {
  val nulllogger = new NullLogger {
    override def log_rcv(name:String,to:String,msg:Msg) = msg match {
    	case SetPort(_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")        
    	  }
//    	  else println("changes: "+count)
    	case AddEnd(_,_,_,_) =>
          synchronized(count+=1)
    	  if (count == nchanges) {
//    		  count=0
    		  print(""+(java.lang.System.currentTimeMillis()-start)+
    				  " \t")
    	  }
//    	  else println("changes: "+count)
    	case m => {} // super.log_rcv(name,to,msg)
    }
  }

  //connect primitives to nodes
  def connect(pr:AbstractPrimitive[SAT],nd:Node[SAT],endname:EndLID) = {
    pr ! SetPort(nd,endname,EndUID(nd.name,endname))
    nd ! AddEnd(pr,pr.ends(endname).iotype.dual,endname,pr.ends(endname).uid)
  }
  
  val nodes1 = new Array[Node[SAT] with NodeSAT](size)
  val nodes2 = new Array[Node[SAT]](size)
  val fifos = new Array[FIFO1](size)
  val sdrains = new Array[SyncDrain](size)
  val lossies = new Array[LossySync](size)
  val wrs = new Array[Writer](size)

  // initial primitives
  val node1 = new Node[SAT]("x",nulllogger) with NodeSAT
  val node2 = new Node[SAT]("y",nulllogger) with NodeSAT
  val node3 = new Node[SAT]("z",nulllogger) with NodeSAT
  val fifo1 = new FIFO1("ff1",nulllogger,-2,None) 
  val fifo2	= new FIFO1("ff2",counterlogger,-1,None) 
  val rd  	= new Taker("rd",nulllogger,-3,1)

  connect(rd,node2,rd.end.name)
  connect(fifo1,node1,"ff1-i")
  connect(fifo1,node2,"ff1-o")
  connect(fifo2,node2,"ff2-i")
  connect(fifo2,node3,"ff2-o")
  
  // remaining primitives
  for (i <- 0 until size) {
	  nodes1(i)  = new Node[SAT]("n"+i,nulllogger) with NodeSAT
	  nodes2(i)  = new Node[SAT]("m"+i,nulllogger) with NodeSAT
	  fifos(i)   = new FIFO1("f"+i,nulllogger,(i*2),None)
	  sdrains(i) = new SyncDrain("s"+i,nulllogger)
	  lossies(i) = new LossySync("l"+i,nulllogger)
	  wrs(i)  	 = new Writer("w"+i,nulllogger,(i*2)+1,List(i))
   
	  connect(wrs(i),nodes1(i),wrs(i).end.name)
	  connect(fifos(i),nodes1(i),"f"+i+"-i")
	  connect(fifos(i),nodes2(i),"f"+i+"-o")
	  connect(lossies(i),nodes1(i),"l"+i+"-i")
	  connect(lossies(i),node1,"l"+i+"-o")
	  connect(sdrains(i),nodes2(i),"s"+i+"-l")
	  connect(sdrains(i),node3,"s"+i+"-r")
  }
  
  
  val prims = (Array(rd,fifo1,fifo2,node1,node2,node3)++
              wrs++fifos++lossies++sdrains++nodes1++nodes2)

  //  println("------ starting actors -------")
  for(p <- prims)
    p.start


//  println("---- set... ----")
  Thread.sleep(2000)
  print("->\t")
  count=0
  start = java.lang.System.currentTimeMillis()  

  
  // Release suspension, so that they stop being in a reconfigurable state.
  for(p <- prims)
    p ! Release("")

  spawn {
	Thread.sleep(1000*time) // 'time' seconds

    print("--")

//  for(p <- (Array(node1)++nodes1))
//    println(""+p.status)
  
  for(p <- prims)
    p ! Kill()
  }

}
