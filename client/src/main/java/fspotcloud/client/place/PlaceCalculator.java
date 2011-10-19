package fspotcloud.client.place;

import java.util.logging.Logger;

import fspotcloud.client.place.api.Navigator;
import fspotcloud.client.place.api.Navigator.Zoom;
/**
 * Holds the current raster size
 * @author steven
 *
 */
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

	public BasePlace getFullscreen(BasePlace place) {
		PlaceConverter converter = new PlaceConverter(place);
		converter.setRowCount(1);
		converter.setColumnCount(1);
		BasePlace dest = converter.getNewPlace();
		return dest;
	}

	public BasePlace toggleRasterView(BasePlace place) {
		PlaceConverter converter = new PlaceConverter(place);
		int width = place.getColumnCount();
		int height = place.getRowCount();
		if (width * height > 1) {
			width = 1;
			height = 1;
		} else {
			width = getRasterWidth();
			height = getRasterHeight();
		}
		converter.setColumnCount(width);
		converter.setRowCount(height);
		return converter.getNewPlace();
	}

	public BasePlace toggleZoomView(BasePlace place, String tagId,
			String photoId) {
		PlaceConverter converter = new PlaceConverter(place);
		converter.setTagId(tagId);
		converter.setPhotoId(photoId);
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
		converter.setColumnCount(width);
		converter.setRowCount(height);
		return converter.getNewPlace();
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
		PlaceConverter converter = new PlaceConverter(place);
		converter.setColumnCount(getRasterWidth());
		converter.setRowCount(getRasterHeight());
		return converter.getNewPlace();
	}

	
	
	public BasePlace zoom(BasePlace now, Navigator.Zoom direction) {
		BasePlace dest;
		if (direction == Zoom.IN) {
			int width = now.getColumnCount();
			int height = now.getRowCount();
			if (width * height == 1) {
					dest = now;
				
			} else {
				setRasterWidth(width - 1);
				setRasterHeight(height - 1);
				if (width - 1 != getRasterWidth()) {
					// switch to 1x1
					dest = new BasePlace(now.getTagId(), now.getPhotoId(),
							1, 1);
				} else {
					dest = new BasePlace(now.getTagId(), now.getPhotoId(),
							getRasterWidth(), getRasterHeight());
				}

			}

		} else {
				int width = now.getColumnCount();
				int height = now.getRowCount();
				setRasterWidth(width + 1);
				setRasterHeight(height + 1);
				dest = new BasePlace(now.getTagId(), now.getPhotoId(),
						getRasterWidth(), getRasterHeight());
		}
		return dest;
	}
}
