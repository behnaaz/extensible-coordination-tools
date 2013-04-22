package org.ect.codegen.v2.proxy.rt.java.wsdl;

public enum WSDLDirection {
	IN, OUT;

	//
	// STATIC
	//

	/**
	 * Convert the string <code>direction</code> to a direction.
	 * 
	 * @param direction
	 *            The string. Not <code>null</code>.
	 * @return A direction. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>direction==null</code>.
	 * @throws DirectionException
	 *             If <code>direction</code> does represents no direction.
	 */
	public static WSDLDirection convertToDirection(final String direction)
			throws DirectionException {

		if (direction == null)
			throw new NullPointerException();

		if (direction.equals("in"))
			return IN;
		else if (direction.equals("out"))
			return OUT;
		else
			throw new DirectionException("I cannot convert the string \""
					+ direction + "\" to a direction.");
	}

	//
	// EXCEPTIONS
	//

	public static class DirectionException extends Exception {
		private static final long serialVersionUID = 1L;

		public DirectionException(String message) {
			super(message);
		}
	}
}