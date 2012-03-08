package com.googlecode.fspotcloud.client.main.view.api;

import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import com.googlecode.fspotcloud.client.main.api.Initializable;

public interface ImageRasterView extends IsWidget {
	interface ImageRasterPresenter extends Initializable, Activity {
		
		public void onMouseWheelNorth();

		public void onMouseWheelSouth();
	}

	List<ImageView> buildRaster(int rowCount, int columnCount);

	void setPresenter(ImageRasterPresenter presenter);

	void setPagingText(String text);
}
