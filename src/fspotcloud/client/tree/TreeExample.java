package fspotcloud.client.tree;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import fspotcloud.shared.tag.TagNode;

public class TreeExample implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Tag service.
	 */
	private final TagServiceAsync tagService = GWT.create(TagService.class);
	
	private final Image mainImage = new Image(
			"http://lh5.ggpht.com/_I6XE5OTEwr4/SWpTcwEnMbI/AAAAAAAADic/bgdh_8p2maU/s800/img_6083.jpg");

	public void onModuleLoad() {
		final Button loadButton = new Button("Load the tree");
		// Create a tree with a few items in it.
		
		final Tree t = new Tree();
		final ScrollPanel treeScroller = new ScrollPanel(t);
		
		LayoutPanel container = new LayoutPanel();
		DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);
		panel.addNorth(new Label("F-Spot Cloud Java Edition"), 80);
		SplitLayoutPanel splitPanel = new SplitLayoutPanel();
		
		splitPanel.addWest(treeScroller, 200);
		
		//panel.addSouth(loadButton, 140);
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.add(mainImage);
		splitPanel.add(decPanel);
		panel.add(splitPanel);
		// Add it to the root panel.
		RootLayoutPanel.get().add(panel);
		
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */boolean treeIsVisible = true;
			public void onClick(ClickEvent event) {
				requestTagTreeFromServer();
				
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			public void requestTagTreeFromServer() {
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
		handler.requestTagTreeFromServer();
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
