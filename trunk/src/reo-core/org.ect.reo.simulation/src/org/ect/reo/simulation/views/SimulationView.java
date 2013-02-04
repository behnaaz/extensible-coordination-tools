/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.reo.simulation.views;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.ect.reo.Connector;
import org.ect.reo.simulation.simulator.*;


/**
 * Reo simulation view.
 */
public class SimulationView extends ViewPart implements ISelectionListener {
	// Keys for the statistic category of all types of statistics
	public static final String COL_STATKEY = "Colourings";
	public static final String SYS_STATE_STATKEY = "SystemState";
	public static final String CH_UT_STATKEY = "ChannelUtilization";
	public static final String CH_LOCKED_STATKEY = "ChannelLocked";
	public static final String BUFFER_UT_STATKEY = "BufferUtilization";
	public static final String NODE_STATE_STATKEY = "NodeState";
	public static final String REQ_OBS_STATKEY = "ReqObservations";
	public static final String AVG_WTG_STATKEY = "AvgWaitingTime";
	public static final String AVG_COND_WTG_STATKEY = "AvgCondWaitingTime";
	public static final String END_END_STATKEY = "EndEndDelay";
	public static final String INTER_ARR_STATKEY = "InterarrivalTimes";
	public static final String ACT_LOSS_STATKEY = "ActualLossRatio";
	public static final String MERGER_STATKEY = "MergerDirections";
	public static final String SPECIALSTATE_STATKEY = "SpecialState";
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.ect.reo.simulation.views.SimulationView";
	
	// A text widget:
	private Text text;
	
	// Currently selected connector:
	public static Connector connector;

	// A button:
	private Button button;
	
	// Memento to save the chosen options
	private IMemento memento;
	
	// Writers for results and colourings
	public static FileWriter colWriter;
	public static FileWriter resultWriter;
	
	// Result and chart tab
	public static CTabFolder chartTabFolder;
	public static CTabFolder resultsTabFolder;

	// Map with all statistic categories
	public static HashMap<String, StatisticCategory> statisticCategories;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		createStatisticCategories();
		CTabFolder tabFolder = new CTabFolder(parent, SWT.NONE);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		tabFolder.setSimple(false);
		
