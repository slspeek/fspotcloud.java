package fspotcloud.client.main;

import java.util.logging.Logger;

import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

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
		boolean tagView = place instanceof TagViewingPlace;
		result = create(tagView, tagId, photoId, width, height);
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
		boolean tagView = place instanceof TagViewingPlace;
		result = create(tagView, tagId, photoId, width, height);
		return result;
	}

	public BasePlace toggleTreeViewVisible(BasePlace place) {
		BasePlace result = null;
		String tagId = place.getTagId();
		String photoId = place.getPhotoId();
		int width = place.getColumnCount();
		int height = place.getRowCount();
		boolean tagView = place instanceof ImageViewingPlace;
		result = create(tagView, tagId, photoId, width, height);
		return result;
	}

	private BasePlace create(boolean tagView, String tagId, String photoId,
			int columns, int rows) {
		BasePlace result;
		if (tagView) {
			result = new TagViewingPlace(tagId, photoId, columns, rows);
		} else {
			result = new ImageViewingPlace(tagId, photoId, columns, rows);
		}
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
		boolean tagView = place instanceof TagViewingPlace;
		BasePlace result = create(tagView, place.getTagId(),
				place.getPhotoId(), getRasterWidth(), getRasterHeight());
		return result;
	}
}
