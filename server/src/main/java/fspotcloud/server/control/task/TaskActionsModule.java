package fspotcloud.server.control.task;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.control.task.actions.intern.PhotoImportScheduleAction;
import fspotcloud.server.control.task.photoimport.PhotoImportScheduleHandler;

public class TaskActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(PhotoImportScheduleAction.class, PhotoImportScheduleHandler.class);
		
	}

}