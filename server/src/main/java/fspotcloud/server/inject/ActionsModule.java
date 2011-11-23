package fspotcloud.server.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.admin.actions.CountPhotosHandler;
import fspotcloud.server.admin.actions.DeleteAllPhotosHandler;
import fspotcloud.server.admin.actions.DeleteAllTagsHandler;
import fspotcloud.server.admin.actions.GetMetaDataHandler;
import fspotcloud.server.admin.actions.ImportTagHandler;
import fspotcloud.server.admin.actions.SynchronizePeerHandler;
import fspotcloud.server.admin.actions.UnImportTagHandler;
import fspotcloud.shared.dashboard.actions.CountPhotos;
import fspotcloud.shared.dashboard.actions.DeleteAllPhotos;
import fspotcloud.shared.dashboard.actions.DeleteAllTags;
import fspotcloud.shared.dashboard.actions.GetMetaData;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.UnImportTag;

public class ActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetMetaData.class, GetMetaDataHandler.class);
		bindHandler(CountPhotos.class, CountPhotosHandler.class);
		bindHandler(DeleteAllPhotos.class, DeleteAllPhotosHandler.class);
		bindHandler(DeleteAllTags.class, DeleteAllTagsHandler.class);
		bindHandler(ImportTag.class, ImportTagHandler.class);
		bindHandler(UnImportTag.class, UnImportTagHandler.class);
		bindHandler(SynchronizePeer.class, SynchronizePeerHandler.class);
	}
}