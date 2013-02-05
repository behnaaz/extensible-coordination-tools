package org.ect.reo.data;

import java.util.HashMap;
import java.util.Iterator;


class NetworkList extends HashMap<Integer,Network> implements Iterable<Network> {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<Network> iterator() {
		return values().iterator();
	}
	
}
