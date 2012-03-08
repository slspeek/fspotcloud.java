package com.googlecode.fspotcloud.peer.handlers;

import java.net.URL;

import net.customware.gwt.dispatch.shared.DispatchException;

import junit.framework.TestCase;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;

public class GetPeerMetaDataHandlerTest extends TestCase {

	GetPeerMetaData action = new GetPeerMetaData();
	Data data;
	GetPeerMetaDataHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		URL testDatabase = ClassLoader.getSystemResource("photos.db");
		String path = testDatabase.getPath();
		data = new Data("jdbc:sqlite:" +path);
		handler = new GetPeerMetaDataHandler(data);
	}
	
	public void testExecute() throws DispatchException {
		PeerMetaDataResult result = handler.execute(action, null);
		assertEquals(5, result.getTagCount());
		assertEquals(28, result.getPhotoCount());
	}

}
