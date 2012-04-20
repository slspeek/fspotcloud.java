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
package com.googlecode.fspotcloud.server.control.callback;

import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseEntity;
import com.googlecode.fspotcloud.server.control.task.actions.intern.TagImportAction;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

import com.googlecode.taskqueuedispatch.NullCallback;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;


@SuppressWarnings("unused")
public class PeerMetaDataCallbackTest {
    PeerDatabase pd;
    PeerDatabases peerDatabases;
    TaskQueueDispatch dispatchAsync;
    PeerMetaDataCallback callback;
    PeerMetaDataResult firstResult;
    PeerMetaDataResult secondResult;

    @BeforeMethod
    protected void setUp() throws Exception {
        peerDatabases = mock(PeerDatabases.class);
        pd = new PeerDatabaseEntity();
        dispatchAsync = mock(TaskQueueDispatch.class);
        callback = new PeerMetaDataCallback(peerDatabases, dispatchAsync);
        firstResult = new PeerMetaDataResult(10, 10);
        secondResult = new PeerMetaDataResult(10, 21);
    }


    @Test
    public void testSerialize() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(callback);
        out.close();
    }


    @Test
    public void testFirstResult() {
        when(peerDatabases.get()).thenReturn(pd);
        callback.onSuccess(firstResult);
        verify(peerDatabases).save(pd);
        verify(dispatchAsync)
            .execute(
            new TagImportAction(0, 10), new NullCallback<VoidResult>());
    }
}
