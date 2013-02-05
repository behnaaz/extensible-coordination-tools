package cwi.yawl;

import java.io.IOException;
import java.util.List;

import org.jdom.Element;

import cwi.yawl.spool.Log;

import au.edu.qut.yawl.elements.data.YParameter;
import au.edu.qut.yawl.engine.interfce.InterfaceBWebsideController;
import au.edu.qut.yawl.worklist.model.SpecificationData;
import au.edu.qut.yawl.worklist.model.TaskInformation;
import au.edu.qut.yawl.worklist.model.WorkItemRecord;
import au.edu.qut.yawl.worklist.model.YParametersSchema;


public abstract class AbstractYAWLService extends InterfaceBWebsideController {
	
	// The session id.
	private String session;
	
	// The log to be used.
	private Log log;
	
	
	/**
	 * This method is called after the work item was checkedOut. 
	 * After this method the work item will be checkedIn.
	 * @param external ExternalWorkItem
	 */
	protected abstract void doMyService(ExternalWorkItem external);
	
	
	/*
	 * (non-Javadoc)
	 * @see au.edu.qut.yawl.engine.interfce.InterfaceBWebsideController#handleEnabledWorkItemEvent(au.edu.qut.yawl.worklist.model.WorkItemRecord)
	 */
	public void handleEnabledWorkItemEvent(WorkItemRecord workItemRecord) {
		
		try {
			
			// Validate the connection.
			if (!checkConnection(session)) {
				session = connect(DEFAULT_ENGINE_USERNAME, DEFAULT_ENGINE_PASSWORD);
			}
			
			// Check for connection errors.
			if (!successful(session)) {
				throw new Exception("Unable to establish session / invalid session: " + session);
			}
			
			// Check out the work items.
			String result = checkOut(workItemRecord.getID(), session);
			if (!successful(result)) {
				throw new Exception("Unable to checkout work items: " + workItemRecord.getID());
			}
			
			List<?> children = getChildren(workItemRecord.getID(), session);
			if (children.size() > 0) {
				WorkItemRecord child = (WorkItemRecord) children.get(0);
				ExternalWorkItem external = getExternalWorkItemAndData(child);
				if (log!=null) log.logMessage("CHECK OUT");
				
				doMyService(external); 
				
				checkInWorkItem(external, child, session);
				if (log!=null) log.logMessage("CHECK IN");
				
			}
				
		} catch (Exception e) {
			if (log!=null) log.logException(e);
		}
	}
	
	
	/**
	 * Given the work item record get all information and data elements.
	 * @param workItemRecord WorkItemRecord
	 * @return ExternalWorkItem
	 */
	private ExternalWorkItem getExternalWorkItemAndData(WorkItemRecord workItemRecord) {
		
		try {
			String taskID = workItemRecord.getTaskID();
			String caseID = workItemRecord.getCaseID();
			String specificationID = workItemRecord.getSpecificationID();
			TaskInformation taskinfo = getTaskInformation(specificationID, taskID, session);
			String decompositionID = taskinfo.getDecompositionID();
			String engineID = workItemRecord.getID();
			ExternalWorkItem external = new ExternalWorkItem(taskID, caseID, specificationID, decompositionID, engineID);

			getOutputData(external, taskinfo);
			getInputData(workItemRecord, external, taskinfo);

			// Check out all children of the work item.
			List<?> children = super.getChildren(workItemRecord.getID(), session);
			for (int i = 0; i < children.size(); i++) {
				WorkItemRecord itemRecord = (WorkItemRecord) children.get(i);
				if (WorkItemRecord.statusFired.equals(itemRecord.getStatus())) {
					checkOut(itemRecord.getID(), session);
				}
			}
			return external;
		} catch (Exception e) {
			if (log!=null) log.logException(e);
		}
		return null;
	
	}

	
	public synchronized void checkInWorkItem(ExternalWorkItem external, WorkItemRecord workItemRecord, String session) {
		try {
			Element task = prepareReplyRootElement(external, session);
			Element outPutData = outputData(external, task);
			Element inPutData = inputData(external, task);
			checkInWorkItem(external.getEngineID(), inPutData, outPutData, session);
		} catch (Exception e) {
			if (log!=null) log.logException(e);
		}
	}

	
	protected Element prepareReplyRootElement(ExternalWorkItem external, String sessionHandle) throws IOException {
	
		Element replyToEngineRootDataElement;
		SpecificationData sdata = getSpecificationData(external.getSpecificationID(), sessionHandle);

		String decompID = external.getDecompositionID();
		if (sdata.usesSimpleRootData()) {
			replyToEngineRootDataElement = new Element("data");
		} else {
			replyToEngineRootDataElement = new Element(decompID);
		}
		return replyToEngineRootDataElement;
	}
	
	
	/**
	 * For the external work item we get all output data from the YAWL engine.
	 *
	 * @param external ExternalWorkItem
	 * @param taskInfo TaskInformation
	 */
	private void getOutputData(ExternalWorkItem external, TaskInformation taskInfo) {
		
		YParametersSchema params;
		if (taskInfo==null) return;
		
		params = taskInfo.getParamSchema();
		List<?> output = params.getOutputParams();
		
		YParameter param = null;
		for (int i = 0; i < output.size(); i++) {
			param = (YParameter) output.get(i);
			String value = param.getInitialValue();
			ExternalDataElement data = new ExternalDataElement(param.getName(), param.getDataTypeName(), value);
			external.addOutputData(data);
		}
		
	}

	
	/**
	 * For the external work item we get all input data from the YAWL engine.
	 *
	 * @param external ExternalWorkItem
	 * @param taskInfo TaskInformation
	 */
	private void getInputData(WorkItemRecord workItemRecord, ExternalWorkItem external, TaskInformation taskInfo) {
		
		org.jdom.Element inputData = workItemRecord.getWorkItemData();

		YParametersSchema params;
		if (taskInfo==null) return;
		
		params = taskInfo.getParamSchema();
		List<?> output = params.getInputParams();
		YParameter parameter = null;

		for (int i = 0; i < output.size(); i++) {
			
			parameter = (YParameter) output.get(i);
			String name = parameter.getName();

			String value = parameter.getInitialValue();
			if (inputData != null) {
				value = ((Element) inputData.getChild(name)).getText();
			}

			String type = parameter.getDataTypeName();

			ExternalDataElement data = new ExternalDataElement(name, type, value);
			external.addInputData(data);
		}
	}
	
	
	public void handleCancelledWorkItemEvent(WorkItemRecord workItemRecord) {
		// add code here if necessary
	}

	/**
	 * Just creating XML form output data. We need this method to write the external work item
	 * to the log fine. We use XML format for the data.
	 *
	 * @param external ExternalWorkItem
	 * @param root Element
	 * @return Element
	 */
	private Element outputData(ExternalWorkItem external, Element root) {
		ExternalDataElement data = null;
		Element dataXML = null;
		for (int i = 0; i < external.outputSize(); i++) {
			data = (ExternalDataElement) external.getOutputElement(i);
			dataXML = new Element(data.getName());
			dataXML.setText(data.getValue());
			root.addContent(dataXML);
		}
		return root;
	}

	/**
	 * Just creating XML form input data. We need this method to write the external work item
	 * to the log fine. We use XML format for the data.
	 *
	 * @param external ExternalWorkItem
	 * @param root Element
	 * @return Element
	 */
	private Element inputData(ExternalWorkItem external, Element root) {
		ExternalDataElement data = null;
		Element dataXML = null;
		for (int i = 0; i < external.inputSize(); i++) {
			data = (ExternalDataElement) external.getInputElement(i);
			dataXML = new Element(data.getName());
			dataXML.setText(data.getValue());
			root.addContent(dataXML);
		}
		return root;
	}

	
	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
	
}
