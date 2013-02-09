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

import scala.actors.Actor
import actors.Actor
import actors.remote.RemoteActor

//import scala.actors.AbstractActor
import org.ect.reo.distengine.common.PreparingNewScala._
import scala.actors.Actor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import scala.actors.Future
import scala.actors.Futures
import collection.mutable.{Map => MutMap}
import collection.mutable.{Set => MutSet}
import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.redrum.distributed._


//import org.eclipse.swt.widgets.Listener; // imported manually!
//import RemoteActorsHacked._

/**
 To be used as an intermediate between editors or managers, and the executing engines.
 Stores information regarding the status of known engines, receives updates from it, and
 sends updates to listeners implementing the ManagerTrait interface/trait.
 Provides operations to manipulate the engine, and to collect information from it. Multiple
 EngineManagers can be connected to the same engine.
 */

class EngineManager extends Actor{
  
  val engines  : MutMap[String,EngInfo] = MutMap() // engkey => enginfo
  val activeEng: MutMap[Int,EngInfo]    = MutMap() // engineHash => enginfo
  val deployed : MutMap[Int,PrimInfo]   = MutMap() // primhash => priminfo
  val managed  : MutMap[Int,PrimInfo]   = MutMap() // EditorRefHash => priminfo
  val ends     : MutMap[Int,EndInfo]    = MutMap() // endhash => endinfo
  val nodes    : MutMap[Int,NodeInfo]   = MutMap() // nodehash => nodeinfo
  var nodeRefs : Map[Int,(Boolean,Set[Int])]      = Map() withDefaultValue((false,Set[Int]()))// nodeRef => set of endhashes
  val table    : PrimTable              = new PrimTable(Nil)

  def none() = None

  var listeners  : Set[ManagerTrait] = Set()

  /*
  var engines    : HashMap[String,EngInfo]  = new HashMap() // engkey   => enginfo
  var primitives : HashMap[String,PrimInfo] = new HashMap() // primkey  => priminfo
  var nodes      : HashMap[String,NodeInfo] = new HashMap() // nodename => nodeinfo
  var listeners  : MutSet[ManagerTrait] = MutSet()
  var seed       : Int = 0
  var usedseed   : Int = 0
  var deployed   : HashMap[AnyRef,String] = new HashMap()   // Prim/Node => primk
  var undeployed : HashMap[AnyRef,String] = new HashMap()   // Prim/Node => primk
  def none : Option[Nothing] = None
  //var listeners  : Map[String,Set[ManagerTrait]] = Map() withDefaultValue Set()
  //var nodes      : List[NodeInfo] = Nil
  //var answer     : Future[Any] = null
  */

  
  //////////////////////////////
  // COLLECTING LOCAL OBJECTS //
  //////////////////////////////

  /** find an engine in engine list.*/
  private def getEngine(engk:String) : Option[EngInfo] = {
    //engines.find(x => x.name==name)
    engines.get(engk)
  }

  /** find the engine of a deployed primitive.*/
  def getDepPrimEngine(primhash:Int) : Option[EngInfo] =
    for (prim <- getDeployedPrim(primhash); res<-prim.status.optEng) yield res

  /** find the engine of a primitive mentioned in the editor.*/
  def getManPrimEngine(primref:Int) : Option[EngInfo] =
    for (prim <- getManagedPrim(primref); res<-prim.status.optEng) yield res

  /*{
    for (prim <- getPrimitive(primk);
         engk <- prim.engine;
         eng <- getEngine(engk))
      yield eng
  }*/

  /** find the engine of a node.*/
  def getNodeEngine(nodehash:Int) : Option[EngInfo] =
    for (nd <- getNode(nodehash)) yield nd.deployed.eng

  /** find a primitive from its index. */
  def getPrimitive(index:Int) : Option[PrimInfo] =
    for(pair<-table.get(index)) yield pair._2
  
  /** find a deployed primitive from its hashcode.*/
  private def getDeployedPrim(primhash:Int) : Option[PrimInfo] =
    deployed.get(primhash)

  /** find a primitive from an editor reference.*/
  private def getManagedPrim(primref:Int) : Option[PrimInfo] =
    managed.get(primref)
  
  /** check for the existence of a managed primitive. */
  def hasManPrim(primref:Int) : Boolean =
    managed.contains(primref)
  /** find the index of a managed primitive (check first that exists!) */
  def getManPrimIndex(primref:Int) : Int =
    managed(primref).index
 
  /** find a (deployed) node from its hashcode.*/
  private def getNode(nodehash:Int) : Option[NodeInfo] =
    nodes.get(nodehash)

  /** Returns the same array as passed by the callback function
   *<code>notifyExistingPrimitives</code>. */
  def existingPrimitives() : Array[String] = table.toArray
  def existingPrimitives2() : String = table.toArray.toString
  
  
  ////////////////////////////
  // REMOVING LOCAL OBJECTS //
  ////////////////////////////

  /** Removes the engine from the list of known engines, and corresponding objects.
   * Also sends a message to the engine to be removed from the listeners set. */
  def forgetEngine(engk:String) : Unit = {
    if (!(engines contains engk)) return
    val enginfo = engines(engk)
    enginfo.actor ! RmListener(hashCode)
    // remove engine from engine list
    engines -= engk
    for (prim <- enginfo.primitives)
      forgetPrimitive(prim)
    for (engh <- enginfo.optHash) {
      enginfo.optHash = None
      activeEng -= engh
    }
  }

  /** Removes a primitive from the deployed list, and corresponding objects (except engine). */
  private def forgetPrimitive(prim:PrimInfo) {
    println("em - forgeting primitive "+prim.name)
    if (prim.status.optHash.isDefined) { println("WAS DEPLOYED");deployed -= prim.status.optHash.get}
    if (prim.status.optRef.isDefined)  { println("WAS MANAGED");managed  -= prim.status.optRef.get}
	    table -= prim.index
    for (eng <- prim.status.optEng)
      eng.primitives -= prim
    println("new primitive table: "+table.toArray.mkString(";"))

    // remove ends from end list
    for (endhash <- prim.endhashes)
      forgetEnd(endhash)
  }

  /** Removes an end from the deployed end list, and corresponding nodes. */
  private def forgetEnd(endhash:Int) {
    //assert(ends contains endhash,"End of primitive not found: "+endhash)
    if (ends contains endhash) {
      val end = ends(endhash)
      println("em - forgeting end "+end.name+"/"+endhash)
      ends -= endhash
      for (node <- end.node) {
        node.endhashes -= endhash
        for (primhash <- node.deployed.optHash)
        if (node.endhashes.isEmpty) nodes -= primhash
      }
      for (nodeRef <- end.nodeRef) {
        /// CHANGED RECENTLY THE UPDATE OF ENDHASH!
        val pair = nodeRefs(nodeRef)
        val newhashes = pair._2 - endhash
        if (newhashes.isEmpty) nodeRefs = nodeRefs - nodeRef
        else 				   //nodeRefs = (nodeRefs(nodeRef) = (pair._1,newhashes))
          nodeRefs += (nodeRef -> (pair._1,newhashes))
      }
    }
    else {notifyError("End of primitive not found: "+endhash)}
  }
  
