package fspotcloud.model.jpa.peerdatabase;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photo;
import fspotcloud.simplejpadao.SimpleDAONamedIdImpl;
import fspotcloud.simplejpadao.cacheddao.CachedSimpleDAONamedIdImpl;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import net.sf.jsr107cache.Cache;

public abstract class CachedPeerDatabaseManagerBase<T extends PeerDatabase, U extends T>
        extends CachedSimpleDAONamedIdImpl<PeerDatabase, U, String> implements PeerDatabases {

    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(CachedPeerDatabaseManagerBase.class.getName());
    final private Provider<EntityManager> entityManagerProvider;

    @Inject
    public CachedPeerDatabaseManagerBase(Class<U> entityType, Cache cache,Provider<EntityManager> entityManagerProvider) {
        super(entityType, cache,new SimpleDAONamedIdImpl<PeerDatabase, U, String>(entityType, entityManagerProvider));        this.entityManagerProvider = entityManagerProvider;
    }

    public T get() {
        T peer;
        peer = getInstance();
        return peer;
    }

    private T getInstance() {
        EntityManager pm = entityManagerProvider.get();
        pm.getTransaction().begin();
        T peerDatabase;
        peerDatabase = (T) pm.find(getEntityClass(), DEFAULT_PEER_ID);
        if (peerDatabase == null) {
            log.info("Default peer not found, creating one.");
            peerDatabase = newInstance();
            peerDatabase.setId(DEFAULT_PEER_ID);
            peerDatabase.setPeerPhotoCount(0);
            peerDatabase.setPhotoCount(0);
            peerDatabase.setTagCount(0);
            peerDatabase.setPeerName("No given name");
            peerDatabase.setPeerLastContact(new Date(0));
            pm.persist(peerDatabase);
        }
        pm.getTransaction().commit();
        return peerDatabase;
    }

    public void touchPeerContact() {
        T dp = get();
        dp.setPeerLastContact(new Date());
        save(dp);
    }

    protected abstract T newInstance();

    protected abstract Class<? extends PeerDatabase> getEntityClass();
}
