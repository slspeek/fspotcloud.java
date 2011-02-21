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
	final private ActivityMapper pagerActivityMapper;
	final private ActivityMapper slideshowActivityMapper;
	String tagId;
	String photoId;

	final private EventBus eventBus;

	@Inject
	public ImageActivity(ImageView imageView, PagerActivityMapper pagerActivityMapper,
			SlideshowActivityMapper slideshowActivityMapper, EventBus eventBus) {
		log.info("ImageActivity created: " + this);
		this.eventBus = eventBus;
		this.imageView = imageView;
		this.pagerActivityMapper = pagerActivityMapper;
		this.slideshowActivityMapper = slideshowActivityMapper; 
	}

	public void init() {
		initActivityManagers(eventBus);
	}
	public void setPlace(BasePlace place) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		setImage();
	}

	private void setImage() {
		if (photoId != null) {
			imageView.setImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}
	private void initActivityManagers(EventBus eventBus) {
		ActivityManager activityManager = new ActivityManager(
				pagerActivityMapper, eventBus);
		activityManager.setDisplay(imageView.getPagerViewContainer());
		activityManager = new ActivityManager(slideshowActivityMapper, eventBus);
		activityManager.setDisplay(imageView.getSlideshowViewContainer());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start image activity for tagId: " + tagId + " photoId: "
				+ photoId + " (" + this+")");
		//initActivityManagers(eventBus);
		containerWidget.setWidget(imageView);
	}
}
