package fspotcloud.client.view.action;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.TreeView;

public class TreeFocusAction implements GestureAction {

	final private TreeView treeView;

	@Inject
	public TreeFocusAction(TreeView treeView) {
		this.treeView = treeView;
	}

	@Override
	public void perform() {
		treeView.requestFocus();
	}

}
