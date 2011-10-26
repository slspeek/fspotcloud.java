package fspotcloud.client.main.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

import fspotcloud.client.main.gin.EventGinjector;
import fspotcloud.client.view.action.api.UserAction;

public class GwtTestEventModule extends GWTTestCase {

	
	
	public void testOne() {
		EventGinjector injector = GWT.create(EventGinjector.class);
		ActionFamily family = injector.getAllActions();
		assertNotNull(family);
		
		assertEquals(4, family.get("Navigation").allActions().size());
		for (UserAction action: family.get("Slideshow").allActions()) {
			assertNotNull(action.getIcon());
		}
	}
	@Override
	public String getModuleName() {

		return "fspotcloud.FSpotCloud";
	}

}
