package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.view.ImageView.ImagePresenter;

public class ImageViewActivityMapper  implements ActivityMapper {
	
	final private ImagePresenterFactory imagePresenterFactory;
	private static final Logger log = Logger.getLogger(ImageViewActivityMapper.class
			.getName());

	@Inject
	public ImageViewActivityMapper(ImagePresenterFactory imagePresenterFactory) {
		this.imagePresenterFactory = imagePresenterFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof BasePlace) {
			ImagePresenter activity = imagePresenterFactory.get((BasePlace) place);
			activity.init();
			return activity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
