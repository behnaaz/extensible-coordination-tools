package org.ect.codegen.ea.runtime;

/**
 * Interface implemented by the "main" simulator class.
 * Intended to be passed to components. A "Master" component
 * should call {@link #launch} to start the sim
 * @author maraikar
 *
 */
public interface ReoEngine<T> {
	T getPort(String portName);
	void launch(Object... components) throws Exception;
}
