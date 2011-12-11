package fspotcloud.server.control.task;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.control.task.actions.intern.DeleteTags;
import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import fspotcloud.server.control.task.actions.intern.TagImportAction;
import fspotcloud.server.control.task.photodelete.DeletePhotosHandler;
import fspotcloud.server.control.task.photoremove.RemovePhotosFromTagHandler;
import fspotcloud.server.control.task.photoupdate.PhotoUpdateHandler;
import fspotcloud.server.control.task.tagdelete.DeleteTagsHandler;
import fspotcloud.server.control.task.tagimport.TagImportHandler;

public class TaskActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(PhotoUpdateAction.class, PhotoUpdateHandler.class);
		bindHandler(TagImportAction.class, TagImportHandler.class);
		bindHandler(DeleteTags.class, DeleteTagsHandler.class);
		bindHandler(DeletePhotos.class, DeletePhotosHandler.class);
		bindHandler(RemovePhotosFromTagAction.class, RemovePhotosFromTagHandler.class);
	}
}