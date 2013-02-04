package org.ect.ea.extensions.constraints;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.util.CompoundValidationResult;
import org.ect.ea.util.IValidationResult;



/**
 * @generated
 */
public class Equation extends ExtensionElement implements Constraint {

	/**
	 * @generated NOT
	 */
	public Equation() {
		super();
		setId(ConstraintExtensionProvider.EXTENSION_ID);
	}

	/**
	 * @generated NOT
	 */
	public Equation(Parameter left, Parameter right) {
		this();
		setLeft(left);
		setRight(right);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return getLeft() + "=" + getRight();
	}

	
	/**
	 * @generated NOT
	 */
	@Override
	public IExtendible getOwner() {
		if (eContainer() instanceof IExtendible) return (IExtendible) eContainer();
		if (eContainer() instanceof IExtension) return ((IExtension) eContainer()).getOwner();
		return null;
	}


	/**
	 * @generated NOT
	 */
	public IValidationResult validate() {
		CompoundValidationResult result = new CompoundValidationResult();
		if (getLeft()!=null) result.add(getLeft().validate());
		if (getRight()!=null) result.add(getRight().validate());
		return result;
	}

	
	/**
	 * @generated NOT
	 */
	public EList<Element> getAllElements() {
		EList<Element> elements = new BasicEList<Element>();
		if (getLeft()!=null) elements.addAll(getLeft().getAllElements());
		if (getRight()!=null) elements.addAll(getRight().getAllElements());
		return elements;
	}

	
	/**
	 * @generated NOT
	 */
	public Disjunction toDNF() {
		Disjunction disjunction = new Disjunction();
		Conjunction conjunction = new Conjunction();
		Equation equation = (Equation) EcoreUtil.copy(this);
		conjunction.getMembers().add(equation);
		disjunction.getMembers().add(conjunction);
		return disjunction;
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */


	/**
	 * @see #getLeft()
	 * @generated
	 * @ordered
	 */
	protected Parameter left;

	/**
	 * @see #getRight()
	 * @generated
	 * @ordered
	 */
	protected Parameter right;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.EQUATION;
	}

	/**
	 * @see #setLeft(String)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getEquation_Left()
	 * @model
	 * @generated
	 */
	public Parameter getLeft() {
		return left;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeft(Parameter newLeft, NotificationChain msgs) {
		Parameter oldLeft = left;
		left = newLeft;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConstraintsPackage.EQUATION__LEFT, oldLeft, newLeft);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.constraints.Equation#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	public void setLeft(Parameter newLeft) {
		if (newLeft != left) {
			NotificationChain msgs = null;
			if (left != null)
				msgs = ((InternalEObject)left).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.EQUATION__LEFT, null, msgs);
			if (newLeft != null)
				msgs = ((InternalEObject)newLeft).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.EQUATION__LEFT, null, msgs);
			msgs = basicSetLeft(newLeft, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.EQUATION__LEFT, newLeft, newLeft));
	}

	/**
	 * @see #setRight(String)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getEquation_Right()
	 * @model
	 * @generated
	 */
	public Parameter getRight() {
		return right;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRight(Parameter newRight, NotificationChain msgs) {
		Parameter oldRight = right;
		right = newRight;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConstraintsPackage.EQUATION__RIGHT, oldRight, newRight);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.constraints.Equation#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	public void setRight(Parameter newRight) {
		if (newRight != right) {
			NotificationChain msgs = null;
			if (right != null)
				msgs = ((InternalEObject)right).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.EQUATION__RIGHT, null, msgs);
			if (newRight != null)
				msgs = ((InternalEObject)newRight).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.EQUATION__RIGHT, null, msgs);
			msgs = basicSetRight(newRight, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.EQUATION__RIGHT, newRight, newRight));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.EQUATION__LEFT:
				return basicSetLeft(null, msgs);
			case ConstraintsPackage.EQUATION__RIGHT:
				return basicSetRight(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.EQUATION__LEFT:
				return getLeft();
			case ConstraintsPackage.EQUATION__RIGHT:
				return getRight();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.EQUATION__LEFT:
				setLeft((Parameter)newValue);
				return;
			case ConstraintsPackage.EQUATION__RIGHT:
				setRight((Parameter)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConstraintsPackage.EQUATION__LEFT:
				setLeft((Parameter)null);
				return;
			case ConstraintsPackage.EQUATION__RIGHT:
				setRight((Parameter)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConstraintsPackage.EQUATION__LEFT:
				return left != null;
			case ConstraintsPackage.EQUATION__RIGHT:
				return right != null;
		}
		return super.eIsSet(featureID);
	}
	
}
