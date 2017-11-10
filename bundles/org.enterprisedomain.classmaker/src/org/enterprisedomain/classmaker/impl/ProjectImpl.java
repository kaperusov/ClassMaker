/**
 * Copyright 2012-2017 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.ClassMakerFactory;
import org.enterprisedomain.classmaker.ClassMakerPackage;
import org.enterprisedomain.classmaker.CompletionListener;
import org.enterprisedomain.classmaker.CompletionNotificationAdapter;
import org.enterprisedomain.classmaker.Project;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.util.ResourceUtils;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Project</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#isDirty <em>Dirty</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#isNeedCompletionNotification <em>Need Completion Notification</em>}</li>
 *   <li>{@link org.enterprisedomain.classmaker.impl.ProjectImpl#getCompletionNotificationAdapter <em>Completion Notification Adapter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProjectImpl extends EObjectImpl implements Project {

	protected ListenerList<CompletionListener> listeners = new ListenerList<CompletionListener>();

	public class ProjectNameAdapter extends AdapterImpl {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(State.class) == ClassMakerPackage.PROJECT__NAME
					&& msg.getEventType() == Notification.SET && msg.getNewStringValue() != null)
				setProjectName(ClassMakerPlugin.getClassMaker().computeProjectName(msg.getNewStringValue()));
		}

	}

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isDirty() <em>Dirty</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DIRTY_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isNeedCompletionNotification() <em>Need Completion Notification</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isNeedCompletionNotification()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEED_COMPLETION_NOTIFICATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedCompletionNotification() <em>Need Completion Notification</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isNeedCompletionNotification()
	 * @generated
	 * @ordered
	 */
	protected boolean needCompletionNotification = NEED_COMPLETION_NOTIFICATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCompletionNotificationAdapter()
	 * <em>Completion Notification Adapter</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCompletionNotificationAdapter()
	 * @generated
	 * @ordered
	 */
	protected CompletionNotificationAdapter completionNotificationAdapter;

	protected EList<Object> children;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ProjectImpl() {
		super();
		setCompletionNotificationAdapter(ClassMakerFactory.eINSTANCE.createCompletionNotificationAdapter());
		eAdapters().add(getCompletionNotificationAdapter());
		eAdapters().add(new ProjectNameAdapter());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClassMakerPackage.Literals.PROJECT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = CodeGenUtil.safeName(newProjectName);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__PROJECT_NAME,
					oldProjectName, projectName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<Object> getChildren() {
		if (children == null) {
			children = ECollections.newBasicEList();
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDirty() {
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Workspace getWorkspace() {
		if (eContainerFeatureID() != ClassMakerPackage.PROJECT__WORKSPACE)
			return null;
		return (Workspace) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkspace(Workspace newWorkspace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newWorkspace, ClassMakerPackage.PROJECT__WORKSPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkspace(Workspace newWorkspace) {
		if (newWorkspace != eInternalContainer()
				|| (eContainerFeatureID() != ClassMakerPackage.PROJECT__WORKSPACE && newWorkspace != null)) {
			if (EcoreUtil.isAncestor(this, newWorkspace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this, ClassMakerPackage.WORKSPACE__PROJECTS,
						Workspace.class, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClassMakerPackage.PROJECT__WORKSPACE, newWorkspace,
					newWorkspace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeedCompletionNotification() {
		return needCompletionNotification;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeedCompletionNotification(boolean newNeedCompletionNotification) {
		boolean oldNeedCompletionNotification = needCompletionNotification;
		needCompletionNotification = newNeedCompletionNotification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION, oldNeedCompletionNotification,
					needCompletionNotification));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CompletionNotificationAdapter getCompletionNotificationAdapter() {
		return completionNotificationAdapter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompletionNotificationAdapter(
			CompletionNotificationAdapter newCompletionNotificationAdapter, NotificationChain msgs) {
		CompletionNotificationAdapter oldCompletionNotificationAdapter = completionNotificationAdapter;
		completionNotificationAdapter = newCompletionNotificationAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, oldCompletionNotificationAdapter,
					newCompletionNotificationAdapter);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompletionNotificationAdapter(CompletionNotificationAdapter newCompletionNotificationAdapter) {
		if (newCompletionNotificationAdapter != completionNotificationAdapter) {
			NotificationChain msgs = null;
			if (completionNotificationAdapter != null)
				msgs = ((InternalEObject) completionNotificationAdapter).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, null,
						msgs);
			if (newCompletionNotificationAdapter != null)
				msgs = ((InternalEObject) newCompletionNotificationAdapter).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, null,
						msgs);
			msgs = basicSetCompletionNotificationAdapter(newCompletionNotificationAdapter, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER, newCompletionNotificationAdapter,
					newCompletionNotificationAdapter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void create(IProgressMonitor monitor) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String projectName = getProjectName();
		IProject project = workspace.getRoot().getProject(projectName);
		ResourceUtils.createProject(project, null, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(IProgressMonitor monitor) throws CoreException {
		if (eIsSet(ClassMakerPackage.PROJECT__WORKSPACE))
			getWorkspace().unregisterProject(this);
		String projectName = getProjectName();
		if (projectName.isEmpty())
			return;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(projectName);
		project.delete(true, true, monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void delete(EList<Object> objects) {
		((Resource) getChildren().get(0)).getContents().removeAll(objects);
	}

	public void notifyCompletion() throws Exception {
		for (Object listener : listeners.getListeners())
			((CompletionListener) listener).completed(this);
		setNeedCompletionNotification(false);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String make(IProgressMonitor monitor) throws CoreException {
		return "You made it!";
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean open(IProgressMonitor monitor) throws CoreException {
		String projectName = getProjectName();
		if (projectName.isEmpty())
			return false;
		initialize(false);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(projectName);
		if (project.isOpen())
			return true;
		project.open(monitor);
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void close(IProgressMonitor monitor) throws CoreException {
		String projectName = getProjectName();
		if (projectName.isEmpty())
			return;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(projectName);
		project.close(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String initialize(boolean commit) {
		URI uri = createResourceURI();
		Resource resource = null;
		if (new File(uri.toFileString()).exists()) {
			getWorkspace().getResourceSet().getResource(uri, false);
			try {
				resource.load(Collections.emptyMap());
			} catch (IOException e) {
				ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
			}
		} else {
			resource = getWorkspace().getResourceSet().createResource(uri);
			if (commit)
				try {
					resource.save(Collections.emptyMap());
				} catch (IOException e) {
					ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
				}
		}
		getChildren().add(resource);
		// TODO Add SCM support if commit
		return "";
	}

	private URI createResourceURI() {
		IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		return URI.createFileURI(workspacePath.toString() + IPath.SEPARATOR + getProjectName() + IPath.SEPARATOR
				+ getName() + "." + ResourceUtils.getModelFileExt());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addCompletionListener(CompletionListener resultListener) {
		listeners.add(resultListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeCompletionListener(CompletionListener resultListener) {
		listeners.remove(resultListener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void onModelResourceCreate(Resource eResource) {
		for (Adapter adapter : eAdapters())
			if (adapter.isAdapterForType(Resource.class)) {
				adapter.notifyChanged(new NotificationImpl(Notification.SET, null, eResource));
			}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__WORKSPACE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetWorkspace((Workspace) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return basicSetWorkspace(null, msgs);
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			return basicSetCompletionNotificationAdapter(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return eInternalContainer().eInverseRemove(this, ClassMakerPackage.WORKSPACE__PROJECTS, Workspace.class,
					msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		if (this == rule)
			return true;
		if (rule instanceof MultiRule) {
			MultiRule multi = (MultiRule) rule;
			ISchedulingRule[] children = multi.getChildren();
			for (int i = 0; i < children.length; i++)
				if (!contains(children[i]))
					return false;
			return true;
		}
		if (ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).contains(rule))
			return true;
		return getWorkspace().contains(rule);
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (this == rule)
			return true;
		return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).isConflicting(rule)
				|| getWorkspace().isConflicting(rule);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__NAME:
			return getName();
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			return getProjectName();
		case ClassMakerPackage.PROJECT__CHILDREN:
			return getChildren();
		case ClassMakerPackage.PROJECT__DIRTY:
			return isDirty();
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return getWorkspace();
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			return isNeedCompletionNotification();
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			return getCompletionNotificationAdapter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__NAME:
			setName((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			setProjectName((String) newValue);
			return;
		case ClassMakerPackage.PROJECT__WORKSPACE:
			setWorkspace((Workspace) newValue);
			return;
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			setNeedCompletionNotification((Boolean) newValue);
			return;
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			setCompletionNotificationAdapter((CompletionNotificationAdapter) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			setProjectName(PROJECT_NAME_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__WORKSPACE:
			setWorkspace((Workspace) null);
			return;
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			setNeedCompletionNotification(NEED_COMPLETION_NOTIFICATION_EDEFAULT);
			return;
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			setCompletionNotificationAdapter((CompletionNotificationAdapter) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ClassMakerPackage.PROJECT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ClassMakerPackage.PROJECT__PROJECT_NAME:
			return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
		case ClassMakerPackage.PROJECT__CHILDREN:
			return !getChildren().isEmpty();
		case ClassMakerPackage.PROJECT__DIRTY:
			return isDirty() != DIRTY_EDEFAULT;
		case ClassMakerPackage.PROJECT__WORKSPACE:
			return getWorkspace() != null;
		case ClassMakerPackage.PROJECT__NEED_COMPLETION_NOTIFICATION:
			return needCompletionNotification != NEED_COMPLETION_NOTIFICATION_EDEFAULT;
		case ClassMakerPackage.PROJECT__COMPLETION_NOTIFICATION_ADAPTER:
			return completionNotificationAdapter != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectImpl other = (ProjectImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", projectName: ");
		result.append(projectName);
		result.append(", needCompletionNotification: ");
		result.append(needCompletionNotification);
		result.append(')');
		return result.toString();
	}

} // ProjectImpl
