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
package org.ect.codegen.reo2ea.ca.transform.channels;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.ect.codegen.reo2ea.core.TransformationException;

import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Literal;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;
import org.ect.reo.DataAware;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.Channel;

abstract class DataAwareHelper extends ChannelTransform {
	protected Constraint mapNames(Channel channel) throws TransformationException {
		Constraint cons;
		try {
			String expression = ((DataAware)channel).getExpression();
			if (expression==null || expression.isEmpty())
				return new Literal(true);
			cons= ConstraintParserHelper.parse(expression);
		} catch (ParseException e) {
			throw new TransformationException(e);
		}

		PrimitiveEnd pe1 = channel.getChannelEndOne();
		PrimitiveEnd pe2 = channel.getChannelEndTwo();
		Map<String,String> names = new HashMap<String, String>();		
		names.put(pe1.getNode().getName(), endNames.getName(pe1));
		names.put(pe2.getNode().getName(), endNames.getName(pe2));
		
		for (Element e : cons.getAllElements())
			if (e.getType()==ElementType.IDENTIFIER) {
				String id = e.getValue();
				if (!names.containsKey(id))
					throw new TransformationException("no substitution for "+id+" in "+cons);
//				else
				e.setValue(names.get(id));
			}
		
		return cons;
	}
}
