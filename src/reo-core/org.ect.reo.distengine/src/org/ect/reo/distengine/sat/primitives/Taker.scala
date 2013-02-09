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
package org.ect.reo.distengine.sat.primitives;

//import org.ect.reo.distengine.colouring._;
import org.ect.reo.distengine.common._;
import org.ect.reo.distengine.common.Utils._;

import org.ect.reo.distengine.redrum.{Primitive, End}
import org.ect.reo.distengine.redrum.genericprim.Initiator
import collection.mutable.HashMap
import collection.mutable.HashSet
import collection.immutable.Set
import org.ect.reo.distengine.sat.{CNF, ConstraintSol, ConstraintList, SAT}


class Taker(name:PrimID,logger:Logger, val rank:Int, var counter:Int)
extends Initiator[SAT](name,logger) {
  val io : IOType = Input
  val behaviour = new SAT(new ConstraintList(Nil))
  val end : End[SAT] = new End(endname,io,this)
  // behaviour must be set by now!
  updateEnds
  val uid = EndUID(name,endname)

  val resultname = "result-"+endname
  val result : AbstractEnd = new End(resultname,Input,this)
  var resultsol = new ConstraintSol(Set[EndUID]())
  
  // the constraints of the taker with flow
  val a = uid; val asr = EndUID(name,endname+"$sr")
  val flow = new ConstraintList(List(new CNF(
		  	 List(List(1,-2)), List(a,asr)))) 
//		  	 List(List(1)), List(a,asr)))) 

  // the constraints of the taker with no flow
  val noFlow = new ConstraintList(List(new CNF(
		  	   List(List(-1),List(2)) , List(a,asr))))

  if (counter==0)
    behaviour.value = noFlow
  else {
    behaviour.value = flow
    initiate()
  }

  //trace("Constraints set: "+behaviour.value)

  def source(ct:ConstraintSol,e:EndUID) : DataSource = {
    val res = new HashSet[EndUID]
    if (e == result.uid) res += e
    return new Left(res)
  }
  
  def updateStep(c:ConstraintSol) = { // c is always the empty ct!!
    if (c hasFlow uid) {
      if (counter > 0) {
        counter -= 1
        if (counter == 0) {
          behaviour.value = noFlow
          logger.log(name,"Cannot accept NO MORE data!")
        }
        else {
          logger.log(name,"INITIATING  AGAIN! - I can still recv "+counter+" values")
          initiate
        }
      }
      else if (counter == -1) {
          logger.log(name,"INITIATING  AGAIN! - always accepts data")
          initiate
      }
    }
    else if (c.flowset exists (x => !x.endname.endsWith("$sk")
                                 && !x.endname.endsWith("$sr"))) {
      if (counter > 0 || counter == -1) {
    	logger.log_debug(name,"STILL CAN ACCEPT. Connector may have changed.")
        logger.log(name,"INITIATING  AGAIN! - connector may have changed ("+counter+")")
        initiate
      }
      else logger.log(name,"NOT initiating again - no data.")
    }
    else logger.log(name,"NOT initiating - connector unchanged!")
  }
  
  /** Propagation of the colouring table when: (1) there exists a valid colouring,
   *  and (2) data is flowing. */
  def propagate(c:ConstraintSol) = {
    if (c hasFlow uid) {
        end.toPort(AskData(c))
        val toReply = new HashSet[EndLID]
        toReply += resultname
        ends(resultname) = result
        resultsol = c
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
      case DataStep(dt:Any,s:ConstraintSol) => {
        logger.log_rcv(name,endname,msg)
        logger.log_gotdata(name)
        logger.log(name,"GOT DATA [[ "+dt+" ]]")
        ends -= resultname
        endStep(endname,s)
        nextInput
      }
      case ReplyData(dt:Any) => {
        logger.log_rcv(name,endname,msg)
        logger.log_gotdata(name)
        logger.log(name,"GOT DATA [[ "+dt+" ]]")
        ends -= resultname
        endStep(endname,resultsol)
        nextInput
      }
      case x => {
        super.gotCommunicateMsg(endname,msg)
      }
    }
  }

}
