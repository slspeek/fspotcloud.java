package fspotcloud.client.tree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
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
	
	private Image mainImage = new Image();
	private final Tree t = new Tree();
	private final ScrollPanel treeScroller = new ScrollPanel(t);
	private final Label statusLabel = new Label();
	private final FlowPanel statusPanel = new FlowPanel(); 
	private final FlowPanel imagePanel = new FlowPanel();
	
	private final Timer slideShowTimer = new Timer() {
		int index = 0;
		@Override
		public void run() {
			if (index < speeltuinKeys.size()) {
				mainImage.setUrl("/image?id=" + speeltuinKeys.get(index++));
				//Window.alert("Set image");
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

		statusPanel.add(statusLabel);
		panel.addSouth(statusPanel, 40);
		SplitLayoutPanel splitPanel = new SplitLayoutPanel();
		splitPanel.addWest(treeScroller, 200);
		imagePanel.add(mainImage);
		splitPanel.add(imagePanel);
		
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
				//Window.alert(caught.getLocalizedMessage());
				fillSpeeltuin();
				slideShowTimer.scheduleRepeating(250);
			}
			
			void fillSpeeltuin() {
				speeltuinKeys = new ArrayList<String>();
				speeltuinKeys.add("10000");
				speeltuinKeys.add("10001");
				speeltuinKeys.add("10002");
				speeltuinKeys.add("10003");
				speeltuinKeys.add("10004");
				speeltuinKeys.add("10005");
				speeltuinKeys.add("10006");
				speeltuinKeys.add("10007");
				speeltuinKeys.add("10052");
				speeltuinKeys.add("10053");
				speeltuinKeys.add("10054");
				speeltuinKeys.add("10055");
				speeltuinKeys.add("10056");
			}
			public void onSuccess(List<String> result) {
				statusLabel.setText(String.valueOf(result));
				if (result.isEmpty()) {
					fillSpeeltuin();
				} else {
					speeltuinKeys = result;
				}
				slideShowTimer.scheduleRepeating(1250);
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
