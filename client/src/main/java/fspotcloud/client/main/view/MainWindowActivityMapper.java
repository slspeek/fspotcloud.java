package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.TagPresenterFactory;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

public class MainWindowActivityMapper implements ActivityMapper {
	private static final Logger log = Logger
			.getLogger(MainWindowActivityMapper.class.getName());

	final private ImagePanelActivityFactory imagePanelActivityFactory;
	final private TagPresenterFactory tagPresenterFactory;

	@Inject
	public MainWindowActivityMapper(TagPresenterFactory tagPresenterFactory,
			ImagePanelActivityFactory imagePanelPresenterFactory) {
		super();
		this.tagPresenterFactory = tagPresenterFactory;
		this.imagePanelActivityFactory = imagePanelPresenterFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = null;
		log.info("getActivity : " + place);
		if (place instanceof ImageViewingPlace) {
			activity = imagePanelActivityFactory.getFullscreen((BasePlace) place);
		} else if (place instanceof TagViewingPlace) {
			activity = tagPresenterFactory.get((BasePlace) place);
		} else {
			log.warning("getActivity will return null for place: " + place);
		}
		return activity;
	}
}
