package com.googlecode.fspotcloud.client.main.event;

import com.googlecode.fspotcloud.client.main.event.ActionFamily;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import com.googlecode.fspotcloud.client.main.gin.EventGinjector;
import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public class GwtTestEventModule extends GWTTestCase {

	
	
	public void testOne() {
		EventGinjector injector = GWT.create(EventGinjector.class);
		ActionFamily family = injector.getAllActions();
		assertNotNull(family);
		
		assertEquals(8, family.get("Navigation").allActions().size());
		for (UserAction action: family.get("Slideshow").allActions()) {
			assertNotNull(action.getIcon());
		}
	}
	@Override
	public String getModuleName() {

		return "com.googlecode.fspotcloud.FSpotCloud";
	}

}
