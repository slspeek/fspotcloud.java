package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.shared.photo.PhotoInfoStore;

public interface PagerView extends IsWidget {
	
	interface PagerPresenter extends Activity {
		void setData(PhotoInfoStore data);
		void setPlace(BasePlace place);
		void goLast();
		void goFirst();
		void goForward();
		void goBackward();
		boolean canGoForward();
		boolean canGoBackward();
	}

	PagerPresenter getPagerPresenter();
	
	
}
