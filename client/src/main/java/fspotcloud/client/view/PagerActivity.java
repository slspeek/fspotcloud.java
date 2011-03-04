package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.BackGestureEvent;
import fspotcloud.client.main.shared.BackGestureHandler;
import fspotcloud.client.main.shared.ForwardGestureEvent;
import fspotcloud.client.main.shared.ForwardGestureHandler;
import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;

public class PagerActivity extends AbstractActivity implements PagerPresenter,
		BackGestureHandler, ForwardGestureHandler {

	final private static Logger log = Logger.getLogger(PagerActivity.class
			.getName());

	final private PagerView pagerView;
	final protected PlaceGoTo placeGoTo;
	final private BasePlace place;

	private PhotoInfoStore store = null;

	String photoId;
	String tagId;
	Integer offset = null;
	boolean fullscreenTarget = false;
	private HandlerRegistration forwardRegistration;
	private HandlerRegistration backRegistration;

	public PagerActivity(BasePlace place, PagerView pagerView,
			PlaceGoTo placeGoTo) {
		this.place = place;
		this.placeGoTo = placeGoTo;
		this.pagerView = pagerView;
		setPlace();
	}

	@Override
	public void setData(PhotoInfoStore data) {
		this.store = data;
		calculateLocation();
	}

	private void calculateLocation() {
		offset = store.indexOf(photoId);
		setViewEnabledStates();
		log.info("calculateLocation ran");
	}

	private void setViewEnabledStates() {
		boolean first, last, next, prev;
		first = offset != null && offset > 0;
		pagerView.getFirst().setEnabled(first);
		last = offset != null && offset < store.lastIndex();
		pagerView.getLast().setEnabled(last);
		next = canGo(true);
		pagerView.getNext().setEnabled(next);
		prev = canGo(false);
		pagerView.getPrevious().setEnabled(prev);
		log.info("States: " + first + " " + prev + " " + next + " " + last);
	}

	private void setPlace() {
		if (place instanceof ImageViewingPlace) {
			fullscreenTarget = true;
		} else {
			fullscreenTarget = false;
		}
		this.photoId = place.getPhotoId();
		this.tagId = place.getTagId();
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
		pagerView.setPagerPresenter(this);
		panel.setWidget(pagerView);
		addEventHandlers(eventBus);
		setViewEnabledStates();
	}

	public void addEventHandlers(EventBus eventBus) {
		forwardRegistration = eventBus.addHandler(ForwardGestureEvent.TYPE,
				this);
		backRegistration = eventBus.addHandler(BackGestureEvent.TYPE, this);
	}

	public void removeEventHandlers() {
		forwardRegistration.removeHandler();
		backRegistration.removeHandler();
	}

	public void onStop() {
		removeEventHandlers();
	}

	@Override
	public void onForwardGesture(ForwardGestureEvent e) {
		go(true);
	}

	@Override
	public void onBackGesture(BackGestureEvent e) {
		go(false);
	}
}
