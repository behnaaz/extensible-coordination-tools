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
package org.ect.reo.channels;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.reo.ReoPackage;
import org.ect.reo.animation.AnimationPackage;
import org.ect.reo.colouring.ColouringPackage;
import org.ect.reo.components.ComponentsPackage;



/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the Channels model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ect.reo.channels.ChannelsFactory
 * @model kind="package"
 * @generated
 */
public class ChannelsPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "channels";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/reo/channels";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "channels";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ChannelsPackage eINSTANCE = org.ect.reo.channels.ChannelsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.Channel <em>Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.Channel
	 * @see org.ect.reo.channels.ChannelsPackage#getChannel()
	 * @generated
	 */
	public static final int CHANNEL = 0;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__ARRIVAL_RATE = ReoPackage.PRIMITIVE__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__PROCESSING_DELAY = ReoPackage.PRIMITIVE__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__RECONF_ACTIONS = ReoPackage.PRIMITIVE__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__PROPERTIES = ReoPackage.PRIMITIVE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__SOURCE_ENDS = ReoPackage.PRIMITIVE__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__SINK_ENDS = ReoPackage.PRIMITIVE__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__CHANNEL_END_ONE = ReoPackage.PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__CHANNEL_END_TWO = ReoPackage.PRIMITIVE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__NODE_ONE = ReoPackage.PRIMITIVE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__NODE_TWO = ReoPackage.PRIMITIVE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__CHANNEL_END_NAME_ONE = ReoPackage.PRIMITIVE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL__CHANNEL_END_NAME_TWO = ReoPackage.PRIMITIVE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CHANNEL_FEATURE_COUNT = ReoPackage.PRIMITIVE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.DirectedChannel <em>Directed Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.DirectedChannel
	 * @see org.ect.reo.channels.ChannelsPackage#getDirectedChannel()
	 * @generated
	 */
	public static final int DIRECTED_CHANNEL = 1;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__ARRIVAL_RATE = CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__PROCESSING_DELAY = CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__RECONF_ACTIONS = CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__PROPERTIES = CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__SOURCE_ENDS = CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__SINK_ENDS = CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__CHANNEL_END_ONE = CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__CHANNEL_END_TWO = CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__NODE_ONE = CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__NODE_TWO = CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE = CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO = CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__SOURCE_END = CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL__SINK_END = CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Directed Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.DrainChannel <em>Drain Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.DrainChannel
	 * @see org.ect.reo.channels.ChannelsPackage#getDrainChannel()
	 * @generated
	 */
	public static final int DRAIN_CHANNEL = 2;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__ARRIVAL_RATE = CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__PROCESSING_DELAY = CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__RECONF_ACTIONS = CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__PROPERTIES = CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__SOURCE_ENDS = CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__SINK_ENDS = CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__CHANNEL_END_ONE = CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__CHANNEL_END_TWO = CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__NODE_ONE = CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__NODE_TWO = CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__CHANNEL_END_NAME_ONE = CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__CHANNEL_END_NAME_TWO = CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__SOURCE_END_ONE = CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL__SOURCE_END_TWO = CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Drain Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DRAIN_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.SpoutChannel <em>Spout Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.SpoutChannel
	 * @see org.ect.reo.channels.ChannelsPackage#getSpoutChannel()
	 * @generated
	 */
	public static final int SPOUT_CHANNEL = 3;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__ARRIVAL_RATE = CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__PROCESSING_DELAY = CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__RECONF_ACTIONS = CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__PROPERTIES = CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__SOURCE_ENDS = CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__SINK_ENDS = CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__CHANNEL_END_ONE = CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__CHANNEL_END_TWO = CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__NODE_ONE = CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__NODE_TWO = CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__CHANNEL_END_NAME_ONE = CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__CHANNEL_END_NAME_TWO = CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Sink End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__SINK_END_ONE = CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sink End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL__SINK_END_TWO = CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Spout Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SPOUT_CHANNEL_FEATURE_COUNT = CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.Sync <em>Sync</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.Sync
	 * @see org.ect.reo.channels.ChannelsPackage#getSync()
	 * @generated
	 */
	public static final int SYNC = 4;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The number of structural features of the '<em>Sync</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.LossySync <em>Lossy Sync</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.LossySync
	 * @see org.ect.reo.channels.ChannelsPackage#getLossySync()
	 * @generated
	 */
	public static final int LOSSY_SYNC = 5;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The number of structural features of the '<em>Lossy Sync</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_SYNC_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.FIFO <em>FIFO</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.FIFO
	 * @see org.ect.reo.channels.ChannelsPackage#getFIFO()
	 * @generated
	 */
	public static final int FIFO = 6;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The feature id for the '<em><b>Full</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__FULL = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cell Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__CELL_NAME = DIRECTED_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO__INITIAL_VALUE = DIRECTED_CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>FIFO</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FIFO_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.SyncDrain <em>Sync Drain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.SyncDrain
	 * @see org.ect.reo.channels.ChannelsPackage#getSyncDrain()
	 * @generated
	 */
	public static final int SYNC_DRAIN = 8;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.AsyncDrain <em>Async Drain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.AsyncDrain
	 * @see org.ect.reo.channels.ChannelsPackage#getAsyncDrain()
	 * @generated
	 */
	public static final int ASYNC_DRAIN = 9;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.SyncSpout <em>Sync Spout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.SyncSpout
	 * @see org.ect.reo.channels.ChannelsPackage#getSyncSpout()
	 * @generated
	 */
	public static final int SYNC_SPOUT = 10;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.AsyncSpout <em>Async Spout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.AsyncSpout
	 * @see org.ect.reo.channels.ChannelsPackage#getAsyncSpout()
	 * @generated
	 */
	public static final int ASYNC_SPOUT = 11;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.LossyFIFO <em>Lossy FIFO</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.LossyFIFO
	 * @see org.ect.reo.channels.ChannelsPackage#getLossyFIFO()
	 * @generated
	 */
	public static final int LOSSY_FIFO = 7;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The feature id for the '<em><b>Shift</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__SHIFT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Full</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__FULL = DIRECTED_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cell Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO__CELL_NAME = DIRECTED_CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Lossy FIFO</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOSSY_FIFO_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__ARRIVAL_RATE = DRAIN_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__PROCESSING_DELAY = DRAIN_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__RECONF_ACTIONS = DRAIN_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__PROPERTIES = DRAIN_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__SOURCE_ENDS = DRAIN_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__SINK_ENDS = DRAIN_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__CHANNEL_END_ONE = DRAIN_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__CHANNEL_END_TWO = DRAIN_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__NODE_ONE = DRAIN_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__NODE_TWO = DRAIN_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__CHANNEL_END_NAME_ONE = DRAIN_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__CHANNEL_END_NAME_TWO = DRAIN_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__SOURCE_END_ONE = DRAIN_CHANNEL__SOURCE_END_ONE;

	/**
	 * The feature id for the '<em><b>Source End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__SOURCE_END_TWO = DRAIN_CHANNEL__SOURCE_END_TWO;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN__EXPRESSION = DRAIN_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sync Drain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_DRAIN_FEATURE_COUNT = DRAIN_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__ARRIVAL_RATE = DRAIN_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__PROCESSING_DELAY = DRAIN_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__RECONF_ACTIONS = DRAIN_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__PROPERTIES = DRAIN_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__SOURCE_ENDS = DRAIN_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__SINK_ENDS = DRAIN_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__CHANNEL_END_ONE = DRAIN_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__CHANNEL_END_TWO = DRAIN_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__NODE_ONE = DRAIN_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__NODE_TWO = DRAIN_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__CHANNEL_END_NAME_ONE = DRAIN_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__CHANNEL_END_NAME_TWO = DRAIN_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__SOURCE_END_ONE = DRAIN_CHANNEL__SOURCE_END_ONE;

	/**
	 * The feature id for the '<em><b>Source End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN__SOURCE_END_TWO = DRAIN_CHANNEL__SOURCE_END_TWO;

	/**
	 * The number of structural features of the '<em>Async Drain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_DRAIN_FEATURE_COUNT = DRAIN_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__ARRIVAL_RATE = SPOUT_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__PROCESSING_DELAY = SPOUT_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__RECONF_ACTIONS = SPOUT_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__PROPERTIES = SPOUT_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__SOURCE_ENDS = SPOUT_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__SINK_ENDS = SPOUT_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__CHANNEL_END_ONE = SPOUT_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__CHANNEL_END_TWO = SPOUT_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__NODE_ONE = SPOUT_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__NODE_TWO = SPOUT_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__CHANNEL_END_NAME_ONE = SPOUT_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__CHANNEL_END_NAME_TWO = SPOUT_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Sink End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__SINK_END_ONE = SPOUT_CHANNEL__SINK_END_ONE;

	/**
	 * The feature id for the '<em><b>Sink End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT__SINK_END_TWO = SPOUT_CHANNEL__SINK_END_TWO;

	/**
	 * The number of structural features of the '<em>Sync Spout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYNC_SPOUT_FEATURE_COUNT = SPOUT_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__ARRIVAL_RATE = SPOUT_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__PROCESSING_DELAY = SPOUT_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__RECONF_ACTIONS = SPOUT_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__PROPERTIES = SPOUT_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__SOURCE_ENDS = SPOUT_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__SINK_ENDS = SPOUT_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__CHANNEL_END_ONE = SPOUT_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__CHANNEL_END_TWO = SPOUT_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__NODE_ONE = SPOUT_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__NODE_TWO = SPOUT_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__CHANNEL_END_NAME_ONE = SPOUT_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__CHANNEL_END_NAME_TWO = SPOUT_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Sink End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__SINK_END_ONE = SPOUT_CHANNEL__SINK_END_ONE;

	/**
	 * The feature id for the '<em><b>Sink End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT__SINK_END_TWO = SPOUT_CHANNEL__SINK_END_TWO;

	/**
	 * The number of structural features of the '<em>Async Spout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASYNC_SPOUT_FEATURE_COUNT = SPOUT_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.Filter <em>Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.Filter
	 * @see org.ect.reo.channels.ChannelsPackage#getFilter()
	 * @generated
	 */
	public static final int FILTER = 12;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER__EXPRESSION = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FILTER_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.Transform <em>Transform</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.Transform
	 * @see org.ect.reo.channels.ChannelsPackage#getTransform()
	 * @generated
	 */
	public static final int TRANSFORM = 13;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM__EXPRESSION = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSFORM_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.Timer <em>Timer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.Timer
	 * @see org.ect.reo.channels.ChannelsPackage#getTimer()
	 * @generated
	 */
	public static final int TIMER = 14;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER__TIMEOUT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Timer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TIMER_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.CustomDirectedChannel <em>Custom Directed Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.CustomDirectedChannel
	 * @see org.ect.reo.channels.ChannelsPackage#getCustomDirectedChannel()
	 * @generated
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL = 15;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__NAME = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__TYPE_URI = DIRECTED_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__RESOLVED = DIRECTED_CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__FOREGROUND_COLOR = DIRECTED_CHANNEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL__SYNCHRONOUS = DIRECTED_CHANNEL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Custom Directed Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DIRECTED_CHANNEL_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.CustomDrainChannel <em>Custom Drain Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.CustomDrainChannel
	 * @see org.ect.reo.channels.ChannelsPackage#getCustomDrainChannel()
	 * @generated
	 */
	public static final int CUSTOM_DRAIN_CHANNEL = 16;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__ARRIVAL_RATE = DRAIN_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__PROCESSING_DELAY = DRAIN_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__RECONF_ACTIONS = DRAIN_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__PROPERTIES = DRAIN_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__SOURCE_ENDS = DRAIN_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__SINK_ENDS = DRAIN_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__CHANNEL_END_ONE = DRAIN_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__CHANNEL_END_TWO = DRAIN_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__NODE_ONE = DRAIN_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__NODE_TWO = DRAIN_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__CHANNEL_END_NAME_ONE = DRAIN_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__CHANNEL_END_NAME_TWO = DRAIN_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__SOURCE_END_ONE = DRAIN_CHANNEL__SOURCE_END_ONE;

	/**
	 * The feature id for the '<em><b>Source End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__SOURCE_END_TWO = DRAIN_CHANNEL__SOURCE_END_TWO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__NAME = DRAIN_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__TYPE_URI = DRAIN_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__RESOLVED = DRAIN_CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__FOREGROUND_COLOR = DRAIN_CHANNEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL__SYNCHRONOUS = DRAIN_CHANNEL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Custom Drain Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_DRAIN_CHANNEL_FEATURE_COUNT = DRAIN_CHANNEL_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.CustomSpoutChannel <em>Custom Spout Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.CustomSpoutChannel
	 * @see org.ect.reo.channels.ChannelsPackage#getCustomSpoutChannel()
	 * @generated
	 */
	public static final int CUSTOM_SPOUT_CHANNEL = 17;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__ARRIVAL_RATE = SPOUT_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__PROCESSING_DELAY = SPOUT_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__RECONF_ACTIONS = SPOUT_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__PROPERTIES = SPOUT_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__SOURCE_ENDS = SPOUT_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__SINK_ENDS = SPOUT_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__CHANNEL_END_ONE = SPOUT_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__CHANNEL_END_TWO = SPOUT_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__NODE_ONE = SPOUT_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__NODE_TWO = SPOUT_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__CHANNEL_END_NAME_ONE = SPOUT_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__CHANNEL_END_NAME_TWO = SPOUT_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Sink End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__SINK_END_ONE = SPOUT_CHANNEL__SINK_END_ONE;

	/**
	 * The feature id for the '<em><b>Sink End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__SINK_END_TWO = SPOUT_CHANNEL__SINK_END_TWO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__NAME = SPOUT_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__TYPE_URI = SPOUT_CHANNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__RESOLVED = SPOUT_CHANNEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR = SPOUT_CHANNEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS = SPOUT_CHANNEL_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Custom Spout Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SPOUT_CHANNEL_FEATURE_COUNT = SPOUT_CHANNEL_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.PrioritySync <em>Priority Sync</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.PrioritySync
	 * @see org.ect.reo.channels.ChannelsPackage#getPrioritySync()
	 * @generated
	 */
	public static final int PRIORITY_SYNC = 18;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The number of structural features of the '<em>Priority Sync</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIORITY_SYNC_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.BlockingSourceSync <em>Blocking Source Sync</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.BlockingSourceSync
	 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSourceSync()
	 * @generated
	 */
	public static final int BLOCKING_SOURCE_SYNC = 19;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The number of structural features of the '<em>Blocking Source Sync</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SOURCE_SYNC_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.BlockingSinkSync <em>Blocking Sink Sync</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.BlockingSinkSync
	 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSinkSync()
	 * @generated
	 */
	public static final int BLOCKING_SINK_SYNC = 20;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The number of structural features of the '<em>Blocking Sink Sync</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SINK_SYNC_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.channels.BlockingSync <em>Blocking Sync</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.channels.BlockingSync
	 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSync()
	 * @generated
	 */
	public static final int BLOCKING_SYNC = 21;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__ARRIVAL_RATE = DIRECTED_CHANNEL__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__PROCESSING_DELAY = DIRECTED_CHANNEL__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__RECONF_ACTIONS = DIRECTED_CHANNEL__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__PROPERTIES = DIRECTED_CHANNEL__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__SOURCE_ENDS = DIRECTED_CHANNEL__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__SINK_ENDS = DIRECTED_CHANNEL__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Channel End One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__CHANNEL_END_ONE = DIRECTED_CHANNEL__CHANNEL_END_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__CHANNEL_END_TWO = DIRECTED_CHANNEL__CHANNEL_END_TWO;

	/**
	 * The feature id for the '<em><b>Node One</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__NODE_ONE = DIRECTED_CHANNEL__NODE_ONE;

	/**
	 * The feature id for the '<em><b>Node Two</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__NODE_TWO = DIRECTED_CHANNEL__NODE_TWO;

	/**
	 * The feature id for the '<em><b>Channel End Name One</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__CHANNEL_END_NAME_ONE = DIRECTED_CHANNEL__CHANNEL_END_NAME_ONE;

	/**
	 * The feature id for the '<em><b>Channel End Name Two</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__CHANNEL_END_NAME_TWO = DIRECTED_CHANNEL__CHANNEL_END_NAME_TWO;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__SOURCE_END = DIRECTED_CHANNEL__SOURCE_END;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC__SINK_END = DIRECTED_CHANNEL__SINK_END;

	/**
	 * The number of structural features of the '<em>Blocking Sync</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BLOCKING_SYNC_FEATURE_COUNT = DIRECTED_CHANNEL_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass channelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directedChannelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass drainChannelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spoutChannelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass syncEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lossySyncEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fifoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass syncDrainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asyncDrainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass syncSpoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asyncSpoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customDirectedChannelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customDrainChannelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customSpoutChannelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass prioritySyncEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockingSourceSyncEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockingSinkSyncEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockingSyncEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lossyFIFOEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.ect.reo.channels.ChannelsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ChannelsPackage() {
		super(eNS_URI, ChannelsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ChannelsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ChannelsPackage init() {
		if (isInited) return (ChannelsPackage)EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI);

		// Obtain or create and register package
		ChannelsPackage theChannelsPackage = (ChannelsPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ChannelsPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ChannelsPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		ReoPackage theReoPackage = (ReoPackage)(EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) instanceof ReoPackage ? EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) : ReoPackage.eINSTANCE);
		ColouringPackage theColouringPackage = (ColouringPackage)(EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) instanceof ColouringPackage ? EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) : ColouringPackage.eINSTANCE);
		AnimationPackage theAnimationPackage = (AnimationPackage)(EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) instanceof AnimationPackage ? EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) : AnimationPackage.eINSTANCE);
		ComponentsPackage theComponentsPackage = (ComponentsPackage)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackage ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);

		// Create package meta-data objects
		theChannelsPackage.createPackageContents();
		theReoPackage.createPackageContents();
		theColouringPackage.createPackageContents();
		theAnimationPackage.createPackageContents();
		theComponentsPackage.createPackageContents();

		// Initialize created meta-data
		theChannelsPackage.initializePackageContents();
		theReoPackage.initializePackageContents();
		theColouringPackage.initializePackageContents();
		theAnimationPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theChannelsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ChannelsPackage.eNS_URI, theChannelsPackage);
		return theChannelsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.Channel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Channel</em>'.
	 * @see org.ect.reo.channels.Channel
	 * @generated
	 */
	public EClass getChannel() {
		return channelEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.Channel#getChannelEndOne <em>Channel End One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Channel End One</em>'.
	 * @see org.ect.reo.channels.Channel#getChannelEndOne()
	 * @see #getChannel()
	 * @generated
	 */
	public EReference getChannel_ChannelEndOne() {
		return (EReference)channelEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.Channel#getChannelEndTwo <em>Channel End Two</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Channel End Two</em>'.
	 * @see org.ect.reo.channels.Channel#getChannelEndTwo()
	 * @see #getChannel()
	 * @generated
	 */
	public EReference getChannel_ChannelEndTwo() {
		return (EReference)channelEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.Channel#getNodeOne <em>Node One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node One</em>'.
	 * @see org.ect.reo.channels.Channel#getNodeOne()
	 * @see #getChannel()
	 * @generated
	 */
	public EReference getChannel_NodeOne() {
		return (EReference)channelEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.Channel#getNodeTwo <em>Node Two</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node Two</em>'.
	 * @see org.ect.reo.channels.Channel#getNodeTwo()
	 * @see #getChannel()
	 * @generated
	 */
	public EReference getChannel_NodeTwo() {
		return (EReference)channelEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.Channel#getChannelEndNameOne <em>Channel End Name One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Channel End Name One</em>'.
	 * @see org.ect.reo.channels.Channel#getChannelEndNameOne()
	 * @see #getChannel()
	 * @generated
	 */
	public EAttribute getChannel_ChannelEndNameOne() {
		return (EAttribute)channelEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.Channel#getChannelEndNameTwo <em>Channel End Name Two</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Channel End Name Two</em>'.
	 * @see org.ect.reo.channels.Channel#getChannelEndNameTwo()
	 * @see #getChannel()
	 * @generated
	 */
	public EAttribute getChannel_ChannelEndNameTwo() {
		return (EAttribute)channelEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.DirectedChannel <em>Directed Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Directed Channel</em>'.
	 * @see org.ect.reo.channels.DirectedChannel
	 * @generated
	 */
	public EClass getDirectedChannel() {
		return directedChannelEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.DirectedChannel#getSourceEnd <em>Source End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source End</em>'.
	 * @see org.ect.reo.channels.DirectedChannel#getSourceEnd()
	 * @see #getDirectedChannel()
	 * @generated
	 */
	public EReference getDirectedChannel_SourceEnd() {
		return (EReference)directedChannelEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.DirectedChannel#getSinkEnd <em>Sink End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sink End</em>'.
	 * @see org.ect.reo.channels.DirectedChannel#getSinkEnd()
	 * @see #getDirectedChannel()
	 * @generated
	 */
	public EReference getDirectedChannel_SinkEnd() {
		return (EReference)directedChannelEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.DrainChannel <em>Drain Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drain Channel</em>'.
	 * @see org.ect.reo.channels.DrainChannel
	 * @generated
	 */
	public EClass getDrainChannel() {
		return drainChannelEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.DrainChannel#getSourceEndOne <em>Source End One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source End One</em>'.
	 * @see org.ect.reo.channels.DrainChannel#getSourceEndOne()
	 * @see #getDrainChannel()
	 * @generated
	 */
	public EReference getDrainChannel_SourceEndOne() {
		return (EReference)drainChannelEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.DrainChannel#getSourceEndTwo <em>Source End Two</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source End Two</em>'.
	 * @see org.ect.reo.channels.DrainChannel#getSourceEndTwo()
	 * @see #getDrainChannel()
	 * @generated
	 */
	public EReference getDrainChannel_SourceEndTwo() {
		return (EReference)drainChannelEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.SpoutChannel <em>Spout Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Spout Channel</em>'.
	 * @see org.ect.reo.channels.SpoutChannel
	 * @generated
	 */
	public EClass getSpoutChannel() {
		return spoutChannelEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.SpoutChannel#getSinkEndOne <em>Sink End One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sink End One</em>'.
	 * @see org.ect.reo.channels.SpoutChannel#getSinkEndOne()
	 * @see #getSpoutChannel()
	 * @generated
	 */
	public EReference getSpoutChannel_SinkEndOne() {
		return (EReference)spoutChannelEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.channels.SpoutChannel#getSinkEndTwo <em>Sink End Two</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sink End Two</em>'.
	 * @see org.ect.reo.channels.SpoutChannel#getSinkEndTwo()
	 * @see #getSpoutChannel()
	 * @generated
	 */
	public EReference getSpoutChannel_SinkEndTwo() {
		return (EReference)spoutChannelEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.Sync <em>Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sync</em>'.
	 * @see org.ect.reo.channels.Sync
	 * @generated
	 */
	public EClass getSync() {
		return syncEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.LossySync <em>Lossy Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lossy Sync</em>'.
	 * @see org.ect.reo.channels.LossySync
	 * @generated
	 */
	public EClass getLossySync() {
		return lossySyncEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.FIFO <em>FIFO</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FIFO</em>'.
	 * @see org.ect.reo.channels.FIFO
	 * @generated
	 */
	public EClass getFIFO() {
		return fifoEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.FIFO#isFull <em>Full</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full</em>'.
	 * @see org.ect.reo.channels.FIFO#isFull()
	 * @see #getFIFO()
	 * @generated
	 */
	public EAttribute getFIFO_Full() {
		return (EAttribute)fifoEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.FIFO#getCellName <em>Cell Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell Name</em>'.
	 * @see org.ect.reo.channels.FIFO#getCellName()
	 * @see #getFIFO()
	 * @generated
	 */
	public EAttribute getFIFO_CellName() {
		return (EAttribute)fifoEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.FIFO#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see org.ect.reo.channels.FIFO#getInitialValue()
	 * @see #getFIFO()
	 * @generated
	 */
	public EAttribute getFIFO_InitialValue() {
		return (EAttribute)fifoEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.SyncDrain <em>Sync Drain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sync Drain</em>'.
	 * @see org.ect.reo.channels.SyncDrain
	 * @generated
	 */
	public EClass getSyncDrain() {
		return syncDrainEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.AsyncDrain <em>Async Drain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Async Drain</em>'.
	 * @see org.ect.reo.channels.AsyncDrain
	 * @generated
	 */
	public EClass getAsyncDrain() {
		return asyncDrainEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.SyncSpout <em>Sync Spout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sync Spout</em>'.
	 * @see org.ect.reo.channels.SyncSpout
	 * @generated
	 */
	public EClass getSyncSpout() {
		return syncSpoutEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.AsyncSpout <em>Async Spout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Async Spout</em>'.
	 * @see org.ect.reo.channels.AsyncSpout
	 * @generated
	 */
	public EClass getAsyncSpout() {
		return asyncSpoutEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter</em>'.
	 * @see org.ect.reo.channels.Filter
	 * @generated
	 */
	public EClass getFilter() {
		return filterEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.Transform <em>Transform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transform</em>'.
	 * @see org.ect.reo.channels.Transform
	 * @generated
	 */
	public EClass getTransform() {
		return transformEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.Timer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer</em>'.
	 * @see org.ect.reo.channels.Timer
	 * @generated
	 */
	public EClass getTimer() {
		return timerEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.Timer#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see org.ect.reo.channels.Timer#getTimeout()
	 * @see #getTimer()
	 * @generated
	 */
	public EAttribute getTimer_Timeout() {
		return (EAttribute)timerEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.CustomDirectedChannel <em>Custom Directed Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Directed Channel</em>'.
	 * @see org.ect.reo.channels.CustomDirectedChannel
	 * @generated
	 */
	public EClass getCustomDirectedChannel() {
		return customDirectedChannelEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.CustomDrainChannel <em>Custom Drain Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Drain Channel</em>'.
	 * @see org.ect.reo.channels.CustomDrainChannel
	 * @generated
	 */
	public EClass getCustomDrainChannel() {
		return customDrainChannelEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.CustomSpoutChannel <em>Custom Spout Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Spout Channel</em>'.
	 * @see org.ect.reo.channels.CustomSpoutChannel
	 * @generated
	 */
	public EClass getCustomSpoutChannel() {
		return customSpoutChannelEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.PrioritySync <em>Priority Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Priority Sync</em>'.
	 * @see org.ect.reo.channels.PrioritySync
	 * @generated
	 */
	public EClass getPrioritySync() {
		return prioritySyncEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.BlockingSourceSync <em>Blocking Source Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Blocking Source Sync</em>'.
	 * @see org.ect.reo.channels.BlockingSourceSync
	 * @generated
	 */
	public EClass getBlockingSourceSync() {
		return blockingSourceSyncEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.BlockingSinkSync <em>Blocking Sink Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Blocking Sink Sync</em>'.
	 * @see org.ect.reo.channels.BlockingSinkSync
	 * @generated
	 */
	public EClass getBlockingSinkSync() {
		return blockingSinkSyncEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.BlockingSync <em>Blocking Sync</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Blocking Sync</em>'.
	 * @see org.ect.reo.channels.BlockingSync
	 * @generated
	 */
	public EClass getBlockingSync() {
		return blockingSyncEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.channels.LossyFIFO <em>Lossy FIFO</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lossy FIFO</em>'.
	 * @see org.ect.reo.channels.LossyFIFO
	 * @generated
	 */
	public EClass getLossyFIFO() {
		return lossyFIFOEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.LossyFIFO#isShift <em>Shift</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shift</em>'.
	 * @see org.ect.reo.channels.LossyFIFO#isShift()
	 * @see #getLossyFIFO()
	 * @generated
	 */
	public EAttribute getLossyFIFO_Shift() {
		return (EAttribute)lossyFIFOEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.LossyFIFO#isFull <em>Full</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full</em>'.
	 * @see org.ect.reo.channels.LossyFIFO#isFull()
	 * @see #getLossyFIFO()
	 * @generated
	 */
	public EAttribute getLossyFIFO_Full() {
		return (EAttribute)lossyFIFOEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.channels.LossyFIFO#getCellName <em>Cell Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cell Name</em>'.
	 * @see org.ect.reo.channels.LossyFIFO#getCellName()
	 * @see #getLossyFIFO()
	 * @generated
	 */
	public EAttribute getLossyFIFO_CellName() {
		return (EAttribute)lossyFIFOEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ChannelsFactory getChannelsFactory() {
		return (ChannelsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		channelEClass = createEClass(CHANNEL);
		createEReference(channelEClass, CHANNEL__CHANNEL_END_ONE);
		createEReference(channelEClass, CHANNEL__CHANNEL_END_TWO);
		createEReference(channelEClass, CHANNEL__NODE_ONE);
		createEReference(channelEClass, CHANNEL__NODE_TWO);
		createEAttribute(channelEClass, CHANNEL__CHANNEL_END_NAME_ONE);
		createEAttribute(channelEClass, CHANNEL__CHANNEL_END_NAME_TWO);

		directedChannelEClass = createEClass(DIRECTED_CHANNEL);
		createEReference(directedChannelEClass, DIRECTED_CHANNEL__SOURCE_END);
		createEReference(directedChannelEClass, DIRECTED_CHANNEL__SINK_END);

		drainChannelEClass = createEClass(DRAIN_CHANNEL);
		createEReference(drainChannelEClass, DRAIN_CHANNEL__SOURCE_END_ONE);
		createEReference(drainChannelEClass, DRAIN_CHANNEL__SOURCE_END_TWO);

		spoutChannelEClass = createEClass(SPOUT_CHANNEL);
		createEReference(spoutChannelEClass, SPOUT_CHANNEL__SINK_END_ONE);
		createEReference(spoutChannelEClass, SPOUT_CHANNEL__SINK_END_TWO);

		syncEClass = createEClass(SYNC);

		lossySyncEClass = createEClass(LOSSY_SYNC);

		fifoEClass = createEClass(FIFO);
		createEAttribute(fifoEClass, FIFO__FULL);
		createEAttribute(fifoEClass, FIFO__CELL_NAME);
		createEAttribute(fifoEClass, FIFO__INITIAL_VALUE);

		lossyFIFOEClass = createEClass(LOSSY_FIFO);
		createEAttribute(lossyFIFOEClass, LOSSY_FIFO__SHIFT);
		createEAttribute(lossyFIFOEClass, LOSSY_FIFO__FULL);
		createEAttribute(lossyFIFOEClass, LOSSY_FIFO__CELL_NAME);

		syncDrainEClass = createEClass(SYNC_DRAIN);

		asyncDrainEClass = createEClass(ASYNC_DRAIN);

		syncSpoutEClass = createEClass(SYNC_SPOUT);

		asyncSpoutEClass = createEClass(ASYNC_SPOUT);

		filterEClass = createEClass(FILTER);

		transformEClass = createEClass(TRANSFORM);

		timerEClass = createEClass(TIMER);
		createEAttribute(timerEClass, TIMER__TIMEOUT);

		customDirectedChannelEClass = createEClass(CUSTOM_DIRECTED_CHANNEL);

		customDrainChannelEClass = createEClass(CUSTOM_DRAIN_CHANNEL);

		customSpoutChannelEClass = createEClass(CUSTOM_SPOUT_CHANNEL);

		prioritySyncEClass = createEClass(PRIORITY_SYNC);

		blockingSourceSyncEClass = createEClass(BLOCKING_SOURCE_SYNC);

		blockingSinkSyncEClass = createEClass(BLOCKING_SINK_SYNC);

		blockingSyncEClass = createEClass(BLOCKING_SYNC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ReoPackage theReoPackage = (ReoPackage)EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		channelEClass.getESuperTypes().add(theReoPackage.getPrimitive());
		directedChannelEClass.getESuperTypes().add(this.getChannel());
		drainChannelEClass.getESuperTypes().add(this.getChannel());
		spoutChannelEClass.getESuperTypes().add(this.getChannel());
		syncEClass.getESuperTypes().add(this.getDirectedChannel());
		lossySyncEClass.getESuperTypes().add(this.getDirectedChannel());
		fifoEClass.getESuperTypes().add(this.getDirectedChannel());
		lossyFIFOEClass.getESuperTypes().add(this.getDirectedChannel());
		syncDrainEClass.getESuperTypes().add(this.getDrainChannel());
		syncDrainEClass.getESuperTypes().add(theReoPackage.getDataAware());
		asyncDrainEClass.getESuperTypes().add(this.getDrainChannel());
		syncSpoutEClass.getESuperTypes().add(this.getSpoutChannel());
		asyncSpoutEClass.getESuperTypes().add(this.getSpoutChannel());
		filterEClass.getESuperTypes().add(this.getDirectedChannel());
		filterEClass.getESuperTypes().add(theReoPackage.getDataAware());
		transformEClass.getESuperTypes().add(this.getDirectedChannel());
		transformEClass.getESuperTypes().add(theReoPackage.getDataAware());
		timerEClass.getESuperTypes().add(this.getDirectedChannel());
		customDirectedChannelEClass.getESuperTypes().add(this.getDirectedChannel());
		customDirectedChannelEClass.getESuperTypes().add(theReoPackage.getNameable());
		customDirectedChannelEClass.getESuperTypes().add(theReoPackage.getCustomPrimitive());
		customDrainChannelEClass.getESuperTypes().add(this.getDrainChannel());
		customDrainChannelEClass.getESuperTypes().add(theReoPackage.getNameable());
		customDrainChannelEClass.getESuperTypes().add(theReoPackage.getCustomPrimitive());
		customSpoutChannelEClass.getESuperTypes().add(this.getSpoutChannel());
		customSpoutChannelEClass.getESuperTypes().add(theReoPackage.getNameable());
		customSpoutChannelEClass.getESuperTypes().add(theReoPackage.getCustomPrimitive());
		prioritySyncEClass.getESuperTypes().add(this.getDirectedChannel());
		blockingSourceSyncEClass.getESuperTypes().add(this.getDirectedChannel());
		blockingSinkSyncEClass.getESuperTypes().add(this.getDirectedChannel());
		blockingSyncEClass.getESuperTypes().add(this.getDirectedChannel());

		// Initialize classes and features; add operations and parameters
		initEClass(channelEClass, Channel.class, "Channel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChannel_ChannelEndOne(), theReoPackage.getPrimitiveEnd(), null, "channelEndOne", null, 0, 1, Channel.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChannel_ChannelEndTwo(), theReoPackage.getPrimitiveEnd(), null, "channelEndTwo", null, 0, 1, Channel.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChannel_NodeOne(), theReoPackage.getNode(), null, "nodeOne", null, 0, 1, Channel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChannel_NodeTwo(), theReoPackage.getNode(), null, "nodeTwo", null, 0, 1, Channel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChannel_ChannelEndNameOne(), ecorePackage.getEString(), "channelEndNameOne", null, 0, 1, Channel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChannel_ChannelEndNameTwo(), ecorePackage.getEString(), "channelEndNameTwo", null, 0, 1, Channel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(directedChannelEClass, DirectedChannel.class, "DirectedChannel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDirectedChannel_SourceEnd(), theReoPackage.getSourceEnd(), null, "sourceEnd", null, 0, 1, DirectedChannel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDirectedChannel_SinkEnd(), theReoPackage.getSinkEnd(), null, "sinkEnd", null, 0, 1, DirectedChannel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(drainChannelEClass, DrainChannel.class, "DrainChannel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDrainChannel_SourceEndOne(), theReoPackage.getSourceEnd(), null, "sourceEndOne", null, 0, 1, DrainChannel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDrainChannel_SourceEndTwo(), theReoPackage.getSourceEnd(), null, "sourceEndTwo", null, 0, 1, DrainChannel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spoutChannelEClass, SpoutChannel.class, "SpoutChannel", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpoutChannel_SinkEndOne(), theReoPackage.getSinkEnd(), null, "sinkEndOne", null, 0, 1, SpoutChannel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpoutChannel_SinkEndTwo(), theReoPackage.getSinkEnd(), null, "sinkEndTwo", null, 0, 1, SpoutChannel.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syncEClass, Sync.class, "Sync", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(lossySyncEClass, LossySync.class, "LossySync", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fifoEClass, org.ect.reo.channels.FIFO.class, "FIFO", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFIFO_Full(), ecorePackage.getEBoolean(), "full", null, 0, 1, org.ect.reo.channels.FIFO.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFIFO_CellName(), ecorePackage.getEString(), "cellName", null, 0, 1, org.ect.reo.channels.FIFO.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFIFO_InitialValue(), ecorePackage.getEString(), "initialValue", null, 0, 1, org.ect.reo.channels.FIFO.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lossyFIFOEClass, LossyFIFO.class, "LossyFIFO", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLossyFIFO_Shift(), ecorePackage.getEBoolean(), "shift", null, 0, 1, LossyFIFO.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLossyFIFO_Full(), ecorePackage.getEBoolean(), "full", null, 0, 1, LossyFIFO.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLossyFIFO_CellName(), ecorePackage.getEString(), "cellName", null, 0, 1, LossyFIFO.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syncDrainEClass, SyncDrain.class, "SyncDrain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(asyncDrainEClass, AsyncDrain.class, "AsyncDrain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(syncSpoutEClass, SyncSpout.class, "SyncSpout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(asyncSpoutEClass, AsyncSpout.class, "AsyncSpout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transformEClass, Transform.class, "Transform", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timerEClass, Timer.class, "Timer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimer_Timeout(), ecorePackage.getEInt(), "timeout", null, 0, 1, Timer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customDirectedChannelEClass, CustomDirectedChannel.class, "CustomDirectedChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(customDrainChannelEClass, CustomDrainChannel.class, "CustomDrainChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(customSpoutChannelEClass, CustomSpoutChannel.class, "CustomSpoutChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(prioritySyncEClass, PrioritySync.class, "PrioritySync", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blockingSourceSyncEClass, BlockingSourceSync.class, "BlockingSourceSync", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blockingSinkSyncEClass, BlockingSinkSync.class, "BlockingSinkSync", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blockingSyncEClass, BlockingSync.class, "BlockingSync", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {
		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.Channel <em>Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.Channel
		 * @see org.ect.reo.channels.ChannelsPackage#getChannel()
		 * @generated
		 */
		public static final EClass CHANNEL = eINSTANCE.getChannel();

		/**
		 * The meta object literal for the '<em><b>Channel End One</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CHANNEL__CHANNEL_END_ONE = eINSTANCE.getChannel_ChannelEndOne();

		/**
		 * The meta object literal for the '<em><b>Channel End Two</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CHANNEL__CHANNEL_END_TWO = eINSTANCE.getChannel_ChannelEndTwo();

		/**
		 * The meta object literal for the '<em><b>Node One</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CHANNEL__NODE_ONE = eINSTANCE.getChannel_NodeOne();

		/**
		 * The meta object literal for the '<em><b>Node Two</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CHANNEL__NODE_TWO = eINSTANCE.getChannel_NodeTwo();

		/**
		 * The meta object literal for the '<em><b>Channel End Name One</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CHANNEL__CHANNEL_END_NAME_ONE = eINSTANCE.getChannel_ChannelEndNameOne();

		/**
		 * The meta object literal for the '<em><b>Channel End Name Two</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CHANNEL__CHANNEL_END_NAME_TWO = eINSTANCE.getChannel_ChannelEndNameTwo();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.DirectedChannel <em>Directed Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.DirectedChannel
		 * @see org.ect.reo.channels.ChannelsPackage#getDirectedChannel()
		 * @generated
		 */
		public static final EClass DIRECTED_CHANNEL = eINSTANCE.getDirectedChannel();

		/**
		 * The meta object literal for the '<em><b>Source End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference DIRECTED_CHANNEL__SOURCE_END = eINSTANCE.getDirectedChannel_SourceEnd();

		/**
		 * The meta object literal for the '<em><b>Sink End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference DIRECTED_CHANNEL__SINK_END = eINSTANCE.getDirectedChannel_SinkEnd();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.DrainChannel <em>Drain Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.DrainChannel
		 * @see org.ect.reo.channels.ChannelsPackage#getDrainChannel()
		 * @generated
		 */
		public static final EClass DRAIN_CHANNEL = eINSTANCE.getDrainChannel();

		/**
		 * The meta object literal for the '<em><b>Source End One</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference DRAIN_CHANNEL__SOURCE_END_ONE = eINSTANCE.getDrainChannel_SourceEndOne();

		/**
		 * The meta object literal for the '<em><b>Source End Two</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference DRAIN_CHANNEL__SOURCE_END_TWO = eINSTANCE.getDrainChannel_SourceEndTwo();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.SpoutChannel <em>Spout Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.SpoutChannel
		 * @see org.ect.reo.channels.ChannelsPackage#getSpoutChannel()
		 * @generated
		 */
		public static final EClass SPOUT_CHANNEL = eINSTANCE.getSpoutChannel();

		/**
		 * The meta object literal for the '<em><b>Sink End One</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SPOUT_CHANNEL__SINK_END_ONE = eINSTANCE.getSpoutChannel_SinkEndOne();

		/**
		 * The meta object literal for the '<em><b>Sink End Two</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SPOUT_CHANNEL__SINK_END_TWO = eINSTANCE.getSpoutChannel_SinkEndTwo();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.Sync <em>Sync</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.Sync
		 * @see org.ect.reo.channels.ChannelsPackage#getSync()
		 * @generated
		 */
		public static final EClass SYNC = eINSTANCE.getSync();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.LossySync <em>Lossy Sync</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.LossySync
		 * @see org.ect.reo.channels.ChannelsPackage#getLossySync()
		 * @generated
		 */
		public static final EClass LOSSY_SYNC = eINSTANCE.getLossySync();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.FIFO <em>FIFO</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.FIFO
		 * @see org.ect.reo.channels.ChannelsPackage#getFIFO()
		 * @generated
		 */
		public static final EClass FIFO = eINSTANCE.getFIFO();

		/**
		 * The meta object literal for the '<em><b>Full</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute FIFO__FULL = eINSTANCE.getFIFO_Full();

		/**
		 * The meta object literal for the '<em><b>Cell Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute FIFO__CELL_NAME = eINSTANCE.getFIFO_CellName();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute FIFO__INITIAL_VALUE = eINSTANCE.getFIFO_InitialValue();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.SyncDrain <em>Sync Drain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.SyncDrain
		 * @see org.ect.reo.channels.ChannelsPackage#getSyncDrain()
		 * @generated
		 */
		public static final EClass SYNC_DRAIN = eINSTANCE.getSyncDrain();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.AsyncDrain <em>Async Drain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.AsyncDrain
		 * @see org.ect.reo.channels.ChannelsPackage#getAsyncDrain()
		 * @generated
		 */
		public static final EClass ASYNC_DRAIN = eINSTANCE.getAsyncDrain();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.SyncSpout <em>Sync Spout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.SyncSpout
		 * @see org.ect.reo.channels.ChannelsPackage#getSyncSpout()
		 * @generated
		 */
		public static final EClass SYNC_SPOUT = eINSTANCE.getSyncSpout();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.AsyncSpout <em>Async Spout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.AsyncSpout
		 * @see org.ect.reo.channels.ChannelsPackage#getAsyncSpout()
		 * @generated
		 */
		public static final EClass ASYNC_SPOUT = eINSTANCE.getAsyncSpout();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.Filter <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.Filter
		 * @see org.ect.reo.channels.ChannelsPackage#getFilter()
		 * @generated
		 */
		public static final EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.Transform <em>Transform</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.Transform
		 * @see org.ect.reo.channels.ChannelsPackage#getTransform()
		 * @generated
		 */
		public static final EClass TRANSFORM = eINSTANCE.getTransform();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.Timer <em>Timer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.Timer
		 * @see org.ect.reo.channels.ChannelsPackage#getTimer()
		 * @generated
		 */
		public static final EClass TIMER = eINSTANCE.getTimer();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute TIMER__TIMEOUT = eINSTANCE.getTimer_Timeout();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.CustomDirectedChannel <em>Custom Directed Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.CustomDirectedChannel
		 * @see org.ect.reo.channels.ChannelsPackage#getCustomDirectedChannel()
		 * @generated
		 */
		public static final EClass CUSTOM_DIRECTED_CHANNEL = eINSTANCE.getCustomDirectedChannel();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.CustomDrainChannel <em>Custom Drain Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.CustomDrainChannel
		 * @see org.ect.reo.channels.ChannelsPackage#getCustomDrainChannel()
		 * @generated
		 */
		public static final EClass CUSTOM_DRAIN_CHANNEL = eINSTANCE.getCustomDrainChannel();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.CustomSpoutChannel <em>Custom Spout Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.CustomSpoutChannel
		 * @see org.ect.reo.channels.ChannelsPackage#getCustomSpoutChannel()
		 * @generated
		 */
		public static final EClass CUSTOM_SPOUT_CHANNEL = eINSTANCE.getCustomSpoutChannel();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.PrioritySync <em>Priority Sync</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.PrioritySync
		 * @see org.ect.reo.channels.ChannelsPackage#getPrioritySync()
		 * @generated
		 */
		public static final EClass PRIORITY_SYNC = eINSTANCE.getPrioritySync();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.BlockingSourceSync <em>Blocking Source Sync</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.BlockingSourceSync
		 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSourceSync()
		 * @generated
		 */
		public static final EClass BLOCKING_SOURCE_SYNC = eINSTANCE.getBlockingSourceSync();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.BlockingSinkSync <em>Blocking Sink Sync</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.BlockingSinkSync
		 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSinkSync()
		 * @generated
		 */
		public static final EClass BLOCKING_SINK_SYNC = eINSTANCE.getBlockingSinkSync();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.BlockingSync <em>Blocking Sync</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.BlockingSync
		 * @see org.ect.reo.channels.ChannelsPackage#getBlockingSync()
		 * @generated
		 */
		public static final EClass BLOCKING_SYNC = eINSTANCE.getBlockingSync();

		/**
		 * The meta object literal for the '{@link org.ect.reo.channels.LossyFIFO <em>Lossy FIFO</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.channels.LossyFIFO
		 * @see org.ect.reo.channels.ChannelsPackage#getLossyFIFO()
		 * @generated
		 */
		public static final EClass LOSSY_FIFO = eINSTANCE.getLossyFIFO();

		/**
		 * The meta object literal for the '<em><b>Shift</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute LOSSY_FIFO__SHIFT = eINSTANCE.getLossyFIFO_Shift();

		/**
		 * The meta object literal for the '<em><b>Full</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute LOSSY_FIFO__FULL = eINSTANCE.getLossyFIFO_Full();

		/**
		 * The meta object literal for the '<em><b>Cell Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute LOSSY_FIFO__CELL_NAME = eINSTANCE.getLossyFIFO_CellName();

	}

} //ChannelsPackage
