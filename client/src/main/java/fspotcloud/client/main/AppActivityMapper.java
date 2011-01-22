package fspotcloud.client.main;

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
		if (place instanceof TagPlace) {
			tagActivity.setPlace((TagPlace)place);
			return tagActivity;
		} else if (place instanceof ImagePlace) {
			imageActivity.setPlace((ImagePlace)place);
			return imageActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
