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
package com.googlecode.fspotcloud.server.control.task.deleteallphotos;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllPhotos;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteTags;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.logging.Logger;

import javax.inject.Inject;


public class DeleteAllPhotosHandler extends SimpleActionHandler<DeleteAllPhotos, VoidResult> {
    private static final Logger log = Logger.getLogger(
            DeleteAllPhotosHandler.class.getName());
    private final TaskQueueDispatch dispatchAsync;
    private final Photos photoManager;

    @Inject
    public DeleteAllPhotosHandler(
        TaskQueueDispatch dispatchAsync, Photos photoManager) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.photoManager = photoManager;
    }

    @Override
    public VoidResult execute(DeleteAllPhotos action, ExecutionContext context)
        throws DispatchException {
        photoManager.deleteBulk(30);

        if (!photoManager.isEmpty()) {
            dispatchAsync.execute(new DeleteAllPhotos());
        }

        return new VoidResult();
    }
}
