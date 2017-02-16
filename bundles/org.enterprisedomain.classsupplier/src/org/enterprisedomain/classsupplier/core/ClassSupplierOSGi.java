/**
 * Copyright 2012-2016 Kyrill Zotkin
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
package org.enterprisedomain.classsupplier.core;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.enterprisedomain.classsupplier.ClassSupplier;
import org.enterprisedomain.classsupplier.ClassSupplierFactory;
import org.enterprisedomain.classsupplier.impl.ClassSupplierImpl;
import org.enterprisedomain.classsupplier.jobs.ProgressMonitorFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class ClassSupplierOSGi extends Plugin {

	public static final String PLUGIN_ID = "org.enterprisedomain.classsupplier"; //$NON-NLS-1$

	public static final String NATURE_ID = PLUGIN_ID + '.' + "enterpriseDomainNature"; //$NON-NLS-1$

	public static final String PDE_PLUGIN_NATURE = "org.eclipse.pde.PluginNature"; //$NON-NLS-1$

	public static final String MODEL_FOLDER_PREF_KEY = "modelFolder"; //$NON-NLS-1$

	public static final String MODEL_RESOURCE_EXT_PREF_KEY = "resourceExtension"; //$NON-NLS-1$

	private static ClassSupplierOSGi instance;

	private static Class<? extends IProgressMonitor> monitorClass;

	private static Object[] monitorParameters;

	private static ServiceTracker<ClassSupplier, ClassSupplierImpl> tracker;

	private ServiceRegistration<ClassSupplier> reg;

	public static ClassSupplierOSGi getInstance() {
		return instance;
	}

	/**
	 * Only for internal use.
	 * 
	 * @return enterpriseDomain service instance
	 */
	public static ClassSupplier getClassSupplier() {
		return tracker.getService();
	}

	public static <T> T getService(String serviceClass) {
		if (getInstance() == null)
			return null;
		BundleContext context = getInstance().getBundle().getBundleContext();
		return getService(serviceClass, context);
	}

	public static <T> T getService(String serviceClass, BundleContext context) {
		ServiceTracker<T, T> tracker = new ServiceTracker<T, T>(context, serviceClass, null);
		tracker.open();
		T result = tracker.getService();
		tracker.close();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		instance = this;

		reg = context.registerService(ClassSupplier.class, ClassSupplierFactory.eINSTANCE.createClassSupplier(), null);
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(IContextFunction.SERVICE_CONTEXT_KEY, ClassSupplier.class.getName());
		context.registerService(IContextFunction.SERVICE_NAME, new ServiceFactory(), properties);
		tracker = new ServiceTracker<ClassSupplier, ClassSupplierImpl>(context, ClassSupplier.class, null);
		tracker.open();
		context.addBundleListener(new BundleListener() {

			@Override
			public void bundleChanged(BundleEvent event) {
				if (event.getBundle().getSymbolicName().equals(PLUGIN_ID))
					if (event.getType() == BundleEvent.STARTED) {
						getClassSupplier().getWorkspace().initialize();
						event.getBundle().getBundleContext().removeBundleListener(this);
					}

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		IProgressMonitor monitor = getInstance().getProgressMonitor();
		Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, monitor);
		ResourcesPlugin.getWorkspace().save(true, monitor);
		tracker.close();
		reg.unregister();
		instance = null;

	}

	public IProgressMonitor getProgressMonitor() {
		return createProgressMonitor();
	}

	public static <T extends IProgressMonitor> void setMonitorParameters(Class<T> monitorClass,
			Object... constructorParameters) {
		ClassSupplierOSGi.monitorClass = monitorClass;
		ClassSupplierOSGi.monitorParameters = constructorParameters;
	}

	public static Class<? extends IProgressMonitor> getProgressMonitorClass() {
		return ClassSupplierOSGi.monitorClass;
	}

	public static Object[] getProgressMonitorClassConstructorParameters() {
		return ClassSupplierOSGi.monitorParameters;
	}

	private IProgressMonitor createProgressMonitor() {
		if (monitorClass == null || monitorParameters == null)
			setMonitorParameters(CodeGenUtil.EclipseUtil.StreamProgressMonitor.class, System.out);
		return ProgressMonitorFactory.create(monitorClass, monitorParameters);
	}

	public static IStatus createOKStatus(String message) {
		return new Status(IStatus.OK, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createInfoStatus(String message) {
		return new Status(IStatus.INFO, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createWarningStatus(String message) {
		return new Status(IStatus.WARNING, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createWarningStatus(Throwable exception) {
		return new Status(IStatus.WARNING, ClassSupplierOSGi.PLUGIN_ID, exception.getLocalizedMessage(), exception);
	}

	public static IStatus createWarningStatus(String message, Throwable exception) {
		return new Status(IStatus.WARNING, ClassSupplierOSGi.PLUGIN_ID,
				message + ": " + exception.getLocalizedMessage(), exception);
	}

	public static IStatus createErrorStatus(String message) {
		return new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID, message);
	}

	public static IStatus createErrorStatus(Throwable exception) {
		return new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID, exception.getLocalizedMessage(), exception);
	}

	public static String bundleStateAsString(int state) {
		switch (state) {
		case Bundle.ACTIVE:
			return "ACTIVE"; //$NON-NLS-1$
		case Bundle.INSTALLED:
			return "INSTALLED"; //$NON-NLS-1$
		case Bundle.RESOLVED:
			return "RESOLVED"; //$NON-NLS-1$
		case Bundle.STARTING:
			return "STARTING"; //$NON-NLS-1$
		case Bundle.STOPPING:
			return "STOPPING"; //$NON-NLS-1$
		case Bundle.UNINSTALLED:
			return "UNINSTALLED"; //$NON-NLS-1$
		default:
			return "unknown bundle state: " + state; //$NON-NLS-1$
		}
	}

}