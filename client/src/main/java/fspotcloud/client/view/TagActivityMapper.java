package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class TagActivityMapper  implements ActivityMapper {
	
	private ImageView.ImagePresenter imageActivity;
	private static final Logger log = Logger.getLogger(TagActivityMapper.class
			.getName());

	@Inject
	public TagActivityMapper(@Named("embedded") ImageView.ImagePresenter imageActivity) {
		super();
		this.imageActivity = imageActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof TagViewingPlace) {
			place = new ImageViewingPlace(((TagViewingPlace) place).getTagId(), ((TagViewingPlace) place).getPhotoId());
		}
		if (place instanceof ImageViewingPlace) {
			imageActivity.setPlace((ImageViewingPlace)place);
			return imageActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
