package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.view.TagView;
import fspotcloud.client.view.TreeView;

public class TagViewImpl extends ResizeComposite implements TagView {

	private static final Logger log = Logger.getLogger(TagViewImpl.class
			.getName());
	private static TagViewImplUiBinder uiBinder = GWT
			.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	@UiField
	HTMLPanel mainPanel;
	@UiField
	SplitLayoutPanel horizontalSplitPanel;

	@UiField
	Label titleLabel;
	@UiField
	Label statusLabel;

	@UiField
	TreeViewImpl treeView;
	@UiField
	SimplePanel imageViewPanel;

	@Inject
	public TagViewImpl(TreeView treeView) {
		this.treeView = (TreeViewImpl) treeView;
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-tag");
		horizontalSplitPanel.addStyleName("fsc-tag-split-panel");
		titleLabel.addStyleName("fsc-tag-title-label");
		statusLabel.addStyleName("fsc-tag-status-label");
		((TreeViewImpl) treeView).addStyleName("fsc-tag-tree-container");
		imageViewPanel.addStyleName("fsc-tag-image-view-container");
	}

	@UiFactory
	public TreeViewImpl getView() {
		log.info("getView");
		return (TreeViewImpl) treeView;
	}

	@Override
	public void setStatusText(String text) {
		statusLabel.setText(text);
	}

	@Override
	public HasOneWidget getImageViewPanelContainer() {
		return imageViewPanel;
	}
}
