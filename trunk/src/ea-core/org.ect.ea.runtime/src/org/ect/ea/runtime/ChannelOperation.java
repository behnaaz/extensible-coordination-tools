package org.ect.ea.runtime;

public interface ChannelOperation<I,O> {
	O apply(I value);
}
