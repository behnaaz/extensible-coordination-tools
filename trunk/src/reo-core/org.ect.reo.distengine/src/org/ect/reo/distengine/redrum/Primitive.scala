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
import actors.Actor

//import org.ect.reo.distengine.common.Behaviour
//import org.ect.reo.distengine.common.CC
import org.ect.reo.distengine.common.Utils._
//import org.ect.reo.distengine.redrum.distributed.RemoteActorsHacked.serialize

import scala.actors.Actor
import scala.actors.Actor._
import collection.mutable.{ Map => MutMap }
import collection.mutable.{ Set => MutSet }
import collection.immutable.{ Map => ImMap }
import collection.immutable.{ Set => ImSet }
import scala.collection.Set


/**
 * <p>
 * Implementation of a primitive that obeys the interface imposed by <code>AbstractPrimitive</code>.
 * It abstracts from the behaviour, and leaves undefined the definitions of:
 * name; behaviour; ends; source; updateBeh. </br>
 * </p>
 
 * <p>
 * To define a new primitive that extends this class, we suggest to:
 * </p><ul>
 * 
 *   <li> start by declaring the primitive name.
 *   e.g. <pre>val name = "primitive_name"</pre></li>
 *   
 *   <li> Declare a <code>behaviour</code>, even if still undefined.
 *   e.g. <pre>val behaviour = new org.ect.reo.distengine.colouring.CC(new ColouringTable)</pre></li>
 *
 *   <li> Define each end, which will use the reference to <code>behavior</code>.
 *   e.g. <pre>val a : AbstractEnd = new End("endname_a",Input,this) with ConsoleLogger</pre></li>
 *
 *   <li> declare the collection of ends, and add each end to it.
 *   e.g. <pre>val ends = new HashMap(); ends("endname_a") = a; ...</pre></li>
 *   
 *   <li> define each colouring, and add it to the primitive's behaviour.
 *   e.g. <pre> val colouring = new Colouring; colouring(a) = Flow; ...;
 *   behaviour.value += colouring; ...</pre></li>
 *   
 *   <li> define how data of each end can be computed, by defining
 *    <code>source</code>.
 *    e.g.<pre>
 *    def source(ct:ColouringTable,e:AbstractEnd) : Either[Set[AbstractEnd],Any] = {
 *      val ret : HashSet[AbstractEnd] = new HashSet
 *      if ((e == a) && (ct hasFlow e)) ret += a
 *      return new Left(ret)
 *    }
 *   </pre></li>
 *    
 *   <li> define how the behaviour evolves after a given step succeeds.
 *   e.g. <pre>def updateStep(c:ColouringTable) = { ... } </pre></li>
 *   
 *  </ul>
 *
 */
