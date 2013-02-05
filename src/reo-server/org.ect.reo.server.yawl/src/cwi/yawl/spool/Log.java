package cwi.yawl.spool;

public interface Log {

	public void logMessage(String msg);

	public void logException(Throwable exception);
	
}
