package fspotcloud.client.admin.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
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
	Label tagNameValueLabel;
	@UiField
	Label tagDescriptionValueLabel;

	@UiField
	Label tagImportIssuedValueLabel;

	@UiField
	Label tagCountValueLabel;
	@UiField
	Label tagLoadedCountValueLabel;
	@UiField
	Button importTagButton;

	private TagDetailsPresenter presenter;

	public TagDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		importTagButton.ensureDebugId("import-tag-button");
	}

	@Override
	public HasText getTagDescriptionValue() {
		return tagDescriptionValueLabel;
	}

		@Override
	public HasText getTagImageCountValue() {
		return tagCountValueLabel;
	}

	
	@Override
	public HasText getTagImportIssuedValue() {
		return tagImportIssuedValueLabel;
	}

	
	@Override
	public HasText getTagLoadedImagesCountValue() {
		return tagLoadedCountValueLabel;
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

	@Override
	public HasEnabled getImportButton() {
		return importTagButton;
	}

	@Override
	public HasText getImportButtonText() {
		return importTagButton;
	}

}
