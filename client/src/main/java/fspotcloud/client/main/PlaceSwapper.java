package fspotcloud.client.main;

import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

public class PlaceSwapper {

	public static final int DEFAULT_RASTER_WIDTH = 9;
	public static final int DEFAULT_RASTER_HEIGHT = 8;
	
	public BasePlace toggleRasterView(BasePlace place) {
		String tagId  = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height=place.getRowCount();
		if (width * height > 1) {
			//switch to single view
			width = 1;
			height = 1;
		} else {
			width = PlaceSwapper.DEFAULT_RASTER_WIDTH;
			height = PlaceSwapper.DEFAULT_RASTER_HEIGHT;
		}
		BasePlace result;
		if (place instanceof ImageViewingPlace) {
			result = new ImageViewingPlace(tagId, photoId, width, height);
		} else if (place instanceof TagViewingPlace) {
			result = new TagViewingPlace(tagId, photoId, width, height);
		} else {
			throw new IllegalStateException("We only have TagViewingPlaces and ImageViewingPlaces.");
		}
		return result;
	}
	public BasePlace toggleZoomView(BasePlace place, ZoomViewEvent e) {
		String tagId  = e.getTagId();
		String photoId = e.getPhotoId();
		int width = place.getColumnCount();
		int height=place.getRowCount();
		if (width * height > 1) {
			//Zoom in
			width = 1;
			height = 1;
		} else {
			//Zoom out
			width = PlaceSwapper.DEFAULT_RASTER_WIDTH;
			height = PlaceSwapper.DEFAULT_RASTER_HEIGHT;
		}
		BasePlace result;
		if (place instanceof ImageViewingPlace) {
			result = new ImageViewingPlace(tagId, photoId, width, height);
		} else if (place instanceof TagViewingPlace) {
			result = new TagViewingPlace(tagId, photoId, width, height);
		} else {
			throw new IllegalStateException("We only have TagViewingPlaces and ImageViewingPlaces.");
		}
		return result;
	}
	
	public BasePlace toggleTreeViewVisible(BasePlace place) {
		BasePlace result = null;
		String tagId  = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height=place.getRowCount();
		if (place instanceof TagViewingPlace && !(place instanceof ImageViewingPlace)) {
			ImageViewingPlace imagePlace = new ImageViewingPlace(tagId, photoId, width, height);
			result = imagePlace;
		} else if (place instanceof ImageViewingPlace) {
			TagViewingPlace tagPlace = new TagViewingPlace(tagId, photoId, width, height);
			result = tagPlace;
		} else {
			throw new IllegalStateException("We only have TagViewingPlaces and ImageViewingPlaces.");
		}
		return result;
	}
}
