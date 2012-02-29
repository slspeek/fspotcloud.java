package fspotcloud.server.model.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.peerdatabase.PeerDatabaseManager;
import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PeerDatabaseGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        bind(EntityManager.class).toProvider(EMProvider.class);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);
        bind(SimpleDAONamedId.class).to(PeerDatabaseManager.class);
    }
}
