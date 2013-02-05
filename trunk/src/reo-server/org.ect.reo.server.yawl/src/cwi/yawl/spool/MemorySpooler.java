package cwi.yawl.spool;

import java.util.HashMap;

public class MemorySpooler extends AbstractSpooler {
	
	// HashMap to store the data-items.
	protected HashMap<String,Object> items;
	
	/**
	 * Default constructor.
	 */
	public MemorySpooler() {
		this.items = new HashMap<String, Object>();
	}
	
	public void put(String portId, Object item) {
		items.put(portId, item);
	}

	public Object read(String portId) {
		return items.get(portId);
	}

	public Object take(String portId) {
		return items.remove(portId);
	}

}
