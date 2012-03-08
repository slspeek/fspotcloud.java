package com.googlecode.fspotcloud.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.place.api.PlaceWhere;

public class PlaceWhereImpl extends PlaceGoToImpl implements PlaceWhere  {

	@Inject
	public PlaceWhereImpl(PlaceController placeController) {
		super(placeController);
	}

	@Override
	public BasePlace where() {
		Place place = placeController.getWhere();
		if (place instanceof BasePlace) {
			BasePlace new_name = (BasePlace) place;
			return new_name;
		}
		throw new IllegalStateException("Not a BasePlace");
	} 
	
}
