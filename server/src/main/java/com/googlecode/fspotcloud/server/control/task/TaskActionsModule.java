package com.googlecode.fspotcloud.server.control.task;

import com.googlecode.fspotcloud.server.control.task.actions.intern.*;
import com.googlecode.fspotcloud.server.control.task.deleteallphotos.DeleteAllPhotosHandler;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import com.googlecode.fspotcloud.server.control.task.photodelete.DeletePhotosHandler;
import com.googlecode.fspotcloud.server.control.task.photoremove.RemovePhotosFromTagHandler;
import com.googlecode.fspotcloud.server.control.task.photoupdate.PhotoUpdateHandler;
import com.googlecode.fspotcloud.server.control.task.tagdelete.DeleteTagsHandler;
import com.googlecode.fspotcloud.server.control.task.tagimport.TagImportHandler;

public class TaskActionsModule extends ActionHandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(PhotoUpdateAction.class, PhotoUpdateHandler.class);
        bindHandler(TagImportAction.class, TagImportHandler.class);
        bindHandler(DeleteTags.class, DeleteTagsHandler.class);
        bindHandler(DeleteAllPhotos.class, DeleteAllPhotosHandler.class);
        bindHandler(DeletePhotos.class, DeletePhotosHandler.class);
        bindHandler(RemovePhotosFromTagAction.class, RemovePhotosFromTagHandler.class);
    }
}