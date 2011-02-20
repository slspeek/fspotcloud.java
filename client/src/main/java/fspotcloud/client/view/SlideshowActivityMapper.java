package fspotcloud.client.view;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.view.SlideshowView.SlideshowPresenter;

public class SlideshowActivityMapper implements ActivityMapper {

	final private SlideshowPresenter activity;

	@Inject
	public SlideshowActivityMapper(SlideshowPresenter activity) {
		this.activity = activity;
	}

	@Override
	public Activity getActivity(Place place) {
		return activity;
	}

}
