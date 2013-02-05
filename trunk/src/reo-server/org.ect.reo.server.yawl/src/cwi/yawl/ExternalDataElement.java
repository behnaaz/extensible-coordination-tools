package cwi.yawl;

import java.io.Serializable;

import org.w3c.dom.*;


public class ExternalDataElement implements Serializable {

	/**
	 * Machine-generated object id, for serialization. 
	 */
	private static final long serialVersionUID = -1567805642493864886L;
	
	private static final String VARIABLE = "variable";
	private static final String TYPE = "type";
	private static final String VALUE = "value";
	private static final String NAME = "name";

	private String type;
	private String value;
	private String name;

	public ExternalDataElement(String name, String type, String value) {
		this.type = validate(type);
		this.name = validate(name);
		this.setValue(value);
	}

	public ExternalDataElement(Element element) {
		name = validate(XMLParser.getSimpleElementText(element, NAME));
		type = validate(XMLParser.getSimpleElementText(element, TYPE));
		this.setValue(XMLParser.getSimpleElementText(element, VALUE));
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		if (value == null) value = "NOT_DEFINED";
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private String validate(String attribute) {
		if (attribute == null) attribute = "";
		return attribute;
	}

	public Element toElement(Document document) {

		Element nameElem = document.createElement(NAME);
		nameElem.appendChild(document.createTextNode(validate(name)));

		Element typeElem = document.createElement(TYPE);
		typeElem.appendChild(document.createTextNode(validate(type)));

		Element valueElem = document.createElement(VALUE);
		valueElem.appendChild(document.createTextNode(validate(value)));

		Element variable = document.createElement(VARIABLE);
		variable.appendChild(nameElem);
		variable.appendChild(typeElem);
		variable.appendChild(valueElem);

		return variable;
	}

	public void fromElement(Element element) {
		if (element != null) {
			this.name = XMLParser.getSimpleElementText(element, NAME);
			this.type = XMLParser.getSimpleElementText(element, TYPE);
			this.value = XMLParser.getSimpleElementText(element, VALUE);
		}
	}
}
