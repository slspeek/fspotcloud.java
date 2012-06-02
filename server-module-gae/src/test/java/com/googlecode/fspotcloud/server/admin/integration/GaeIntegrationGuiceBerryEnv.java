/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.server.admin.integration;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.controller.inject.LocalControllerModule;
import com.googlecode.fspotcloud.model.jpa.CachedModelModule;
import com.googlecode.fspotcloud.model.jpa.gae.GaeCacheProvider;
import com.googlecode.fspotcloud.model.jpa.gae.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.gae.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.gae.tag.TagManager;
import com.googlecode.fspotcloud.peer.CopyDatabase;
import com.googlecode.fspotcloud.peer.ImageData;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.server.admin.handler.UserDeletesAllHandler;
import com.googlecode.fspotcloud.server.admin.handler.UserImportsTagHandler;
import com.googlecode.fspotcloud.server.admin.handler.UserSynchronizesPeerHandler;
import com.googlecode.fspotcloud.server.admin.handler.UserUnImportsTagHandler;
import com.googlecode.fspotcloud.server.control.task.inject.TaskActionsModule;
import com.googlecode.fspotcloud.server.inject.MainActionModule;
import com.googlecode.fspotcloud.server.main.handler.GetTagTreeHandler;
import com.googlecode.fspotcloud.server.model.api.PeerDatabaseDao;
import com.googlecode.fspotcloud.server.model.api.PhotoDao;
import com.googlecode.fspotcloud.server.model.api.TagDao;
import com.googlecode.fspotcloud.shared.dashboard.UserDeletesAllAction;
import com.googlecode.fspotcloud.shared.dashboard.UserImportsTagAction;
import com.googlecode.fspotcloud.shared.dashboard.UserSynchronizesPeerAction;
import com.googlecode.fspotcloud.shared.dashboard.UserUnImportsTagAction;
import com.googlecode.fspotcloud.shared.main.GetTagTreeAction;
import com.googlecode.fspotcloud.shared.peer.ImageSpecs;
import com.googlecode.fspotcloud.user.LenientUserModule;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import net.sf.jsr107cache.Cache;


public class GaeIntegrationGuiceBerryEnv extends GuiceBerryModule {
    @Override
    public void configure() {
        super.configure();
        System.setProperty("photo.dir.original", "//home/steven/PhotoDao");
        System.setProperty("photo.dir.override",
            "" + System.getProperty("user.dir") +
            "/../peer/src/test/resources/PhotoDao");
        bind(Integer.class).annotatedWith(Names.named("maxTicks"))
            .toInstance(new Integer(3));
        install(new MyAdminActionsModule());
        install(new CachedModelModule(1));
        //install(new MyModelModule());
        bind(ImageSpecs.class).annotatedWith(Names.named("defaultImageSpecs"))
            .toInstance(new ImageSpecs(1024, 768, 512, 378));
        bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks"))
            .toInstance(2);
        install(new LocalControllerModule());
        install(new TaskQueueDispatchDirectModule());
        install(new TaskActionsModule());
        install(new PeerActionsModule());
        install(new MyPeerModule());
        install(new LenientUserModule());
        bind(Cache.class).toProvider(GaeCacheProvider.class);
        install(new MainActionModule());

        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
    }
}


class MyModelModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PhotoDao.class).to(PhotoManager.class).in(Singleton.class);
        bind(PeerDatabaseDao.class).to(PeerDatabaseManager.class)
            .in(Singleton.class);
        bind(TagDao.class).to(TagManager.class).in(Singleton.class);

        bind(Integer.class).annotatedWith(Names.named("maxDelete"))
            .toInstance(new Integer(1));
        install(new EntityModule("gae"));
    }
}


class MyAdminActionsModule extends ActionHandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(UserDeletesAllAction.class, UserDeletesAllHandler.class);
        bindHandler(UserImportsTagAction.class, UserImportsTagHandler.class);
        bindHandler(UserUnImportsTagAction.class, UserUnImportsTagHandler.class);
        bindHandler(UserSynchronizesPeerAction.class,
            UserSynchronizesPeerHandler.class);
        bindHandler(GetTagTreeAction.class, GetTagTreeHandler.class);
    }
}


class MyPeerModule extends AbstractModule {
    protected void configure() {
        bind(Data.class).in(Singleton.class);
        bind(ImageData.class);
        bind(String.class).annotatedWith(Names.named("JDBC URL"))
            .toProvider(CopyDatabase.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("DatabasePath"))
            .toInstance(System.getProperty("db",
                System.getProperty("user.dir") +
                "/../peer/src/test/resources/photos.db"));
        bind(String.class).annotatedWith(Names.named("WorkDir"))
            .toInstance(System.getProperty("user.dir"));
        bind(Integer.class).annotatedWith(Names.named("stop port"))
            .toInstance(Integer.valueOf(System.getProperty("stop.port", "4444")));
    }
}
