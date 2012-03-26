package com.googlecode.fspotcloud.client.main;

import com.googlecode.fspotcloud.client.main.ui.GwtTestImageViewImpl;
import junit.framework.Test;
import junit.framework.TestCase;

import com.google.gwt.junit.tools.GWTTestSuite;

import com.googlecode.fspotcloud.client.main.event.GwtTestEventModule;

public class DefaultGwtTestSuite extends TestCase {
	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("All Gwt Tests");
		suite.addTestSuite(GwtTestImageViewImpl.class);
		//suite.addTestSuite(GwtTestButtonPanelPresenterProvider.class);
		suite.addTestSuite(GwtTestEventModule.class);
		return suite;
	}
}