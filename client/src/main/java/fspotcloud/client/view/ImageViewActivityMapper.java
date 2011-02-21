package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ImageViewActivityMapper  implements ActivityMapper {
	
	private ImageView.ImagePresenter imageActivity;
	private static final Logger log = Logger.getLogger(ImageViewActivityMapper.class
			.getName());

	@Inject
	public ImageViewActivityMapper(@Named("embedded") ImageView.ImagePresenter imageActivity) {
		super();
		this.imageActivity = imageActivity;
		imageActivity.init();
	}

	@Override
	public Activity getActivity(Place place) {
		log.info("getActivity : " + place + "  (" +this +")");
		if (place instanceof BasePlace) {
			imageActivity.setPlace((BasePlace)place);
			return imageActivity;
		} else {
			log.warning("getActivity will return null for place: " + place);
			return null;
		}
	}
}
