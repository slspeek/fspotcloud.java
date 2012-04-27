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
import com.googlecode.fspotcloud.user.AdminPermission;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class GetMetaDataHandlerTest {
    GetMetaDataHandler handler;
    GetMetaDataAction action = new GetMetaDataAction();
    @Mock
    Commands commandManager;
    @Mock
    PeerDatabases defaultPeer;
    @Mock
    AdminPermission adminPermission;
    PeerDatabase pd;

    @BeforeMethod
    protected void setUp() throws Exception {
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
        AssertJUnit.assertEquals(0, result.getPeerPhotoCount());
        AssertJUnit.assertEquals(100, result.getPendingCommandCount());
    }


    @Test
    public void testException() {
        when(commandManager.getCountUnderAThousend())
            .thenThrow(RuntimeException.class);
        when(defaultPeer.get()).thenReturn(pd);

        try {
            GetMetaDataResult result = handler.execute(action, null);
            Assert.fail();
        } catch (DispatchException e) {
        }
    }


    @Test(expectedExceptions = SecurityException.class)
    void forbidden() throws DispatchException {
        doThrow(new SecurityException()).when(adminPermission)
            .chechAdminPermission();

        GetMetaDataResult result = handler.execute(action, null);
    }
}