  /** Stops listening to every known engine, by unregistering itself.*/
  def stopListening(): Unit = actorDo {
    for (eng <- engines.values)
      eng.actor ! RmListener(hashCode)
  }

    /** Stops listening to an engine, by unregistering itself.*/
  def stopListening(engk: String): Unit = actorDo {
    for (eng <- engines.get(engk))
      eng.actor ! RmListener(hashCode)
  }

  /** Sends a terminate signal to the engine, and then forget it. */
  def killEngine(engk:String) = actorDo { 
    getEngine(engk) match {
      case Some(eng) => eng.actor ! Kill()
      case None      => println("Engine not found")
    }
    forgetEngine(engk)
  }

  /** Sends a kill signal to itself. */
  def stop() : Unit = {
    this ! Kill()
  }

  /** Sends a kill signal to itself within a given time. */
  def stopWithin(microseconds:Int) : Unit = {
    Thread.sleep(microseconds)
    this ! Kill()
  }
 


  ////////////////////////
  // MANAGING LISTENERS //
  ////////////////////////

  def addListener(listener: ManagerTrait) =
    listeners = listeners + listener
  def rmListener(listener: ManagerTrait)  =
    listeners = listeners - listener
  def becomeTheListener(listener: ManagerTrait) =
    listeners = Set(listener)

  //def addListener(lst : Listener) : Unit = {}
  
  /** Notify the status for all listeners */
  private def notifyStatus(reply: String) =
    for (list <- listeners)
      list.updateStatus(reply)
  /** Notify the list of primitives for all listeners */
  private def notifyPrimitives(engk:String, reply: Array[String]) =
    for (list <- listeners)
      list.updatePrimitives(engk,reply)
  /** Notify the list of engines for all listeners */
  private def notifyEngines(reply: Array[String]) =
    for (list <- listeners)
      list.updateEngines(reply)
  /** Notify time out for all listeners */
  private def notifyTimeOut() =
    for (list <- listeners)
      list.updateTimeOut()
  /** Notify error for all listeners */
  private def notifyError(msg:String) =
    for (list <- listeners)
      list.updateError(msg)
  
  /** Collect primitives and nodes, and notify to listeners. */
  private def notifyExistingPrimitives(engk:String) {
    //val resultarray = primitives.keys.map(_.toString).toList.toArray ++
    //                  nodes.keys.map(_.toString).toList.toArray
    notifyPrimitives(engk,table.toArray)
  }
 

  ///////////////////////////////
  // COLLECTING REMOTE OBJECTS //
  ///////////////////////////////

  /** Sends a request to refresh the current state of an engine.
   * Executed outside the actor thread. */
  def requestStatus(engk:String) : Unit = actorDo {
    getEngine(engk) match {
      case None      => {notifyError("Engine not found: "+engk)}
      case Some(eng) => { //requestStatus(eng:EngInfo)
    //val b = a !? (3000:Long,serialize(Status()))}
    //sendAndDo(eng.actor,Status()) {
      println("Sending status msg to engine!")    
		  eng.actor ! Status()
		  receiveWithin(2000) {
		      case (s:String) => {
		        //println("new status of '"+eng.name+"':\n"+s)
                // return information by a callback
                val extra = s + "\n\n--Engines\n"+ engines.values.mkString("\n") +
                                "\n--Deployed\n"+ deployed.values.mkString("/") +
                                "\n--Managed\n"+ managed.values.mkString("/") +
                                "\n--Nodes\n"+ nodes.values.mkString("/")
                notifyStatus(extra)
                             //+ "\n" + nodeRefs.mkString(";")
                             //+ "\n" + ends.mapElements((x:EndInfo) =>
                             //  ""+x.nodeRef+"/"+x.node.map(_.deployed.hashCode)))
		      }
		      case scala.actors.TIMEOUT =>{
		        //println("Timed out")
                notifyTimeOut
		        //eng.lastStatus = "Timed out"
		      }
		  }
      }
    }
  }

  /** Notify the current list of agents, returning also empty string as engine key. */
  def requestPrimitives() : Unit = notifyExistingPrimitives("")

  /** Send request for the updated list of agents, notify current lists,
   * and update manager info. */
  def requestPrimitives(engk:String) : Unit = 
    actorDo { privRequestPrimitives(engk) }

  private def privRequestPrimitives(engk:String) : Unit = {
    // println("em - searching for engine "+engk)
    getEngine(engk) match {
      case Some(eng) => {
        // println("em - found engine. Sending 'Content()' msg.")
        eng.actor ! AskContent()
        receiveWithin(2000) {
          // Get list of primitives; Update data base!
          case (Content(primscontent: List[PrimContent],fromHash:Int)) => {
            var receivedPrims: List[Int] = Nil
            for (PrimContent(name,hash,sourceends,sinkends,state) <- primscontent) {
              updatePrimitive(eng,name,hash,sourceends,sinkends,state,0)
              receivedPrims = hash :: receivedPrims
            }
            // if some of the engine primitives does not appear, forget it.
            for (prim <- eng.primitives) {
              val primhash = prim.status.optHash.get
              if (!(receivedPrims contains primhash)) {
                eng.primitives -= prim
                forgetPrimitive(prim)
              }
            }
            // store sender information, for updates on unknown primitives
            eng.optHash = Some(fromHash)
            activeEng(fromHash) = eng

            // notify the change
            notifyExistingPrimitives(engk)
              
          }
          
          case scala.actors.TIMEOUT =>{
            println("em - got tired of waiting for reply to content()!")
             //println("Timed out")
            notifyTimeOut
          }

        }
      }
      case None => println("Engine not found")
    }
  }
  
  /** Update a given primitive, or create an entry if new.
   * Request the connection of ends if their node reference coincide. */
  private def updatePrimitive(eng:EngInfo,name:String,hash:Int,
                              sourceends:List[EndContent],
                              sinkends:List[EndContent],
                              state:State,tag:Int) {
    var prim : PrimInfo = null

    // known deployed primitive: update fields
    if (deployed contains hash) {
      prim = deployed(hash)
      prim.name = name
      // remove deleted ends
      for (endhash <- prim.endhashes) {
        if (ends(endhash).isSource) 
          { if (!(sourceends exists (_.endhash==endhash))) forgetEnd(endhash) }
        else
          { if (!(sinkends exists (_.endhash==endhash))) forgetEnd(endhash) }
      }
      // content, ends and status updated below
    }

    // not deployed: use the tag to search for an undeployed reference
    else if (tag != 0 && managed.isDefinedAt(tag)) {
      prim = managed(tag)
      prim.name = name
      // remove deleted ends
      for (endhash <- prim.endhashes) {
        if (ends(endhash).isSource) 
          if (!(sourceends exists (_.endhash==endhash))) forgetEnd(endhash)
        else
          if (!(sinkends exists (_.endhash==endhash))) forgetEnd(endhash)
      }

      println("em - updating undeployed primitive, previously set with node refs: "+
              prim.srcRef.mkString(",")+"/"+prim.snkRef.mkString(","))

      // content, ends and status updated below
    }

    // new primitive: create entry 
    else {
      //println("em - adding new primitive (tag/hash = "+tag+"/"+hash+")")
      prim = new PrimInfo(name,0, Nil, Nil, MutSet[Int](), new IsIdle(null,0,None))
      table += ("",prim) // also updates index value
      // content, ends and status updated below
    }

    // update status
    val ref = prim.status.optRef
    prim.status = state match {
      case Idle                   => new IsIdle(eng,hash,ref)
      case Committing(_,_,_,_,_)  => new IsCommitted(eng,hash,ref)
      case Committed(_,_,_,_)     => new IsCommitted(eng,hash,ref)
      case Suspended(locks)       => new IsSuspended(eng,hash,(MutSet[String]())++locks,ref)
    }

    // update table and deployed list
    deployed(hash) = prim
    table(prim.index) = (prim.info,prim)

    // add new ends
    for (endcont <- sourceends) updateEnd(endcont,prim,true)
    for (endcont <- sinkends)   updateEnd(endcont,prim,false)
  }


