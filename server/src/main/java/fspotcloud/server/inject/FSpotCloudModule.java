package fspotcloud.server.inject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

import fspotcloud.server.control.Controller;
import fspotcloud.server.control.GuiceRequestProcessorFactoryFactory;
import fspotcloud.server.control.Scheduler;
import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.server.control.reciever.MetaReciever;
import fspotcloud.server.control.reciever.PhotoReciever;
import fspotcloud.server.control.reciever.TagReciever;
import fspotcloud.server.mapreduce.MapReduceInfo;
import fspotcloud.server.mapreduce.MapReduceStateUtil;

public class FSpotCloudModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
				new Integer(100));
		/* For XmlRpc server */
		bind(GuiceRequestProcessorFactoryFactory.class).in(Singleton.class);
		bind(Controller.class).in(Singleton.class);
		bind(MetaReciever.class).in(Singleton.class);
		bind(TagReciever.class).in(Singleton.class);
		bind(PhotoReciever.class).in(Singleton.class);

		bind(Queue.class).annotatedWith(Names.named("default")).toInstance(
				QueueFactory.getDefaultQueue());
		bind(SchedulerInterface.class).to(Scheduler.class);
		bind(MapReduceInfo.class).to(MapReduceStateUtil.class);

		install(new FactoryModuleBuilder()
				.build(ImageDataImporterFactory.class));
	}

	@Provides
	DatastoreService getDatastoreService() {
		return DatastoreServiceFactory.getDatastoreService();
	}

}
