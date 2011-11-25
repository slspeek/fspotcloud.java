package fspotcloud.client.admin.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.client.main.view.api.TimerInterface;
import fspotcloud.shared.admin.GetMetaDataResult;
import fspotcloud.shared.dashboard.actions.CommandDeleteAll;
import fspotcloud.shared.dashboard.actions.DeleteAllTags;
import fspotcloud.shared.dashboard.actions.GetMetaData;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class GlobalActionsPresenter implements
		GlobalActionsView.GlobalActionsPresenter {
	private static final Logger log = Logger
			.getLogger(GlobalActionsPresenter.class.getName());

	final private GlobalActionsView globalActionsView;
	final private DispatchAsync dispatcher;
	final private TimerInterface timer;

	@Inject
	public GlobalActionsPresenter(GlobalActionsView globalActionsView,
			DispatchAsync dispatcher, TimerInterface timer) {
		super();
		this.timer = timer;
		this.globalActionsView = globalActionsView;
		globalActionsView.setPresenter(this);
		this.dispatcher = dispatcher;
	}

	@Override
	public void deleteAllCommands() {
		globalActionsView.getDeleteAllCommandsButton().setEnabled(false);
		dispatcher.execute(new CommandDeleteAll(),
				new AsyncCallback<VoidResult>() {

					@Override
					public void onFailure(Throwable caught) {
						log.log(Level.SEVERE, "Action Exception ", caught);
						globalActionsView.getDeleteAllCommandsButton()
								.setEnabled(true);
					}

					@Override
					public void onSuccess(VoidResult result) {
						globalActionsView.getDeleteAllCommandsButton()
								.setEnabled(true);
					}

				});
	}

	@Override
	public void deleteAllTags() {
		globalActionsView.getDeleteAllTagsButton().setEnabled(false);
		dispatcher.execute(new DeleteAllTags(),
				new AsyncCallback<VoidResult>() {

					@Override
					public void onFailure(Throwable caught) {
						log.log(Level.SEVERE, "Action Exception ", caught);
					}

					@Override
					public void onSuccess(VoidResult result) {
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
						log.log(Level.SEVERE, "Action Exception ", caught);
					}

					@Override
					public void onSuccess(VoidResult result) {
						log.info("succes update");
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
						log.log(Level.SEVERE, "Action Exception from remote: ",
								caught);
						timer.setRunnable(new Runnable() {

							@Override
							public void run() {
								getMetaData();
							}
						});
						timer.schedule(6000);
					}
				});

	}

	private void populateView(GetMetaDataResult info) {
		log.info("populate");
		globalActionsView.getLastSeenPeerValue().setText(
				String.valueOf(info.getPeerLastSeen()));
		globalActionsView.getPhotoCountOnPeerValue().setText(
				String.valueOf(info.getPeerPhotoCount()));
		globalActionsView.getTagCountValue().setText(
				String.valueOf(info.getTagCount()));
		globalActionsView.getPendingCommandCountValue().setText(
				String.valueOf(info.getPendingCommandCount()));

		globalActionsView.getDeleteAllTagsButton().setEnabled(
				!info.isDeleteTagsActive());
		timer.setRunnable(new Runnable() {
			@Override
			public void run() {
				getMetaData();
			}
		});
		timer.schedule(7000);
	}

}
