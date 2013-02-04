package org.ect.ea.extensions.constraints;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.util.CompoundValidationResult;
import org.ect.ea.util.IValidationResult;


/**
 * @generated
 */
public class Conjunction extends ExtensionElement implements Composite {

	/**
	 * Create a new conjunction.
	 * @generated NOT
	 */
	public Conjunction() {
		super();
		setId(ConstraintExtensionProvider.EXTENSION_ID);
	}

	
	/**
	 * Creates a new conjunction with all members of the 
	 * two parameter conjunctions. The members are copied. 
	 * The original constraints are not changed.
	 * @generated NOT
	 */
	public Conjunction(Conjunction c1, Conjunction c2) {
		super();
		for (Constraint constraint : c1.getMembers()) {
			getMembers().add((Constraint) EcoreUtil.copy(constraint));
		}
		for (Constraint constraint : c2.getMembers()) {
			getMembers().add((Constraint) EcoreUtil.copy(constraint));
		}
	}

	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(");
		for (int i=0; i<getMembers().size(); i++) {
			result.append(getMembers().get(i).toString());
			if (i!=getMembers().size()-1) result.append(" & ");
		}
		result.append(")");
		return result.toString();
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
		for (Constraint member : getMembers()) {
			result.add(member.validate());
		}
		return result;
	}

	
	/**
	 * @generated NOT
	 */
	public EList<Element> getAllElements() {
		EList<Element> elements = new BasicEList<Element>();
		for (Constraint member : getMembers()) {
			elements.addAll(member.getAllElements());
		}
		return elements;
	}
	
	
	/**
	 * @generated NOT
	 */
	public Disjunction toDNF() {
		
		Disjunction result = null;
		
		for (Constraint member : getMembers()) {
			Disjunction dnf = member.toDNF();
			if (result==null) {
				result = dnf;
				continue;
			}
			Disjunction newResult = new Disjunction();
			for (Constraint c1 : result.getMembers()) {
				for (Constraint c2 : dnf.getMembers()) {
					newResult.getMembers().add( new Conjunction((Conjunction) c1, (Conjunction) c2) );
				}
			}
			result = newResult;
		}
		
		return result!=null ? result : new Disjunction();
	}		


	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getMembers()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> members;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.CONJUNCTION;
	}

	/**
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getConjunction_Members()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Constraint> getMembers() {
		if (members == null) {
			members = new EObjectContainmentEList<Constraint>(Constraint.class, this, ConstraintsPackage.CONJUNCTION__MEMBERS);
		}
		return members;
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.CONJUNCTION__MEMBERS:
				return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.CONJUNCTION__MEMBERS:
				return getMembers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.CONJUNCTION__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection<? extends Constraint>)newValue);
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
			case ConstraintsPackage.CONJUNCTION__MEMBERS:
				getMembers().clear();
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
			case ConstraintsPackage.CONJUNCTION__MEMBERS:
				return members != null && !members.isEmpty();
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Constraint.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Composite.class) {
			switch (derivedFeatureID) {
				case ConstraintsPackage.CONJUNCTION__MEMBERS: return ConstraintsPackage.COMPOSITE__MEMBERS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Constraint.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Composite.class) {
			switch (baseFeatureID) {
				case ConstraintsPackage.COMPOSITE__MEMBERS: return ConstraintsPackage.CONJUNCTION__MEMBERS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
		
}
