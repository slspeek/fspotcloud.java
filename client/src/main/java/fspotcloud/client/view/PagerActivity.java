package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;

public class PagerActivity extends AbstractActivity implements PagerPresenter {

	final private static Logger log = Logger.getLogger(PagerActivity.class
			.getName());

	final private PagerView pagerView;
	final protected PlaceGoTo placeGoTo;

	private PhotoInfoStore store = null;

	String photoId;
	String tagId;
	Integer offset = null;
	boolean fullscreenTarget = false;

	@Inject
	public PagerActivity(PagerView pagerView, PlaceGoTo placeGoTo) {
		log.info("Pager activity created : " + this);
		this.placeGoTo = placeGoTo;
		this.pagerView = pagerView;
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
		log.info("setPlace" + this + " : " + place);
	}

	private void calculateLocation() {
		if (store != null) {
			offset = store.indexOf(photoId);
		}
	}

	protected void goToPhoto(String tagId, String photoId) {
		BasePlace place;
		if (fullscreenTarget) {
			place = new ImageViewingPlace(tagId, photoId);
		} else {
			place = new TagViewingPlace(tagId, photoId);
		}
		log.info("About to go to: " + this + " : " + place);
		placeGoTo.goTo(place);
	}

	private void goToPhoto(String photoId) {
		goToPhoto(tagId, photoId);
	}

	@Override
	public void go(boolean forward) {
		if (canGo(forward)) {
			int increment = forward ? 1 : -1;
			String photoId = store.get(offset + increment).getId();
			goToPhoto(photoId);
		}
	}

	@Override
	public void goEnd(boolean first) {
		if (!store.isEmpty()) {
			String photoId;
			if (first) {
				photoId = store.get(0).getId();
			} else {
				photoId = store.last().getId();
			}
			goToPhoto(photoId);
		}
	}
	
	@Override
	public boolean canGo(boolean forward) {
		if (offset != null) {
			if (forward) {
				return offset >= 0 && offset < store.lastIndex();
			} else {
				return offset > 0; 
			}
		} else {
			return false;
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		log.info("Start");
		pagerView.setPagerPresenter(this);
		panel.setWidget(pagerView);
	}
}
