package fspotcloud.server.inject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class FSpotCloudModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
				new Integer(100));
		bind(Queue.class).annotatedWith(Names.named("default")).toInstance(
				QueueFactory.getDefaultQueue());
	}

	@Provides
	DatastoreService getDatastoreService() {
		return DatastoreServiceFactory.getDatastoreService();
	}

}
