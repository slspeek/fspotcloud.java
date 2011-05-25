package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.rpc.AdminServiceAsync;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void importTags() {
		globalActionsView.getImportTagsButton().setEnabled(false);
		adminServiceAsync.deleteAllTags(new AsyncCallback<Void>() {

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
		globalActionsView.getUpdateButton().setEnabled(false);
		adminServiceAsync.update(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				enableButton();
			}

			@Override
			public void onSuccess(Void result) {
				enableButton();
			}

			private void enableButton() {
				globalActionsView.getUpdateButton().setEnabled(true);
			}
		});
	}

	@Override
	public void init() {
		log.info("init");
		getPhotoCount();
	}

	private void getPhotoCount() {
		adminServiceAsync.getPhotoCount(new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				globalActionsView.getPhotoCountOnPeerValue().setText(
						String.valueOf(result));
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}