  /** Update a given end, or create an entry if new. */
  private def updateEnd(endCont:EndContent,prim:PrimInfo,isSource:Boolean) = {
    // WHERE THE MAGIC WILL HAPPEN... ASSOCIATE IT TO FIRST NODE REF IT FINDS, AND CREATE NODE IF NECESSARY
    val endhash  = endCont.endhash
    val nodehash = endCont.nodehash
    val endname  = endCont.endname

    // collect node reference information, associate it to this end, and connect ends if needed
    var nodeRef = if (isSource) prim.srcRef.firstOption else prim.snkRef.firstOption
    if (isSource) prim.srcRef = prim.srcRef.drop(1)
    else 		  prim.snkRef = prim.snkRef.drop(1)
    
    //println("em - maybe adding noderef "+nodeRef+" to end "+endname)
    
    for (ref <- nodeRef) {
      /// CHANGED RECENTLY THE UPDATE OF ENDHASH!
      val pair = nodeRefs(ref)
      //nodeRefs = (nodeRefs(ref) = (pair._1,pair._2 + endhash))
      nodeRefs += (ref -> (pair._1,pair._2 + endhash))
      if (nodeRefs(ref)._2.size > 1) {
        //println("em - asking to connect endhashes: "+nodeRefs(ref).mkString(","))
        connectEnds(nodeRefs(ref)._2,nodeRefs(ref)._1)
      }
    }

    // recovering old nodeRef, if exists
    for (oldend <- ends.get(endhash); ndref <- oldend.nodeRef) nodeRef = Some(ndref)
    
    //println("em - maybe adding nodehash "+nodehash+" to end "+endname)
    // collect information about the node before creating the EndInfo,
    // and create NodeInfo if necessary
    if (nodehash == 0)
      ends(endhash) = new EndInfo(endname,isSource,prim,nodeRef,None)
    else{
      // create node if necessary
      if (!(nodes contains nodehash)) 
        nodes(nodehash) = new NodeInfo(new Deployed(prim.status.optEng.get,nodehash,None)
                                      ,MutSet(endhash)
                                      ,None )
      val newnode = nodes(nodehash)
      val oldnode = // if endhash exists and has a node
          for (end <- ends.get(endhash); node <- end.node) yield node
      // update end info, overriding if already existed
      //println("adding node to end: "+newnode.deployed.primhash+" to "+endhash)
      ends(endhash) = new EndInfo(endname,isSource,prim,nodeRef,Some(newnode))
      // update endhashes if new end not there yet
      if (!(newnode.endhashes contains endhash)) {
        newnode.endhashes += endhash
        // remove from the old node the current end
        if (oldnode.isDefined) {
          oldnode.get.endhashes -= endhash
          if (oldnode.get.endhashes.isEmpty && oldnode.get.deployed.optHash.isDefined)
            nodes -= oldnode.get.deployed.optHash.get
        }
      }
      
      //update node's status (suspended or not)
      for (node <- nodes.get(nodehash)) {
        var isSuspended = false
        for (nodeend <- node.endhashes; end <- ends.get(nodeend)) {
          isSuspended = isSuspended || end.primitive.status.isSuspended
        }
        node.deployed.isSuspended = isSuspended
      }
    }
    // add end to primitive!
    prim.endhashes += endhash
  }


  /** Method used when the engine notifies some node was changed (or created).
   * Update the node, that can be originaly bogus. If the node was changed,
   * new connections need to be requested to the engine. Joining of nodes
   * might be required. */
  private def gotNewNode(endhash:Int,nodehash:Int) : Unit = {
    // nodehash is the deployed node on the engine, attached to endhash.
    // 3 cases (possibly only case 1 will occur...):
    //  1 - oldnode was bogus (and nodehash is new!), so all the connections need to be requested;
    //  2 - there was no old node (this engine didn't requested the creation of the node);
    //  3 - old node is deployed and diff from new node -- notify error!

    println("GOT NEW NODE! - "+endhash+"/"+nodehash)
    if (!ends.contains(endhash)) {
      notifyError("Received new node information for an unknow end! How strange... ABORTING!")
      return
    }
    val end = ends(endhash)
    var oldnodehash = 0
    for (endnode <- end.node; endnodehash <- endnode.deployed.optHash)
      oldnodehash = endnodehash

    // Case 1 -- end has bogus node, to know all ends that are pending to be connected (and deployed engine).
    if (end.hasNode && oldnodehash == 0) {
      if (nodes contains nodehash) {
        notifyError("The world doesn't make sense. Old node trying to replace a bogus/nonexistent node!")
        return
      }
      val oldnode = end.node.get
      // create new node, redefine its ends, and ask to connect.
    //val newnode = NodeInfo(new Deployed(oldnode.deployed.eng, nodehash), MutSet(endhash), None)
      val newnode = new NodeInfo(new Deployed(oldnode.deployed.eng, nodehash,None), MutSet(), None)
      nodes(nodehash) = newnode
      for (existingendhash <- oldnode.endhashes; existingend <- ends.get(existingendhash)) {
        //existingend.node = newnode
        newnode.deployed.eng.actor !
          Connect(PrimPat,List(existingend.primitive.name,existingend.name,nodehash))
        // set of ends in newnode will be updated by updatePrim, after confirmation from engine
        // (changed my mind in the end... update now.)
      }
      oldnode.deployed match {
        case Requested(_,postAct) => postAct(nodehash)
        case _ => {}
      }
    }

    // Case 2 -- end has no bogus node, so there are no pending ends waiting to be connected
    //           (the connection will probably be performed from another engine manager...)
    else if (!end.hasNode) {
      // we could add the node to the database, but it will be done (and connected to end)
      // once the primitive on the engine is changed, and its change updated here.
        /*
        // add new node to database, if engine is known by the end (it should be...)
        for (eng <- end.primitive.state.optEng) {
          val newnode = new NodeInfo(new Deployed(eng,nodehash,None), MutSet(), None)
          nodes(nodehash) = newnode
        }
        // end and node completely independent so far... when the connection is made, an update
        // message for the primitive will be sent, that will connect them.
        */
    }

    // Case 3 -- end has node and is not bogus!
    else { // end has a node
        //notifyError("Life is terrible... got new node, but already had information about it! " +
        //  "Its ends are: "+(for(endhash<-end.node.get.endhashes; end<-ends.get(endhash)) yield end.name))
      println("Got new node that will replace information for old one!")
      val node = end.node.get
      for (nodeend <- node.endhashes) {
        ends(nodeend).node = Some(node)
      }
    }

  }


