package fspotcloud.server.model.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.peerdatabase.PeerDatabaseManager;
import fspotcloud.model.jpa.photo.PhotoManager;
import fspotcloud.model.jpa.tag.TagManager;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.simplejpadao.EMProvider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class J2eeModelGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        install(new ModelModule());
    }
}
class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        bind(EntityManager.class).toProvider(EMProvider.class);
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);
        System.out.println("Test J2EE ModelModule configured.");
    }
}
