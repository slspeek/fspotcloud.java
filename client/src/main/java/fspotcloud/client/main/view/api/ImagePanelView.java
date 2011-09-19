package fspotcloud.client.main.view.api;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.api.Initializable;

public interface ImagePanelView extends IsWidget {

	interface ImagePanelPresenter extends Activity, Initializable {
		
		public void onMouseWheelNorth();

		public void onMouseWheelSouth();
	}

	ImageRasterView getImageRasterView();

	ButtonPanelView getButtonPanelView();
	
	void setPresenter(ImagePanelPresenter presenter);
}
