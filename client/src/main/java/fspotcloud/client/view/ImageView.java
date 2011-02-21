package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	interface ImagePresenter extends Activity {
		void setPlace(BasePlace place);
		void init();
	}

	void setImageUrl(String url);

	HasOneWidget getPagerViewContainer();

	HasOneWidget getSlideshowViewContainer();
}
