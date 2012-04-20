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
package com.googlecode.fspotcloud.server.control.task;

import com.googlecode.fspotcloud.server.control.task.actions.intern.*;
import com.googlecode.fspotcloud.server.control.task.deleteallphotos.DeleteAllPhotosHandler;
import com.googlecode.fspotcloud.server.control.task.photodelete.DeletePhotosHandler;
import com.googlecode.fspotcloud.server.control.task.photoremove.RemovePhotosFromTagHandler;
import com.googlecode.fspotcloud.server.control.task.photoupdate.PhotoUpdateHandler;
import com.googlecode.fspotcloud.server.control.task.tagdelete.DeleteTagsHandler;
import com.googlecode.fspotcloud.server.control.task.tagimport.TagImportHandler;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;


public class TaskActionsModule extends ActionHandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(PhotoUpdateAction.class, PhotoUpdateHandler.class);
        bindHandler(TagImportAction.class, TagImportHandler.class);
        bindHandler(DeleteTags.class, DeleteTagsHandler.class);
        bindHandler(DeleteAllPhotos.class, DeleteAllPhotosHandler.class);
        bindHandler(DeletePhotos.class, DeletePhotosHandler.class);
        bindHandler(
            RemovePhotosFromTagAction.class, RemovePhotosFromTagHandler.class);
    }
}
