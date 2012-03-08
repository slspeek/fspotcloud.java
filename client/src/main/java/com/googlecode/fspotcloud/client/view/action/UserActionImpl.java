package com.googlecode.fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.UserEventHandler;
import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public class UserActionImpl implements UserAction {
	private static final Logger log = Logger.getLogger(UserActionImpl.class
			.getName());
	final private String description;
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private ImageResource imageResource;
	final private String caption;
	final private String id;
	final private Provider<? extends UserEvent<? extends UserEventHandler>> eventProvider;

	final private EventBus eventBus;

	@Inject
	public UserActionImpl(
			@Assisted("id") String id,
			@Assisted("caption") String caption,
			@Assisted("description") String description,
			@Assisted("key") KeyStroke key,
			@Assisted("altKey") KeyStroke alternateKey,
			@Assisted ImageResource imageResource,
			@Assisted Provider<? extends UserEvent<? extends UserEventHandler>> eventProvider,
			EventBus eventBus) {
		super();
		this.id = id;
		this.caption = caption;
		this.description = description;
		this.key = key;
		this.alternateKey = alternateKey;
		this.imageResource = imageResource;
		this.eventProvider = eventProvider;
		this.eventBus = eventBus;
	}

	public Provider<? extends UserEvent<? extends UserEventHandler>> getEventProvider() {
		return eventProvider;
	}

	public String getDescription() {
		return description;
	}

	public KeyStroke getKey() {
		return key;
	}

	public KeyStroke getAlternateKey() {
		return alternateKey;
	}

	public ImageResource getIcon() {
		return imageResource;
	}

	@Override
	public void run() {
		GwtEvent event = eventProvider.get();
		eventBus.fireEvent(event);
	}

	@Override
	public String getCaption() {
		return caption;
	}

	public String getId() {
		return id;
	}

}
