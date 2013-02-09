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
package org.ect.reo.distengine.sat.primitives

import org.ect.reo.distengine.common.SeqChartLogger
import org.ect.reo.distengine.common.Utils._
import org.ect.reo.distengine.common.EndUID
import org.ect.reo.distengine.common.Logger
import org.ect.reo.distengine.redrum.genericprim.{Node => GNode}
import org.ect.reo.distengine.sat.{SAT, ConstraintList, CNF}

/**
 * Constructor to create a node with a behaviour given by it constraints.
 * 
 * The update of the colouring table is made by rebuilding it from scratch.
 * It is easier and probably more efficient. For that reason, the argument
 * of <code> updateBeh(end:EndLID)</code> is ignored.
 */
object Node {
  def apply(id:String,logger:Logger) : GNode[SAT] = {
    new GNode[SAT](id,logger:Logger)  with NodeSAT
  }
}
  
trait NodeSAT {
  //val ends : Utils.Ends
  //val uids : Utils.UIDs
  val endflow : EndFlow
  
  val behaviour = new SAT(new ConstraintList(Nil))

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

        val const: List[CNF] = Nil
        
        // redo all behaviour from scratch. Constraints are:
        // 1 - one sk implies one sr
        // 2 - one sr implies all sk
        // 3 - all pairs of sr, not  sr1 and sr2
        // 4 - one fromIn implies all toIn and toOut
        // 5 - all fromOut implies all toIn
        // 6 - flow axiom: not(e) implies a$sr or a$sk
        // instead of 4/5/6:
          // 4a - all sr noflow implies some FromOut or all FromIn
          // 5a - strict flow axiom. not(e) implies FromIn and ToOut
          //						 			 OR ToIn and FromOut
        
        // get input and output ends
        var inputs : List[EndUID] = List()
        var outputs : List[EndUID] = List()
        for ((uid,io) <- endflow)//((key,end) <- ends)
          if (io == Input) inputs  = uid :: inputs
          else             outputs = uid :: outputs
          
        var from = 1; var to = from + inputs.size
        val sr = from until to
        from = to; to = to + outputs.size
        val sk = from until to
        from = to; to = to + sr.size
        val srr = from until to
        from = to; to = to + sk.size
        val skk = from until to
        from = to; to = to + sr.size
        val srk = from until to
        from = to; to = to + sk.size
        val skr = from until to
          
        // RECALL: input/output from CHANNEL's and not NODE's perspective
        // build flow colourings
        
        // 1 (-sk1,sr1,..,srn), (-sk2,sr1,..,srn),...
        var const1: List[List[Int]] = Nil
        for (e <- sk) const1 = (List(-e) ++ sr) :: const1
        
        // 2 (-sr1,sk),..,(-sr1,skn), ... ,(-sk1m,sr1),..,(-srm,skn)
        var const2: List[List[Int]] = Nil
        for (e1 <- sr; e2 <- sk) const2 = (List(-e1,e2)) :: const2
        
        // 3 (-sr1,-sr2),..(-sr1,-srn), (-sr2,-sr3),..,(-sr2,-srn), ...
        var const3: List[List[Int]] = Nil
        for (e1 <- 1 until (sr.size+1); e2 <- (e1+1) until (sr.size+1))
          const3 = List(-e1,-e2) :: const3
        
//        // 4 (-srr1,-srr2),..,(-srr1,-srrn),(-srr1,-skk1),..,(-srr1,-skkm),..
//        var const4: List[List[Int]] = Nil
//        for (e1 <- srr) {
//          for (e2 <- e1 until (1+outputs.size+inputs.size+sr.size))
//            const4 = List(-e1,-e2) :: const4
//          for (e2 <- skk)
//            const4 = List(-e1,-e2) :: const4
//        }// CHECK!!
        
        // 4a (sr1,..,srn,-srr1,..,-srrm,skk1), .. , (sr1,..,srn,-srr1,..,-srrm,skko) 
        // 4a try again: (sr1,..,srn,-skk1,..,-skkm,-srr1), .. , (sr1,..,srn,-skk1,..,-skkm,-srro) 
        var const4: List[List[Int]] = Nil
        val arr: List[Int] = (List(): List[Int]) ++ sr ++ skk.map(_.*(-1))
        for (e <- srr)
          const4 = (arr ++ List(-e)) :: const4
        
        // 5a (sr1,srr1,srk1), (sr1,-srr1,-skk1), .. srn sk1..skn
        var const5: List[List[Int]] = Nil
        for (i <- 0 until sr.size)
          const5 = (List(sr(i),srr(i),srk(i))) ::
        	  	   (List(sr(i),-srr(i),-srk(i))) :: const5
        for (i <- 0 until sk.size)
          const5 = (List(sk(i),skr(i),skk(i))) ::
        	  	   (List(sk(i),-skr(i),-skk(i))) :: const5
        
