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
package org.ect.reo.libraries;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.util.PrimitiveEndNames;


/**
 * @author Christian Krause
 * @generated NOT
 */
class JavaLibraryHelper extends ASTVisitor {
	
	// Primitive end names.
	private PrimitiveEndNames names;
	
	// Ends.
	private Map<IVariableBinding,PrimitiveEnd> ends = new HashMap<IVariableBinding,PrimitiveEnd>();

	// Animation table.
	private AnimationTable animations;
	
	private Animation last;
	
	
	public JavaLibraryHelper(PrimitiveEndNames names) {
		this.names = names;
		this.animations = new AnimationTable();
	}
	
	/*
	 * Gather source and sink fields.
	 */
	@Override
	public boolean visit(FieldDeclaration field) {
		
		ITypeBinding binding = field.getType().resolveBinding();
		for (Object fragment : field.fragments()) {
			if (fragment instanceof VariableDeclarationFragment) {
				
				// Find the primitive end.
				SimpleName name = ((VariableDeclarationFragment) fragment).getName();
				PrimitiveEnd end = findEnd(name);
				if (end==null) continue;
				String typeName = binding.getQualifiedName();
				
				// Check its type.
				if ((end instanceof SourceEnd && typeName.startsWith("org.ect.ea.runtime.Source")) ||
					(end instanceof SinkEnd && typeName.startsWith("org.ect.ea.runtime.Sink"))) {
					ends.put((IVariableBinding) name.resolveBinding(), (PrimitiveEnd) end);
				}
			}
		}		
		return true;
		
	}
	
	/*
	 * Check if it is the run method.
	 */
	@Override
	public boolean visit(MethodDeclaration method) {
		return "run".equals(method.getName().getFullyQualifiedName());
	}
	
	@Override
	public boolean visit(MethodInvocation invocation) {
		
		if (invocation.getExpression() instanceof SimpleName) {
			
			IBinding binding = ((SimpleName) invocation.getExpression()).resolveBinding();
			PrimitiveEnd end = ends.get(binding);
			String method = invocation.getName().getFullyQualifiedName();
			
			if (end!=null && (method.startsWith("read") || method.startsWith("write"))) {
				
				Animation animation = new Animation();
				for (PrimitiveEnd current : ends.values()) {
					FlowColour colour = (current==end) ? FlowColour.FLOW_LITERAL : FlowColour.NO_FLOW_GIVE_REASON_LITERAL;
					animation.setColour(current, colour);
				}
				
				AnimationTable current;
				if (last==null) {
					current = animations;
				} else {
					current = new AnimationTable();
					last.setNextAnimationTable(current);
				}
				
				current.add(animation);
				last = animation;
			}
		}
		return true;
	}
	
	private PrimitiveEnd findEnd(SimpleName name) {
		for (PrimitiveEnd end : names.keySet()) {
			if (name.getFullyQualifiedName().equals(names.get(end))) return end;
		}
		return null;
	}
	
	public AnimationTable getAnimationTable() {
		return animations;
	}
}
