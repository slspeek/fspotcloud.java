package fspotcloud.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DashboardITest.class, ApplicationActionsITest.class,
		TabularITest.class, IntersectionDeleteITest.class, DeleteITest.class })
public class FirefoxLocalSuite {

}
