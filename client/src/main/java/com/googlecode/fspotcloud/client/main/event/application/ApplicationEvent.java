package com.googlecode.fspotcloud.client.main.event.application;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;

public class ApplicationEvent extends UserEvent<ApplicationEvent.Handler> {

	final public static Type<ApplicationEvent.Handler> TYPE = new Type<ApplicationEvent.Handler>();

	@Inject
	public ApplicationEvent(@Assisted ActionDef actionType) {
		super(actionType);
	}

	public static interface Handler extends UserEventHandler {
	}

	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}
}