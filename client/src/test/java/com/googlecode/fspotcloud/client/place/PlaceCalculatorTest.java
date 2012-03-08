package com.googlecode.fspotcloud.client.place;

import com.googlecode.fspotcloud.client.place.SlideshowPlace;
import junit.framework.TestCase;
import com.googlecode.fspotcloud.client.place.BasePlace;
import com.googlecode.fspotcloud.client.place.PlaceCalculator;
import com.googlecode.fspotcloud.client.place.api.Navigator.Zoom;

public class PlaceCalculatorTest extends TestCase {

	PlaceCalculator placeCalculator = new PlaceCalculator();
	
	
	public void testUnslideshow() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1);
		BasePlace newPlace = (BasePlace) placeCalculator.unslideshow(tagViewingPlace);
		assertEquals(newPlace, tagViewingPlace);
		
		
		SlideshowPlace place = new SlideshowPlace("1", "2", 0f);
		newPlace = placeCalculator.unslideshow(place);
		
		assertFalse(newPlace instanceof SlideshowPlace);
		
	}
	
	
	
	
	public void testToggleRasterView() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 1);
		BasePlace newPlace = (BasePlace) placeCalculator.toggleRasterView(tagViewingPlace);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
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
	}	
	
	public void testZoomIn() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 3, 3);
		BasePlace newPlace = placeCalculator.zoom(tagViewingPlace, Zoom.IN);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(2, newPlace.getColumnCount());
		assertEquals(2, newPlace.getRowCount());;
		
		newPlace = placeCalculator.zoom(newPlace, Zoom.IN);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		
		newPlace = placeCalculator.zoom(newPlace, Zoom.IN);
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		
		newPlace = placeCalculator.zoom(newPlace, Zoom.IN);
		assertEquals(1, newPlace.getColumnCount());
		assertEquals(1, newPlace.getRowCount());
		
		
	}	
	
	public void testZoomOut() {
		BasePlace tagViewingPlace = new BasePlace("1", "10", 2, 2);
		BasePlace newPlace = placeCalculator.zoom(tagViewingPlace, Zoom.OUT);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(3, newPlace.getColumnCount());
		assertEquals(3, newPlace.getRowCount());;
		newPlace = placeCalculator.zoom(newPlace, Zoom.OUT);
		assertEquals("1", ((BasePlace)newPlace).getTagId());
		assertEquals("10", ((BasePlace)newPlace).getPhotoId());
		assertEquals(4, newPlace.getColumnCount());
		assertEquals(4, newPlace.getRowCount());
	}	
	
	
}
