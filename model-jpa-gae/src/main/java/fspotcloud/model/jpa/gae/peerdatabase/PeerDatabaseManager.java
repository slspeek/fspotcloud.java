package fspotcloud.model.jpa.gae.peerdatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.model.jpa.peerdatabase.PeerDatabaseManagerBase;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class PeerDatabaseManager extends PeerDatabaseManagerBase {

    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(PeerDatabaseManagerBase.class.getName());

    @Inject
    public PeerDatabaseManager(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    protected PeerDatabase newPhoto() {
        return new PeerDatabaseEntity();
    }

    @Override
    protected Class<? extends PeerDatabase> getEntityClass() {
        return PeerDatabaseEntity.class;
    }
}
