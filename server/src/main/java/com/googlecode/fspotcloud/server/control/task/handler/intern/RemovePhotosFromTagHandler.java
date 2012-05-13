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
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.inject.Named;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

public class RemovePhotosFromTagHandler extends SimpleActionHandler<RemovePhotosFromTagAction, VoidResult> {

    private final int MAX_DELETE_TICKS;
    private final TaskQueueDispatch dispatchAsync;
    private final Photos photos;
    private final Tags tagManager;
    private final PeerDatabases peerDatabaseManager;

    @Inject
    public RemovePhotosFromTagHandler(@Named("maxDelete") int maxDeleteTicks, TaskQueueDispatch dispatchAsync, Photos photos,
            Tags tagManager, PeerDatabases peerDatabaseManager) {
        super();
        MAX_DELETE_TICKS = maxDeleteTicks;
        this.dispatchAsync = dispatchAsync;
        this.photos = photos;
        this.tagManager = tagManager;
        this.peerDatabaseManager = peerDatabaseManager;
    }

    @Override
    public VoidResult execute(RemovePhotosFromTagAction action,
            ExecutionContext context) throws DispatchException {
        Tag tag = tagManager.find(action.getTagId());
        Iterator<String> it = action.getToBeDeleted().iterator();

        for (int i = 0; (i < MAX_DELETE_TICKS) && it.hasNext(); i++) {
            String photoId = it.next();

            checkForDeletion(tag, tag.getId(), photoId, it);
        }

        tagManager.save(tag);

        if (!action.getToBeDeleted().isEmpty()) {
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

    private void checkForDeletion(Tag tag, String deleteTagId, String key,
            Iterator<?> it) {
        Photo photo = photos.find(key);

        if (photo != null) {
            boolean moreImports = false;

            for (String tagId : photo.getTagList()) {
                if (!deleteTagId.equals(tagId)) {
                    Tag tagRelated = tagManager.find(tagId);
                    if (tagRelated != null) {
                        if (tagRelated.isImportIssued()) {
                            moreImports = true;
                            break;
                        }
                    }
                }
            }
            if (!moreImports) {
                photos.delete(photo);
                final TreeSet<PhotoInfo> cachedPhotoList = tag.getCachedPhotoList();
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