  /* 
  / Updates the list of primitives. /
  private def updatePrimitives(engk:String, list:List[RedrumPair[String,State]]) {
    // 1 - collect resulting primitives
    val newprims: HashMap[String,PrimInfo] = new HashMap()
    for (RedrumPair(prim,st) <- list) {
      val primInfo = PrimInfo(prim,Some(engk),getState(st))
      newprims(primInfo.key) = primInfo
    }
    // 2 - remove unused primitives
    primitives.retain((key,pinfo) => if (pinfo.engine!=Some(engk)) true
                                     else (newprims contains key))
    // 3 - add new primitives
    primitives ++= newprims
  }

  / Updates the list of nodes. /
  private def updateNodes(engk:String, list:List[RedrumPair[String,State]]) {
    println("em - processing list: "+list)
    // 1 - collect resulting nodes
    val newnodes: HashMap[String,NodeInfo] = new HashMap()
    for (RedrumPair(node,st) <- list) {
      val nodeInfo = NodeInfo(node,Some(engk),getState(st),None)
      newnodes(nodeInfo.key) = nodeInfo
    }
    println("em - new collected nodes: "+newnodes)
    // 2 - remove unused nodes
    nodes.retain((key,ninfo) => if (ninfo.engine!=Some(engk)) true
                                else (newnodes contains key))
    println("em - NODES after retention: "+nodes)
    // 3 - add new nodes
    nodes ++= newnodes
    println("em - prims after adding newnodes: "+nodes)
  }
  */

  /** Remove primitives and nodes with no associated engine.*/
  def cleanUndeployed() {
    var changed = false
    for ((hash,prim) <- managed) {
      if (!prim.status.isDeployed) {
        println("Removing primitice "+prim.name+" with index "+prim.index)
        changed = true
        table -= prim.index
        managed -= hash
      }
    }
    if (changed) notifyExistingPrimitives("")
    /*
    primitives.retain((y,pinfo) => pinfo.engine != None)
    nodes.retain(     (y,ninfo) => ninfo.engine != None)
    seed = usedseed
    undeployed = new HashMap()
    */
  }


  ////////////////////
  // ADDING OBJECTS //
  ////////////////////

  /** Introduces an engine, creating the corresponding remote actor.
   * Also sends a message to the engine to be added to its listeners set. */
  def addEngine(name:String,ip:String,port:Int) = {
    actorDo {
      println("Adding engine "+name)
      val actor = scala.actors.remote.RemoteActor.select(actors.remote.Node(ip,port), Symbol(name))
      val eng = new EngInfo(name,ip,port,actor) // ,"-")
      actor ! AddListener(hashCode)
      /*
      actor ! Status()
      receiveWithin(3000) {
          case (s:String) => eng.lastStatus = s
          case scala.actors.TIMEOUT => eng.lastStatus = "Timed out"
      }
      */
      engines(eng.key) = eng
      println("Added engine.")
      privRequestPrimitives(new EngInfo(name,ip,port,null).key)
    }
  }

  /** Just add a primitive to the list of known (undeployed) primitives. */
  def addPrimitive(primtype:String, args:Array[Any],
                   srcRef:Array[Int], srcIsRouter:Array[Boolean],
                   snkRef:Array[Int], snkIsRouter:Array[Boolean], ref:Int) : PrimInfo = {    
    if (managed contains ref)
      return managed(ref)
    val priminfo = new PrimInfo(primtype,0,srcRef.toList,
                                snkRef.toList,MutSet(),
                                new Undeployed(ref,primtype,args.toList))
    for (i <- List.range(0,srcRef.size)) {
      val hashes = nodeRefs(srcRef(i))._2
      //nodeRefs = nodeRefs(srcRef(i)) = (srcIsRouter(i),hashes)
      nodeRefs += (srcRef(i) -> (srcIsRouter(i),hashes))
    }
    for (i <- List.range(0,snkRef.size)) {
      val hashes = nodeRefs(snkRef(i))._2
      //nodeRefs = nodeRefs(snkRef(i)) = (snkIsRouter(i),hashes)
      nodeRefs += (snkRef(i) -> (snkIsRouter(i),hashes))
    }
    managed(ref) = priminfo
    table += (priminfo.info,priminfo)
    println("em - Adding undeployed primitive!")
    notifyExistingPrimitives("")
    priminfo
  }
  
  //def createPrimitive(engk:String, primType:String, args:Any*) 
  /** Create a primitive and deploy it. */
  def addPrimitive(engk:String, primType:String, args:Array[Any],
                   srcRef:Array[Int],srcIsRouter:Array[Boolean],
                   snkRef:Array[Int],snkIsRouter:Array[Boolean],ref:Int) : Unit = {
    val prim : PrimInfo = addPrimitive(primType,args,srcRef,
                                       srcIsRouter,snkRef,snkIsRouter,ref) : PrimInfo
    deployPrimitive(engk,prim.index)
  }

  /** Create a node. Done internally when deploying 2 connected primitives,
   * or when connecting 2 deployed primitives. Should be called within the actor thread. */
  private def addNode(eng:EngInfo,isRouter:Boolean,optPort:Option[Int],requesterhash:Int):Unit = {
    println("About to send a create node request!")
    if (optPort.isDefined)
      eng.actor ! Create(NodePat,List(isRouter,optPort.get,requesterhash))
    else
      eng.actor ! Create(NodePat,List(isRouter,requesterhash))
  }


  /** Connects two ends into the same node, creating the node if needed.
   * It just uses connectEnds.
   * Should be called OUTSIDE the actor thread.
   * The 2nd argument is only used when a new node is required.
   * It uses <code>connectNode</code>.
   * @see #connectEnds */
  def connectTwoEnds(endhash1:Int, endhash2:Int, isRouter:Boolean) =
    actorDo { privConnectEnds(Set(endhash1,endhash2), isRouter) }

  /** Connects a set of ends into the same node, creating the node if needed.
   * Should be called OUTSIDE the actor thread.
   * The 2nd argument is only used when a new node is required.
   * It uses <code>connectNode</code>.
   * @see #connectNode */
  def connectEnds(connEnds:Set[Int], isRouter:Boolean) : Unit =
    actorDo { privConnectEnds(connEnds, isRouter) }
  
