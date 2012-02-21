package fspotcloud.model.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import fspotcloud.model.jpa.gae.photo.PhotoManager;
import fspotcloud.model.jpa.gae.tag.TagManager;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.simplejpadao.EMProvider;
import javax.persistence.EntityManager;

public class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
          bind(EntityManager.class).toProvider(EMProvider.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("gae");
        System.out.println("ModelModule configured.");
        
    }
}
