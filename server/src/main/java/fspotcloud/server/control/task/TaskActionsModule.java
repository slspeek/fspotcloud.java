package fspotcloud.server.control.task;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.control.task.actions.intern.DeleteTags;
import fspotcloud.server.control.task.actions.intern.PhotoImportScheduleAction;
import fspotcloud.server.control.task.actions.intern.TagImportAction;
import fspotcloud.server.control.task.photodelete.DeletePhotosHandler;
import fspotcloud.server.control.task.photoimport.PhotoImportScheduleHandler;
import fspotcloud.server.control.task.tagdelete.DeleteTagsHandler;
import fspotcloud.server.control.task.tagimport.TagImportHandler;

public class TaskActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(PhotoImportScheduleAction.class, PhotoImportScheduleHandler.class);
		bindHandler(TagImportAction.class, TagImportHandler.class);
		bindHandler(DeleteTags.class, DeleteTagsHandler.class);
		bindHandler(DeletePhotos.class, DeletePhotosHandler.class);
	}
}