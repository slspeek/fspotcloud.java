package fspotcloud.client.main.shared;

import junit.framework.TestCase;

public class SlideshowEventTest extends TestCase {

	public void testSlideshowEvent() {
		SlideshowEvent event = new SlideshowEvent(SlideshowEvent.ACTION_START);
		assertNotNull(event);
	}

	public void testGetActionType() {
		SlideshowEvent event = new SlideshowEvent(SlideshowEvent.ACTION_START);
		assertEquals(SlideshowEvent.ACTION_START, event.getActionType());
		event = new SlideshowEvent(SlideshowEvent.ACTION_STOP);
		assertEquals(SlideshowEvent.ACTION_STOP, event.getActionType());
	}

}
