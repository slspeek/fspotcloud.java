package fspotcloud.client.admin.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface TagDetailsView extends IsWidget {
	
	interface TagDetailsPresenter {
		void importTag();
	}
	void setTagName();
	void setTagDescription();
	void setLoadedImagesCount();
	void setImageCount();
}
