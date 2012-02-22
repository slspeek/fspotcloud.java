package fspotcloud.server.model.api;

import fspotcloud.simplejpadao.SimpleDAONamedId;

public interface PeerDatabases extends SimpleDAONamedId<PeerDatabase,String> {

	PeerDatabase get();

}