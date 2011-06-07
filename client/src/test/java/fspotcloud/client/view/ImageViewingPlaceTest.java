package fspotcloud.client.view;

import junit.framework.TestCase;

import com.google.gwt.place.shared.Place;

import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

public class ImageViewingPlaceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEqualsObject() {
		Place place = new ImageViewingPlace("1", "2");
		Place same = new ImageViewingPlace("1", "2");
		assertEquals(place, same);
		
		Place other = new ImageViewingPlace("1", "1");
		assertNotSame(place, other);
		Place otherType = new TagViewingPlace("1", "2");
		assertNotSame(place, otherType);
	}

}
