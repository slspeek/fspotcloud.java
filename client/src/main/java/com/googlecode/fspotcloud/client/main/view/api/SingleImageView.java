package com.googlecode.fspotcloud.client.main.view.api;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import com.googlecode.fspotcloud.client.view.action.KeyStroke;

public interface SingleImageView extends IsWidget {

	interface SingleImagePresenter extends Activity {
			
	}

	ImageRasterView getImageRasterView();

	ButtonPanelView getButtonPanelView();
	
	void hideControlsLater(int visibleDuration);

	void setPresenter(SingleImagePresenter singleImageActivity);
}
