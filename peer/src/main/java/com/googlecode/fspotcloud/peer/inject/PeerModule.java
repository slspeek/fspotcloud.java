package com.googlecode.fspotcloud.peer.inject;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import com.googlecode.fspotcloud.peer.CopyDatabase;
import com.googlecode.fspotcloud.peer.ImageData;
import com.googlecode.fspotcloud.peer.db.Data;

public class PeerModule extends AbstractModule {

    private final String db;
    private final String workDir;
    private final int stopPort;

    public PeerModule(String db, String workDir, int stopPort) {
        this.db = db;
        this.workDir = workDir;
        this.stopPort = stopPort;
    }

    @Override
    protected void configure() {
        bind(Data.class);
        bind(ImageData.class);
        bind(String.class).annotatedWith(Names.named("JDBC URL"))
                .toProvider(CopyDatabase.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("DatabasePath"))
                .toInstance(db);
        bind(String.class).annotatedWith(Names.named("WorkDir")).toInstance(
                workDir);
        bind(Integer.class).annotatedWith(Names.named("stop port")).toInstance(
                Integer.valueOf(stopPort));
    }
}
