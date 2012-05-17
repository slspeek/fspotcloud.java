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
import com.googlecode.fspotcloud.model.jpa.tag.TagEntity;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.UserImportsTagAction;
import com.googlecode.fspotcloud.shared.peer.TagData;
import com.googlecode.fspotcloud.shared.peer.TagDataResult;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
public class TagDataCallbackTest {
    Dispatch dispatch;
    Tags tagManager;
    Tag tag;
    PeerDatabase peer = new PeerDatabaseEntity();
    PeerDatabases peers;
    TagDataCallback callback;
    final String TAGNAME = "Foo";
    final String TAGID = "FooID";
    TagDataResult incoming;
    TagData row;
    ArgumentCaptor<List<Tag>> argumentCaptor = (ArgumentCaptor<List<Tag>>) (Object) ArgumentCaptor.forClass(List.class);

    @Before
    public void setUp() throws Exception {
        dispatch = mock(Dispatch.class);
        tagManager = mock(Tags.class);
        peers = mock(PeerDatabases.class);
        when(peers.get()).thenReturn(peer);
        tag = new TagEntity();
        tag.setId(TAGID);
        row = new TagData(TAGID, TAGNAME, null, 10);

        List<TagData> list = new ArrayList<TagData>();
        list.add(row);
        incoming = new TagDataResult(list);
        when(tagManager.findOrNew(TAGID)).thenReturn(tag);
        callback = new TagDataCallback(tagManager, dispatch, peers);
    }

    @Test
    public void testSerialize() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(callback);
        out.close();
    }

    @Test
    public void testRecieveTagData() throws DispatchException {
        callback.onSuccess(incoming);
        assertEquals(10, tag.getCount());
        assertEquals(TAGNAME, tag.getTagName());
        assertNull(tag.getParentId());
        verify(peers).get();
        verifyNoMoreInteractions(dispatch, peers);
    }

    @Test
    public void testRecieveTagDataImported() throws DispatchException {
        tag.setImportIssued(true);
        callback.onSuccess(incoming);
        assertEquals(10, tag.getCount());
        assertEquals(TAGNAME, tag.getTagName());
        assertNull(tag.getParentId());

        //        ArgumentCaptor<UserImportsTagAction> actionCaptor = ArgumentCaptor.forClass(UserImportsTagAction.class);
        //        verify(dispatch).execute(actionCaptor.capture());
        //
        //        UserImportsTagAction action = actionCaptor.getValue();
        //        assertEquals(TAGID, action.getTagId());
        verify(peers).get();
        verifyNoMoreInteractions(peers, dispatch);
    }
}
