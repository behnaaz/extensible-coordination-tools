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
package org.ect.reo.distengine.ui.views;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;
import org.ect.reo.diagram.edit.parts.*;

import org.ect.reo.distengine.redrum.deployment.Engine;
import org.ect.reo.distengine.redrum.deployment.EngineManager;
import org.ect.reo.distengine.redrum.deployment.ManagerTrait;
import org.ect.reo.distengine.ui.wizards.EngineWizard;
import org.ect.reo.distengine.common.Logger;
import org.ect.reo.distengine.common.NullLogger;
import org.ect.reo.distengine.common.SeqChartLogger;

//import scala.runtime.Nothing$;
import scala.collection.immutable.*;
import scala.runtime.Nothing$;

public class EnginesView extends ViewPart implements //ISelectionListener,
IPageListener {
	
	//private Connector connector;
	//private Label label;
	private Combo engines;
	private EngineManager engManager = new EngineManager();
	private EnginesView engView = this;
	
	private Text outbox = null;
	private ISelectionListener workbenchListener;

	@Override
	public void createPartControl(Composite parent) {
		
		
		engManager.start();
		
		getViewSite().getWorkbenchWindow().addPageListener(this);

		
		Composite container = new Composite(parent, SWT.FILL);
		container.setLayout(new GridLayout(2, false));
		
		Composite leftContainer = new Composite(container, SWT.NONE);
		Composite rightContainer = new Composite(container, SWT.NONE);
		leftContainer.setLayout(new GridLayout(1, true));
		leftContainer.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		rightContainer.setLayout(new GridLayout(1, true));
		rightContainer.setLayoutData(new GridData(GridData.FILL_BOTH));


		
		// RIGHT CONTAINER: OUTPUT
		final Text output = new Text(rightContainer,SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		output.setEditable(false);
		output.setFont(new org.eclipse.swt.graphics.Font(getSite().getWorkbenchWindow().getShell().getDisplay(),
				"Courier New",12,SWT.NORMAL));
		output.setText("<Logging window>");
		output.setLayoutData(new GridData(GridData.FILL_BOTH));
		outbox = output;


		// LEFT CONTAINER: Engines and Primitives
		GridData leftData = new GridData(GridData.FILL_HORIZONTAL);

		// ENGINES
		Label enginesLabel = new Label(leftContainer, SWT.NORMAL);
		enginesLabel.setText("Engines: ");
		enginesLabel.setLayoutData(leftData);
				
		Composite buttonArea = new Composite(leftContainer, SWT.FILL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		buttonArea.setLayoutData(data);
		buttonArea.setLayout(new GridLayout(4,false));
		
		engines = new Combo(buttonArea,SWT.DROP_DOWN);
		engines.setItems(new String[0]);
		engines.setLayoutData(data);
		// Select Engine (combo box selection)
		engines.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String sel = engines.getItem(engines.getSelectionIndex());
				refreshEngineStatus(sel,output);
			}
		});

		final Button addEng = new Button(buttonArea,SWT.BORDER);
		addEng.setText("Add");
		// Add new engine, using a wizard (button addEng)
		addEng.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {				
				// Instantiates and initializes the wizard
				EngineWizard wizard = new EngineWizard();
				wizard.init(getSite().getWorkbenchWindow().getWorkbench(),null);
				wizard.setEngManager(engView);
				// Instantiates the wizard container with the wizard and opens it
				WizardDialog dialog = new WizardDialog(getSite().getShell(), wizard);
				dialog.create();
				dialog.open();
				//refreshEngineStatus(engines.getItem(engines.getSelectionIndex()),output);
			}
		});

		final Button delEng = new Button(buttonArea,SWT.PUSH);
		delEng.setText("Forget");
		// Forget the selected engine (button delEng)
		delEng.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int engk_i = engines.getSelectionIndex();
				if (engk_i >= 0) {
					String engk = engines.getItem(engk_i);
					if (engk.equals("local@127.0.0.1:9010"))
						engManager.killEngine(engk);
					else
						engManager.forgetEngine(engk);
					engines.remove(engk_i);
					engines.setText("");
				}
				else
					showError("No engine selected!");
			}
		});

		final Button refreshEng = new Button(buttonArea,SWT.PUSH);
		final boolean[] askedStatus = {false};
		refreshEng.setText("Status");
		// Refresh status of engine (button refreshEng)
		refreshEng.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				askedStatus[0] = true;
				refreshEngineStatus(engines.getItem(engines.getSelectionIndex()),output);
			}
		});

		
		
		// PRIMITIVES
		Label primitivesLabel = new Label(leftContainer, SWT.NORMAL);
		primitivesLabel.setText("Primitives: ");
		primitivesLabel.setLayoutData(leftData);

		buttonArea = new Composite(leftContainer, SWT.FILL);
		data = new GridData(GridData.FILL_BOTH);
		//data.widthHint = 200;
	    //data.heightHint = 150;
	    buttonArea.setLayoutData(data);
		buttonArea.setLayout(new GridLayout(1,true));
		
		final List primitives = new List(buttonArea,SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		primitives.setLayoutData(data);
		primitives.setFont(new org.eclipse.swt.graphics.Font(getSite().getWorkbenchWindow().getShell().getDisplay(),
				"Courier New",12,SWT.BOLD));
		
		buttonArea = new Composite(leftContainer, SWT.FILL);
		data = new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_CENTER);
		data.horizontalSpan = 0;
		buttonArea.setLayoutData(data);
		buttonArea.setLayout(new GridLayout(5,false));

		final Button deploy = new Button(buttonArea,SWT.PUSH);
		deploy.setText("Deploy");
		// Deploy selected primitives on the selected engine.
		deploy.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int[] primks = primitives.getSelectionIndices();
				int index = engines.getSelectionIndex();
				if (index == -1) {
					showError("No engine selected!");
					return;
				}
				String engk = engines.getItem(index);
				//System.out.println("Found engine key "+engk);
				for (int i=0; i < primks.length; i++) {
					System.out.println("deploying prim/node from manager "+primks[i]);
					engManager.deployPrimitive(engk,primks[i]);
				}
				//refreshEngineStatus(engines.getItem(engines.getSelectionIndex()),output);
				// NOT NEEDED NOW! // engManager.requestPrimitives(engk);
			}
		});

		final Button release = new Button(buttonArea,SWT.PUSH);
		release.setText("Release");
		// Release primitives
		release.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int[] primks = primitives.getSelectionIndices();
				System.out.println("Requesting release of selected primitives/nodes.");
				for (int i=0; i < primks.length; i++) {
					System.out.println("Releasing suspension of prim/node: "+primks[i]);
					engManager.releasePrimitive(primks[i]);
				}
				//refreshEngineStatus(engines.getItem(engines.getSelectionIndex()),output);
				//engManager.requestPrimitives();
			}
		});

		final Button suspend = new Button(buttonArea,SWT.PUSH);
		suspend.setText("Suspend");
		// Suspend primitives
		suspend.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int[] primks = primitives.getSelectionIndices();
				System.out.println("Requesting suspension of selected primitives/nodes.");
				for (int i=0; i < primks.length; i++) {
					System.out.println("Suspending prim/node: "+primks[i]);
					engManager.suspendPrimitive(primks[i]);
				}
				//refreshEngineStatus(engines.getItem(engines.getSelectionIndex()),output);
				//engManager.requestPrimitives();
			}
		});

		final Button undeploy = new Button(buttonArea,SWT.PUSH);
		undeploy.setText("Undeploy");
		// Remove(undeploy) primitive, disconnecting from connected ends
		undeploy.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int[] primks = primitives.getSelectionIndices();
				//for (int i=0; i < primks.length; i++) {
				System.out.println("removing prims on table from manager.");
				engManager.removePrimitives(primks);
				//}
				//refreshEngineStatus(engines.getItem(engines.getSelectionIndex()),output);
				//engManager.requestPrimitives();
			}
		});

		final Button connect = new Button(buttonArea,SWT.PUSH);
		connect.setText("Unify");
		// Refresh last list of primitives on an engine, and sends a request to selected engine
		// to retrieve most recent primitive list (button refreshp)
		// NOW THIS SHOULD BE AUTOMATIC! but the button doesn't hurt...
		connect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int count = primitives.getSelectionCount();
				if (count != 2) {
					showError("Please select the two primitives you want to connect.");
				}
				else {
					int[] primindexes = primitives.getSelectionIndices();
					engManager.blindConnectRemotePrimsIndexes(primindexes[0],primindexes[1]);
				}
			}
		});

		final Button refreshp = new Button(buttonArea,SWT.PUSH);
		refreshp.setText("Refresh");
		// Refresh last list of primitives on an engine, and sends a request to selected engine
		// to retrieve most recent primitive list (button refreshp)
		// NOW THIS SHOULD BE AUTOMATIC! but the button doesn't hurt...
		refreshp.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Number of engines in list: "+engines.getItemCount());
				String engk = engines.getItem(engines.getSelectionIndex());
				System.out.println("Found engine key "+engk);
				engManager.requestPrimitives(engk);
			}
		});


		
		// INITIAL VALUES FOR LISTS:
		// ADDING ENGINE MANUALLY AT STARTUP!
		//engManager.addEngine("local", "127.0.0.1", 9010);
		//engines.setItems(new String[] {"local@127.0.0.1:9010"});
				//engManager.engines().keys().toList().toArray());
		//engManager.requestPrimitives("geuze@127.0.0.1:9010");
		//primitives.setItems(new String[] {"prim1","prim2","..."});



		// Hook up a selection listener.
		final ISelectionService service = getSite().getWorkbenchWindow().getSelectionService();
		//service.addSelectionListener(this);
		
		

		// Callback functions
		final ManagerTrait listener = new ManagerTrait () {

			//@Override
			public void updatePrimitives(final String engk, final String[] arg0) {
				//System.out.println("Reply from primitives request.");
				//for (String str : arg0) {
				//	System.out.println(" - "+str);
				//}

				getSite().getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						
						primitives.removeAll();			
						primitives.setItems(engManager.existingPrimitives());
						String result = "Got list of primitives after updating "+engk+":\n";
						for (String str : arg0) {
							result = (result + str + "\n");
						}
												
						// Logging result in output box
						output.setText(result);
						
						// update selection of primitives
						for (Object element : ((IStructuredSelection) service.getSelection()).toArray()) {
							
							if (element instanceof ConnectorEditPart || element instanceof ConnectorCompartmentEditPart) {
								Connector conn = (Connector) ((GraphicalEditPart) element).getNotationView().getElement();
								for (Object prim : conn.getPrimitives()) {
									if (engManager.hasManPrim(prim.hashCode())) {
										System.out.println("selecting reader/writer with index/hashcode: "+engManager.getManPrimIndex(prim.hashCode())+"/"+prim.hashCode());
										primitives.select(engManager.getManPrimIndex(prim.hashCode()));
									}
								}
							}
						}
				   }
				});
			}

			//@Override
			public void updateStatus(final String arg0) {
				System.out.println("Reply from status request");// - "+arg0);
				if(askedStatus[0]) {
					askedStatus[0] = false;
					getSite().getShell().getDisplay().asyncExec(new Runnable() {
						public void run() {
							final String result = "Status of "+
												  engines.getItem(engines.getSelectionIndex())+
							                      ":\n"+arg0;
							output.setText(result);
						}
					});
				}
			}

			//@Override
			public void updateTimeOut() {
				System.out.println("Timed out!");
				getSite().getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						output.setText("Timed out!");
						MessageDialog.openInformation(
								getSite().getWorkbenchWindow().getWorkbench().getActiveWorkbenchWindow().getShell(), 
								"ERROR", "Timed out!");
					}
				});


			}

			//@Override
			public void updateEngines(String[] arg0) {
				// TODO Auto-generated method stub
				
			}

			//@Override
			public void updateError(final String msg) {
				System.out.println("Error: "+msg);
				getSite().getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						output.setText("Error: "+msg);
						MessageDialog.openInformation(
								getSite().getWorkbenchWindow().getWorkbench().getActiveWorkbenchWindow().getShell(), 
								"ERROR", msg);

					}
				});

			}
    		
    	};

		
		// Hook up as listener of the engine manager
    	engManager.addListener(listener);

		// initial update
		updateSelection(null, service.getSelection(),primitives);
		// event
		workbenchListener = new ISelectionListener() {
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				updateSelection(part, selection, primitives);
			}
		};
		service.addSelectionListener(workbenchListener);

	}

	@Override
	public void dispose() {
		super.dispose();
		if (workbenchListener!=null) {
			getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(workbenchListener);
			workbenchListener = null;
		}
	}
	
	/////////////
	// auxiliary methods
	/////////////
	
	/*
	 * Update the group according to the current selection.
	 */
	private void updateSelection(IWorkbenchPart part, ISelection selection, List primitives) {
		
		if (selection==null) return;
		
		//System.out.println("Got selection:\n-- "+
		//		selection.toString()+
		//		"\nwith type:\n-- "+
		//		selection.getClass().toString()+
		//		"\nwith hash of 1st elem: "+
		//		((IStructuredSelection)selection).getFirstElement().hashCode());

		if (!(selection instanceof IStructuredSelection)) return;
		Object first = ((IStructuredSelection) selection).getFirstElement();
		if (first == null) return;
		if (first instanceof ConnectorEditPart || first instanceof ConnectorCompartmentEditPart) {
			engManager.cleanUndeployed();
		}
		
		primitives.deselectAll();
		for (Object element : ((IStructuredSelection) selection).toArray()) {
			
			// load a connector if it is selected
			if (element instanceof ConnectorEditPart || element instanceof ConnectorCompartmentEditPart) {
				Connector connector = (Connector) ((GraphicalEditPart) element).getNotationView().getElement();
				loadPrimitives(connector,primitives);
				// get the new primitives, and refresh the prim list.
				//engManager.requestPrimitives();
			}
			
			// clean primitive list if background is selected
			else if (element instanceof ModuleEditPart) {
				System.out.println("Removing undeployed parts...");
				engManager.cleanUndeployed();
				//engManager.requestPrimitives();
			}
			
			// select the right primitive on the list if it is selected on the editor
			else if (element instanceof ConnectionNodeEditPart) {
				Primitive prim = (Primitive) ((ConnectionNodeEditPart) element).getNotationView().getElement();
				if (engManager.hasManPrim(prim.hashCode())) {
					System.out.println("selecting primitive with index/hashcode: "+engManager.getManPrimIndex(prim.hashCode())+"/"+prim.hashCode());
					primitives.select(engManager.getManPrimIndex(prim.hashCode()));
				}
				else {
					System.out.println("primitive not found: "+prim.getName());
				}
			}
			else if (element instanceof ShapeNodeEditPart) {
				Primitive prim = (Primitive) ((ShapeNodeEditPart) element).getNotationView().getElement();
				if (engManager.hasManPrim(prim.hashCode())) {
					System.out.println("selecting reader/writer with index/hashcode: "+engManager.getManPrimIndex(prim.hashCode())+"/"+prim.hashCode());
					primitives.select(engManager.getManPrimIndex(prim.hashCode()));
				}
				else {
					System.out.println("primitive not found: "+prim.getName());
				}
			}

			// select the right node on the list if it is selected on the editor
			/*
			else if (element instanceof NodeEditPart) {
			
				Node node = (Node) ((NodeEditPart) element).getNotationView().getElement();
				scala.Option optprimk = engManager.getPrimKey(node);
				if (optprimk.isDefined()) {
					int index = primitives.indexOf((String) optprimk.get());
					if (index!=-1) primitives.select(index);
				}
			}
			*/
		}
		
	}
	
	private void showError(String msg) {
		MessageDialog.openInformation(
				getSite().getWorkbenchWindow().getWorkbench().getActiveWorkbenchWindow().getShell(), 
				"ERROR", msg);
		Reo.logError(msg);
	}
	private void loadPrimitives(Connector conn, List primitives) {
		System.out.println("Loading connector: "+conn+"/prims: "+conn.getPrimitives());

		String type = "";
		Object[] args = null;
		//##Array args2 = null;
		//Iterator<Primitive> primIter  = conn.getPrimitives().iterator();
		//for (Component prim : conns) {
		for (Object prim : conn.getPrimitives()) {
			//System.out.println("LOADING PRIMITIVE: "+prim.getPackage());
			//System.out.println("creating primitive: "+prim);
	        //if      (prim.getPackage().equals("Sync")) {
			if (prim instanceof Sync) {
	        	type = "Sync";
	        	Object[] myargs1 = {};
	        	////Object[] myargs = {engManager.newPrimName(type)};
	        	//##Array myargs2 = new Array(0);
	        	args = myargs1;
	        	//##args2 = myargs2;
	        }
	        else if (prim instanceof SyncDrain) {
	        	type = "SyncDrain";
	        	Object[] myargs = {};
	        	args = myargs;
	        }
	        else if (prim instanceof AsyncDrain) {
	        	type = "AsyncDrain";
	        	Object[] myargs = {};
	        	args = myargs;
	        }
	        else if (prim instanceof SyncSpout) {
	        	type = "SyncSpout";
	        	Object[] myargs = {};
	        	args = myargs;
	        }
	        else if (prim instanceof AsyncSpout) {
	        	type = "AsyncSpout";
	        	Object[] myargs = {};
	        	args = myargs;
	        }
	        else if (prim instanceof LossySync) {
	        	type = "LossySync";
	        	Object[] myargs = {};
	        	args = myargs;
	        }
	        else if (prim instanceof FIFO) {
	        	System.out.println("ADDING FIFO/DFVAR");
	        	type = "FIFO1";
	        	String nodename = ((FIFO) prim).getNodeTwo().getName();
	        	if (nodename != null)
	        		if (nodename.toLowerCase().equals("var")) 
	        			type = "DfVar";
	        	System.out.println("ADDED - "+type);
	        	if (((FIFO) prim).isFull()) {
		        	Object[] myargs = {prim.hashCode(),true};
		        	args = myargs;	        		
	        	}
	        	else {
	        		Object[] myargs = {prim.hashCode(),""};
	        		args = myargs;
	        	}
	        }
	        //else
	        	//System.out.println("Analysing a component with package: "+prim.getPackage());
	        
	        if (!(type.equals("")) && args != null) {
	        	EList<SourceEnd> srcs = ((Primitive) prim).getSourceEnds();
	        	EList<SinkEnd> snks = ((Primitive) prim).getSinkEnds();
	        	int[] srcRef = new int[srcs.size()];
	        	int[] snkRef = new int[snks.size()];
	        	boolean[] srcIsRouter = new boolean[srcs.size()];
	        	boolean[] snkIsRouter = new boolean[snks.size()];
	        	int i = 0;
	        	for (SourceEnd end : srcs) {
	        		srcRef[i] = end.getNode().hashCode();
	        		boolean b = end.getNode().getType().equals(org.ect.reo.NodeType.ROUTE);
	        		System.out.println("-- "+b);
	        		srcIsRouter[i] = b;
	        		i++;
	        	}
	        	i = 0;
	        	for (SinkEnd end : snks) {
	        		snkRef[i] = end.getNode().hashCode();
	        		snkIsRouter[i] = end.getNode().getType().equals(org.ect.reo.NodeType.ROUTE);
	        		i++;
	        	}
	    		System.out.println("Adding primitive!: "+type);
	        	engManager.addPrimitive(type, args, srcRef, srcIsRouter,
	        			snkRef, snkIsRouter, prim.hashCode());
	        }
	        else
	    		System.out.println("strange...: "+type+"/"+args.length);
		}
		
		Network net = new Network(conn);
		net.update(); // add referenced components
		EList<Component> conns = net.getComponents();
		System.out.println("Loading components: "+net.getComponents());

		for (Object prim : conns) {
			if (prim instanceof Reader) {
				Reader rd = (Reader) prim;
	        	Object[] myargs = {prim.hashCode(),rd.getRequests()};
	        	SourceEnd src = rd.getSourceEnd();
	        	int[]  srcRef = {src.getNode().hashCode()};
	        	boolean[]  srcIsRouter = {src.getNode().getType().equals(org.ect.reo.NodeType.ROUTE)};
	        	engManager.addPrimitive("Reader", myargs, srcRef, srcIsRouter,
	        			new int[0], new boolean[0], prim.hashCode());
	        	
			}
			else if (prim instanceof Writer) {
				Writer wr = (Writer) prim;
				scala.collection.immutable.List newlist = Nil$.MODULE$;
//	        	scala.List list = null;
	        	if (wr.getRequests() >= 0) {
//	                list = scala.List$.MODULE$.range(0,wr.getRequests());
	                for (int i = 0; i<wr.getRequests(); i++) {
	                	newlist = $colon$colon$.MODULE$.apply((Integer) i, newlist);
	                }
	        	}
	              else {
//	            	  list = scala.List$.MODULE$.range(0,100);
		                for (int i = 0; i<100; i++) {
		                	newlist = $colon$colon$.MODULE$.apply((Integer) i, newlist);
		                }
	              }
	        	//Object[] myargs = {prim.hashCode(),list.toArray()};
	        	if (((Writer) prim).getName().equals("Writer")) {
//	        		Object[] myargs = {prim.hashCode(),list};
	        		Object[] myargs = {prim.hashCode(),newlist};
	        		args = myargs;
	        	}
	        	else {
//	        		Object[] myargs = {prim.hashCode(),list,((Writer) prim).getName()};
	        		Object[] myargs = {prim.hashCode(),newlist,((Writer) prim).getName()};
	        		args = myargs;
	        	}
	        	SinkEnd snk = wr.getSinkEnd();
	        	int[] snkRef = {snk.getNode().hashCode()};
	        	boolean[]  snkIsRouter = {snk.getNode().getType().equals(org.ect.reo.NodeType.ROUTE)};
	        	engManager.addPrimitive("Writer", args, new int[0], new boolean[0],
	        			snkRef, snkIsRouter, prim.hashCode());
				
			}			
		}


		
		/* NOT ADDING NODES!
		type = "";
		args = null;
		for (Object elem : conn.getNodes()) {
			if (elem instanceof Node) {
				Node node = (Node) elem;
		        if (node.getType() == NodeType.REPLICATE) {
		        	Object[] myargs = {false};
		        	args = myargs;
		        }
		        else if (node.getType() == NodeType.ROUTE) {
		        	Object[] myargs = {true};
		        	args = myargs;
		        }
		        if (args != null)
		        	engManager.addNode(engManager.newPrimName("node"),
		        			engManager.seed(), args, node);
			}
		        
		}
		*/

	}
	
	
	/** request new status, and refresh screen with last received status. */
	private void refreshEngineStatus(String engk, final Text output) {
		engManager.requestStatus(engk);
		//output.setText(this.getTraces().mkString("\n"));			
    }
	

	
	@Override
	public void setFocus() {
	}

	/*
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) return;
		IStructuredSelection sel = (IStructuredSelection) selection;
		
		Object selected = sel.getFirstElement();
		if (selected instanceof ConnectorEditPart) {
			//connector = (Connector) ((ConnectorEditPart) selected).getNotationView().getElement();
			//label.setText("Connector changed: " + connector);
			
		}
		
	}
	*/
	
	public void addEngine(String name, String ip, int port) {
		
		if (name.equals("local") && ip.equals("127.0.0.1") && port==9010) {
			Engine eng = new Engine(name,ip,port,new OutboxLogger(outbox));
    		eng.start();    		
    		System.out.println("Now running: "+name+"@"+ip+":"+eng.port());
		}
		engManager.addEngine(name,ip,port);
		engines.add(name+"@"+ip+":"+port);
		engines.select(engines.getItemCount()-1);
	}

	public void pageActivated(IWorkbenchPage page) {
		// TODO Auto-generated method stub
		System.out.println("page activated");
		
	}

	public void pageClosed(IWorkbenchPage page) {
		// TODO Auto-generated method stub
		System.out.println("page closed.");
		engManager.stopListening();

		
	}

	public void pageOpened(IWorkbenchPage page) {
		// TODO Auto-generated method stub
		System.out.println("page openened.");
		
	}
	
	class OutboxLogger extends SeqChartLogger {
		
		final Text output;
		Logger logger = this;
		
		OutboxLogger(Text output) {
			this.output = output;
		}

		@Override
		public void updConfigs(String arg0) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					output.setText(logger.getConfigs().mkString("\n"));
				}
			});
		}

		@Override
		public void updTraces(String arg0) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					output.setText(logger.getTraces().mkString("\n"));
				}
			});
		}

		@Override
		public void updWarnings(String arg0) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					output.setText(logger.getWarnings().mkString("\n"));
				}
			});
		}
		
	}

}