  private def privConnectEnds(connEnds:Set[Int], isRouter:Boolean) : Unit = {
    // check if everything is suspended, and find biggest node.
    println("Asked to connect ends: "+connEnds.map((x:Int)=>""+x+"/"+ends(x).node.map(_.deployed.optHash)))
    if (connEnds.isEmpty) return

    var mainEnd : EndInfo = null
    var max : Int = 0
    for (endhash <- connEnds; end <- ends.get(endhash)) {
      if (!end.primitive.status.isSuspended) {
        notifyError("Failed to connect ends. Primitives not suspended!")
        return
      }
      val nends = if (end.hasNode) end.node.get.endhashes.size else -1
      if (max <= nends) {
        mainEnd = end
        max = nends
      }
      // mainEnd will be the end with a node with more ends, if such end exists
    }
    // If no node is found, create new node and deploy it.
    // Assume it exists before confirmation of deployment!
    if (mainEnd == null) {
      println("No end with node found. About to create a new node")
      for (endhash <- connEnds.find((x:Int)=>true); // random end
           end<-ends.get(endhash);
           eng<-end.primitive.status.optEng) {
        // CREATE NEW NODE IN ENGINE
        addNode(eng,isRouter,None,endhash)
        // STORE TEMPORARY (BOGUS) NODE ASSOCIATED TO ENDS!
        //val bogusnode = new NodeInfo(new Deployed(eng,0,None),MutSet()++connEnds,None)
        val bogusnode = new NodeInfo(new Requested(eng,(_:Int)=>{}),MutSet()++connEnds,None)
        for (endhash<-connEnds; end<-ends.get(endhash))
          end.node = Some(bogusnode)
        mainEnd = end
      }
    }
    else
      println("END WITH NODE FOUND: "+mainEnd)
    // if it failled to find a valid mainEnd until now, fail!
    if (mainEnd == null) {
      notifyError("No valid end found when connecting ends!")
      return
    }
    // All this to get a main end, with a (maybe bogus) node associated to it.
    // Now... make the connections!
    println("about to send connect requests!")
    //if (mainEnd.node.get.status.optHash.get == 0)
      for (endhash<-connEnds; _<-ends.get(endhash)) {
        println("Entering connectNode for end "+ends(endhash).name)
        connectNode(mainEnd.node.get,endhash,isRouter)
      }
    //else
    //  for (endhash<-connEnds; _<-ends.get(endhash))
    //    if (ends(endhash) != mainEnd) connectNode(mainEnd.node,endhash,isRouter)
  }

  /** Connect a node (could be bogus, ie, not confirmed that was deployed) to an end.
   * Needs to be executed in the Actor thread.
   * 3 cases: 1 - node and endnode coincide (and not bogus)-- do nothing;
              2 - end has no node or bogus node -- ask to connect to node;
              3 - end has another node -- for all ends in endnode, send disconnect old and connect new. */
  private def connectNode(node:NodeInfo, endhash:Int, isRouter:Boolean) :Unit = {
    println("about to connect node to end "+ends(endhash).name)
    // check if everything is defined and suspended
    assume (ends.isDefinedAt(endhash))
    assert (node.deployed.optEng.isDefined , "Node must be deployed!")
    val end = ends(endhash)
    if (!end.primitive.status.isSuspended) {
      notifyError("Primitive must be suspended!")
      return
    }
    val nodehash = if (node.deployed.optHash.isDefined) node.deployed.optHash.get
                   else 0 
    // case 1
    for (endnode <- end.node) if (endnode == node && nodehash != 0) return

    // case 1.5
    if (nodehash == 0) {
      println("Just adding endhash to the bogus node set of ends.")
        node.endhashes += endhash
        return
      }

    // case 2
    if ((!end.hasNode) || end.node.get.deployed.optHash.get == 0) {
      println("about to send a connect signal finally! - "+
        List(end.primitive.name,end.name,node.deployed.optHash.get))
      node.deployed.optEng.get.actor ! 
        Connect(PrimPat,List(end.primitive.name,end.name,nodehash))
    }

    // case 3
    else
      for (endnode <- end.node; nodeendhash <- endnode.endhashes; end <- ends.get(nodeendhash)) {
        println("DISCONNECTING FIRST END "+end.name)
        privDisconnectNode(end)
        end.node = None // to send connect signal on recursive call.
        connectNode(node, nodeendhash, isRouter) // to check if suspended and all is defined.
      }
    // update endinfo and nodeinfo when confirmed by the engine, via updatePrim
  }

  /** Sends a disconnect signal from an end to a node. Not checking if node is suspended! */
  private def privDisconnectNode(end:EndInfo) : Unit =  {
    if (!end.primitive.status.isSuspended) {
      notifyError("Primitive must be suspended!")
      return
    }    
    end.primitive.status.optEng.get.actor !
      Disconnect(PrimPat,List(end.primitive.name:PrimID,end.name:EndLID))
  }

  /** Sends a disconnect signal from an end to a node. Not checking if node is suspended! */
  def disconnectNode(endhash:Int) : Unit = actorDo {
    if (!ends.isDefinedAt(endhash)) {
      notifyError("End with code "+endhash+" was not found.")
      return
    }
    privDisconnectNode(ends(endhash))
  }
  

      
  /** UNDER CONSTRUCTION -- Connect two remote nodes, creating a new remote node if required. 
   * The first node is attached to a known end, and the second is just a remote reference. **/
  def connectRemotetNodes(endhash:Int, isRouter:Boolean
                        ,remotename:String, remoteIP:String, remotePort:Int) :Unit = {
    // check if everything is defined and suspended
    assume (ends.isDefinedAt(endhash))
    val end = ends(endhash)
    for (node <- end.node) {
      assert (node.deployed.optEng.isDefined && node.deployed.optHash.isDefined, "Node must be deployed!")
      for (nodeendh <- node.endhashes; nodeend <- ends.get(nodeendh))
        if (!nodeend.primitive.status.isSuspended) {
          notifyError("Primitive must be suspended! - "+nodeend.primitive.name)
          return
        }
    }
    // Should now create a new remote node if needed, update the ends attached to old node,
    // and only then send the request for remote connection. Suspension of all primitives
    // is only required if a new node needs to be created.
  }


  /** Deploy a primitive by calling the factory in the corresponding engine.
   * It will use the engine value previously defined. */
  def deployPrimitive(primIndex:Int) : Unit =
    deployPrimitive("",primIndex)

  /** Deploy a primitive by calling the factory in the corresponding engine.
   * It will use the new engine value only if it is a non-empty string. */
  def deployPrimitive(engk:String, primIndex:Int) : Unit = actorDo {
    // if the index exists
    val optprim = getPrimitive(primIndex)
    if (optprim isDefined) {
      val pinfo = optprim.get
      if (pinfo.status.isInstanceOf[Undeployed]) {
        //case IsUndeployed(primtype,id,args) =>
        val optEng : Option[EngInfo] = if (engk == "") pinfo.status.optEng else getEngine(engk)
        pinfo.status.optEng = optEng
        optEng match {
          case Some(eng) =>
            println("em - about to deploy primitive "+pinfo.name)
            val st = pinfo.status.asInstanceOf[Undeployed]
            eng.actor ! Create(PrimPat,List(st.primtype,st.args,st.ref))
            eng.primitives += pinfo
            // Do not update internal list now. An updContent msg will be sent later.
              /*
              pinfo.engine = Some(engk)
              primitives -= primk
              primitives(pinfo.key) = pinfo
              usedseed = usedseed max id
              notifyExistingPrimitives(engk)
              */
           case None =>
             notifyError("Engine not found when deploying primitive "+pinfo.name)
                
          }
      }
      else notifyError("Primitive is deployed already: "+pinfo)                             
    }
  }
    

