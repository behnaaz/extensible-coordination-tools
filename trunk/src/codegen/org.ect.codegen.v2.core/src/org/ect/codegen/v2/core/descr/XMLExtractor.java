package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.core.descr.XMLModule.XMLComponentRegistry.XMLComponent;
import org.ect.codegen.v2.core.descr.XMLModule.XMLConnectorRegistry.XMLConnector;
import org.ect.codegen.v2.core.descr.XMLModule.XMLConnectorRegistry.XMLConnector.XMLNodeRegistry.XMLNode;
import org.ect.codegen.v2.core.descr.XMLModule.XMLConnectorRegistry.XMLConnector.XMLPrimitive;

public class XMLExtractor {

	//
	// CONSTRUCTORS
	//

	/**
	 * Deprecates the constructor.
	 * 
	 * @deprecated Use this class in a static way.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	private XMLExtractor() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The connector library.
	 */
	private static ConnectorLib<DefaultConstraintAutomaton> library = new ConnectorLib<DefaultConstraintAutomaton>(
			new DefaultConstraintAutomatonLibrary());

	//
	// STATIC - METHODS
	//

	/**
	 * Extracts the links between the components and the connectors of the
	 * module <code>module</code> from that module.
	 * 
	 * @param module
	 *            The module. Not <code>null</code>.
	 * @return A collection of links. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>module==null</code>.
	 */
	public static Collection<Link> extractLinksFrom(final XMLModule module) {
		if (module == null)
			throw new NullPointerException();

		final Collection<Link> links = new ArrayList<Link>();

		String connectorName, connectorVertexName, componentName, componentVertexName;
		
		for (final XMLConnector c : module.getConnectors())
			c.getNodes();
		
		for (final XMLComponent c : module.getComponents()) {
			for (final Entry<String, XMLNode> e : c.getLinkedNodes().entrySet()) {

				connectorName = e.getValue().getConnector().getName();
				connectorVertexName = e.getValue().getName();
				componentName = c.getName();
				componentVertexName = e.getKey();
				
				links.add(new Link(connectorName, connectorVertexName,
						componentName, componentVertexName));
			}
		}
	
		return links;
	}

	/**
	 * Tries to extract compositions of nodes and primitives from the connectors
	 * of the module <code>module</code>, throwing an exception if something
	 * goes wrong.
	 * 
	 * @param connector
	 *            The connector. Not <code>null</code>.
	 * @return A nonempty list of compositions. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>connector==null</code>.
	 * @throws XMLExtractorException
	 *             If something goes wrong while extracting.
	 */
	public static Collection<Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> tryExtractFrom(
			final XMLModule module) throws XMLExtractorException {

		if (module == null)
			throw new NullPointerException();

		try {
			if (!module.hasConnectors())
				throw new XMLExtractorException(
						"Every module should contain at least one connector.");

			final List<Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> compositions = new ArrayList<Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>>();
			for (final XMLConnector c : module.getConnectors()) 
				compositions.add(tryExtractFrom(c));

			return compositions;
		}

		catch (final Exception e) {
			throw new XMLExtractorException(
					"I failed to extract compositions from "
							+ (module.hasName() ? "the module named \""
									+ module.getName() + "\""
									: "a nameless module") + ".", e);
		}
	}

