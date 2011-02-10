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

	final private DataManager tagNodeProvider;
	final private ImageView imageView;
	final private Slideshow slideShowTimer;
	final protected PlaceGoTo placeGoTo;
	
	String tagId;
	String photoId;
	Integer offset = null;
	List<PhotoInfo> photoList = Collections.emptyList();

	@Inject
	public ImageActivity(ImageView imageView, DataManager dataManager,
			PlaceGoTo placeGoTo, Slideshow slideShowTimer) {
		this.imageView = imageView;
		this.tagNodeProvider = dataManager;
		this.placeGoTo = placeGoTo;
		this.slideShowTimer = slideShowTimer;
	}

	public void setPlace(ImageViewingPlace place) {
		offset = null;
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		log
				.info("setPlace called for tagId: " + tagId + " photoId: "
						+ photoId);
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
			if (!photoList.isEmpty()) {
				goFirst();
			} else {
				log.info("photo list is empty.");
				offset = -1;
			}
		} else {
			offset = where;
		}
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
		log.info("Start image activity for tagId: " + tagId + "photoId: "
				+ photoId);
		imageView.setPresenter(this);
		slideShowTimer.setPresenter(this);
		containerWidget.setWidget(imageView);
	}

	@Override
	public void onStop() {
		slideShowTimer.stopSlideshow();
		super.onStop();
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
		slideShowTimer.toggleSlideshow();		
	}
}
