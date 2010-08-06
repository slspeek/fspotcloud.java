package fspotcloud.server.model;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestSuite;

import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3Config;
import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3TestRun;
import com.google.appengine.testing.cloudcover.spi.TestRun;

import fspotcloud.server.model.batch.BatchManagerTest;
import fspotcloud.server.model.photo.PhotoManagerTest;

public class TestRunnerConfig extends JUnit3Config {
	public TestRun newTestRun(String suiteId) {
		if (suiteId.equals("suite 1")) {
			return new JUnit3TestRun(BatchManagerTest.suite());
		} else if (suiteId.equals("suite 2")) {
			return new JUnit3TestRun(PhotoManagerTest.suite());
		}
		throw new RuntimeException("unknown suite " + suiteId);
	}

	@Override
	public List<String> getAvailableSuiteIds() {
		return Arrays.asList("suite 1", "suite 2");
	}

}