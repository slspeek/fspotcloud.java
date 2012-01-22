package fspotcloud.client.main.shared;

import junit.framework.TestCase;
import fspotcloud.client.main.event.navigation.NavigationEvent;
import fspotcloud.client.main.event.navigation.NavigationType;

public class NavigationEventTest extends TestCase {

	public void testNavigationEvent() {
		NavigationEvent event = new NavigationEvent(NavigationType.BACK);
		assertNotNull(event);
	}

	public void testGetActionType() {
		NavigationEvent event = new NavigationEvent(NavigationType.BACK);
		assertEquals(NavigationType.BACK, event.getActionDef());
	}
}
