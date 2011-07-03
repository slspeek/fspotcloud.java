package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
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
	final private Navigator navigator;

	@Inject
	public MainWindowActivityMapper(TagPresenterFactory tagPresenterFactory,
			ImagePanelActivityFactory imagePanelPresenterFactory,
			Navigator navigator) {
		super();
		this.tagPresenterFactory = tagPresenterFactory;
		this.imagePanelActivityFactory = imagePanelPresenterFactory;
		this.navigator = navigator;
	}

	@Override
	public Activity getActivity(Place place) {
		storeCurrentRasterDimension(place);
		if (place instanceof BasePlace) {
			BasePlace basePlace = (BasePlace) place;
			if (basePlace.getTagId().equals("latest")) {
				navigator.goToLatestTag();
			}
		}
		Activity activity = null;
		log.info("getActivity : " + place);
		if (place instanceof ImageViewingPlace) {
			activity = imagePanelActivityFactory
					.getFullscreen((BasePlace) place);
		} else if (place instanceof TagViewingPlace) {
			activity = tagPresenterFactory.get((BasePlace) place);
		} else {
			log.warning("getActivity will return null for place: " + place);
		}
		return activity;
	}

	private void storeCurrentRasterDimension(Place place) {
		if (place instanceof BasePlace) {
			BasePlace basePlace = (BasePlace) place;
			int width = basePlace.getColumnCount();
			int height = basePlace.getRowCount();
			if (height * width > 1) {
				navigator.setRasterWidth(width);
				navigator.setRasterHeight(height);
			}
		}
	}
}
