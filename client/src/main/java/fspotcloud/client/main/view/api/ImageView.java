package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	interface ImagePresenter {
		void init();
		void imageClicked();
	}

	void setImageUrl(String url);
	void setPresenter(ImagePresenter presenter);
	
}
