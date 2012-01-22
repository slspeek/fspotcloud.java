package fspotcloud.server.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.admin.actions.CommandDeleteAllHandler;
import fspotcloud.server.admin.actions.GetMetaDataHandler;
import fspotcloud.server.admin.actions.ImportTagHandler;
import fspotcloud.server.admin.actions.SynchronizePeerHandler;
import fspotcloud.server.admin.actions.TagDeleteAllHandler;
import fspotcloud.server.admin.actions.UnImportTagHandler;
import fspotcloud.shared.dashboard.actions.CommandDeleteAll;
import fspotcloud.shared.dashboard.actions.GetMetaData;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.TagDeleteAll;
import fspotcloud.shared.dashboard.actions.UnImportTag;

public class AdminActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetMetaData.class, GetMetaDataHandler.class);
		bindHandler(TagDeleteAll.class, TagDeleteAllHandler.class);
		bindHandler(CommandDeleteAll.class, CommandDeleteAllHandler.class);
		bindHandler(ImportTag.class, ImportTagHandler.class);
		bindHandler(UnImportTag.class, UnImportTagHandler.class);
		bindHandler(SynchronizePeer.class, SynchronizePeerHandler.class);
	}
}