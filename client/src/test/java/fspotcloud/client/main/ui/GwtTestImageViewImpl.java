package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.junit.client.GWTTestCase;

import fspotcloud.client.main.view.api.TimerInterface;

public class GwtTestImageViewImpl extends GWTTestCase {

	private static final Logger log = Logger
			.getLogger(GwtTestImageViewImpl.class.getName());

	ImageViewImpl imageView;
	public void testConstructor() {
	
	}

	public void testSetUrl() {
		testConstructor();
		//imageView.setImageUrl("foo");
		//assertEquals("gwt-debug-image-view-0x0", imageView.getElement().getId());
		//assertEquals("foo", imageView.getElement().getAttribute("src"));
	}
	@Override
	public String getModuleName() {

		return "fspotcloud.FSpotCloud";
	}

}
