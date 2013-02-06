package org.ect.codegen.ea.runtime;

/**
 * An abstract transition condition/constraint 
 * @author maraikar
 */
public interface TransCon {

	/** 
	 * @return a Boolean indicating success/failure
	 */
	boolean execute();
}