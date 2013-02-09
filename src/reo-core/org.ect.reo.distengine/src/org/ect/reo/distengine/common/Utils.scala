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
package org.ect.reo.distengine.common

//import org.ect.reo.distengine.common.End
import scala.actors.Actor
import collection.mutable.Map
import collection.mutable.Set
import java.lang.System.getProperty


/**
* Definition of some type aliases to make the code easier to read.
*/
final object Utils {

  type PrimID = String                       //  primitivename
  type EndLID = String                       //  endname
  type Ends = Map[EndLID,AbstractEnd]    //  name => actor
  type EndFlow = Map[EndUID,IOType]      //  enduid => iotype 
  type Engines = Map[String,Actor]       //  name => actor
  type RequestEnds = Set[EndLID]             //  endnames
  type DataSource = Either[scala.collection.Set[EndUID],Any]  //  EndsWithData + SomeData
  type Port = scala.actors.AbstractActor //{def ! (msg:Any) : Unit}

  // IO Type
  sealed abstract class IOType      { def dual : IOType }
  case object Input extends IOType  { def dual = Output }
  case object Output extends IOType { def dual = Input  }

  /*
  val NORMAL  = getProperty("normal")  ne null // not in use (supposed to remove optimizations such as pending requests)
  val RUNONCE = getProperty("runonce") ne null // initiators send kill after getting behaviour
  val DEBUG   = getProperty("debug")   ne null // in runreograph, check status of system 2 seconds after execution
  */
  def resizeStr(n:Int,str:String) : String = {
    assume (n>3)
    val diff = n-str.size
    if (diff >= 0) str+(List.make(diff," ")./:("")(_+_))
    else           str.take(n-4) + "..." + str.last
  }
}


/**
* Temporary constructions for better compatibility when moving from scala 2.7.1 to 2.7.2.
*/
final object PreparingNewScala {
  // type AbstractActor = Actor
  type AbstractActor = scala.actors.AbstractActor
}

////////////////
// EXCEPTIONS //
////////////////
class EngineException extends Throwable
class EndAlreadyExistsException     extends EngineException
class EndDoesNotExistException      extends EngineException
class EndNotIdleException           extends EngineException
class EngineDoesNotExistException   extends EngineException
class EngineAlreadyExistsException  extends EngineException


