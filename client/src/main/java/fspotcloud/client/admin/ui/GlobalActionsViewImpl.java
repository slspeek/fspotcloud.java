package fspotcloud.client.admin.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.admin.view.GlobalActionsView;

public class GlobalActionsViewImpl extends Composite implements
		GlobalActionsView {

	private static GlobalActionsViewImplUiBinder uiBinder = GWT
			.create(GlobalActionsViewImplUiBinder.class);

	interface GlobalActionsViewImplUiBinder extends
			UiBinder<Widget, GlobalActionsViewImpl> {
	}

	@UiField
	Label headerLabel;
	@UiField
	Label photoCountLabel;
	@UiField
	Label photoCountValueLabel;
	@UiField
	Label peerPhotoCountLabel;
	@UiField
	Label peerPhotoCountValueLabel;
	@UiField
	Label lastSeenPeerLabel;
	@UiField
	Label lastSeenPeerValueLabel;
	@UiField
	Label importTagsLabel;
	@UiField
	Button importTagButton;
	@UiField
	Label updateLabel;
	@UiField
	Button updateButton;
	@UiField
	Label deleteAllTagsLabel;
	@UiField
	Button deleteAllTagsButton;
	@UiField
	Label deleteAllPhotosLabel;
	@UiField
	Button deleteAllPhotosButton;

	
	public GlobalActionsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasText getHeader() {
		return headerLabel;
	}

	@Override
	public HasText getPhotoCount() {
		return photoCountLabel;
	}

	@Override
	public HasText getPhotoCountValue() {
		return photoCountValueLabel;
	}

	@Override
	public HasText getPhotoCountOnPeer() {
		return peerPhotoCountLabel;
	}

	@Override
	public HasText getPhotoCountOnPeerValue() {
		return peerPhotoCountValueLabel;
	}

	@Override
	public HasText getDeleteAllTagsDescription() {
		return deleteAllTagsLabel;
	}

	@Override
	public HasEnabled getDeleteTagsAllButton() {
		return deleteAllTagsButton;
	}

	@Override
	public HasText getDeleteAllPhotosDescription() {
		return deleteAllPhotosLabel;
	}

	@Override
	public HasEnabled getDeleteAllPhotosButton() {
		return deleteAllPhotosButton;
	}

	@Override
	public HasText getImportTagsDescription() {
		return importTagsLabel;
	}

	@Override
	public HasEnabled getImportTagsButton() {
		return importTagButton;
	}

	@Override
	public HasText getUpdateButtonDescription() {
		return updateLabel;
	}

	@Override
	public HasEnabled getUpdateButton() {
		return updateButton;
	}

	@Override
	public HasText getLastSeenPeer() {
		return lastSeenPeerLabel;
	}

	@Override
	public HasText getLastSeenPeerValue() {
		return lastSeenPeerValueLabel;
	}

}
