package fspotcloud.botdispatch.model;

import java.util.logging.Logger;

import junit.framework.TestCase;

import com.google.appengine.testing.cloudcover.server.KindTrackingDatastoreDelegate;
import com.google.appengine.testing.cloudcover.util.CloudCoverLocalServiceTestHelper;
import com.google.appengine.testing.cloudcover.util.ThreadLocalDelegate;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.Delegate;

public abstract class DatastoreTest extends TestCase {

	private static final Logger log = Logger.getLogger(DatastoreTest.class
			.getName());
	private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    public void setUp() {
        helper.setUp();
    }

    public void tearDown() {
        helper.tearDown();
    }

	public DatastoreTest(String name) {
		super(name);
	}


}