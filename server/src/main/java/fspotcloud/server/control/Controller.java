package fspotcloud.server.control;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

public class Controller {

	private Commands commandManager;
	private final PeerDatabases defaultPeer;

	@Inject
	public Controller(Commands commandManager, PeerDatabases defaultPeer) {
		super();
		this.commandManager = commandManager;
		this.defaultPeer = defaultPeer;
	}

	private void touchPeerContact() {
		PeerDatabase pd = defaultPeer.get();
		pd.touchPeerContact();
		defaultPeer.save(pd);
	}
	
	
	@SuppressWarnings("unchecked")
	public Object[] getCommand() {
		touchPeerContact();
		return commandManager.popOldestCommand();
	}

}
