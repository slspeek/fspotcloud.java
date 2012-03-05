package fspotcloud.peer.inject;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import fspotcloud.peer.CopyDatabase;
import fspotcloud.peer.ImageData;
import fspotcloud.peer.db.Data;

public class PeerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Data.class);
        bind(ImageData.class);
        bind(String.class).annotatedWith(Names.named("JDBC URL")).toProvider(CopyDatabase.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("DatabasePath")).toInstance(
                System.getProperty(
                "db",
                System.getProperty("user.dir")
                + "/src/test/resources/photos.db"));
        bind(String.class).annotatedWith(Names.named("WorkDir")).toInstance(
                System.getProperty("user.dir"));
        bind(Integer.class).annotatedWith(Names.named("stop port")).toInstance(
                Integer.valueOf(System.getProperty("stop.port", "4444")));
    }
}