  /////////////////////////////
  // UPDATING REMOTE OBJECTS //
  /////////////////////////////

  /** Suspend a primitive, given its index.*/
  def suspendPrimitive(primindex:Int) : Unit = actorDo {
    val send =
      for (pair <- table.get(primindex); eng <- pair._2.status.optEng)
        yield eng.actor ! Suspend(pair._2.name)
    if (!send.isDefined)
      notifyError("Failed to send suspension request.\nPrimitive or engine unknown at index "+primindex)
  }

  /** Release a primitive, given its index.*/
  def releasePrimitive(primindex:Int) : Unit = actorDo {
    val send =
      for (pair <- table.get(primindex); eng <- pair._2.status.optEng)
        yield eng.actor ! Release(pair._2.name)
    if (!send.isDefined)
      notifyError("Failed to send release request.\nPrimitive or engine unknown at index "+primindex)
  }


  /** Send a remove signal to a primitive, and forget it (disconnecting from nodes).
   * The primitive should send a disconnect signal to its adjacent nodes.
   * The primitive needs to be suspended. */
  def removePrimitive(primindex:Int) : Unit = removePrimitives(Array(primindex))
  
  /** Sends multiple remove signals, without waiting for the confirmation.
   *  Also removes the resulting empty nodes. */
  def removePrimitives(primindexes:Array[Int]) : Unit = actorDo {
    // collext all primitives
    val prims = primindexes.map(
      (x:Int) => for (pair <- table.get(x)) yield (pair._2)
    )
    
    for (optPrim <- prims) {
	    val send =
	      for (prim <- optPrim; eng <- prim.status.optEng) yield {
	        println("Removing primitive "+prim.name+"/"+prim.index)
	        if (!prim.status.isSuspended) {
	          notifyError("Failed to remove primitive "+prim.name+
                         ", because it is not suspended.")
	        }
            else {
              var suspNodes = true
              for (endhash <- prim.endhashes; end <- ends get endhash; node <- end.node)
                if (!node.deployed.isSuspended) suspNodes = false
              if (!suspNodes)
                notifyError("Failed to remove primitive "+prim.name+
                            ", because an attached node is not suspended.")
              else {
                // THESE LINES BREAK THE REMOVAL!
                //for (endhash <- prim.endhashes; end <- ends get endhash; _ <- end.node)
                //  eng.actor ! Disconnect(PrimPat,List(prim.name,end.name))
                eng.actor ! Remove(PrimPat,prim.name)
                for (endhash <- prim.endhashes; end <- ends get endhash; node <- end.node) {
                  if (node.endhashes == MutSet(endhash))
                    eng.actor ! Remove(NodePat,node.deployed.optHash.get)
                }
                forgetPrimitive(prim)
                notifyExistingPrimitives(eng.key)
              }
            }
	      }
	    if (!send.isDefined)
	      notifyError("Failed to send remove request and removing primitive."+
	                  " Primitive unknown: "+optPrim.map(_.name)+" at index "+optPrim.map(_.index))
    }
  }
  
  /** Makes an end(or node) alive, in the sense that it can be accessed from the network
   * using a given port. */
  def awakeNode(endhash:Int, port:Int) : Unit = {
    awakeNodeAndDo(endhash,port,(_:Int)=>{})
  }
  
  def awakeNodeAndDo(endornodehash:Int, port:Int, andDo:Int=>Unit) : Unit = actorDo {
    // endhash is known?
    if (!ends.contains(endornodehash) && !nodes.contains(endornodehash)) {
      notifyError("endhash/nodehash not found: "+endornodehash)
      return
    }
    var endhash = 0
    if (ends.contains(endornodehash))
      endhash = endornodehash
    else for (node <- nodes.get(endornodehash); endh <- node.endhashes)
        endhash = endh
    
    val end: EndInfo = ends(endhash)
    //var node : NodeInfo == null
    // node exists already?
    for (endnode <- end.node) {
      // node already awake to the same port? good... finished
      if (endnode.port.isDefined && endnode.port.get == port) return
      // chech if all ends are suspended
      for (endhash<-endnode.endhashes; end<-ends.get(endhash))
        if (!end.primitive.status.isSuspended) {
          notifyError("Failed to awake node. Primitive not suspended: "+end.primitive.name)
          return
        }
      // new node... a bogus node with the port information
      //val deployed = new Deployed(endnode.deployed.eng, 0, endnode.deployed.optRef)
      val deployed = new Requested(endnode.deployed.eng, andDo)
      val bogusnode = new NodeInfo(deployed, endnode.endhashes, Some(port))
      for (endhash<-endnode.endhashes; otherend<-ends.get(endhash)) {
          otherend.node = Some(bogusnode)
      }
      // end.node = Some(bogusnode) <--- already, if node.endhashes and end.node are consistent
      // add a node associated to this end, to later replace the bogus node.
      // note that it will not be a router node, since remote node have to be simple
      addNode(endnode.deployed.eng,false,Some(port),endhash)
      // removing previous (not awake) node
      println("REMOVING NODE STATIC NODE?")
      for (nodehash <- endnode.deployed.optHash) {
        println("YES! - "+nodehash)
        endnode.deployed.eng.actor ! Remove(NodePat,nodehash)
      }

    }
    if (!end.hasNode) {
      // add simpler node that if it already existed
      //val deployed = new Deployed(end.primitive.status.optEng.get, 0, None)
      val deployed = new Requested(end.primitive.status.optEng.get, andDo)
      val bogusnode = new NodeInfo(deployed, MutSet(endhash), Some(port))
      end.node = Some(bogusnode)
      // add a node associated to this end, to later replace the bogus node.
      // note that it will not be a router node, since remote node have to be simple
      addNode(end.primitive.status.optEng.get,false,Some(port),endhash)
    }
    
    // connections should be done once the node creation is confirmed.    
  }


  ////////////////////////////////////////
  // MANUAL OPERATIONS, if known engine //
  ////////////////////////////////////////

  /** Blindely introduces an engine, creating the corresponding remote actor.
   * Also sends a message to the engine to be added to its listeners set.
   * DOES NOT send a message to request the updated list of primitives.  */
  def blindAddEngine(name:String,ip:String,port:Int) = {
    actorDo {
      val actor = scala.actors.remote.RemoteActor.select(actors.remote.Node(ip,port), Symbol(name))
      val eng = new EngInfo(name,ip,port,actor) // ,"-")
      actor ! AddListener(hashCode)
      engines(eng.key) = eng
    }
  }

    
  /** Blindely send a message to a node or primitive of a known engine, dependent of
   * whether the name can be parsed as an integer or nor, repectively
   */
  private def blindSendMsg(engk:String, obj:String, primmsg:String => Msg, nodemsg:Int => Msg) = actorDo {
    for (eng<-engines.get(engk)) {
      try
        { eng.actor ! nodemsg(java.lang.Integer.parseInt(obj)) }
      catch
        { case _ => eng.actor ! primmsg(obj) }
    }
  }

  /** Blindely send a remove primitive signal, without updating internal data structures.
   * @see blindSendMsg */
  def blindSendRemoveObject(engk:String, objname:String) =
    blindSendMsg(engk,objname,Remove(PrimPat,_),Remove(NodePat,_))

