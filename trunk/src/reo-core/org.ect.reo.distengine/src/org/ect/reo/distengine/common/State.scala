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
package org.ect.reo.distengine.common;

import collection.mutable.{Set => MutSet}
import collection.mutable.{Map => MutMap}
import Utils.EndLID

//////////////////////////////////
//// STATES FOR THE ALGORITHM ////
//////////////////////////////////
/**
* Representation of possible states of a primitive or end.
* Some of the states only hold for primitives or for ends.
*/
abstract class State {
  def deepToString : String
  def isSuspended = false
}

sealed trait PrimState extends State
//sealed trait EndState  extends State


////////////////////////////////
///////// PRIM STATES //////////
////////////////////////////////

case object Idle extends PrimState {
  override def toString = "Idle"
  def deepToString = "Idle"
}

case class Committing[Beh <: BehaviourRep[StepRep]]
(rank:Int,root:EndLID, children:MutSet[EndLID],pending:MutMap[EndLID,Int],childBeh:Beh)
extends PrimState {
  override def toString = "Committing("+rank+"/"+root+"/"+children+"/"+pending+")"
  def deepToString = "Committing("+rank+"/"+root+"/"+children+"/"+pending+")"
}

case class Committed[Beh <: BehaviourRep[StepRep]]
(rank:Int, root:EndLID, children:scala.collection.Set[EndLID],resBeh:Beh)
extends PrimState {
  override def toString = "Committed("+rank+"/"+root+"/"+children+")"
  def deepToString = "Committed("+rank+"/"+root+"/"+children+")"
}

case class WaitingData[Rep <: StepRep](toSend:MutSet[EndLID],toReply:MutSet[EndLID],step:Rep)
extends PrimState {
  override  def toString = {
    "WaitingData to send to "+toSend.mkString ("[",",","]")+
    " and to reply to "+toReply.mkString ("[",",","]")}

  def deepToString = {
    "WaitingData from "+ (if (toSend.isEmpty) "no ends!" else "some ends.")
  }
}

//case class WaitingDataR[Rep <: StepRep](ends:mutSet[EndLID],step:Rep) 
//extends PrimState {
//  override  def toString = { "WaitingData from "+ends.mkString ("[",",","]") }
//  def deepToString = {
//    "WaitingData from "+ (if (ends.isEmpty) "no ends!" else "some ends.")
//  }
//}

case class Suspended(suspended:Set[String])
extends PrimState {
  override def toString = "Suspended("+suspended.mkString(",")+")"
  def deepToString = "Suspended("+suspended.mkString(",")+")"
  override def isSuspended = true
}



////////////////////////////////
////////// END STATES //////////
////////////////////////////////

/*
case class FwdP[Beh <: BehaviourRep[StepRep]]
(rank:Int, ends:Utils.RequestEnds, childBeh:Beh)
extends EndState {
  override  def toString = {
   "FwdP( "+rank+", "+ ends+")"//, "+t.deepToString+" )"
  }
  def deepToString = {
    "FwdP("+rank+"): waiting_for="+
    (if (ends.isEmpty) "Empty" else ends.mkString("",",",""))+
    " / table="+
    childBeh.deepToString//(if (t.isEmpty) "Empty" else t.deepMkString("",",",""))
  }
}
case class Fwd(rank:Int, parent:AbstractEnd, lastSent:Int)
extends EndState {
  override  def toString = {
    "Fwd( "+rank+"("+lastSent+"), "+parent+" )"
  }
  def deepToString = {
    "Fwd("+rank+")("+lastSent+"): parent="+parent 
  }
}

case class Bwd[Beh <: BehaviourRep[StepRep]]
(rank:Int, childBeh:Beh)
extends EndState {
  override  def toString = { "Bwd("+rank+") -- "+childBeh }

  def deepToString = {
    "Bwd("+rank+"): table="+ childBeh.deepToString
  }
}
*/