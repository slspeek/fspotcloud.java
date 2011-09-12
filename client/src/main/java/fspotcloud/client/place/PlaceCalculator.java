package fspotcloud.client.place;

import java.util.logging.Logger;

import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.Navigator.Zoom;
import fspotcloud.client.place.api.PhotoInTag;

public class PlaceCalculator  {

	final private static Logger log = Logger.getLogger(PlaceCalculator.class
			.getName());

	public static final int DEFAULT_RASTER_WIDTH = 5;
	public static final int DEFAULT_RASTER_HEIGHT = 4;

	public static final int MINIMUM_RASTER_WIDTH = 2;
	public static final int MINIMUM_RASTER_HEIGHT = 2;

	private int rasterWidth = DEFAULT_RASTER_WIDTH;
	private int rasterHeight = DEFAULT_RASTER_HEIGHT;
		

	public PlaceCalculator() {
		log.info("Created");
	}

	public BasePlace getFullscreen(PhotoInTag place) {
		String photoId = place.getPhotoId();
		String tagId = place.getTagId();
		BasePlace dest = new BasePlace(tagId, photoId, 1, 1, false, false);
		return dest;
	}
	
	public BasePlace toggleRasterView(BasePlace place) {
		String tagId = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height = place.getRowCount();
		if (width * height > 1) {
			// switch to single view
			width = 1;
			height = 1;
		} else {
			width = getRasterWidth();
			height = getRasterHeight();
		}
		BasePlace result;
		boolean tagView = place.hasTreeVisible();
		boolean buttonsVisible = place.hasButtonsVisible();
		result = create(tagId, photoId, width, height, tagView, buttonsVisible);
		return result;
	}

	public BasePlace toggleZoomView(BasePlace place, String tagId,
			String photoId) {
		int width = place.getColumnCount();
		int height = place.getRowCount();
		if (width * height > 1) {
			// Zoom in
			width = 1;
			height = 1;
		} else {
			// Zoom out
			width = getRasterWidth();
			height = getRasterHeight();
		}
		BasePlace result;
		boolean tagView = place.hasTreeVisible();
		boolean buttonsVisible = place.hasButtonsVisible();
		result = create(tagId, photoId, width, height, tagView, buttonsVisible);
		return result;
	}

	public BasePlace toggleTreeViewVisible(BasePlace place) {
		boolean treeVisible = !place.hasTreeVisible();
		return setTreeVisible(place, treeVisible);
	}

	private BasePlace create(String tagId, String photoId, int columns,
			int rows, boolean tagView, boolean buttons) {
		BasePlace result;
		result = new BasePlace(tagId, photoId, columns, rows, tagView, buttons);
		return result;
	}

	public void setRasterHeight(int rasterHeight) {
		if (rasterHeight >= MINIMUM_RASTER_HEIGHT) {
			this.rasterHeight = rasterHeight;
		}
	}

	public int getRasterHeight() {
		return rasterHeight;
	}

	public void setRasterWidth(int rasterWidth) {
		if (rasterWidth >= MINIMUM_RASTER_WIDTH) {
			this.rasterWidth = rasterWidth;
		}
	}

	public int getRasterWidth() {
		return rasterWidth;
	}

	public BasePlace getTabularPlace(BasePlace place) {
		boolean tagView = place.hasTreeVisible();
		boolean buttonsVisible = place.hasButtonsVisible();
		BasePlace result = create(place.getTagId(), place.getPhotoId(),
				getRasterWidth(), getRasterHeight(), tagView, buttonsVisible);
		return result;
	}

	public BasePlace toggleButtonsVisible(BasePlace place) {
		boolean buttonsVisible = !place.hasButtonsVisible();
		return setButtonsVisible(place, buttonsVisible);
	}

	public BasePlace setButtonsVisible(BasePlace place, boolean visible) {
		BasePlace result = null;
		String tagId = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height = place.getRowCount();
		boolean treeVisible = place.hasTreeVisible();
		result = create(tagId, photoId, width, height, treeVisible,
				visible);
		return result;
	}

	public BasePlace setTreeVisible(BasePlace place, boolean visible) {
		BasePlace result = null;
		String tagId = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height = place.getRowCount();
		boolean buttonsVisible = place.hasButtonsVisible();
		if (visible) {
			result = create(tagId, photoId, width, height, visible,
					true);
		} else {
			result = create(tagId, photoId, width, height, visible,
					buttonsVisible);
		}
		return result;
	}

	public BasePlace zoom(BasePlace now, Navigator.Zoom direction) {
		BasePlace dest;
		if (direction == Zoom.IN) {
			if (now.hasTreeVisible()) {
				dest = setTreeVisible(now, false);
			} else if (now.hasButtonsVisible()){
				dest = setButtonsVisible(now, false);
			} else {
				int width = now.getColumnCount();
				int height = now.getRowCount();
				setRasterWidth(width - 1);
				setRasterHeight(height - 1);
				if (width == getRasterWidth()) {
					//switch to 1x1 
					dest  = getFullscreen(now);
				} else {
					dest = new BasePlace(now.getTagId(), now.getPhotoId(), getRasterWidth(), getRasterHeight(), false, false); 
				}
				
			}
			
		} else {
			if (!now.hasButtonsVisible()){
				dest = setButtonsVisible(now, true);
			} else if (!now.hasTreeVisible()) {
				dest = setTreeVisible(now, true);
			} else {
				int width = now.getColumnCount();
				int height = now.getRowCount();
				setRasterWidth(width + 1);
				setRasterHeight(height + 1);
				dest = new BasePlace(now.getTagId(), now.getPhotoId(), getRasterWidth(), getRasterHeight(), false, false); 
			}
		}
		return dest;
	}
}
