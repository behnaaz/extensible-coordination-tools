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
package org.ect.reo.distengine.redrum;

import org.ect.reo.distengine.common._
import org.ect.reo.distengine.common.Utils._

import scala.actors.Actor
import org.ect.reo.distengine.common.PreparingNewScala._
import collection.mutable.HashMap
import collection.mutable.Set

  
/**
 * 'End' is an AbstractEnd with no initiating capabilities
 */
class End[BehaviourModel <: AbstractBehaviour]
(val name:EndLID,var iotype:IOType,protected val primitive:Primitive[BehaviourModel])
extends AbstractEnd(primitive.logger)
{

    //val endid : EndID = { (primitive.name, name) }
    // THESE WERE PRIVATE
    val primname = primitive.name
    
    val behaviour = primitive.behaviour // will create a COPY of beh, not link to the same beh.value
    type Beh = behaviour.Beh
    
    
   /** Returns string with 'name', 'IOtype', and if it is
    * connected to a node. */
    def status : String = {
      "'"+name+ //"' - "+state+//.deepToString+ // comment deepToString to clean status
      " | "+iotype+" | "+
      (if (!hasPort) "Disconnected" else "Connected")
    }

    def reset {
      toPort (Reset(/*primitive.name*/))
    }

    def kill {
      toPort(Kill())
    }
    
    /** Sends a message to port (should be suspended) to remove the corresponding end. */
    def removeEnd() {
      toPort (RemoveEnd(uid))
    }

    // logging function are imported from primitive
    /*
    def log(from:String,msg:String)                  = primitive.log(from,msg)
    def log_debug(from:String,msg:String)            = primitive.log_debug(from,msg)
    def log_rcv(prim:String,end:String,msg:Msg)      = primitive.log_rcv(prim,end,msg)
    def log_toPort(prim:String,end:String,msg:Msg)   = primitive.log_toPort(prim,end,msg)
    def log_warning(fromend:String,msg:String)       = primitive.log_warning(fromend,msg)
    def log_updateStep(from:String)                  = primitive.log_updateStep(from)
    def log_initiate(from:String,rank:Int)           = primitive.log_initiate(from,rank)
    def log_propagate(from:String)                   = primitive.log_propagate(from)
    def log_commit(from:String,table:String,toOutside:Boolean) = primitive.log_commit(from,table,toOutside)
    def log_gotbehaviour(from:String,beh:String)     = primitive.log_gotbehaviour(from,beh)
    def log_gotdata(from:String)                     = primitive.log_gotdata(from)
    def log_sentdata(from:String)                    = primitive.log_sentdata(from)
    */
}
