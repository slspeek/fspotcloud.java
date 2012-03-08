package com.googlecode.fspotcloud.client.main.ui;

import com.googlecode.fspotcloud.client.main.ui.ImageViewImpl;
import java.util.logging.Logger;

import com.google.gwt.junit.client.GWTTestCase;

import com.googlecode.fspotcloud.client.main.view.api.TimerInterface;

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

		return "com.googlecode.fspotcloud.FSpotCloud";
	}

}
