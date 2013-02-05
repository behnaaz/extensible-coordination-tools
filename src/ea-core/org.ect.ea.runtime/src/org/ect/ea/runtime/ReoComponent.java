package org.ect.ea.runtime;

/**
 * A fluent interface for wiring Reo components
 * @author maraikar
 *
 * @param <T> The data type the the ports accept or return
 */
@SuppressWarnings("rawtypes")
public interface ReoComponent<I,O> extends Runnable {
	public ReoComponent withSinkPorts(Sink<O>...  sinks);
	public ReoComponent withSourcePorts(Source<I>...  sources);
//	public ReoComponent usingProps(Collection props);
}
