package cwi.yawl;

import java.util.*;
import javax.xml.parsers.*;

import org.apache.xerces.dom.*;
import org.w3c.dom.*;

public class ExternalWorkItem {

	private static final String XML_MAIN = "workitem";
	private static final String TASK_ID = "task";
	private static final String CASE_ID = "case";
	private static final String DATA = "data";
	private static final String INPUT_DATA = "input";
	private static final String OUTPUT_DATA = "output";
	private static final String SPECIFICATION_ID = "specification";
	private static final String DECOMPOSITION_ID = "decomposition";
	private static final String ENGINE_ID = "engine";

	private String taskID;
	private String caseID;
	private String specificationID;
	private String decompositionID;
	private String engineID;
	private List<ExternalDataElement> inputData;
	private List<ExternalDataElement> outputData;

	public ExternalWorkItem() {
		this("", "", "", "", "");
	}

	public ExternalWorkItem(String taskID, String caseID, String specificationID, String decompositionID, String engineID) {
		this.taskID = taskID;
		this.caseID = caseID;
		this.specificationID = specificationID;
		this.decompositionID = decompositionID;
		this.engineID = engineID;
		this.inputData = new ArrayList<ExternalDataElement>();
		this.outputData = new ArrayList<ExternalDataElement>();
	}

	public void addInputData(ExternalDataElement data) {
		if (data.getName() != null) {
			inputData.add(data);
		}
	}

	public void addOutputData(ExternalDataElement data) {
		if (data.getName() != null) {
			outputData.add(data);
		}
	}

	public Element toElement() {
		Element root = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation domImpl = builder.getDOMImplementation();
			Document document = domImpl.createDocument(null, "available", null);
			root = document.createElement(XML_MAIN);

			Element taskIDElem = document.createElement(TASK_ID);
			taskIDElem.appendChild(document.createTextNode(taskID));
			root.appendChild(taskIDElem);

			Element caseIDElem = document.createElement(CASE_ID);
			caseIDElem.appendChild(document.createTextNode(caseID));
			root.appendChild(caseIDElem);

			Element decompositionIDElem = document
					.createElement(DECOMPOSITION_ID);
			decompositionIDElem.appendChild(document
					.createTextNode(decompositionID));
			root.appendChild(decompositionIDElem);

			Element engineIDElem = document.createElement(ENGINE_ID);
			engineIDElem.appendChild(document
					.createTextNode((engineID != null) ? engineID : ""));
			root.appendChild(engineIDElem);

			Element specIDElem = document.createElement(SPECIFICATION_ID);
			specIDElem.appendChild(document.createTextNode(specificationID));
			root.appendChild(specIDElem);

			Element dataElem = this.dataToElement(document);
			root.appendChild(dataElem);
		} catch (ParserConfigurationException e) {
			// what happens here???
		}
		return root;
	}

	public void fromElement(Element element) {
		if (element != null) {
			this.taskID = XMLParser.getSimpleElementText(element, TASK_ID);
			this.caseID = XMLParser.getSimpleElementText(element, CASE_ID);
			this.specificationID = XMLParser.getSimpleElementText(element, SPECIFICATION_ID);
			this.decompositionID = XMLParser.getSimpleElementText(element, DECOMPOSITION_ID);
			this.engineID = XMLParser.getSimpleElementText(element, ENGINE_ID);
			this.dataFromElement(element);
		}
	}

	public void dataFromString(String externalWorkItemXML) {
		try {
			Node node = XMLParser.fromString(externalWorkItemXML);
			if (node instanceof DocumentImpl) {
				DocumentImpl document = (DocumentImpl) node;
				node = document.getElementsByTagName(XML_MAIN).item(0);
			}
			if (node instanceof Element)
				this.dataFromElement((Element) node);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fromString(String externalWorkItemXML) {
		try {
			Node node = XMLParser.fromString(externalWorkItemXML);
			if (node instanceof DocumentImpl) {
				DocumentImpl document = (DocumentImpl) node;
				node = document.getElementsByTagName(XML_MAIN).item(0);
			}
			if (node instanceof Element)
				this.fromElement((Element) node);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toXML() {
		return XMLParser.toString(this.toElement());
	}

	public String toTCPIP() {
		return this.toXML() + '\n';
	}

	private Element dataToElement(Document document) {

		Element input = dataListToElement(document, inputData, INPUT_DATA);
		Element output = dataListToElement(document, outputData, OUTPUT_DATA);

		Element result = document.createElement(DATA);
		result.appendChild(input);
		result.appendChild(output);
		return result;
	}

	private Element dataListToElement(Document document, List<ExternalDataElement> list, String tag) {
		Element result = document.createElement(tag);
		for (int i = 0; i < list.size(); i++) {
			ExternalDataElement data = list.get(i);
			Element dataElement = data.toElement(document);
			result.appendChild(dataElement);
		}
		return result;
	}

	private void dataListFromElement(Element element, List<ExternalDataElement> list) {
		NodeList nl = element.getChildNodes();

		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				ExternalDataElement data = new ExternalDataElement(
						(Element) node);
				list.add(data);
			}
		}
	}

	private void dataFromElement(Element element) {
		Element data = XMLParser.getFirstElement(element, DATA);
		Element input = XMLParser.getFirstElement(data, INPUT_DATA);
		dataListFromElement(input, inputData);
		Element output = XMLParser.getFirstElement(data, OUTPUT_DATA);
		dataListFromElement(output, outputData);

	}

	public String getSpecificationID() {
		return specificationID;
	}

	public String getCaseID() {
		return caseID;
	}

	public String getTaskID() {
		return this.taskID;
	}

	public String getEngineID() {
		return engineID;
	}

	public void setEngineID(String engineID) {
		this.engineID = engineID;
	}

	public String getDecompositionID() {
		return decompositionID;
	}

	private ExternalDataElement getDataElement(List<ExternalDataElement> list, int i) {
		if (list.size() > i) {
			return list.get(i);
		}
		return null;
	}

	public int inputSize() {
		return inputData.size();
	}

	public int outputSize() {
		return outputData.size();
	}

	public ExternalDataElement getInputElement(int i) {
		return getDataElement(inputData, i);
	}

	public ExternalDataElement getOutputElement(int i) {
		return getDataElement(outputData, i);
	}

	public void save(Document d, String file) {
		XMLParser.toFile(d, file);
	}

	public String save(Element e) {
		return XMLParser.toString(e);
	}
}
