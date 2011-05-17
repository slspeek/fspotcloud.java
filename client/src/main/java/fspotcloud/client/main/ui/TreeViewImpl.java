package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

import fspotcloud.client.view.TreeView;

public class TreeViewImpl extends ResizeComposite implements TreeView {

	private static final Logger log = Logger.getLogger(TreeViewImpl.class
			.getName());
	
	private static TreeViewImplUiBinder uiBinder = GWT
			.create(TreeViewImplUiBinder.class);

	interface TreeViewImplUiBinder extends UiBinder<Widget, TreeViewImpl> {
	}

	CellTree cellTree;
	@UiField
	ScrollPanel tagTreeViewPanel;
	
	public TreeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		log.info("Created.");
	}

	@Override
	public void setTreeModel(TreeViewModel model) {
		cellTree = new CellTree(model, null);
		tagTreeViewPanel.setWidget(cellTree);
	}
	
	@Override
	public TreeNode getRootNode() {
		if (cellTree != null) {
			return cellTree.getRootTreeNode();
		} else {
			log.warning("cellTree is null");
			return null;
		}
	}
	
	public void requestFocus() {
		if (cellTree != null) {
			cellTree.setFocus(true);
		}
	}
}
