package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ect.codegen.v2.core.descr.ConnectorLib.NodeType;
import org.ect.codegen.v2.core.descr.ConnectorLib.PrimitiveType;
import org.ect.codegen.v2.core.descr.XMLModule.XMLComponentRegistry.XMLComponent;
import org.ect.codegen.v2.core.descr.XMLModule.XMLConnectorRegistry.XMLConnector;
import org.ect.codegen.v2.core.descr.XMLModule.XMLConnectorRegistry.XMLConnector.XMLNodeRegistry.XMLNode;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;

public class XMLModule {

	//
	// FIELDS
	//

	/**
	 * The actual module represented by this module.
	 */
	private final Module module;

	/**
	 * A registry of components.
	 */
	private final XMLComponentRegistry componentRegistry = new XMLComponentRegistry();

	/**
	 * A registry of connectors.
	 */
	private final XMLConnectorRegistry connectorRegistry = new XMLConnectorRegistry();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a module representing the actual module <code>module</code>.
	 * 
	 * @param module
	 *            The module. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>module==null</code>.
	 */
	public XMLModule(final Module module) {
		if (module == null)
			throw new NullPointerException();

		this.module = module;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the components of this module.
	 * 
	 * @return A nonempty collection of components. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasComponents()</code>.
	 * 
	 * @see #hasComponents()
	 */
	public Collection<XMLComponent> getComponents() {
		if (!hasComponents())
			throw new IllegalStateException();

		final Collection<XMLComponent> components = new ArrayList<XMLComponent>();
		for (final Component c : module.getComponents())
			components.add(componentRegistry.addThenGet(c));

		return components;
	}

	/**
	 * Gets the connectors of this module.
	 * 
	 * @return A nonempty collection of connectors. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasConnectors()</code>.
	 * 
	 * @see #hasConnectors()
	 */
	public Collection<XMLConnector> getConnectors() {
		if (!hasConnectors())
			throw new IllegalStateException();

		final Collection<XMLConnector> connectors = new ArrayList<XMLConnector>();
		for (final Connector c : module.getConnectors())
			connectors.add(connectorRegistry.addThenGet(c));

		return connectors;
	}

	/**
	 * Gets the name of this module.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasName()</code>.
	 * 
	 * @see #hasName()
	 */
	public String getName() {
		if (!hasName())
			throw new IllegalStateException();

		return module.getName();
	}

	/**
	 * Checks if this module has components.
	 * 
	 * @return <code>true</code> if this module has components;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasComponents() {
		return module.getComponents() != null
				&& !module.getComponents().isEmpty();
	}

	/**
	 * Checks if this module has connectors.
	 * 
	 * @return <code>true</code> if this module has connectors;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasConnectors() {
		return module.getConnectors() != null
				&& !module.getConnectors().isEmpty();
	}

	/**
	 * Checks if this module has a name.
	 * 
	 * @return <code>true</code> if this module has a name; <code>false</code>
	 *         otherwise.
	 */
	public boolean hasName() {
		return module.getName() != null && !module.getName().isEmpty();
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Gets the representation of the node <code>node</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!isRegistered(node)</code>.
	 * @throws NullPointerException
	 *             If <code>node==null</code>.
	 * 
	 * @see #isRegistered(Node)
	 */
	XMLNode getRepresentationOf(final Node node) {
		if (node == null)
			throw new NullPointerException();

		for (final XMLConnector c : getConnectors())
			if (c.nodeRegistry.contains(node))
				return c.nodeRegistry.get(node);

		throw new IllegalArgumentException();
	}

	/**
	 * Checks if node <code>node</node> is registered in one of the connectors
	 * of this module.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @return <code>true</code> if node is registered; <code>false</code>
	 *         otherwise.
	 */
	boolean isRegistered(final Node node) {
		if (node == null)
			throw new NullPointerException();

		try {
			getRepresentationOf(node);
			return true;
		} catch (final IllegalArgumentException e) {
			return false;
		}
	}

	//
	// CLASSES
	//

	class XMLConnectorRegistry extends XMLRegistry<Connector, XMLConnector> {

		//
		// METHODS
		//

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		XMLConnector newRepresentation(final Connector nameable,
				final long sequenceNumber) {

			return new XMLConnector(nameable, sequenceNumber);
		}

		//
		// CLASSES
		//

		class XMLConnector extends XMLRepresentation<Connector> {

			//
			// FIELDS
			//

			/**
			 * A registry of nodes.
			 */
			private final XMLNodeRegistry nodeRegistry = new XMLNodeRegistry();

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <em>super(node,sequenceNumber)</em>.
			 * 
			 * @see XMLRepresentation#Representation(cwi.reo.Nameable, long)
			 */
			private XMLConnector(final Connector connector, long sequenceNumber) {
				super(connector, sequenceNumber);
			}

			//
			// METHODS
			//

			// /**
			// * Gets a Reo-to-CA converter for this connector.
			// *
			// * @return A Reo-to-CA converter. Not <code>null</code>.
			// * @throws Exception
			// * If something goes wrong while getting.
			// */
			// public Reo2CA getConverter() throws Exception {
			// try {
			// return new Reo2CA(connector);
			// } catch (final Exception e) {
			// throw new Exception();
			// }
			// }

			/**
			 * Gets the nodes of this connector.
			 * 
			 * @return A nonempty set of nodes. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasNodes()</code>.
			 * 
			 * @see #hasNodes()
			 */
			public Set<XMLNode> getNodes() {
				if (!hasNodes())
					throw new IllegalStateException();
				
				final Set<XMLNode> set = new HashSet<XMLNode>();
				for (final Node n : super.nameable.getNodes())
					if (n != null)
						set.add(nodeRegistry.addThenGet(n));

				return set;
			}

			/**
			 * Gets the primitives of this connector.
			 * 
			 * @return A nonempty set of primitives. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasPrimitives()</code>.
			 * 
			 * @see #hasPrimitives()
			 */
			public Set<XMLPrimitive> getPrimitives() {
				if (!hasPrimitives())
					throw new IllegalStateException();

				final Set<XMLPrimitive> set = new HashSet<XMLPrimitive>();
				for (final Primitive p : super.nameable.getPrimitives())
					if (p != null)
						set.add(new XMLPrimitive(p));

				return set;
			}

			/**
			 * Checks if this connector has nodes.
			 * 
			 * @return <code>true</code> if this connector has nodes;
			 *         <code>false</code> otherwise.
			 */
			public boolean hasNodes() {
				return super.nameable.getNodes() != null
						&& !super.nameable.getNodes().isEmpty();
			}

			/**
			 * Checks if this connector has primitives.
			 * 
			 * @return <code>true</code> if this connector has primitives;
			 *         <code>false</code> otherwise.
			 */
			public boolean hasPrimitives() {
				return super.nameable.getPrimitives() != null
						&& !super.nameable.getPrimitives().isEmpty();
			}

			//
			// METHODS - DEFAULT
			//

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			@Override
			String getDefaultNamePrefix() {
				return DEFAULT_CONNECTOR_NAME;
			}

			//
			// CLASSES
			//

			class XMLNodeRegistry extends XMLRegistry<Node, XMLNode> {

				//
				// METHODS
				//

				/**
				 * <em>Inherited documentation:</em>
				 * 
				 * <p>
				 * {@inheritDoc}
				 * </p>
				 */
				@Override
				XMLNode newRepresentation(final Node nameable,
						final long sequenceNumber) {

					return new XMLNode(nameable, sequenceNumber);
				}

				//
				// CLASSES
				//

				class XMLNode extends XMLRepresentation<Node> {

					//
					// CONSTRUCTORS
					//

					/**
					 * Invokes <em>super(node,sequenceNumber)</em>.
					 * 
					 * @see XMLRepresentation#Representation(cwi.reo.Nameable,
					 *      long)
					 */
					private XMLNode(final Node node, final long sequenceNumber) {
						super(node, sequenceNumber);
					}

					//
					// METHODS - PUBLIC
					//

					/**
					 * Counts the sink ends of this node.
					 * 
					 * @return A nonnegative integer.
					 */
					public int countSinkEnds() {
						if (super.nameable.getSinkEnds() == null)
							return 0;

						int n = 0;
						for (final SinkEnd e : super.nameable.getSinkEnds())
							if (!e.isComponentEnd())
								n++;

						return n;
					}

					/**
					 * Counts the source ends of this node.
					 * 
					 * @return A nonnegative integer.
					 */
					public int countSourceEnds() {
						if (super.nameable.getSourceEnds() == null)
							return 0;

						int n = 0;
						for (final SourceEnd e : super.nameable.getSourceEnds())
							if (!e.isComponentEnd())
								n++;

						return n;
					}

					/**
					 * Gets the connector containing this node.
					 * 
					 * @return A connector. Never <code>null</code>.
					 */
					public XMLConnector getConnector() {
						return XMLConnector.this;
					}

					/**
					 * Gets the type of this node.
					 * 
					 * @return A type. Never <code>null</code>.
					 * @throws IllegalStateException
					 *             If <code>!hasType()</code>.
					 * 
					 * @see #hasType()
					 */
					public NodeType getType() {
						if (super.nameable.getType() == null)
							throw new IllegalStateException();

						switch (super.nameable.getType()) {
						case REPLICATE:
							return NodeType.REPLICATOR;
						case ROUTE:
							return NodeType.ROUTER;
						default:
							throw new IllegalStateException();
						}
					}

					/**
					 * Checks if this node has a supported type.
					 * 
					 * @return <code>true</code> if this node has a type;
					 *         <code>false</code> otherwise.
					 */
					public boolean hasType() {
						try {
							getType();
							return true;
						} catch (final IllegalStateException e) {
							return false;
						}
					}

					/**
					 * Checks if this node is a <em>mixed node</em>. In that
					 * case, its has both sink and source ends.
					 * 
					 * <p>
					 * <em>Shorthand for:</em>
					 * </p>
					 * 
					 * <p>
					 * <center>
					 * <code>countSinkEnds()&gt;0&&countSourceEnds()&gt;0</code>
					 * </center>
					 * </p>
					 * 
					 * @return <code>true</code> if this node is a mixed node;
					 *         <code>false</code> otherwise.
					 * 
					 * @see #countSinkEnds()
					 * @see #countSourceEnds()
					 */
					public boolean isMixedNode() {
						return countSinkEnds() > 0 && countSourceEnds() > 0;
					}

					/**
					 * Checks if this node is a <em>source node</em>. In that
					 * case, its has only sink ends.
					 * 
					 * <p>
					 * <em>Shorthand for:</em>
					 * </p>
					 * 
					 * <p>
					 * <center>
					 * <code>countSinkEnds()&gt;0&&countSourceEnds()==0</code>
					 * </center>
					 * </p>
					 * 
					 * @return <code>true</code> if this node is a source node;
					 *         <code>false</code> otherwise.
					 */
					public boolean isSourceNode() {
						return countSinkEnds() > 0 && countSourceEnds() == 0;
					}

					/**
					 * Checks if this node is a <em>sink node</em>. In that
					 * case, its has only sink ends.
					 * 
					 * <p>
					 * <em>Shorthand for:</em>
					 * </p>
					 * 
					 * <p>
					 * <center>
					 * <code>countSinkEnds()==0&&countSourceEnds()&gt;0</code>
					 * </center>
					 * </p>
					 * 
					 * @return <code>true</code> if this node is a source node;
					 *         <code>false</code> otherwise.
					 */
					public boolean isSinkNode() {
						return countSinkEnds() == 0 && countSourceEnds() > 0;
					}

					//
					// METHODS - DEFAULT
					//

					/**
					 * <em>Inherited documentation:</em>
					 * 
					 * <p>
					 * {@inheritDoc}
					 * </p>
					 */
					@Override
					String getDefaultNamePrefix() {
						return DEFAULT_NODE_NAME;
					}
				}
			}

			class XMLPrimitive {

				//
				// FIELDS
				//

				/**
				 * The actual primitive represented by this primitive.
				 */
				private final Primitive primitive;

				//
				// CONSTRUCTORS
				//

				/**
				 * Constructs a primitive representing the primitive
				 * <code>primitive</code>.
				 * 
				 * @param primitive
				 *            The primitive. Not <code>null</code>.
				 * @throws NullPointerException
				 *             If <code>primitive==null</code>.
				 */
				public XMLPrimitive(final Primitive primitive) {
					if (primitive == null)
						throw new NullPointerException();

					this.primitive = primitive;
				}

				//
				// METHODS
				//

				/**
				 * Gets the constraint text of this primitive.
				 * 
				 * @return A string. Never <code>null</code>.
				 */
				public String getConstraintText() {
					if (!hasConstraintText())
						throw new IllegalStateException();

					return ((Filter) primitive).getExpression();
				}

				/**
				 * Gets the item in the buffer of this primitive.
				 * 
				 * @return A string. Never <code>null</code>.
				 * @throws IllegalStateException
				 *             If <code>!hasBufferItem()</code>.
				 */
				public String getBufferItem() {
					if (!hasBufferItem())
						throw new IllegalStateException();

					return ((FIFO) primitive).getInitialValue();
				}

				/**
				 * Gets the nodes of this primitive.
				 * 
				 * @return A nonempty set of nodes. Never <code>null</code>.
				 * @throws IllegalStateException
				 *             If <code>!hasNodes()</code>.
				 * 
				 * @see #hasNodes()
				 */
				public Set<XMLNode> getNodes() {
					if (!hasNodes())
						throw new IllegalStateException();

					final Set<XMLNode> set = new HashSet<XMLNode>();

					if (hasSourceNodes())
						set.addAll(getSourceNodes());
					if (hasSinkNodes())
						set.addAll(getSinkNodes());

					return set;
				}

				/**
				 * Gets the sink nodes of this primitive.
				 * 
				 * @return A nonempty set of nodes. Never <code>null</code>.
				 * @throws IllegalStateException
				 *             If <code>!hasSinkNodes()</code>.
				 * 
				 * @see #hasSinkNodes()
				 */
				public Set<XMLNode> getSinkNodes() {
					if (!hasSinkNodes())
						throw new IllegalStateException();

					final Set<XMLNode> set = new HashSet<XMLNode>();
					for (final SinkEnd e : primitive.getSinkEnds()) {
						final Node node = e.getNode();
						if (node != null)
							set.add(XMLConnector.this.nodeRegistry
									.addThenGet(node));
					}
					return set;
				}

				/**
				 * Gets the source nodes of this primitive
				 * 
				 * @return A nonempty set of nodes. Never <code>null</code>.
				 * @throws IllegalStateException
				 *             If <code>!hasSourceNodes()</code>.
				 * 
				 * @see #hasSourceNodes()
				 */
				public Set<XMLNode> getSourceNodes() {
					if (!hasSourceNodes())
						throw new IllegalStateException();

					final Set<XMLNode> set = new HashSet<XMLNode>();
					for (final SourceEnd e : primitive.getSourceEnds()) {
						final Node node = e.getNode();
						if (node != null)
							set.add(XMLConnector.this.nodeRegistry
									.addThenGet(node));
					}
					return set;
				}

				/**
				 * Gets the type of this primitive.
				 * 
				 * @return A type. Never <code>null</code>.
				 * @throws IllegalStateException
				 *             If <code>!hasType()</code>.
				 * 
				 * @see #hasType()
				 */
				public PrimitiveType getType() {
					if (primitive.getName() == null)
						throw new IllegalStateException();

					if (primitive instanceof AsyncDrain)
						return PrimitiveType.ASYNC_DRAIN;
					if (primitive instanceof AsyncSpout)
						return PrimitiveType.ASYNC_SPOUT;
					if (primitive instanceof FIFO)
						return PrimitiveType.FIFO;
					if (primitive instanceof Filter)
						return PrimitiveType.FILTER;
					if (primitive instanceof LossySync)
						return PrimitiveType.LOSSY_SYNC;
					if (primitive instanceof Sync)
						return PrimitiveType.SYNC;
					if (primitive instanceof SyncDrain)
						return PrimitiveType.SYNC_DRAIN;
					if (primitive instanceof SyncSpout)
						return PrimitiveType.SYNC_SPOUT;

					throw new IllegalStateException();
				}

				/**
				 * Checks if this primitive has a buffer.
				 * 
				 * @return <code>true</code> if this primitive has a buffer;
				 *         <code>false</code> otherwise.
				 */
				public boolean hasBuffer() {
					return primitive instanceof FIFO;
				}

				/**
				 * Checks if this primitive has a buffer item.
				 * 
				 * @return <code>true</code> if this primitive has a buffer;
				 *         <code>false</code> otherwise.
				 */
				public boolean hasBufferItem() {
					return hasBuffer()
							&& ((FIFO) primitive).getInitialValue() != null;
				}

				/**
				 * Checks if this primitive has a constraint text.
				 * 
				 * @return <code>true</code> if this primitive has a constraint
				 *         text; <code>false</code> otherwise.
				 */
				public boolean hasConstraintText() {
					if (primitive instanceof Filter) {
						final String constraintText = ((Filter) primitive)
								.getExpression();
						return constraintText != null
								&& !constraintText.isEmpty();
					} else
						return false;
				}

				/**
				 * Checks if this primitive has an empty buffer.
				 * 
				 * @return <code>true</code> if this primitive has an empty
				 *         buffer; <code>false</code> otherwise.
				 * @throws IllegalStateException
				 *             If <code>!hasBuffer()</code>.
				 * 
				 * @see #hasBuffer()
				 */
				public boolean hasEmptyBuffer() {
					if (!hasBuffer())
						throw new IllegalStateException();

					return !((FIFO) primitive).isFull();
				}

				/**
				 * Checks if this primitive has a full buffer.
				 * 
				 * @return <code>true</code> if this primitive has a full
				 *         buffer; <code>false</code> otherwise.
				 * @throws IllegalStateException
				 *             If <code>!hasBuffer()</code>.
				 * 
				 * @see #hasBuffer()
				 */
				public boolean hasFullBuffer() {
					if (!hasBuffer())
						throw new IllegalStateException();

					return ((FIFO) primitive).isFull();
				}

				/**
				 * Checks if this primitive has nodes.
				 * 
				 * @return <code>true</code> if this primitive has nodes;
				 *         <code>false</code> otherwise.
				 */
				public boolean hasNodes() {
					return hasSinkNodes() || hasSourceNodes();
				}

				/**
				 * Checks if this primitive has sink nodes.
				 * 
				 * @return <code>true</code> if this primitive has sink nodes;
				 *         <code>false</code> otherwise.
				 */
				public boolean hasSinkNodes() {
					if (primitive.getSinkEnds() == null)
						return false;

					for (final SinkEnd e : primitive.getSinkEnds())
						if (e.getNode() != null)
							return true;

					return false;
				}

				/**
				 * Checks if this primitive has source nodes.
				 * 
				 * @return <code>true</code> if this primitive has source nodes;
				 *         <code>false</code> otherwise.
				 */
				public boolean hasSourceNodes() {
					if (primitive.getSourceEnds() == null)
						return false;

					for (final SourceEnd e : primitive.getSourceEnds())
						if (e.getNode() != null)
							return true;

					return false;
				}

				/**
				 * Checks if this primitive has a supported type.
				 * 
				 * @return <code>true</code> if this primitive has a supported
				 *         type; <code>false</code> otherwise.
				 */
				public boolean hasType() {
					try {
						getType();
						return true;
					} catch (final IllegalStateException e) {
						return false;
					}
				}
			}
		}
	}

	class XMLComponentRegistry extends XMLRegistry<Component, XMLComponent> {

		//
		// METHODS
		//

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		XMLComponent newRepresentation(final Component nameable,
				final long sequenceNumber) {

			return new XMLComponent(nameable, sequenceNumber);
		}

		//
		// CLASSES
		//

		class XMLComponent extends XMLRepresentation<Component> {

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <em>super(node,sequenceNumber)</em>.
			 * 
			 * @see XMLRepresentation#Representation(cwi.reo.Nameable, long)
			 */
			private XMLComponent(final Component component, long sequenceNumber) {
				super(component, sequenceNumber);
			}

			//
			// METHODS - PUBLIC
			//

			/**
			 * Gets a map from the names of the linked ends of this component to
			 * the nodes linked to them.
			 * 
			 * @return A map from strings to nodes. Never <code>null</code>.
			 */
			public Map<String, XMLNode> getLinkedNodes() {
				final Map<String, XMLNode> map = new HashMap<String, XMLNode>();
				
				for (final PrimitiveEnd e : super.nameable.getAllEnds()) {
					if (e != null && e.getName() != null
							&& !e.getName().isEmpty() && e.getNode() != null
							&& XMLModule.this.isRegistered(e.getNode()))

						map.put(e.getName(),
								XMLModule.this.getRepresentationOf(e.getNode()));
				}
				
				return map;
			}

			//
			// METHODS - DEFAULT
			//

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			@Override
			String getDefaultNamePrefix() {
				return DEFAULT_COMPONENT_NAME;
			}
		}
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The default name for components.
	 */
	public static final String DEFAULT_COMPONENT_NAME = "Component";

	/**
	 * The default name for connectors.
	 */
	public static final String DEFAULT_CONNECTOR_NAME = "Connector";

	/**
	 * The default name for nodes.
	 */
	public static final String DEFAULT_NODE_NAME = "Node";
}
