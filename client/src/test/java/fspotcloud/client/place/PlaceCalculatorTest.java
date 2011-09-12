package fspotcloud.client.place;

import junit.framework.TestCase;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.PlaceCalculator;
import fspotcloud.client.place.api.Navigator.Zoom;

public class PlaceCalculatorTest extends TestCase {

	PlaceCalculator placeCalculator = new PlaceCalculator();
	
	public void testSimpleSwap() {
		BasePlace imageViewingPlace = new BasePlace("1", "10", 8, 9, false);
		BasePlace newPlace = placeCalculator.toggleTreeViewVisible(imageViewingPlace);
		assertTrue(newPlace.hasTreeVisible());
		assertEquals("1", newPlace.getTagId());
		assertEquals("10", newPlace.getPhotoId());
		assertEquals(8, newPlace.getColumnCount());
		assertEquals(9, newPlace.getRowCount());
	}
	
	public void testReverse() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 1, 1, true);
		BasePlace newPlace = placeCalculator.toggleTreeViewVisible(tagViewingPlace);
		assertEquals(false, newPlace.hasTreeVisible());
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, ((BasePlace)newPlace).getColumnCount());
		assertEquals(1, ((BasePlace)newPlace).getRowCount());
	}
	
	public void testToggleRasterView() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1);
		BasePlace newPlace = (BasePlace) placeCalculator.toggleRasterView(tagViewingPlace);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
	}
	
	public void testZoomViewWithTree() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1, true);
		BasePlace newPlace = (BasePlace) placeCalculator.toggleZoomView(tagViewingPlace,"1", "11");
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("11", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		assertEquals(true, newPlace.hasTreeVisible());
	}
	
	public void testZoomViewWithoutTree() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1, false);
		BasePlace newPlace = (BasePlace) placeCalculator.toggleZoomView(tagViewingPlace,"1", "11");
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("11", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		assertEquals(false, newPlace.hasTreeVisible());
	}
	
	
	public void testZoomViewOut() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 1, 1);
		BasePlace newPlace = (BasePlace) placeCalculator.toggleZoomView(tagViewingPlace, "1", "10");
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(PlaceCalculator.DEFAULT_RASTER_WIDTH, newPlace.getColumnCount());
		assertEquals(PlaceCalculator.DEFAULT_RASTER_HEIGHT, newPlace.getRowCount());
	}
	
	public void testGetFullscreen() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 13, 41);
		BasePlace newPlace = placeCalculator.getFullscreen(tagViewingPlace);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		assertFalse(newPlace.hasButtonsVisible());
		assertFalse(newPlace.hasTreeVisible());
	}	
	
	public void testZoomIn() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 2, true, true);
		BasePlace newPlace = placeCalculator.zoom(tagViewingPlace, Zoom.IN);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertFalse(newPlace.hasTreeVisible());
		assertTrue(newPlace.hasButtonsVisible());
		newPlace = placeCalculator.zoom(newPlace, Zoom.IN);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertFalse(newPlace.hasButtonsVisible());
//		
//		assertEquals(1, newPlace.getColumnCount());
//		assertEquals(1, newPlace.getRowCount());
		
		
	}	
	
	
}
