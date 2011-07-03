package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.place.BasePlace;

public class ZoomViewEventHandler implements ZoomViewEvent.Handler {

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
