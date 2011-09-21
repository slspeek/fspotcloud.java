package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.TagView;
import fspotcloud.client.main.view.api.TreeView;

public class TagViewImpl extends ResizeComposite implements TagView {

	private static final Logger log = Logger.getLogger(TagViewImpl.class
			.getName());
	private static TagViewImplUiBinder uiBinder = GWT
			.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	private final TreeView treeView;

	@UiField
	DockLayoutPanel mainPanel;
	@UiField
	SplitLayoutPanel horizontalSplitPanel;

	@UiField
	Label titleLabel;
	@UiField
	Label statusLabel;

	@UiField
	DockLayoutPanel imageViewPanel;
	
	@Inject
	public TagViewImpl(TreeView treeView) {
		this.treeView = treeView;
		initWidget(uiBinder.createAndBindUi(this));
		mainPanel.addStyleName("fsc-tag");
		horizontalSplitPanel.addStyleName("fsc-tag-split-panel");
		titleLabel.addStyleName("fsc-tag-title-label");
		statusLabel.addStyleName("fsc-tag-status-label");
		treeView.asWidget().addStyleName("fsc-tag-tree-container");
		imageViewPanel.addStyleName("fsc-tag-image-view-container");
	}

	@UiFactory
	public TreeViewImpl getView() {
		return (TreeViewImpl) treeView;
	}

	@Override
	public void setStatusText(String text) {
		statusLabel.setText(text);
	}

	@Override
	public HasOneWidget getImageViewPanelContainer() {
		return new HasOneWidgetAdapter(imageViewPanel);
	}

	@Override
	public void setSize(int width, int height) {
		// layout.setPixelSize(width, height);
	}
}
