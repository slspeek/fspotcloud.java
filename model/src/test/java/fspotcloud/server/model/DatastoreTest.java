package fspotcloud.server.model;

import junit.framework.TestCase;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public abstract class DatastoreTest extends TestCase {

	private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    public void setUp() throws Exception {
        helper.setUp();
    }

    public void tearDown() throws Exception {
        helper.tearDown();
    }

	public DatastoreTest() {
	}


}