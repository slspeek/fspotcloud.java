package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.view.PagerView.PagerPresenter;

public interface ImageView extends IsWidget {

	void setPresenter(ImagePresenter presenter);

	interface ImagePresenter extends Activity {
		void setPlace(BasePlace place);
		PagerPresenter getPager();
	}

	void setImageUrl(String url);

	PagerPresenter getPagerPresenter();
}
