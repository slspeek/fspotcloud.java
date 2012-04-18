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

    private int maxDelete;

    public ModelModule(int maxDelete) {
        this.maxDelete = maxDelete;
    }

    @Override
    protected void configure() {
        bind(Photos.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabases.class).to(PeerDatabaseManager.class).in(
                Singleton.class);
        bind(Tags.class).to(TagManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxDelete")).toInstance(maxDelete);
        install(new EntityModule("derby"));
    }
}
