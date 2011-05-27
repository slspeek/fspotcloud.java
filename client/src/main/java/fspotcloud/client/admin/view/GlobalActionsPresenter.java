package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.rpc.AdminServiceAsync;
import fspotcloud.shared.admin.MetaDataInfo;

public class GlobalActionsPresenter implements
		GlobalActionsView.GlobalActionsPresenter {
	private static final Logger log = Logger
			.getLogger(GlobalActionsPresenter.class.getName());

	final private GlobalActionsView globalActionsView;
	final private AdminServiceAsync adminServiceAsync;

	@Inject
	public GlobalActionsPresenter(GlobalActionsView globalActionsView,
			AdminServiceAsync adminServiceAsync) {
		super();
		this.globalActionsView = globalActionsView;
		globalActionsView.setPresenter(this);
		this.adminServiceAsync = adminServiceAsync;
	}

	@Override
	public void deleteAllPhotos() {
		globalActionsView.getDeleteAllPhotosButton().setEnabled(false);
		adminServiceAsync.deleteAllPhotos(new AsyncCallback<Long>() {

			@Override
			public void onFailure(Throwable caught) {
				enableButton();

			}

			@Override
			public void onSuccess(Long result) {
				enableButton();

			}

			private void enableButton() {
				globalActionsView.getDeleteAllPhotosButton().setEnabled(true);
			}
		});
	}

	@Override
	public void deleteAllTags() {
		globalActionsView.getDeleteAllTagsButton().setEnabled(false);
		adminServiceAsync.deleteAllTags(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				enableButton();

			}

			@Override
			public void onSuccess(Void result) {
				enableButton();

			}

			private void enableButton() {
				globalActionsView.getDeleteAllTagsButton().setEnabled(true);
			}
		});
		

	}

	@Override
	public void importTags() {
		globalActionsView.getImportTagsButton().setEnabled(false);
		adminServiceAsync.importTags(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				enableButton();
			}

			public void onSuccess(Void result) {
				enableButton();
			}

			private void enableButton() {
				globalActionsView.getImportTagsButton().setEnabled(true);
			}
		});

	}

	@Override
	public void update() {
		log.info("update");
		globalActionsView.getUpdateButton().setEnabled(false);
		adminServiceAsync.update(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				enableButton();
			}

			@Override
			public void onSuccess(Void result) {
				log.info("succes update");
				enableButton();
			}

			private void enableButton() {
				globalActionsView.getUpdateButton().setEnabled(true);
			}
		});
	}

	@Override
	public void init() {
		globalActionsView.setPresenter(this);
		log.info("init");
		getMetaData();
	}

	private void getMetaData() {
		adminServiceAsync.getMetaData(new AsyncCallback<MetaDataInfo>() {
			@Override
			public void onSuccess(MetaDataInfo meta) {
				populateView(meta);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	
	private void populateView(MetaDataInfo info) {
		log.info("populate");
		globalActionsView.getLastSeenPeerValue().setText(String.valueOf(info.getPeerLastSeen()));
		globalActionsView.getPhotoCountOnPeerValue().setText(String.valueOf(info.getPeerPhotoCount()));
		
		
	}
}
