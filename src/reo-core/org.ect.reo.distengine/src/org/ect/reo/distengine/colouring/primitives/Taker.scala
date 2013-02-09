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
import org.ect.reo.distengine.redrum.genericprim.Initiator
import collection.mutable.HashMap
import collection.mutable.HashSet
import collection.Set
import org.ect.reo.distengine.colouring._


class Taker(name:PrimID,logger:Logger, val rank:Int, var counter:Int)
extends Initiator[CC](name,logger) {
  val io : IOType = Input
  val behaviour : CC = new CC(new ColouringTable)
  val end : End[CC] = new End(endname,io,this) // behaviour must be set by now!
  updateEnds
  val uid = EndUID(name,endname)

  //val ends = new HashMap[EndLID,End]
  //ends(endname) = end  

  val resultname = "result-"+endname
  val result : AbstractEnd = new End(resultname,Input,this)
//                      {otherEnd = EndUID("HAAAA","HAAAAAAAA")}
  var resultcol = new Colouring //ColouringTable.zero
  
  // the ColTable of the taker
  val flow = new ColouringTable
  val col1 = new Colouring
  col1(uid) = Flow
  flow += col1
  val col2 = new Colouring
  col2(uid) = FromIn
  flow += col2
  
  // the ColTable with no flow 
  val noFlow = new ColouringTable
  val col3 = new Colouring
  col3(uid) = ToIn
  noFlow += col3
  // use only if FLIP RULE NOT APPLIED in nodes
  //val col4 = new Colouring
  //col4(uid) = FromIn
  //noFlow += col4
  
  if (counter==0)
    behaviour.value = noFlow
  else {
    behaviour.value = flow
    initiate()
  }

  //trace("Colouring table set: "+behaviour.value)

  def source(ct:Colouring,e:EndUID) : DataSource = {
    val res = new HashSet[EndUID]
    if (e == result.uid) res += e
    return new Left(res)
  }
  
  def updateStep(c:Colouring) = { // c is always the empty ct!!
    if (c hasFlow uid) {
      if (counter > 0) {
        counter -= 1
        if (counter == 0) {
          behaviour.value = noFlow
          logger.log(name,"Cannot accept NO MORE data!")
        }
        else {
          logger.log(name,"INITIATING AGAIN!")
          initiate
        }
      }
      else if (counter == -1) {
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
        logger.log_rcv(name,endname,msg)
        logger.log_gotdata(name)
        logger.log(name,"GOT DATA: [[ "+dt+" ]]")
        ends -= resultname
        endStep(endname,s)
        nextInput
      }
      case ReplyData(dt:Any) => {
        logger.log_rcv(name,endname,msg)
        logger.log_gotdata(name)
        logger.log(name,"GOT DATA: [[ "+dt+" ]]")
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
