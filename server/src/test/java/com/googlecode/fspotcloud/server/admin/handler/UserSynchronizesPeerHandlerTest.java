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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.admin.handler;

import static com.google.common.collect.Lists.newArrayList;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.model.jpa.tag.TagEntity;
import com.googlecode.fspotcloud.server.control.callback.PeerMetaDataCallback;
import com.googlecode.fspotcloud.server.control.task.actions.intern.ImportManyTagsPhotosAction;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.UserSynchronizesPeerAction;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaDataAction;
import com.googlecode.fspotcloud.user.IAdminPermission;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import org.jukito.JukitoRunner;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.inject.Inject;


@RunWith(JukitoRunner.class)
public class UserSynchronizesPeerHandlerTest {
    @Inject
    private UserSynchronizesPeerHandler handler;
    private final UserSynchronizesPeerAction action = new UserSynchronizesPeerAction();
    private final String TAG_ID = "1";

    @Test
    public void testNormalExecute(
        ControllerDispatchAsync dispatch, IAdminPermission adminPermission,
        TaskQueueDispatch taskQueueDispatch, Tags tagManager,
        ArgumentCaptor<GetPeerMetaDataAction> actionCaptor,
        ArgumentCaptor<PeerMetaDataCallback> callbackCaptor,
        ArgumentCaptor<ImportManyTagsPhotosAction> taskActionCaptor)
        throws Exception {
        List<Tag> importedTags = newArrayList();
        TagEntity tag = new TagEntity();
        tag.setId(TAG_ID);
        importedTags.add(tag);
        when(tagManager.getImportedTags()).thenReturn(importedTags);

        VoidResult result = handler.execute(action, null);

        verify(dispatch)
            .execute(actionCaptor.capture(), callbackCaptor.capture());

        verify(taskQueueDispatch).execute(taskActionCaptor.capture());

        ImportManyTagsPhotosAction importAction = taskActionCaptor.getValue();
        assertEquals(TAG_ID, importAction.getTagIdList().get(0));
    }


    @Test(expected = SecurityException.class)
    public void testUnAuthorizedExecute(
        ControllerDispatchAsync dispatch, IAdminPermission adminPermission,
        TaskQueueDispatch taskQueueDispatch, Tags tagManager)
        throws Exception {
        doThrow(new SecurityException()).when(adminPermission)
            .checkAdminPermission();

        VoidResult result = handler.execute(action, null);
    }
}