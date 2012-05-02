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
package com.googlecode.fspotcloud.server.admin.handler;

import com.googlecode.botdispatch.model.api.Commands;

import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseEntity;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetMetaDataAction;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetMetaDataResult;
import com.googlecode.fspotcloud.user.IAdminPermission;

import net.customware.gwt.dispatch.shared.DispatchException;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;


public class GetMetaDataHandlerTest {
    GetMetaDataHandler handler;
    GetMetaDataAction action = new GetMetaDataAction();
    @Mock
    Commands commandManager;
    @Mock
    PeerDatabases defaultPeer;
    @Mock
    IAdminPermission adminPermission;
    PeerDatabase pd;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        pd = new PeerDatabaseEntity();
        handler = new GetMetaDataHandler(
                commandManager, defaultPeer, adminPermission);
    }


    @Test
    public void testExecute() throws DispatchException {
        when(commandManager.getCountUnderAThousend()).thenReturn(100);
        when(defaultPeer.get()).thenReturn(pd);

        GetMetaDataResult result = handler.execute(action, null);
        assertNull(result.getInstanceName());
        assertEquals(0, result.getPeerPhotoCount());
        assertEquals(100, result.getPendingCommandCount());
    }


    @Test
    public void testException() {
        when(commandManager.getCountUnderAThousend())
            .thenThrow(RuntimeException.class);
        when(defaultPeer.get()).thenReturn(pd);

        try {
            GetMetaDataResult result = handler.execute(action, null);
            fail();
        } catch (DispatchException e) {
        }
    }


    @Test(expected = SecurityException.class)
    public void forbidden() throws DispatchException {
        doThrow(new SecurityException()).when(adminPermission)
            .checkAdminPermission();

        GetMetaDataResult result = handler.execute(action, null);
    }
}
