package com.googlecode.fspotcloud.client.main.event.application;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.event.AbstractActionMap;
import com.googlecode.fspotcloud.client.main.ui.Resources;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;
import com.googlecode.fspotcloud.client.view.action.api.UserActionFactory;

public class ApplicationMapBuilder extends AbstractActionMap {

	private Resources resources;

	@Inject
	public ApplicationMapBuilder(UserActionFactory userActionFactory,
			Resources resources) {
		super(userActionFactory, "Application");
		this.resources = resources;
	}

	public void buildMap() {
		put(ApplicationType.HIDE_CONTROLS, resources.hideControlsIcon());
		put(ApplicationType.TOGGLE_HELP, resources.helpIcon());
		put(ApplicationType.START_DEMO, resources.demoIcon());
		put(ApplicationType.TREE_FOCUS, resources.treeFocusIcon());
		put(ApplicationType.ZOOM_IN, resources.zoomInIcon());
		put(ApplicationType.ZOOM_OUT, resources.zoomOutIcon());
		put(ApplicationType.DASHBOARD, resources.dashboardIcon());
		put(ApplicationType.ABOUT, resources.aboutIcon());
                put(ApplicationType.LOGIN, resources.loginIcon());
                put(ApplicationType.LOGOUT, resources.logoutIcon());
	}

	private void put(ActionDef actionDef, ImageResource icon) {
		put(actionDef, icon, new ApplicationEventProvider(actionDef));
	}
}
