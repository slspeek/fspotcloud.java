package fspotcloud.client.main.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.main.view.api.PagerView;
import fspotcloud.client.main.view.api.SlideshowView;

public interface ImagePanelView extends IsWidget {

	interface ImagePanelPresenter extends Activity {
		void init();
	}

	ImageView getImageView();

	PagerView getPagerView();

	SlideshowView getSlideshowView();
	
	void setSize(int width, int height);
}
