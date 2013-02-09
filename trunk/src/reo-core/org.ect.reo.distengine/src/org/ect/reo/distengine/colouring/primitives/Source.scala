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
import org.ect.reo.distengine.common._;
import org.ect.reo.distengine.common.Utils._;

import org.ect.reo.distengine.redrum.{Primitive, End}
import org.ect.reo.distengine.colouring._
import org.ect.reo.distengine.redrum.genericprim.Initiator
import collection.mutable.HashMap
import collection.mutable.HashSet
import collection.mutable.Queue
import scala.collection.Set


class Source(name:PrimID,logger:Logger,val rank:Int)
extends Initiator[CC](name,logger) {
  var counter:Int = 0
  var bag:Queue[Any] = new Queue
  val io : IOType = Input
  val behaviour : CC = new CC(new ColouringTable)
  val end : End[CC] = new End(endname,io,this) // behaviour must be set by now!
  updateEnds
  val uid = EndUID(name,endname)
    
  assume(state.isSuspended)
  //state = Suspended(scala.collection.immutable.HashSet(name))

  override def status : String = {
    super.status + "\n    - "+counter+" pending waits."
  }


  //val ends = new HashMap[EndLID,End]
  //ends(endname) = end  
  
  /** Use this method to request the reception of the next 'n' data elements. */
  def getMore(n:Int) = {
    assert(n > 0, name+ ": Value has to be greater than 0")
    if (counter == 0) {
      behaviour.value = flowCT
      initiate
    }    
    counter += n
    //if (counter==n) {
    //  // try to initiate again when possible
    //  this ! Suspend(name)
    //  this ! Release(name)
    //}
  }
  
  /** Use this method to get a copy of the received data. */
  def getBag : Queue[Any] = bag.clone
  
  /** Use this method to delete the current content of the received data. */
  def emptyBag() {
    bag = new Queue
  }
  
  /* Use this method to suspend this primitive (lock name is its own name). */
  def suspend = this ! Suspend(name)
  /* Use this method to release this primitive from suspension (lock name is its own name). */
  def release = this ! Release(name)

  
  

  val resultname = "result-"+endname
  val result = new End(resultname,Input,this)
//                      {otherEnd = EndUID("value never used")}
  var resultcol : Colouring = new Colouring //ColouringTable.zero
  
  // the ColTable with flow
  val col1 = new Colouring
  val flowCT = new ColouringTable
  col1(uid) = Flow
  flowCT += col1
  val col2 = new Colouring
  col2(uid) = FromIn
  flowCT += col2
  
  // the ColTable with no flow
  val noFlowCT = new ColouringTable
  val col3 = new Colouring
  col3(uid) = ToIn
  noFlowCT += col3
  
  if (counter==0) behaviour.value = noFlowCT
  else 			  behaviour.value = flowCT
  
  //trace("Colouring table set: "+behaviour.value)

  def source(ct:Colouring,e:EndUID) : DataSource = {
    val res = new HashSet[EndUID]
    if (e == result.uid) res += e
    return new Left(res)
  }
  
  def updateStep(c:Colouring) = { // c is always the empty ct!!
    if ((c hasFlow uid) && (counter > 0)) {
      counter -= 1
      if (counter == 0) {
        behaviour.value = noFlowCT
        logger.log(name,"Cannot accept NO MORE data!")
      }
      else {
        logger.log(name,"INITIATING AGAIN!")
        initiate
      }
    }
  }
  
  /** Propagation of the colouring table when: (1) there exists a valid colouring,
   *  and (2) data is flowing. */
  def propagate(c:Colouring) = {
    //val ct = c.getSingleton
    if (c hasFlow uid) {
        end.toPort(AskData(c))
        val toReply = new HashSet[EndLID]
        toReply += resultname
        ends(resultname) = result
        resultcol = c
        state = WaitingData(new HashSet,toReply,c)
      }
      else {
        end.toPort(SingleStep(c))
        endStep(endname,c)
      }
  }

  override def gotCommunicateMsg(endname:EndLID,msg:CommunicateMsg) : Nothing = {
    //trace("Taker received some data message of type "+msg.getClass)
    msg match {
      case DataStep(dt:Any,s:Colouring) => {
        //logger.log(name,msg)
        logger.log_gotdata(name)
        logger.log(name,"DATA: "+dt)
        bag += dt
        ends -= resultname
        endStep(endname,s)
        nextInput
      }
      case ReplyData(dt:Any) => {
        //logger.log(name,msg)
        logger.log_gotdata(name)
        logger.log(name,"DATA: "+dt)
        bag += dt
        ends -= resultname
        endStep(endname,resultcol)
        nextInput
      }
      case x => {
        super.gotCommunicateMsg(endname,msg)
      }
    }
  }

}
