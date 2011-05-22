package fspotcloud.client.data;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.client.main.view.TagCell;
import fspotcloud.shared.tag.TagNode;

public class TagTreeModel implements TreeViewModel {
	
	private static final Logger log = Logger.getLogger(TagTreeModel.class
			.getName());

	private final List<TagNode> roots;

	public TagTreeModel(List<TagNode> roots) {
		this.roots = roots;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		log.info("getNodeInfo: " + value);
		if (value == null) {
			ListDataProvider<TagNode> dataProvider = new ListDataProvider<TagNode>(
					roots);
			return new DefaultNodeInfo(dataProvider, new TagCell());
		}
		TagNode node = (TagNode) value;
		ListDataProvider<TagNode> dataProvider = new ListDataProvider<TagNode>(
				node.getChildren());
		return new DefaultNodeInfo(dataProvider, new TagCell());
	}

	@Override
	public boolean isLeaf(Object value) {
		log.info("isLeaf: " + value);
		if (value == null) {
			return false;
		} else {
			TagNode node = (TagNode) value;
			return node.getChildren().size() == 0;
		}
	}

}
