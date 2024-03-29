/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it
                and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free
                Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is
                distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied
                warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public
                License for more details.
 * You should have received a copy of the GNU General Public License
 * along
                with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330,
                Boston, MA 02111-1307, USA.
 *
 */

package com.googlecode.fspotcloud.server.control.callback;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import com.googlecode.fspotcloud.shared.peer.PhotoRemovedFromTag;
import com.googlecode.fspotcloud.shared.peer.TagUpdateInstructionsResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.newArrayList;

public class TagUpdateInstructionsCallback implements SerializableAsyncCallback<TagUpdateInstructionsResult> {
    private static final long serialVersionUID = -6213572441944313878L;
    @Inject
    @VisibleForTesting
    Logger log;
    @Inject
    private transient TaskQueueDispatch dispatchAsync;
    private String tagId;

    public TagUpdateInstructionsCallback(String tagId,
                                         TaskQueueDispatch dispatchAsync) {
        super();
        this.tagId = tagId;
        this.dispatchAsync = dispatchAsync;
    }

    @Override
    public void onFailure(Throwable caught) {
        log.log(Level.SEVERE, "Caught: ", caught);
    }

    @Override
    public void onSuccess(TagUpdateInstructionsResult result) {
        PhotoUpdateAction photoUpdate = new PhotoUpdateAction(result.getToBoUpdated());
        List<String> photoIds = newArrayList();

        for (PhotoRemovedFromTag removal : result.getToBoRemovedFromTag()) {
            photoIds.add(removal.getPhotoId());
        }

        RemovePhotosFromTagAction photoRemove = new RemovePhotosFromTagAction(tagId,
                photoIds);
        log.info("PhotoRemove to be executed: " + photoRemove);
        dispatchAsync.execute(photoRemove);
        log.info("PhotoUpdate to be executed: " + photoUpdate);
        dispatchAsync.execute(photoUpdate);
    }
}
