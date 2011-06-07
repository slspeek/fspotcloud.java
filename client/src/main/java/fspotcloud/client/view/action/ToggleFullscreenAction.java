package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.main.PlaceSwapper;
import fspotcloud.client.place.PlaceGoTo;
import fspotcloud.client.place.PlaceWhere;

public class ToggleFullscreenAction implements GestureAction {
	
	private static final Logger log = Logger
	.getLogger(ToggleFullscreenAction.class.getName());
	
	final private PlaceGoTo goTo;
	final private PlaceWhere where;
	final private PlaceSwapper swapper;
	
	@Inject
	public ToggleFullscreenAction(PlaceGoTo goTo, PlaceWhere where, PlaceSwapper swapper) {
		this.goTo = goTo;
		this.where = where;
		this.swapper = swapper;
	}

	@Override
	public void perform() {
		Place place = where.where();
		Place target = swapper.swap(place);
		goTo.goTo(target);
	}
}
