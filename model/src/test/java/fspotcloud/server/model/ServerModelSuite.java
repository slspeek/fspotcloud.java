package fspotcloud.server.model;

import fspotcloud.server.model.batch.BatchManagerTest;
import fspotcloud.server.model.photo.PhotoManagerTest;
import junit.framework.TestSuite;

public class ServerModelSuite extends TestSuite {
	
	public ServerModelSuite() {
		addTestSuite(BatchManagerTest.class);
		addTestSuite(PhotoManagerTest.class);
	}

}
