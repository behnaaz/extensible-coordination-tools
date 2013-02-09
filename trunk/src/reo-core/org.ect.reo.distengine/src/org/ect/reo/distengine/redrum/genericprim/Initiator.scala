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
package org.ect.reo.distengine.redrum.genericprim;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.redrum.{End, IListenerEndOfStep, Primitive}
import collection.mutable.{ Map => MutMap }
import collection.mutable.{ Set => MutSet }


/**
 * An initiator is a special primitive with a single end,
 * that sends an initial request.
 * Abstract class, that requires the definition of:
 * end:SimpleEnd[Beh]; io:IOType; rank:Int.
 * Also requires, for abstract class 'Primitive':
 * behaviour:Beh. Run <code>updateEnds</code> after defining
 * <code>end</code>, to updeate value  <code>ends</code> from 
 * 'Primitive'.
 */ 
abstract class Initiator[Behaviour <: AbstractBehaviour](val name:PrimID,logger:Logger)
extends Primitive[Behaviour](logger)
{
    val rank:Int
    val io:IOType
    //val name = "Init_"+rank
    //val behaviour = new TestCC(ColTable("<Init("+io+")-"+rank+">"))
    val endname = name//"inend_"+rank
    val end:End[Behaviour]
    val dummyEndName = "%dummy-"+name
    val ends : Ends = MutMap[EndLID,AbstractEnd]()
    
    def updateEnds : Unit = {
      type InitBeh = Beh
      //val ends = new HashMap[EndLID,End]
      val dummyEnd = new End[Behaviour](dummyEndName,end.iotype.dual,this) {
        override def toPort(msg:Msg) = {
          msg match {
            case Table(beh:InitBeh) => becameLeader(beh)
            case _ => {}
          }
        } 
      }
      ends.clear
      ends(endname) = end
      ends(dummyEndName) = dummyEnd
      logger.log(name,"Updated ends with beh: "+behaviour.value)
    }
    
    def becameLeader(beh:Beh) {
      logger.log_gotbehaviour(name,"--"+beh)
      //logger.log(name,"rank: "+newrank) //+", behaviour:\n"+newbeh)
      val thisstep = beh.pickone
      logger.log_propagate(endname)
      logger.log(name,"PICKED AS "+thisstep)
      propagate(thisstep getOrElse behaviour.nostep)
    }
    
    /**
     * Sends the behaviour, maybe together with data or just requesting the result,
     * and updates the state.
     * To be defined by the primitive.
     */
    def propagate(rep:Step) : Unit
    var requestSent = false

    var listeners: scala.collection.immutable.Set[IListenerEndOfStep] = Set()
   
    override def endStep(endname:EndLID,rep:Step) = {
      for (lst <- listeners)
        lst.endOfStep(rep hasFlow end.uid)
      super.endStep(endname,rep)
    }	
   
    def addListener(lst: IListenerEndOfStep) {
      listeners = listeners + lst
    }
    def rmListener(lst: IListenerEndOfStep) {
      listeners = listeners - lst
    }	

    
    
    /**
     * Start the algorithm by sending a request to the port.
     */
    def initiate() = {
     //assert(state == Idle, "Trying to initiate the algorithm without being Idle! - "+name)
     logger.log_initiate(name,rank)
     //logger.log(name,"Asking to initiate with rank "+rank+" as soon as nextInput is called (and after previous early requests)")
     this ! (dummyEndName,Request(rank))
   }
}
