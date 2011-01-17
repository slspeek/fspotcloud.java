package fspotcloud.client.main;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class AppActivityMapper  implements ActivityMapper {
	
	private TagActivity tagActivity;

	@Inject
	public AppActivityMapper(TagActivity tagActivity) {
		super();
		this.tagActivity = tagActivity;
		tagActivity.reloadTree();
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TagPlace) {
			tagActivity.setPlace((TagPlace)place);
			return tagActivity;
		}
		return null;
	}
}
