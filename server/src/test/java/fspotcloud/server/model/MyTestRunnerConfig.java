package fspotcloud.server.model;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestSuite;

import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3Config;
import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3TestRun;
import com.google.appengine.testing.cloudcover.spi.TestRun;

import fspotcloud.server.model.batch.BatchManagerTest;
import fspotcloud.server.model.photo.PhotoManagerTest;

public class MyTestRunnerConfig extends JUnit3Config {

	private enum Suite {

		SUITE1("Suite1") {
			@Override
			public TestSuite getTestSuite() {
				suite.addTestSuite(PhotoManagerTest.class);
				return suite;
			}
		},

		SUITE2("Suite2") {
			@Override
			public TestSuite getTestSuite() {
				suite.addTestSuite(BatchManagerTest.class);
				return suite;
			}
		};

		private final String name;
		protected final TestSuite suite = new TestSuite(
				"F-Spot Cloud Server Model Testsuite");

		private Suite(String name) {
			this.name = name;
		}

		public abstract TestSuite getTestSuite();

		public static Suite forName(String suiteId) {
			for (Suite s : values()) {
				if (s.name.equals(suiteId)) {
					return s;
				}
			}
			return null;
		}
	}

	@Override
	public TestRun newTestRun(String suiteId) {
		if (suiteId == null) {
			throw new IllegalArgumentException(
					"Something  went terribly wrong "
							+ "with the cloudcover framework!");
		}
		return new JUnit3TestRun(Suite.forName(suiteId).getTestSuite());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getAvailableSuiteIds() {

		List ids = new ArrayList();
		for (Suite s : Suite.values()) {
			ids.add(s.name);
		}
		return ids;
	}
}
