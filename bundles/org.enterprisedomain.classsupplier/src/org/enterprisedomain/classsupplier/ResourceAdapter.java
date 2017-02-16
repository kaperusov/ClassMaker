/**
 * Copyright 2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classsupplier;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classsupplier.ResourceAdapter#getResource <em>Resource</em>}</li>
 *   <li>{@link org.enterprisedomain.classsupplier.ResourceAdapter#getFilename <em>Filename</em>}</li>
 * </ul>
 *
 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getResourceAdapter()
 * @model superTypes="org.enterprisedomain.classsupplier.Adapter"
 * @generated
 */
public interface ResourceAdapter extends EObject, Adapter {

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(Resource)
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getResourceAdapter_Resource()
	 * @model type="org.enterprisedomain.classsupplier.Resource"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the '{@link org.enterprisedomain.classsupplier.ResourceAdapter#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

	/**
	 * Returns the value of the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filename</em>' attribute.
	 * @see org.enterprisedomain.classsupplier.ClassSupplierPackage#getResourceAdapter_Filename()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getFilename();
} // ResourceAdapter