package fspotcloud.server.model;

import java.util.logging.Logger;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.appengine.testing.cloudcover.server.KindTrackingDatastoreDelegate;
import com.google.appengine.testing.cloudcover.util.CloudCoverLocalServiceTestHelper;
import com.google.appengine.testing.cloudcover.util.ThreadLocalDelegate;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.Delegate;

public abstract class DatastoreTest extends TestCase {

	private static final Logger log = Logger.getLogger(DatastoreTest.class
			.getName());

	private CloudCoverLocalServiceTestHelper helper;
	private KindTrackingDatastoreDelegate kindTracker;
	@SuppressWarnings("unchecked")
	private Delegate base;

	public DatastoreTest() {
		super();
	}

	public DatastoreTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		base = ApiProxy.getDelegate();
		ThreadLocalDelegate tld = new ThreadLocalDelegate(base);
		kindTracker = new KindTrackingDatastoreDelegate(base);
		tld.setDelegateForThread(kindTracker);
		CloudCoverLocalServiceTestHelper.setDelegate(tld);

		helper = new CloudCoverLocalServiceTestHelper();
		helper.setUp();
		log.info("Set up finished");
	}

	protected void tearDown() throws Exception {

		kindTracker.wipeData();
		CloudCoverLocalServiceTestHelper.setDelegate(base);
		helper.tearDown();
		log.info("Tear down finished");
	}
}