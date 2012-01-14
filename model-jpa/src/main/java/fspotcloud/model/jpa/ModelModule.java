package fspotcloud.model.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import fspotcloud.model.jpa.tag.TagManager;
import fspotcloud.server.model.api.Tags;

public class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
//        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
//                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        install(new JpaPersistModule("derby"));
        System.out.println("ModelModule configured.");
    }
}
