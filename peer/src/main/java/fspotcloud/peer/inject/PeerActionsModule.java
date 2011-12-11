package fspotcloud.peer.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.peer.handlers.GetPeerMetaDataHandler;
import fspotcloud.peer.handlers.GetPhotoDataHandler;
import fspotcloud.peer.handlers.GetTagDataHandler;
import fspotcloud.peer.handlers.GetTagUpdateInstructionsHandler;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.GetTagData;
import fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;

public class PeerActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetPeerMetaData.class, GetPeerMetaDataHandler.class);
		bindHandler(GetTagData.class, GetTagDataHandler.class);
		bindHandler(GetPhotoData.class, GetPhotoDataHandler.class);
		bindHandler(GetTagUpdateInstructionsAction.class, GetTagUpdateInstructionsHandler.class);
	}

}