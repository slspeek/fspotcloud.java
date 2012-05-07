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

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import java.util.logging.Logger;
import javax.persistence.EntityManager;


public class PeerDatabaseManager extends PeerDatabaseManagerBase<PeerDatabase, PeerDatabaseEntity> {
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
    protected Class<?extends PeerDatabase> getEntityClass() {
        return PeerDatabaseEntity.class;
    }
}
