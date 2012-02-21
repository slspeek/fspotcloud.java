package fspotcloud.server.model.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;
import fspotcloud.model.jpa.photo.PhotoManager;
import fspotcloud.model.jpa.tag.TagManager;
import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import javax.persistence.EntityManager;


public class PhotoGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("derby");
        bind(SimpleDAONamedId.class).to(PhotoManager.class);
    }
}
