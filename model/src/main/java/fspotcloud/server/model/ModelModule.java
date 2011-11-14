package fspotcloud.server.model;

import javax.jdo.PersistenceManager;

import net.sf.jsr107cache.Cache;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.photo.CachedPhotoManager;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.model.tag.CachedTagManager;
import fspotcloud.server.model.tag.TagManager;

public class ModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Photos.class).to(CachedPhotoManager.class).in(Singleton.class);
		bind(Photos.class).annotatedWith(Names.named("uncached"))
				.to(PhotoManager.class).in(Singleton.class);
		bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
				Singleton.class);
		bind(Tags.class).to(CachedTagManager.class).in(Singleton.class);
		bind(Tags.class).annotatedWith(Names.named("uncached"))
				.to(TagManager.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(
				PersistenceManagerProvider.class);
		bind(Cache.class).toProvider(MemCacheProvider.class);
	}
}
