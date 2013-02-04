/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.reo.animation;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;



/**
 * @see org.ect.reo.animation.AnimationPackage#getReplicateTokenStep()
 * @model kind="class"
 * @generated
 */
public class ReplicateStep extends AnimationStep {

	/**
	 * @generated NOT
	 */
	public ReplicateStep() {
		super();
	}

	/**
	 * @generated NOT
	 */
	public ReplicateStep(PrimitiveEnd end) {
		super(end);
	}

	/**
	 * @generated NOT
	 */
	public AnimationStep getCopy() {
		AnimationStep step = new ReplicateStep();
		step.copyActiveEnds(this);
		return step; 
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public int hashCode() {
		int hash = 4 + getActiveEnds().hashCode();
		return hash;
	}
	
	
	/**
	 * Returns a list of primitive ends that are the target of a
	 * token replication. The argument end is where the original
	 * token is.
	 * @generated NOT
	 */
	public List<PrimitiveEnd> getTargets(PrimitiveEnd activeEnd) {
		
		List<PrimitiveEnd> targets = new Vector<PrimitiveEnd>();
		Animation animation = getAnimation();
		if (animation==null) return targets;
		
		// Check if the replication is done in a node or a primitive.
		if (activeEnd instanceof SinkEnd) {
			for (SourceEnd source : activeEnd.getNode().getSourceEnds()) {
				if (animation.isFlow(source)) targets.add(source);
			}
		} else {
			for (SinkEnd sink : activeEnd.getPrimitive().getSinkEnds()) {
				if (animation.isFlow(sink)) targets.add(sink);
			}
		}
		
		return targets;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("replicate(");
		for (PrimitiveEnd end : getActiveEnds()) {
			result.append(end + ",");
		}
		result.append(")");
		return result.toString();
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnimationPackage.Literals.REPLICATE_STEP;
	}

} // ReplicateTokenStep