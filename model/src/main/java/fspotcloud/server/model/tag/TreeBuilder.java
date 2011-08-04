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
		return getFilteredRoots(new Filter() {
			@Override
			public boolean isValid(TagNode node) {
				return true;
			}

		});
	}

	private void buildMap() {
		index = new HashMap<String, TagNode>();
		for (TagNode node : flatNodes) {
			String name = node.getId();
			index.put(name, node);
		}
	}

	public List<TagNode> getPublicRoots() {
		return getFilteredRoots(new Filter() {
			@Override
			public boolean isValid(TagNode node) {
				return node.isImportIssued();
			}

		});
	}

	private List<TagNode> getFilteredRoots(Filter f) {
		buildMap();
		List<TagNode> roots = new ArrayList<TagNode>();
		for (TagNode node : flatNodes) {
			if (f.isValid(node)) {
				TagNode parent = getFilteredParent(node, f);
				if (parent == null) {
					roots.add(node);
				} else {
					parent.addChild(node);
				}
			}
		}
		return roots;
	}

	private TagNode getFilteredParent(TagNode node, Filter filter) {
		if ("0".equals(node.getParentId())) {
			return null;
		} else {
			TagNode parent;
			while ((parent = index.get(node.getParentId())) != null) {
				if (filter.isValid(parent)) {
					return parent;
				} else {
					node = parent;
				}
			}
			return null;
		}
	}
}
