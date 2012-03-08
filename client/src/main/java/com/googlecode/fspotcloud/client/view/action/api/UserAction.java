package com.googlecode.fspotcloud.client.view.action.api;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;

public interface UserAction extends Runnable, ActionDef {
	
	ImageResource getIcon();
	
	Provider<? extends UserEvent<? extends UserEventHandler>> getEventProvider();
	
}
