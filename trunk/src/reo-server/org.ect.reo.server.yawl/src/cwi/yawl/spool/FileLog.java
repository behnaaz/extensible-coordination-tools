package cwi.yawl.spool;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class FileLog implements Log {

	private File outputFile;
	private OutputStream out;
	private PrintStream p;

	public FileLog(String fileName) {
		try {
			outputFile = new File(fileName);
			out = new FileOutputStream(outputFile, true);
			p = new PrintStream(out);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(p);
		}
	}

	protected void finalize() throws Throwable {
		p.close();
	}

	public void logMessage(String msg) {
		p.println(currentTime() + msg);
	}
	
	
	private String currentTime() {
		Calendar cal = new GregorianCalendar();
		int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
		int min = cal.get(Calendar.MINUTE); // 0..59
		int sec = cal.get(Calendar.SECOND); // 0..59
		final String tsep = ":";
		final String dsep = ":";
		return Calendar.DAY_OF_MONTH + dsep + Calendar.MONTH + dsep
				+ Calendar.YEAR + " " + hour24 + tsep + min + tsep + sec;
	}

	
	public void logException(Throwable exception) {
	
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter, true);
		
		exception.printStackTrace(printWriter);
		printWriter.flush();
		stringWriter.flush();

		logMessage(exception.getMessage() + "\n" + stringWriter.toString());
		
	}
}
