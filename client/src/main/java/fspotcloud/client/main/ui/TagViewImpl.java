package fspotcloud.client.main.ui;


import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.client.main.TagView;

public class TagViewImpl extends ResizeComposite implements TagView {
	private static final Logger log = Logger.getLogger(TagViewImpl.class.getName());
	
	private static TagViewImplUiBinder uiBinder = GWT
			.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	private TagView.TagPresenter presenter;
	@UiField
	HTMLPanel mainPanel;

	@UiField
	Tree tagTree;
	
	@UiField
	SimplePanel imageListPanel;

	@UiField
	Label titleLabel;

	@UiField
	Label statusLabel;

	TreeItem treeModel;

	TreeViewModel treeViewModel;

	SingleSelectionModel<TreeItem> treeSelectionModel;
	

	public TagViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		tagTree.addItem("Loading, please wait.");
		this.treeSelectionModel = new SingleSelectionModel<TreeItem>();
		
	}

	@Override
	public void setPresenter(TagPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setStatusText(String text) {
		statusLabel.setText(text);
	}

	@Override
	public void setTreeModel(TreeItem root) {
		this.treeModel = root;
		tagTree.removeItems();
		tagTree.addItem(root);
		tagTree.setAccessKey('t');
		root.setState(true);
		for (int i = 0; i < root.getChildCount(); i++) {
			TreeItem child = root.getChild(i);
			child.setState(true);
		}
	}

	@UiHandler("loadTreeButton")
	public void onLoadTreeButtonClicked(ClickEvent event) {
		presenter.reloadTree();
	}

	@UiHandler("tagTree")
	public void onTreeSelectionEvent(SelectionEvent<TreeItem> event) {
		presenter.treeSelectionChanged(event);
	}

	@Override
	public TreeItem getTreeModel() {
		return treeModel;
	}

	@Override
	public void setSelectedItem(TreeItem item) {

	}

	@Override
	public void setImageList(List<String> imageList) {
		CellList<String> cellList = new CellList<String>(new ImageCell());
		cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellList.setRowData(0, imageList);
		cellList.setRowCount(imageList.size());
		Widget old = imageListPanel.getWidget();
		log.info("old widget: " + old);
		if (old != null) {
			imageListPanel.remove(old);
		}
		imageListPanel.setWidget((IsWidget)cellList);
	}
}
