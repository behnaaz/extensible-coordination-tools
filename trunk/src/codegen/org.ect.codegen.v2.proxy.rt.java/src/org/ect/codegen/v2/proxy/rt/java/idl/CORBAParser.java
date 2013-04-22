package org.ect.codegen.v2.proxy.rt.java.idl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.corba.idl.parser.IDLLexer;
import org.apache.axis2.corba.idl.parser.IDLParser;
import org.apache.axis2.corba.idl.parser.IDLVisitor;
import org.apache.axis2.corba.idl.types.IDL;
import org.apache.axis2.corba.idl.types.Interface;
import org.ect.codegen.v2.proxy.rt.java.Resource;

import antlr.RecognitionException;

public class CORBAParser {

	//
	// CONSTRUCTORS
	//

	/**
	 * Hides the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private CORBAParser() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Parses the resource <code>idlResource</code> as an IDL resource.
	 * 
	 * @param idlResource
	 *            The resource. Not <code>null</code>.
	 * @return A nonempty map from names to IDL interfaces. Never
	 *         <code>null</code>.
	 * @throws CORBAParserException
	 *             If something goes wrong before or while parsing.
	 * @throws NullPointerException
	 *             If <code>idlResource==null</code>.
	 */
	public static Map<String, CORBAInterface> parse(final Resource idlResource)
			throws CORBAParserException {

		if (idlResource == null)
			throw new NullPointerException();

		/* Prepare an exception message. */
		final String message = "I cannot parse the resource \""
				+ idlResource.getCroppedResourceText()
				+ "\" as an IDL resource.";

		/* Parse $idlResource. */
		final IDL idl;
		try {

			/* Get a reader. */
			final Reader reader = new InputStreamReader(
					idlResource.newInputStream());

			/* Get a parser. */
			final IDLParser parser = new IDLParser(new IDLLexer(reader)) {
				@Override
				public void reportError(RecognitionException arg0) {
				}

				@Override
				public void reportError(String arg0) {
				}

				@Override
				public void reportWarning(String arg0) {
				}
			};

			/* Parse. */
			parser.specification();
			final IDLVisitor visitor = new IDLVisitor();
			visitor.visit(parser.getAST());
			idl = visitor.getIDL();
		} catch (Exception e) {
			throw new CORBAParserException(message, e);
		}

		if (idl == null || idl.getInterfaces() == null)
			throw new CORBAParserException(message);

		/* Return. */
		final Map<String, CORBAInterface> namesToInterfaces = new HashMap<String, CORBAInterface>();
		for (final Object o : idl.getInterfaces().keySet()) {
			final Object oo = idl.getInterfaces().get(o);
			if (o instanceof String && oo instanceof Interface) {
				final String interfaceName = (String) o;
				final Interface interfaze = (Interface) oo;

				namesToInterfaces.put(interfaceName, new CORBAInterface(
						interfaceName, interfaze));
			}
		}

		if (namesToInterfaces.isEmpty())
			throw new CORBAParserException("The IDL resource \"" + idlResource
					+ "\" contains no valid interface definitions.");

		return namesToInterfaces;
	}

	//
	// STATIC - EXCEPTIONS
	//

	public static class CORBAParserException extends Exception {
		private static final long serialVersionUID = 1L;

		private CORBAParserException(String message) {
			super(message);
		}

		private CORBAParserException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}