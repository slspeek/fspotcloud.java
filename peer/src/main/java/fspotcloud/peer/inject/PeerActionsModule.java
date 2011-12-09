package fspotcloud.peer.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.peer.handlers.GetImageDataHandler;
import fspotcloud.peer.handlers.GetPeerMetaDataHandler;
import fspotcloud.peer.handlers.GetPhotoDataHandler;
import fspotcloud.peer.handlers.GetTagDataHandler;
import fspotcloud.shared.peer.rpc.actions.GetImageData;
import fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.GetTagData;

public class PeerActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetPeerMetaData.class, GetPeerMetaDataHandler.class);
		bindHandler(GetImageData.class, GetImageDataHandler.class);
		bindHandler(GetTagData.class, GetTagDataHandler.class);
		bindHandler(GetPhotoData.class, GetPhotoDataHandler.class);
	}

}