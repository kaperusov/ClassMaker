/**
 */
package org.classsupplier;

import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Workspace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.classsupplier.ModelWorkspace#getContents <em>Contents</em>}</li>
 *   <li>{@link org.classsupplier.ModelWorkspace#getResourceSet <em>Resource Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.classsupplier.ClassSupplierPackage#getModelWorkspace()
 * @model
 * @generated
 */
public interface ModelWorkspace extends EObject {
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link org.classsupplier.Bundle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see org.classsupplier.ClassSupplierPackage#getModelWorkspace_Contents()
	 * @model containment="true"
	 * @generated
	 */
	EList<Bundle> getContents();

	/**
	 * Returns the value of the '<em><b>Resource Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Set</em>' attribute.
	 * @see #setResourceSet(ResourceSet)
	 * @see org.classsupplier.ClassSupplierPackage#getModelWorkspace_ResourceSet()
	 * @model transient="true"
	 * @generated
	 */
	ResourceSet getResourceSet();

	/**
	 * Sets the value of the '{@link org.classsupplier.ModelWorkspace#getResourceSet <em>Resource Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Set</em>' attribute.
	 * @see #getResourceSet()
	 * @generated
	 */
	void setResourceSet(ResourceSet value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model listenerDataType="org.classsupplier.Adapter"
	 * @generated
	 */
	void addRefreshListener(Adapter listener);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model listenerDataType="org.classsupplier.Adapter"
	 * @generated
	 */
	void removeRefreshListener(Adapter listener);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void registerBundle(EPackage model, Bundle bundle);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean containsBundle(EPackage model);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Bundle getBundle(String projectName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Bundle getBundle(EPackage ePackage);

} // ModelWorkspace
