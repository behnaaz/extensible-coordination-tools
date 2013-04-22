package org.ect.codegen.v2.proxy.descr.java;

import java.util.Collection;

import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.proxy.SimAutParser;
import org.ect.codegen.v2.proxy.SimAutResource;
import org.ect.codegen.v2.proxy.rt.java.Constants;
import org.ect.codegen.v2.proxy.rt.java.Resource;
import org.ect.codegen.v2.proxy.rt.java.idl.CORBAComponent;
import org.ect.codegen.v2.proxy.rt.java.idl.CORBAInterface;


public class IDLParty extends AbstractParty<CORBAInterface> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an IDL party for the interface named
	 * <code>interfaceName</code> from the IDL resource <code>idlResource</code>
	 * , behaving according to the simulation automaton from the resource
	 * <code>simAutResource</code>.
	 * 
	 * @param interfaceName
	 *            The name of the interface. Not <code>null</code> or empty.
	 * @param idlResource
	 *            The IDL resource. Not <code>null</code>.
	 * @param simAutResource
	 *            The simulation automaton resource. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>interfaceName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>interfaceName==null</code> or
	 *             <code>idlResource==null</code> or
	 *             <code>simAutResource==null</code>.
	 * @throws PartyException
	 *             If something goes wrong while constructing.
	 * 
	 * @see String#isEmpty()
	 */
	public IDLParty(final String interfaceName, final IDLResource idlResource,
			final SimAutResource simAutResource) throws PartyException {

		super(interfaceName, interfaceName, idlResource, simAutResource);
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
	protected Collection<CORBAInterface> extractInterfacesFrom(
			final Resource interfacesResource) throws PartyException {

		if (interfacesResource == null)
			throw new NullPointerException();

		try {
			return new CORBAComponent(interfacesResource).getInterfaces();

		} catch (final Exception e) {
			throw new IDLPartyException(
					IDLPartyException.EXTRACT_INTERFACES_FROM(this,
							interfacesResource), e);
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected SimAutResource getDefaultSimAutResourceFor(
			final CORBAInterface interfaze) throws IDLPartyException {

		if (interfaze == null)
			throw new NullPointerException();

		try {
			/* Declare variables. */
			String sourceState, targetState, vertexName;
			final StringBuilder inputBuilder = new StringBuilder();
			final StringBuilder outputBuilder = new StringBuilder();
			final StringBuilder transitionsBuilder = new StringBuilder();
			int lastStateIndex = 0;

			/* Update $inputBuilder, $outputBuilder, and $transitionsBuilder. */
			for (final String s : interfaze.getOperationNames()) {
				sourceState = "q0";
				targetState = "q" + ++lastStateIndex;
				vertexName = s;

				/* Update builders. */
				inputBuilder.append(vertexName + " ");
				outputBuilder.append(vertexName + Constants.PROXY_VERTEX_NAME_RESULT_SUFFIX
						+ " ");
				transitionsBuilder.append("\n	" + sourceState + " "
						+ vertexName + " " + targetState);
				transitionsBuilder.append("\n	" + targetState + " "
						+ vertexName + Constants.PROXY_VERTEX_NAME_RESULT_SUFFIX + " "
						+ sourceState);
			}

			/* Build the resource text. */
			final StringBuilder builder = new StringBuilder();
			builder.append("begin " + interfaze.getInterfaceName());
			builder.append("\n	input: " + inputBuilder.toString());
			builder.append("\n	output: " + outputBuilder.toString());
			builder.append("\n	init: q0");
			builder.append("\n	" + transitionsBuilder.toString());
			builder.append("\nend");

			return new SimAutResource(builder.toString());

		} catch (final Exception e) {
			if (e instanceof IDLPartyException)
				throw (IDLPartyException) e;

			throw new IDLPartyException(
					IDLPartyException.GET_DEFAULT_SIM_AUT_RESOURCE_FOR(this,
							interfaze), e);
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected void validate(final Connector<DefaultConstraintAutomaton> simAut,
			final CORBAInterface interfaze) throws PartyException {

		if (simAut == null || interfaze == null)
			throw new NullPointerException();

		/* Validate input vertices. */
		for (final Vertex v : simAut.getInputVertices()) {
			final String vertexName = v.getName();
			if (!SimAutParser.checkIsGenuineVertexName(vertexName))
				continue;

			if (!interfaze.hasOperation(vertexName))
				throw new IDLPartyException(
						IDLPartyException.VALIDATE(this, simAut, interfaze),
						"The input vertex named \""
								+ vertexName
								+ "\" corresponds to no operation in the IDL resource \""
								+ super.getInterfacesResource()
										.getCroppedResourceText() + "\".");
		}

		/* Validate output vertices. */
		for (final Vertex v : simAut.getOutputVertices()) {
			final String vertexName = v.getName();
			if (!SimAutParser.checkIsGenuineVertexName(vertexName))
				continue;

			if (!vertexName.endsWith(Constants.PROXY_VERTEX_NAME_RESULT_SUFFIX))
				throw new IDLPartyException(IDLPartyException.VALIDATE(this,
						simAut, interfaze), "The output vertex named \""
						+ vertexName
						+ "\" complies not to the syntax \"<operationName>"
						+ Constants.PROXY_VERTEX_NAME_RESULT_SUFFIX + "\".");

			final String operationName = vertexName.substring(0,
					vertexName.length() - Constants.PROXY_VERTEX_NAME_RESULT_SUFFIX.length());
			if (!interfaze.hasOperation(operationName))
				throw new IDLPartyException(
						IDLPartyException.VALIDATE(this, simAut, interfaze),
						"The output vertex named \""
								+ vertexName
								+ "\" corresponds to the return value of no operation in the IDL resource \""
								+ super.getInterfacesResource()
										.getCroppedResourceText() + "\".");
		}
	}

	//
	// EXCEPTIONS
	//

	public static class IDLPartyException extends PartyException {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected IDLPartyException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		protected IDLPartyException(final String message, final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String EXTRACT_INTERFACES_FROM(final IDLParty thiz,
				final Resource interfacesResource) {

			return PartyException.EXTRACT_INTERFACES_FROM(thiz,
					interfacesResource);
		}

		protected static String GET_DEFAULT_SIM_AUT_RESOURCE_FOR(
				final IDLParty thiz, final CORBAInterface interfaze) {

			return PartyException.GET_DEFAULT_SIM_AUT_RESOURCE_FOR(thiz,
					interfaze);
		}

		protected static String VALIDATE(final IDLParty thiz,
				final Connector<DefaultConstraintAutomaton> simAut,
				final CORBAInterface interfaze) {

			return PartyException.VALIDATE(thiz, simAut, interfaze);
		}
	}
}
