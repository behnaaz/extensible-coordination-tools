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
package org.ect.reo.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.BlockingSinkSync;
import org.ect.reo.channels.BlockingSourceSync;
import org.ect.reo.channels.BlockingSync;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.diagram.edit.helpers.ReoBaseEditHelper;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class ReoBaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	 * Extended request data key to hold editpart visual id.
	 * 
	 * @generated
	 */
	public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final IElementType myElementType;

	/**
	 * @generated
	 */
	protected ReoBaseItemSemanticEditPolicy(IElementType elementType) {
		myElementType = elementType;
	}

	/**
	 * Extended request data key to hold editpart visual id.
	 * Add visual id of edited editpart to extended data of the request
	 * so command switch can decide what kind of diagram element is being edited.
	 * It is done in those cases when it's not possible to deduce diagram
	 * element kind from domain element.
	 * 
	 * @generated
	 */
	@Override
	public Command getCommand(Request request) {
		if (request instanceof ReconnectRequest) {
			Object view = ((ReconnectRequest) request).getConnectionEditPart()
					.getModel();
			if (view instanceof View) {
				Integer id = new Integer(
						ReoVisualIDRegistry.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	 * Returns visual id from request parameters.
	 * 
	 * @generated
	 */
	protected int getVisualID(IEditCommandRequest request) {
		Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getSemanticCommand(IEditCommandRequest request) {
		IEditCommandRequest completedRequest = completeRequest(request);
		Command semanticCommand = getSemanticCommandSwitch(completedRequest);
		semanticCommand = getEditHelperCommand(completedRequest,
				semanticCommand);
		if (completedRequest instanceof DestroyRequest) {
			DestroyRequest destroyRequest = (DestroyRequest) completedRequest;
			return shouldProceed(destroyRequest) ? addDeleteViewCommand(
					semanticCommand, destroyRequest) : null;
		}
		return semanticCommand;
	}

	/**
	 * @generated
	 */
	protected Command addDeleteViewCommand(Command mainCommand,
			DestroyRequest completedRequest) {
		Command deleteViewCommand = getGEFWrapper(new DeleteCommand(
				getEditingDomain(), (View) getHost().getModel()));
		return mainCommand == null ? deleteViewCommand : mainCommand
				.chain(deleteViewCommand);
	}

	/**
	 * @generated
	 */
	private Command getEditHelperCommand(IEditCommandRequest request,
			Command editPolicyCommand) {
		if (editPolicyCommand != null) {
			ICommand command = editPolicyCommand instanceof ICommandProxy ? ((ICommandProxy) editPolicyCommand)
					.getICommand() : new CommandProxy(editPolicyCommand);
			request.setParameter(ReoBaseEditHelper.EDIT_POLICY_COMMAND, command);
		}
		IElementType requestContextElementType = getContextElementType(request);
		request.setParameter(ReoBaseEditHelper.CONTEXT_ELEMENT_TYPE,
				requestContextElementType);
		ICommand command = requestContextElementType.getEditCommand(request);
		request.setParameter(ReoBaseEditHelper.EDIT_POLICY_COMMAND, null);
		request.setParameter(ReoBaseEditHelper.CONTEXT_ELEMENT_TYPE, null);
		if (command != null) {
			if (!(command instanceof CompositeTransactionalCommand)) {
				command = new CompositeTransactionalCommand(getEditingDomain(),
						command.getLabel()).compose(command);
			}
			return new ICommandProxy(command);
		}
		return editPolicyCommand;
	}

	/**
	 * @generated
	 */
	private IElementType getContextElementType(IEditCommandRequest request) {
		IElementType requestContextElementType = ReoElementTypes
				.getElementType(getVisualID(request));
		return requestContextElementType != null ? requestContextElementType
				: myElementType;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) {
			return getSetCommand((SetRequest) req);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getConfigureCommand(ConfigureRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSetCommand(SetRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getEditContextCommand(GetEditContextRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getMoveCommand(MoveRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected final Command getGEFWrapper(ICommand cmd) {
		return new ICommandProxy(cmd);
	}

	/**
	 * Returns editing domain from the host edit part.
	 * 
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	 * Clean all shortcuts to the host element from the same diagram
	 * @generated
	 */
	protected void addDestroyShortcutsCommand(ICompositeCommand cmd, View view) {
		assert view.getEAnnotation("Shortcut") == null; //$NON-NLS-1$
		for (Iterator it = view.getDiagram().getChildren().iterator(); it
				.hasNext();) {
			View nextView = (View) it.next();
			if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
				continue;
			}
			cmd.add(new DeleteCommand(getEditingDomain(), nextView));
		}
	}

	/**
	 * @generated
	 */
	public static LinkConstraints getLinkConstraints() {
		LinkConstraints cached = ReoDiagramEditorPlugin.getInstance()
				.getLinkConstraints();
		if (cached == null) {
			ReoDiagramEditorPlugin.getInstance().setLinkConstraints(
					cached = new LinkConstraints());
		}
		return cached;
	}

	/**
	 * @generated
	 */
	public static class LinkConstraints {
		/**
		 * @generated
		 */
		LinkConstraints() {
			// use static method #getLinkConstraints() to access instance
		}

		/**
		 * @generated
		 */
		public boolean canCreateSync_3001(Connector container, Node source,
				Node target) {
			return canExistSync_3001(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateLossySync_3002(Connector container,
				Node source, Node target) {
			return canExistLossySync_3002(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateFIFO_3003(Connector container, Node source,
				Node target) {
			return canExistFIFO_3003(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateSyncDrain_3005(Connector container,
				Node source, Node target) {
			return canExistSyncDrain_3005(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateSyncSpout_3006(Connector container,
				Node source, Node target) {
			return canExistSyncSpout_3006(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateAsyncDrain_3007(Connector container,
				Node source, Node target) {
			return canExistAsyncDrain_3007(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateAsyncSpout_3008(Connector container,
				Node source, Node target) {
			return canExistAsyncSpout_3008(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateFilter_3011(Connector container, Node source,
				Node target) {
			return canExistFilter_3011(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateTransform_3012(Connector container,
				Node source, Node target) {
			return canExistTransform_3012(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateTimer_3015(Connector container, Node source,
				Node target) {
			return canExistTimer_3015(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateNodeSourceEnds_3013(Node source,
				SourceEnd target) {
			if (source != null) {
				if (source.getSourceEnds().contains(target)) {
					return false;
				}
			}
			if (target != null && (target.getNode() != null)) {
				return false;
			}

			return canExistNodeSourceEnds_3013(source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateSinkEndNode_3014(SinkEnd source, Node target) {
			if (source != null) {
				if (source.getNode() != null) {
					return false;
				}
			}
			if (target != null && (target.getSinkEnds().contains(target))) {
				return false;
			}

			return canExistSinkEndNode_3014(source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateCustomDirectedChannel_3016(Connector container,
				Node source, Node target) {
			return canExistCustomDirectedChannel_3016(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateCustomDrainChannel_3017(Connector container,
				Node source, Node target) {
			return canExistCustomDrainChannel_3017(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateCustomSpoutChannel_3018(Connector container,
				Node source, Node target) {
			return canExistCustomSpoutChannel_3018(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreatePrioritySync_3019(Connector container,
				Node source, Node target) {
			return canExistPrioritySync_3019(container, null, source, target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateBlockingSync_3020(Connector container,
				Node source, Node target) {
			return canExistBlockingSync_3020(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canCreateBlockingSinkSync_3021(Connector container,
				Node source, Node target) {
			return canExistBlockingSinkSync_3021(container, null, source,
					target);
		}
		
		/**
		 * @generated
		 */
		public boolean canCreateBlockingSourceSync_3022(Connector container,
				Node source, Node target) {
			return canExistBlockingSourceSync_3022(container, null, source,
					target);
		}

		/**
		 * @generated
		 */
		public boolean canExistSync_3001(Connector container,
				Sync linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistLossySync_3002(Connector container,
				LossySync linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistFIFO_3003(Connector container,
				org.ect.reo.channels.FIFO linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistSyncDrain_3005(Connector container,
				SyncDrain linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistSyncSpout_3006(Connector container,
				SyncSpout linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistAsyncDrain_3007(Connector container,
				AsyncDrain linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistAsyncSpout_3008(Connector container,
				AsyncSpout linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistFilter_3011(Connector container,
				Filter linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistTransform_3012(Connector container,
				Transform linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistTimer_3015(Connector container,
				Timer linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistSync_3001(Connector container,
				Node source, Node target) {
			return canExistDirectedChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistLossySync_3002(Connector container,
				Node source, Node target) {
			return canExistDirectedChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistFIFO_3003(Connector container,
				Node source, Node target) {
			return canExistDirectedChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistFIFO_3004(Connector container,
				Node source, Node target) {
			return canExistDirectedChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistSyncDrain_3005(Connector container,
				Node source, Node target) {
			return canExistDrainChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistSyncSpout_3006(Connector container,
				Node source, Node target) {
			return canExistSpoutChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistAsyncDrain_3007(Connector container,
				Node source, Node target) {
			return canExistDrainChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistAsyncSpout_3008(Connector container,
				Node source, Node target) {
			return canExistSpoutChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistFilter_3011(Connector container,
				Node source, Node target) {
			return canExistDirectedChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistTransform_3012(Connector container,
				Node source, Node target) {
			return canExistDirectedChannel(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistNodeSourceEnds_3013(Node source,
				SourceEnd target) {

			// Source must be not null.
			if (source == null)
				return false;

			// Must be a Sink Node.
			if (!source.isSinkNode())
				return false;

			if (target != null) {
				// Must not be connected already.
				if (target.getNode() != null)
					return false;
			}

			// Ok.
			return true;
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistSinkEndNode_3014(SinkEnd source,
				Node target) {

			// Source must be not null.
			if (source == null)
				return false;

			// Must not be connected already.
			if (source.getNode() != null)
				return false;

			if (target != null) {
				// Must be a Source Node.
				if (!target.isSourceNode())
					return false;
			}

			// Ok.
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistCustomDirectedChannel_3016(Connector container,
				CustomDirectedChannel linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistCustomDrainChannel_3017(Connector container,
				CustomDrainChannel linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistCustomSpoutChannel_3018(Connector container,
				CustomSpoutChannel linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistPrioritySync_3019(Connector container,
				PrioritySync linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistBlockingSinkSync_3021(Connector container,
				BlockingSinkSync linkInstance, Node source, Node target) {
			return true;
		}

		/**
		 * @generated
		 */
		public boolean canExistBlockingSourceSync_3022(Connector container,
				BlockingSourceSync linkInstance, Node source, Node target) {
			return true;
		}
		
		/**
		 * @generated
		 */
		public boolean canExistBlockingSync_3020(Connector container,
				BlockingSync linkInstance, Node source, Node target) {
			return true;
		}
		
		/**
		 * @generated NOT
		 */
		public static boolean canExistDirectedChannel(Connector container,
				Node source, Node target) {
			return !hasReader(source, container)
					&& !hasWriter(target, container);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistDrainChannel(Connector container,
				Node source, Node target) {
			return !hasReader(source, container)
					&& !hasWriter(target, container);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistSpoutChannel(Connector container,
				Node source, Node target) {
			return !hasWriter(source, container)
					&& !hasWriter(target, container);
		}

		/**
		 * Check if there is a foreign primitive attached to a node (as a reader).
		 * @generated NOT
		 */
		public static boolean hasReader(Node node, Connector connector) {
			if (connector == null)
				connector = node.getConnector();
			EList<Primitive> foreign = connector.getForeignPrimitives();
			for (SourceEnd end : node.getSourceEnds()) {
				if (foreign.contains(end.getPrimitive()))
					return true;
			}
			return false;
		}

		/**
		 * Check if there is a foreign primitive attached to a node (as a writer).
		 * @generated NOT
		 */
		public static boolean hasWriter(Node node, Connector connector) {
			if (connector == null)
				connector = node.getConnector();
			EList<Primitive> foreign = connector.getForeignPrimitives();
			for (SinkEnd end : node.getSinkEnds()) {
				if (foreign.contains(end.getPrimitive()))
					return true;
			}
			return false;
		}

	}

}
