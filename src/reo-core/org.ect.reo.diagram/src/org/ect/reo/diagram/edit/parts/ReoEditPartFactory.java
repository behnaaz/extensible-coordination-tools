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

package org.ect.reo.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.view.util.ReconfEditPartMonitor;


/**
 * @generated
 */
public class ReoEditPartFactory implements EditPartFactory {

	/**
	 * Reconf monitor.
	 * @generated NOT
	 */
	private ReconfEditPartMonitor reconfMonitor = new ReconfEditPartMonitor();

	/**
	 * Add some adapters to the edit parts.
	 * @generated NOT
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart result = createEditPartGen(context, model);
		// Add the monitor.
		if (result instanceof IGraphicalEditPart) {
			reconfMonitor.monitor((IGraphicalEditPart) result, true);
		}
		return result;
	}

	/**
	 * @generated
	 */
	public EditPart createEditPartGen(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ReoVisualIDRegistry.getVisualID(view)) {

			case ModuleEditPart.VISUAL_ID:
				return new ModuleEditPart(view);

			case ConnectorEditPart.VISUAL_ID:
				return new ConnectorEditPart(view);

			case ConnectorNameEditPart.VISUAL_ID:
				return new ConnectorNameEditPart(view);

			case ReaderEditPart.VISUAL_ID:
				return new ReaderEditPart(view);

			case ReaderNameEditPart.VISUAL_ID:
				return new ReaderNameEditPart(view);

			case WriterEditPart.VISUAL_ID:
				return new WriterEditPart(view);

			case WriterNameEditPart.VISUAL_ID:
				return new WriterNameEditPart(view);

			case ComponentEditPart.VISUAL_ID:
				return new ComponentEditPart(view);

			case ComponentNameEditPart.VISUAL_ID:
				return new ComponentNameEditPart(view);

			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);

			case NodeNameEditPart.VISUAL_ID:
				return new NodeNameEditPart(view);

			case SubConnectorEditPart.VISUAL_ID:
				return new SubConnectorEditPart(view);

			case SubConnectorNameEditPart.VISUAL_ID:
				return new SubConnectorNameEditPart(view);

			case InternalReaderEditPart.VISUAL_ID:
				return new InternalReaderEditPart(view);

			case InternalReaderNameEditPart.VISUAL_ID:
				return new InternalReaderNameEditPart(view);

			case PropertyEditPart.VISUAL_ID:
				return new PropertyEditPart(view);

			case SourceEndEditPart.VISUAL_ID:
				return new SourceEndEditPart(view);

			case SourceEndNameEditPart.VISUAL_ID:
				return new SourceEndNameEditPart(view);

			case SinkEndEditPart.VISUAL_ID:
				return new SinkEndEditPart(view);

			case SinkEndNameEditPart.VISUAL_ID:
				return new SinkEndNameEditPart(view);

			case InternalWriterEditPart.VISUAL_ID:
				return new InternalWriterEditPart(view);

			case InternalWriterNameEditPart.VISUAL_ID:
				return new InternalWriterNameEditPart(view);

			case InternalComponentEditPart.VISUAL_ID:
				return new InternalComponentEditPart(view);

			case InternalComponentNameEditPart.VISUAL_ID:
				return new InternalComponentNameEditPart(view);

			case ConnectorCompartmentEditPart.VISUAL_ID:
				return new ConnectorCompartmentEditPart(view);

			case SubConnectorCompartmentEditPart.VISUAL_ID:
				return new SubConnectorCompartmentEditPart(view);

			case InternalReaderCompartmentEditPart.VISUAL_ID:
				return new InternalReaderCompartmentEditPart(view);

			case InternalWriterCompartmentEditPart.VISUAL_ID:
				return new InternalWriterCompartmentEditPart(view);

			case InternalComponentCompartmentEditPart.VISUAL_ID:
				return new InternalComponentCompartmentEditPart(view);

			case ReaderCompartmentEditPart.VISUAL_ID:
				return new ReaderCompartmentEditPart(view);

			case WriterCompartmentEditPart.VISUAL_ID:
				return new WriterCompartmentEditPart(view);

			case ComponentCompartmentEditPart.VISUAL_ID:
				return new ComponentCompartmentEditPart(view);

			case SyncEditPart.VISUAL_ID:
				return new SyncEditPart(view);

			case LossySyncEditPart.VISUAL_ID:
				return new LossySyncEditPart(view);

			case FIFOEditPart.VISUAL_ID:
				return new FIFOEditPart(view);

			case SyncDrainEditPart.VISUAL_ID:
				return new SyncDrainEditPart(view);

			case SyncDrainExpressionEditPart.VISUAL_ID:
				return new SyncDrainExpressionEditPart(view);

			case SyncSpoutEditPart.VISUAL_ID:
				return new SyncSpoutEditPart(view);

			case AsyncDrainEditPart.VISUAL_ID:
				return new AsyncDrainEditPart(view);

			case AsyncSpoutEditPart.VISUAL_ID:
				return new AsyncSpoutEditPart(view);

			case FilterEditPart.VISUAL_ID:
				return new FilterEditPart(view);

			case FilterExpressionEditPart.VISUAL_ID:
				return new FilterExpressionEditPart(view);

			case TransformEditPart.VISUAL_ID:
				return new TransformEditPart(view);

			case TransformExpressionEditPart.VISUAL_ID:
				return new TransformExpressionEditPart(view);

			case TimerEditPart.VISUAL_ID:
				return new TimerEditPart(view);

			case TimerTimeoutEditPart.VISUAL_ID:
				return new TimerTimeoutEditPart(view);

			case NodeSourceEndsEditPart.VISUAL_ID:
				return new NodeSourceEndsEditPart(view);

			case SinkEndNodeEditPart.VISUAL_ID:
				return new SinkEndNodeEditPart(view);

			case CustomDirectedChannelEditPart.VISUAL_ID:
				return new CustomDirectedChannelEditPart(view);

			case CustomDrainChannelEditPart.VISUAL_ID:
				return new CustomDrainChannelEditPart(view);

			case CustomSpoutChannelEditPart.VISUAL_ID:
				return new CustomSpoutChannelEditPart(view);

			case PrioritySyncEditPart.VISUAL_ID:
				return new PrioritySyncEditPart(view);

			case BlockingSyncEditPart.VISUAL_ID:
				return new BlockingSyncEditPart(view);

			case BlockingSinkSyncEditPart.VISUAL_ID:
				return new BlockingSinkSyncEditPart(view);

			case BlockingSourceSyncEditPart.VISUAL_ID:
				return new BlockingSourceSyncEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn()
						&& getWrapLabel().getText().length() > 0) {
					rect.setSize(new Dimension(text.computeSize(rect.width,
							SWT.DEFAULT)));
				} else {
					int avr = FigureUtilities.getFontMetrics(text.getFont())
							.getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
							SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}

	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
