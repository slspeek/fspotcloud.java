package fspotcloud.client.data;

import java.util.List;
import java.util.Map;

import fspotcloud.shared.tag.TagNode;

public class IndexingUtil {
	
	public void rebuildTagNodeIndex(Map<String,TagNode> tagNodeIndex, List<TagNode> tagTreeData) {
		tagNodeIndex.clear();
		for (TagNode root: tagTreeData) {
			addTagNodeIndex(tagNodeIndex, root);
		}
	}
	
	private void addTagNodeIndex(Map<String,TagNode> tagNodeIndex, TagNode node) {
		tagNodeIndex.put(node.getId(), node);
		for (TagNode child: node.getChildren()) {
			addTagNodeIndex(tagNodeIndex, child);
		}
	}
}