  /** Blindely send a suspend signal to a node or primitive (to a node if objname is an integer),
   * without updating internal data structures. */
  def blindSendSuspendPrimitive(engk:String, objname:String) = actorDo {
    for (eng<-engines.get(engk))
      eng.actor ! Suspend(objname)
  }

  /** Blindely send a suspend primitive signal, without updating internal data structures. */
  def blindSendReleasePrimitive(engk:String, objname:String) = actorDo {
    for (eng<-engines.get(engk))
      eng.actor ! Release(objname)
  }

  /** Blindely send a connect signal, to connect an end to a node, without updating internal data structures.
   * If nodehash is 0 then create a new node (not yet this part). */
  def blindSendConnectNode(engk:String, primname:String, endname:String, nodehash:Int) = actorDo {
    for (eng<-engines.get(engk)) {
      val end = new EndInfo(endname,true,new PrimInfo(primname,0,Nil,Nil,MutSet(),new IsSuspended(eng,0,MutSet(),None)),None,None)
      val endhash = end.hashCode
      end.primitive.endhashes += endhash
      val node = new NodeInfo(new Deployed(eng,nodehash,None),MutSet(endhash),None)
      end.node = Some(node)
      ends(endhash) = end
      connectNode(node, endhash, false)
      ends -= endhash
    }
  }

  /** Blindely add a node to an end that may be unknown to the internal data structure. */
  def blindSendCreateNode_old(engk: String, primname: String, endname: String, isRouter: Boolean) = actorDo {
    println("Entered the blindSendCreateNode method...")
    for (eng<-engines.get(engk)) {
      val end = new EndInfo(endname,true,new PrimInfo(primname,0,Nil,Nil,MutSet(),new IsSuspended(eng,0,MutSet(),None)),None,None)
      val endhash = end.hashCode
      ends(endhash) = end
      end.primitive.endhashes += endhash
      println("about to connect ends with only the endhash "+endhash)
      privConnectEnds(Set(endhash),isRouter)
      ends -= endhash
    }
  }


  /*
  / Blindely send a connect signal, between 2 ends, without updating internal data structures.
   * If any of the ends already exists in the internal data structures, they are removed. /
  def blindSendConnectEnds(engk:String, endhash1:Int, endhash2:Int, nodehash:Int) = actorDo {
    for (eng<-engines.get(engk)) {
      val fakeprim1 = new PrimInfo("p1",0,MutSet(),new IsSuspended(eng,0,MutSet()))
      val fakeprim2 = new PrimInfo("p2",0,MutSet(),new IsSuspended(eng,0,MutSet()))
    }
  }
  */


  /** Blindely send a connect signal, to connect 2 remote nodes, without updating internal data structures.
   *  Accepts both a node hash and an end hash. */
  def blindSendConnectRemoteNodes(engk:String, localnode:Int,
               remotenodename:String, remoteIP:String, remotePort:Int) = actorDo {
    getEngine(engk) match {
      case Some(eng) =>
        ends.get(localnode) match {
          case Some(end) => for (node <- end.node; hash <- node.deployed.optHash)
            eng.actor ! Connect(NodePat,List(hash,remotenodename,remoteIP,remotePort))
          case None =>
            eng.actor ! Connect(NodePat,List(localnode,remotenodename,remoteIP,remotePort))
        }
        //eng.actor ! Connect(NodePat,List(localnode,remotenodename,remoteIP,remotePort))
      case None => {}
    }
  }
  
  /** Blindely sends a signal to create the nodes, and a connect signal.
   *  Main difference is that it accepts only the end names! */
  def blindConnectRemotePrimsIndexes(primindex1:Int, primindex2:Int) = {
    var success = false
    for (p1 <- getPrimitive(primindex1); hash1 <- p1.status.optHash; eng1 <- p1.status.optEng;
         p2 <- getPrimitive(primindex2); hash2 <- p2.status.optHash; eng2 <- p2.status.optEng) {
      val port1 = scala.actors.remote.TcpService.generatePort
      val port2 = scala.actors.remote.TcpService.generatePort
      blindConnectRemotePrims(eng1.key,hash1, port1, eng2.key, hash2, port2)
      success = true
    }
    if (!success)
      notifyError("Primitives must be deployed (engine ot hashcode not found).")
  }
  def blindConnectRemotePrims(engk1:String, primhash1:Int, port1:Int,
                             engk2:String, primhash2:Int, port2:Int) = actorDo {
    println("em - asked to connect 2 prims")
    println("em - current engines and deployed prims: "+engines.values.mkString(";")+
       " -- "+deployed.values.mkString(";"))
    for (eng1 <- getEngine(engk1); eng2 <- getEngine(engk2);
         prim1 <- deployed.get(primhash1); prim2 <- deployed.get(primhash2)) {
      println("em - found 2 prims")
      var done1 = false
      var endhash1 = 0
      var done2 = false
      var endhash2 = 0
      for (endhash <- prim1.endhashes; end <- ends.get(endhash))
        if (!done1 && !end.hasNode) {
          endhash1 = endhash
          done1 = true
          }
      for (endhash <- prim2.endhashes; end <- ends.get(endhash))
        if (!done2 && !end.hasNode) {
          endhash2 = endhash
          done2 = true
          }
      if (!done1 || !done2) {
        notifyError("No end without node was found next to primitive "+primhash1+" or "+primhash2+".")
      }
      else {
	      println("em - found 2 ends with a nice node")
	      
	     
	      awakeNodeAndDo(endhash1,port1, (nodehash1:Int) => {
	        println("em - created node 1!")
	        awakeNodeAndDo(endhash2,port2, (nodehash2:Int) => {
	          println("em - created node 2!")
	          //println("Lets check if the port was known by now... "+nodes(nodehash2).port)
              //println("Lets check better if the port was known by now... "+nodes.values.map(_.port).mkString(";"))
	          eng2.actor !
	            Connect(NodePat,List(nodehash2,nodehash1,eng1.ip,port2,eng1.port, eng1.name))        
	        })
	      })
       }
    }
  }



  ////////////////////////////////////////
  // MSG PROCESSING BY THE ACTOR THREAD //
  ////////////////////////////////////////

