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
import scala.collection.Set
import org.ect.reo.distengine.sat.{CNF, ConstraintSol, ConstraintList, SAT}

trait TaggedData {
  var tag: String
  var curdata: List[Any]
  
  curdata = curdata.map(tag+"-"+_)
}

class Writer(name:PrimID,logger:Logger,i:Int,data:List[Any])
extends Initiator[SAT](name,logger) {
  val rank = i
  val io : IOType = Output
  val behaviour = new SAT(new ConstraintList(Nil))
  // behaviour must be set by now!
  val end : End[SAT] = new End(endname,io,this) {
    override def toPort(msg:Msg) : Unit = {
      msg match {
        case DataStep(dt,_) =>
          logger.log_sentdata(name)
          logger.log(name,"SENT DATA [[ "+dt+" ]]")
        case ReplyData(dt) =>
          logger.log_sentdata(name)
          logger.log(name,"SENT DATA [[ "+dt+" ]]")
        case _ => {}
      }
      super.toPort(msg)
    }
  }

  updateEnds
  val uid = EndUID(name,endname)
  // val ends = new HashMap[EndLID,End]
  // ends(endname) = end
  
  var curdata = data
  
  // the constraints of the writer with flow
  val a = uid; val ask = EndUID(name,endname+"$sk")
  val flow = new ConstraintList(List(new CNF(
		  	 List(List(1,-2)), List(a,ask)))) 

  // the constraints of the writer with no flow
  val noFlow = new ConstraintList(List(new CNF(
		  	   List(List(-1),List(2)) , List(a,ask))))
  
  if (data == Nil) behaviour.value = noFlow
  else {
    behaviour.value = flow
    initiate()
  }
  //logger.log_debug(name,"Colouring table set: "+behaviour.value)
  
  
  def source(c:ConstraintSol,e:EndUID) : DataSource = {
    if ((e == uid) && (c hasFlow e)) return new Right(curdata.head)
    return new Left(new HashSet[EndUID])
  }
  
  def updateStep(c:ConstraintSol) = {
    if (c hasFlow uid) {
      if (curdata.size == 1) {
        logger.log(name,"NOT initiating again - last token sent")
        logger.log_debug(name,"sending last data : "+curdata.head)
        curdata = curdata.tail
        behaviour.value = noFlow
      }
      else if (curdata.size > 1) {
        logger.log_debug(name,"STILL HAVE DATA. Keeping beh. Data sent + left: "+curdata)
        logger.log(name,"INITIATING AGAIN! (still have data)")
        curdata = curdata.tail
        initiate
      }
      else logger.log(name,"NOT initating again - no data.")
    }
    else if (c.flowset exists (x => !x.endname.endsWith("$sk")
                                 && !x.endname.endsWith("$sr"))) {
      if (curdata.size > 0) {
    	logger.log(name,"STILL HAVE DATA. Connector may have changed. My data: "+curdata)
        logger.log(name,"INITIATING AGAIN! (connector may have changed)")
        initiate
      }
      else logger.log(name,"NOT initating again - no data.")
    }
    else logger.log(name,"NOT initiating - connector unchanged!")
  }
  
  /** Propagation of the solution when: (1) there exists a valid colouring,
   *  and (2) data is flowing. */
  def propagate(c:ConstraintSol) = {
    logger.log_debug(name,"about to propagate! using atomic step: "+c)
    logger.log_debug(name,"choosing the atomic step...")
    if (c hasFlow uid) {
      assert (curdata != Nil)
      logger.log_debug(name,"Data in the buffer, and we have flow! sending data and as!")
      end.toPort(DataStep(curdata.head,c))
    }
    else {
      logger.log_debug(name,"No flow at the end... sending atomic step!")
      end.toPort(SingleStep(c))
    }
    logger.log_debug(name,"atomic step sent: "+c)
    logger.log_debug(name,"propagated... now ending step!")
    endStep(endname,c)
  }

}
