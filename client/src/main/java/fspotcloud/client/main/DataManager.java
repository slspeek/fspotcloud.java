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
