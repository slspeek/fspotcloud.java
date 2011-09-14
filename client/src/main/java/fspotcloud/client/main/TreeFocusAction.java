package fspotcloud.client.main;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.place.api.Navigator;

public class TreeFocusAction implements Runnable {

	final private TreeView treeView;
	final private Navigator navigator;

	@Inject
	public TreeFocusAction(Navigator navigator, TreeView treeView) {
		this.navigator = navigator;
		this.treeView = treeView;
	}

	@Override
	public void run() {
		navigator.setTreeVisible(true);
		treeView.requestFocus();
	}

}
