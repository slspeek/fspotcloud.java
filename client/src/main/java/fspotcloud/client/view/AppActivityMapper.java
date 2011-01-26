package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class AppActivityMapper  implements ActivityMapper {
	
	private TagActivity tagActivity;
	private ImageActivity imageActivity;
	private static final Logger log = Logger.getLogger(AppActivityMapper.class
			.getName());

	@Inject
	public AppActivityMapper(TagActivity tagActivity, ImageActivity imageActivity) {
		super();
		this.tagActivity = tagActivity;
		this.imageActivity = imageActivity;
		tagActivity.reloadTree();
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof ImageViewingPlace) {
			imageActivity.setPlace((ImageViewingPlace)place);
			return imageActivity;
		} else if (place instanceof TagViewingPlace) {
			tagActivity.setPlace((TagViewingPlace)place);
			return tagActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
