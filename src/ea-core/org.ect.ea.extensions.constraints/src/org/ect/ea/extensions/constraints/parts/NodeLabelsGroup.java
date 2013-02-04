package org.ect.ea.extensions.constraints.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class NodeLabelsGroup extends Composite {

	protected static final int SIZING_TEXT_FIELD_WIDTH = 250;

	
	public NodeLabelsGroup(Composite parent) {

		super(parent, SWT.NONE);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.makeColumnsEqualWidth = true;

		this.setLayout(gridLayout);
		this.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label nodeLabel = new Label(this, SWT.NONE);
		nodeLabel.setText("Node");
		nodeLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label inportLabel = new Label(this, SWT.NONE);
		inportLabel.setText("Inport");
		inportLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label outportLabel = new Label(this, SWT.NONE);
		outportLabel.setText("Outport");
		outportLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	}

}
