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

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeletePhotos;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;


public class DeletePhotosHandler extends SimpleActionHandler<DeletePhotos, VoidResult> {
    private final int MAX_DELETE_TICKS;
    private final TaskQueueDispatch dispatchAsync;
    private final Photos photos;
    private Tags tagManager;

    @Inject
    public DeletePhotosHandler(
        @Named("maxDelete")
    int maxDeleteTicks, TaskQueueDispatch dispatchAsync, Photos photos,
        Tags tagManager) {
        super();
        MAX_DELETE_TICKS = maxDeleteTicks;
        this.dispatchAsync = dispatchAsync;
        this.photos = photos;
        this.tagManager = tagManager;
    }

    @Override
    public VoidResult execute(DeletePhotos action, ExecutionContext context)
        throws DispatchException {
        Tag tag = tagManager.find(action.getTagId());
        Iterator<PhotoInfo> it = action.getToBoDeleted().iterator();

        for (int i = 0; (i < MAX_DELETE_TICKS) && it.hasNext(); i++) {
            String key = it.next().getId();
            checkForDeletion(tag, tag.getId(), key, it);
        }

        tagManager.save(tag);

        if (!action.getToBoDeleted().isEmpty()) {
            dispatchAsync.execute(action);
        }

        return new VoidResult();
    }


    private void checkForDeletion(
        Tag tag, String deleteTagId, String key, Iterator<PhotoInfo> it) {
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

                final TreeSet<PhotoInfo> cachedPhotoList = tag
                    .getCachedPhotoList();
                cachedPhotoList.remove(find(tag.getCachedPhotoList(), key));
                tag.setCachedPhotoList(cachedPhotoList);
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
