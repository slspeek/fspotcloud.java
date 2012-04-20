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
package com.googlecode.fspotcloud.server.control.task.tagdelete;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteTags;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.logging.Logger;

import javax.inject.Inject;


public class DeleteTagsHandler extends SimpleActionHandler<DeleteTags, VoidResult> {
    private static final Logger log = Logger.getLogger(
            DeleteTagsHandler.class.getName());
    private final TaskQueueDispatch dispatchAsync;
    private final Tags tagManager;

    @Inject
    public DeleteTagsHandler(TaskQueueDispatch dispatchAsync, Tags tagManager) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.tagManager = tagManager;
    }

    @Override
    public VoidResult execute(DeleteTags action, ExecutionContext context)
        throws DispatchException {
        log.info("Delete tags entered");
        tagManager.deleteBulk(30);

        if (!tagManager.isEmpty()) {
            dispatchAsync.execute(new DeleteTags());
        }

        return new VoidResult();
    }
}
