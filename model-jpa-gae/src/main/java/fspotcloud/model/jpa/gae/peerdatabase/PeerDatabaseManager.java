package fspotcloud.model.jpa.gae.peerdatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.model.jpa.peerdatabase.PeerDatabaseManagerBase;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class PeerDatabaseManager extends PeerDatabaseManagerBase<PeerDatabase, PeerDatabaseEntity> implements PeerDatabases {

    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(PeerDatabaseManagerBase.class.getName());

    @Inject
    public PeerDatabaseManager(Provider<EntityManager> entityManagerProvider) {
        super(PeerDatabaseEntity.class, entityManagerProvider);
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
