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

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllPhotosAction;
import com.googlecode.fspotcloud.server.control.task.actions.intern.ImportManyTagsPhotosAction;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.shared.dashboard.UserImportsTagAction;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class ImportManyTagsPhotosHandler extends SimpleActionHandler<ImportManyTagsPhotosAction, VoidResult> {
    private static final Logger log = Logger.getLogger(ImportManyTagsPhotosHandler.class.getName());
    private final TaskQueueDispatch dispatchAsync;
    private final Photos photoManager;
    private final int MAX_DATA_TICKS;

    @Inject
    public ImportManyTagsPhotosHandler(TaskQueueDispatch dispatchAsync,
        Photos photoManager, @Named("maxTicks")
    int MAX_DATA_TICKS) {
        super();
        this.dispatchAsync = dispatchAsync;
        this.photoManager = photoManager;
        this.MAX_DATA_TICKS = MAX_DATA_TICKS;
    }

    @Override
    public VoidResult execute(ImportManyTagsPhotosAction action,
        ExecutionContext context) throws DispatchException {
        final List<String> tagIdList = action.getTagIdList();

        if (tagIdList.size() > MAX_DATA_TICKS) {
            List<String> nextList = new ArrayList<String>();
            nextList.addAll(tagIdList.subList(MAX_DATA_TICKS, tagIdList.size()));
            dispatchAsync.execute(new ImportManyTagsPhotosAction(nextList));
        }

        for (int i = 0; (i < MAX_DATA_TICKS) && (i < tagIdList.size()); i++) {
            String tagId = tagIdList.get(i);
            dispatchAsync.execute(new UserImportsTagAction(tagId));
        }

        return new VoidResult();
    }
}
