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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.ReoPackage;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.diagram.edit.parts.ComponentNameEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorNameEditPart;
import org.ect.reo.diagram.edit.parts.FilterExpressionEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentNameEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderNameEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterNameEditPart;
import org.ect.reo.diagram.edit.parts.NodeNameEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.ReaderNameEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNameEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndNameEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorNameEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainExpressionEditPart;
import org.ect.reo.diagram.edit.parts.TimerTimeoutEditPart;
import org.ect.reo.diagram.edit.parts.TransformExpressionEditPart;
import org.ect.reo.diagram.edit.parts.WriterNameEditPart;
import org.ect.reo.diagram.parsers.MessageFormatParser;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;


/**
 * @generated
 */
public class ReoParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser connectorName_4004Parser;

	/**
	 * @generated
	 */
	private IParser getConnectorName_4004Parser() {
		if (connectorName_4004Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			connectorName_4004Parser = parser;
		}
		return connectorName_4004Parser;
	}

	/**
	 * @generated
	 */
	private IParser readerName_4005Parser;

	/**
	 * @generated
	 */
	private IParser getReaderName_4005Parser() {
		if (readerName_4005Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			readerName_4005Parser = parser;
		}
		return readerName_4005Parser;
	}

	/**
	 * @generated
	 */
	private IParser writerName_4006Parser;

	/**
	 * @generated
	 */
	private IParser getWriterName_4006Parser() {
		if (writerName_4006Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			writerName_4006Parser = parser;
		}
		return writerName_4006Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_4007Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_4007Parser() {
		if (componentName_4007Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			componentName_4007Parser = parser;
		}
		return componentName_4007Parser;
	}

	/**
	 * @generated
	 */
	private IParser nodeName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getNodeName_4001Parser() {
		if (nodeName_4001Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			nodeName_4001Parser = parser;
		}
		return nodeName_4001Parser;
	}

	/**
	 * @generated
	 */
	private IParser connectorName_4012Parser;

	/**
	 * @generated
	 */
	private IParser getConnectorName_4012Parser() {
		if (connectorName_4012Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			connectorName_4012Parser = parser;
		}
		return connectorName_4012Parser;
	}

	/**
	 * @generated
	 */
	private IParser readerName_4016Parser;

	/**
	 * @generated
	 */
	private IParser getReaderName_4016Parser() {
		if (readerName_4016Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			readerName_4016Parser = parser;
		}
		return readerName_4016Parser;
	}

	/**
	 * @generated
	 */
	private IParser property_2004Parser;

	/**
	 * @generated
	 */
	private IParser getProperty_2004Parser() {
		if (property_2004Parser == null) {
			EAttribute[] features = new EAttribute[] {
					ReoPackage.eINSTANCE.getProperty_Key(),
					ReoPackage.eINSTANCE.getProperty_Value() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}={1}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}={1}"); //$NON-NLS-1$
			parser.setEditPattern("{0}={1}"); //$NON-NLS-1$
			property_2004Parser = parser;
		}
		return property_2004Parser;
	}

	/**
	 * @generated
	 */
	private IParser sourceEndName_4010Parser;

	/**
	 * @generated
	 */
	private IParser getSourceEndName_4010Parser() {
		if (sourceEndName_4010Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			sourceEndName_4010Parser = parser;
		}
		return sourceEndName_4010Parser;
	}

	/**
	 * @generated
	 */
	private IParser sinkEndName_4011Parser;

	/**
	 * @generated
	 */
	private IParser getSinkEndName_4011Parser() {
		if (sinkEndName_4011Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			sinkEndName_4011Parser = parser;
		}
		return sinkEndName_4011Parser;
	}

	/**
	 * @generated
	 */
	private IParser writerName_4017Parser;

	/**
	 * @generated
	 */
	private IParser getWriterName_4017Parser() {
		if (writerName_4017Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			writerName_4017Parser = parser;
		}
		return writerName_4017Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_4018Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_4018Parser() {
		if (componentName_4018Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getNameable_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			componentName_4018Parser = parser;
		}
		return componentName_4018Parser;
	}

	/**
	 * @generated
	 */
	private IParser syncDrainExpression_4019Parser;

	/**
	 * @generated
	 */
	private IParser getSyncDrainExpression_4019Parser() {
		if (syncDrainExpression_4019Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getDataAware_Expression() };
			EAttribute[] editableFeatures = new EAttribute[] { ReoPackage.eINSTANCE
					.getDataAware_Expression() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			syncDrainExpression_4019Parser = parser;
		}
		return syncDrainExpression_4019Parser;
	}

	/**
	 * @generated
	 */
	private IParser filterExpression_4008Parser;

	/**
	 * @generated
	 */
	private IParser getFilterExpression_4008Parser() {
		if (filterExpression_4008Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getDataAware_Expression() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			filterExpression_4008Parser = parser;
		}
		return filterExpression_4008Parser;
	}

	/**
	 * @generated
	 */
	private IParser transformExpression_4009Parser;

	/**
	 * @generated
	 */
	private IParser getTransformExpression_4009Parser() {
		if (transformExpression_4009Parser == null) {
			EAttribute[] features = new EAttribute[] { ReoPackage.eINSTANCE
					.getDataAware_Expression() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			transformExpression_4009Parser = parser;
		}
		return transformExpression_4009Parser;
	}

	/**
	 * @generated
	 */
	private IParser timerTimeout_4015Parser;

	/**
	 * @generated
	 */
	private IParser getTimerTimeout_4015Parser() {
		if (timerTimeout_4015Parser == null) {
			EAttribute[] features = new EAttribute[] { ChannelsPackage.eINSTANCE
					.getTimer_Timeout() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("t={0}"); //$NON-NLS-1$
			parser.setEditorPattern("t={0}"); //$NON-NLS-1$
			parser.setEditPattern("t={0}"); //$NON-NLS-1$
			timerTimeout_4015Parser = parser;
		}
		return timerTimeout_4015Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ConnectorNameEditPart.VISUAL_ID:
			return getConnectorName_4004Parser();
		case ReaderNameEditPart.VISUAL_ID:
			return getReaderName_4005Parser();
		case WriterNameEditPart.VISUAL_ID:
			return getWriterName_4006Parser();
		case ComponentNameEditPart.VISUAL_ID:
			return getComponentName_4007Parser();
		case NodeNameEditPart.VISUAL_ID:
			return getNodeName_4001Parser();
		case SubConnectorNameEditPart.VISUAL_ID:
			return getConnectorName_4012Parser();
		case InternalReaderNameEditPart.VISUAL_ID:
			return getReaderName_4016Parser();
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2004Parser();
		case SourceEndNameEditPart.VISUAL_ID:
			return getSourceEndName_4010Parser();
		case SinkEndNameEditPart.VISUAL_ID:
			return getSinkEndName_4011Parser();
		case InternalWriterNameEditPart.VISUAL_ID:
			return getWriterName_4017Parser();
		case InternalComponentNameEditPart.VISUAL_ID:
			return getComponentName_4018Parser();
		case SyncDrainExpressionEditPart.VISUAL_ID:
			return getSyncDrainExpression_4019Parser();
		case FilterExpressionEditPart.VISUAL_ID:
			return getFilterExpression_4008Parser();
		case TransformExpressionEditPart.VISUAL_ID:
			return getTransformExpression_4009Parser();
		case TimerTimeoutEditPart.VISUAL_ID:
			return getTimerTimeout_4015Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(ReoVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(ReoVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (ReoElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		@Override
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}
}
