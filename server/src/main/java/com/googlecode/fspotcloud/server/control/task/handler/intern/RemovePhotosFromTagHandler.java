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

import com.googlecode.fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import com.googlecode.fspotcloud.server.model.api.*;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoRemovedFromTag;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.Iterator;
import java.util.SortedSet;

import javax.inject.Inject;
import javax.inject.Named;


public class RemovePhotosFromTagHandler extends SimpleActionHandler<RemovePhotosFromTagAction, VoidResult> {
    private final int MAX_DELETE_TICKS;
    private final TaskQueueDispatch dispatchAsync;
    private final Photos photos;
    private final Tags tagManager;
    private final PeerDatabases peerDatabaseManager;

    @Inject
    public RemovePhotosFromTagHandler(
        @Named("maxDelete")
    int maxDeleteTicks, TaskQueueDispatch dispatchAsync, Photos photos,
        Tags tagManager, PeerDatabases peerDatabaseManager) {
        super();
        MAX_DELETE_TICKS = maxDeleteTicks;
        this.dispatchAsync = dispatchAsync;
        this.photos = photos;
        this.tagManager = tagManager;
        this.peerDatabaseManager = peerDatabaseManager;
    }

    @Override
    public VoidResult execute(
        RemovePhotosFromTagAction action, ExecutionContext context)
        throws DispatchException {
        Tag tag = tagManager.find(action.getTagId());
        Iterator<PhotoRemovedFromTag> it = action.getToBoDeleted().iterator();

        for (int i = 0; (i < MAX_DELETE_TICKS) && it.hasNext(); i++) {
            PhotoRemovedFromTag operation = it.next();

            checkForDeletion(tag, tag.getId(), operation.getPhotoId(), it);
        }

        tagManager.save(tag);

        if (!action.getToBoDeleted().isEmpty()) {
            dispatchAsync.execute(action);
        }

        clearTreeCache();

        return new VoidResult();
    }


    private void clearTreeCache() {
        PeerDatabase peer = peerDatabaseManager.get();

        if (peer.getCachedTagTree() != null) {
            peer.setCachedTagTree(null);
            peerDatabaseManager.save(peer);
        }
    }


    private void checkForDeletion(
        Tag tag, String deleteTagId, String key,
        Iterator<PhotoRemovedFromTag> it) {
        Photo photo = photos.find(key);

        if (photo != null) {
            boolean moreImports = false;

            for (String tagId : photo.getTagList()) {
                Tag tagRelated = tagManager.find(tagId);

                if (tagRelated != null) {
                    if (!deleteTagId.equals(tagId)) {
                        if (tagRelated.isImportIssued()) {
                            moreImports = true;

                            break;
                        }
                    }
                }
            }

            if (!moreImports) {
                photos.delete(photo);
                tag.getCachedPhotoList()
                   .remove(find(tag.getCachedPhotoList(), key));
            }
        }

        it.remove();
    }


    private PhotoInfo find(SortedSet<PhotoInfo> set, String id) {
        for (PhotoInfo info : set) {
            if (info.getId().equals(id)) {
                return info;
            }
        }

        return null;
    }
}