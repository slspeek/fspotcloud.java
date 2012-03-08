package com.googlecode.fspotcloud.client.main.event.navigation;

import com.google.inject.Provider;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;

public class NavigationEventProvider implements Provider<UserEvent<? extends UserEventHandler>> {

	private final ActionDef action;
	
	public NavigationEventProvider(ActionDef action) {
		this.action = action;
	}
	@Override
	public UserEvent<? extends UserEventHandler> get() {
		return new NavigationEvent(action);
	}

}
