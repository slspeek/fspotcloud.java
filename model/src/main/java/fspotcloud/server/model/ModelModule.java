package fspotcloud.server.model;

import javax.jdo.PersistenceManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.model.tag.TagManager;

public class ModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(
				new Integer(100));
		bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
		bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
				Singleton.class);
		bind(Tags.class).to(TagManager.class).in(Singleton.class);
		// bind(Commands.class).to(CommandManager.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(PersistenceManagerProvider.class);
	}
}
