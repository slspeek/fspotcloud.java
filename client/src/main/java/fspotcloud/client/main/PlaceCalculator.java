package fspotcloud.client.main;

import java.util.logging.Logger;

import fspotcloud.client.place.BasePlace;

public class PlaceCalculator {

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
		boolean tagView = place.isTreeVisible();
		result = create(tagId, photoId, width, height, tagView);
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
		boolean tagView = place.isTreeVisible();
		result = create(tagId, photoId, width, height, tagView);
		return result;
	}

	public BasePlace toggleTreeViewVisible(BasePlace place) {
		BasePlace result = null;
		String tagId = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height = place.getRowCount();
		boolean treeVisible = !place.isTreeVisible();
		result = create(tagId, photoId, width, height, treeVisible);
		return result;
	}

	private BasePlace create(String tagId, String photoId, int columns,
			int rows, boolean tagView) {
		BasePlace result;
		result = new BasePlace(tagId, photoId, columns, rows, tagView);
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
		boolean tagView = place.isTreeVisible();
		BasePlace result = create(place.getTagId(), place.getPhotoId(),
				getRasterWidth(), getRasterHeight(), tagView);
		return result;
	}
}
