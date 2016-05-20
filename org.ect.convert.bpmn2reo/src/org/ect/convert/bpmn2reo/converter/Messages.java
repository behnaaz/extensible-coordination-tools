package org.ect.convert.bpmn2reo.converter;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.ect.convert.bpmn2reo.converter.messages"; //$NON-NLS-1$
	public static String BPMN2ReoAction_inputFile;
	public static String BPMN2ReoAction_outputFile;
	public static String BPMN2ReoAction_2;
	public static String BPMN2ReoAction_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
