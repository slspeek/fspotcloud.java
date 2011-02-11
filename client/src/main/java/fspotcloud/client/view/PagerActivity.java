package fspotcloud.client.view;

import com.google.inject.Inject;

import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;

public class PagerActivity implements PagerPresenter {

	private PhotoInfoStore store = null;
	final protected PlaceGoTo placeGoTo;
	String photoId;
	String tagId;
	Integer offset = null;
	boolean fullscreenTarget = false;
	private Slideshow slideshow;
	
	@Inject
	public PagerActivity(PlaceGoTo placeGoTo, Slideshow slideshow) {
		this.placeGoTo = placeGoTo;
		this.slideshow = slideshow;
	}

	@Override
	public void setData(PhotoInfoStore data) {
		this.store = data;
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
