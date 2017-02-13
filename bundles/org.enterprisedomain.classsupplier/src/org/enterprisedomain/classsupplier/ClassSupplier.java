/**
 * Copyright 2012-2016 Kyrill Zotkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classsupplier;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meta Maker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.ClassSupplier#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getClassSupplier()
 * @model
 * @generated
 */
public interface ClassSupplier extends EObject {

	public static final String INVOCATION_DELEGATE_URI = "org.enterprisedomain.classsupplier.reflection.java"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' reference.
	 * @see #setWorkspace(Workspace)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getClassSupplier_Workspace()
	 * @model
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.ClassSupplier#getWorkspace <em>Workspace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace</em>' reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException"
	 * @generated
	 */
	EPackage create(EPackage dynamicModel) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	EPackage create(EPackage dynamicModel, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException"
	 * @generated
	 */
	EPackage update(EPackage queryModel, EPackage dynamicModel) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	EPackage update(EPackage queryModel, EPackage dynamicModel, IProgressMonitor monitor) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException"
	 * @generated
	 */
	EPackage update(EPackage queryModel, EPackage dynamicModel, boolean changeVersion) throws CoreException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="org.enterprisedomain.classsupplier.CoreException" monitorDataType="org.enterprisedomain.classsupplier.IProgressMonitor"
	 * @generated
	 */
	EPackage update(EPackage queryModel, EPackage dynamicModel, boolean changeVersion, IProgressMonitor monitor)
			throws CoreException;

} // ClassSupplier
