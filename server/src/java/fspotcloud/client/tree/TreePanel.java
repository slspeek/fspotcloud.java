package fspotcloud.client.tree;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import fspotcloud.shared.tag.TagNode;

public class TreePanel extends ScrollPanel {

	private TagServiceAsync tagService;
	private Tree tagTree = new Tree();
	private SelectionHandler<TreeItem> handler;
	private Label statusLabel;

	public TreePanel(TagServiceAsync tagService,
			SelectionHandler<TreeItem> handler, Label statusLabel) {
		this.tagService = tagService;
		this.handler = handler;
		this.statusLabel = statusLabel;
		buildUI();
	}

	public void buildUI() {
		this.add(tagTree);
		requestTagTreeFromServer();
		tagTree.addSelectionHandler(handler);
	}

	private void requestTagTreeFromServer() {
		tagService.loadTagTree(new AsyncCallback<List<TagNode>>() {
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
				statusLabel.setText(caught.getLocalizedMessage());
			}

			public void onSuccess(List<TagNode> result) {
				tagTree.clear();
				for (TagNode root : result) {
					addSubTree(root, tagTree);
				}
			}
		});
	}

	private void addSubTree(TagNode node, TreeItem target) {
		TreeItem newItem = target.addItem(node.getTagName());
		newItem.setUserObject(node.getName());
		for (TagNode child : node.getChildren()) {
			addSubTree(child, newItem);
		}
	}

	private void addSubTree(TagNode node, Tree tree) {
		TreeItem newItem = tree.addItem(node.getTagName());
		newItem.setUserObject(node.getName());
		for (TagNode child : node.getChildren()) {
			addSubTree(child, newItem);
		}
	}
}
