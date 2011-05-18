package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;

public class ImagePanelViewActivityMapper  implements ActivityMapper {
	private static final Logger log = Logger.getLogger(ImagePanelViewActivityMapper.class
			.getName());

	final private ImagePanelActivityFactory imagePanelPresenterFactory;
	
	@Inject
	public ImagePanelViewActivityMapper(ImagePanelActivityFactory imagePanelPresenterFactory) {
		this.imagePanelPresenterFactory = imagePanelPresenterFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		log.info("getActivity for : " + place + "(" + this +")");
		if (place instanceof BasePlace) {
			ImagePanelPresenter activity = imagePanelPresenterFactory.getEmbedded((BasePlace) place);
			return activity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
