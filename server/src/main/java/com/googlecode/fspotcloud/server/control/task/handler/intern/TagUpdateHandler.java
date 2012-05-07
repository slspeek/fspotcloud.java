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
package com.googlecode.fspotcloud.server.control.task.handler.intern;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.fspotcloud.server.control.callback.TagDataCallback;
import com.googlecode.fspotcloud.server.control.task.actions.intern.TagUpdateAction;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.peer.GetTagDataAction;
import com.googlecode.fspotcloud.shared.peer.TagUpdate;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class TagUpdateHandler extends SimpleActionHandler<TagUpdateAction, VoidResult> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(TagUpdateHandler.class.getName());
    private final int MAX_DATA_TICKS;
    private final ControllerDispatchAsync controllerDispatch;
    private final TaskQueueDispatch dispatchAsync;

    @Inject
    public TagUpdateHandler(@Named("maxTicks")
    int maxTicks, ControllerDispatchAsync controllerDispatch,
        TaskQueueDispatch dispatchAsync) {
        super();
        this.controllerDispatch = controllerDispatch;
        this.dispatchAsync = dispatchAsync;
        MAX_DATA_TICKS = maxTicks;
    }

    @Override
    public VoidResult execute(TagUpdateAction action, ExecutionContext context)
        throws DispatchException {
        List<TagUpdate> updates = action.getUpdates();
        int size = updates.size();
        int countWeWillDo;
        int needToScheduleCount = (int) Math.ceil((double) size / (double) MAX_DATA_TICKS);

        if (needToScheduleCount > MAX_DATA_TICKS) {
            countWeWillDo = MAX_DATA_TICKS;

            List<TagUpdate> nextList = new ArrayList<TagUpdate>();
            nextList.addAll(updates.subList(countWeWillDo * MAX_DATA_TICKS, size));
            dispatchAsync.execute(new TagUpdateAction(nextList));
        } else {
            countWeWillDo = needToScheduleCount;
        }

        // Do our part of the job, scheduling the head
        for (int i = 0; i < countWeWillDo; i++) {
            int beginning = i * MAX_DATA_TICKS;
            List<String> imageKeys = new ArrayList<String>();

            for (int j = beginning;
                    (j < (MAX_DATA_TICKS + beginning)) && (j < updates.size());
                    j++) {
                TagUpdate tagUpdate = updates.get(j);
                imageKeys.add(tagUpdate.getTagId());
            }

            // log.info("Doing our part " + imageKeys);
            GetTagDataAction botAction = new GetTagDataAction(imageKeys);
            TagDataCallback callback = new TagDataCallback(null, null);
            controllerDispatch.execute(botAction, callback);
        }

        return new VoidResult();
    }
}
