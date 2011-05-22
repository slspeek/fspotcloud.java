package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

public interface SlideshowView extends IsWidget {

	interface SlideshowPresenter {
		void init();
		
		void slower();

		void start();

		void stop();

		void faster();
		
		void cleanup();
	}

	void setLabelText(String text);

	void setPresenter(SlideshowPresenter presenter);
}
