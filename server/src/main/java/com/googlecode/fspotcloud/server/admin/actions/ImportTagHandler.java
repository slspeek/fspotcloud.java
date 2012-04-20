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

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.server.control.callback.TagUpdateInstructionsCallback;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.ImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;
import com.googlecode.fspotcloud.user.AdminPermission;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.logging.Logger;


public class ImportTagHandler extends SimpleActionHandler<ImportTag, VoidResult> {
    protected static final Logger log = Logger.getLogger(
            ImportTagHandler.class.getName());
    private final Tags tagManager;
    private final ControllerDispatchAsync dispatchAsync;
    private final AdminPermission adminPermission;

    @Inject
    public ImportTagHandler(
        Tags tagManager, ControllerDispatchAsync dispatchAsync,
        AdminPermission adminPermission) {
        this.tagManager = tagManager;
        this.dispatchAsync = dispatchAsync;
        this.adminPermission = adminPermission;
    }

    @Override
    public VoidResult execute(ImportTag action, ExecutionContext context)
        throws DispatchException {
        adminPermission.chechAdminPermission();

        try {
            String tagId = action.getTagId();
            Tag tag = tagManager.find(tagId);

            if (!tag.isImportIssued()) {
                tag.setImportIssued(true);
                tagManager.save(tag);
            }

            GetTagUpdateInstructionsAction peerAction = new GetTagUpdateInstructionsAction(
                    tagId, tag.getCachedPhotoList());
            TagUpdateInstructionsCallback callback = new TagUpdateInstructionsCallback(
                    tagId, null);
            log.info("before execute for tag: " + action.getTagId());
            dispatchAsync.execute(peerAction, callback);
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return new VoidResult();
    }
}
