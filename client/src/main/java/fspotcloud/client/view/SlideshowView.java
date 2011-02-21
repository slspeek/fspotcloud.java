package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.view.PagerView.PagerPresenter;

public interface SlideshowView extends IsWidget {
	
	interface SlideshowPresenter extends Activity {
		void setPresenter(PagerPresenter presenter);
		void slower();
		void start();
		void stop();
		void faster();
	}
	
	void setLabelText(String text);
	void setSlideshowPresenter(SlideshowPresenter presenter);
}
