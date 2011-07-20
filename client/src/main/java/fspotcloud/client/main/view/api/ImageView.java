package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	interface ImagePresenter {
		void init();
		void setMaxWidth(int width);
		void setMaxHeight(int height);
		void imageClicked();
	}

	void setImageUrl(String url);

	void setPresenter(ImagePresenter presenter);

	void setMaxWidth(int width);

	void setMaxHeight(int height);
}
