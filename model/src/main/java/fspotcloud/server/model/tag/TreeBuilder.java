package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fspotcloud.shared.tag.TagNode;

public class TreeBuilder {

	private List<TagNode> flatNodes;
	private Map<String, TagNode> index;

	public TreeBuilder(List<TagNode> flatNodes) {
		this.flatNodes = flatNodes;
	}

	public List<TagNode> getRoots() {
		buildMap();
		List<TagNode> roots = new ArrayList<TagNode>();
		for (TagNode node : flatNodes) {
			if ("0".equals(node.getParentId())) {
				roots.add(node);
			} else {
				TagNode parent = index.get(node.getParentId());
				parent.addChild(node);
			}
		}
		return roots;
	}

	private void buildMap() {
		index = new HashMap<String, TagNode>();
		for (TagNode node : flatNodes) {
			String name = node.getName();
			index.put(name, node);
		}
	}

}
