package org.ect.reo.data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class NetworkIdentifier {
	
	// Delimiter of elements.
	public static final char DELIMITER = ':';
	
	private List<Integer> ids;
	
	/**
	 * Default constructor.
	 * @param ids Identifier elements.
	 * @param parameters Optional parameters.
	 */
	public NetworkIdentifier(int... ids) {
		
		this.ids = new ArrayList<Integer>();
		
		// Only non-negative ids are allowed.
		if (ids!=null) {
			for (int id : ids) {
				if (id>=0) this.ids.add(id);
				else break;
			}
		}		
	}
	
	/**
	 * Get the ids of this identifier.
	 * @return The ids.
	 */
	public List<Integer> ids() {
		return ids;
	}
		
	public int popId() {
		return ids().remove(ids.size()-1);
	}
		
	public NetworkIdentifier copy() {
		int[] ids = new int[this.ids.size()];
		for (int i=0; i<ids.length; i++) ids[i] = this.ids.get(i);		
		return new NetworkIdentifier(ids);
	}
	
	
	/**
	 * Parse a network identifier.
	 * @param identifier Network identifier as a string.
	 * @return NetworkIdentifier.
	 * @throws ParseException ParseException.
	 */
	public static NetworkIdentifier parse(String input) throws ParseException {
		
		// Create an empty identifier.
		NetworkIdentifier identifier = new NetworkIdentifier(null);
		
		// Remove '/' from the start.
		if (input.startsWith("/")) {
			input = input.substring(1);
		}
		
		// Find the index of the first parameter.
		int index = input.indexOf('&');		
		String ids = (index>=0) ? input.substring(0,index) : input;
		
		StringTokenizer tokenizer = new StringTokenizer(ids, String.valueOf(DELIMITER));
		while (tokenizer.hasMoreElements()) {
			String next = tokenizer.nextToken();
			int id = Integer.parseInt(next);
			if (id<0) throw new ParseException("Negative id in network identifier.", 0);
			identifier.ids().add(id);
		}
		
		return identifier;	
	}
	
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i<ids.size(); i++) {
			result.append(ids.get(i));
			if (i!=ids.size()-1) result.append(DELIMITER);
		}
		return result.toString();
	}
	
	
}
