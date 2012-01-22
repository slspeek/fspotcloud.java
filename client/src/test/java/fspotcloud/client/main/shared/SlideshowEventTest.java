package fspotcloud.client.main.shared;

import fspotcloud.client.main.event.slideshow.SlideshowEvent;
import fspotcloud.client.main.event.slideshow.SlideshowType;
import junit.framework.TestCase;

public class SlideshowEventTest extends TestCase {

	public void testSlideshowEvent() {
		SlideshowEvent event = new SlideshowEvent(SlideshowType.SLIDESHOW_START);
		assertNotNull(event);
	}

	public void testGetActionType() {
		SlideshowEvent event = new SlideshowEvent(SlideshowType.SLIDESHOW_START);
		assertEquals(SlideshowType.SLIDESHOW_START, event.getActionDef());
		event = new SlideshowEvent(SlideshowType.SLIDESHOW__END);
		assertEquals(SlideshowType.SLIDESHOW__END, event.getActionDef());
	}

}
