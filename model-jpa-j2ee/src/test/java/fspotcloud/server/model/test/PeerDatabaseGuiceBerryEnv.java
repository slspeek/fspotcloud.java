package fspotcloud.server.model.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.peerdatabase.PeerDatabaseManager;
import fspotcloud.simplejpadao.EntityModule;
import fspotcloud.simplejpadao.SimpleDAONamedId;

public class PeerDatabaseGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
       install(new EntityModule("derby"));
        bind(SimpleDAONamedId.class).to(PeerDatabaseManager.class);
    }
}
