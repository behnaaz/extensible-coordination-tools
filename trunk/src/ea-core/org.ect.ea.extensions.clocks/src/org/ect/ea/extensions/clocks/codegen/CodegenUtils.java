package org.ect.ea.extensions.clocks.codegen;

/** Utility class for code generation.*/

public class CodegenUtils {

	
	/** Prefix of abstraction information. */
	public static final String ABSTRACTION_INFO_PREFIX = "##################" +
			"###################################################\n#\n";
	
	/** Suffix of abstraction information. */
	public static final String ABSTRACTION_INFO_SUFFIX = "#################" +
			"###################################################\n# " +
			"INFORMATION NEEDED FOR ABSTRACTION, DO NOT EDIT ABOVE THIS " +
			"LINE! #\n#####################################################" +
			"###############\n";

	/** Symbol for MathSAT line comments. */
	public final static String MathSAT_LINE_COMMENT = "#";
	
	/** Name of the field in plugin.xml which contains the unfolding depth. */
	public static final String PROPERTY_DEPTH = "depth";
	
	/** Default unfolding depth for formula generation. */
	public static final String DEFAULT_DEPTH = "15";
	
	/** Name of the field in plugin.xml which contains the range of data values. */
	public static final String PROPERTY_RANGE = "range";
	
	/** Default range of data values for formula generation. */
	public static final String DEFAULT_RANGE = "0..1";

	/** Name of the field in plugin.xml which contains the output file name. */
	public static final String PROPERTY_FILENAME = "filename";

	/** Abstraction information about states starts with this. */
	public static final String ABS_INFO_STATES_BEGIN = "#States: ";
	
	/** Abstraction information about ports starts with this. */
	public static final String ABS_INFO_PORTS_BEGIN = "#Ports: ";
	
	/** Abstraction information about clocks starts with this. */
	public static final String ABS_INFO_CLOCKS_BEGIN = "#Clocks: ";

	/** Abstraction information about memory cells starts with this. */
	public static final String ABS_INFO_MEMCELLS_BEGIN = "#Memory cells: ";
	
	/** Abstraction information about unfolding depth starts with this. */
	public static final String ABS_INFO_UNFOLDING_DEPTH = "#Depth: ";
	
	/** Name of the global clock. */
	public static final String GLOBAL_CLOCK = "z";

	/** Prefix to add to port activity variables to obtain port data variables. */
	public static final String DATA_PREFIX = "Data";
	
	/** String representation of the integer value representing 'no data flow'. */
	public static final String NO_FLOW_VALUE = "-1";
	
	/** String representation of the name of the variable representing 'no data flow'. */
	public static final String NO_FLOW_NAME = "noflow";
}
