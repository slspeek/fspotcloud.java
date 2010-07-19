package fspotcloud.client.tree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import fspotcloud.shared.tag.TagNode;
import fspotcloud.rpc.TagService;
import fspotcloud.rpc.TagServiceAsync;

public class TreeExample implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Tag service.
	 */
	private final TagServiceAsync tagService = GWT.create(TagService.class);

	private List<String> speeltuinKeys = null;

	private Image mainImage = new Image();
	private final Label statusLabel = new Label();
	private final SelectionHandler<TreeItem> treeHandler = new SelectionHandler<TreeItem>() {

		@Override
		public void onSelection(SelectionEvent<TreeItem> event) {
			TreeItem item = event.getSelectedItem();
			String tagId = (String) item.getUserObject();
			statusLabel.setText("Tag " + tagId + " selected.");
		}
	};
	private final TreePanel treePanel = new TreePanel(tagService, treeHandler,
			statusLabel);

	private final FlowPanel statusPanel = new FlowPanel();
	private final TabPanel centerTabPanel = new TabPanel();
	private final FlowPanel imagePanel = new FlowPanel();
	private final FlexTable albumTable = new FlexTable();

	private final Timer slideShowTimer = new Timer() {
		int index = 0;

		@Override
		public void run() {
			if (index < speeltuinKeys.size()) {
				mainImage.setUrl("/image?id=" + speeltuinKeys.get(index++));
				// Window.alert("Set image");
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
		splitPanel.addWest(treePanel, 200);
		imagePanel.add(mainImage);
		// mainImage.setPixelSize(400, 300);
		// mainImage.setHeight("100%");
		// mainImage.setWidth("100%");
		centerTabPanel.add(imagePanel, "Image");
		centerTabPanel.add(albumTable, "Album");

		splitPanel.add(centerTabPanel);
		panel.add(splitPanel);

		// Add it to the root panel.
		RootLayoutPanel.get().add(panel);
		//treePanel.buildUI();
		requestKeysForTag("46");
	}

	private void requestKeysForTag(String tagId) {
		tagService.keysForTag(tagId, new AsyncCallback<List<String>>() {
			public void onFailure(Throwable caught) {
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
				statusLabel.setText("Photo list recieved.");
				if (result.isEmpty()) {
					fillSpeeltuin();
				} else {
					speeltuinKeys = result;
				}
				slideShowTimer.scheduleRepeating(3250);
				int index = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						albumTable.setWidget(i, j, new Image("/image?thumb&id="
								+ result.get(index++)));
					}
				}
			}
		});
	}

}
