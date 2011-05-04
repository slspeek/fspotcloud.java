package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class MainWindowActivityMapper implements ActivityMapper {
	private static final Logger log = Logger
			.getLogger(MainWindowActivityMapper.class.getName());

	final private ImagePresenterFactory imagePresenterFactory;
	final private TagPresenterFactory tagPresenterFactory;

	@Inject
	public MainWindowActivityMapper(TagPresenterFactory tagPresenterFactory,
			ImagePresenterFactory imagePresenterFactory) {
		super();
		this.tagPresenterFactory = tagPresenterFactory;
		this.imagePresenterFactory = imagePresenterFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = null;
		log.info("getActivity : " + place);
		if (place instanceof ImageViewingPlace) {
			activity = imagePresenterFactory.get((BasePlace) place);
		} else if (place instanceof TagViewingPlace) {
			activity = tagPresenterFactory.get((BasePlace) place);
		} else {
			log.warning("getActivity will return null for place: " + place);
		}
		return activity;
	}
}
