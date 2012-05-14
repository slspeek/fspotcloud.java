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
package com.googlecode.fspotcloud.server.control.task.handler.intern;

import static com.google.common.collect.Lists.*;
import com.googlecode.fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import com.googlecode.fspotcloud.server.control.task.actions.intern.RemoveTagsDeletedFromPeerAction;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.fspotcloud.shared.peer.TagRemovedFromPeer;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class RemoveTagsFromPeerHandler extends SimpleActionHandler<RemoveTagsDeletedFromPeerAction, VoidResult> {
    private final int MAX_DELETE_TICKS;
    private final TaskQueueDispatch dispatchAsync;
    private Tags tagManager;

    @Inject
    public RemoveTagsFromPeerHandler(@Named("maxDelete")
    int maxDeleteTicks, TaskQueueDispatch dispatchAsync, Tags tagManager) {
        super();
        MAX_DELETE_TICKS = maxDeleteTicks;
        this.dispatchAsync = dispatchAsync;
        this.tagManager = tagManager;
    }

    @Override
    public VoidResult execute(RemoveTagsDeletedFromPeerAction action,
        ExecutionContext context) throws DispatchException {
        Iterator<TagRemovedFromPeer> it = action.getToBoDeleted().iterator();

        for (int i = 0; (i < MAX_DELETE_TICKS) && it.hasNext(); i++) {
            TagRemovedFromPeer operation = it.next();
            String tagId = operation.getTagId();
            handleOneTag(tagId);
            it.remove();
        }

        if (!action.getToBoDeleted().isEmpty()) {
            dispatchAsync.execute(action);
        }

        return new VoidResult();
    }

    private void handleOneTag(String tagId) {
        Tag tag = tagManager.find(tagId);

        List<String> idList = newArrayList();

        for (PhotoInfo info : tag.getCachedPhotoList()) {
            idList.add(info.getId());
        }

        dispatchAsync.execute(new RemovePhotosFromTagAction(tagId, idList));
        tagManager.deleteByKey(tagId);
    }
}
