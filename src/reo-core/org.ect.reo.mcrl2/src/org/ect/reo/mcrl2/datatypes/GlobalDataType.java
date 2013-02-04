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
package org.ect.reo.mcrl2.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Element;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Specification;
import org.ect.reo.mcrl2.Structure;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class GlobalDataType extends Structure {
	
	private List<Sort> registered = new ArrayList<Sort>();
	
	private Element[] tuples = new Element[4];
	
	
	/**
	 * Default constructor.
	 * @param name Name of the data type.
	 */
	public GlobalDataType(String name) {
		super(name);
	}
	
	
	/**
	 * Initialize the global data type for a specification.
	 */
	public void initialize(Specification specification) {
		
		for (int i=0; i<specification.getSorts().size(); i++) {
			
			Sort sort = specification.getSorts().get(i);
			if (sort instanceof GlobalDataType || sort instanceof ColouredDataType) continue;
			register(sort);
			
			Element element = new Element(getConstructor(sort), new Atom(getSelector(sort) , sort));
			element.setDiscriminatorName(getDiscriminator(sort));
			getElements().add(element);
		}
				
		if (!specification.getSorts().contains(this)) {
			specification.getSorts().add(this);			
		}
		
	}
	
	public Element getTuple(int length) {
		if (length<2) return null;
		if (length>=tuples.length) {
			Element[] oldTuples = tuples;
			tuples = new Element[length+2];
			for (int i=0; i<length; i++) {
				tuples[i] = oldTuples[i];
			}
		}
		if (tuples[length]==null) {
			Element tuple = new Element("Tuple"+length);
			for (int i=0; i<length; i++) {
				tuple.getParameters().add(new Atom("t"+(i+1),this));
			}
			tuple.setDiscriminatorName("isTuple" + length);
			getElements().add(tuple);
			tuples[length] = tuple;
		}
		return tuples[length];
	}
	
	private void register(Sort sort) {
		if (!registered.contains(sort)) {
			registered.add(sort);
		}		
	}
	
	public String getConstructor(Sort sort) {
		register(sort);
		return "d" + (registered.indexOf(sort)+1);
	}
	
	public String getSelector(Sort sort) {
		register(sort);
		return "e" + (registered.indexOf(sort)+1);
	}
	
	public String getDiscriminator(Sort sort) {
		return "is" + sort.getName();
	}
	
}
