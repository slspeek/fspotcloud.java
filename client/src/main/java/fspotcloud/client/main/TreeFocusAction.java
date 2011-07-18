package fspotcloud.client.main;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.TreeView;

public class TreeFocusAction implements Runnable {

	final private TreeView treeView;

	@Inject
	public TreeFocusAction(TreeView treeView) {
		this.treeView = treeView;
	}

	@Override
	public void run() {
		treeView.requestFocus();
	}

}
