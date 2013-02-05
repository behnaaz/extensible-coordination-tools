package cwi.yawl.spool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSpooler extends AbstractSpooler {
	
	// Directory where the data items are stored.
	private File directory;
	
	/**
	 * Default constructor.
	 * @param path Spool directory.
	 */
	public FileSpooler(String path) {
		directory = new File(path);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		directory.deleteOnExit();
	}
	

	public void put(String portId, Object item) {
		
		try {
			// Create the file.
			File file = getFile(portId);
			if (!file.exists()) {
				file.createNewFile();
			}
			// Write the data item.
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
			stream.writeObject(item);
			stream.close();
			
		} catch (Exception e) {
			if (log!=null) log.logException(e);
		}
		
	}
	
	
	public Object read(String portId) {
		
		Object item = null;
		
		try {
			// Get the file.
			File file = getFile(portId);

			// Read the data item.
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			item = stream.readObject();
			stream.close();
			
		} catch (Exception e) {
			if (log!=null) log.logException(e);
		}

		return item;
	}

	
	public Object take(String portId) {
		
		Object item = read(portId);
		
		if (item!=null) {
			// Delete the file.
			File file = getFile(portId);
			file.delete();
		}

		return item;
	}

	
	private File getFile(String portId) {
		String path = directory.getAbsolutePath() + File.separator + portId;
		return new File(path);
	}
	
}
