package com.googlecode.fspotcloud.server.admin.integration;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.googlecode.botdispatch.controller.inject.LocalControllerModule;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseManager;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoManager;
import com.googlecode.fspotcloud.model.jpa.tag.TagManager;
import com.googlecode.fspotcloud.peer.CopyDatabase;
import com.googlecode.fspotcloud.peer.ImageData;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.server.admin.actions.ImportTagHandler;
import com.googlecode.fspotcloud.server.admin.actions.SynchronizePeerHandler;
import com.googlecode.fspotcloud.server.admin.actions.TagDeleteAllHandler;
import com.googlecode.fspotcloud.server.admin.actions.UnImportTagHandler;
import com.googlecode.fspotcloud.server.control.task.TaskActionsModule;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.ImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.SynchronizePeer;
import com.googlecode.fspotcloud.shared.dashboard.actions.TagDeleteAll;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.ImageSpecs;
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
        System.setProperty("photo.dir.override", "" + System.getProperty("user.dir") + "/../peer/src/test/resources/Photos");
        install(new MyFSpotCloudModule());
        install(new MyAdminActionsModule());
        install(new MyModelModule());
        install(new MyTaskModule());
        install(new LocalControllerModule());
        install(new TaskQueueDispatchDirectModule());
        install(new TaskActionsModule());
        install(new PeerActionsModule());
        install(new MyPeerModule());
        install(new MyUserModule());
    }
}

class MyAdminActionsModule extends ActionHandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(TagDeleteAll.class, TagDeleteAllHandler.class);
        bindHandler(ImportTag.class, ImportTagHandler.class);
        bindHandler(UnImportTag.class, UnImportTagHandler.class);
        bindHandler(SynchronizePeer.class, SynchronizePeerHandler.class);
    }
}

class MyFSpotCloudModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
                new Integer(3));
    }
}

class MyTaskModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ImageSpecs.class).annotatedWith(Names.named("defaultImageSpecs")).toInstance(new ImageSpecs(1024, 768, 512, 378));
        bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks")).toInstance(2);
    }
}

class MyModelModule extends AbstractModule {

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

class MyPeerModule extends AbstractModule {

    protected void configure() {
        bind(Data.class).in(Singleton.class);
        bind(ImageData.class);
        bind(String.class).annotatedWith(Names.named("JDBC URL")).toProvider(CopyDatabase.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("DatabasePath")).toInstance(
                System.getProperty(
                "db",
                System.getProperty("user.home")
                + "/fspotcloud/peer/src/test/resources/photos.db"));
        bind(String.class).annotatedWith(Names.named("WorkDir")).toInstance(
                System.getProperty("user.dir"));
        bind(Integer.class).annotatedWith(Names.named("stop port")).toInstance(
                Integer.valueOf(System.getProperty("stop.port", "4444")));
    }
}

class MyUserModule extends AbstractModule {

    protected void configure() {
        bind(UserService.class).to(LenientUserService.class);
    }
}