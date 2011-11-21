package fspotcloud.taskqueuedispatch.inject;

import javax.inject.Singleton;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.taskqueuedispatch.TaskQueueDispatchImpl;


public class TaskQueueDispatchModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TaskQueueDispatch.class).to(TaskQueueDispatchImpl.class).in(Singleton.class);
		bind(Queue.class).annotatedWith(Names.named("dispatch")).toInstance(
				QueueFactory.getDefaultQueue());
	}
	
}
