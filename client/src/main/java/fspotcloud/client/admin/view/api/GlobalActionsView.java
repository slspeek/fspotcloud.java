package fspotcloud.client.admin.view.api;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface GlobalActionsView extends IsWidget {
	interface GlobalActionsPresenter {
		void init();
		void deleteAllPhotos();
		void deleteAllTags();
		void importTags();
		void update();
	}
	HasText getHeader();
	HasText getPhotoCount();
	HasText getPhotoCountValue();
	HasText getPhotoCountOnPeer();
	HasText getPhotoCountOnPeerValue();
	
	HasText getLastSeenPeer();
	HasText getLastSeenPeerValue();
	
	HasText getDeleteAllTagsDescription();
	HasEnabled getDeleteTagsAllButton();
	HasText getDeleteAllPhotosDescription();
	HasEnabled getDeleteAllPhotosButton();
	
	HasText getImportTagsDescription();
	HasEnabled getImportTagsButton();
	HasText getUpdateButtonDescription();
	HasEnabled getUpdateButton();
}