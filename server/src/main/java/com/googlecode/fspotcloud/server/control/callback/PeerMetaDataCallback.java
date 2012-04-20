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
package com.googlecode.fspotcloud.server.control.callback;

import com.google.inject.Inject;

import com.googlecode.botdispatch.SerializableAsyncCallback;

import com.googlecode.fspotcloud.server.control.task.actions.intern.TagImportAction;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

import com.googlecode.taskqueuedispatch.NullCallback;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import java.util.logging.Logger;


public class PeerMetaDataCallback implements SerializableAsyncCallback<PeerMetaDataResult> {
    private static final Logger log = Logger.getLogger(
            PeerMetaDataCallback.class.getName());
    private static final long serialVersionUID = 1851403859917750767L;
    @Inject
    private transient PeerDatabases defaultPeer;
    @Inject
    private transient TaskQueueDispatch dispatchAsync;

    public PeerMetaDataCallback(
        PeerDatabases defaultPeer, TaskQueueDispatch dispatchAsync) {
        super();
        this.defaultPeer = defaultPeer;
        this.dispatchAsync = dispatchAsync;
    }

    @Override
    public void onSuccess(PeerMetaDataResult result) {
        log.info("On success: " + result);

        int count = result.getPhotoCount();
        int tagCount = result.getTagCount();
        PeerDatabase p = defaultPeer.get();
        dispatchAsync.execute(
            new TagImportAction(0, tagCount), new NullCallback<VoidResult>());
        p.setPeerPhotoCount(count);
        p.setTagCount(tagCount);
        defaultPeer.save(p);
    }


    @Override
    public void onFailure(Throwable caught) {
    }
}
