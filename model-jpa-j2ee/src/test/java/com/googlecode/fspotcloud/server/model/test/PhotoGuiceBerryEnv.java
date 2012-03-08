package com.googlecode.fspotcloud.server.model.test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoManager;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.simplejpadao.SimpleDAONamedId;

public class PhotoGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        install(new EntityModule("derby-test"));
        bind(SimpleDAONamedId.class).to(PhotoManager.class);
    }
}
