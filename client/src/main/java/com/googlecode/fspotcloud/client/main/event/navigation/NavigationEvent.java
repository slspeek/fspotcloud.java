package com.googlecode.fspotcloud.client.main.event.navigation;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;

public class NavigationEvent extends UserEvent<NavigationEvent.Handler> {

	final public static Type<NavigationEvent.Handler> TYPE = new Type<NavigationEvent.Handler>();

	public static interface Handler extends UserEventHandler {
		
	}

	@Inject
	public NavigationEvent(@Assisted ActionDef actionType) {
			super(actionType);
	}


	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}
}