		CTabItem runItem = new CTabItem(tabFolder, SWT.NONE);
		runItem.setText("Run");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER
		        | SWT.H_SCROLL | SWT.V_SCROLL);
		
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(1350);
		scrolledComposite.setMinHeight(480);
	    
		Composite comp = new Composite(scrolledComposite, SWT.NONE);
		comp.setLayout(new GridLayout(3, true));
		
		scrolledComposite.setContent(comp);
		runItem.setControl(scrolledComposite);	
		
		Label label = new Label(comp, SWT.NONE);
		label.setText("Selected connector:");
		
		text = new Text(comp, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		button = new Button(comp, SWT.NONE);
		button.setText("Start simulation");
		button.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				computeAnimations();
			}
		});
		
		SimulationViewResultOptions.addResultOptions(tabFolder, memento, statisticCategories);
		
		SimulationViewOptions.addOptions(comp, memento);
		
		// Result tab
		CTabItem resultItem = new CTabItem(tabFolder, SWT.NONE);
		resultItem.setText("Results");
		
		SimulationView.resultsTabFolder = new CTabFolder(tabFolder, SWT.NONE);
		resultsTabFolder.setBorderVisible(true);
		resultsTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
		resultsTabFolder.setSimple(false);
	    resultItem.setControl(resultsTabFolder);
	    
	    // Chart tab
		CTabItem chartItem = new CTabItem(tabFolder, SWT.NONE);
		chartItem.setText("Charts");
		
		SimulationView.chartTabFolder = new CTabFolder(tabFolder, SWT.NONE);
	    chartTabFolder.setBorderVisible(true);
	    chartTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
	    chartTabFolder.setSimple(false);
	    chartItem.setControl(chartTabFolder);
	    
	    tabFolder.setSelection(0);
		updateView();
	}
	
	
	/**
	 * Create the hashMap with all statistic categories
	 */
	public static void createStatisticCategories() {
		statisticCategories = new HashMap<String, StatisticCategory>();

		int maxChartPoints;
		try {
			maxChartPoints = Integer.valueOf(SimulationViewOptions.maxChartPointsText.getText());
		} catch (Throwable t) {
			maxChartPoints = 5000;
		}
		
		statisticCategories.put(COL_STATKEY, new StatisticCategory("Colourings", COL_STATKEY, true, maxChartPoints));
		statisticCategories.put(SYS_STATE_STATKEY, new StatisticCategory("System state", SYS_STATE_STATKEY, true, maxChartPoints));
		statisticCategories.put(CH_UT_STATKEY, new StatisticCategory("Channel utilization", CH_UT_STATKEY, true, maxChartPoints));
		statisticCategories.put(CH_LOCKED_STATKEY, new StatisticCategory("Channel locked", CH_LOCKED_STATKEY, false));
		statisticCategories.put(BUFFER_UT_STATKEY, new StatisticCategory("Buffer utilization", BUFFER_UT_STATKEY, true, maxChartPoints));
		statisticCategories.put(NODE_STATE_STATKEY, new StatisticCategory("Node state", NODE_STATE_STATKEY, true, maxChartPoints));
		statisticCategories.put(REQ_OBS_STATKEY, new StatisticCategory("Req observations", REQ_OBS_STATKEY, false));
		statisticCategories.put(AVG_WTG_STATKEY, new StatisticCategory("Avg waiting time", AVG_WTG_STATKEY, false));
		statisticCategories.put(AVG_COND_WTG_STATKEY, new StatisticCategory("Avg cond waiting time", AVG_COND_WTG_STATKEY, false));
		statisticCategories.put(END_END_STATKEY, new StatisticCategory("End-end delay", END_END_STATKEY, true, maxChartPoints));
		statisticCategories.put(INTER_ARR_STATKEY, new StatisticCategory("Inter-arrival times", INTER_ARR_STATKEY, false));
		statisticCategories.put(ACT_LOSS_STATKEY, new StatisticCategory("Actual loss ratio", ACT_LOSS_STATKEY, false));
		statisticCategories.put(MERGER_STATKEY, new StatisticCategory("Merger directions", MERGER_STATKEY, false));
		statisticCategories.put(SPECIALSTATE_STATKEY, new StatisticCategory("Special state", SPECIALSTATE_STATKEY, true, maxChartPoints));
	}
	

	private static void resetCharts() {
		// Remove all tab items from the result and chart tab.
		for (int i = chartTabFolder.getItemCount() - 1; i >= 0; i--) {
			chartTabFolder.getItem(i).dispose();
		}
		for (int i = resultsTabFolder.getItemCount() - 1; i >= 0; i--) {
			resultsTabFolder.getItem(i).dispose();
		}
	}	
	

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite, org.eclipse.ui.IMemento)
	 */
	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site);
		// Add a selection listener to the workbench window:
		site.getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		
		this.memento = memento; 	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.ViewPart#saveState(org.eclipse.ui.IMemento)
	 */
	@Override
	public void saveState(IMemento memento) {
        super.saveState(memento);
        SimulationViewOptions.saveOptions(memento);
        SimulationViewResultOptions.saveResultOptions(memento, statisticCategories);
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		// Remove the listener again:
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);		
		super.dispose();
	}
	
	
	/*
	 * Compute the animation table for the current network.
	 */
	private void computeAnimations() {	
		if (connector!=null) {
			// Create a job for this.
			final SimulationJob job = createSimulationJob();
			if (job != null) {
				job.addJobChangeListener(new JobChangeAdapter() {
					public void done(IJobChangeEvent event) {
						getSite().getShell().getDisplay().asyncExec(new Runnable() {
							public void run() {
								doSomething(job.getSimulator());									
							}								
						});
					}
				});
				
				// Start the job.
				job.schedule();
			}
		}		
	}
	
	
	private SimulationJob createSimulationJob() {
		try {
			createStatisticCategories();
			// For all statistic categories, set if the result and/or chart has to be shown after the simulation
			for (String key : statisticCategories.keySet()) {
	        	statisticCategories.get(key).setUseResult(SimulationViewResultOptions.resultButtons.get(key).getSelection());
	        	if (SimulationViewResultOptions.chartButtons.containsKey(key)) {
	        		statisticCategories.get(key).setUseChart(SimulationViewResultOptions.chartButtons.get(key).getSelection());	
	        	}
	        }
			
			int seed;
			if (SimulationViewOptions.seedText.getText().equals("")) {
				seed = Integer.MIN_VALUE;
			} else {
				seed = Integer.parseInt(SimulationViewOptions.seedText.getText());
			}
			
			String[] stopStates = new String[0];
			if (!SimulationViewOptions.stopStatesText.getText().equals("")) {
				stopStates = SimulationViewOptions.stopStatesText.getText().split(",");
				for (int i = 0; i < stopStates.length; i++) {
					stopStates[i] = stopStates[i].trim();
				}
			}
			
			String[] specialStates = new String[0];
			if (!SimulationViewOptions.specialStatesText.getText().equals("")) {
				specialStates = SimulationViewOptions.specialStatesText.getText().split(",");
				for (int i = 0; i < specialStates.length; i++) {
					specialStates[i] = specialStates[i].trim();
				}
			}
			
			int liveLockSteps = 0;
			if (SimulationViewOptions.liveLockButton.getSelection()) {
				liveLockSteps = Integer.parseInt(SimulationViewOptions.liveLockColouringsText.getText());
			}
	        
			// Create the actual simulation job
			return new SimulationJob(connector, SimulationViewOptions.longTermButton.getSelection(), !SimulationViewOptions.timeButton.getSelection(), 
									 Double.parseDouble(SimulationViewOptions.warmUpText.getText()), Double.parseDouble(SimulationViewOptions.totalLengthText.getText()), 
									 Integer.parseInt(SimulationViewOptions.batchNumberText.getText()), Double.parseDouble(SimulationViewOptions.confidenceText.getText()),
									 SimulationViewOptions.deadLockButton.getSelection(), SimulationViewOptions.liveLockButton.getSelection(), 
									 liveLockSteps, seed, stopStates, specialStates, statisticCategories);
		} catch (Throwable t) {
			MessageDialog.openInformation(getSite().getShell(), "Wrong options", "The options are not in the right format");
			return null;
		}
	}
	

	/*
	 * Do something with the animation table...
	 */
	private void doSomething(ReoSimulator simulator) {
		resetCharts();
		
		try
		{
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();

			// To get to the directory of the Workspace
			IPath path = root.getLocation();
			String stringPath = path.toString();
			
			// Create result directory
			(new File(stringPath + "/Results")).mkdir();
			
			// Get current time in string format
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
			String date = dateFormat.format(cal.getTime());
			
			// Create result file and colouring file
			String fileName = connector.getName() + " - " + date;
			resultWriter = new FileWriter(stringPath + "/Results/" + fileName + ".csv");
			resultWriter.append("Statistic, Mean, Observations per batch, Standard deviation, Coefficient of variation, Lower bound, Upper bound" + '\n');
			colWriter = new FileWriter(stringPath + "/Results/" + fileName + " - Colourings.csv");
		    
			simulator.outputResults();
 
			resultWriter.flush();
			resultWriter.close();
			colWriter.flush();
			colWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		} 
	}	
	
	
	private void updateView() {
		// Update the label:
		text.setText(connector==null ? "none" : connector.getName());
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		// Reset connector:
		connector = null;
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) selection;
			Object first = structured.getFirstElement();
			
			if (first instanceof IGraphicalEditPart) {
				IGraphicalEditPart editpart = (IGraphicalEditPart) first;
				Object element = editpart.getNotationView().getElement();
				
				// Now we can check if we have the correct element:
				if (element instanceof Connector) {
					connector = (Connector) element;
				}				
			}			
		}
		updateView();
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// someField.setFocus();
	}			
}