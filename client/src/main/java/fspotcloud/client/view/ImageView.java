package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	void setPresenter(ImagePresenter presenter);
	
	interface ImagePresenter extends Activity {
		void setPlace(ImageViewingPlace place);
		void goLast();
		void goFirst();
		void goForward();
		void goBackward();
		boolean canGoForward();
		boolean canGoBackward();
		void toggleSlideshow();
	}

	void setImageUrl(String url);

	void setSlideshowButtonCaption(String caption);
	
}