abstract class Primitive[Behaviour <: AbstractBehaviour](val logger:Logger)
extends Actor
with AbstractPrimitive[Behaviour]
// with SeqChartLogged
{
  
  //var stage = 1
  def stage = state match {
    case Suspended(_)          => 1
    case Idle                  => 2
    case Committing(_,_,_,_,_) => 3
    case Committed(_,_,_,_)    => 4
    case WaitingData(_,_,_)    => 5
  }
  
  var state : PrimState = Suspended(ImSet(""))
  def act = nextInput
  
 /**
  * Process actor messages while in <b>Running mode</b>.
  * 4 types of messages it can process:
  *  CommitMsg to ends (such as Request, Table, ...),
  *  CommunicateMsg to Ends (such as Step, DataStep, DataReply, ...),
  *  AdminMsg (Suspend, Kill, Abort, ...)
  *  Release a lock (is a reconfig msg so far...)
  */
  def nextInput : Nothing = {
//    if(name endsWith "24")
//      println("status24 - "+status)
    state match {
      /// STAGE 1 - SUSPENDED
      case Suspended(locks) => react {
        case Suspend(lock)    => gotSuspend(locks,lock)
        case msg:ReconfigMsg  => gotReconfigMsg(locks,msg)
        case msg:AdminMsg     => gotAdminMsg(msg)
        // rest ignore
      }
      /// STAGE 2 - IDLE -> ONLY THAT THROWS ERRORS WITH UNEXPECTED MSGS!
      case Idle => react {
        case Suspend(lock)                => gotSuspend(ImSet(),lock)
        case (endname:String,msg:Request) => gotCommitMsg(endname,msg)
        case msg:AdminMsg                 => gotAdminMsg(msg)
        case (endname:String,msg:CommunicateMsg) =>
          logger.log_rcv(name,"",msg)
          debug("Ignoring late commitMsg to end "+endname+". Not analysing commiting msgs while Idle.")
          nextInput
        case unexpected: AnyRef =>
          logger.log_warning(name,"ERROR: We're getting a signal "+unexpected.toString+" of type "+unexpected.getClass)
          nextInput
      }
      /// STAGE 3 - COMMITTING
      case Committing(_,_,_,_,_) => react {
        case (endname:String,msg:CommitMsg) => gotCommitMsg(endname,msg)
        case msg:AdminMsg                   => gotAdminMsg(msg)
        case (endname:String,msg:CommunicateMsg) =>
          debug("Ignoring late commitMsg to end "+endname+". Not analysing commiting msgs while Idle.")
          nextInput
        // rest ignore
      }
      /// STAGE 4 - COMMITTED
      case Committed(rank:Int,_,_,_) => react {
        case (endname:String,StrongerReq(_))      => gotLateStrongerReq(endname,rank)
        case (endname:String, msg:CommunicateMsg) => gotCommunicateMsg(endname,msg)
        case msg:AdminMsg                         => gotAdminMsg(msg)        
        // rest ===> delay until stage 2 (no more early requests!)
      }
      /// STAGE 5 - WAITING DATA
      case WaitingData(_,_,_) => react {
        case (endname:String, msg:CommunicateMsg) => gotCommunicateMsg(endname,msg)
        case msg:AdminMsg                         => gotAdminMsg(msg)        
        case (endname,StrongerReq(_)) 			  =>
          logger.log_warning(name,"ERROR ERROR: Unexpected stronger request while waiting for data")
          nextInput
        // rest ===> delay until stage 2 (no more early requests!)
      }
    }
  }
  

  /** process a suspension message. */
  protected def gotSuspend(locks:ImSet[String],newlock:EndLID) : Nothing = {
    logger.log_rcv(name,"",Suspend(newlock))
    state = Suspended(locks + newlock)
    nextInput
  }
  
  /** Process a reconfiguration message. */
  protected def gotReconfigMsg(locks:ImSet[String],msg:ReconfigMsg) : Nothing = {
    logger.log_rcv(name,"",msg)
    
    msg match {
      case Release(lock:String) => {
        debug("Releasing suspension")
        val newlocks = locks - lock
        if (newlocks.isEmpty) {
          state = Idle
          debug("Unsuspended!")
        }
        else {
          state = Suspended(newlocks)
          debug("Still suspended - "+state)
        }
      }
      case RemovePrim() => {
        //for (end <- ends.values) {
        //  end.removeEnd
        //}
        exit() // Not sure it exits
      }      
      case SetPort(null,from:EndLID,null) => {
        debug("dettaching end (name) "+from)
        if (ends contains from) {
         ends(from).resetPort
        }
        else logger.log_warning(name,"ERROR: dettaching port of an unknown end: "+from)
      }
      case SetPort(otherPort:Port,from:EndLID,to:EndUID) => {
        debug("attaching end (name) "+from+" to end (uid) "+to)
        if (ends contains from) {
         ends(from).setPort(otherPort,to)
        }
        else logger.log_warning(name,"ERROR: attaching port to an unknown end: "+from)
      }
      case x =>
        logger.log_warning(name,"reconfiguration message '"+x+"' is not available for now...!!")
    }
    nextInput
  }

  
  /** Process an Admin message. */
  def gotAdminMsg(msg:AdminMsg) : Nothing = {
    logger.log_rcv(name,"",msg)
    msg match {
      case Reset() => { // resets the end (and propagates reseting)
        debug("asking to reset")
        for (ref <- ends.values)
          ref.reset
      }
      case Kill() => { // kill connector end
        killPrim
      }
      case Status() => { // status
        //show(status)
        sender ! status
      }
    }
    nextInput
   } // gotAdminMsg

  
  /** Process a StrongerReq that arrived while committed. */
   protected def gotLateStrongerReq(endname:EndLID,rank:Int) : Nothing = {
     logger.log_rcv(name,endname,StrongerReq(rank))
     assert(ends contains endname, name+": expected to contain end "+endname+
              ", but only got ends "+ends.keys.mkString(","))
     ends(endname).toPort(Busy(rank))
     nextInput
   }

    
  /**
   * Process a commit message - regarding the stage where primitives try to agree. 
   */
  def gotCommitMsg(endname:String,msg:CommitMsg) : Nothing = {
      logger.log_rcv(name,endname,msg)

      assert(ends contains endname, name+": expected to contain end "+endname+
           ", but only got ends "+ends.keys.mkString(","))
          
      debug("evolving...")
      //logger.log(name,"evolving after receiving '"+msg+"' to "+endname+" and in state - "+state+".")

      def propagate_request
      (rank:Int,children:MutSet[EndLID],pending:MutMap[EndLID,Int],childBeh:Beh,oldRoot:Option[EndLID]) {
        val newRoot = endname
        
        // changing direction, so just send to old root and update state
        for (name <- oldRoot; end <- ends.get(name)) {
          pending -= newRoot
          if (end.hasPort) {
            pending(name) = rank
            end.toPort(Request(rank))
          }
        }
        // First request, so send to all ends except the root
        if (!oldRoot.isDefined) 
          for ((name,end) <- ends)
            if ((name != newRoot) && end.hasPort) {
              pending(name) = rank
              end.toPort(Request(rank))
            }
        
        if (!pending.isEmpty)
          state = Committing(rank,newRoot,children,pending,childBeh)
        else
          commit_primitive(rank,newRoot,children,childBeh)
      }
      
      def commit_primitive
      (rank:Int, root:EndLID, children:MutSet[EndLID], childBeh:Beh) {
        assert(ends contains root, "Root of committing primitive is not known: "+root+". Ends: "+ends.mkString(";"))
        //logger.log(name,"adding behaviour "+childBeh+" to mine "+behaviour.value)
        val resBeh = behaviour +++ childBeh
        logger.log_commit(name,resBeh.toString,true)
        state = Committed(rank,root,children,resBeh)
        ends(root).toPort(Table(resBeh))
      }
      
      def remove_pending      
      (rank:Int,root:EndLID,children:MutSet[EndLID],pending:MutMap[EndLID,Int],childBeh:Beh) {
        pending -= endname
        if (pending.isEmpty)
          commit_primitive(rank,root,children,childBeh)
      }
      
      state match {
        ///////////////////
        ////// IDLE ///////
        case Idle => msg match {
          // -- Request from the port
          case Request(rank) => {
            propagate_request(rank,MutSet(),MutMap(),behaviour.unit,None)
          }
          // Other requests should be filtered by nextInput
          case unexpected: AnyRef => {
            logger.log_warning(name,"### UNEXPECTED: Idle and we're getting a signal " +
               unexpected.toString + " of type " + unexpected.getClass)
           }
        } //case Idle


        ////////////////////////////////////////////////////
        ////// COMMITTING - Waiting for pending ends ///////
        case Committing(i,root,children,pending,childBeh:Beh) => msg match {
          // -- Request from Port
          case Request(rank:Int) => {
            // request from a pending end
            if (pending contains endname) {
              val lastSent = pending(endname)
              if (i == rank) {
                debug("request received with same rank! ("+i+"). "+
                              "If last request was weaker ("+lastSent+"), sending busy (request should also work).")
                if (i>lastSent)
                  ends(endname).toPort (Busy(i))
                remove_pending(rank,root,children,pending,childBeh)
                logger.log(name,"removed pending end after same request. New state: "+state)
              }
              else if (rank < i) { // lower request from port
                if (rank >= lastSent) { // OLD BUG - was i > lastSent! Second bug: was rank > lastSent!
                  debug("sending a stronger request to port")
                  pending(endname) = i
                  ends(endname).toPort(StrongerReq(i))
                }
                // else ignore, because stronger request was already sent
                else debug("ignoring weaker request, beacuse stronger was already sent")
              }
              else { // j > i -- since it was waiting for replies from ports, change direction!
                debug("got stronger request ("+rank+"). Changing direction into primitive")
                propagate_request(rank,children,pending,childBeh,Some(root))
              }
            }
            // request from another end: root, end with no port, or already commited end
            else
              logger.log_debug(name,"### Unexpected request to a committing primitive from a non-pending end: "+endname)
          }

          // Stronger request from port, as a reply to a previously sent request
          // Means it has sent a weaker request first and we had already sent a
          //  stronger request. Behave as normal request then.
          // Can also mean 
          case StrongerReq(rank) => {            
            // Still pending because had a weaker request
            //if (pending contains endname)
              gotCommitMsg(endname,Request(rank))
          }
                    
          // -- Busy -- forget end
          case Busy(rank:Int) => {
            debug("Removing end, since it already committed to forget this link.")
            remove_pending(rank,root,children,pending,childBeh)
          }
            
          // -- Table
          case Table(otherrep:Beh) => {
            debug("got table. removing end: "+endname+" if is pending: "+pending)
            if (pending contains endname) {
              val newBeh = behaviour.join(childBeh,otherrep)
              state = Committing(i,root,children+endname,pending,newBeh)
              remove_pending(i,root,children,pending,newBeh)
              debug("removed pending. new state: "+state)
            }
            else
              logger.log_warning(name,"ERROR!! ignoring table from port (waiting for primitive and not port!)")
          }

          // -- REST (requestP, TableP) -- not sure if unmatched messages stay in the mailbox...
          case _ => {}
        } //case Committing
        
        case state => { logger.log_warning(name,"ERROR: Wrong state (should have been filtered by nextInput. "+state)}
      } //State match
      nextInput
  } // gotCommitMsg
   
   
  /**
   * Action when a message to an end that contains the decided step is received.
   * This is where all the magic of the communication stage happens, where data is sent.
   * The method <code>endStep</code> is called when all data and behaviour have been dealt with.
   */
  def gotCommunicateMsg(endname:EndLID,msg:CommunicateMsg) : Nothing = {
     logger.log_rcv(name,endname,msg)

     assert(ends contains endname, name+": expected to contain end "+endname+
              ", but only got ends "+ends.keys.mkString(","))

     /**
      * Send behaviour through ends which have no flow, and return the remaining
      * input and output ends
      */
     def processData(domain:Iterable[EndLID],step:Step) : MutSet[EndLID]= {
//       if(name endsWith "n24"){
//         println("HELLOOOOOOOO!")
//       }
       val toSend : MutSet[EndLID] = MutSet()
       for (endname <- domain) {
         val end = ends(endname)
         if (hasFlow(step,end)) {
           if (end.iotype == Input) { // is input and with flow
             val ad : CommunicateMsg = AskData(step)
             end.toPort(ad)
             //toAsk += endname
           }
           else // is output and with flow
             toSend += endname
         }
         else  // there is no flow
           end.toPort(SingleStep(step))
       }
       return toSend//(toAsk,toSend)
     }

     
     state match {
        case Committed(_,_,_,_) => {
          msg match {
            case DataStep(data,step:Step) => {
              debug("Commited and data step received")
              // send no-flow info and ask for data, and return remaining input and output ends
              val toSend = processData(ends.keys.filter(endname.!=),step)
              // search for output end that uses 'data' or some known data
//              for (endToSend <- toSend) {
              val toSend2 = (Array():Array[String]) ++ toSend
              for (i <- 0 until toSend2.size) {
                val endToSend = toSend2(i)
                val end = ends(endToSend)
                // end is an output end with flow data. Need to check if was the data received. 
                source(step,end.uid) match {
                  case Left(sourceuids) => 
                    // DEBUG!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    debug("checking if "+ends(endname).uid+" is in "+sourceuids.mkString(",")+
                      ": "+(sourceuids contains ends(endname).uid))

                    if (sourceuids contains ends(endname).uid) {
                      end.toPort(DataStep(data,step))
                      toSend -= endToSend
                    }
                  case Right(dt) =>
                    end.toPort(DataStep(dt,step))
                    toSend -= endToSend
                }
//                println("done with "+endToSend+" now "+toSend.mkString("(",",",")"))
              }
              // update state according to pending requests of data
              if (toSend.isEmpty) {
                endStep(endname,step)
              }
              else {
                state = WaitingData(toSend,MutSet(),step)
              }
            }
            
            case SingleStep(step:Step) => {
              debug("Commited and step received")
              // send no-flow info and ask for data, and return remaining input and output ends
              val toSend = processData(ends.keys.filter(endname.!=),step)
              debug("will try to send data thought ends "+toSend)
              // search for output end that has flow with known data (in source function)
              for (endToSend <- toSend) {
                val end = ends(endToSend)
                source(step,ends(endToSend).uid) match {
                  case Right(dt) =>
                    end.toPort(DataStep(dt,step))
                    toSend -= endToSend
                  case _ => {
                    debug("end '"+endToSend+"' has no data as the source...")
                  }
                }
              }
              // update state according to pending requests of data
                if (toSend.isEmpty) {
                  endStep(endname,step)
                }
                else {
                  state = WaitingData(toSend,MutSet(),step)
                }
              }

              case AskData(step:Step) => {
                debug("Commited and ask step received")
                debug("endname: "+endname)
                debug("ends.keys: "+ends.keys.mkString("(", "; ", ")"))
                debug("domain(filter != endname): "+ends.keys.filter(endname.!=).mkString("(", "; ", ")"))
                debug("step: "+step)
                // send no-flow info and ask for data, and return remaining input and output ends
                debug("about to process data")
                val toSend = processData(ends.keys.filter(endname.!=),step)
                debug("finished to process data")
                val toReply : MutSet[EndLID] = MutSet()
                debug("source of "+endname+" to reply immidiately data, if known")
                source(step,ends(endname).uid) match {
                  case Right(dt) =>
                    ends(endname).toPort(ReplyData(dt))
                  case x =>
                    toReply += endname
                }
                debug("sending data to all output ends, if it known.")
                // search for output end that has flow with known data (in source function)
                for (endToSend <- toSend) {
                  val end = ends(endToSend)
                  source(step,ends(endToSend).uid) match {
                    case Right(dt) => {
                      end.toPort(DataStep(dt,step))
                      toSend -= endToSend
                    }
                    case x => {}
                  }
                }
                debug("updating step accordingly ("+toSend+"/"+toReply+")")
                // update state according to pending requests of data
                if (toSend.isEmpty && toReply.isEmpty) {
                  endStep(endname,step)
                }
                else {
                  state = WaitingData(toSend,toReply,step)
                }
                debug("finished!")
              }

              case ReplyData(data) => {
                debug("ignoring replyData, since no request for data was sent. Maybe is from the previous step.")
              }
            }
          }
          
        ///////////////////////////////////////////////////////////////////
        // very specific:
        // 'data' arrives, and every end x waiting for reply where sender in source(x) gets 'data'
        case WaitingData(toSend:Set[EndLID],toReply:Set[EndLID],step:Step) => {
          msg match {
            case DataStep(data,_) => {
              debug("WaitingData and data step received")
              // ignore step because it is known. Focus on data.
              // search for output end that uses 'data'
              
                for (someendid <- toReply) {
                  val end = ends(someendid)
                  // end is an output end with flow data. Need to check if was the data received. 
                source(step,end.uid) match {
                  case Left(sourceends) =>
                    // DEBUG!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    debug("checking if "+ends(endname).uid+" is in "+sourceends.mkString(",")+
                      ": "+(sourceends contains ends(endname).uid))
                    if (sourceends contains ends(endname).uid) {
                      end.toPort(ReplyData(data))
                      toReply -= someendid
                    }
                  // other case was already checked before
                    case Right(_) =>
                    assume(false,"Source of end "+end+
                     " is marked as data, but initial check failed!")
                  }
                }

                for (someendid <- toSend) {
                  val end = ends(someendid)
                  // end is an output end with flow data. Need to check if was the data received. 
                source(step,end.uid) match {
                  case Left(sourceends) => 
                    // DEBUG!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    debug("checking if "+ends(endname).uid+" is in "+sourceends.mkString(",")+
                      ": "+(sourceends contains ends(endname).uid))
                    if (sourceends contains ends(endname).uid) {
                      end.toPort(DataStep(data,step))
                      toSend -= someendid
                    }
                  // other case was already checked before
                  case Right(_) =>
                   assume(false,"Source of end "+end+
                    " is marked as data, but initial check failed!")
                }
              }
              // update state according to pending requests of data
              if (toSend.isEmpty && toReply.isEmpty) {
                endStep(endname,step)
             }
              else {
                //state = WaitingData(remaining,step)
                }
            }
              
            case SingleStep(_) => {
                debug("Ignoring step, because it was already known.")
            }

            case AskData(_) => {
                debug("got AskData. Ignoring step because it is already known, and updating state.")
                if (toSend contains endname) {
                  toSend -= endname
                  toReply += endname
                }
            }

            case ReplyData(data) => {
                debug("WaitingData and reply step received")

                for (someendid <- toReply) {
                  val end = ends(someendid)
                  // end is an output end with flow data. Need to check if was the data received. 
                  source(step,end.uid) match {
                    case Left(sourceends) =>
                      // DEBUG!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                      debug("checking if "+ends(endname).uid+" is in "+sourceends.mkString(",")+
                        ": "+(sourceends contains ends(endname).uid))               
                      if (sourceends contains ends(endname).uid)
                        end.toPort(ReplyData(data))
                        toReply -= someendid
                    // other case was already checked before
                      case Right(_) =>
                        assume(false,"Source of end "+end+
                                 " is marked as data, but initial check failed!")
                  }
                }

                for (someendid <- toSend) {
                  val end = ends(someendid)
                  // end is an output end with flow data. Need to check if was the data received. 
                  source(step,end.uid) match {
                    case Left(sourceends) =>
                      // DEBUG!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                      debug("checking if "+ends(endname).uid+" is in "+sourceends.mkString(",")+
                        ": "+(sourceends contains ends(endname).uid))
                      if (sourceends contains ends(endname).uid) {
                        end.toPort(DataStep(data,step))
                        toSend -= someendid
                      }
                    // other case was already checked before
                    case Right(_) =>
                    assume(false,"Source of end "+end+
                     " is marked as data, but initial check failed!")
                  }
                }
                // update state according to pending requests of data
                if (toSend.isEmpty && toReply.isEmpty) {
                  endStep(endname,step)
                }
                else {
                 //state = WaitingData(toSend,toAsk,step)
                }
            }
          }
        }

