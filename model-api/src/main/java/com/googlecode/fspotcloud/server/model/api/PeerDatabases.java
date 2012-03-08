package com.googlecode.fspotcloud.server.model.api;

import com.googlecode.simplejpadao.SimpleDAONamedId;

public interface PeerDatabases extends SimpleDAONamedId<PeerDatabase,String> {

	PeerDatabase get();

}