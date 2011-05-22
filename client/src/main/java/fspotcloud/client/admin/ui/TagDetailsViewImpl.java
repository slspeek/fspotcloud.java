package fspotcloud.client.admin.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.admin.view.api.TagDetailsView;

public class TagDetailsViewImpl extends Composite implements TagDetailsView {

	private static TagDetailsViewImplUiBinder uiBinder = GWT
			.create(TagDetailsViewImplUiBinder.class);

	interface TagDetailsViewImplUiBinder extends
			UiBinder<Widget, TagDetailsViewImpl> {
	}

	@UiField
	Label headerLabel;
	@UiField
	Label tagNameLabel;
	@UiField
	Label tagNameValueLabel;
	@UiField
	Label tagDesciptionLabel;
	@UiField
	Label tagDesciptionValueLabel;

	@UiField
	Label tagCountLabel;
	@UiField
	Label tagCountValueLabel;
	@UiField
	Label tagLoadedCountLabel;
	@UiField
	Label tagLoadedCountValueLabel;
	@UiField
	Label importTagsLabel;
	@UiField
	Button importTagButton;

	public TagDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasText getTagName() {
		return tagNameLabel;
	}

	@Override
	public HasText getTagDescription() {
		return tagDesciptionLabel;
	}

	public HasText getTagLoadedImagesCount() {
		return tagLoadedCountLabel;
	}

	public HasText getTagImageCount() {
		return tagCountLabel;
	}

	@Override
	public HasText getTagNameValue() {
		return tagNameValueLabel;
	}

	@Override
	public HasText getTagDescriptionValue() {
		return tagDesciptionValueLabel;
	}

	@Override
	public HasText getTagLoadedImagesCountValue() {
		return tagLoadedCountValueLabel;
	}

	@Override
	public HasText getTagImageCountValue() {
		return tagCountValueLabel;
	}

}