	/**
	 * Tries to extract a composition of nodes and primitives from the connector
	 * <code>connector</code>, throwing an exception if something goes wrong.
	 * 
	 * @param connector
	 *            The connector. Not <code>null</code>.
	 * @return A composition. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>connector==null</code>.
	 * @throws XMLExtractorException
	 *             If something goes wrong while extracting.
	 */
	public static Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> tryExtractFrom(
			final XMLConnector connector) throws XMLExtractorException {

		if (connector == null)
			throw new NullPointerException();

		try {
			/* Extract node connectors. */
			if (!connector.hasNodes())
				throw new XMLExtractorException(
						"Every connector should consist of at least one node.");

			final Set<String> nodeNames = new HashSet<String>();
			for (final XMLNode n : connector.getNodes())
				nodeNames.add(n.getName());

			final List<Connector<DefaultConstraintAutomaton>> nodeConnectors = new ArrayList<Connector<DefaultConstraintAutomaton>>();
			for (final XMLNode n : connector.getNodes())
				nodeConnectors.add(tryExtractFrom(n));

			/* Extract primitive connectors. */
			if (!connector.hasPrimitives())
				throw new XMLExtractorException(
						"Every connector should consist of at least one primitive.");

			final List<Connector<DefaultConstraintAutomaton>> primitiveConnectors = new ArrayList<Connector<DefaultConstraintAutomaton>>();
			for (final XMLPrimitive p : connector.getPrimitives())
				primitiveConnectors.add(tryExtractFrom(p));

			/* Match node connectors with primitive connectors. */
			final Map<String, Iterator<Vertex>> nodeNamesToInputVertices = new HashMap<String, Iterator<Vertex>>();
			final Map<String, Iterator<Vertex>> nodeNamesToOutputVertices = new HashMap<String, Iterator<Vertex>>();

			for (final Connector<DefaultConstraintAutomaton> c : nodeConnectors) {
				final String nodeName = c.getName();

				nodeNamesToInputVertices.put(nodeName, c.getInputVertices()
						.iterator());

				nodeNamesToOutputVertices.put(nodeName, c.getOutputVertices()
						.iterator());
			}

			for (final Connector<DefaultConstraintAutomaton> c : primitiveConnectors)
				for (final Vertex v : c.getVertices())
					if (v.isInputVertex() || v.isOutputVertex()) {
						final String nodeName = v.getName();
						final Iterator<Vertex> iterator = v.isInputVertex() ? nodeNamesToOutputVertices
								.get(nodeName) : nodeNamesToInputVertices
								.get(nodeName);

						String newVertexName = null;
						while (iterator.hasNext()
								&& nodeNames.contains(newVertexName = iterator
										.next().getName()))
							;

						if (newVertexName == null
								|| nodeNames.contains(newVertexName))
							throw new XMLExtractorException("The node named \""
									+ nodeName + "\" has too few ends.");

						c.getBehavior()
								.renameVertex(v.getName(), newVertexName);
						// v.rename(newVertexName);
					}

			/* Validate matching. */
			// final List<Entry<String, Iterator<Vertex>>> nodeNamesToVertices =
			// new ArrayList<Map.Entry<String, Iterator<Vertex>>>();
			// nodeNamesToVertices.addAll(nodeNamesToInputVertices.entrySet());
			// nodeNamesToVertices.addAll(nodeNamesToOutputVertices.entrySet());
			//
			// for (final Entry<String, Iterator<Vertex>> e :
			// nodeNamesToVertices)
			// if (e.getValue().hasNext())
			// throw new XMLExtractorException("The node named \""
			// + e.getKey() + "\" has too many ends.");

			/* Return. */
			final List<Connector<DefaultConstraintAutomaton>> connectors = new ArrayList<Connector<DefaultConstraintAutomaton>>();
			connectors.addAll(nodeConnectors);
			connectors.addAll(primitiveConnectors);

			final Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> composition = new Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>(
					connector.getName());

			for (final Connector<DefaultConstraintAutomaton> c : connectors)
				composition.addConnector(c);

			return composition;
		}

		catch (final Exception e) {
			throw new XMLExtractorException(
					"I failed to extract a composition from the connector named \""
							+ connector.getName() + "\".", e);
		}
	}

	/**
	 * Tries to extract a node connector from the node <code>node</code>,
	 * throwing an exception if something goes wrong.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>node==null</code>.
	 * @throws XMLExtractorException
	 *             If something goes wrong while extracting.
	 */
	public static Connector<DefaultConstraintAutomaton> tryExtractFrom(
			final XMLNode node) throws XMLExtractorException {

		if (node == null)
			throw new NullPointerException();

		try {
			if (!node.hasType())
				throw new XMLExtractorException(
						"Every node should have a supported type.");

			return library.newNode(node.getName(), node.countSinkEnds(),
					node.countSourceEnds(), node.getType());
		}

		catch (final Exception e) {
			throw new XMLExtractorException(
					"I failed to extract a connector from the node named \""
							+ node.getName() + "\".", e);
		}
	}

