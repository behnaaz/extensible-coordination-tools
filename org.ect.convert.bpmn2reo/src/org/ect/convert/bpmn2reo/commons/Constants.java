package org.ect.convert.bpmn2reo.commons;

import org.eclipse.osgi.util.NLS;

public class Constants extends NLS {
	private static final String BUNDLE_NAME = "org.ect.convert.bpmn2reo.commons.messages"; //$NON-NLS-1$
	public static String BPMN2toReoConverter_BPMNEcore;
	public static String BPMN2toReoConverter_ReoEcore;
	public static String BPMN2toReoConverter_2;
	public static String BPMN2toReoConverter_3;
	public static String BPMN2toReoConverter_4;
	public static String BPMN2toReoConverter_5;
	public static String BPMN2toReoConverter_ASMFile;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Constants.class);
	}

	private Constants() {
	}
}
