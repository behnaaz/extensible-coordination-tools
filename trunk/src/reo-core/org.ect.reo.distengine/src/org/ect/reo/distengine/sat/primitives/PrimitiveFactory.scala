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
package org.ect.reo.distengine.sat.primitives;

import org.ect.reo.distengine.common.Utils.PrimID
import org.ect.reo.distengine.common.AbstractPrimitive
import org.ect.reo.distengine.common.SeqChartLogger
import org.ect.reo.distengine.common.Logger
import org.ect.reo.distengine.sat.SAT

object PrimitiveFactory {
  
 /**
  * <p>This basic factory allows the creation of a simple set of
  * Reo primitives. The supported cases are listed below.</p>
<pre>
"AsyncDrain" -  id:PrimID
"AsyncSpout" -  id:PrimID
"LossySync"  -  id:PrimID
"Sync"       -  id:PrimID
"SyncDrain"  -  id:PrimID
"SyncSpout"  -  id:PrimID
"FIFO1"      -  (name:PrimID,rank:Int,buffer:Option[Any])
"Taker"      -  (name:PrimID,rank:Int,counter:Int)
"Writer"     -  (name:PrimID,i:Int,data:List[Any])
"CompIn"     -  rank:Int
"CompOut"    -  rank:Int
</pre>
  */
  def create(primtype:String, args:List[Any],log:Logger) : Option[AbstractPrimitive[SAT]] = {
    (primtype.toLowerCase,args) match {
      case ("asyncdrain",List(id:PrimID)) => Some(new AsyncDrain(id,log))// with SeqChartLogger)
      case ("asyncspout",List(id:PrimID)) => Some(new AsyncSpout(id,log))// with SeqChartLogger)
      case ("lossysync",List(id:PrimID))  => Some(new LossySync(id,log) )// with SeqChartLogger)
      case ("sync",List(id:PrimID))       => Some(new Sync(id,log)      )// with SeqChartLogger)
      case ("syncdrain",List(id:PrimID))  => Some(new SyncDrain(id,log) )// with SeqChartLogger)
      case ("syncspout",List(id:PrimID))  => Some(new SyncSpout(id,log) )// with SeqChartLogger)
      case ("fifo1",List(name:PrimID, rank:Int, buffer:Any))
                                          => if (buffer == "")
                                                Some(new FIFO1(name,log,rank,None))
                                             else
                                                Some(new FIFO1(name,log,rank,Some(buffer)))
//      case ("dfvar",List(name:PrimID, rank:Int, buffer:Any))
//                                          => if (buffer == "")
//                                                Some(new DfVar(name,log,rank,None))
//                                             else
//                                                Some(new DfVar(name,log,rank,Some(buffer)))
      case ("taker" ,List(name:PrimID, rank:Int, counter:Int))  => Some(new Taker(name,log,rank,counter))
      case ("reader",List(name:PrimID, rank:Int, counter:Int))  => Some(new Taker(name,log,rank,counter))
      case ("writer",List(name:PrimID, i:Int, data:List[_])) => Some(new Writer(name,log,i,data))
      case ("writer",List(name:PrimID, i:Int, data:List[_], wtag:String))
                                                    => Some(new Writer(name,log,i,data)
                                                            with TaggedData {var tag = wtag})
      case ("source",List(name:PrimID, rank:Int))   => Some(new Source(name,log,rank))
      case ("sink",List(name:PrimID, rank:Int))     => Some(new Sink(name,log,rank,{}))
      case _ => None
    }
  }
  
  def apply(primtype:String, args:List[Any],logger:Logger) = create(primtype,args,logger)
  
  def isDefinedAt(primtype:String, args:List[Any]) : Boolean = {
    (primtype.toLowerCase,args) match {
      case ("asyncdrain",List(id:PrimID)) => true
      case ("asyncspout",List(id:PrimID)) => true
      case ("lossysync",List(id:PrimID))  => true
      case ("sync",List(id:PrimID))       => true
      case ("syncdrain",List(id:PrimID))  => true
      case ("syncspout",List(id:PrimID))  => true
      case ("fifo1",List(name:PrimID, rank:Int, buffer:String))
                                          => true
      case ("dfvar",List(name:PrimID, rank:Int, buffer:String))
                                          => true
      case ("taker",List(mame:PrimID, rank:Int, counter:Int))  => true
      case ("reader",List(mame:PrimID, rank:Int, counter:Int))  => true
      case ("writer",List(name:PrimID, i:Int, data:List[_])) => true
      case ("source",List(rank:Int))      => true
      case ("sink",List(rank:Int))        => true
      case _ => false
    }

  }
}