        var names1 = (List(): List[EndUID]) ++ inputs ++ outputs
        var names4 = names1 ++
          			 inputs.map(x => x.renameend(y => y+"$sr")) ++
          			 outputs.map(x => x.renameend(y => y+"$sk"))
        var names5 = names4 ++
          			 inputs.map(x => x.renameend(y => y+"$sk")) ++
          			 outputs.map(x => x.renameend(y => y+"$sr"))

        // set the current ct to the one calculated
        behaviour.value = ConstraintList(List(
          new CNF(const1,names1),new CNF(const2,names1),
          new CNF(const3,names1),new CNF(const4,names4),
          new CNF(const5,names5)
        ))
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
  def apply(id:String,logger:Logger) : GNode[SAT] = {
    new GNode[SAT](id,logger) with RouterNodeSAT
  }
}
  
trait RouterNodeSAT {
  //val ends : Utils.Ends
  //val uids : Utils.UIDs
  val endflow : EndFlow
  
  val behaviour = new SAT(new ConstraintList(Nil))

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
        val const: List[CNF] = Nil
        
        // redo all behaviour from scratch. Constraints are:
        // 1 - one sk implies one sr
        // 2 - one sr implies ONE sk
        // 3 - all pairs of sr AND SK, not  sr1 and sr2
          // 4a - all sr noflow implies ALL FromOut or all FromIn
          // 5a - strict flow axiom. not(e) implies FromIn and ToOut
          //						 			 OR ToIn and FromOut
        
        // get input and output ends
        var inputs : List[EndUID] = List()
        var outputs : List[EndUID] = List()
        for ((uid,io) <- endflow)//((key,end) <- ends)
          if (io == Input) inputs  = uid :: inputs
          else             outputs = uid :: outputs
          
        var from = 1; var to = from + inputs.size
        val sr = from until to
        
        from = to; to = to + outputs.size
        val sk = from until to
        
        from = to; to = to + sr.size
        val srr = from until to
        
        from = to; to = to + sk.size
        val skk = from until to
        
        from = to; to = to + sr.size
        val srk = from until to
        
        from = to; to = to + sk.size
        val skr = from until to
          
        // RECALL: input/output from CHANNEL's and not NODE's perspective
        // build flow colourings
        
        // 1 (-sk1,sr1,..,srn), (-sk2,sr1,..,srn),...
        var const1: List[List[Int]] = Nil
        for (e <- sk) const1 = (List(-e) ++ sr) :: const1
        
        // 2 (-sr1,sk1,..,skn), (-sr2,sk1,..,skn),...
        var const2: List[List[Int]] = Nil
        for (e <- sr) const2 = (List(-e) ++ sk) :: const2
        
        // 3 (-sr1,-sr2),..(-sr1,-srn), (-sr2,-sr3),..,(-sr2,-srn), ...
        var const3: List[List[Int]] = Nil
        for (e1 <- 1 until (sr.size+1); e2 <- (e1+1) until (sr.size+1))
          const3 = List(-e1,-e2) :: const3
        for (e1 <- (sr.size+1) until (sr.size+1+sk.size); e2 <- (e1+1) until (sr.size+1+sk.size))
          const3 = List(-e1,-e2) :: const3
        
        // 4a (sr1,..,srn,-srri,-skkj), for all i,j 
        var const4: List[List[Int]] = Nil
        val arr: List[Int] = (List(): List[Int]) ++ sr
        for (e1 <- srr; e2 <- skk)
          const4 = (arr ++ List(-e1,-e2)) :: const4
        
        // 5a (sr1,srr1,srk1), (sr1,-srr1,-skk1), .. srn sk1..skn
        var const5: List[List[Int]] = Nil
        for (i <- 0 until sr.size)
          const5 = (List(sr(i),srr(i),srk(i))) ::
        	  	   (List(sr(i),-srr(i),-srk(i))) :: const5
        for (i <- 0 until sk.size)
          const5 = (List(sk(i),skr(i),skk(i))) ::
        	  	   (List(sk(i),-skr(i),-skk(i))) :: const5
        
        var names1 = (List(): List[EndUID]) ++ inputs ++ outputs
        var names4 = names1 ++
          			 inputs.map(x => x.renameend(y => y+"$sr")) ++
          			 outputs.map(x => x.renameend(y => y+"$sk"))
        var names5 = names4 ++
          			 inputs.map(x => x.renameend(y => y+"$sk")) ++
          			 outputs.map(x => x.renameend(y => y+"$sr"))

        // set the current ct to the one calculated
        behaviour.value = ConstraintList(List(
          new CNF(const1,names1),new CNF(const2,names1),
          new CNF(const3,names1),new CNF(const4,names4),
          new CNF(const5,names5)
        ))
      }// updateBeh

}
