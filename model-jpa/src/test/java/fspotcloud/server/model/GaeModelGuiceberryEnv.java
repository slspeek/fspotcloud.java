package fspotcloud.server.model;

import javax.inject.Inject;

import com.google.guiceberry.GuiceBerryEnvMain;
import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import fspotcloud.model.jpa.tag.TagManager;
import fspotcloud.server.model.api.Tags;

public class GaeModelGuiceberryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(GuiceBerryEnvMain.class).to(PersistServiceInitMain.class);
        install(new ModelModule());
    }

    static class PersistServiceInitMain implements GuiceBerryEnvMain {

        @Inject
        PersistService service;

        @Override
        public void run() {
            System.setProperty("appengine.orm.duplicate.emf.exception", "true");
            service.start();
            System.out.println("Persistency Started! ");
        }
    }
}

class ModelModule extends AbstractModule {

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
