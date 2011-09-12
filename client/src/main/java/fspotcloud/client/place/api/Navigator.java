package fspotcloud.client.place.api;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;

public interface Navigator {
	void goEndAsync(boolean first);

	void goAsync(boolean forward);

	void canGoAsync(boolean forward, AsyncCallback<Boolean> callback);
	
	void getPageCountAsync(String tagId, int pageSize,
			AsyncCallback<Integer> callback);

	void getPageAsync(String tagId, int pageSize, int pageNumber,
			AsyncCallback<List<PhotoInfo>> callback);

	void getPageAsync(String tagId, String photoId, int pageSize,
			AsyncCallback<List<PhotoInfo>> callback);

	void toggleZoomViewAsync(String tagId, String photoId);
	
	void toggleShowTagTree();

	void goToTag(String otherTagId, PhotoInfoStore store);
	
	void goToLatestTag();

	void setRasterWidth(int width);

	void setRasterHeight(int height);
	
	void increaseRasterWidth(int amount);
	
	void increaseRasterHeight(int amount);

	void toggleRasterView();
	
	void toggleButtonsVisible();

	void setRasterDimension(int i, int j);

	void resetRasterSize();
	
	void fullscreen();
	
	void setButtonsVisible(boolean visible);
	
	void setTreeVisible(boolean visible);
	
	enum Zoom {
		IN, OUT;
	}
	
	void zoom(Zoom direction);
	
}
