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

import com.googlecode.botdispatch.model.api.Commands;

import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetMetaDataAction;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetMetaDataResult;
import com.googlecode.fspotcloud.user.IAdminPermission;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.logging.Logger;


public class GetMetaDataHandler extends SimpleActionHandler<GetMetaDataAction, GetMetaDataResult> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(
            GetMetaDataHandler.class.getName());
    private final Commands commandManager;
    private final PeerDatabases defaultPeer;
    private final IAdminPermission IAdminPermission;

    @Inject
    public GetMetaDataHandler(
        Commands commandManager, PeerDatabases defaultPeer,
        IAdminPermission IAdminPermission) {
        super();
        this.commandManager = commandManager;
        this.defaultPeer = defaultPeer;
        this.IAdminPermission = IAdminPermission;
    }

    @Override
    public GetMetaDataResult execute(
        GetMetaDataAction action, ExecutionContext context)
        throws DispatchException {
        IAdminPermission.checkAdminPermission();

        GetMetaDataResult dataInfo = new GetMetaDataResult();

        try {
            PeerDatabase peerDatabase = defaultPeer.get();
            dataInfo.setInstanceName(peerDatabase.getPeerName());
            dataInfo.setPeerLastSeen(peerDatabase.getPeerLastContact());
            dataInfo.setPeerPhotoCount(peerDatabase.getPeerPhotoCount());
            dataInfo.setPhotoCount(peerDatabase.getPhotoCount());
            dataInfo.setTagCount(peerDatabase.getTagCount());
            dataInfo.setPendingCommandCount(getPendingCommandCount());
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return dataInfo;
    }


    private int getPendingCommandCount() {
        int result = commandManager.getCountUnderAThousend();

        return result;
    }
}
