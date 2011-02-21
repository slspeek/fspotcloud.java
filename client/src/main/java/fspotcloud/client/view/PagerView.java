package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.shared.photo.PhotoInfoStore;

public interface PagerView extends IsWidget {
	
	interface PagerPresenter extends Activity {
		void setData(PhotoInfoStore data);
		void setPlace(BasePlace place);
		void goEnd(boolean first);
		void go(boolean forward);
		boolean canGo(boolean forward);
	}

	void setPagerPresenter(PagerPresenter pagerPresenter);
	
	
}
