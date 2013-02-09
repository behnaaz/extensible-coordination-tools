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

import Utils._

trait Logged
//extends scala.util.logging.Logged
{
  def log(from:String,msg:String) : Unit
  def log_debug(from:String,msg:String) : Unit
  def log_rcv(prim:PrimID,end:EndLID,msg:Msg) : Unit
  def log_toPort(prim:PrimID,end:EndLID,msg:Msg) : Unit
  def log_warning(fromend:String,msg:String) : Unit
  def log_updateStep(from:String) : Unit
  def log_initiate(from:String,rank:Int) : Unit
  def log_propagate(from:String) : Unit
  def log_commit(fromend:String,table:String,toOutside:Boolean) : Unit
  def log_gotbehaviour(from:String,beh:String) : Unit
  def log_gotdata(from:String) : Unit
  def log_sentdata(from:String) : Unit
}

trait ConsoleLogged extends Logged
//extends scala.util.logging.ConsoleLogger
{
  protected def write(from_ :String,msg:String) = {
    var from : String = from_
    if (from == null) { from = "Unnamed" }
    val size = 15 - from.length
    if (size>0) {from = from + new String(Array.make(size,' ')) }
        //(("" /: List.make(size," ")) (_ + _))}
    println(from+": "+msg)
  }

  protected def printMsg(msg:Msg) = {
    msg match {
      case Table(_) => "Some behaviour"
      case TableP(_,e) => "Some behaviour (from end "+e+")"
      case DataStep(d,_) => "Step and data '"+d+"'"
      case SingleStep(_) => "Step"
      case AskData(_) => "Step and request for data"
      //case AddEnd(_,io,from,_) => "Adding end '"+from+"':"+io
      case _ => msg.toString
    }
  }
  
  private def from(prim:String,end:String) = {
    prim+"."+end
  }
  
  def log(from:String,msg:String) =
    write(from,msg)
  def log_debug(from:String,msg:String) =
    {}
  def log_rcv(prim:String,end:String,msg:Msg) =
    write(from(prim,end), "<---  : "+printMsg(msg))
  def log_toPort(prim:String,end:String,msg:Msg) =
    write(from(prim,end), "  --->: "+printMsg(msg))
  def log_warning(from:String,msg:String) =
    write(from, "##### WARNING!! : "+msg)
  def log_updateStep(from:String) = 
    write(from, "FINISHED data/beh propagation. Updating step.")
  def log_initiate(from:String,rank:Int) =
    write(from, "initating the commiting algorithm")
  def log_propagate(from:String) =
    write(from,"starting the data/behaviour propagation")
  def log_commit(from:String,table:String,toOutside:Boolean) =
    if (toOutside) write(from, "commiting primitive to some behaviour")
    else           write(from, "commiting end to some behaviour")
  def log_gotbehaviour(from:String,beh:String) = {
    write(from,"---------GOT BEHAVIOUR!!---------")      
  }
  def log_gotdata(from:String)= {
    write(from,"---------GOT DATA!!-----------")      
  }
  def log_sentdata(from:String)= {
    write(from,"---------SENT DATA!!-----------")      
  }
}

/** Makes an instance of Logged ignore any logging function */
trait NullLogged extends Logged
{
  override def log(from:String,msg:String) = {}
  override def log_debug(from:String,msg:String) = {}
  override def log_rcv(prim:String,end:String,msg:Msg) = {}
  override def log_toPort(prim:PrimID,end:EndLID,msg:Msg) = {}
  override def log_warning(from:String,msg:String) = {}
  override def log_updateStep(from:String) = {} 
  override def log_initiate(from:String,rank:Int) = {}
  override def log_propagate(from:String) = {}
  override def log_commit(from:String,table:String,toOutside:Boolean) = {}
  override def log_gotbehaviour(from:String,beh:String) = {}
  override def log_gotdata(from:String) = {}
  override def log_sentdata(from:String)= {}
}


