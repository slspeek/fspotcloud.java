package fspotcloud.client.main.view.api;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

public interface ImagePanelView extends IsWidget {

	interface ImagePanelPresenter extends Activity {
		void init();
	}

	ImageRasterView getImageRasterView();

	PagerView getPagerView();

	SlideshowView getSlideshowView();

	ButtonPanelView getButtonPanelView();
}
