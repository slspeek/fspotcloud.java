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
package com.googlecode.fspotcloud.server.control.task.photoupdate;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.server.control.callback.PhotoDataCallback;
import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoUpdate;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;


public class PhotoUpdateHandler extends SimpleActionHandler<PhotoUpdateAction, VoidResult> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(
            PhotoUpdateHandler.class.getName());
    private final int MAX_DATA_TICKS;
    private final int MAX_PHOTO_TICKS;
    private final ControllerDispatchAsync controllerDispatch;
    private final TaskQueueDispatch dispatchAsync;
    private final ImageSpecs imageSpecs;

    @Inject
    public PhotoUpdateHandler(
        @Named("maxTicks")
    int maxTicks, @Named("maxPhotoTicks")
    int maxPhotoTicks, @Named("defaultImageSpecs")
    ImageSpecs imageSpecs, ControllerDispatchAsync controllerDispatch,
        TaskQueueDispatch dispatchAsync) {
        super();
        this.controllerDispatch = controllerDispatch;
        this.dispatchAsync = dispatchAsync;
        MAX_DATA_TICKS = maxTicks;
        MAX_PHOTO_TICKS = maxPhotoTicks;
        this.imageSpecs = imageSpecs;
    }

    @Override
    public VoidResult execute(
        PhotoUpdateAction action, ExecutionContext context)
        throws DispatchException {
        List<PhotoUpdate> updates = action.getUpdates();
        int size = updates.size();
        int countWeWillDo;
        int needToScheduleCount = (int)Math.ceil(
                (double)size / (double)MAX_PHOTO_TICKS);

        if (needToScheduleCount > MAX_DATA_TICKS) {
            countWeWillDo = MAX_DATA_TICKS;

            List<PhotoUpdate> nextList = new ArrayList<PhotoUpdate>();
            nextList.addAll(
                updates.subList(countWeWillDo * MAX_PHOTO_TICKS, size));
            dispatchAsync.execute(new PhotoUpdateAction(nextList));
        } else {
            countWeWillDo = needToScheduleCount;
        }

        // Do our part of the job, scheduling the head
        for (int i = 0; i < countWeWillDo; i++) {
            int beginning = i * MAX_PHOTO_TICKS;
            List<String> imageKeys = new ArrayList<String>();

            for (
                int j = beginning;
                    (j < (MAX_PHOTO_TICKS + beginning)) && (j < updates.size());
                    j++) {
                PhotoUpdate photoUpdate = updates.get(j);
                imageKeys.add(photoUpdate.getPhotoId());
            }

            // log.info("Doing our part " + imageKeys);
            GetPhotoData botAction = new GetPhotoData(imageSpecs, imageKeys);
            PhotoDataCallback callback = new PhotoDataCallback(
                    null, null, null);
            controllerDispatch.execute(botAction, callback);
        }

        return new VoidResult();
    }
}
