package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.main.PlaceCalculator;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.PlaceGoTo;
import fspotcloud.client.place.PlaceWhere;

public class ToggleFullscreenAction implements GestureAction {
	
	private static final Logger log = Logger
	.getLogger(ToggleFullscreenAction.class.getName());
	
	final private PlaceGoTo goTo;
	final private PlaceWhere where;
	final private PlaceCalculator swapper;
	
	@Inject
	public ToggleFullscreenAction(PlaceGoTo goTo, PlaceWhere where, PlaceCalculator swapper) {
		this.goTo = goTo;
		this.where = where;
		this.swapper = swapper;
	}

	@Override
	public void perform() {
		BasePlace place = (BasePlace) where.where();
		Place target = swapper.toggleTreeViewVisible(place);
		goTo.goTo(target);
	}
}
