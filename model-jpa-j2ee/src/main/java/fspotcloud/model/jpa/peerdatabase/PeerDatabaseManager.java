package fspotcloud.model.jpa.peerdatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.server.model.api.PeerDatabase;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class PeerDatabaseManager 
extends PeerDatabaseManagerBase<PeerDatabase, PeerDatabaseEntity> {

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
