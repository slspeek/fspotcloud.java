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
package com.googlecode.fspotcloud.server.control.callback;

import static com.google.common.collect.Lists.newArrayList;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseEntity;
import com.googlecode.fspotcloud.model.jpa.tag.TagEntity;
import com.googlecode.fspotcloud.server.admin.handler.*;
import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.GetAdminTagTreeAction;
import com.googlecode.fspotcloud.shared.main.TagNode;
import com.googlecode.fspotcloud.shared.main.TagTreeResult;
import com.googlecode.fspotcloud.shared.peer.GetPeerUpdateInstructionsAction;
import com.googlecode.fspotcloud.shared.peer.PhotoRemovedFromTag;
import com.googlecode.fspotcloud.shared.peer.PhotoUpdate;
import com.googlecode.fspotcloud.shared.peer.TagUpdateInstructionsResult;
import com.googlecode.fspotcloud.user.IAdminPermission;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.List;
import javax.inject.Inject;
import net.customware.gwt.dispatch.shared.Action;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * DOCUMENT ME!
 *
 * @author steven
*/
@RunWith(JukitoRunner.class)
public class TagUpdateInstructionsCallbackTest {
    public static final String TAG_ID = "1";
    public static final String PHOTO_UPDATE_ID = "55";
    public static final String PHOTO_DELETE_ID = "5";
    TagUpdateInstructionsCallback callback;

    @Before
    public void createCallback(TaskQueueDispatch dispatchAsync) {
        callback = new TagUpdateInstructionsCallback(TAG_ID, dispatchAsync);
    }

    @Test
    public void testNormalExecute(TaskQueueDispatch dispatchAsync,
        ArgumentCaptor<GetPeerUpdateInstructionsAction> actionCaptor,
        ArgumentCaptor<PeerUpdateInstructionsCallback> callbackCaptor,
        ArgumentCaptor<Action> updateCaptor) throws Exception {
        List<PhotoUpdate> toBoUpdated = newArrayList(new PhotoUpdate(
                    PHOTO_UPDATE_ID));
        List<PhotoRemovedFromTag> toBoRemovedFromTag = newArrayList(new PhotoRemovedFromTag(
                    PHOTO_DELETE_ID,
                    TAG_ID));
        TagUpdateInstructionsResult result = new TagUpdateInstructionsResult(toBoUpdated,
                toBoRemovedFromTag);
        System.out.println("Caal" + callback);
        callback.onSuccess(result);
        verify(dispatchAsync, times(2)).execute(updateCaptor.capture());

        List<Action> actions = updateCaptor.getAllValues();
        PhotoUpdateAction update = (PhotoUpdateAction) actions.get(1);
        RemovePhotosFromTagAction photoRemove = (RemovePhotosFromTagAction) actions.get(0);
        assertEquals(PHOTO_DELETE_ID,
            photoRemove.getToBoDeleted().get(0).getPhotoId());
        assertEquals(PHOTO_UPDATE_ID, update.getUpdates().get(0).getPhotoId());
    }
}
