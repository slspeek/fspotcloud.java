package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.shared.admin.GetMetaDataResult;
import fspotcloud.shared.dashboard.actions.CountPhotos;
import fspotcloud.shared.dashboard.actions.DeleteAllPhotos;
import fspotcloud.shared.dashboard.actions.DeleteAllTags;
import fspotcloud.shared.dashboard.actions.GetMetaData;
import fspotcloud.shared.dashboard.actions.ImportImageData;
import fspotcloud.shared.dashboard.actions.ResetSynchronizationPoint;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class GlobalActionsPresenter implements
		GlobalActionsView.GlobalActionsPresenter {
	private static final Logger log = Logger
			.getLogger(GlobalActionsPresenter.class.getName());

	final private GlobalActionsView globalActionsView;
	final private DispatchAsync dispatcher;

	@Inject
	public GlobalActionsPresenter(GlobalActionsView globalActionsView,
			DispatchAsync dispatcher) {
		super();
		this.globalActionsView = globalActionsView;
		globalActionsView.setPresenter(this);
		this.dispatcher = dispatcher;
	}

	@Override
	public void deleteAllPhotos() {
		globalActionsView.getDeleteAllPhotosButton().setEnabled(false);
		dispatcher.execute(new DeleteAllPhotos(),
				new AsyncCallback<VoidResult>() {

					@Override
					public void onFailure(Throwable caught) {
						enableButton();
					}

					@Override
					public void onSuccess(VoidResult result) {
						enableButton();

					}

					private void enableButton() {
						globalActionsView.getDeleteAllPhotosButton()
								.setEnabled(true);
					}
				});
	}

	@Override
	public void deleteAllTags() {
		globalActionsView.getDeleteAllTagsButton().setEnabled(false);
		dispatcher.execute(new DeleteAllTags(), new AsyncCallback<VoidResult>() {

			@Override
			public void onFailure(Throwable caught) {
				enableButton();

			}

			@Override
			public void onSuccess(VoidResult result) {
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
		dispatcher.execute(new ResetSynchronizationPoint(),
				new AsyncCallback<VoidResult>() {

					public void onFailure(Throwable caught) {
						enableButton();
					}

					public void onSuccess(VoidResult result) {
						enableButton();
					}

					private void enableButton() {
						globalActionsView.getResetMetaDataButton().setEnabled(
								true);
					}
				});

	}

	@Override
	public void update() {
		log.info("update");
		globalActionsView.getUpdateButton().setEnabled(false);
		dispatcher.execute(new SynchronizePeer(),
				new AsyncCallback<VoidResult>() {

					public void onFailure(Throwable caught) {
						enableButton();
					}

					@Override
					public void onSuccess(VoidResult result) {
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
		dispatcher.execute(new GetMetaData(),
				new AsyncCallback<GetMetaDataResult>() {
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
		dispatcher.execute(new CountPhotos(), new AsyncCallback<VoidResult>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(VoidResult result) {

			}
		});
	}

	@Override
	public void importImageData() {
		dispatcher.execute(new ImportImageData(),
				new AsyncCallback<VoidResult>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(VoidResult result) {
					}
				});

	}
}
