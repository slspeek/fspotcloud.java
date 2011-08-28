package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.rpc.AdminServiceAsync;
import fspotcloud.shared.actions.GetMetaData;
import fspotcloud.shared.admin.GetMetaDataResult;

public class GlobalActionsPresenter implements
		GlobalActionsView.GlobalActionsPresenter {
	private static final Logger log = Logger
			.getLogger(GlobalActionsPresenter.class.getName());

	final private GlobalActionsView globalActionsView;
	final private AdminServiceAsync adminServiceAsync;
	final private DispatchAsync dispatcher;

	@Inject
	public GlobalActionsPresenter(GlobalActionsView globalActionsView,
			AdminServiceAsync adminServiceAsync,
			DispatchAsync dispatcher) {
		super();
		this.globalActionsView = globalActionsView;
		globalActionsView.setPresenter(this);
		this.adminServiceAsync = adminServiceAsync;
		this.dispatcher = dispatcher;
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
	public void resetPeerPhotoCount() {
		globalActionsView.getResetMetaDataButton().setEnabled(false);
		adminServiceAsync.resetPeerPhotoCount(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				enableButton();
			}

			public void onSuccess(Void result) {
				enableButton();
			}

			private void enableButton() {
				globalActionsView.getResetMetaDataButton().setEnabled(true);
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
		dispatcher.execute(new GetMetaData(), new AsyncCallback<GetMetaDataResult>() {
			@Override
			public void onSuccess(GetMetaDataResult meta) {
				populateView(meta);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
	}

	private void populateView(GetMetaDataResult info) {
		log.info("populate");
		globalActionsView.getLastSeenPeerValue().setText(
				String.valueOf(info.getPeerLastSeen()));
		globalActionsView.getPhotoCountOnPeerValue().setText(
				String.valueOf(info.getPeerPhotoCount()));
		globalActionsView.getPhotoCountValue().setText(
				String.valueOf(info.getPhotoCount()));
		globalActionsView.getTagCountValue().setText(
				String.valueOf(info.getTagCount()));
		globalActionsView.getPendingCommandCountValue().setText(
				String.valueOf(info.getPendingCommandCount()));
	}

	@Override
	public void countPhotos() {
		adminServiceAsync.countPhotos(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Void result) {

			}
		});
	}

	@Override
	public void importImageData() {
		adminServiceAsync.importImageData(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}
		});

	}
}
