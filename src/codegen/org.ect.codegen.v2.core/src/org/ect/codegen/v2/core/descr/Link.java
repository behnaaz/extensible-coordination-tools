package org.ect.codegen.v2.core.descr;

import java.util.Collection;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public class Link {

	private final String componentName;

	private final String componentVertexName;

	private final String connectorName;

	private final String connectorVertexName;

	public Link(final String connectorName, final String connectorVertexName,
			final String componentName, final String componentVertexName) {

		if (connectorName == null || connectorVertexName == null
				|| componentName == null || componentVertexName == null)
			throw new NullPointerException();
		if (connectorName.isEmpty() || connectorVertexName.isEmpty()
				|| componentName.isEmpty() || componentVertexName.isEmpty())
			throw new IllegalArgumentException();

		this.connectorName = connectorName;
		this.connectorVertexName = connectorVertexName;
		this.componentName = componentName;
		this.componentVertexName = componentVertexName;
	}

	public String getComponentName() {
		return componentName;
	}

	public String getComponentVertexName() {
		return componentVertexName;
	}

	public String getConnectorName() {
		return connectorName;
	}

	public String getConnectorVertexName() {
		return connectorVertexName;
	}

	public <E extends Entity> ResolvedLink<E> resolve(
			final Collection<? extends Composition<?, ?>> connectors,
			final Collection<E> components) throws Exception {

		if (connectors == null || components == null
				|| connectors.contains(null) || components.contains(null))
			throw new NullPointerException();

		Composition<?, ?> connector = null;
		for (final Composition<?, ?> c : connectors)
			if (c.getName().equals(connectorName)) {
				connector = c;
				break;
			}

		if (connector == null)
			throw new Exception(); // TODO: Throw proper exception.

		if (!connector.hasVertex(connectorVertexName))
			throw new Exception(); // TODO: Throw proper exception.

		final Vertex connectorVertex = connector.getVertex(connectorVertexName);

		E component = null;
		for (final E e : components)
			if (e.getName().equals(componentName)) {
				component = e;
				break;
			}

		if (component == null)
			throw new Exception(); // TODO: Throw proper exception.

		if (!component.hasVertex(componentVertexName)) {
			System.out.println(componentVertexName);
			throw new Exception(); // TODO: Throw proper exception.
		}

		final Vertex componentVertex = component.getVertex(componentVertexName);

		return new ResolvedLink<E>(connector, connectorVertex, component,
				componentVertex);
	}

	@Override
	public String toString() {
		return "{" + connectorName + "}" + connectorVertexName + " -- {"
				+ componentName + "}" + componentVertexName;
	}
}
