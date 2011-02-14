package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;

public class PagerActivity implements PagerPresenter {

	final private static Logger log = Logger.getLogger(PagerActivity.class
			.getName());

	final private Slideshow slideshow;
	final protected PlaceGoTo placeGoTo;
	
	private PhotoInfoStore store = null;
	
	String photoId;
	String tagId;
	Integer offset = null;
	boolean fullscreenTarget = false;
	
	@Inject
	public PagerActivity(PlaceGoTo placeGoTo,
			Slideshow slideshow) {
		log.info("Pager activity created : " + this);
		this.placeGoTo = placeGoTo;
		this.slideshow = slideshow;
	}

	@Override
	public void setData(PhotoInfoStore data) {
		this.store = data;
		//log.info("Set store to: " + store);

	}

	@Override
	public void setPlace(BasePlace place) {
		if (place instanceof ImageViewingPlace) {
			fullscreenTarget = true;
		} else {
			fullscreenTarget = false;
		}
		this.photoId = place.getPhotoId();
		this.tagId = place.getTagId();
		calculateLocation();
		log.info("setPlace" + this + " : " + place );
	}

	private void calculateLocation() {
		offset = store.indexOf(photoId);
	}

	protected void goToPhoto(String tagId, String photoId) {
		BasePlace place;
		if (fullscreenTarget) {
			place = new ImageViewingPlace(tagId, photoId);
		} else {
			place = new TagViewingPlace(tagId, photoId);
		}
		log.info("About to go to: " + this + " : "  + place);
		placeGoTo.goTo(place);
	}

	private void goToPhoto(String photoId) {
		goToPhoto(tagId, photoId);
	}

	@Override
	public void goBackward() {
		if (!store.isEmpty() && canGoBackward()) {
			String photoId = store.get(offset - 1).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goFirst() {
		if (!store.isEmpty()) {
			String photoId = store.get(0).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goForward() {
		if (!store.isEmpty() && canGoForward()) {
			String photoId = store.get(offset + 1).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goLast() {
		if (!store.isEmpty()) {
			String photoId = store.last().getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public boolean canGoBackward() {
		return offset != null && offset > 0;
	}

	@Override
	public boolean canGoForward() {
		return offset != null && offset >= 0 && offset < store.lastIndex();
	}

	@Override
	public void stop() {
		slideshow.stopSlideshow();
	}
}
