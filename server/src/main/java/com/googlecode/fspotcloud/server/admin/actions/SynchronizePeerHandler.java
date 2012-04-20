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

import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.server.control.callback.PeerMetaDataCallback;
import com.googlecode.fspotcloud.shared.dashboard.actions.SynchronizePeer;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import com.googlecode.fspotcloud.user.AdminPermission;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;


public class SynchronizePeerHandler extends SimpleActionHandler<SynchronizePeer, VoidResult> {
    private final ControllerDispatchAsync dispatch;
    private final AdminPermission adminPermission;

    @Inject
    public SynchronizePeerHandler(
        ControllerDispatchAsync dispatch, AdminPermission adminPermission) {
        super();
        this.dispatch = dispatch;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(SynchronizePeer action, ExecutionContext context)
        throws DispatchException {
        adminPermission.chechAdminPermission();

        try {
            GetPeerMetaData metaAction = new GetPeerMetaData();
            SerializableAsyncCallback<PeerMetaDataResult> callback = new PeerMetaDataCallback(
                    null, null);
            dispatch.execute(metaAction, callback);
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return new VoidResult();
    }
}
