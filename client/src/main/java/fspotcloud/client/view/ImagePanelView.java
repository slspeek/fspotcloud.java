package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

public interface ImagePanelView extends IsWidget {

	interface ImagePanelPresenter extends Activity {
		void init();
	}

	ImageView getImageView();
	PagerView getPagerView();
	SlideshowView getSlideshowView();
}
