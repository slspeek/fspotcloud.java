package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.junit.client.GWTTestCase;

import fspotcloud.client.main.ui.ImageViewImpl;

public class GwtTestImageViewImpl extends GWTTestCase {

	private static final Logger log = Logger
			.getLogger(GwtTestImageViewImpl.class.getName());

	public void testOne() {
		ImageViewImpl actions = new ImageViewImpl("0x0");
		assertNotNull(actions);
	}

	@Override
	public String getModuleName() {

		return "fspotcloud.FSpotCloud";
	}

}
