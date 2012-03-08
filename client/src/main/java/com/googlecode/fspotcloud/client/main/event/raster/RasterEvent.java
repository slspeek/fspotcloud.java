package com.googlecode.fspotcloud.client.main.event.raster;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;

public class RasterEvent extends UserEvent<RasterEvent.Handler> {

	final public static Type<RasterEvent.Handler> TYPE = new Type<RasterEvent.Handler>();

	@Inject
	public RasterEvent(@Assisted ActionDef actionType) {
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