  def act = {
    //println("starting!")
    // set the class loader.
    RemoteActor.classLoader = getClass().getClassLoader()
    
    //react{ case m => { println("bu")}}
    
    loop { react {
      // Forward a message to an engine
      case Forward(eng:Actor,m:Msg) => {
        println("Sending msg: "+m)
        eng ! m
      }

      // Forward a message to an engine and runs some code
      case ForwardRun(eng:Actor,m:Msg,f) => {
        eng ! m
        f()
      }

      // Forward a message to an engine and runs some code
      case ActorRun(f) => {
        f()
      }

      // Update on some primitive (search for it first in deployed, then in managed with the tag)
      case UpdContent(primct: PrimContent, tag:Int, fromHash:Int) => {
        // needs to find the engine where this primitive belong. Search in deployed and then in managed.
        if (deployed contains primct.primhash)
          for (prim <- deployed.get(primct.primhash); eng <- prim.status.optEng)
            updatePrimitive(eng,primct.name,primct.primhash,primct.sourceends,primct.sinkends,primct.state,tag)
        else if (managed contains tag)
          for (prim <- managed.get(tag); eng <- prim.status.optEng)
            updatePrimitive(eng,primct.name,primct.primhash,primct.sourceends,primct.sinkends,primct.state,tag)
        else if (activeEng contains fromHash)
          for (eng <- activeEng.get(fromHash))
            updatePrimitive(eng,primct.name,primct.primhash,primct.sourceends,primct.sinkends,primct.state,tag)
        else notifyError("Received a primitive update from "+fromHash+", but wasn't deployed yet,"+
                         " not managed, and not from an active engine!\n"+
                         "(deployed/managed/activeEng) = ("+deployed.keys.mkString(",")+
                         " / "+managed.keys.mkString(",")+" / "+activeEng.keys.mkString(",")+")")
        // notify the changes
        notifyExistingPrimitives("")
      }

      // Update on a node.
      case NewNode(RedrumPair(endhash: Int, nodehash: Int)) => {
        gotNewNode(endhash,nodehash)
      }
      
      case ErrorMsg(msg:String) => {
        notifyError(msg)
      }

      case Kill() => {
        for (eng <- engines.values) eng.actor ! RmListener(hashCode)
        exit()
      }
                   //{println("killing manager!"); exit()}
      case m:AnyRef => {
        println("Unknwown message received '"+m+"':"+m.getClass())
      }
    }}
  }
  
  def send(eng:Actor,m:Msg) = this ! Forward(eng,m)
  def sendAndDo(eng:Actor,m:Msg)(f : =>Unit) =
    this ! ForwardRun(eng,m,() => f)
  def actorDo(f : =>Unit) =
    this ! ActorRun(() => f)
  
}

/////////////////////
// DATA STRUCTURES //
/////////////////////

class EngInfo(val name:String,
              val ip:String, 
              val port:Int,
              val actor:AbstractActor) {
  val primitives: MutSet[PrimInfo] = MutSet()
  var optHash: Option[Int] = None
  def key:String = name+"@"+ip+":"+port
  
  override def toString() = {
    key + " - " + primitives.mkString("/")
  }
}

class PrimInfo(var name: String,
               var index: Int,
               var srcRef: List[Int],
               var snkRef: List[Int],
               val endhashes: MutSet[Int],
               var status: StatusInfo) {
  def info : String =
    resizeStr(16,name) + " " + status.info
  //def key:String = engine match {
  //  case Some(engk) => name+" - "+engk
  //  case None => name+" - NoEngine"
  //}
  override def toString() = {
    if (status.optHash.isDefined) name+"["+index+"<"+status.optHash.get+">]"
    else 						  name+"["+index+"]"
  }
}


class EndInfo(val name: String,
              val isSource: Boolean,
              val primitive: PrimInfo,
              var nodeRef: Option[Int],
              var node: Option[NodeInfo]) {
  def hasNode = node.isDefined
}


class NodeInfo(val deployed:NodeStatus,
               val endhashes:MutSet[Int],
               val port:Option[Int]) {
  //def key:String = engine match {
  //  case Some(engk) => name+" - "+engk
  //  case None => name+" - NoEngine"
  //}
  override def toString() = {
    "("+endhashes.mkString("*")+" - "+deployed.state+")"
  }
}

// Status of a primitive (for nodes, only the deployed case is considered!)
abstract class StatusInfo {
  def optRef: Option[Int]
  var optEng: Option[EngInfo]
  def optHash: Option[Int]
  def isIdle = false
  def isCommitted = false
  var isSuspended = false
  def isDeployed :Boolean
  def state = 
    if      (isIdle)      "Idle"
    else if (isCommitted) "Committed"
    else if (isSuspended) "Suspended"
    else if (!isDeployed) "Undeployed"
    else                  "Unknown state"

  def info = {
    var res:String = ""
    res =  (if (optEng isDefined) (resizeStr(9,optEng.get.name)+" ")     else resizeStr(10,""))
    res += (if (optRef isDefined) (resizeStr(12,"["+optRef.get+"]")+" ") else resizeStr(13,""))
    res += "- "
    res += state
    res
  }
}

sealed abstract class NodeStatus extends org.ect.reo.distengine.redrum.deployment.StatusInfo {
  val eng:EngInfo
} 

class Undeployed(val ref:Int,val primtype:String, val args:List[Any])
extends StatusInfo {
  def optRef = Some(ref)
  var optEng: Option[EngInfo] = None
  def optHash: Option[Int] = None
  def isDeployed = false
  override def toString : String =
    "Undeployed("+args.mkString(",")+")"
}

case class Requested(val eng:EngInfo, val postAction:Int=>Unit)
extends NodeStatus {
  var optEng: Option[EngInfo] = Some(eng)
  def optRef = None
  def optHash = None
  def isDeployed = false
  override def toString: String = "Requested to "+eng.name
}

class Deployed(val eng:EngInfo, val primhash:Int, ref:Option[Int])
extends NodeStatus {
  def optRef = ref
  var optEng: Option[EngInfo] = Some(eng)
  def optHash = Some(primhash)
  def isDeployed = true
}

class IsIdle(engInfo:EngInfo, primhash:Int, ref:Option[Int])    extends Deployed(engInfo,primhash,ref) {
  override def isIdle = true
}
class IsCommitted(engInfo:EngInfo, primhash:Int, ref:Option[Int]) extends Deployed(engInfo,primhash,ref) {
  override def isCommitted = true
}
class IsSuspended(engInfo:EngInfo, primhash:Int, locks:MutSet[String], ref:Option[Int])
extends Deployed(engInfo,primhash,ref) {
  isSuspended = true
  override def toString : String =
    "Suspended("+locks.mkString(",")+")"
}

class PrimTable(private var list: List[(String,PrimInfo)]) {
  /** returns a list of readable entries of known primitives. */
  def toArray = list.map(_._1).toArray
  /** Returns an optional reference to the primitive in the editor. */
  def apply(index:Int) : Option[PrimInfo] = get(index) map (_._2)
  /** Returns an optional pair from the table. */
  def get(index:Int) : Option[(String,PrimInfo)] =
    if(list.isDefinedAt(index)) Some(list(index))
    else None
  /** Adds a new element to the end of the table. */
  def += (content:String,prim:PrimInfo) = {
    prim.index = list.size
    list = list ::: List((content,prim)) // adds to the end of the list (index=size)
  }
  /** Removes an element, and places the last element in that place. */
  def -= (index:Int) {
    if (index<0 || index>(list.size-1)) throw new java.util.NoSuchElementException("when accessing index "+index)
    else if (index == (list.size-1)) list = list.init
    else {
      list = list.take(index) ::: (list.last :: (list.drop(index+1).init))
      list(index)._2.index = index
    }
  }
  /** Updates the content of an element of the table. */
  def update(index:Int,value:(String,PrimInfo)) = {
    list = list.take(index) ::: (value :: (list.drop(index+1)))
    list(index)._2.index = index
  }


}

case class Forward(eng:Actor, m:Msg)
case class ForwardRun(eng:Actor, m:Msg, f : () => Unit)
case class ActorRun(f : () => Unit)
