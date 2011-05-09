package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.view.BasePlace;
import fspotcloud.client.view.ImageViewingPlace;
import fspotcloud.client.view.PlaceGoTo;
import fspotcloud.client.view.TagViewingPlace;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class NavigatorImpl implements Navigator {

	final private static Logger log = Logger.getLogger(NavigatorImpl.class
			.getName());

	final private PlaceGoTo placeGoTo;
	final private DataManager dataManager;

	@Inject
	public NavigatorImpl(PlaceGoTo placeGoTo, DataManager dataManager) {
		this.placeGoTo = placeGoTo;
		this.dataManager = dataManager;
	}

	@Override
	public void goEnd(final boolean first, final BasePlace place) {
		dataManager.getTagNode(place.getTagId(), new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.warning("getTagNode failed " + caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				PhotoInfoStore store = result.getCachedPhotoList();
				goEnd(first, place, store);
			}

		});
	}

	private void goEnd(boolean first, BasePlace place, PhotoInfoStore store) {
		if (!store.isEmpty()) {
			String photoId;
			if (first) {
				photoId = store.get(0).getId();
			} else {
				photoId = store.last().getId();
			}
			goToPhoto(place, place.getTagId(), photoId);
		}
	}

	private int indexOf(BasePlace place, PhotoInfoStore store) {
		return store.indexOf(place.getPhotoId());
	}

	@Override
	public void go(final boolean forward, final BasePlace place) {
		dataManager.getTagNode(place.getTagId(), new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.warning("getTagNode failed " + caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				PhotoInfoStore store = result.getCachedPhotoList();
				go(forward, place, store);
			}

		});

	}

	private void go(boolean forward, BasePlace place, PhotoInfoStore store) {
		int offset = indexOf(place, store);
		if (canGo(forward, place, store)) {
			int increment = forward ? 1 : -1;
			String photoId = store.get(offset + increment).getId();
			goToPhoto(place, place.getTagId(), photoId);
		}
	}

	private boolean canGo(boolean forward, BasePlace place, PhotoInfoStore store) {
		int offset = indexOf(place, store);
		if (forward) {
			return offset >= 0 && offset < store.lastIndex();
		} else {
			return offset > 0;
		}
	}

	@Override
	public void canGo(final boolean forward, final BasePlace place,
			final AsyncCallback<Boolean> callback) {
		dataManager.getTagNode(place.getTagId(), new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.warning("getTagNode failed " + caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				PhotoInfoStore store = result.getCachedPhotoList();
				callback.onSuccess(canGo(forward, place, store));
			}

		});
	}

	protected void goToPhoto(BasePlace place, String tagId, String photoId) {
		BasePlace newPlace;
		if (place instanceof ImageViewingPlace) {
			newPlace = new ImageViewingPlace(tagId, photoId);
		} else {
			newPlace = new TagViewingPlace(tagId, photoId);
		}
		log.info("About to go to: " + this + " : " + newPlace + " from: "
				+ place);
		placeGoTo.goTo(newPlace);
	}
}
