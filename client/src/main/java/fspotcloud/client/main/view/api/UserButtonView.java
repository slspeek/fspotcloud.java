package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface UserButtonView extends IsWidget {

	interface UserButtonPresenter {
		void buttonClicked();

		void init();

		Widget getView();
	}

	void setCaption(String caption);

	void setPresenter(UserButtonPresenter presenter);
	
	void setDebugId(String id);

}
