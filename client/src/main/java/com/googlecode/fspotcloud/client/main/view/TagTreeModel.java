package com.googlecode.fspotcloud.client.main.view;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Provider;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.googlecode.fspotcloud.shared.tag.TagNode;


public class TagTreeModel implements TreeViewModel {

	private static final Logger log = Logger.getLogger(TagTreeModel.class
			.getName());

	List<TagNode> roots;
	SelectionModel<TagNode> selectionModel;
	Provider<Cell<TagNode>> cellProvider;


	public TagTreeModel(List<TagNode> roots,
			SelectionModel<TagNode> selectionModel,
			Provider<Cell<TagNode>> cellProvider) {
		super();
		this.roots = roots;
		this.selectionModel = selectionModel;
		this.cellProvider = cellProvider;
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			ListDataProvider<TagNode> rootNodes = new ListDataProvider<TagNode>(
					roots);
			return new DefaultNodeInfo<TagNode>(rootNodes, cellProvider.get(),
					selectionModel, null);
		} else if (value instanceof TagNode) {
			TagNode node = (TagNode) value;
			List<TagNode> children = node.getChildren();
			ListDataProvider<TagNode> childNodes = new ListDataProvider<TagNode>(
					children);
			return new DefaultNodeInfo<TagNode>(childNodes, cellProvider.get(),
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
