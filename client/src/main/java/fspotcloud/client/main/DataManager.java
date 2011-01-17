package fspotcloud.client.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.rpc.TagServiceAsync;
import fspotcloud.shared.tag.TagNode;

public class DataManager {
	final private TagServiceAsync tagService;
	
	private List<TagNode> tagTreeData = null;
	final private Map<String,TagNode> tagNodeIndex = new HashMap<String,TagNode>();

	private static final Logger log = Logger.getLogger(DataManager.class
			.getName());

	@Inject
	public DataManager(TagServiceAsync tagService) {
		this.tagService = tagService;
	}

	public TagNode getTagNode(String id) {
		return tagNodeIndex.get(id); 
	}
	
	private void rebuildTagNodeIndex() {
		tagNodeIndex.clear();
		for (TagNode root: tagTreeData) {
			addTagNodeIndex(root);
		}
	}
	
	private void addTagNodeIndex(TagNode node) {
		tagNodeIndex.put(node.getId(), node);
		for (TagNode child: node.getChildren()) {
			addTagNodeIndex(child);
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
					rebuildTagNodeIndex();
					callback.onSuccess(result);
				}
			});
		}
	}

}
