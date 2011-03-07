package fspotcloud.client.main.ui;

import java.util.logging.Logger;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;
import com.google.inject.Inject;

import fspotcloud.client.view.TagView;

public class TagViewImpl extends ResizeComposite implements TagView {

	private static final Logger log = Logger.getLogger(TagViewImpl.class
			.getName());
	private static TagViewImplUiBinder uiBinder = GWT.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	@UiField
	HTMLPanel mainPanel;
	@UiField
	HorizontalSplitPanel horizontalSplitPanel;
	
	@UiField
	Label titleLabel;
	@UiField
	Label statusLabel;
	
	@UiField 
	SimplePanel tagTreeViewPanel;
	@UiField 
	SimplePanel imageViewPanel;
	
	CellTree cellTree;
	
	@Inject
	public TagViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-tag");
		horizontalSplitPanel.addStyleName("fsc-tag-split-panel");
		titleLabel.addStyleName("fsc-tag-title-label");
		statusLabel.addStyleName("fsc-tag-status-label");
		tagTreeViewPanel.addStyleName("fsc-tag-tree-container");
		imageViewPanel.addStyleName("fsc-tag-image-view-container");
	}

	@Override
	public void setStatusText(String text) {
		statusLabel.setText(text);
	}

	@Override
	public void setTreeModel(TreeViewModel model) {
		cellTree = new CellTree(model, null);
		tagTreeViewPanel.setWidget(cellTree);
	}
	
	@Override
	public HasOneWidget getImageViewContainer() {
		return imageViewPanel;
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
}
