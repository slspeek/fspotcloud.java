package fspotcloud.server.model;

import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3Config;
import com.google.appengine.testing.cloudcover.harness.junit3.JUnit3TestRun;
import com.google.appengine.testing.cloudcover.spi.TestRun;

public class TestRunnerConfig extends JUnit3Config {
  public TestRun newTestRun(String suiteId) {
    return new JUnit3TestRun(ServerModelSuite.suite());
  }
}