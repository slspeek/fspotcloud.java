package com.googlecode.fspotcloud.client.admin.view.api;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

public interface DashboardView extends IsWidget{

	interface DashboardPresenter {
		DashboardView getView();
		void init();
	}
	
	GlobalActionsView getGlobalActionsView();
	HasOneWidget getTagDetailsContainer();
	
}
