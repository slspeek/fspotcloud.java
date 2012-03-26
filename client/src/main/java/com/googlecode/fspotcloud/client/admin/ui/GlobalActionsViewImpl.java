package com.googlecode.fspotcloud.client.admin.ui;

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

import com.googlecode.fspotcloud.client.admin.view.api.GlobalActionsView;

public class GlobalActionsViewImpl extends Composite implements
		GlobalActionsView {

	private static GlobalActionsViewImplUiBinder uiBinder = GWT
			.create(GlobalActionsViewImplUiBinder.class);

	interface GlobalActionsViewImplUiBinder extends
			UiBinder<Widget, GlobalActionsViewImpl> {
	}

	private GlobalActionsPresenter presenter;

	@UiField
	Label peerPhotoCountValueLabel;
	@UiField
	Label lastSeenPeerValueLabel;
	@UiField
	Label tagCountOnPeerValueLabel;
	@UiField
	Label pendingCommandCountValueLabel;
	@UiField
	Button updateButton;
	@UiField
	Button deleteAllTagsButton;
	@UiField
	Button deleteAllCommandsButton;
	
	public GlobalActionsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		deleteAllTagsButton.ensureDebugId("delete-all-tags-button");
		tagCountOnPeerValueLabel.ensureDebugId("tag-count-on-peer-label");
		peerPhotoCountValueLabel.ensureDebugId("peer-photo-count-label");
		updateButton.ensureDebugId("update-button");
		deleteAllCommandsButton.ensureDebugId("delete-all-photos-button");
		pendingCommandCountValueLabel
				.ensureDebugId("pending-command-count-label");
	}

	@UiHandler("deleteAllCommandsButton")
	public void onDeleteAllPhotosButtonClicked(ClickEvent event) {
		presenter.deleteAllCommands();
	}

	@UiHandler("updateButton")
	public void updateButtonClicked(ClickEvent event) {
		presenter.update();
	}

	@UiHandler("deleteAllTagsButton")
	public void deleteAllTagsButtonClicked(ClickEvent event) {
		presenter.deleteAllTags();
	}

	@Override
	public HasText getPhotoCountOnPeerValue() {
		return peerPhotoCountValueLabel;
	}

	@Override
	public HasEnabled getDeleteAllTagsButton() {
		return deleteAllTagsButton;
	}

	@Override
	public HasEnabled getDeleteAllCommandsButton() {
		return deleteAllCommandsButton;
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

	@Override
	public HasText getTagCountValue() {
		return tagCountOnPeerValueLabel;
	}

	@Override
	public HasText getPendingCommandCountValue() {
		return pendingCommandCountValueLabel;
	}

}