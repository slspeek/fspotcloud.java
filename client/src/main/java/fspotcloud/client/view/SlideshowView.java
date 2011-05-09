package fspotcloud.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface SlideshowView extends IsWidget {

	interface SlideshowPresenter {
		void slower();

		void start();

		void stop();

		void faster();
	}

	void setLabelText(String text);

	void setPresenter(SlideshowPresenter presenter);
}
