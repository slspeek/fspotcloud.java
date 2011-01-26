package fspotcloud.client.view;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.shared.tag.TagNode;

public class ImageActivity extends AbstractActivity implements
		ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImageActivity.class
			.getName());

	final DataManager dataManager;
	final private PlaceController placeController;
	private ImageView imageView;

	String tagId;
	String photoId;
	Integer offset = null;
	List<String> photoList = null;

	private boolean slideshowRunning = false;
	private Timer slideshowTimer = new Timer() {
		public void run() {
			log.info("Slideshow timer about to call goForward");
			goForward();
		}
	};

	@Inject
	public ImageActivity(ImageView imageView, DataManager dataManager,
			PlaceController placeController) {
		this.imageView = imageView;
		this.dataManager = dataManager;
		this.placeController = placeController;
	}

	public void setPlace(ImageViewingPlace place) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		log.info("setPlace called for tagId: " + tagId + " photoId: " + photoId);
		TagNode node = dataManager.getTagNode(tagId);
		if (node != null) {
			photoList = node.getCachedPhotoList();
		} else {
			photoList = Collections.emptyList();
			log.warning("No information found for tagId: " + tagId);
		}
		int where = photoList.indexOf(photoId);
		if (where == -1) {
			if (!photoList.isEmpty()) {
				photoId = photoList.get(0);
				offset = 0;
			} else {
				offset = -1;
			}
		} else {
			offset = where;
		}
		if (photoId != null) {
			imageView.setImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start image activity for tagId: " + tagId + "photoId: " + photoId);
		imageView.setPresenter(this);
		containerWidget.setWidget(imageView);
	}

	@Override
	public boolean canGoBackward() {
		return offset > 0;
	}

	@Override
	public boolean canGoForward() {
		return offset >= 0 && offset < photoList.size() - 1;
	}

	protected void goToPhoto(String otherTagId, String photoId) {
		placeController.goTo(new ImageViewingPlace(otherTagId, photoId));
	}

	private void goToPhoto(String photoId) {
		goToPhoto(tagId, photoId);
	}

	@Override
	public void goBackward() {
		if (!photoList.isEmpty() && canGoBackward()) {
			String photoId = photoList.get(offset - 1);
			goToPhoto(photoId);
		}
	}

	@Override
	public void goFirst() {
		if (!photoList.isEmpty()) {
			String photoId = photoList.get(0);
			goToPhoto(photoId);
		}
	}

	@Override
	public void goForward() {
		if (!photoList.isEmpty() && canGoForward()) {
			String photoId = photoList.get(offset + 1);
			goToPhoto(photoId);
		}
	}

	@Override
	public void goLast() {
		if (!photoList.isEmpty()) {
			String photoId = photoList.get(photoList.size() - 1);
			goToPhoto(photoId);
		}
	}

	@Override
	public void toggleSlideshow() {
		if (slideshowRunning) {
			imageView.setSlideshowButtonCaption("Start");
			slideshowTimer.cancel();
		} else {
			imageView.setSlideshowButtonCaption("Stop");
			slideshowTimer.scheduleRepeating(3000);
		}
		slideshowRunning = !slideshowRunning;
	}

	@Override
	public void setView(ImageView imageView) {
		this.imageView = imageView;
	}
}
