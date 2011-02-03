package fspotcloud.client.view;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.client.main.TagCell;
import fspotcloud.shared.tag.TagNode;

public class TagTreeModel implements TreeViewModel {

	private static final Logger log = Logger.getLogger(TagTreeModel.class
			.getName());

	List<TagNode> roots;
	SelectionModel<TagNode> selectionModel;

	public TagTreeModel(List<TagNode> roots,
			SelectionModel<TagNode> selectionModel) {
		this.roots = roots;
		this.selectionModel = selectionModel;
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			ListDataProvider<TagNode> rootNodes = new ListDataProvider<TagNode>(
					roots);
			return new DefaultNodeInfo<TagNode>(rootNodes, new TagCell(),
					selectionModel, null);
		} else if (value instanceof TagNode) {
			TagNode node = (TagNode) value;
			List<TagNode> children = node.getChildren();
			ListDataProvider<TagNode> childNodes = new ListDataProvider<TagNode>(
					children);
			return new DefaultNodeInfo<TagNode>(childNodes, new TagCell(),
					selectionModel, null);
		} else {
			log.warning("getNodeInfo called with non-TagNode value: " + value);
		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		if (value == null) {
			return false;
		} else if (value instanceof TagNode) {
			TagNode node = (TagNode) value;
			List<TagNode> children = node.getChildren();
			boolean hasNoChildren = children.isEmpty();
			return hasNoChildren;
		} else {
			log.warning("isLeaf called with non-TagNode value: " + value);
		}
		return false;
	}

}
