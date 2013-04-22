package org.ect.codegen.v2.core.descr;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public class ResolvedLink<E extends Entity> extends Link {

	private final QVertex<E> qComponentVertex;

	private final QVertex<Composition<?, ?>> qConnectorVertex;

	public ResolvedLink(final Composition<?, ?> connector,
			final Vertex connectorVertex, final E component,
			final Vertex componentVertex) {

		super(connector.getName(), connectorVertex.getName(), component
				.getName(), componentVertex.getName());

		if (!component.hasVertex(componentVertex.getName())
				|| !connector.hasVertex(connectorVertex.getName()))
			throw new IllegalArgumentException();

		this.qComponentVertex = new QVertex<E>(component, componentVertex);
		this.qConnectorVertex = new QVertex<Composition<?, ?>>(connector,
				connectorVertex);
	}

	public E getComponent() {
		return qComponentVertex.getEntity();
	}

	public Vertex getComponentVertex() {
		return qComponentVertex.getVertex();
	}

	public Composition<?, ?> getConnector() {
		return qConnectorVertex.getEntity();
	}

	public Vertex getConnectorVertex() {
		return qConnectorVertex.getVertex();
	}

	public QVertex<E> getQComponentVertex() {
		return qComponentVertex;
	}

	public QVertex<Composition<?, ?>> getQConnectorVertex() {
		return qConnectorVertex;
	}
}
