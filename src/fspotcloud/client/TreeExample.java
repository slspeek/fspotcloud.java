package fspotcloud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class TreeExample implements EntryPoint {

	public void onModuleLoad() {
		// Create a tree with a few items in it.
		TreeItem root = new TreeItem("Steven's fotos");
		TreeItem bas = new TreeItem("Bas");
		root.addItem(bas);
		root.addItem("Zelfportretten");
		root.addItem("Hortus");
		bas.addItem("Bas en Claire");
		bas.addItem("Speeltuin");
		bas.addItem("Bakkum");
		

		// Add a CheckBox to the tree
		TreeItem item = new TreeItem("Evenementen");
		root.addItem(item);

		Tree t = new Tree();
		t.addItem(root);

		// Add it to the root panel.
		RootPanel.get("treeContainer").add(t);
	}
}
