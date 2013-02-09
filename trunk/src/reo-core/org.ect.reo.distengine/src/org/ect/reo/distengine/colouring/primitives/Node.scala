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
package org.ect.reo.distengine.colouring.primitives

import org.ect.reo.distengine.common.SeqChartLogger
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.common.EndUID
import org.ect.reo.distengine.common.Logger
import org.ect.reo.distengine.colouring._

/**
 * Constructor to create a node with a behaviour given by the Connector Colouring
 * semantics.
 * 
 * The update of the colouring table is made by rebuilding it from scratch.
 * It is easier and probably more efficient. For that reason, the argument
 * of <code> updateBeh(end:EndLID)</code> is ignored.
 */
object Node {
  //def apply(id:String,logger:Logged) : Node[CC] = {
  def apply(id:String,logger:Logger) : org.ect.reo.distengine.redrum.genericprim.Node[CC] = {
    new org.ect.reo.distengine.redrum.genericprim.Node[CC](id,logger:Logger)  with NodeCC
  }
}
  
trait NodeCC {
  //val ends : Utils.Ends
  //val uids : Utils.UIDs
  val endflow : EndFlow
  
  val behaviour = new CC(new ColouringTable())

      /**
       * <p>Adding an <strong>input</strong> end 'e' affects the behaviour by:
       * each colouring will
       * also give reason to 'e', colouring giving reason in outputs will ask
       * for reason in 'e', new colouring corresponding to flow in 'e' and in
       * outputs.</p>
       *
       * <p>Adding an <strong>output</strong> end 'e' affects the behaviour by:
       * each colouring with any flow will also have flow in 'e', ....</p>
       *  
       * <p>Instead of updating, we are rebuilding instead. Easier and likely to
       * be more efficient. For this reason the argument is ignored.</p>
       */
      def updateBeh(end:EndUID) = {
        val ct = new ColouringTable()
        
        // get input and output ends
        var inputs : List[EndUID] = List()
        var outputs : List[EndUID] = List()
        for ((uid,io) <- endflow)//((key,end) <- ends)
          if (io == Input) inputs  = uid :: inputs
          else             outputs = uid :: outputs
        
        // build flow colourings
        if (!outputs.isEmpty)
          for (iend <- inputs) {
            val col1 = new Colouring
            for (iend2 <- inputs) {
              if (iend == iend2) col1(iend2) = Flow
              else               col1(iend2) = FromOut //ToOut
            }
            for (oend <- outputs) col1(oend) = Flow
            ct += col1
            // again, but to extend the flip rule
            val col2 = new Colouring
            for (iend2 <- inputs) {
              if (iend == iend2) col2(iend2) = Flow
              else               col2(iend2) = ToOut
            }
            for (oend <- outputs) col2(oend) = Flow
            ct += col2	
	    }
        
        // no-flow from inputs
        //val col = new Colouring
        //for (iend <- inputs)  col(iend) = ToOut  // FromOut
        //for (oend <- outputs) col(oend) = FromIn // ToIn
        //ct += col
        // again no-flow from inputs, to extend the flip rule
        var lst : List[List[Colour]] = List(Nil)
        for (_ <- outputs) lst = lst.map(FromIn::_) ::: lst.map(ToIn::_)
        for (colours <- lst) {
          val col = new Colouring
          for (iend <- inputs) col(iend) = ToOut
          for ((oend,colour) <- outputs zip colours) {
            col(oend) = colour
          }
          ct += col
        }
        

        
        // no-flow from outputs
        /*
        for (oend <- outputs) {
          var col = new Colouring
          for (oend2 <- outputs) {
            if (oend == oend2) col(oend2) = ToIn   // FromIn
            else               col(oend2) = FromIn // ToIn
          }
          for (iend <- inputs) col(iend) = FromOut // ToOut
          ct += col
        }
        */
        // again, to expand the flip rule
        var ilst : List[List[Colour]] = List(Nil)
        for (_ <- inputs) ilst = 
          ilst.map(FromOut::_) ::: ilst.map(ToOut::_)
        var olst : List[List[Colour]] = List(Nil)
        var toRm : List[Colour] = Nil
        for (_ <- outputs) {
          olst = olst.map(FromIn::_) ::: olst.map(ToIn::_)
          toRm = FromIn :: toRm
        }
        olst = olst - toRm
        for (icolours <- ilst; ocolours <- olst) {
          var col = new Colouring
          for ((iend,colour) <- inputs zip icolours)
            col(iend) = colour
          for ((oend,colour) <- outputs zip ocolours)
            col(oend) = colour
          ct += col
        }

        
        // set the current ct to the one calculated
        behaviour.value = ct
      }// updateBeh

}

/**
 * Constructor to create a node which behaves as an exclusive router,
 * using the the Connector Colouring semantics.
 * 
 * The update of the colouring table is made by rebuilding it from scratch.
 * It is easier and probably more efficient. For that reason, the argument
 * of <code> updateBeh(end:EndLID)</code> is ignored.
 * 
 * NOTE: Not expanding the flip rule as with the normal node. BE CAREFULL!
 */
object RouterNode {
  //def apply(id:String,logger:Logged) : Node[CC] = {
  def apply(id:String,logger:Logger) : org.ect.reo.distengine.redrum.genericprim.Node[CC] = {
    new org.ect.reo.distengine.redrum.genericprim.Node[CC](id,logger) with RouterNodeCC
  }
}
  
trait RouterNodeCC {
  //val ends : Utils.Ends
  //val uids : Utils.UIDs
  val endflow : EndFlow

      val behaviour = new CC(new ColouringTable())

      /**
       * <p>Instead of updating, we are rebuilding instead. Easier and likely to
       * be more efficient. For this reason the argument is ignored.</p>
       * 
       * <p>Possible behaviour is: (1) Flow from one of the inputs to one of the
       * outputs, giving reasons to every other end; (2) No flow with the
       * reason comming from all input ends to all output ends; and (3)
       * the same as (2) but from output ends to input ends. 
       */
      def updateBeh(end:EndUID) = {
        val ct = new ColouringTable()
        
        // get input and output ends
        var inputs : List[EndUID] = List()
        var outputs : List[EndUID] = List()
        for ((uid,io) <- endflow) //((key,end) <- ends)
          if (io == Input) inputs  = uid :: inputs
          else             outputs = uid :: outputs
        
        // build flow colurings
        if (!outputs.isEmpty)
          for (iend <- inputs) {
            for (oend <- outputs) {
              val col = new Colouring
              for (iend2 <- inputs) {
                if (iend == iend2) col(iend2) = Flow
                else               col(iend2) = FromOut // ToIn
              }
              for (oend2 <- outputs) {
                if (oend == oend2) col(oend2) = Flow
                else               col(oend2) = FromIn // ToOut
              }
              ct += col
            }
        }
        
        // no-flow from inputs
        val col_in = new Colouring
        for (iend <- inputs)  col_in(iend) = ToOut   // FromIn
        for (oend <- outputs) col_in(oend) = FromIn  // ToOut
        ct += col_in
        
        // no-flow from outputs
        val col_out = new Colouring
        for (iend <- inputs)  col_out(iend) = FromOut // ToIn
        for (oend <- outputs) col_out(oend) = ToIn    // FromOut
        ct += col_out
        
        // set the current ct to the one calculated
        behaviour.value = ct
      }// updateBeh

}
