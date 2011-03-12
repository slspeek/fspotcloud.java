package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ImageActivity extends AbstractActivity implements
		ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImageActivity.class
			.getName());
	
	final private ImageView imageView;
	final String tagId;
	final String photoId;

	@Inject
	public ImageActivity(BasePlace place, ImageView imageView) {
		
		this.imageView = imageView;
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		log.info("ImageActivity created: " + this+ " " + tagId + " " + photoId);
	}

	public void init() {
		setImage();
	}
	
	public void setImage() {
		if (photoId != null) {
			imageView.setImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start image activity for tagId: " + tagId + " photoId: "
				+ photoId + " (" + this+")");
		containerWidget.setWidget(imageView);
	}
}
