package fspotcloud.client.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class PagingNavigatorImpl implements PagingNavigator {

	final private static Logger log = Logger
			.getLogger(PagingNavigatorImpl.class.getName());

	final private DataManager dataManager;

	@Inject
	public PagingNavigatorImpl(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public void getPageCount(String tagId, final int pageSize,
			final AsyncCallback<Integer> callback) {
		dataManager.getTagNode(tagId, new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.WARNING, "getTagNode on datamanager failed",
						caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				getPageCount(result, pageSize, callback);
			}
		});
	}

	private void getPageCount(TagNode node, int pageSize,
			AsyncCallback<Integer> callback) {
		PhotoInfoStore store = node.getCachedPhotoList();
		int result = store.size() / pageSize;
		if (store.size() % pageSize != 0) {
			result++;
		}
		callback.onSuccess(result);
	}

	@Override
	public void getPage(String tagId, final int pageSize, final int pageNumber,
			final AsyncCallback<List<BasePlace>> callback) {
		dataManager.getTagNode(tagId, new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.WARNING, "getTagNode on datamanager failed",
						caught);
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				getPage(result, pageSize, pageNumber, callback);
			}
		});

	}

	private void getPage(TagNode node, int pageSize, int pageNumber,
			AsyncCallback<List<BasePlace>> callback) {
		PhotoInfoStore store = node.getCachedPhotoList();
		log.info("Store: " + store);
		int offset = pageNumber * pageSize;
		List<BasePlace> result = new ArrayList<BasePlace>();
		for (int i = offset; i < offset + pageSize; i++) {
			if (i <= store.lastIndex()) {
				result.add(new ImageViewingPlace(node.getId(), store.get(i)
						.getId()));
			} else {
				break;
			}
		}
		callback.onSuccess(result);
	}

	@Override
	public void getPage(String tagId, final String photoId, final int pageSize,
			final AsyncCallback<List<BasePlace>> callback) {
		dataManager.getTagNode(tagId, new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.WARNING, "getTagNode on datamanager failed",
						caught);
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				PhotoInfoStore store = result.getCachedPhotoList();
				int index = store.indexOf(photoId);
				int pageNumber = index / pageSize;
				getPage(result, pageSize,pageNumber, callback);
			}
		});

	}

}
