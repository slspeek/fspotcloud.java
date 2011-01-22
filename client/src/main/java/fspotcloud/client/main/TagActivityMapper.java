package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class TagActivityMapper  implements ActivityMapper {
	
	private ImageActivity imageActivity;
	private static final Logger log = Logger.getLogger(TagActivityMapper.class
			.getName());

	@Inject
	public TagActivityMapper(ImageActivity imageActivity) {
		super();
		this.imageActivity = imageActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TagPlace) {
			place = new ImagePlace(((TagPlace) place).getTagId(), ((TagPlace) place).getPhotoId());
		}
		if (place instanceof ImagePlace) {
			imageActivity.setPlace((ImagePlace)place);
			return imageActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
