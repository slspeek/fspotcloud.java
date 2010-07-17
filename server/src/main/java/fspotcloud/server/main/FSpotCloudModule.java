package fspotcloud.server.main;

import javax.jdo.PersistenceManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.server.control.Controller;
import fspotcloud.server.control.GuiceRequestProcessorFactoryFactory;
import fspotcloud.server.control.reciever.MetaReciever;
import fspotcloud.server.control.reciever.PhotoReciever;
import fspotcloud.server.control.reciever.TagReciever;
import fspotcloud.server.model.command.CommandDAO;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.model.tag.TagReader;
import fspotcloud.server.util.PersistenceManagerProvider;

public class FSpotCloudModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(DefaultPeer.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(
				PersistenceManagerProvider.class);
		bind(CommandDAO.class).in(Singleton.class);
		bind(PhotoManager.class).in(Singleton.class);
		bind(TagReader.class).in(Singleton.class);
		bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(
				new Integer(100));
		bind(Integer.class).annotatedWith(Names.named("maxCount")).toInstance(
				new Integer(333));
		/* For XmlRpc server */
		bind(GuiceRequestProcessorFactoryFactory.class).in(Singleton.class);
		bind(Controller.class).in(Singleton.class);
		bind(MetaReciever.class).in(Singleton.class);
		bind(TagReciever.class).in(Singleton.class);
		bind(PhotoReciever.class).in(Singleton.class);
	}

}
