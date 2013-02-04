/**
 */
package org.ect.reo.channels;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.ect.reo.Connectable;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.DataAware;
import org.ect.reo.Delayable;
import org.ect.reo.Nameable;
import org.ect.reo.Primitive;
import org.ect.reo.PropertyHolder;
import org.ect.reo.Reconfigurable;

import org.ect.reo.animation.Animatable;

import org.ect.reo.colouring.Colourable;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.ect.reo.channels.ChannelsPackage
 * @generated
 */
public class ChannelsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ChannelsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChannelsSwitch() {
		if (modelPackage == null) {
			modelPackage = ChannelsPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ChannelsPackage.CHANNEL: {
				Channel channel = (Channel)theEObject;
				T result = caseChannel(channel);
				if (result == null) result = casePrimitive(channel);
				if (result == null) result = caseConnectable(channel);
				if (result == null) result = caseAnimatable(channel);
				if (result == null) result = caseDelayable(channel);
				if (result == null) result = caseReconfigurable(channel);
				if (result == null) result = casePropertyHolder(channel);
				if (result == null) result = caseColourable(channel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.DIRECTED_CHANNEL: {
				DirectedChannel directedChannel = (DirectedChannel)theEObject;
				T result = caseDirectedChannel(directedChannel);
				if (result == null) result = caseChannel(directedChannel);
				if (result == null) result = casePrimitive(directedChannel);
				if (result == null) result = caseConnectable(directedChannel);
				if (result == null) result = caseAnimatable(directedChannel);
				if (result == null) result = caseDelayable(directedChannel);
				if (result == null) result = caseReconfigurable(directedChannel);
				if (result == null) result = casePropertyHolder(directedChannel);
				if (result == null) result = caseColourable(directedChannel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.DRAIN_CHANNEL: {
				DrainChannel drainChannel = (DrainChannel)theEObject;
				T result = caseDrainChannel(drainChannel);
				if (result == null) result = caseChannel(drainChannel);
				if (result == null) result = casePrimitive(drainChannel);
				if (result == null) result = caseConnectable(drainChannel);
				if (result == null) result = caseAnimatable(drainChannel);
				if (result == null) result = caseDelayable(drainChannel);
				if (result == null) result = caseReconfigurable(drainChannel);
				if (result == null) result = casePropertyHolder(drainChannel);
				if (result == null) result = caseColourable(drainChannel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.SPOUT_CHANNEL: {
				SpoutChannel spoutChannel = (SpoutChannel)theEObject;
				T result = caseSpoutChannel(spoutChannel);
				if (result == null) result = caseChannel(spoutChannel);
				if (result == null) result = casePrimitive(spoutChannel);
				if (result == null) result = caseConnectable(spoutChannel);
				if (result == null) result = caseAnimatable(spoutChannel);
				if (result == null) result = caseDelayable(spoutChannel);
				if (result == null) result = caseReconfigurable(spoutChannel);
				if (result == null) result = casePropertyHolder(spoutChannel);
				if (result == null) result = caseColourable(spoutChannel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.SYNC: {
				Sync sync = (Sync)theEObject;
				T result = caseSync(sync);
				if (result == null) result = caseDirectedChannel(sync);
				if (result == null) result = caseChannel(sync);
				if (result == null) result = casePrimitive(sync);
				if (result == null) result = caseConnectable(sync);
				if (result == null) result = caseAnimatable(sync);
				if (result == null) result = caseDelayable(sync);
				if (result == null) result = caseReconfigurable(sync);
				if (result == null) result = casePropertyHolder(sync);
				if (result == null) result = caseColourable(sync);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.LOSSY_SYNC: {
				LossySync lossySync = (LossySync)theEObject;
				T result = caseLossySync(lossySync);
				if (result == null) result = caseDirectedChannel(lossySync);
				if (result == null) result = caseChannel(lossySync);
				if (result == null) result = casePrimitive(lossySync);
				if (result == null) result = caseConnectable(lossySync);
				if (result == null) result = caseAnimatable(lossySync);
				if (result == null) result = caseDelayable(lossySync);
				if (result == null) result = caseReconfigurable(lossySync);
				if (result == null) result = casePropertyHolder(lossySync);
				if (result == null) result = caseColourable(lossySync);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.FIFO: {
				FIFO fifo = (FIFO)theEObject;
				T result = caseFIFO(fifo);
				if (result == null) result = caseDirectedChannel(fifo);
				if (result == null) result = caseChannel(fifo);
				if (result == null) result = casePrimitive(fifo);
				if (result == null) result = caseConnectable(fifo);
				if (result == null) result = caseAnimatable(fifo);
				if (result == null) result = caseDelayable(fifo);
				if (result == null) result = caseReconfigurable(fifo);
				if (result == null) result = casePropertyHolder(fifo);
				if (result == null) result = caseColourable(fifo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.LOSSY_FIFO: {
				LossyFIFO lossyFIFO = (LossyFIFO)theEObject;
				T result = caseLossyFIFO(lossyFIFO);
				if (result == null) result = caseDirectedChannel(lossyFIFO);
				if (result == null) result = caseChannel(lossyFIFO);
				if (result == null) result = casePrimitive(lossyFIFO);
				if (result == null) result = caseConnectable(lossyFIFO);
				if (result == null) result = caseAnimatable(lossyFIFO);
				if (result == null) result = caseDelayable(lossyFIFO);
				if (result == null) result = caseReconfigurable(lossyFIFO);
				if (result == null) result = casePropertyHolder(lossyFIFO);
				if (result == null) result = caseColourable(lossyFIFO);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.SYNC_DRAIN: {
				SyncDrain syncDrain = (SyncDrain)theEObject;
				T result = caseSyncDrain(syncDrain);
				if (result == null) result = caseDrainChannel(syncDrain);
				if (result == null) result = caseDataAware(syncDrain);
				if (result == null) result = caseChannel(syncDrain);
				if (result == null) result = casePrimitive(syncDrain);
				if (result == null) result = caseConnectable(syncDrain);
				if (result == null) result = caseAnimatable(syncDrain);
				if (result == null) result = caseDelayable(syncDrain);
				if (result == null) result = caseReconfigurable(syncDrain);
				if (result == null) result = casePropertyHolder(syncDrain);
				if (result == null) result = caseColourable(syncDrain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.ASYNC_DRAIN: {
				AsyncDrain asyncDrain = (AsyncDrain)theEObject;
				T result = caseAsyncDrain(asyncDrain);
				if (result == null) result = caseDrainChannel(asyncDrain);
				if (result == null) result = caseChannel(asyncDrain);
				if (result == null) result = casePrimitive(asyncDrain);
				if (result == null) result = caseConnectable(asyncDrain);
				if (result == null) result = caseAnimatable(asyncDrain);
				if (result == null) result = caseDelayable(asyncDrain);
				if (result == null) result = caseReconfigurable(asyncDrain);
				if (result == null) result = casePropertyHolder(asyncDrain);
				if (result == null) result = caseColourable(asyncDrain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.SYNC_SPOUT: {
				SyncSpout syncSpout = (SyncSpout)theEObject;
				T result = caseSyncSpout(syncSpout);
				if (result == null) result = caseSpoutChannel(syncSpout);
				if (result == null) result = caseChannel(syncSpout);
				if (result == null) result = casePrimitive(syncSpout);
				if (result == null) result = caseConnectable(syncSpout);
				if (result == null) result = caseAnimatable(syncSpout);
				if (result == null) result = caseDelayable(syncSpout);
				if (result == null) result = caseReconfigurable(syncSpout);
				if (result == null) result = casePropertyHolder(syncSpout);
				if (result == null) result = caseColourable(syncSpout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.ASYNC_SPOUT: {
				AsyncSpout asyncSpout = (AsyncSpout)theEObject;
				T result = caseAsyncSpout(asyncSpout);
				if (result == null) result = caseSpoutChannel(asyncSpout);
				if (result == null) result = caseChannel(asyncSpout);
				if (result == null) result = casePrimitive(asyncSpout);
				if (result == null) result = caseConnectable(asyncSpout);
				if (result == null) result = caseAnimatable(asyncSpout);
				if (result == null) result = caseDelayable(asyncSpout);
				if (result == null) result = caseReconfigurable(asyncSpout);
				if (result == null) result = casePropertyHolder(asyncSpout);
				if (result == null) result = caseColourable(asyncSpout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				T result = caseFilter(filter);
				if (result == null) result = caseDirectedChannel(filter);
				if (result == null) result = caseDataAware(filter);
				if (result == null) result = caseChannel(filter);
				if (result == null) result = casePrimitive(filter);
				if (result == null) result = caseConnectable(filter);
				if (result == null) result = caseAnimatable(filter);
				if (result == null) result = caseDelayable(filter);
				if (result == null) result = caseReconfigurable(filter);
				if (result == null) result = casePropertyHolder(filter);
				if (result == null) result = caseColourable(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.TRANSFORM: {
				Transform transform = (Transform)theEObject;
				T result = caseTransform(transform);
				if (result == null) result = caseDirectedChannel(transform);
				if (result == null) result = caseDataAware(transform);
				if (result == null) result = caseChannel(transform);
				if (result == null) result = casePrimitive(transform);
				if (result == null) result = caseConnectable(transform);
				if (result == null) result = caseAnimatable(transform);
				if (result == null) result = caseDelayable(transform);
				if (result == null) result = caseReconfigurable(transform);
				if (result == null) result = casePropertyHolder(transform);
				if (result == null) result = caseColourable(transform);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.TIMER: {
				Timer timer = (Timer)theEObject;
				T result = caseTimer(timer);
				if (result == null) result = caseDirectedChannel(timer);
				if (result == null) result = caseChannel(timer);
				if (result == null) result = casePrimitive(timer);
				if (result == null) result = caseConnectable(timer);
				if (result == null) result = caseAnimatable(timer);
				if (result == null) result = caseDelayable(timer);
				if (result == null) result = caseReconfigurable(timer);
				if (result == null) result = casePropertyHolder(timer);
				if (result == null) result = caseColourable(timer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.CUSTOM_DIRECTED_CHANNEL: {
				CustomDirectedChannel customDirectedChannel = (CustomDirectedChannel)theEObject;
				T result = caseCustomDirectedChannel(customDirectedChannel);
				if (result == null) result = caseDirectedChannel(customDirectedChannel);
				if (result == null) result = caseNameable(customDirectedChannel);
				if (result == null) result = caseCustomPrimitive(customDirectedChannel);
				if (result == null) result = caseChannel(customDirectedChannel);
				if (result == null) result = casePrimitive(customDirectedChannel);
				if (result == null) result = caseConnectable(customDirectedChannel);
				if (result == null) result = caseAnimatable(customDirectedChannel);
				if (result == null) result = caseDelayable(customDirectedChannel);
				if (result == null) result = caseReconfigurable(customDirectedChannel);
				if (result == null) result = casePropertyHolder(customDirectedChannel);
				if (result == null) result = caseColourable(customDirectedChannel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.CUSTOM_DRAIN_CHANNEL: {
				CustomDrainChannel customDrainChannel = (CustomDrainChannel)theEObject;
				T result = caseCustomDrainChannel(customDrainChannel);
				if (result == null) result = caseDrainChannel(customDrainChannel);
				if (result == null) result = caseNameable(customDrainChannel);
				if (result == null) result = caseCustomPrimitive(customDrainChannel);
				if (result == null) result = caseChannel(customDrainChannel);
				if (result == null) result = casePrimitive(customDrainChannel);
				if (result == null) result = caseConnectable(customDrainChannel);
				if (result == null) result = caseAnimatable(customDrainChannel);
				if (result == null) result = caseDelayable(customDrainChannel);
				if (result == null) result = caseReconfigurable(customDrainChannel);
				if (result == null) result = casePropertyHolder(customDrainChannel);
				if (result == null) result = caseColourable(customDrainChannel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL: {
				CustomSpoutChannel customSpoutChannel = (CustomSpoutChannel)theEObject;
				T result = caseCustomSpoutChannel(customSpoutChannel);
				if (result == null) result = caseSpoutChannel(customSpoutChannel);
				if (result == null) result = caseNameable(customSpoutChannel);
				if (result == null) result = caseCustomPrimitive(customSpoutChannel);
				if (result == null) result = caseChannel(customSpoutChannel);
				if (result == null) result = casePrimitive(customSpoutChannel);
				if (result == null) result = caseConnectable(customSpoutChannel);
				if (result == null) result = caseAnimatable(customSpoutChannel);
				if (result == null) result = caseDelayable(customSpoutChannel);
				if (result == null) result = caseReconfigurable(customSpoutChannel);
				if (result == null) result = casePropertyHolder(customSpoutChannel);
				if (result == null) result = caseColourable(customSpoutChannel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.PRIORITY_SYNC: {
				PrioritySync prioritySync = (PrioritySync)theEObject;
				T result = casePrioritySync(prioritySync);
				if (result == null) result = caseDirectedChannel(prioritySync);
				if (result == null) result = caseChannel(prioritySync);
				if (result == null) result = casePrimitive(prioritySync);
				if (result == null) result = caseConnectable(prioritySync);
				if (result == null) result = caseAnimatable(prioritySync);
				if (result == null) result = caseDelayable(prioritySync);
				if (result == null) result = caseReconfigurable(prioritySync);
				if (result == null) result = casePropertyHolder(prioritySync);
				if (result == null) result = caseColourable(prioritySync);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.BLOCKING_SOURCE_SYNC: {
				BlockingSourceSync blockingSourceSync = (BlockingSourceSync)theEObject;
				T result = caseBlockingSourceSync(blockingSourceSync);
				if (result == null) result = caseDirectedChannel(blockingSourceSync);
				if (result == null) result = caseChannel(blockingSourceSync);
				if (result == null) result = casePrimitive(blockingSourceSync);
				if (result == null) result = caseConnectable(blockingSourceSync);
				if (result == null) result = caseAnimatable(blockingSourceSync);
				if (result == null) result = caseDelayable(blockingSourceSync);
				if (result == null) result = caseReconfigurable(blockingSourceSync);
				if (result == null) result = casePropertyHolder(blockingSourceSync);
				if (result == null) result = caseColourable(blockingSourceSync);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.BLOCKING_SINK_SYNC: {
				BlockingSinkSync blockingSinkSync = (BlockingSinkSync)theEObject;
				T result = caseBlockingSinkSync(blockingSinkSync);
				if (result == null) result = caseDirectedChannel(blockingSinkSync);
				if (result == null) result = caseChannel(blockingSinkSync);
				if (result == null) result = casePrimitive(blockingSinkSync);
				if (result == null) result = caseConnectable(blockingSinkSync);
				if (result == null) result = caseAnimatable(blockingSinkSync);
				if (result == null) result = caseDelayable(blockingSinkSync);
				if (result == null) result = caseReconfigurable(blockingSinkSync);
				if (result == null) result = casePropertyHolder(blockingSinkSync);
				if (result == null) result = caseColourable(blockingSinkSync);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ChannelsPackage.BLOCKING_SYNC: {
				BlockingSync blockingSync = (BlockingSync)theEObject;
				T result = caseBlockingSync(blockingSync);
				if (result == null) result = caseDirectedChannel(blockingSync);
				if (result == null) result = caseChannel(blockingSync);
				if (result == null) result = casePrimitive(blockingSync);
				if (result == null) result = caseConnectable(blockingSync);
				if (result == null) result = caseAnimatable(blockingSync);
				if (result == null) result = caseDelayable(blockingSync);
				if (result == null) result = caseReconfigurable(blockingSync);
				if (result == null) result = casePropertyHolder(blockingSync);
				if (result == null) result = caseColourable(blockingSync);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChannel(Channel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Directed Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Directed Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDirectedChannel(DirectedChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Drain Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Drain Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDrainChannel(DrainChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Spout Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Spout Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpoutChannel(SpoutChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sync</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sync</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSync(Sync object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lossy Sync</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lossy Sync</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLossySync(LossySync object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FIFO</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FIFO</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFIFO(FIFO object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lossy FIFO</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lossy FIFO</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLossyFIFO(LossyFIFO object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sync Drain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sync Drain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSyncDrain(SyncDrain object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Async Drain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Async Drain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsyncDrain(AsyncDrain object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sync Spout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sync Spout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSyncSpout(SyncSpout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Async Spout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Async Spout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsyncSpout(AsyncSpout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilter(Filter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transform</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transform</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransform(Transform object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimer(Timer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Directed Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Directed Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomDirectedChannel(CustomDirectedChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Drain Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Drain Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomDrainChannel(CustomDrainChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Spout Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Spout Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomSpoutChannel(CustomSpoutChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Priority Sync</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Priority Sync</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrioritySync(PrioritySync object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Blocking Source Sync</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Blocking Source Sync</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockingSourceSync(BlockingSourceSync object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Blocking Sink Sync</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Blocking Sink Sync</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockingSinkSync(BlockingSinkSync object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Blocking Sync</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Blocking Sync</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockingSync(BlockingSync object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connectable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connectable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectable(Connectable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Colourable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Colourable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseColourable(Colourable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Animatable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Animatable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnimatable(Animatable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delayable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delayable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDelayable(Delayable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reconfigurable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reconfigurable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReconfigurable(Reconfigurable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Holder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Holder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyHolder(PropertyHolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitive(Primitive object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Aware</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Aware</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataAware(DataAware object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameable(Nameable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Primitive</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Primitive</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomPrimitive(CustomPrimitive object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ChannelsSwitch
