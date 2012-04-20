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
package com.googlecode.fspotcloud.server.admin.actions;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeletePhotos;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.fspotcloud.user.AdminPermission;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UnImportTagHandler extends SimpleActionHandler<UnImportTag, VoidResult> {
    private static final Logger log = Logger.getLogger(
            UnImportTagHandler.class.getName());
    private final Tags tagManager;
    private final TaskQueueDispatch dispatchAsync;
    private final PeerDatabases peerDatabases;
    private final AdminPermission adminPermission;

    @Inject
    public UnImportTagHandler(
        Tags tagManager, TaskQueueDispatch dispatchAsync,
        PeerDatabases peerDatabases, AdminPermission adminPermission) {
        super();
        this.tagManager = tagManager;
        this.dispatchAsync = dispatchAsync;
        this.peerDatabases = peerDatabases;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(UnImportTag action, ExecutionContext context)
        throws DispatchException {
        log.info("Executing: " + action.getTagId());

        //FIXME this gets DeleteAllPhotos to work for NOW
        //adminPermission.chechAdminPermission();
        try {
            String tagId = action.getTagId();
            Tag tag = tagManager.find(tagId);

            if (tag.isImportIssued()) {
                tag.setImportIssued(false);
                tagManager.save(tag);
            }

            List<PhotoInfo> infoList = new ArrayList<PhotoInfo>();
            infoList.addAll(tag.getCachedPhotoList());
            dispatchAsync.execute(new DeletePhotos(tag.getId(), infoList));
            clearTreeCache();
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return new VoidResult();
    }


    private void clearTreeCache() {
        PeerDatabase peer = peerDatabases.get();

        if (peer.getCachedTagTree() != null) {
            peer.setCachedTagTree(null);
            peerDatabases.save(peer);
        }
    }
}
