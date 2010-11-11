package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.TagView;

public class TagViewImpl extends ResizeComposite implements TagView {

	private static TagViewImplUiBinder uiBinder = GWT.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	private TagView.TagPresenter presenter; 
	@UiField
	HTMLPanel mainPanel;
	
	@UiField
	Tree tagTree;
	
	@UiField
	Image mainImage;
	
	@UiField
	Label titleLabel;
	
	@UiField
	Label statusLabel;
	
	@UiField
	PushButton firstButton;

	@UiField
	PushButton prevButton;
	
	@UiField
	PushButton nextButton;
	
	@UiField
	PushButton lastButton;
	
	TreeItem treeModel;

	public TagViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		tagTree.addItem("Loading, please wait.");
	}


	@Override
	public void setPresenter(TagPresenter presenter) {
		this.presenter = presenter;
	}


	@Override
	public void setMainImageUrl(String url) {
		mainImage.setUrl(url); 
	}


	@Override
	public void setTagId(String tagId) {
		
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
	
	@UiHandler("firstButton")
	public void onFirstButtonClicked(ClickEvent event) {
		presenter.goFirst();
	}
	
	@UiHandler("nextButton")
	public void onNextButtonClicked(ClickEvent event) {
		presenter.goForward();
	}
	@UiHandler("prevButton")
	public void onPreviousButtonClicked(ClickEvent event) {
		presenter.goBackward();
	}
	@UiHandler("lastButton")
	public void onLastButtonClicked(ClickEvent event) {
		presenter.goLast();
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
}
