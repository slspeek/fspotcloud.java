package fspotcloud.client.admin.view.api;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface GlobalActionsView extends IsWidget {
	interface GlobalActionsPresenter {
		void init();

		void deleteAllCommands();

		void deleteAllTags();

		void update();
	}

	void setPresenter(GlobalActionsPresenter presenter);

	HasText getTagCountValue();

	HasText getPhotoCountOnPeerValue();

	HasText getPendingCommandCountValue();

	HasText getLastSeenPeerValue();

	HasEnabled getDeleteAllTagsButton();

	HasEnabled getDeleteAllCommandsButton();

	HasEnabled getUpdateButton();

}
