package com.googlecode.fspotcloud.model.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.tag.TagManager;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.simplejpadao.EntityModule;
import java.util.logging.Logger;

public class ModelModule extends AbstractModule {

    private static final Logger log = Logger.getLogger(ModelModule.class.getName());

    @Override
    protected void configure() {
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(new Integer(100));
        install(new EntityModule("derby"));
        System.out.println("ModelModule configured.");
    }
}