package fspotcloud.client.admin.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.admin.view.api.TagDetailsView;

public class TagDetailsViewImpl extends Composite implements TagDetailsView {

	interface TagDetailsViewImplUiBinder extends
			UiBinder<Widget, TagDetailsViewImpl> {
	}

	private static TagDetailsViewImplUiBinder uiBinder = GWT
			.create(TagDetailsViewImplUiBinder.class);

	@UiField
	Label headerLabel;
	@UiField
	Label tagNameLabel;
	@UiField
	Label tagNameValueLabel;
	@UiField
	Label tagDescriptionLabel;
	@UiField
	Label tagDescriptionValueLabel;

	@UiField
	Label tagImportIssuedLabel;
	@UiField
	Label tagImportIssuedValueLabel;

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
	
	private TagDetailsPresenter presenter;

	public TagDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public HasText getTagDescription() {
		return tagDescriptionLabel;
	}

	@Override
	public HasText getTagDescriptionValue() {
		return tagDescriptionValueLabel;
	}

	public HasText getTagImageCount() {
		return tagCountLabel;
	}

	@Override
	public HasText getTagImageCountValue() {
		return tagCountValueLabel;
	}

	@Override
	public HasText getTagImportIssued() {
		return tagImportIssuedLabel;
	}

	@Override
	public HasText getTagImportIssuedValue() {
		return tagImportIssuedValueLabel;
	}

	public HasText getTagLoadedImagesCount() {
		return tagLoadedCountLabel;
	}

	@Override
	public HasText getTagLoadedImagesCountValue() {
		return tagLoadedCountValueLabel;
	}

	@Override
	public HasText getTagName() {
		return tagNameLabel;
	}

	@Override
	public HasText getTagNameValue() {
		return tagNameValueLabel;
	}

	@UiHandler("importTagButton")
	public void onImportClicked(ClickEvent event) {
		presenter.importTag();
	}

	@Override
	public void setPresenter(TagDetailsPresenter presenter) {
		this.presenter = presenter;

	}

}
