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

import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.UserImportsTagAction;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.TagData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.TagDataResult;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class TagDataCallback implements SerializableAsyncCallback<TagDataResult> {
    protected static final Logger log = Logger.getLogger(
            TagDataCallback.class.getName());
    private static final long serialVersionUID = 5342287706825285919L;
    @Inject
    private transient Tags tagManager;
    @Inject
    private transient Dispatch dispatch;

    public TagDataCallback(Tags tagManager, Dispatch dispatch) {
        super();
        this.tagManager = tagManager;
        this.dispatch = dispatch;
    }

    @Override
    public void onFailure(Throwable caught) {
        log.log(Level.SEVERE, "TagDataCallbask", caught);
    }


    @Override
    public void onSuccess(TagDataResult result) {
        for (TagData data : result.getTagDataList()) {
            String keyName = data.getTagId();
            Tag tag = tagManager.findOrNew(keyName);
            int previousCount = tag.getCount();
            recieveTag(data, tag);
            tagManager.save(tag);
            importNewImages(tag, previousCount);
        }
    }


    private void importNewImages(Tag tag, int previousCount) {
        if (tag.isImportIssued()) {
            try {
                dispatch.execute(
                    new UserImportsTagAction(tag.getId()));
            } catch (DispatchException e) {
                log.log(Level.SEVERE, "Caught: ", e);
            }
        }
    }


    private void recieveTag(TagData data, Tag tag) {
        String tagName = data.getName();
        String parentId = data.getParentId();
        int count = data.getCount();
        tag.setTagName(tagName);
        tag.setParentId(parentId);
        tag.setCount(count);
    }
}
