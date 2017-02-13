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
package org.enterprisedomain.classsupplier.jobs;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.enterprisedomain.classsupplier.Stage;

public class DelegatingJob extends EnterpriseDomainJob {

	private Job delegate;

	private Stage jobStage;

	private Stage dirtyStage;

	public DelegatingJob(Job delegate, long runId) {
		super(delegate.getName(), runId);
		this.delegate = delegate;
	}

	@Override
	public IStatus work(IProgressMonitor monitor) throws CoreException {
		delegate.schedule();
		try {
			delegate.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return delegate.getResult();
	}

	@Override
	public Stage getPrerequisiteStage() {
		return Stage.DEFINED;
	}

	public void setResultStage(Stage resultStage) {
		this.jobStage = resultStage;
	}

	@Override
	public Stage getResultStage() {
		return jobStage;
	}

	public void setDirtyStage(Stage dirtyStage) {
		this.dirtyStage = dirtyStage;
	}

	@Override
	public Stage getDirtyStage() {
		return dirtyStage;
	}

}
