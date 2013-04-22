package org.ect.codegen.v2.orch.gen.java;

import java.util.ArrayList;
import java.util.Collection;

import org.ect.codegen.v2.core.gen.ModuleResource;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgramArguments;
import org.ect.reo.Component;
import org.ect.reo.Module;
import org.ect.reo.Property;


public class ModuleWithArgumentsPropertiesResource extends ModuleResource {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a resource for the module <code>module</code>.
	 * 
	 * @param module
	 *            The module. Not <code>null</code>.
	 * @throws ModuleResourceException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>module==null</code>.
	 */
	public ModuleWithArgumentsPropertiesResource(final Module module)
			throws ModuleResourceException {

		super(module);
	}

	/**
	 * Constructs a resource for the module in the file referenced by the path
	 * <code>modulePath</code>.
	 * 
	 * @param modulePath
	 *            The path. Not <code>null</code>.
	 * @throws ModuleResourceException
	 *             If something goes wrong while constructing.
	 */
	public ModuleWithArgumentsPropertiesResource(final String modulePath)
			throws ModuleResourceException {

		super(modulePath);
	}

	//
	// METHODS
	//

	/**
	 * Extracts, for every component with such a property in this resource, the
	 * (deserialized) value of the property named "proxygen.arguments".
	 * 
	 * @return A collection of arguments records.
	 * @throws ModuleWithArgumentsPropertiesResourceException
	 *             If something goes wrong while extracting.
	 */
	public Collection<ProxyJavaGeneratorProgramArguments> extractArgumentsProperties()
			throws ModuleWithArgumentsPropertiesResourceException {

		try {
			final Collection<ProxyJavaGeneratorProgramArguments> collection = new ArrayList<ProxyJavaGeneratorProgramArguments>();
			for (final Component c : super.getModule().getComponents())
				for (final Property p : c.getProperties())
					if (p.getKey().equals("proxygen.arguments"))
						collection.add(ProxyJavaGeneratorProgramArguments.deserialize(p
								.getValue()));

			return collection;
		} catch (final Exception e) {
			throw new ModuleWithArgumentsPropertiesResourceException(
					ModuleWithArgumentsPropertiesResourceException
							.EXTRACT_ARGUMENTS_PROPERTIES(this),
					e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class ModuleWithArgumentsPropertiesResourceException extends
			ModuleResourceException {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected ModuleWithArgumentsPropertiesResourceException(
				final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		protected ModuleWithArgumentsPropertiesResourceException(
				final String message, final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String EXTRACT_ARGUMENTS_PROPERTIES(
				final ModuleWithArgumentsPropertiesResource thiz) {

			return "The module resource \""
					+ thiz
					+ "\" failed to extract, for every component with such a property in this resource, the (deserialized) value of the property named \"proxygen.arguments\".";
		}
	}
}
