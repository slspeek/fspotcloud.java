package fspotcloud.client.main;

import junit.framework.TestCase;
import fspotcloud.client.main.view.TagViewingPlace;
import fspotcloud.client.place.ImageViewingPlace;

import com.google.gwt.place.shared.Place;

public class PlaceSwapperTest extends TestCase {

	PlaceSwapper swapper = new PlaceSwapper();
	
	public void testSimpleSwap() {
		Place imageViewingPlace = new ImageViewingPlace("1", "10");
		Place newPlace = swapper.swap(imageViewingPlace);
		assertEquals(TagViewingPlace.class, newPlace.getClass());
		assertEquals("1", ((TagViewingPlace)newPlace).getTagId());
		assertEquals("10", ((TagViewingPlace)newPlace).getPhotoId());
	}
	
	public void testReverse() {
		Place tagViewingPlace = new TagViewingPlace("1", "10");
		Place newPlace = swapper.swap(tagViewingPlace);
		assertEquals(ImageViewingPlace.class, newPlace.getClass());
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("10", ((ImageViewingPlace)newPlace).getPhotoId());
	}
}
