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
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.controller.inject.LocalControllerModule;
import com.googlecode.fspotcloud.model.jpa.ModelModule;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.tag.TagManager;
import com.googlecode.fspotcloud.peer.CopyDatabase;
import com.googlecode.fspotcloud.peer.ImageData;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.server.admin.handler.*;
import com.googlecode.fspotcloud.server.control.task.inject.TaskActionsModule;
import com.googlecode.fspotcloud.server.main.handler.GetTagTreeHandler;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.UserDeletesAllAction;
import com.googlecode.fspotcloud.shared.dashboard.UserImportsTagAction;
import com.googlecode.fspotcloud.shared.dashboard.UserSynchronizesPeerAction;
import com.googlecode.fspotcloud.shared.dashboard.UserUnImportsTagAction;
import com.googlecode.fspotcloud.shared.main.GetTagTreeAction;
import com.googlecode.fspotcloud.shared.peer.ImageSpecs;
import com.googlecode.fspotcloud.user.LenientUserModule;
import com.googlecode.fspotcloud.user.LenientUserService;
import com.googlecode.fspotcloud.user.UserService;
import com.googlecode.simplejpadao.EntityModule;
import com.googlecode.taskqueuedispatch.inject.TaskQueueDispatchDirectModule;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;


public class J2eeIntegrationGuiceBerryEnv extends GuiceBerryModule {
    @Override
    public void configure() {
        super.configure();
        System.setProperty("photo.dir.original", "//home/steven/Photos");
        System.setProperty("photo.dir.override",
            "" + System.getProperty("user.dir") +
            "/../peer/src/test/resources/Photos");
        bind(Integer.class).annotatedWith(Names.named("maxTicks"))
            .toInstance(new Integer(3));
        install(new MyAdminActionsModule());
        install(new ModelModule(100));
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
