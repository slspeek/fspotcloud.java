package fspotcloud.client.main;

import junit.framework.TestCase;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.PlaceCalculator;

public class PlaceCalculatorTest extends TestCase {

	PlaceCalculator swapper = new PlaceCalculator();
	
	public void testSimpleSwap() {
		BasePlace imageViewingPlace = new BasePlace("1", "10", 8, 9, false);
		BasePlace newPlace = swapper.toggleTreeViewVisible(imageViewingPlace);
		assertTrue(newPlace.isTreeVisible());
		assertEquals("1", newPlace.getTagId());
		assertEquals("10", newPlace.getPhotoId());
		assertEquals(8, newPlace.getColumnCount());
		assertEquals(9, newPlace.getRowCount());
	}
	
	public void testReverse() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 1, 1, true);
		BasePlace newPlace = swapper.toggleTreeViewVisible(tagViewingPlace);
		assertEquals(false, newPlace.isTreeVisible());
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, ((BasePlace)newPlace).getColumnCount());
		assertEquals(1, ((BasePlace)newPlace).getRowCount());
	}
	
	public void testToggleRasterView() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1);
		BasePlace newPlace = (BasePlace) swapper.toggleRasterView(tagViewingPlace);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
	}
	
	public void testZoomViewWithTree() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1, true);
		BasePlace newPlace = (BasePlace) swapper.toggleZoomView(tagViewingPlace,"1", "11");
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("11", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		assertEquals(true, newPlace.isTreeVisible());
	}
	
	public void testZoomViewWithoutTree() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1, false);
		BasePlace newPlace = (BasePlace) swapper.toggleZoomView(tagViewingPlace,"1", "11");
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("11", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		assertEquals(false, newPlace.isTreeVisible());
	}
	
	
	public void testZoomViewOut() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 1, 1);
		BasePlace newPlace = (BasePlace) swapper.toggleZoomView(tagViewingPlace, "1", "10");
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(PlaceCalculator.DEFAULT_RASTER_WIDTH, newPlace.getColumnCount());
		assertEquals(PlaceCalculator.DEFAULT_RASTER_HEIGHT, newPlace.getRowCount());
	}
	
	
}
