package fspotcloud.client.main;

import junit.framework.Test;
import junit.framework.TestCase;

import com.google.gwt.junit.tools.GWTTestSuite;

import fspotcloud.client.main.ui.GwtTestImageViewImpl;
import fspotcloud.client.main.view.api.GwtTestButtonPanelPresenterProvider;

public class DefaultGwtTestSuite extends TestCase {
	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("All Gwt Tests");
		suite.addTestSuite(GwtTestImageViewImpl.class);
		//suite.addTestSuite(GwtTestButtonPanelPresenterProvider.class);
		return suite;
	}
}
