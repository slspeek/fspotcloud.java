package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

public interface SlideshowView extends IsWidget {

	interface SlideshowPresenter {
		void init();

		SlideshowView getView();
	}

	void setLabelText(String text);
}