	/**
	 * Tries to extract a primitive connector from the primitive
	 * <code>primitive</code>, throwing an exception if something goes wrong.
	 * 
	 * @param primitive
	 *            The primitive. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>primitive==null</code>.
	 * @throws XMLExtractorException
	 *             If something goes wrong while extracting.
	 */
	public static Connector<DefaultConstraintAutomaton> tryExtractFrom(
			final XMLPrimitive primitive) throws XMLExtractorException {

		if (primitive == null)
			throw new NullPointerException();

		try {
			if (!primitive.hasNodes())
				throw new XMLExtractorException(
						"Every primitive should connect to at least one node.");

			if (!primitive.hasType())
				throw new XMLExtractorException(
						"Every primitive should have a supported type.");

			/* Get relevant source nodes and sink nodes. */
			Collection<XMLNode> sourceNodes = Collections.emptyList();
			Collection<XMLNode> sinkNodes = Collections.emptyList();
			switch (primitive.getType()) {
			case ASYNC_DRAIN:
			case SYNC_DRAIN:
				if (!primitive.hasSourceNodes()
						|| (sourceNodes = primitive.getSourceNodes()).size() > 2)

					throw new XMLExtractorException(
							"Every AsyncDrain and SyncDrain should connect to one or two source nodes.");
				break;

			case ASYNC_SPOUT:
			case SYNC_SPOUT:
				if (!primitive.hasSinkNodes()
						|| (sinkNodes = primitive.getSinkNodes()).size() > 2)

					throw new XMLExtractorException(
							"Every AsyncSpout and SyncSpout should connect to one or two sink nodes.");
				break;

			default:
				if (!primitive.hasSourceNodes()
						|| !primitive.hasSinkNodes()
						|| (sourceNodes = primitive.getSourceNodes()).size() != 1
						|| (sinkNodes = primitive.getSinkNodes()).size() != 1)

					throw new XMLExtractorException(
							"Every FIFO, Filter, LossySync, and Sync should connect one source node and one sink node.");
			}

			/* Get node names. */
			final List<XMLNode> nodes = new ArrayList<XMLNode>();
			nodes.addAll(sourceNodes);
			nodes.addAll(sinkNodes);
			final Iterator<XMLNode> iterator = nodes.iterator();

			final String nodeName1 = iterator.next().getName();
			final String nodeName2 = iterator.hasNext() ? iterator.next()
					.getName() : nodeName1;

			/* Return. */
			switch (primitive.getType()) {
			case ASYNC_DRAIN:
				return library.newAsyncDrain(nodeName1, nodeName2);
			case ASYNC_SPOUT:
				return library.newAsyncSpout(nodeName1, nodeName2);
			case FIFO:
				return library.newFIFO(nodeName1, nodeName2, primitive
						.hasFullBuffer(),
						primitive.hasBufferItem() ? primitive.getBufferItem()
								: "null");
			case FILTER:
				return library.newFilter(nodeName1, nodeName2, primitive
						.hasConstraintText() ? primitive.getConstraintText()
						: "true");
			case LOSSY_SYNC:
				return library.newLossySync(nodeName1, nodeName2);
			case SYNC:
				return library.newSync(nodeName1, nodeName2);
			case SYNC_DRAIN:
				return library.newSyncDrain(nodeName1, nodeName2);
			case SYNC_SPOUT:
				return library.newSyncSpout(nodeName1, nodeName2);
			default:
				throw new XMLExtractorException(
						"Every primitive should have a supported type.");
			}
		}

		catch (final Exception e) {
			throw new XMLExtractorException(
					"I failed to extract a connector from "
							+ (primitive.hasNodes() ? "the primitive between nodes \""
									+ primitive.getNodes() + "\""
									: "an empty primitive") + ".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class XMLExtractorException extends Exception {
		private static final long serialVersionUID = 1L;

		private XMLExtractorException(final String message) {
			super(message);
		}

		private XMLExtractorException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
