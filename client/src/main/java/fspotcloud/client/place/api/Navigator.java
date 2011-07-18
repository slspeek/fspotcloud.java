package fspotcloud.client.place.api;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.place.BasePlace;
import fspotcloud.shared.photo.PhotoInfoStore;

public interface Navigator {
	void goEndAsync(boolean first);

	void goAsync(boolean forward);

	void canGoAsync(boolean forward, AsyncCallback<Boolean> callback);
	
	void getPageCountAsync(String tagId, int pageSize,
			AsyncCallback<Integer> callback);

	void getPageAsync(String tagId, int pageSize, int pageNumber,
			AsyncCallback<List<BasePlace>> callback);

	void getPageAsync(String tagId, String photoId, int pageSize,
			AsyncCallback<List<BasePlace>> callback);

	void toggleZoomViewAsync(String tagId, String photoId);
	
	void toggleShowTagTree();

	void goToTag(String otherTagId, PhotoInfoStore store);
	
	void goToLatestTag();

	void setRasterWidth(int width);

	void setRasterHeight(int height);
	
	void increaseRasterWidth(int amount);
	
	void increaseRasterHeight(int amount);

	void toggleRasterView();

	void setRasterDimension(int i, int j);

	void resetRasterSize();
}
