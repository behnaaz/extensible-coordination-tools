package org.ect.codegen.ea.runtime;

public interface ChannelOperation<I,O> {
	O apply(I value);
}
