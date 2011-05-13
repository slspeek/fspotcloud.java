package fspotcloud.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fspotcloud.client.main.gin.AppGinjector;

public class MVPSetupGwtTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "MVPSetupTest";
	}
	
	public void testMVPSetup() {
		AppGinjector injector = GWT.create(AppGinjector.class);
		MVPSetup setup = injector.getMVPSetup();
	}
}
