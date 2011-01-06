package fspotcloud.client.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.rpc.TagServiceAsync;
import fspotcloud.shared.tag.TagNode;

public class DataManager {
	TagServiceAsync tagService;

	Map<String, List<String>> photoListMap = new HashMap<String, List<String>>();
	List<TagNode> tagTreeData = null;

	private static final Logger log = Logger.getLogger(DataManager.class
			.getName());

	public DataManager(TagServiceAsync tagService) {
		this.tagService = tagService;
	}

	public void getPhotoListForTag(final String tagId,
			final AsyncCallback<List<String>> callback) {
		if (photoListMap.keySet().contains(tagId)) {
			callback.onSuccess(photoListMap.get(tagId));
		} else {
			tagService.keysForTag(tagId, new AsyncCallback<List<String>>() {
				@Override
				public void onFailure(Throwable caught) {
					log.warning(caught.getLocalizedMessage());
					callback.onFailure(caught);
				}

				@Override
				public void onSuccess(List<String> result) {
					photoListMap.put(tagId, result);
					callback.onSuccess(photoListMap.get(tagId));
				}
			});
		}
	}

	public void getTagTree(
			final AsyncCallback<List<TagNode>> callback) {
		if (tagTreeData != null) {
			callback.onSuccess(tagTreeData);
		} else {
			tagService.loadTagTree(new AsyncCallback<List<TagNode>>() {
				public void onFailure(Throwable caught) {
					log.warning(caught.getLocalizedMessage());
					callback.onFailure(caught);
				}

				public void onSuccess(List<TagNode> result) {
					tagTreeData = result;
					callback.onSuccess(result);
				}
			});
		}
	}

}
