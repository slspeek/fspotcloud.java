package fspotcloud.client.main.shared;

import junit.framework.TestCase;

public class NavigationEventTest extends TestCase {

	public void testNavigationEvent() {
		NavigationEvent event = new NavigationEvent(NavigationEvent.BACK);
		assertNotNull(event);
	}

	public void testGetActionType() {
		NavigationEvent event = new NavigationEvent(NavigationEvent.BACK);
		assertEquals(NavigationEvent.BACK, event.getActionType());
	}
}
