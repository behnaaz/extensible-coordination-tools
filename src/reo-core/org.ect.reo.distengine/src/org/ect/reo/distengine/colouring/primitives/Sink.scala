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
import org.ect.reo.distengine.colouring._
import collection.mutable.HashMap
import collection.mutable.HashSet
import scala.collection.Set


class Sink(name:PrimID,logger:Logger,val rank:Int, becameEmpty: => Unit)
extends Initiator[CC](name,logger) {
  val io : IOType = Output
  val behaviour : CC = new CC(new ColouringTable)
  val end : End[CC] = new End(endname,io,this) // behaviour must be set by now!
  updateEnds
  val uid = EndUID(name,endname)
  // val ends = new HashMap[EndLID,End]
  // ends(endname) = end
  private var data : List[Any]= Nil

  // Probably not necessary...
  assume(state.isSuspended)
  //state = Suspended(scala.collection.immutable.HashSet(name))

  override def status : String = {
    super.status + "\n    - "+data.length+" remaining tokens."
  }
  
  /** Use this method to send a new data item through this port. */
  def addDatum(a:Any) = addData(List(a))

  /** Use this method to send a sequence of data items through this port. */
  def addData(as:Iterable[Any]) = {
    println("Adding new data to be send to the port! - " +as)
    if (data.isEmpty) {
      data = data ++ as
      behaviour.value = flowct // if noflowct was sent, then value will not be asked.
      //println("suspending and releasing core primitive, to reactivate initiator.")
      //if (!state.isSuspended) {
      //  this ! Suspend(name)
      //  this ! Release(name)
      //}
      initiate
    }
    else data = data ++ as
  }
  
  /** Checks if there is data waiting to be sent. */
  def hasData = data.isEmpty
  
  /** Use this method to remove all the content from the send buffer. */
  def flush = {
    if (!data.isEmpty) {
      assert(state==Idle || state.isSuspended || state==Committed,
          "Output end cannot be waiting to send data")
      if (state == Idle || state.isSuspended) {
        data = Nil
        behaviour.value = noflowct
      }
      else if (state == Committed) {
        data = List(data.head)
      }
    }
  }
  /* Use this method to suspend this primitive (lock name is its own name). */
  def suspend = this ! Suspend(name)
  /* Use this method to release this primitive from suspension (lock name is its own name). */
  def release = this ! Release(name)
  
  // the flow ColTable
  val flowct = new ColouringTable
  val col1 = new Colouring
  col1(uid) = Flow
  flowct += col1
  val col2 = new Colouring
  col2(uid) = FromOut
  flowct += col2
  // the noflow ColTable
  val noflowct = new ColouringTable
  val col3 = new Colouring
  col3(uid) = ToOut
  noflowct += col3
  
  if (data == Nil) behaviour.value = noflowct
  else behaviour.value = flowct
  //logger.log_debug(name,"Colouring table set: "+behaviour.value)
  
  //behaviour.ns = {
  //  x:Colouring =>
  //    if (data.size == 1) noflowct
  //    else behaviour.value
  //}
  
  def source(ct:Colouring,e:EndUID) : DataSource = {
    if ((e == uid) && (ct hasFlow e)) return new Right(data.head)
    return new Left(new HashSet[EndUID])
  }
  
  def updateStep(c:Colouring) = {
    if (c hasFlow uid) {
      if (data.size == 1) {
        logger.log(name,"updating to noflow ct! Not INITIATING again - last token sent")
        logger.log_debug(name,"sending last data : "+data.head)
        data = data.tail
        behaviour.value = noflowct
        becameEmpty
      }
      else if (data.size > 1) {
        logger.log_debug(name,"STILL HAVE DATA. Keeping CT. Data sent + left: "+data)
        logger.log(name,"INITIATING AGAIN!")
        data = data.tail
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
      assert (data != Nil)
      logger.log_debug(name,"Data in the buffer, and we have flow! sending data and ct!")
      end.toPort(DataStep(data.head,c))
    }
    else {
      logger.log_debug(name,"No flow at the end... sending ct!")
      end.toPort(SingleStep(c))
    }
    logger.log_debug(name,"colouring sent: "+c)
    logger.log_debug(name,"propagated... now ending step!")
    endStep(endname,c)
  }

}
