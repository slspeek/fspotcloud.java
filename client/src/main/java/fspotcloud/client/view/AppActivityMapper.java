package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class AppActivityMapper  implements ActivityMapper {
	
	private TagView.TagPresenter tagActivity;
	private ImageView.ImagePresenter imageActivity;
	private static final Logger log = Logger.getLogger(AppActivityMapper.class
			.getName());

	@Inject
	public AppActivityMapper(TagView.TagPresenter tagActivity,
			@Named("fullscreen") ImageView.ImagePresenter imageActivity) {
		super();
		this.tagActivity = tagActivity;
		this.imageActivity = imageActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = null;
		if (place instanceof ImageViewingPlace) {
			imageActivity.setPlace((ImageViewingPlace)place);
			activity = imageActivity;
		} else if (place instanceof TagViewingPlace) {
			tagActivity.setPlace((TagViewingPlace)place);
			activity = tagActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
		}
		return activity;
	}
}
