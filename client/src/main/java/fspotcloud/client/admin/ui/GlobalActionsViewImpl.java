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

import fspotcloud.client.admin.view.api.GlobalActionsView;

public class GlobalActionsViewImpl extends Composite implements
		GlobalActionsView {

	private static GlobalActionsViewImplUiBinder uiBinder = GWT
			.create(GlobalActionsViewImplUiBinder.class);

	interface GlobalActionsViewImplUiBinder extends
			UiBinder<Widget, GlobalActionsViewImpl> {
	}

	private GlobalActionsPresenter presenter;

	@UiField
	Label photoCountValueLabel;
	@UiField
	Label peerPhotoCountValueLabel;
	@UiField
	Label lastSeenPeerValueLabel;
	@UiField
	Button importTagsButton;
	@UiField
	Button updateButton;
	@UiField
	Button deleteAllTagsButton;
	@UiField
	Button deleteAllPhotosButton;

	public GlobalActionsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("importTagsButton")
	public void importButtonClicked(ClickEvent event) {
		presenter.importTags();
	}
	
	@UiHandler("deleteAllPhotosButton")
	public void onDeleteAllPhotosButtonClicked(ClickEvent event) {
		presenter.deleteAllPhotos();
	}


	@Override
	public HasText getPhotoCountValue() {
		return photoCountValueLabel;
	}

	@Override
	public HasText getPhotoCountOnPeerValue() {
		return peerPhotoCountValueLabel;
	}

	@Override
	public HasEnabled getDeleteTagsAllButton() {
		return deleteAllTagsButton;
	}

	@Override
	public HasEnabled getDeleteAllPhotosButton() {
		return deleteAllPhotosButton;
	}

	@Override
	public HasEnabled getImportTagsButton() {
		return importTagsButton;
	}

	@Override
	public HasEnabled getUpdateButton() {
		return updateButton;
	}

	@Override
	public HasText getLastSeenPeerValue() {
		return lastSeenPeerValueLabel;
	}

	@Override
	public void setPresenter(GlobalActionsPresenter presenter) {
		this.presenter = presenter;
	}

}
