package fspotcloud.model.jpa.peerdatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.apache.commons.lang.SerializationUtils;

public class PeerDatabaseManager implements PeerDatabases {

    private static final String DEFAULT_PEER = "DEFAULT_PEER";
    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(PeerDatabaseManager.class.getName());
    final private Provider<EntityManager> entityManagerProvider;

    @Inject
    public PeerDatabaseManager(Provider<EntityManager> entityManagerProvider) {
        this.entityManagerProvider = entityManagerProvider;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PeerDatabase get() {
        PeerDatabase peer;
        peer = getJDOImpl();
        return peer;
    }

    private PeerDatabase getJDOImpl() {
        EntityManager pm = entityManagerProvider.get();
        PeerDatabaseEntity peerDatabase;
        peerDatabase = pm.find(PeerDatabaseEntity.class, DEFAULT_PEER_ID);
        if (peerDatabase == null) {
            log.info("Default peer not found, creating one.");
            peerDatabase = new PeerDatabaseEntity();
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
        byte[] serializedPeer = SerializationUtils.serialize((Serializable) pd);
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
}
