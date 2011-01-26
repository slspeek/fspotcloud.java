package fspotcloud.client.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.rpc.TagServiceAsync;
import fspotcloud.shared.tag.TagNode;

public class DataManagerImpl implements DataManager {
	private static final Logger log = Logger.getLogger(DataManagerImpl.class
			.getName());

	private final TagServiceAsync tagService;
	private final IndexingUtil indexingUtil;
	
	private List<TagNode> tagTreeData = null;
	private final Map<String,TagNode> tagNodeIndex = new HashMap<String,TagNode>();

	@Inject
	public DataManagerImpl(TagServiceAsync tagService, IndexingUtil indexingUtil) {
		this.tagService = tagService;
		this.indexingUtil = indexingUtil;
	}

	public TagNode getTagNode(String id) {
		return tagNodeIndex.get(id); 
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
					indexingUtil.rebuildTagNodeIndex(tagNodeIndex, tagTreeData);
					callback.onSuccess(result);
				}
			});
		}
	}

}
