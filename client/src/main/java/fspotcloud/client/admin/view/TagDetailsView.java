package fspotcloud.client.admin.view;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface TagDetailsView extends IsWidget {
	
	interface TagDetailsPresenter {
		void importTag();
	}
	HasText getTagName();
	HasText getTagNameValue();
	
	HasText getTagDescription();
	HasText getTagDescriptionValue();
	
	HasText getTagLoadedImagesCount();
	HasText getTagLoadedImagesCountValue();
	
	HasText getTagImageCount();
	HasText getTagImageCountValue();

}
