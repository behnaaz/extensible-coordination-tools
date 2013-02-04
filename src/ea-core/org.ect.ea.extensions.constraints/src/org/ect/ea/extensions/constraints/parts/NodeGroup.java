package org.ect.ea.extensions.constraints.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.ect.ea.extensions.constraints.CA.PortType;



public class NodeGroup extends Composite {

	protected static final int SIZING_TEXT_FIELD_WIDTH = 250;

	final Button tempInport;
	final Button tempOutport;
	final Label tempLabel;

	
	public NodeGroup(String nodeName, Composite parent) {
	
		super(parent, SWT.NONE);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.makeColumnsEqualWidth = true;
		this.setLayout(gridLayout);
		this.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tempLabel = new Label(this, SWT.NONE);
		tempLabel.setText(nodeName);
		tempLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tempInport = new Button(this, SWT.RADIO);
		tempInport.setText("");
		tempInport.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tempOutport = new Button(this, SWT.RADIO);
		tempOutport.setText("");
		tempOutport.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	}
	
	
	public PortType getPortType() {
		if (tempInport.getSelection()) return PortType.IN_PORT;
		if (tempOutport.getSelection()) return PortType.OUT_PORT;
		return null;
	}

	public void setPortType(PortType type) {
		if (type==PortType.OUT_PORT) tempOutport.setSelection(true);
		else tempInport.setSelection(true);
	}

	public String getNodeName() {
		return tempLabel.getText();
	}

}
