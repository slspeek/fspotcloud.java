package fspotcloud.client.main.shared;

import junit.framework.TestCase;

public class NavigationEventTest extends TestCase {

	public void testNavigationEvent() {
		NavigationEvent event = new NavigationEvent(NavigationEvent.ActionType.BACK);
		assertNotNull(event);
	}

	public void testGetActionType() {
		NavigationEvent event = new NavigationEvent(NavigationEvent.ActionType.BACK);
		assertEquals(NavigationEvent.ActionType.BACK, event.getActionType());
	}
}
