package fspotcloud.server.inject;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.server.control.Controller;
import fspotcloud.server.control.GuiceRequestProcessorFactoryFactory;
import fspotcloud.server.control.reciever.MetaReciever;
import fspotcloud.server.control.reciever.PhotoReciever;
import fspotcloud.server.control.reciever.TagReciever;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Batches;
import fspotcloud.server.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.batch.BatchManager;
import fspotcloud.server.model.command.CommandManager;
import fspotcloud.server.model.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.model.tag.TagManager;

public class FSpotCloudModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Batches.class).to(BatchManager.class).in(Singleton.class);
		bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
		bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(Singleton.class);
		bind(Tags.class).to(TagManager.class).in(Singleton.class);
		bind(Commands.class).to(CommandManager.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(
				PersistenceManagerProvider.class);
		bind(CommandManager.class).in(Singleton.class);
		bind(PhotoManager.class).in(Singleton.class);
		bind(TagManager.class).in(Singleton.class);
		bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(
				new Integer(100));
		bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
				new Integer(100));
		bind(Integer.class).annotatedWith(Names.named("maxCount")).toInstance(
				new Integer(333));
		/* For XmlRpc server */
		bind(GuiceRequestProcessorFactoryFactory.class).in(Singleton.class);
		bind(Controller.class).in(Singleton.class);
		bind(MetaReciever.class).in(Singleton.class);
		bind(TagReciever.class).in(Singleton.class);
		bind(PhotoReciever.class).in(Singleton.class);
		
		bind(Queue.class).annotatedWith(Names.named("default")).toInstance(
				QueueFactory.getDefaultQueue());
	}

}
