package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.view.ImageView.ImagePresenter;
import fspotcloud.client.view.TagView.TagPresenter;

public class MainWindowActivityMapper  implements ActivityMapper {
	
	private TagPresenter tagActivity;
	private ImagePresenter imageActivity;
	private static final Logger log = Logger.getLogger(MainWindowActivityMapper.class
			.getName());

	@Inject
	public MainWindowActivityMapper(TagPresenter tagActivity,
			@Named("fullscreen") ImagePresenter imageActivity) {
		super();
		this.tagActivity = tagActivity;
		this.imageActivity = imageActivity;
	}

	public void init() {
		tagActivity.init();
		imageActivity.init();
	}
	@Override
	public Activity getActivity(Place place) {
		Activity activity = null;
		log.info("getActivity : " + place);
		if (place instanceof ImageViewingPlace) {
			imageActivity.setPlace((BasePlace) place);
			activity = imageActivity;
		} else if (place instanceof TagViewingPlace) {
			tagActivity.setPlace((BasePlace) place);
			activity = tagActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
		}
		return activity;
	}
}
