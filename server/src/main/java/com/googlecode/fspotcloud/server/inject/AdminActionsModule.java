package com.googlecode.fspotcloud.server.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import com.googlecode.fspotcloud.server.admin.actions.CommandDeleteAllHandler;
import com.googlecode.fspotcloud.server.admin.actions.GetMetaDataHandler;
import com.googlecode.fspotcloud.server.admin.actions.ImportTagHandler;
import com.googlecode.fspotcloud.server.admin.actions.SynchronizePeerHandler;
import com.googlecode.fspotcloud.server.admin.actions.TagDeleteAllHandler;
import com.googlecode.fspotcloud.server.admin.actions.UnImportTagHandler;
import com.googlecode.fspotcloud.shared.dashboard.actions.*;

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