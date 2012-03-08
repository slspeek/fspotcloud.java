package com.googlecode.fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.fspotcloud.client.main.api.Initializable;

public interface UserButtonView extends IsWidget {

	interface UserButtonPresenter extends Initializable {
		void buttonClicked();
		Widget getView();
	}

	void setCaption(String caption);

	void setPresenter(UserButtonPresenter presenter);
	
	void setDebugId(String id);

	void setTooltip(String tooltip);

}
