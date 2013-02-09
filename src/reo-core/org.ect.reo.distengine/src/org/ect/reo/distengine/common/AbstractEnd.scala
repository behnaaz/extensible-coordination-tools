/*
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *
 * End of a Reo primitive.
 *
 * @author Jose Proenca
 */

package org.ect.reo.distengine.common

import Utils.{EndLID,PrimID,Port}
//import scala.actors.AbstractActor
import org.ect.reo.distengine.common.PreparingNewScala._

abstract class AbstractEnd(logger:Logger)
{
  val name : EndLID
  val primname : PrimID
  protected var optPortPair : Option[(Port,EndUID)] = None //AbstractPrimitive[AbstractBehaviour] x otherEnd
  //def toPort(msg:CommitMsg) : Unit
  //def toPort(msg:CommunicateMsg) : Unit
  var iotype : Utils.IOType
  
  def uid : EndUID = EndUID(primname,name)

  def hasPort = optPort.isDefined
  def setPort(newport:Port,other:EndUID) {
    optPortPair = Some((newport,other))
    //port = newport
    //otherEnd = other
  }
  def resetPort = optPortPair = None
  def optOtherEnd = for ((_,otherEnd) <- optPortPair) yield otherEnd
  def optPort = for ((port,_) <- optPortPair) yield port

  /** Send to port when it exists. */
  def toPort(msg:Msg) : Unit = { // to typecheck send type
    for ((port,otherEnd) <- optPortPair) {
      logger.log_toPort(primname,name,otherEnd,msg)
      //print("To port "+otherEnd.endname+" with msg "+msg+"... ")
      //print(".")
      try {
      msg match {
        case _:CommitMsg      => { 
          port !
            ((otherEnd.
                endname,
              msg)) }
        case _:CommunicateMsg => { 
          port !
            ((otherEnd.
                endname,
              msg)) }
        case _                => { port ! msg }
        // trace("sending non-evol msg - "+msg+" :: "+msg.getClass)
      }
      }
      catch {
        case e:Exception => {
          println("when trying to send message "+msg.toString+" of type "+msg.getClass)
          println("to end "+otherEnd.endname+" of type "+otherEnd.endname.getClass)
          println("using port with type "+port.getClass)
          println("EXCEPTION found by "+name+"! - "+e.printStackTrace)
//            print("error ")
        }
      }
      //println(" . . . done")
    }
  }

  
  // Admin
  def status : String
  def reset : Unit
  def kill : Unit
  def removeEnd : Unit
  
  // Auxiliary
  override def toString = name
  def deepToString = name
  override def hashCode : Int = name.hashCode
}
