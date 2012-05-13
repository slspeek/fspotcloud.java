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
package com.googlecode.fspotcloud.server.admin.handler;

import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.UserUnImportsTagAction;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.fspotcloud.user.IAdminPermission;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;


public class UserUnImportsTagHandler extends SimpleActionHandler<UserUnImportsTagAction, VoidResult> {
    private static final Logger log = Logger.getLogger(UserUnImportsTagHandler.class.getName());
    private final Tags tagManager;
    private final TaskQueueDispatch dispatchAsync;
    private final PeerDatabases peerDatabases;
    private final IAdminPermission adminPermission;

    @Inject
    public UserUnImportsTagHandler(Tags tagManager,
        TaskQueueDispatch dispatchAsync, PeerDatabases peerDatabases,
        IAdminPermission adminPermission) {
        super();
        this.tagManager = tagManager;
        this.dispatchAsync = dispatchAsync;
        this.peerDatabases = peerDatabases;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(UserUnImportsTagAction action,
        ExecutionContext context) throws DispatchException {
        log.info("Executing: " + action.getTagId());
        adminPermission.checkAdminPermission();
        try {
            String tagId = action.getTagId();
            Tag tag = tagManager.find(tagId);

            if (tag.isImportIssued()) {
                tag.setImportIssued(false);
                tagManager.save(tag);
            }

            List<String> idList = new ArrayList<String>();
            for (PhotoInfo info: tag.getCachedPhotoList()) {
                idList.add(info.getId());
            }
            dispatchAsync.execute(new RemovePhotosFromTagAction(tag.getId(), idList));
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
