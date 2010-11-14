package fspotcloud.client.main;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.shared.tag.TagNode;

public class TreeEntryPoint implements EntryPoint {
	private static final Logger log = Logger.getLogger(TreeEntryPoint.class.getName());
	@Override
	public void onModuleLoad() {
		log.info("Hello tree example");
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		DataManager dm = clientFactory.getDataManager();
		dm.getTagTree(new AsyncCallback<List<TagNode>>() {
			
			@Override
			public void onSuccess(List<TagNode> result) {
				TreeViewModel model = new TagTreeModel(result);
				CellTree cellTree = new CellTree(model, null); 
				RootLayoutPanel.get().add(cellTree);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				log.warning(caught.getLocalizedMessage());
			}
		});
		
	}
}
