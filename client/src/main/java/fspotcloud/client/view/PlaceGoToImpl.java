package fspotcloud.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class PlaceGoToImpl implements PlaceGoTo {

	private final PlaceController placeController;
	
	@Inject 
	public PlaceGoToImpl(PlaceController placeController) {
		this.placeController = placeController;
	}
	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

}
