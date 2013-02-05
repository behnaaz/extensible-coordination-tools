package org.ect.reo.actions;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ect.reo.Reo;
import org.ect.reo.jobapi.Job;
import org.ect.reo.jobapi.JobProgress;




public class UploadAction extends HttpServlet {

	// Machine-generated serial id.
	private static final long serialVersionUID = 6842994417939040420L;
	
	// PageHelper.
	private PageHelper helper;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.helper = new PageHelper(config);
	}
    
    
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	final Job job = helper.getScheduler().createJob("Handling upload...");
    	job.setRefresh(PageHelper.PAGE_NETWORKS);
    	job.setRefresh(PageHelper.PAGE_RULES);
    	
        // Handle upload and load networks.
    	try {
    		
    		File upload = handleUpload(request);
    		
    		if (upload.getName().endsWith("." + Reo.FILE_EXT)) {
    			helper.getWorkspace().loadNetworks(upload, new JobProgress(job));
    		}
    		/*else if (upload.getName().endsWith("." + Reconfiguration.FILE_EXT)) {
    			helper.getWorkspace().loadReconfigurationRules(upload, new JobProgress(job));
    		}
    		*/
		}
		catch (Throwable e) {
			job.logError("Error loading file.", e);
		} finally {
			job.finish();
		}
		
	}
	
	
	/*
	 * Handle the file upload.
	 */
	protected File handleUpload(HttpServletRequest request) throws Exception {
		
		// Create a temporary directory.
		File directory = File.createTempFile("upload", "");
		directory.delete();
		directory.mkdirs();
		directory.deleteOnExit();
		
		// Do the upload.
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(directory);
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        File reoFile = null;
        
        for (Object item : upload.parseRequest(request)) {
            
        	FileItem fileItem = (FileItem) item;
            if (fileItem.isFormField()) continue;
            
            // Create the file.
            File file = new File(directory.getPath() + File.separator + fileItem.getName());
        	file.createNewFile();
        	file.deleteOnExit();
        	fileItem.write(file);
        	
            if (fileItem.getName().endsWith("."+Reo.FILE_EXT) 
//            	|| fileItem.getName().endsWith("."+Reconfiguration.FILE_EXT)
            	) {
            	reoFile = file;
            }
        }
        return reoFile;
	}
    
    
}
