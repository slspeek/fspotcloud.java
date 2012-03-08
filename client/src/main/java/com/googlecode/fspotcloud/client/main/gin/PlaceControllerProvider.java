package com.googlecode.fspotcloud.client.main.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PlaceControllerProvider implements Provider<PlaceController> {

	final private PlaceController placeController;
	
	@Inject
	public PlaceControllerProvider(EventBus eventBus) {
		this.placeController = new PlaceController(eventBus);
	}
	
	@Override
	public PlaceController get() {
		return placeController;
	}

}
