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

package org.ect.reo.diagram.providers;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.ect.reo.Property;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;


/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public void init_Reader_1002(Reader instance) {
		try {
			Object value_0 = properties_Reader_1002(instance);
			if (value_0 instanceof Collection) {
				instance.getProperties().clear();
				instance.getProperties().addAll(((Collection) value_0));
			} else {
				instance.getProperties().add((Property) value_0);
			}
			Object value_1 = sourceEnd_Reader_1002(instance);
			instance.setSourceEnd((SourceEnd) value_1);
		} catch (RuntimeException e) {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Writer_1003(Writer instance) {
		try {
			Object value_0 = properties_Writer_1003(instance);
			if (value_0 instanceof Collection) {
				instance.getProperties().clear();
				instance.getProperties().addAll(((Collection) value_0));
			} else {
				instance.getProperties().add((Property) value_0);
			}
			Object value_1 = sinkEnd_Writer_1003(instance);
			instance.setSinkEnd((SinkEnd) value_1);
		} catch (RuntimeException e) {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Reader_2008(Reader instance) {
		try {
			Object value_0 = properties_Reader_2008(instance);
			if (value_0 instanceof Collection) {
				instance.getProperties().clear();
				instance.getProperties().addAll(((Collection) value_0));
			} else {
				instance.getProperties().add((Property) value_0);
			}
			Object value_1 = sourceEnd_Reader_2008(instance);
			instance.setSourceEnd((SourceEnd) value_1);
		} catch (RuntimeException e) {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Writer_2009(Writer instance) {
		try {
			Object value_0 = properties_Writer_2009(instance);
			if (value_0 instanceof Collection) {
				instance.getProperties().clear();
				instance.getProperties().addAll(((Collection) value_0));
			} else {
				instance.getProperties().add((Property) value_0);
			}
			Object value_1 = sinkEnd_Writer_2009(instance);
			instance.setSinkEnd((SinkEnd) value_1);
		} catch (RuntimeException e) {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated NOT
	 */
	private static List properties_Reader_1002(Reader self) {
		List<Property> properties = new Vector<Property>();
		properties.add(new Property(Reader.REQUESTS, "1"));
		return properties;
	}

	/**
	 * @generated NOT
	 */
	private static List properties_Writer_1003(Writer self) {
		List<Property> properties = new Vector<Property>();
		properties.add(new Property(Writer.REQUESTS, "1"));
		return properties;
	}

	/**
	 * @generated NOT
	 */
	private static SourceEnd sourceEnd_Reader_1002(Reader self) {
		return new SourceEnd("in");
	}

	/**
	 * @generated NOT
	 */
	private static SinkEnd sinkEnd_Writer_1003(Writer self) {
		return new SinkEnd("out");
	}

	/**
	 * @generated NOT
	 */
	private static List properties_Reader_2008(Reader self) {
		return properties_Reader_1002(self);
	}

	/**
	 * @generated NOT
	 */
	private static SourceEnd sourceEnd_Reader_2008(Reader self) {
		return sourceEnd_Reader_1002(self);
	}

	/**
	 * @generated NOT
	 */
	private static List properties_Writer_2009(Writer self) {
		return properties_Writer_1003(self);
	}

	/**
	 * @generated NOT
	 */
	private static SinkEnd sinkEnd_Writer_2009(Writer self) {
		return sinkEnd_Writer_1003(self);
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = ReoDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			ReoDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}

}
