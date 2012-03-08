package com.googlecode.fspotcloud.peer.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import com.googlecode.fspotcloud.peer.handlers.GetPeerMetaDataHandler;
import com.googlecode.fspotcloud.peer.handlers.GetPhotoDataHandler;
import com.googlecode.fspotcloud.peer.handlers.GetTagDataHandler;
import com.googlecode.fspotcloud.peer.handlers.GetTagUpdateInstructionsHandler;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;

public class PeerActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetPeerMetaData.class, GetPeerMetaDataHandler.class);
		bindHandler(GetTagData.class, GetTagDataHandler.class);
		bindHandler(GetPhotoData.class, GetPhotoDataHandler.class);
		bindHandler(GetTagUpdateInstructionsAction.class, GetTagUpdateInstructionsHandler.class);
	}

}