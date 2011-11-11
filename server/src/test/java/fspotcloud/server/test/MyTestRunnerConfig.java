package fspotcloud.server.test;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestSuite;

import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3Config;
import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3TestRun;
import com.google.appengine.testing.cloudcover.spi.TestRun;

import fspotcloud.server.admin.actions.CountPhotosHandlerTest;
import fspotcloud.server.control.task.DelayedDataSchedulerTest;
import fspotcloud.server.cron.CronTest;
import fspotcloud.server.model.ModelModuleTest;
import fspotcloud.server.model.PersistenceManagerProviderTest;
import fspotcloud.server.model.peerdatabase.PeerDatabaseManagerTest;
import fspotcloud.server.model.photo.PhotoDOBuilderTest;
import fspotcloud.server.model.photo.PhotoManagerTest;
import fspotcloud.server.model.tag.TagManagerTest;
import fspotcloud.server.model.tag.TreeBuilderTest;

public class MyTestRunnerConfig extends JUnit3Config {

	private enum Suite {

		SUITE1("Model") {
			@Override
			public TestSuite getTestSuite() {
				suite.addTestSuite(PhotoManagerTest.class);
				suite.addTestSuite(PhotoDOBuilderTest.class);
				suite.addTestSuite(TagManagerTest.class);
				suite.addTestSuite(PersistenceManagerProviderTest.class);
				suite.addTestSuite(PeerDatabaseManagerTest.class);
				suite.addTestSuite(TreeBuilderTest.class);
				suite.addTestSuite(ModelModuleTest.class);
				return suite;
			}
		},
		SUITE2("Server") {
			@Override
			public TestSuite getTestSuite() {
				suite.addTestSuite(DelayedDataSchedulerTest.class);
				//suite.addTestSuite(InjectorTest.class);
				suite.addTestSuite(CountPhotosHandlerTest.class);
				suite.addTestSuite(CronTest.class);
				return suite;
			}
		};

	/*	SUITE3("Suite3") {
			@Override
			public TestSuite getTestSuite() {
				suite.addTestSuite(BatchManagerTest.class);
				return suite;
			}
		};*/

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
