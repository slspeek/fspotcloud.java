package fspotcloud.client.view;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.tag.TagNode;

public class ImageActivity extends AbstractActivity implements
		ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImageActivity.class
			.getName());

	final DataManager tagNodeProvider;
	final protected PlaceGoTo placeGoTo;
	private ImageView imageView;

	String tagId;
	String photoId;
	Integer offset = null;
	List<PhotoInfo> photoList = Collections.emptyList();

	boolean slideshowRunning = false;

	private final SlideshowTimer slideShowTimer;

	@Inject
	public ImageActivity(ImageView imageView, DataManager dataManager,
			PlaceGoTo placeGoTo, SlideshowTimer slideShowTimer) {
		this.imageView = imageView;
		this.tagNodeProvider = dataManager;
		this.placeGoTo = placeGoTo;
		this.slideShowTimer = slideShowTimer;
	}

	@Override
	public void onStop() {
		if (slideshowRunning) {
			toggleSlideshow();
		}
		super.onStop();
	}

	public void setPlace(ImageViewingPlace place) {
		offset = null;
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		log.info("setPlace called for tagId: " + tagId + " photoId: " + photoId);
		calculateLocation();
		setImage();
	}

	public void calculateLocation() {
		TagNode node = tagNodeProvider.getTagNode(tagId);
		if (node != null) {
			photoList = node.getCachedPhotoList();
		} else {
			photoList = Collections.emptyList();
			log.warning("No information found for tagId: " + tagId);
		}
		int where = findInList(photoList, photoId);
		if (where == -1) {
			log.info("where=-1");
			if (!photoList.isEmpty()) {
				photoId = photoList.get(0).getId();
				offset = 0;
			} else {
				offset = -1;
			}
		} else {
			offset = where;
		}
		//log.info("end of calculateLocation offset: " + offset);
	}
	/**
	 * Needed for GWT
	 */
	private int findInList(List<PhotoInfo> list, String id) {
		int index = -1;
		ListIterator<PhotoInfo> it = list.listIterator();
		while (it.hasNext()) {
			PhotoInfo pi = it.next();
			if (id.equals(pi.getId())) {
				index = it.previousIndex();
				break;
			} 
		} 
		return index;
	}
	
	private void setImage() {
		if (photoId != null) {
			imageView.setImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start image activity for tagId: " + tagId + "photoId: " + photoId);
		slideShowTimer.setImagePresenter(this);
		imageView.setPresenter(this);
		containerWidget.setWidget(imageView);
	}

	@Override
	public boolean canGoBackward() {
		return offset != null && offset > 0;
	}

	@Override
	public boolean canGoForward() {
		return offset != null && offset >= 0 && offset < photoList.size() - 1;
	}

	protected void goToPhoto(String otherTagId, String photoId) {
		placeGoTo.goTo(new ImageViewingPlace(otherTagId, photoId));
	}

	private void goToPhoto(String photoId) {
		goToPhoto(tagId, photoId);
	}

	@Override
	public void goBackward() {
		if (!photoList.isEmpty() && canGoBackward()) {
			String photoId = photoList.get(offset - 1).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goFirst() {
		if (!photoList.isEmpty()) {
			String photoId = photoList.get(0).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goForward() {
		if (!photoList.isEmpty() && canGoForward()) {
			String photoId = photoList.get(offset + 1).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goLast() {
		if (!photoList.isEmpty()) {
			String photoId = photoList.get(photoList.size() - 1).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void toggleSlideshow() {
		if (slideshowRunning) {
			imageView.setSlideshowButtonCaption("Start");
			slideShowTimer.cancel();
		} else {
			imageView.setSlideshowButtonCaption("Stop");
			slideShowTimer.scheduleRepeating(3000);
		}
		slideshowRunning = !slideshowRunning;
	}

	@Override
	public void setView(ImageView imageView) {
		this.imageView = imageView;
	}
}
