package fspotcloud.peer.inject;

import fspotcloud.peer.handlers.GetPeerMetaDataHandler;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class ActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetPeerMetaData.class, GetPeerMetaDataHandler.class);
	}

}