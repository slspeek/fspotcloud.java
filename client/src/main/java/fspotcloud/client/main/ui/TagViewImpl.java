package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.TagView;
import fspotcloud.shared.tag.TagNode;

public class TagViewImpl extends Composite implements TagView {

	private static TagViewImplUiBinder uiBinder = GWT.create(TagViewImplUiBinder.class);

	interface TagViewImplUiBinder extends UiBinder<Widget, TagViewImpl> {
	}

	private TagView.TagPresenter presenter; 
	@UiField
	DockLayoutPanel mainPanel;
	
	@UiField
	FlowPanel statusPanel;
	
	@UiField
	Tree tagTree;
	
	@UiField
	Image mainImage;
	
	@UiField
	Label titleLabel;
	
	@UiField
	Label statusLabel;
	

	public TagViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
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
}
