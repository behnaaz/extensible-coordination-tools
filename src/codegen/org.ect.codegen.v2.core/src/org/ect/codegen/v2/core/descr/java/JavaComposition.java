package org.ect.codegen.v2.core.descr.java;

import org.apache.commons.lang3.StringUtils;
import org.ect.codegen.v2.core.descr.Behavior;
import org.ect.codegen.v2.core.descr.Composition;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.java.JavaConnector.JavaConnectorException;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory.JavaIdentifier;

public class JavaComposition<C extends JavaConnector<B>, B extends Behavior<B>>
		extends Composition<C, B> implements JavaClass {

	//
	// FIELDS
	//

	/**
	 * The class name associated with this composition.
	 */
	private JavaIdentifier className;

	/**
	 * A factory for Java identifiers.
	 */
	private final JavaIdentifierFactory identifierFactory;

	/**
	 * The instance name associated with this composition.
	 */
	private JavaIdentifier instanceName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an empty composition with Java identifiers.
	 * 
	 * @param name
	 *            The name of the composition to construct. Not
	 *            <code>null</code> or empty.
	 * @param identifierFactory
	 *            A factory for Java identifiers used by the composition to
	 *            construct. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or
	 *             <code>identifierFactory==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	private JavaComposition(final String name,
			final JavaIdentifierFactory identifierFactory) {

		super(name);
		if (identifierFactory == null)
			throw new NullPointerException();

		this.identifierFactory = identifierFactory;
	}

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
	public JavaIdentifier getClassName() {
		return className == null ? className = identifierFactory
				.constructOrGet(StringUtils.capitalize(super.getName()))
				: className;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public JavaIdentifier getInstanceName() {
		return instanceName == null ? instanceName = identifierFactory
				.constructOrGet(super.getName()) : instanceName;
	}

	//
	// STATIC
	//

	/**
	 * Constructs a composition with Java identifiers based on the prototype
	 * <code>prototype</code>.
	 * 
	 * @param prototype
	 *            The prototype. Not <code>null</code>.
	 * @param identifierFactory
	 *            A factory for Java identifiers. Not <code>null</code>.
	 * @return A composition. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>prototype==null</code> or
	 *             <code>identifierFactory==null</code>.
	 */
	public static JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> newInstance(
			final Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> prototype,
			final JavaIdentifierFactory identifierFactory)
			throws JavaConnectorException {

		if (prototype == null || identifierFactory == null)
			throw new NullPointerException();

		/* Construct a composition with Java identifiers. */
		final JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> composition = new JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>(
				prototype.getName(), identifierFactory);

		/* Add connectors with Java identifiers to $composition. */
		for (final Connector<DefaultConstraintAutomaton> c : prototype)
			composition.addConnector(JavaConnector.newInstance(c,
					identifierFactory));

		/* Return. */
		return composition;
	}
}
