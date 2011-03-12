package fspotcloud.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class PlaceWhereImpl extends PlaceGoToImpl implements PlaceWhere  {

	@Inject
	public PlaceWhereImpl(PlaceController placeController) {
		super(placeController);
	}

	@Override
	public Place where() {
		return placeController.getWhere();
	} 
	
}
