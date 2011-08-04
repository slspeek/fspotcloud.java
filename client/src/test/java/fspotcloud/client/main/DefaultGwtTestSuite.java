package fspotcloud.client.main;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestCase;
import fspotcloud.client.main.gin.GwtTestEventGinjector;
import fspotcloud.client.main.ui.GwtTestImageViewImpl;

public class DefaultGwtTestSuite extends TestCase {
	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("All Gwt Tests");
		suite.addTestSuite(GwtTestEventGinjector.class);
		suite.addTestSuite(GwtTestImageViewImpl.class);
		return suite;
	}
}