//        // REST (Committing, Idle, Suspended)  -- not sure if unmatched messages stay in the mailbox...
//        case _ => {}
     } // state match
     nextInput
  } // gotCommunicateMsg
   
   
  def hasFlow(step:Step,end:AbstractEnd) : Boolean = {
    step hasFlow end.uid
  }

   
  def status : String = {
   var res = "'"+name+"' ("+state.toString+") - ends:"
   if (ends.isEmpty)
     res += " Empty"
   else
     for (end <- ends.values) {
       if (!end.name.startsWith("%",0)) // exclude hidden variables (dummy ends...)
         res += ("\n    *"+end.hashCode.toString+"* "+end.status)
     }
   // uncomment for include behaviour on status
   //res += (" "+behaviour.value.toString)
   res
  }
  
  protected def killPrim : Nothing = {
   //if (DEBUG) {
     /*
     debug(",^^ killing primitive")
     debug(status)
     for (ref <- ends.keys)
       debug("killing "+ref)
     for (ref <- ends.values)
       ref.kill
     this ! ()
     debug("cleaning mailbox before continuing")
     loop { react {
       case () => 
         debug("`__ primitive killed")
         exit()
       case x:Any =>
         debug("del pending msg: "+x)
     }}
     */
   exit()
  }
  
  /** Abort the current step. Depends on the state. It will become Idle,
   * and propagate the abort to the neighbours if */
  private def abortStep() : Unit = {
    state match {
      case Idle => return
      //case Committing

//      //REST (Committing, Committed, Suspended, WaitingData)  -- not sure if unmatched messages stay in the mailbox...
//      case _ => {}
    }    
  }
  
  
  /** Sets the ends and primitive states to Idle,
    * logs update, and runs the primitive's customized
    * updateStep function. In the end tries to load the older delayed action
    * (request or suspension) before looking at the next message.
    */
  def endStep(endname:EndLID,rep:Step) : Unit = {
    debug("finishing data prop step. Updating behaviour.")
    state = Idle
    logger.log_updateStep(name)
    updateStep(rep)
    //loadEarlyMessage()
  }

  override def toString = name
  def deepToString = name
  
  def debug(msg:String) = logger.log_debug(name,msg)
}
