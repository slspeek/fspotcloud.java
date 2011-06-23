package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.PlaceSwapper;
import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.PlaceGoTo;
import fspotcloud.client.place.PlaceWhere;

public class ZoomViewEventHandler implements ZoomViewEvent.Handler {

	final private PlaceSwapper placeSwapper;
	final private PlaceWhere placeWhere;
	final private PlaceGoTo placeGoTo;
	final private EventBus eventBus;

	@Inject
	public ZoomViewEventHandler(PlaceSwapper swapper, PlaceWhere where, PlaceGoTo placeGoTo, EventBus eventBus) {
		this.placeSwapper = swapper;
		this.placeGoTo = placeGoTo;
		this.eventBus = eventBus;
		this.placeWhere = where;
	}

	
	public void init() {
		eventBus.addHandler(ZoomViewEvent.TYPE, this);
	}


	@Override
	public void onEvent(ZoomViewEvent e) {
		BasePlace newPlace = placeSwapper.toggleZoomView(placeWhere.where(), e);
		placeGoTo.goTo(newPlace);
	}
}
