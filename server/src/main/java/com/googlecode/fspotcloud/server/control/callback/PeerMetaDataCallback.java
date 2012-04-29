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

import static com.google.common.collect.Lists.newArrayList;

import com.google.inject.Inject;

import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerUpdateInstructionsAction;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.TagData;

import java.util.List;
import java.util.logging.Logger;


public class PeerMetaDataCallback implements SerializableAsyncCallback<PeerMetaDataResult> {
    private static final Logger log = Logger.getLogger(
            PeerMetaDataCallback.class.getName());
    private static final long serialVersionUID = 1851403859917750767L;
    @Inject
    private transient PeerDatabases defaultPeer;
    @Inject
    private transient Tags tagManager;
    @Inject
    private transient ControllerDispatchAsync dispatchAsync;

    public PeerMetaDataCallback(
        PeerDatabases defaultPeer, ControllerDispatchAsync dispatchAsync) {
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
            new GetPeerUpdateInstructionsAction(getTagData()),
            new PeerUpdateInstructionsCallback(null));
        p.setPeerPhotoCount(count);
        p.setTagCount(tagCount);
        defaultPeer.save(p);
    }


    @Override
    public void onFailure(Throwable caught) {
    }


    private List<TagData> getTagData() {
        List<TagData> result = newArrayList();

        for (Tag tag : tagManager.findAll(1000)) {
            TagData data = new TagData(
                    tag.getId(), tag.getTagName(), tag.getParentId(),
                    tag.getCount());
            result.add(data);
        }

        return result;
    }
}
