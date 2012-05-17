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
import com.googlecode.fspotcloud.server.control.callback.PhotoDataCallback;
import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.peer.GetPhotoDataAction;
import com.googlecode.fspotcloud.shared.peer.ImageSpecs;
import com.googlecode.fspotcloud.shared.peer.PhotoUpdate;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import static com.google.common.collect.Lists.*;
import com.googlecode.fspotcloud.server.control.task.actions.intern.AbstractBatchAction;

public class PhotoUpdateHandler extends AbstractBatchActionHandler<PhotoUpdate> implements ActionHandler<PhotoUpdateAction, VoidResult> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoUpdateHandler.class.getName());
    private final int MAX_DATA_TICKS;
    private final int MAX_PHOTO_TICKS;
    private final ControllerDispatchAsync controllerDispatch;
    private final TaskQueueDispatch dispatchAsync;
    private final ImageSpecs imageSpecs;

    @Inject
    public PhotoUpdateHandler(@Named("maxTicks")
    int maxTicks, @Named("maxPhotoTicks")
    int maxPhotoTicks, @Named("defaultImageSpecs")
    ImageSpecs imageSpecs, ControllerDispatchAsync controllerDispatch,
        TaskQueueDispatch dispatchAsync) {
        super(dispatchAsync, maxTicks);
        this.controllerDispatch = controllerDispatch;
        this.dispatchAsync = dispatchAsync;
        MAX_DATA_TICKS = maxTicks;
        MAX_PHOTO_TICKS = maxPhotoTicks;
        this.imageSpecs = imageSpecs;
    }

    @Override
    public VoidResult execute(PhotoUpdateAction action, ExecutionContext context)
        throws DispatchException {
        return super.execute(action, context);
    }

    @Override
    public Class<PhotoUpdateAction> getActionType() {
        PhotoUpdateAction action = new PhotoUpdateAction(null);
        return (Class<PhotoUpdateAction>) action.getClass();
    }

    @Override
    public void rollback(PhotoUpdateAction a, VoidResult r, ExecutionContext ec) throws DispatchException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doWork(AbstractBatchAction<PhotoUpdate> action, Iterator<PhotoUpdate> workLoad) {
            List<String> imageKeys = newArrayList();
            for (int j = 0; j < MAX_PHOTO_TICKS &&  workLoad.hasNext(); j++) {
                PhotoUpdate photoUpdate = workLoad.next();
                imageKeys.add(photoUpdate.getPhotoId());
            }
            GetPhotoDataAction botAction = new GetPhotoDataAction(imageSpecs,
                    imageKeys);
            PhotoDataCallback callback = new PhotoDataCallback(null, null, null);
            controllerDispatch.execute(botAction, callback);
        
    }
}
