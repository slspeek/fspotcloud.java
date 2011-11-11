package fspotcloud.client.admin.view.api;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface GlobalActionsView extends IsWidget {
	interface GlobalActionsPresenter {
		void init();

		void countPhotos();

		void deleteAllPhotos();

		void deleteAllTags();

		void update();
	}

	void setPresenter(GlobalActionsPresenter presenter);

	HasText getTagCountValue();

	HasText getPhotoCountValue();

	HasText getPhotoCountOnPeerValue();

	HasText getPendingCommandCountValue();

	HasText getLastSeenPeerValue();

	HasEnabled getDeleteAllTagsButton();

	HasEnabled getDeleteAllPhotosButton();

	HasEnabled getUpdateButton();

	HasEnabled getCountPhotosButton();

}
