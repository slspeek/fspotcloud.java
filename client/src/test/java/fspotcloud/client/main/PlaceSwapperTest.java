package fspotcloud.client.main;

import junit.framework.TestCase;

import com.google.gwt.place.shared.Place;

import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

public class PlaceSwapperTest extends TestCase {

	PlaceSwapper swapper = new PlaceSwapper();
	
	public void testSimpleSwap() {
		BasePlace imageViewingPlace = new ImageViewingPlace("1", "10");
		BasePlace newPlace = swapper.toggleTreeViewVisible(imageViewingPlace);
		assertEquals(TagViewingPlace.class, newPlace.getClass());
		assertEquals("1", newPlace.getTagId());
		assertEquals("10", newPlace.getPhotoId());
	}
	
	public void testReverse() {
		BasePlace tagViewingPlace = new TagViewingPlace("1", "10");
		Place newPlace = swapper.toggleTreeViewVisible(tagViewingPlace);
		assertEquals(ImageViewingPlace.class, newPlace.getClass());
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("10", ((ImageViewingPlace)newPlace).getPhotoId());
	}
	
	public void testToggleRasterView() {
		BasePlace tagViewingPlace = new ImageViewingPlace("1", "10", 2, 1);
		ImageViewingPlace newPlace = (ImageViewingPlace) swapper.toggleRasterView(tagViewingPlace);
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("10", ((ImageViewingPlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
	}
}
