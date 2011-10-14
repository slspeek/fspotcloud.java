package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.ImageRasterView;
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
	private final ButtonPanelView buttonPanelView;
	private final ImageRasterView imageRasterView;
	private TagPresenter presenter;

	@UiField
	LayoutPanel mainPanel;

	@Inject
	public TagViewImpl(TreeView treeView, @Named("Main") ButtonPanelView buttonPanelView,
			ImageRasterView imageRasterView) {
		this.treeView = treeView;
		this.buttonPanelView = buttonPanelView;
		this.imageRasterView = imageRasterView;
		initWidget(uiBinder.createAndBindUi(this));
		treeView.asWidget().addStyleName("fsc-tag-tree-container");
	}

	@UiFactory
	public TreeViewImpl getView() {
		return (TreeViewImpl) treeView;
	}

	@UiFactory
	public ButtonPanelViewImpl getButtonView() {
		return (ButtonPanelViewImpl) buttonPanelView;
	}

	@UiFactory
	public ImageRasterViewImpl getImageRasterView() {
		return (ImageRasterViewImpl) imageRasterView;
	}

	@Override
	public void setPresenter(TagPresenter presenter) {
		this.presenter = presenter;
	}
}
