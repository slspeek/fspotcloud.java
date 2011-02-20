package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	void setPresenter(ImagePresenter presenter);

	interface ImagePresenter extends Activity {
		void setPlace(BasePlace place);
	}

	void setImageUrl(String url);

	AcceptsOneWidget getPagerViewContainer();

	AcceptsOneWidget getSlideshowViewContainer();
}
