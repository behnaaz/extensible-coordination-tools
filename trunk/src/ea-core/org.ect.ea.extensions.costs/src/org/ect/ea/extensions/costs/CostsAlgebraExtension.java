/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.extensions.costs;

import java.text.ParseException;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;
import org.ect.ea.costs.UnsupportedCostsTypeException;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.StringListExtension;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Algebra Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.costs.CostsAlgebraExtension#getCostsAlgebras <em>Costs Algebras</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsAlgebraExtension()
 * @model kind="class"
 * @generated
 */
public class CostsAlgebraExtension extends ExtensionElement {
	
	/**
	 * @generated NOT
	 */
	public String print(CostsObjectExtension costs) {
		
		StringBuffer result = new StringBuffer();
		int count = Math.min(getCostsAlgebras().size(), costs.getCostsObjects().size());
		if (count==0) {
			return "<no costs>";
		}
		for (int i=0; i<count; i++) {
			CostsAlgebra algebra = getCostsAlgebras().get(i);
			CostsObject object = costs.getCostsObjects().get(i);
			try {
				result.append( algebra.prettyPrint(object) );
			} catch (UnsupportedCostsTypeException e) {
				result.append( "?" );
			}
			if (i<count-1) result.append(", ");
		}
		return result.toString();
	}

	
	/**
	 * @generated NOT
	 */
	public CostsObjectExtension createNulls() {
		CostsObjectExtension result = new CostsObjectExtension();
		for (int i=0; i<getCostsAlgebras().size(); i++) {
			CostsAlgebra algebra = getCostsAlgebras().get(i);
			result.getCostsObjects().add( algebra.getNull() );
		}
		return result;
	}

	
	/**
	 * @throws ParseException 
	 * @generated NOT
	 */
	public CostsObjectExtension parse(String values) throws ParseException {
		
		CostsObjectExtension result = new CostsObjectExtension();
		StringListExtension list = StringListExtension.parse(values);
		int count = Math.min(getCostsAlgebras().size(), list.getValues().size());
		
		for (int i=0; i<count; i++) {
			CostsAlgebra algebra = getCostsAlgebras().get(i);
			String value = list.getValues().get(i).trim();
			//System.out.println("Parsing '" + value + "' with algebra " + algebra.getName() + ".");
			result.getCostsObjects().add( algebra.parse(value) );
		}
		
		return result;
	}
	
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		
		if (getCostsAlgebras().isEmpty()) {
			return "<no cost algebras>";
		}
		
		StringBuffer result = new StringBuffer();
		for (int i=0; i<getCostsAlgebras().size(); i++) {
			result.append(getCostsAlgebras().get(i).getName());
			if (i<getCostsAlgebras().size()-1) result.append(", ");
		}
		
		return result.toString();
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CostsAlgebraExtension)) return false;
		CostsAlgebraExtension costs = (CostsAlgebraExtension) object;
		if (costs.getCostsAlgebras().size()!=getCostsAlgebras().size()) return false;
		for (int i=0; i<getCostsAlgebras().size(); i++) {
			CostsAlgebra a1 = getCostsAlgebras().get(i);
			CostsAlgebra a2 = costs.getCostsAlgebras().get(i);
			if (!a1.getClass().equals( a2.getClass() )) return false;
		}
		return true;
	}
	
	/**
	 * The cached value of the '{@link #getCostsAlgebras() <em>Costs Algebras</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostsAlgebras()
	 * @generated
	 * @ordered
	 */
	protected EList<CostsAlgebra> costsAlgebras;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CostsAlgebraExtension() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CostsPackage.Literals.COSTS_ALGEBRA_EXTENSION;
	}

	/**
	 * Returns the value of the '<em><b>Costs Algebras</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.ea.costs.CostsAlgebra}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Costs Algebras</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Costs Algebras</em>' containment reference list.
	 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsAlgebraExtension_CostsAlgebras()
	 * @model containment="true"
	 * @generated
	 */
	public EList<CostsAlgebra> getCostsAlgebras() {
		if (costsAlgebras == null) {
			costsAlgebras = new EObjectContainmentEList<CostsAlgebra>(CostsAlgebra.class, this, CostsPackage.COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS);
		}
		return costsAlgebras;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CostsPackage.COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS:
				return ((InternalEList<?>)getCostsAlgebras()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CostsPackage.COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS:
				return getCostsAlgebras();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CostsPackage.COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS:
				getCostsAlgebras().clear();
				getCostsAlgebras().addAll((Collection<? extends CostsAlgebra>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CostsPackage.COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS:
				getCostsAlgebras().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CostsPackage.COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS:
				return costsAlgebras != null && !costsAlgebras.isEmpty();
		}
		return super.eIsSet(featureID);
	}


} // CostsAlgebraExtension
