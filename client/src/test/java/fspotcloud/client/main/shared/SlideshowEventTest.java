package fspotcloud.client.main.shared;

import junit.framework.TestCase;

public class SlideshowEventTest extends TestCase {

	public void testSlideshowEvent() {
		SlideshowEvent event = new SlideshowEvent(SlideshowEvent.ActionType.START);
		assertNotNull(event);
	}

	public void testGetActionType() {
		SlideshowEvent event = new SlideshowEvent(SlideshowEvent.ActionType.START);
		assertEquals(SlideshowEvent.ActionType.START, event.getActionType());
		event = new SlideshowEvent(SlideshowEvent.ActionType.STOP);
		assertEquals(SlideshowEvent.ActionType.STOP, event.getActionType());
	}

}
