package fspotcloud.server.control.hook;

import com.google.inject.Inject;

import fspotcloud.botdispatch.controller.callback.ControllerHook;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

public class TimeLoggingControllerHook implements ControllerHook {

	final private PeerDatabases defaultPeer;
	
	@Inject
	public TimeLoggingControllerHook(PeerDatabases defaultPeer) {
		super();
		this.defaultPeer = defaultPeer;
	}
	@Override
	public void preprocess(long id, byte[] result) {
		PeerDatabase peer = defaultPeer.get();
		peer.touchPeerContact();
		defaultPeer.save(peer);
	}

}
