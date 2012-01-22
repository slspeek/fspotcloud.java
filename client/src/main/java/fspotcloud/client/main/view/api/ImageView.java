package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;

import fspotcloud.client.main.api.Initializable;

public interface ImageView extends IsWidget {

	interface ImagePresenter extends Initializable {
		void imageClicked();

		void setSelected(boolean selected);
	}

	void setSelected(boolean selected);
	
	void setImageUrl(String url);

	void setPresenter(ImagePresenter presenter);

	void setDescription(String date);
	
	void hideLabelLater(int visibleDuration);
	
	void adjustSize();
}
