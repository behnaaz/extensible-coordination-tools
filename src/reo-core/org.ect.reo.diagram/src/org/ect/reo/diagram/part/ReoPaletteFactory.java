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
package org.ect.reo.diagram.part;

import java.util.List;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;

import java.util.ArrayList;

import java.util.Collections;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.ect.reo.diagram.providers.ReoElementTypes;

/**
 * @generated
 */
public class ReoPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createConnectors1Group());
		paletteRoot.add(createChannels2Group());
		paletteRoot.add(createIO3Group());
	}

	/**
	 * Creates "Connectors" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnectors1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Connectors1Group_title);
		paletteContainer.setId("createConnectors1Group"); //$NON-NLS-1$
		paletteContainer.add(createConnector1CreationTool());
		paletteContainer.add(createComponent2CreationTool());
		paletteContainer.add(createNode3CreationTool());
		paletteContainer.add(createSourceEnd4CreationTool());
		paletteContainer.add(createSinkEnd5CreationTool());
		paletteContainer.add(createLink6CreationTool());
		paletteContainer.add(createProperty7CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Channels" palette tool group
	 * @generated
	 */
	private PaletteContainer createChannels2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Channels2Group_title);
		paletteContainer.setId("createChannels2Group"); //$NON-NLS-1$
		paletteContainer.add(createSync1CreationTool());
		paletteContainer.add(createLossySync2CreationTool());
		paletteContainer.add(createFIFO3CreationTool());
		paletteContainer.add(createSyncDrain4CreationTool());
		paletteContainer.add(createSyncSpout5CreationTool());
		paletteContainer.add(createAsyncSpout6CreationTool());
		paletteContainer.add(createAsyncDrain7CreationTool());
		paletteContainer.add(createFilter8CreationTool());
		paletteContainer.add(createTransform9CreationTool());
		paletteContainer.add(createTimer10CreationTool());
		paletteContainer.add(createPrioritySync11CreationTool());
		paletteContainer.add(createBlockingSync12CreationTool());
		paletteContainer.add(createBlockingSinkSync13CreationTool());
		paletteContainer.add(createBlockingSourceSync14CreationTool());
		paletteContainer.add(createCustom13CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "I/O" palette tool group
	 * @generated
	 */
	private PaletteContainer createIO3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.IO3Group_title);
		paletteContainer.setId("createIO3Group"); //$NON-NLS-1$
		paletteContainer.add(createReader1CreationTool());
		paletteContainer.add(createWriter2CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnector1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ReoElementTypes.Connector_1001);
		types.add(ReoElementTypes.Connector_2005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Connector1CreationTool_title,
				Messages.Connector1CreationTool_desc, types);
		entry.setId("createConnector1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Connector_1001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComponent2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ReoElementTypes.Component_2010);
		types.add(ReoElementTypes.Component_1004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Component2CreationTool_title,
				Messages.Component2CreationTool_desc, types);
		entry.setId("createComponent2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Component_2010));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNode3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Node3CreationTool_title,
				Messages.Node3CreationTool_desc,
				Collections.singletonList(ReoElementTypes.Node_2001));
		entry.setId("createNode3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Node_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSourceEnd4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.SourceEnd4CreationTool_title,
				Messages.SourceEnd4CreationTool_desc,
				Collections.singletonList(ReoElementTypes.SourceEnd_2006));
		entry.setId("createSourceEnd4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.SourceEnd_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSinkEnd5CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.SinkEnd5CreationTool_title,
				Messages.SinkEnd5CreationTool_desc,
				Collections.singletonList(ReoElementTypes.SinkEnd_2007));
		entry.setId("createSinkEnd5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.SinkEnd_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ReoElementTypes.NodeSourceEnds_3013);
		types.add(ReoElementTypes.SinkEndNode_3014);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Link6CreationTool_title,
				Messages.Link6CreationTool_desc, types);
		entry.setId("createLink6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoDiagramEditorPlugin
				.findImageDescriptor("/org.ect.reo.diagram/icons/obj16/Link.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProperty7CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Property7CreationTool_title,
				Messages.Property7CreationTool_desc,
				Collections.singletonList(ReoElementTypes.Property_2004));
		entry.setId("createProperty7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Property_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSync1CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Sync1CreationTool_title,
				Messages.Sync1CreationTool_desc,
				Collections.singletonList(ReoElementTypes.Sync_3001));
		entry.setId("createSync1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Sync_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLossySync2CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.LossySync2CreationTool_title,
				Messages.LossySync2CreationTool_desc,
				Collections.singletonList(ReoElementTypes.LossySync_3002));
		entry.setId("createLossySync2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.LossySync_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFIFO3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.FIFO3CreationTool_title,
				Messages.FIFO3CreationTool_desc,
				Collections.singletonList(ReoElementTypes.FIFO_3003));
		entry.setId("createFIFO3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.FIFO_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSyncDrain4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.SyncDrain4CreationTool_title,
				Messages.SyncDrain4CreationTool_desc,
				Collections.singletonList(ReoElementTypes.SyncDrain_3005));
		entry.setId("createSyncDrain4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.SyncDrain_3005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSyncSpout5CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.SyncSpout5CreationTool_title,
				Messages.SyncSpout5CreationTool_desc,
				Collections.singletonList(ReoElementTypes.SyncSpout_3006));
		entry.setId("createSyncSpout5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.SyncSpout_3006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAsyncSpout6CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.AsyncSpout6CreationTool_title,
				Messages.AsyncSpout6CreationTool_desc,
				Collections.singletonList(ReoElementTypes.AsyncSpout_3008));
		entry.setId("createAsyncSpout6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.AsyncSpout_3008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAsyncDrain7CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.AsyncDrain7CreationTool_title,
				Messages.AsyncDrain7CreationTool_desc,
				Collections.singletonList(ReoElementTypes.AsyncDrain_3007));
		entry.setId("createAsyncDrain7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.AsyncDrain_3007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFilter8CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Filter8CreationTool_title,
				Messages.Filter8CreationTool_desc,
				Collections.singletonList(ReoElementTypes.Filter_3011));
		entry.setId("createFilter8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Filter_3011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransform9CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Transform9CreationTool_title,
				Messages.Transform9CreationTool_desc,
				Collections.singletonList(ReoElementTypes.Transform_3012));
		entry.setId("createTransform9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Transform_3012));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTimer10CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Timer10CreationTool_title,
				Messages.Timer10CreationTool_desc,
				Collections.singletonList(ReoElementTypes.Timer_3015));
		entry.setId("createTimer10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Timer_3015));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPrioritySync11CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.PrioritySync11CreationTool_title,
				Messages.PrioritySync11CreationTool_desc,
				Collections.singletonList(ReoElementTypes.PrioritySync_3019));
		entry.setId("createPrioritySync11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.PrioritySync_3019));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createBlockingSync12CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.BlockingSync12CreationTool_title,
				Messages.BlockingSync12CreationTool_desc,
				Collections
						.singletonList(ReoElementTypes.BlockingSync_3020));
		entry.setId("createBlockingSync12CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.BlockingSync_3020));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createBlockingSourceSync14CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.BlockingSourceSync14CreationTool_title,
				Messages.BlockingSourceSync14CreationTool_desc,
				Collections
						.singletonList(ReoElementTypes.BlockingSourceSync_3022));
		entry.setId("createBlockingSourceSync14CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.BlockingSourceSync_3022));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	
	/**
	 * @generated
	 */
	private ToolEntry createBlockingSinkSync13CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.BlockingSinkSync13CreationTool_title,
				Messages.BlockingSinkSync13CreationTool_desc,
				Collections
						.singletonList(ReoElementTypes.BlockingSinkSync_3021));
		entry.setId("createBlockingSinkSync13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.BlockingSinkSync_3021));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCustom13CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(3);
		types.add(ReoElementTypes.CustomDirectedChannel_3016);
		types.add(ReoElementTypes.CustomDrainChannel_3017);
		types.add(ReoElementTypes.CustomSpoutChannel_3018);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Custom13CreationTool_title,
				Messages.Custom13CreationTool_desc, types);
		entry.setId("createCustom13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.CustomDirectedChannel_3016));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReader1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ReoElementTypes.Reader_2008);
		types.add(ReoElementTypes.Reader_1002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Reader1CreationTool_title,
				Messages.Reader1CreationTool_desc, types);
		entry.setId("createReader1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Reader_2008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createWriter2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ReoElementTypes.Writer_2009);
		types.add(ReoElementTypes.Writer_1003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Writer2CreationTool_title,
				Messages.Writer2CreationTool_desc, types);
		entry.setId("createWriter2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ReoElementTypes
				.getImageDescriptor(ReoElementTypes.Writer_2009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		@Override
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		@Override
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
