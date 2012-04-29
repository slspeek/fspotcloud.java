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

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllPhotos;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllTags;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.dashboard.actions.UserDeletesAllAction;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.user.AdminPermission;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import javax.inject.Inject;


public class UserDeletesAllHandler extends SimpleActionHandler<UserDeletesAllAction, VoidResult> {
    private final TaskQueueDispatch dispatchAsync;
    private final AdminPermission adminPermission;
    private final PeerDatabases peerDatabaseManager;

    @Inject
    public UserDeletesAllHandler(
        TaskQueueDispatch dispatchAsync, AdminPermission adminPermission,
        PeerDatabases peerDatabaseManager) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.adminPermission = adminPermission;
        this.peerDatabaseManager = peerDatabaseManager;
    }

    @Override
    public VoidResult execute(
        UserDeletesAllAction action, ExecutionContext context)
        throws DispatchException {
        adminPermission.chechAdminPermission();

        try {
            dispatchAsync.execute(new DeleteAllTags());
            dispatchAsync.execute(new DeleteAllPhotos());
            clearPeer();
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return new VoidResult();
    }


    private void clearPeer() {
        peerDatabaseManager.deleteBulk(1);
    }
}
