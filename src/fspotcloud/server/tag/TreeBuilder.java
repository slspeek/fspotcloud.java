package fspotcloud.server.tag;

import java.util.List;

import fspotcloud.shared.tag.TagNode;

public class TreeBuilder {
	
	private List<TagNode> flatNodes;

	public TreeBuilder(List<TagNode> flatNodes) {
		this.flatNodes = flatNodes;
	}
	
	public List<TagNode> getRoots() {
		return null;
	}

}
