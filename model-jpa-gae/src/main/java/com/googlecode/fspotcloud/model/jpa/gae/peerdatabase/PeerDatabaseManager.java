package com.googlecode.fspotcloud.model.jpa.gae.peerdatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.CachedPeerDatabaseManagerBase;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseManagerBase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import net.sf.jsr107cache.Cache;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

public class PeerDatabaseManager extends CachedPeerDatabaseManagerBase<PeerDatabase, PeerDatabaseEntity> implements PeerDatabases {

    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(PeerDatabaseManagerBase.class.getName());

    @Inject
    public PeerDatabaseManager(Provider<EntityManager> entityManagerProvider, Cache cache) {
        super(PeerDatabaseEntity.class, cache, entityManagerProvider);
    }

    @Override
    protected PeerDatabase newInstance() {
        return new PeerDatabaseEntity();
    }

    @Override
    protected Class<? extends PeerDatabase> getEntityClass() {
        return PeerDatabaseEntity.class;
    }
}
