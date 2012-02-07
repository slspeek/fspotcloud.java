package fspotcloud.model.jpa.peerdatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public abstract class PeerDatabaseManagerBase implements PeerDatabases {

    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(PeerDatabaseManagerBase.class.getName());
    final private Provider<EntityManager> entityManagerProvider;

    @Inject
    public PeerDatabaseManagerBase(Provider<EntityManager> entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @Override
    public PeerDatabase get() {
        PeerDatabase peer;
        peer = getInstance();
        return peer;
    }

    private PeerDatabase getInstance() {
        EntityManager pm = entityManagerProvider.get();
        PeerDatabase peerDatabase;
        peerDatabase = pm.find(getEntityClass(), DEFAULT_PEER_ID);
        if (peerDatabase == null) {
            log.info("Default peer not found, creating one.");
            peerDatabase = newPhoto();
            peerDatabase.setName(DEFAULT_PEER_ID);
            peerDatabase.setPeerPhotoCount(0);
            peerDatabase.setPhotoCount(0);
            peerDatabase.setTagCount(0);
            peerDatabase.setPeerName("No given name");
            peerDatabase.setPeerLastContact(new Date(0));
            pm.persist(peerDatabase);
        }
        return peerDatabase;
    }

    public void save(PeerDatabase pd) {
        EntityManager pm = entityManagerProvider.get();
        pm.getTransaction().begin();
        pm.persist(pd);
        pm.getTransaction().commit();
    }

    public void touchPeerContact() {
        PeerDatabase dp = get();
        dp.setPeerLastContact(new Date());
        save(dp);
    }

    protected abstract PeerDatabase newPhoto();

    protected abstract Class<? extends PeerDatabase> getEntityClass();
}