/** Compiles the information needed to a sequence chart. */
trait SeqChartLogged_from_gen_in_c
extends NullLogged
{
  val attachedTo : scala.collection.mutable.HashMap[(PrimID,EndLID),String]
  
  override def log_toPort(prim:String,end:String,msg:Msg) = {
    val newmsg = parseMsg(msg)
    if (attachedTo contains (prim,end)) {
      var from = prim+" -> "+ attachedTo((prim,end))
      if (newmsg != "") {
        val size = 16 - from.length
        if (size>0) {from = from + new String(Array.make(size,' ')) }
        //println(from+": "+newmsg)
        println(from+" [ label = \""+newmsg+"\"];")
      }
    }
    else
      log(prim,"Sending to unkown end '"+end+"': "+newmsg)
  }
  
  protected def parseMsg(msg:Msg) = {
    msg match {
      case Request(_) =>  msg.toString
      case StrongerReq(_) =>  msg.toString
      case Busy(_) => msg.toString
      case Table(t) => "ColTable"
      case DataStep(d,_) => "Step(with data '"+d+"')"
      case SingleStep(s) => "Step"
      case AskData(_) => "Step(with data request)"
      case ReplyData(d) => "Data reply("+d+")"
      case _ => ""
    }
  }
  
  override def log_rcv(prim:String,end:String,msg:Msg) = {
    val newmsg = parseMsg(msg)
    if (newmsg != "") {
      println(prim+" => "+prim+" [ label = \"processed "+newmsg+"\"];")
    }
  }
  
  override def log_gotbehaviour(from:String,beh:String) = {
    println(from+" => "+from+" [ label = \"Behaviour! - "+beh+"\"];")
  }
  override def log_gotdata(from:String) = {
    println(from+" => "+from+" [ label = \"Data\"];")
  }
  
  override def log(from:String,msg:String) = {
    println(from+" => "+from+" [ label = \"<< "+msg+" >>\"];")
  }
  
  override def log_warning(from:String,msg:String) {
    log(from,"?????????? WARNING!!! - "+msg)
  }
  
  override def log_debug(from:String,msg:String) = {}
  override def log_updateStep(from:String) = {} 
  override def log_initiate(from:String,rank:Int) = {}
  override def log_propagate(from:String) = {}
  override def log_commit(from:String,table:String,toOutside:Boolean) = {}
  override def log_sentdata(from:String)= {}

}


/** Compiles the information needed to a sequence chart
 *  Output can be interpreted by a tool called flow2dot, that generates a graphviz .dot file.
 *  Results not satisfactory when using olf graphviz version (in Mac OS).
 */
trait SeqChartLogged
extends Logged
//extends NullLogger
{
  val ends : scala.collection.mutable.Map[EndLID,AbstractEnd]
  private def attachedTo(end:EndLID) = {
    assume (ends contains end, "Logger searching for end "+end+", but not found!")
    assume (ends(end).hasPort,"Logger expected otherend of "+end+" to be defined!")
    ends(end).optOtherEnd.get.primname
  }
  
  protected def fix(str:String) : String = 
    str.replaceAll("\\\\","\\\\\\\\").replaceAll("\n","\\\\n")

  
  override def log_toPort(prim:String,end:String,msg:Msg) = {
    val newmsg = parseMsg(msg)
    //if (attachedTo contains (prim,end)) {
    if (ends contains end) {
      if (newmsg != "") {
        var from = prim+" -> "+ attachedTo(end)
        val size = 16 - from.length
        if (size>0) {from = from + new String(Array.make(size,' ')) }
        //println(from+": "+newmsg)
        println(from+": "+newmsg.replaceAll("\n","\\\\n"))
      }
    }
    else
      log(prim,"Sending to unkown end '"+end+"': "+newmsg)
  }
  
  protected def parseMsg(msg:Msg) = {
    msg match {
      case Request(_) =>  msg.toString
      case StrongerReq(_) =>  msg.toString
      case Busy(_) => msg.toString
      case Table(t) => "ColTable"
      case DataStep(d,s) => "Step(with data '"+d+"')"
      case SingleStep(s) => "Step"
      case AskData(_) => "Step(with data request)"
      case ReplyData(d) => "Data reply("+d+")"
      case m => "" //"DebugMsg - "+m
    }
  }
  
  override def log_rcv(prim:String,end:String,msg:Msg) = {
    val newmsg = parseMsg(msg)
    if (newmsg != "") {
      println(prim+": processed '"+fix(newmsg)+"'")
    }
  }
  
  override def log_gotbehaviour(from:String,beh:String) = {
    println(from+" : Behaviour! - "+fix(beh))
  }
  override def log_gotdata(from:String) = {
    println(from+" : Data")
  }
  
  override def log(from:String,msg:String) = {
    println(from+" : << "+fix(msg)+" >>")
  }
  
  override def log_warning(from:String,msg:String) {
    log(from,"?????????? WARNING!!! - "+fix(msg))
  }

  override def log_debug(from:String,msg:String) = {}
  override def log_updateStep(from:String) = {} 
  override def log_initiate(from:String,rank:Int) = {}
  override def log_propagate(from:String) = {}
  override def log_commit(from:String,table:String,toOutside:Boolean) = {}
  override def log_sentdata(from:String)= {}

}



/** A debug version of the SeqChartLogger, providing extra information.
 */
trait SeqChartLoggedDebugger
extends SeqChartLogged {

  override def log_debug(from:String, msg:String) =
    log(from,"XXXXXXXXX - "+msg)

  override def log_commit(from:String,table:String,toOutside:Boolean) =
    if (toOutside)
      log(from,"Commited! - "+fix(table))
}

