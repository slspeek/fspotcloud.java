package fspotcloud.client.main;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper  implements ActivityMapper {
	private ClientFactory clientFactory;
	private TagActivity tagActivity;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
		this.tagActivity = new TagActivity(clientFactory);
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
