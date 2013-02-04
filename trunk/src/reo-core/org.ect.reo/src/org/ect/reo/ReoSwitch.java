/**
 */
package org.ect.reo;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see org.ect.reo.ReoPackage
 * @generated
 */
public class ReoSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ReoPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReoSwitch() {
		if (modelPackage == null) {
			modelPackage = ReoPackage.eINSTANCE;
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
			case ReoPackage.COMPOSITE: {
				Composite composite = (Composite)theEObject;
				T result = caseComposite(composite);
				if (result == null) result = caseNameable(composite);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.MODULE: {
				Module module = (Module)theEObject;
				T result = caseModule(module);
				if (result == null) result = caseComposite(module);
				if (result == null) result = casePropertyHolder(module);
				if (result == null) result = caseNameable(module);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.NETWORK: {
				Network network = (Network)theEObject;
				T result = caseNetwork(network);
				if (result == null) result = caseComposite(network);
				if (result == null) result = caseAnimatable(network);
				if (result == null) result = caseNameable(network);
				if (result == null) result = caseColourable(network);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.CONNECTOR: {
				Connector connector = (Connector)theEObject;
				T result = caseConnector(connector);
				if (result == null) result = caseNameable(connector);
				if (result == null) result = caseAnimatable(connector);
				if (result == null) result = caseReconfigurable(connector);
				if (result == null) result = casePropertyHolder(connector);
				if (result == null) result = caseColourable(connector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.COMPONENT: {
				Component component = (Component)theEObject;
				T result = caseComponent(component);
				if (result == null) result = casePrimitive(component);
				if (result == null) result = caseNameable(component);
				if (result == null) result = caseCustomPrimitive(component);
				if (result == null) result = caseConnectable(component);
				if (result == null) result = caseAnimatable(component);
				if (result == null) result = caseDelayable(component);
				if (result == null) result = caseReconfigurable(component);
				if (result == null) result = casePropertyHolder(component);
				if (result == null) result = caseColourable(component);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = caseConnectable(node);
				if (result == null) result = caseNameable(node);
				if (result == null) result = caseAnimatable(node);
				if (result == null) result = caseDelayable(node);
				if (result == null) result = caseReconfigurable(node);
				if (result == null) result = casePropertyHolder(node);
				if (result == null) result = caseColourable(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.PRIMITIVE: {
				Primitive primitive = (Primitive)theEObject;
				T result = casePrimitive(primitive);
				if (result == null) result = caseConnectable(primitive);
				if (result == null) result = caseAnimatable(primitive);
				if (result == null) result = caseDelayable(primitive);
				if (result == null) result = caseReconfigurable(primitive);
				if (result == null) result = casePropertyHolder(primitive);
				if (result == null) result = caseColourable(primitive);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.PRIMITIVE_END: {
				PrimitiveEnd primitiveEnd = (PrimitiveEnd)theEObject;
				T result = casePrimitiveEnd(primitiveEnd);
				if (result == null) result = caseNameable(primitiveEnd);
				if (result == null) result = casePropertyHolder(primitiveEnd);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.SOURCE_END: {
				SourceEnd sourceEnd = (SourceEnd)theEObject;
				T result = caseSourceEnd(sourceEnd);
				if (result == null) result = casePrimitiveEnd(sourceEnd);
				if (result == null) result = caseNameable(sourceEnd);
				if (result == null) result = casePropertyHolder(sourceEnd);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.SINK_END: {
				SinkEnd sinkEnd = (SinkEnd)theEObject;
				T result = caseSinkEnd(sinkEnd);
				if (result == null) result = casePrimitiveEnd(sinkEnd);
				if (result == null) result = caseNameable(sinkEnd);
				if (result == null) result = casePropertyHolder(sinkEnd);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.PROPERTY: {
				Property property = (Property)theEObject;
				T result = caseProperty(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.CONNECTABLE: {
				Connectable connectable = (Connectable)theEObject;
				T result = caseConnectable(connectable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.DELAYABLE: {
				Delayable delayable = (Delayable)theEObject;
				T result = caseDelayable(delayable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.NAMEABLE: {
				Nameable nameable = (Nameable)theEObject;
				T result = caseNameable(nameable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.DATA_AWARE: {
				DataAware dataAware = (DataAware)theEObject;
				T result = caseDataAware(dataAware);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.PROPERTY_HOLDER: {
				PropertyHolder propertyHolder = (PropertyHolder)theEObject;
				T result = casePropertyHolder(propertyHolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.CUSTOM_PRIMITIVE: {
				CustomPrimitive customPrimitive = (CustomPrimitive)theEObject;
				T result = caseCustomPrimitive(customPrimitive);
				if (result == null) result = caseConnectable(customPrimitive);
				if (result == null) result = casePropertyHolder(customPrimitive);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.RECONFIGURABLE: {
				Reconfigurable reconfigurable = (Reconfigurable)theEObject;
				T result = caseReconfigurable(reconfigurable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.RECONF_RULE: {
				ReconfRule reconfRule = (ReconfRule)theEObject;
				T result = caseReconfRule(reconfRule);
				if (result == null) result = caseNameable(reconfRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ReoPackage.RECONF_ACTION: {
				ReconfAction reconfAction = (ReconfAction)theEObject;
				T result = caseReconfAction(reconfAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComposite(Composite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModule(Module object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Network</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Network</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNetwork(Network object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnector(Connector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponent(Component object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNode(Node object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Primitive End</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive End</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveEnd(PrimitiveEnd object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source End</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source End</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSourceEnd(SourceEnd object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sink End</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sink End</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSinkEnd(SinkEnd object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Reconf Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reconf Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReconfRule(ReconfRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reconf Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reconf Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReconfAction(ReconfAction object) {
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

} //ReoSwitch
