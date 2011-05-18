package fspotcloud.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	interface ImagePresenter {
		void init();
	}

	void setImageUrl(String url);
	
}
