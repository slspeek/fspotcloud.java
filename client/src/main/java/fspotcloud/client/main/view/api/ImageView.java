package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.api.Initializable;

public interface ImageView extends IsWidget {

	interface ImagePresenter extends Initializable {
		void setMaxWidth(int width);

		void setMaxHeight(int height);

		void imageClicked();

		void imageDoubleClicked();
	}

	void setImageUrl(String url);

	void setPresenter(ImagePresenter presenter);

	void setMaxWidth(int width);

	void setMaxHeight(int height);

	void setTooltip(String date);
}
