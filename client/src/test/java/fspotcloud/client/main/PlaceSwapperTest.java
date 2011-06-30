package fspotcloud.client.main;

import junit.framework.TestCase;

import com.google.gwt.place.shared.Place;

import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

public class PlaceSwapperTest extends TestCase {

	PlaceSwapper swapper = new PlaceSwapper();
	
	public void testSimpleSwap() {
		BasePlace imageViewingPlace = new ImageViewingPlace("1", "10", 8, 9);
		BasePlace newPlace = swapper.toggleTreeViewVisible(imageViewingPlace);
		assertEquals(TagViewingPlace.class, newPlace.getClass());
		assertEquals("1", newPlace.getTagId());
		assertEquals("10", newPlace.getPhotoId());
		assertEquals(8, newPlace.getColumnCount());
		assertEquals(9, newPlace.getRowCount());
	}
	
	public void testReverse() {
		BasePlace tagViewingPlace = new TagViewingPlace("1", "10");
		Place newPlace = swapper.toggleTreeViewVisible(tagViewingPlace);
		assertEquals(ImageViewingPlace.class, newPlace.getClass());
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("10", ((ImageViewingPlace)newPlace).getPhotoId());
		assertEquals(1, ((ImageViewingPlace)newPlace).getColumnCount());
		assertEquals(1, ((ImageViewingPlace)newPlace).getRowCount());
	}
	
	public void testToggleRasterView() {
		BasePlace tagViewingPlace = new ImageViewingPlace("1", "10", 2, 1);
		ImageViewingPlace newPlace = (ImageViewingPlace) swapper.toggleRasterView(tagViewingPlace);
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("10", ((ImageViewingPlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
	}
	
	public void testZoomView() {
		BasePlace tagViewingPlace = new ImageViewingPlace("1", "10", 2, 1);
		ZoomViewEvent event = new ZoomViewEvent("1", "11");
		ImageViewingPlace newPlace = (ImageViewingPlace) swapper.toggleZoomView(tagViewingPlace, event);
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("11", ((ImageViewingPlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
	}
	
	public void testZoomViewOut() {
		BasePlace tagViewingPlace = new ImageViewingPlace("1", "10", 1, 1);
		ZoomViewEvent event = new ZoomViewEvent("1", "10");
		ImageViewingPlace newPlace = (ImageViewingPlace) swapper.toggleZoomView(tagViewingPlace, event);
		assertEquals("1", ((ImageViewingPlace)newPlace).getTagId());
		assertEquals("10", ((ImageViewingPlace)newPlace).getPhotoId());
		assertEquals(PlaceSwapper.DEFAULT_RASTER_WIDTH, newPlace.getColumnCount());
		assertEquals(PlaceSwapper.DEFAULT_RASTER_HEIGHT, newPlace.getRowCount());
	}
	
	
}
