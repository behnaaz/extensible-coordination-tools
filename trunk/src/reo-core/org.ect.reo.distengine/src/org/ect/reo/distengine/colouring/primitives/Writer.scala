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
import scala.collection.Set
import org.ect.reo.distengine.colouring._

trait TaggedData {
  var tag: String
  var curdata: List[Any]
  
  curdata = curdata.map(tag+"-"+_)
}

class Writer(name:PrimID,logger:Logger,i:Int,data:List[Any])  extends Initiator[CC](name,logger) {
  val rank = i
  val io : IOType = Output
  val behaviour : CC = new CC(new ColouringTable)
  val end : End[CC] = new End(endname,io,this) { // behaviour must be set by now!
    override def toPort(msg:Msg) : Unit = {
      msg match {
        case DataStep(dt,_) => logger.log(name,"SENT DATA: [[ "+dt+" ]]")
        case ReplyData(dt) =>  logger.log(name,"SENT DATA: [[ "+dt+" ]]")
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
  
  // the flow ColTable
  val flowct = new ColouringTable
  private val col1 = new Colouring
  col1(uid) = Flow
  flowct += col1
  private val col2 = new Colouring
  col2(uid) = FromOut
  flowct += col2
  // the ColTable with no flow
  val noflowct = new ColouringTable
  private val col3 = new Colouring
  col3(uid) = ToOut
  noflowct += col3
  // use only if FLIP RULE NOT APPLIED in nodes
  //private val col4 = new Colouring
  //col4(uid) = FromOut
  //noflowct += col4
  
  if (data == Nil) behaviour.value = noflowct
  else {
    behaviour.value = flowct
    initiate()
  }
  //logger.log_debug(name,"Colouring table set: "+behaviour.value)
  
  //behaviour.ns = {
  //  x:Colouring =>
  //    if (data.size == 1) noflowct
  //    else behaviour.value
  //}
  
  def source(ct:Colouring,e:EndUID) : DataSource = {
    if ((e == uid) && (ct hasFlow e)) return new Right(curdata.head)
    return new Left(new HashSet[EndUID])
  }
  
  def updateStep(c:Colouring) = {
    if (c hasFlow uid) {
      if (curdata.size == 1) {
        logger.log(name,"updating to noflow ct! Not INITIATING again - last token sent")
        logger.log_debug(name,"sending last data : "+curdata.head)
        curdata = curdata.tail
        behaviour.value = noflowct
      }
      else if (curdata.size > 1) {
        logger.log_debug(name,"STILL HAVE DATA. Keeping CT. Data sent + left: "+curdata)
        logger.log(name,"INITIATING AGAIN!")
        curdata = curdata.tail
        initiate
      }
      else logger.log(name,"updating to noflow ct! Not INITIATING again - no data")
    }
    else logger.log(name,"Behaviour unchanged")

  }
  
  /** Propagation of the colouring table when: (1) there exists a valid colouring,
   *  and (2) data is flowing. */
  def propagate(c:Colouring) = {
    logger.log_debug(name,"about to propagate! using colouring: "+c)
    //val ct = new ColouringTable
    logger.log_debug(name,"picking one...")
    //c.pickone match {
    //val ct = c.getSingleton
    if (c hasFlow uid) {
      assert (curdata != Nil)
      logger.log_debug(name,"Data in the buffer, and we have flow! sending data and ct!")
      end.toPort(DataStep(curdata.head,c))
    }
    else {
      logger.log_debug(name,"No flow at the end... sending ct!")
      end.toPort(SingleStep(c))
    }
    logger.log_debug(name,"colouring sended: "+c)
    logger.log_debug(name,"propagated... now ending step!")
    endStep(endname,c)
  }

}
