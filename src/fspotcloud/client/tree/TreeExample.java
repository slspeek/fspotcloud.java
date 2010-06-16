package fspotcloud.client.tree;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import fspotcloud.shared.tag.TagNode;

public class TreeExample implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Tag service.
	 */
	private final TagServiceAsync tagService = GWT.create(TagService.class);

	public void onModuleLoad() {
		final Button loadButton = new Button("Load the tree");
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

		final Tree t = new Tree();
		VerticalPanel panel = new VerticalPanel();

		t.addItem(root);

		panel.add(t);
		panel.add(loadButton);
		// Add it to the root panel.
		RootPanel.get("treeContainer").add(panel);
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				requestTagTreeFromServer();
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void requestTagTreeFromServer() {
				loadButton.setEnabled(false);
				t.clear();
				tagService.loadTagTree(new AsyncCallback<List<TagNode>>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						loadButton.setText("Error occurred" + caught);

					}

					public void onSuccess(List<TagNode> result) {
						for (TagNode root : result) {
							addSubTree(root, t);
						}
						loadButton.setEnabled(true);

					}

				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		loadButton.addClickHandler(handler);

	}

	private void addSubTree(TagNode node, TreeItem target) {
		TreeItem newItem = target.addItem(node.getTagName());
		for (TagNode child : node.getChildren()) {
			addSubTree(child, newItem);
		}
	}

	private void addSubTree(TagNode node, Tree tree) {
		TreeItem newItem = tree.addItem(node.getTagName());
		for (TagNode child : node.getChildren()) {
			addSubTree(child, newItem);
		}
	}
}
