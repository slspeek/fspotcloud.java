package fspotcloud.server.model.test.gae;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import fspotcloud.server.model.test.GaeLocalDatastoreTestWrapper;
import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import javax.persistence.EntityManager;

public class PeerDatabaseGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("gae");
        bind(SimpleDAONamedId.class).to(PeerDatabaseManager.class);
    }
}
