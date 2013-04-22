package org.ect.codegen.v2.proxy.descr.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.axis2.description.AxisMessage;
import org.apache.axis2.description.AxisOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ws.commons.schema.XmlSchemaComplexType;
import org.apache.ws.commons.schema.XmlSchemaSequence;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.proxy.SimAutParser;
import org.ect.codegen.v2.proxy.SimAutResource;
import org.ect.codegen.v2.proxy.rt.java.Resource;
import org.ect.codegen.v2.proxy.rt.java.wsdl.WSDLDirection;
import org.ect.codegen.v2.proxy.rt.java.wsdl.WSDLService;
import org.ect.codegen.v2.proxy.rt.java.wsdl.WSDLServices;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLResource;


public class WSDLParty extends AbstractParty<WSDLService> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a WSDL party for the service named <code>serviceName</code>
	 * from the WSDL resource <code>wsdlResource</code>, behaving according to
	 * the simulation automaton from the resource <code>simAutResource</code>.
	 * 
	 * @param serviceName
	 *            The name of the service. Not <code>null</code> or empty.
	 * @param wsdlResource
	 *            The WSDL resource. Not <code>null</code>.
	 * @param simAutResource
	 *            The simulation automaton resource. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>serviceName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>serviceName==null</code> or
	 *             <code>wsdlResource==null</code> or
	 *             <code>simAutResource==null</code>.
	 * @throws PartyException
	 *             If something goes wrong while constructing.
	 * 
	 * @see String#isEmpty()
	 */
	public WSDLParty(final String serviceName, final WSDLResource wsdlResource,
			final SimAutResource simAutResource) throws PartyException {

		super(serviceName, serviceName, wsdlResource, simAutResource);
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected Collection<WSDLService> extractInterfacesFrom(
			final Resource interfacesResource) throws WSDLPartyException {

		if (interfacesResource == null)
			throw new NullPointerException();

		try {
			return new WSDLServices(interfacesResource).getInterfaces();

		} catch (final Exception e) {
			throw new WSDLPartyException(
					WSDLPartyException.EXTRACT_INTERFACES_FROM(this,
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
			final WSDLService interfaze) throws WSDLPartyException {

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
			if (interfaze.hasOperations())

				/* Iterate over operations. */
				for (final AxisOperation o : interfaze.getOperations()) {
					final String operationName = o.getName().getLocalPart();

					/* Get the messages exchanged for operation $o. */
					final List<AxisMessage> messages = new ArrayList<AxisMessage>();
					if (interfaze.hasMessages(operationName))

						/* Iterate over messages. */
						for (final AxisMessage m : interfaze
								.getMessages(operationName)) {
							try {
								if (((XmlSchemaSequence) ((XmlSchemaComplexType) m
										.getSchemaElement().getSchemaType())
										.getParticle()).getItems().getCount() > 0)

									messages.add(m);

							} catch (final Exception e) {
								/* Ignore $e. */
							}
						}

					/* Process messages. */
					for (int i = 0; i < messages.size(); i++) {
						final AxisMessage m = messages.get(i);
						final String messageName = m.getName();

						/* Add a vertex. */
						vertexName = operationName + "." + messageName;
						if (interfaze.hasDirection(operationName, messageName)) {

							/* Get the direction of message $m. */
							try {
								final WSDLDirection direction = interfaze
										.getDirection(operationName,
												messageName);
								switch (direction) {
								case IN:
									inputBuilder.append(vertexName + " ");
									break;
								case OUT:
									outputBuilder.append(vertexName + " ");
									break;
								default:
									throw new Exception();
								}
							}

							/* Skip operation $o if something goes wrong. */
							catch (final Exception e) {
								break;
							}
						}

						/* Add a transition text. */
						sourceState = i == 0 ? "q0" : "q" + lastStateIndex;
						targetState = i + 1 < messages.size() ? "q"
								+ ++lastStateIndex : "q0";
						transitionsBuilder.append("\n	" + sourceState + " "
								+ vertexName + " " + targetState);
					}
				}

			/* Build the resource text. */
			final StringBuilder builder = new StringBuilder();
			builder.append("begin " + interfaze.getName());
			builder.append("\n	input: " + inputBuilder.toString());
			builder.append("\n	output: " + outputBuilder.toString());
			builder.append("\n	init: q0");
			builder.append("\n	" + transitionsBuilder.toString());
			builder.append("\nend");

			return new SimAutResource(builder.toString());

		} catch (final Exception e) {
			if (e instanceof WSDLPartyException)
				throw (WSDLPartyException) e;

			throw new WSDLPartyException(
					WSDLPartyException.GET_DEFAULT_SIM_AUT_RESOURCE_FOR(this,
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
			final WSDLService interfaze) throws WSDLPartyException {

		if (simAut == null)
			throw new NullPointerException();

		try {
			/* Validate input vertices. */
			for (final Vertex v : simAut.getInputVertices()) {
				final String vertexName = v.getName();
				if (!SimAutParser.checkIsGenuineVertexName(vertexName))
					continue;

				/* Tokenize. */
				final String[] tokens = vertexName.split("\\.");
				if (tokens.length != 2)
					throw new WSDLPartyException(
							WSDLPartyException.VALIDATE(this, simAut, interfaze),
							"The vertex named \""
									+ vertexName
									+ "\" complies not to the syntax \"<operationName>.<messageName>\".");

				/* Get the name of the operation. */
				String operationName = tokens[0];
				if (super.getInterface().hasOperation(
						StringUtils.capitalize(operationName)))
					operationName = StringUtils.capitalize(operationName);
				else if (super.getInterface().hasOperation(
						StringUtils.uncapitalize(operationName)))
					operationName = StringUtils.uncapitalize(operationName);
				else
					throw new WSDLPartyException(WSDLPartyException.VALIDATE(
							this, simAut, interfaze), "No operation named \""
							+ operationName + "\" exists.");

				/* Get the name of the message. */
				final String messageName = tokens[1];
				if (!super.getInterface()
						.hasMessage(operationName, messageName))
					throw new WSDLPartyException(WSDLPartyException.VALIDATE(
							this, simAut, interfaze), "No message named \""
							+ messageName
							+ "\" exists for the operation named \""
							+ operationName + "\".");

				/* Get the direction. */
				if (!super.getInterface().hasDirection(operationName,
						messageName))
					throw new WSDLPartyException(WSDLPartyException.VALIDATE(
							this, simAut, interfaze), "The message named \""
							+ messageName + "\" in the operation named \""
							+ operationName + "\" has no direction.");

				final WSDLDirection direction = super.getInterface()
						.getDirection(operationName, messageName);

				/* Process */
				if (direction == WSDLDirection.OUT)
					throw new WSDLPartyException(
							WSDLPartyException.VALIDATE(this, simAut, interfaze),
							"The input vertex named \""
									+ vertexName
									+ "\" corresponds to the outgoing message named \""
									+ messageName
									+ "\" (of the operation named \""
									+ operationName + "\").");
			}

			/* Validate output vertices. */
			for (final Vertex v : simAut.getOutputVertices()) {
				final String vertexName = v.getName();
				if (!SimAutParser.checkIsGenuineVertexName(vertexName))
					continue;

				/* Tokenize. */
				final String[] tokens = vertexName.split("\\.");
				if (tokens.length != 2)
					throw new WSDLPartyException(
							WSDLPartyException.VALIDATE(this, simAut, interfaze),
							"The vertex named \""
									+ vertexName
									+ "\" complies not to the syntax \"<operationName>.<messageName>\".");

				/* Get the name of the operation. */
				String operationName = tokens[0];
				if (super.getInterface().hasOperation(
						StringUtils.capitalize(operationName)))
					operationName = StringUtils.capitalize(operationName);
				else if (super.getInterface().hasOperation(
						StringUtils.uncapitalize(operationName)))
					operationName = StringUtils.uncapitalize(operationName);
				else
					throw new WSDLPartyException(WSDLPartyException.VALIDATE(
							this, simAut, interfaze), "No operation named \""
							+ operationName + "\" exists.");

				/* Get the name of the message. */
				final String messageName = tokens[1];
				if (!super.getInterface()
						.hasMessage(operationName, messageName))
					throw new WSDLPartyException(WSDLPartyException.VALIDATE(
							this, simAut, interfaze), "No message named \""
							+ messageName
							+ "\" exists for the operation named \""
							+ operationName + "\".");

				/* Get the direction. */
				if (!super.getInterface().hasDirection(operationName,
						messageName))
					throw new WSDLPartyException(WSDLPartyException.VALIDATE(
							this, simAut, interfaze), "The message named \""
							+ messageName + "\" in the operation named \""
							+ operationName + "\" has no direction.");

				final WSDLDirection direction = super.getInterface()
						.getDirection(operationName, messageName);

				/* Process. */
				if (direction == WSDLDirection.IN)
					throw new WSDLPartyException(
							WSDLPartyException.VALIDATE(this, simAut, interfaze),
							"The output vertex named \""
									+ vertexName
									+ "\" corresponds to the incoming message named \""
									+ messageName
									+ "\" (of the operation named \""
									+ operationName + "\").");
			}

		} catch (final Exception e) {
			if (e instanceof WSDLPartyException)
				throw (WSDLPartyException) e;

			throw new WSDLPartyException(WSDLPartyException.VALIDATE(this,
					simAut, interfaze), e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class WSDLPartyException extends PartyException {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected WSDLPartyException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		protected WSDLPartyException(final String message, final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String EXTRACT_INTERFACES_FROM(final WSDLParty thiz,
				final Resource interfacesResource) {

			return PartyException.EXTRACT_INTERFACES_FROM(thiz,
					interfacesResource);
		}

		protected static String GET_DEFAULT_SIM_AUT_RESOURCE_FOR(
				final WSDLParty thiz, final WSDLService interfaze) {

			return PartyException.GET_DEFAULT_SIM_AUT_RESOURCE_FOR(thiz,
					interfaze);
		}

		protected static String VALIDATE(final WSDLParty thiz,
				final Connector<DefaultConstraintAutomaton> simAut,
				final WSDLService interfaze) {

			return PartyException.VALIDATE(thiz, simAut, interfaze);
		}
	}
}
