/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.model.jpa.peerdatabase;

import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabaseDao;
import com.googlecode.fspotcloud.shared.main.TagNode;
import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;
import com.googlecode.simplejpadao.cacheddao.CachedSimpleDAONamedIdImpl;
import net.sf.jsr107cache.Cache;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public abstract class CachedPeerDatabaseManagerBase<T extends PeerDatabase, U extends T>
    extends CachedSimpleDAONamedIdImpl<PeerDatabase, U, String>
    implements PeerDatabaseDao {
    private static final String DEFAULT_PEER_ID = "1";
    private static final Logger log = Logger.getLogger(CachedPeerDatabaseManagerBase.class.getName());
    private final Provider<EntityManager> entityManagerProvider;

    @Inject
    public CachedPeerDatabaseManagerBase(Class<U> entityType, Cache cache,
        Provider<EntityManager> entityManagerProvider) {
        super(entityType, cache,
            new SimpleDAONamedIdImpl<PeerDatabase, U, String>(entityType,
                entityManagerProvider));
        this.entityManagerProvider = entityManagerProvider;
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

    @Override
    public void touchPeerContact() {
        T dp = get();
        dp.setPeerLastContact(new Date());
        save(dp);
    }

    @Override
    public void resetCachedTagTree() {
        T dp = get();
        List<TagNode> tree = dp.getCachedTagTree();
        dp.setCachedTagTree(null);
        save(dp);
        log.info("RESET Tree was:" + tree);
    }

    protected abstract T newInstance();

    protected abstract Class<?extends PeerDatabase> getEntityClass();
}
