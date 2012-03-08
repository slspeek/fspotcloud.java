package com.googlecode.fspotcloud.client.place.api;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.fspotcloud.shared.photo.PhotoInfoStore;


public interface Navigator {
	
	enum Unit {
		SINGLE, ROW, PAGE, BORDER
	}
	enum Direction {
		BACKWARD, FORWARD;
	}
	void goAsync(Direction direction, Unit step);

	void canGoAsync(Direction direction, Unit step, AsyncCallback<Boolean> callback);
	
	void getPageCountAsync(String tagId, int pageSize,
			AsyncCallback<Integer> callback);
	
	

	void getPageAsync(String tagId, int pageSize, int pageNumber,
			AsyncCallback<List<PhotoInfo>> callback);

	void getPageAsync(String tagId, String photoId, int pageSize,
			AsyncCallback<List<PhotoInfo>> callback);

	void getPageRelativePositionAsync(String tagId, String photoId, int pageSize,
			AsyncCallback<Integer[]> callback);

	void toggleZoomViewAsync(String tagId, String photoId);
	
	void goToTag(String otherTagId, PhotoInfoStore store);
	
	void goToLatestTag();

	void setRasterWidth(int width);

	void setRasterHeight(int height);
	
	void increaseRasterWidth(int amount);
	
	void increaseRasterHeight(int amount);

	void toggleRasterView();
	
	void setRasterDimension(int i, int j);

	void resetRasterSize();
	
	void fullscreen();
	
	void slideshow();
	
	enum Zoom {
		IN, OUT;
	}
	
	void zoom(Zoom direction);

	void unslideshow();	
}
