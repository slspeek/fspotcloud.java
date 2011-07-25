package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.api.Initializable;

public interface SlideshowView extends IsWidget {

	interface SlideshowPresenter extends Initializable {
		SlideshowView getView();
	}

	void setLabelText(String text);
}
