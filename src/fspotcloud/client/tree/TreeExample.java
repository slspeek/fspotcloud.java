package fspotcloud.client.tree;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import fspotcloud.shared.tag.TagNode;

public class TreeExample implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Tag service.
	 */
	private final TagServiceAsync tagService = GWT.create(TagService.class);

	private List<String> speeltuinKeys = null;
	
	private Image mainImage = new Image(
			"http://lh5.ggpht.com/_I6XE5OTEwr4/SWpTcwEnMbI/AAAAAAAADic/bgdh_8p2maU/s800/img_6083.jpg");

	private final Tree t = new Tree();
	private final ScrollPanel treeScroller = new ScrollPanel(t);
	
	private final Timer slideShowTimer = new Timer() {
		int index = 0;
		@Override
		public void run() {
			if (index < speeltuinKeys.size()) {
				mainImage.setUrl("/image?id=" + speeltuinKeys.get(index++));
			} else {
				cancel();
			}
		}
	};

	public void onModuleLoad() {
		
		DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);
		Label titleLabel = new Label("F-Spot Cloud Java Edition");
		titleLabel.addStyleDependentName("title");
		panel.addNorth(titleLabel, 80);
		SplitLayoutPanel splitPanel = new SplitLayoutPanel();

		splitPanel.addWest(treeScroller, 200);

		LayoutPanel container = new LayoutPanel();
		container.add(mainImage);
		container.setWidgetLeftRight(mainImage, 2, Unit.EM, 2, Unit.EM); // Center
																			// panel
		container.setWidgetTopBottom(mainImage, 2, Unit.EM, 2, Unit.EM);
		splitPanel.add(container);
		panel.add(splitPanel);
		// Add it to the root panel.
		RootLayoutPanel.get().add(panel);
		requestTagTreeFromServer();
		requestKeysForTag("46");
	}

	private void requestTagTreeFromServer() {
		tagService.loadTagTree(new AsyncCallback<List<TagNode>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(List<TagNode> result) {
				t.clear();
				for (TagNode root : result) {
					addSubTree(root, t);
				}
			}
		});
	}
	
	private void requestKeysForTag(String tagId) {
		tagService.keysForTag(tagId, new AsyncCallback<List<String>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(List<String> result) {
				speeltuinKeys = result;
				slideShowTimer.scheduleRepeating(2500);
			}
		});
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
