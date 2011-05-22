package fspotcloud.client.main.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.place.BasePlace;
import fspotcloud.shared.tag.TagNode;

public class TreePresenterImpl implements TreeView.TreePresenter {
	private static final Logger log = Logger.getLogger(TreePresenterImpl.class
			.getName());

	final private TreeView treeView;
	final private DataManager dataManager;
	final private SingleSelectionModel<TagNode> selectionModel;
	final private TreeSelectionHandler  treeSelectionHandler;

	private BasePlace place;

	@Inject
	public TreePresenterImpl(TreeView treeView, DataManager dataManager,
			SingleSelectionModel<TagNode> singleSelectionModel,
			TreeSelectionHandler treeSelectionHandler) {
		this.treeView = treeView;
		this.dataManager = dataManager;
		this.selectionModel = singleSelectionModel;
		this.treeSelectionHandler = treeSelectionHandler;
	}

	public void init() {
		treeSelectionHandler.setSelectionModel(selectionModel);
		reloadTree();
	}

	private void setModel(List<TagNode> roots) {
		TagTreeModel treeModel = new TagTreeModel(roots, selectionModel);
		treeView.setTreeModel(treeModel);
		updatePlace();
	}

	private void requestTagTreeData() {
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Exception: " + caught);
			}

			@Override
			public void onSuccess(List<TagNode> result) {
				setModel(result);

			}
		});
	}

	public void reloadTree() {
		requestTagTreeData();
	}

	private void openSelectedTreeNode(TreeNode node) {
		try {
			for (int i = 0; i < node.getChildCount(); i++) {
				TreeNode child = node.setChildOpen(i, true, false);
				if (child != null) {
					openSelectedTreeNode(child);
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "openTreeNode", e);
		}
	}

	public void setPlace(BasePlace place) {
		this.place = place;
		updatePlace();
	}

	private void updatePlace() {
		if (place != null) {
			TagNode node = new TagNode();
			String tagId = place.getTagId();
			node.setId(tagId);
			selectionModel.setSelected(node, true);
			TreeNode root = treeView.getRootNode();
			if (root != null) {
				openSelectedTreeNode(root);
			} else {
				log.warning("Root node is null");
			}
		} else {
			log.warning("Root node is null");
		}
	}
}
