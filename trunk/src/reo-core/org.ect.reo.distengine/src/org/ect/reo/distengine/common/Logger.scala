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

import Utils._
import collection.mutable.Queue
import collection.mutable.{ Map => MutMap }

/**
 * A single instance of a logger is used by all primitives inside an engine.
 * It stores information about the protocol, and about provides two ways to access it:
 * by get methods for the full history, or by update methods for live monitoring.
 */
abstract class Logger {

  // storing
  def log(from:String,msg:String) : Unit
  def log_debug(from:String,msg:String) : Unit
  def log_rcv(prim:PrimID,end:EndLID,msg:Msg) : Unit
  def log_toPort(prim:PrimID,end:EndLID,to:EndUID,msg:Msg) : Unit
  def log_warning(fromend:String,msg:String) : Unit
  def log_updateStep(from:String) : Unit
  def log_initiate(from:String,rank:Int) : Unit
  def log_propagate(from:String) : Unit
  def log_commit(fromend:String,table:String,toOutside:Boolean) : Unit
  def log_gotbehaviour(from:String,beh:String) : Unit
  def log_gotdata(from:String) : Unit
  def log_sentdata(from:String) : Unit

  // inspecting
  def getTraces: Seq[String]
  def getConfigs: Seq[String]
  def getWarnings: Seq[String]
  
  // live updating
  def updTraces(msg: String): Unit 
  def updConfigs(msg: String): Unit 
  def updWarnings(msg: String): Unit 
}

class NullLogger extends Logger
{
  def log(from:String,msg:String) = {}
  def log_debug(from:String,msg:String) = {}
  def log_rcv(prim:String,end:String,msg:Msg) = {}
  def log_toPort(prim:PrimID,end:EndLID,to:EndUID,msg:Msg) = {}
  def log_warning(from:String,msg:String) = {}
  def log_updateStep(from:String) = {} 
  def log_initiate(from:String,rank:Int) = {}
  def log_propagate(from:String) = {}
  def log_commit(from:String,table:String,toOutside:Boolean) = {}
  def log_gotbehaviour(from:String,beh:String) = {}
  def log_gotdata(from:String) = {}
  def log_sentdata(from:String)= {}

  def getTraces = Nil
  def getConfigs = Nil
  def getWarnings = Nil
  
  def updTraces(m: String) = {}
  def updConfigs(m: String) = {}
  def updWarnings(m: String) = {}
}

/**
 * Builds strings for each protocol method, and implements the storing
 * of old traces, without any bound. Leaves the update methods undefined.
 */
abstract class SeqChartLogger
extends Logger
{
  var traces: Queue[String] = new Queue[String]
  var configs: Queue[String] = new Queue[String]
  var warnings: Queue[String] = new Queue[String]
  
  def getTraces = traces
  def getConfigs = configs
  def getWarnings = warnings
  
  def trace(m:String) = {traces enqueue m; updTraces(m)}  
  def config(m:String) = {configs enqueue m; updConfigs(m)} 
  def warning(m:String) = {warnings enqueue m; updWarnings(m)} 
  
  protected def fix(str:String) : String = 
    str.replaceAll("\\\\","\\\\\\\\").replaceAll("\n","\\\\n")

  protected def trimFrom(from:String): String = {
    val size = 33 - from.length
    if (size>0) return (from + new String(Array.make(size,' ')))
    else return from
  }
  
  def log_toPort(prim:String,end:String,to:EndUID,msg:Msg) = {
    val newmsg = parseMsg(msg)
    //if (attachedTo contains (prim,end)) {
    //if (ends contains end) {
      if (newmsg != "") {
        var from = trimFrom(prim+" -> "+ to.primname)
//        val size = 33 - from.length
//        if (size>0) {from = (from + new String(Array.make(size,' '))) }
        //println(from+": "+newmsg)
        trace(from+": "+newmsg.replaceAll("\n","\\\\n"))
      }
    //}
    //else
    //  log(prim,"Sending to unkown end '"+end+"': "+newmsg)
  }

  protected def parseMsg(msg:Msg) = {
    msg match {
      case Request(_) =>  msg.toString
      case StrongerReq(_) =>  msg.toString
      case Busy(_) => msg.toString
      case Table(t) => "BD"
      case DataStep(d,s) => "AS + [[ "+d+" ]]"
      case SingleStep(s) => "AS"
      case AskData(_) => "AS + AskData"
      case ReplyData(d) => "[[ "+d+" ]]"
      case m => "" //"DebugMsg - "+m
    }
  }
  
   def log_rcv(prim:String,end:String,msg:Msg) = {
    val newmsg = parseMsg(msg)
    if (newmsg != "") {
      trace(trimFrom(prim)+": Got '"+fix(newmsg)+"'")
    }
  }
  
  def log_gotbehaviour(from:String,beh:String) = {
    trace(trimFrom(from)+": Global BD "+fix(beh))
  }
  def log(from:String,msg:String) = {
    trace(trimFrom(from)+": << "+fix(msg)+" >>")
  }
  
  override def log_warning(from:String,msg:String) {
    warning(trimFrom(from)+": "+fix(msg))
    trace(trimFrom(from)+": << ?????????? WARNING!!! - "+fix(msg)+" >>")
  }

  def log_debug(from:String,msg:String) = {}
  def log_updateStep(from:String) = {} 
  def log_initiate(from:String,rank:Int) = {
    trace(trimFrom(from)+": Initiating<"+rank+">")
  }
  def log_propagate(from:String) = {
//    ...
  }
  
  def log_commit(from:String,table:String,toOutside:Boolean) = {
//    if (toOutside) trace(trimFrom(from)+": Committing to the outside" )
//    else           trace(trimFrom(from)+": Committing" )
  }

  def log_gotdata(from:String) = {
//	  trace(trimFrom(from)+": Got data.")
  }
  
  def log_sentdata(from:String)= {
//        trace(trimFrom(from)+": Sent data.")
  }

}

/**
 * Uses SeqChartLogger notation, but it only prints the results to
 * stdout, without storing any information.
 */

class ConsoleLogger extends SeqChartLogger {
    
  override def trace(m:String) = {updTraces(m)}  
  override def config(m:String) = {updConfigs(m)} 
  override def warning(m:String) = {updWarnings(m)}
  
  def updTraces(m: String) = {println("T - "+m)}
  def updConfigs(m: String) = {println("C - "+m)}
  def updWarnings(m: String) = {println("WW - "+m)}


}

