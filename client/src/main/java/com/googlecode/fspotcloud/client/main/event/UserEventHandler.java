package com.googlecode.fspotcloud.client.main.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserEventHandler extends EventHandler{

	void onEvent(UserEvent<? extends UserEventHandler> event);
}
