package fspotcloud.taskqueuedispatch.inject;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;

import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.taskqueuedispatch.TaskQueueDispatchDirectImpl;


public class TaskQueueDispatchDirectModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TaskQueueDispatch.class).to(TaskQueueDispatchDirectImpl.class).in(Singleton.class);
	}
	
}
