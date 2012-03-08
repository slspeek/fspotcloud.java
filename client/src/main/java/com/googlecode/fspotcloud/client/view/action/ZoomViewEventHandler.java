package com.googlecode.fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.api.Initializable;
import com.googlecode.fspotcloud.client.main.shared.ZoomViewEvent;
import com.googlecode.fspotcloud.client.place.api.Navigator;

public class ZoomViewEventHandler implements ZoomViewEvent.Handler,
		Initializable {

	final private Navigator navigator;
	final private EventBus eventBus;

	@Inject
	public ZoomViewEventHandler(Navigator navigator, EventBus eventBus) {
		super();
		this.navigator = navigator;
		this.eventBus = eventBus;
	}

	public void init() {
		eventBus.addHandler(ZoomViewEvent.TYPE, this);
	}

	@Override
	public void onEvent(ZoomViewEvent e) {
		navigator.toggleZoomViewAsync(e.getTagId(), e.getPhotoId());
	}
}
