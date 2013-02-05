package org.ect.reo.jobapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class HTMLLog implements Log {
	
	// The log as a ring buffer with string entries.
	private String[] entries;
	private int last;
	
	// Date format for the time-stamps. 
	private DateFormat format;
	
	/**
	 * Default constructor.
	 * @param size Maximal number of log entries.
	 */
	public HTMLLog(int size) {
		this.entries = new String[size];
		this.last = -1;
	    this.format = new SimpleDateFormat("dd-MM-yy [HH:mm:ss]");
	}
	
	/* (non-Javadoc)
	 * @see cwi.reo.data.Log#logMessage(java.lang.String)
	 */
	public void logMessage(String msg) {
		if (msg==null || msg.trim().equals("")) return;
		last = (last + 1) % entries.length;
		entries[last] = time() + " <span class=\"logmessage\"> " + msg + "</span><br>\n";
		System.out.println("INFO: " + msg);
	}
	
	/* (non-Javadoc)
	 * @see cwi.reo.data.Log#logError(java.lang.String, java.lang.Throwable)
	 */
	public void logError(String msg, Throwable t) {
		last = (last + 1) % entries.length;
		String detail = (t!=null) ? t.getMessage() : msg;
		entries[last] = time() + " <span class=\"logerror\" title=\"" + detail + "\">" + msg + "</span><br>\n";
		System.err.println("ERROR: " + msg);
		if (t!=null) t.printStackTrace();
	}
	
	/* (non-Javadoc)
	 * @see cwi.reo.data.Log#print()
	 */
	public String printLog() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i<entries.length; i++) {
			int pos = (last + i + 1) % entries.length;
			if (entries[pos]!=null) {
				result.append(entries[pos]);
			}
		}
		return result.toString();
	}
	
	/*
	 * Helper method for getting a time-stamp.
	 */
	private String time() {
	    return format.format(Calendar.getInstance().getTime());
	}
}
