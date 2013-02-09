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
package org.ect.reo.distengine.colouring.primitives;

//import org.ect.reo.distengine.colouring._;
//import org.ect.reo.distengine.colouring.
import org.ect.reo.distengine.common._;
import org.ect.reo.distengine.common.Utils._;
import org.ect.reo.distengine.redrum.{Primitive, End}
import org.ect.reo.distengine.redrum.genericprim.Initiator
import org.ect.reo.distengine.colouring._
import collection.mutable.HashMap
import collection.mutable.HashSet
import collection.mutable.{ Set => MutSet }
import collection.immutable.{HashSet => ImutSet}
import scala.collection.Set

/*
 * FIFO1 channel
 * Seems to work fine only if rank is stronger, because:
 * - initiating goes only to the direction of the end, and
 * - higher requests to the initiating end are propagated (from both directions)
 */
class FIFO1(val name:PrimID,logger:Logger, val rank:Int,private var buffer :Option[Any])
extends AbstractPrimitive[org.ect.reo.distengine.colouring.CC] with scala.actors.Actor
{
  
  private val aEndName  = name + "-i"
  private val bEndName = name + "-o"
  val ends : Ends = new HashMap()
  
  // initial behaviour with no CT, so the ends can be initiallised, required to define the CT
  val behaviour = new org.ect.reo.distengine.colouring.CC(new ColouringTable)
  
  private val fifo = this

  private val aPrim: Initiator[CC] = new Initiator[CC](name, logger: Logger) {
    override val endname = aEndName
    val rank: Int = fifo.rank
    val io: IOType = Input
    val behaviour = new org.ect.reo.distengine.colouring.CC(new ColouringTable)
    val end: End[CC] = new End(endname, io, this) // behaviour must be set by now!
    updateEnds
    val uid = EndUID(name, endname)

    var propagatedCol = new Colouring //ColouringTable.zero


    def source(col: Colouring, e: EndUID) = {
      new Left(new HashSet[EndUID])
    }

    def updateStep(col: Colouring) = {
      //log_warning(name,"UPDATING STEP ACCORDING TO: "+ct)
      if (col hasFlow aUID) {
        logger.log(name, "INITIATING to the other side!")
        //logger.log_warning(name,"going from empty to full and trying to init b. State: "+getstate(endout))
        behaviour.value = a_ctfull // updating value of both a and b primitives
        bPrim.behaviour.value = b_ctfull
        bPrim.initiate()
      }
      else logger.log(name, "Behaviour unchanged! Not INITIATING.")
    }

    /** Propagation of the colouring table when: (1) there exists a valid colouring,
      * and (2) data is flowing. */
    def propagate(col: Colouring) = {
      //val ct = c.getSingleton
      if (col hasFlow uid) {
        end.toPort(AskData(col))
        val toReply = MutSet[EndLID](dummyEndName)
        propagatedCol = col
        state = WaitingData(new HashSet, toReply, col)
      }
      else {
        end.toPort(SingleStep(col))
        endStep(endname, col)
      }
    }

    override def gotCommunicateMsg(endname: EndLID, msg: CommunicateMsg): Nothing = {
      //logger.log_debug(name,"Taker received some data message of type "+msg.getClass)
      msg match {
        case DataStep(dt: Any, col: Colouring) => {
          logger.log_rcv(name, endname, msg)
          logger.log_gotdata(name)
          logger.log(name, "GOT DATA: [[ " + dt + " ]]")
          //println("GOT DATA STEP!! Updating fifo.buffer.")
          fifo.buffer = Some(dt)
          endStep(endname, col)
          nextInput
        }
        case ReplyData(dt: Any) => {
          logger.log_rcv(name, endname, msg)
          logger.log_gotdata(name)
          logger.log(name, "GOT DATA: [[ " + dt + " ]]")
          //println("GOT DATA REPLY!! Updating fifo.buffer.")
          fifo.buffer = Some(dt)
          endStep(endname, propagatedCol)
          nextInput
        }
        case x => {
          super.gotCommunicateMsg(endname, msg)
        }
      }
    }

    // logging function are imported from fifo abstract primitive
    /*
    override def log(from:String,msg:String)                = fifo.log(from+"-in",msg)
    override def log_debug(from:String,msg:String)          = fifo.log_debug(from,msg)
    override def log_rcv(prim:String,end:String,msg:Msg)    = fifo.log_rcv(prim+"-in",end,msg)
    override def log_toPort(prim:String,end:String,msg:Msg) = fifo.log_toPort(prim+"-in",end,msg)
    override def log_warning(fromend:String,msg:String)     = fifo.log_warning(fromend,msg+" (INPUT END)")
    override def log_updateStep(from:String)                = fifo.log_updateStep(from)
    override def log_initiate(from:String,rank:Int)         = fifo.log_initiate(from,rank)
    override def log_propagate(from:String)                 = fifo.log_propagate(from)
    override def log_commit(from:String,table:String,toOutside:Boolean) = fifo.log_commit(from,table,toOutside)
    override def log_gotbehaviour(from:String,beh:String)   = fifo.log_gotbehaviour(from,beh)
    override def log_gotdata(from:String)                   = fifo.log_gotdata(from)
    override def log_sentdata(from:String)                  = fifo.log_sentdata(from)
    */
  }

  private val bPrim: Initiator[CC] = new Initiator[CC](name, logger: Logger) {
    override val endname = bEndName
    val rank = fifo.rank
    val io: IOType = Output
    val behaviour = new org.ect.reo.distengine.colouring.CC(new ColouringTable)
    val end: End[CC] = new End(endname, io, this) {
      // behaviour must be set by now!
      override def toPort(msg: Msg): Unit = {
        msg match {
          case DataStep(dt, _) => logger.log(primname, "SENT DATA: [[ " + dt + " ]]")
          case ReplyData(dt) => logger.log(primname, "SENT DATA: [[ " + dt + " ]]")
          case _ => {}
        }
        super.toPort(msg)
      }
    }
    updateEnds
    val uid = EndUID(name, endname)

    def source(col: Colouring, e: EndUID) = {
      if ((e == uid) & (col hasFlow uid)) {
        fifo.buffer match {
          case Some(d) => new Right(d)
          case None => throw new NoSuchElementException("FIFO1.buffer")
        }
      }
      else new Left(new HashSet[EndUID])
    }

    def updateStep(col: Colouring) = {
      //log_warning(name,"UPDATING STEP ACCORDING TO: "+ct)
      if (col hasFlow bUID) {
        logger.log(name, "INITIATING to the other side!")
        //logger.log_warning(name,"going from empty to full and trying to init a. State: "+getstate(endin))
        behaviour.value = b_ctempty
        aPrim.behaviour.value = a_ctempty
        //a.updateBeh(ctempty)
        //b.updateBeh(ctempty)
        aPrim.initiate()
        //aPrim ! nextInput // BUG??
        //NEXT INPUT SHOULD WAKE UP aPRIM, BUT SOMETIMES IT PROCESSES IT BEFORE
        //the INITIATE PUTS A REQUEST IN THE EARLY REQUESTS QUEUE! EVERYTHING WOULD
        //BE EASIER THE NORMAL QUEUE WOULD BE USED, AND WHILE IN STAGE 2 REQUESTS
        //AND SUSPENSIONS/REQUESTS WOULD BE JUST POSTPONED.

        //fifo.state = aPrim.state
      }
      else logger.log(name, "Behaviour unchanged! Not INITIATING.")
    }

    /** Propagation of the colouring table when: (1) there exists a valid colouring,
      * and (2) data is flowing. */
    def propagate(col: Colouring) = {
      logger.log_debug(name, "about to propagate! using colouring: " + col)
      logger.log_debug(name, "picking one...")
      if (col hasFlow uid) {
        assert(fifo.buffer != None)
        logger.log_debug(name, "Data in the buffer, and we have flow! sending data and ct!")
        end.toPort(DataStep(fifo.buffer.get, col))
      }
      else {
        logger.log_debug(name, "No flow at the end... sending ct!")
        end.toPort(SingleStep(col))
      }
      logger.log_debug(name, "ending step after sending colouring: " + col)
      endStep(endname, col)
    }

    /*
    override def gotAdminMessage(msg:AdminMsg) {
      super.gotAdminMessage(msg)
      fifo.state = state
    }
    override def gotEndMessage(endname:String,msg:CommitMsg) = {
      super.gotEndMessage(endname,msg)
      fifo.state = state
    }

    override def gotDataMessage(endname:EndLID,msg:CommunicateMsg) : Unit = {
      super.gotDataMessage(endname,msg)
      fifo.state = state
    }
    */

    // logging function are imported from fifo abstract primitive
    /*
    override def log(from:String,msg:String)                = fifo.log(from+"-out",msg)
    override def log_debug(from:String,msg:String)          = fifo.log_debug(from,msg)
    override def log_rcv(prim:String,end:String,msg:Msg)    = fifo.log_rcv(prim+"-out",end,msg)
    override def log_toPort(prim:String,end:String,msg:Msg) = fifo.log_toPort(prim+"-out",end,msg)
    override def log_warning(fromend:String,msg:String)     = fifo.log_warning(fromend,msg+" (OUTPUT END)")
    override def log_updateStep(from:String)                = fifo.log_updateStep(from)
    override def log_initiate(from:String,rank:Int)         = fifo.log_initiate(from,rank)
    override def log_propagate(from:String)                 = fifo.log_propagate(from)
    override def log_commit(from:String,table:String,toOutside:Boolean) = fifo.log_commit(from,table,toOutside)
    override def log_gotbehaviour(from:String,beh:String)   = fifo.log_gotbehaviour(from,beh)
    override def log_gotdata(from:String)                   = fifo.log_gotdata(from)
    override def log_sentdata(from:String)                  = fifo.log_sentdata(from)
    */
  }
  
  private val a : End[org.ect.reo.distengine.colouring.CC] = aPrim.end
  private val b : End[org.ect.reo.distengine.colouring.CC] = bPrim.end
  private val aUID = EndUID(name,aEndName)
  private val bUID = EndUID(name,bEndName)
  def status = {aPrim.status +"\n"+bPrim.status}
  def state : PrimState = (aPrim.state,bPrim.state) match {
    case (Suspended(x),_) => Suspended(x)
    case (_,Suspended(x)) => Suspended(x)
    case (WaitingData(x,y,z),_) => WaitingData(x,y,z)
    case (_,WaitingData(x,y,z)) => WaitingData(x,y,z)
    case (Committed(w,x,y,z),_) => Committed(w,x,y,z)
    case (_,Committed(w,x,y,z)) => Committed(w,x,y,z)
    case (Committing(v,w,x,y,z),_) => Committing(v,w,x,y,z)
    case (_,Committing(v,w,x,y,z)) => Committing(v,w,x,y,z)
    case (x,_) => x
  } 


  ends(aEndName) = a
  ends(bEndName) = b
  
  // the ColTable of the FIFO channel
  private val a_ctempty = new ColouringTable
  private val a_col1 = new Colouring
  a_col1(aUID) = Flow
  a_ctempty += a_col1
  private val a_col2 = new Colouring
  a_col2(aUID) = FromIn
  a_ctempty += a_col2

  private val b_ctempty = new ColouringTable
  private val b_col1 = new Colouring
  b_col1(bUID) = ToOut
  b_ctempty += b_col1
  // to remove if flip rule is expanded in nodes
  //private val b_col2 = new Colouring
  //b_col2(bUID) = FromOut
  //b_ctempty += b_col2

  private val a_ctfull = new ColouringTable
  private val a_col3 = new Colouring
  a_col3(aUID) = ToIn
  a_ctfull += a_col3
  // to remove if flip rule is expanded in nodes
  //private val a_col4 = new Colouring
  //a_col4(aUID) = FromIn
  //a_ctfull += a_col4

  private val b_ctfull = new ColouringTable
  private val b_col3 = new Colouring
  b_col3(bUID) = Flow
  b_ctfull += b_col3
  private val b_col4 = new Colouring
  b_col4(bUID) = FromOut
  b_ctfull += b_col4
  
  aPrim.behaviour.value = buffer match {
    case Some(x) => a_ctfull
    case None    => a_ctempty
  }
  bPrim.behaviour.value = buffer match {
    case Some(x) => b_ctfull
    case None    => b_ctempty
  }


  // set as initiator, but will only initiate after releasing suspension
  aPrim.initiate()
  
  
  def source(col:Colouring,e:EndUID) : DataSource = {
    if 		(e == aUID) aPrim.source(col,e)
    else if (e == bUID) bPrim.source(col,e)
    else    throw new NoSuchElementException("end "+e+" from "+name)
    }

  def updateStep(col:Colouring) = { }
  
  //// ABSTRACT PRIMITIVE METHODS!
  
  // ACTOR method 1/2
  override def start() = {
    aPrim.start
    bPrim.start
    super.start
  }
  
  // ACTOR method 2/2
//  def ! (msg : Any) : Unit = msg match {
  def act: Nothing = react {
    case msg => msg match {
	    case msg:Suspend => {
	       aPrim ! msg
	       bPrim ! msg
	     }
	     
	    case msg@(endname:String, _:CommitMsg) =>
	       fwdMsg(endname,msg)
	     
	    case msg@(endname:String, _:CommunicateMsg) =>
	       fwdMsg(endname,msg)
	  
	    case msg:AdminMsg => {
	       aPrim ! msg
	       bPrim ! msg
	       if (msg == Kill()) exit
	    }
	
	    case msg:ReconfigMsg => msg match {
	       case SetPort(otherPrim,from:Utils.EndLID,to:EndUID) =>
	         fwdMsg(to.endname,msg)
	       case _ => {
	         aPrim ! msg
	         bPrim ! msg
	       }
	    }
	     
	    case unexpected: AnyRef =>
	       logger.log_warning(name,"WWe're getting a signal " +
	          unexpected.toString + " of type " + unexpected.getClass)
    }
    act
  }
  
  /** forwards a message to the corresponding primitive */
  def fwdMsg(to:EndLID,msg:Any) = {
    if      (to == aPrim.endname) aPrim ! msg
    else if (to == bPrim.endname) bPrim ! msg
    else logger.log_warning(name,"Message to unkonwn end: "+to+"(ends: "+aEndName+"/"+bEndName+")")
  }
  /** forwards a message to the corresponding primitive */
  def fwdMsg(to:EndUID,msg:Any) = {
    if      (to == aUID) aPrim ! msg
    else if (to == bUID) bPrim ! msg
    else logger.log_warning(name,"Message to unkonwn end: "+to+"(ends: "+aUID+"/"+bUID+")")
  }
}