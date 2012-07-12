package com.googlecode.fspotcloud.server.admin.integration;

import net.customware.gwt.dispatch.shared.DispatchException;

import java.sql.SQLException;

public class Fixture extends PeerServerEnvironment {

    public void setUpFixture() throws SQLException, DispatchException {
        setUpPeer();
        synchronizePeer();
        verifyAllTagsAreLoaded();
        importTag("3");
    }


}
