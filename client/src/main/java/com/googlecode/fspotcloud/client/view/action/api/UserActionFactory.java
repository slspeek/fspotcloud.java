package com.googlecode.fspotcloud.client.view.action.api;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;
import com.googlecode.fspotcloud.client.view.action.KeyStroke;

public interface UserActionFactory {
	UserAction get(@Assisted("id") String id,
			@Assisted("caption") String caption,
			@Assisted("description") String description,
			@Assisted("key") KeyStroke key,
			@Assisted("altKey") KeyStroke alternateKey,
			@Assisted ImageResource imageResource,
			@Assisted Provider<? extends UserEvent<? extends UserEventHandler>> eventProvider);
}
