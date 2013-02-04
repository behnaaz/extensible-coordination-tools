package org.ect.ea.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.ect.ea.diagram.providers.AutomataElementTypes;


/**
 * @generated
 */
public class AutomataPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createEa1Group());
	}

	/**
	 * Creates "ea" palette tool group
	 * @generated
	 */
	private PaletteContainer createEa1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Ea1Group_title);
		paletteContainer.add(createAutomaton1CreationTool());
		paletteContainer.add(createState2CreationTool());
		paletteContainer.add(createTransition3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAutomaton1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(AutomataElementTypes.Automaton_1001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Automaton1CreationTool_title,
				Messages.Automaton1CreationTool_desc, types);
		entry.setSmallIcon(AutomataElementTypes
				.getImageDescriptor(AutomataElementTypes.Automaton_1001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createState2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(AutomataElementTypes.State_2001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.State2CreationTool_title,
				Messages.State2CreationTool_desc, types);
		entry.setSmallIcon(AutomataElementTypes
				.getImageDescriptor(AutomataElementTypes.State_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransition3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(AutomataElementTypes.Transition_3001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Transition3CreationTool_title,
				Messages.Transition3CreationTool_desc, types);
		entry.setSmallIcon(AutomataElementTypes
				.getImageDescriptor(AutomataElementTypes.Transition_3001));
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
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
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
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
