package fspotcloud.client.admin.view;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.main.view.TagTreeModel;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.PlaceGoTo;
import fspotcloud.client.place.TagPlace;
import fspotcloud.shared.tag.TagNode;

public class AdminTreePresenterImpl implements TreeView.TreePresenter, Handler {

	private static final Logger log = Logger
			.getLogger(AdminTreePresenterImpl.class.getName());

	final private TreeView treeView;
	final private DataManager dataManager;
	final private SingleSelectionModel<TagNode> selectionModel;
	final private PlaceGoTo placeGoTo;

	@Inject
	public AdminTreePresenterImpl(TreeView treeView, DataManager dataManager,
			SingleSelectionModel<TagNode> selectionModel, PlaceGoTo placeGoTo) {
		super();
		this.treeView = treeView;
		this.dataManager = dataManager;
		this.selectionModel = selectionModel;
		this.placeGoTo = placeGoTo;
	}

	public void init() {
		log.info("init");
		reloadTree();
		selectionModel.addSelectionChangeHandler(this);
	}

	private void setModel(List<TagNode> roots) {
		TagTreeModel treeModel = new TagTreeModel(roots, selectionModel);
		treeView.setTreeModel(treeModel);
	}

	private void requestTagTreeData() {
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Exception: " + caught);
			}

			@Override
			public void onSuccess(List<TagNode> result) {
				setModel(result);

			}
		});
	}

	public void reloadTree() {
		requestTagTreeData();
	}

	@Override
	public void setPlace(BasePlace place) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		log.info("Selection event from tree" + selectionModel);
		TagNode node = selectionModel.getSelectedObject();
		if (node != null) {
			String tagId = node.getId();
			log.info("About to go");
			placeGoTo.goTo(new TagPlace(tagId));
		}
	}

}