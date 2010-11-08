package fspotcloud.client.admin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TreeItem;

import fspotcloud.client.main.ui.TreePanel;
import fspotcloud.rpc.AdminService;
import fspotcloud.rpc.AdminServiceAsync;
import fspotcloud.rpc.TagService;
import fspotcloud.rpc.TagServiceAsync;
import fspotcloud.shared.admin.BatchInfo;

public class Admin implements EntryPoint {

	private static final String SCHEDULE_UPDATE_WITH_THE_PEER = "Schedule update with the peer";
	private static final String CLEARS_ALL_PHOTOS_IMPORTED_FROM_THE_PEER = "Clears all photos imported from the peer.";
	private DockLayoutPanel dockLayout = new DockLayoutPanel(Unit.PX);
	private Label titleLabel = new Label("F-Spot Cloud Admin");

	private Grid mainGrid = new Grid(4, 1);

	private FlexTable infoTable = new FlexTable();

	private Label deleteAllTagsLabel = new Label(
			"Clears all tags imported from the peer.");
	private Button deleteAllTagsButton = new Button("Clear tags");
	private Label deleteAllPhotosLabel = new Label(
			CLEARS_ALL_PHOTOS_IMPORTED_FROM_THE_PEER);
	private Button deleteAllPhotosButton = new Button("Clear photos");
	private Label importTagsLabel = new Label(
			"Schedule an import of the tags from the peer");
	private Button importTagsButton = new Button("Import tags");

	private Label startUpdateWithPeerLabel = new Label(
			SCHEDULE_UPDATE_WITH_THE_PEER);
	private Button startUpdateWithPeerButton = new Button("Update");

	private Label calculateTagViewLabel = new Label(
			"Calculate the viewable photos for a tag");
	private Button calculateTagViewButton = new Button("Calculate");

	// private Label importImagesForTagLabel = new
	// Label("Schedule an import of the tags from the peer");

	private FlexTable actionTable = new FlexTable();

	private FlowPanel cachePanel = new FlowPanel();
	private FlowPanel statusPanel = new FlowPanel();
	private Label statusLabel = new Label("Status bar. Ready.");
	private long serverPhotoCountBatchId = 0;
	private Timer serverPhotoCountTimer = new Timer() {
		public void run() {
			getBatchInfoForPhotoCount(serverPhotoCountBatchId);
		}
	};

	private long deleteAllPhotosBatchId = 0;
	private Timer deleteAllPhotosTimer = new Timer() {
		public void run() {
			getBatchInfoForDeleteAllPhotos(deleteAllPhotosBatchId);
		}
	};

	private long calculateTagViewablePhotosBatchId = 0;
	private Timer calculateTagViewablePhotosTimer = new Timer() {
		public void run() {
			getBatchInfoForTagView(calculateTagViewablePhotosBatchId);
		}
	};

	private final AdminServiceAsync adminService = GWT
			.create(AdminService.class);
	
	private final TagServiceAsync tagService = GWT
	.create(TagService.class);

	private final SelectionHandler<TreeItem> treeHandler = new SelectionHandler<TreeItem>() {

		@Override
		public void onSelection(SelectionEvent<TreeItem> event) {
			TreeItem item = event.getSelectedItem();
			String tagId = (String) item.getUserObject();
			statusLabel.setText("Tag " + tagId + " selected.");
		}
	};
	@Override
	public void onModuleLoad() {
		dockLayout.addNorth(titleLabel, 80);
		titleLabel.addStyleDependentName("title");

		infoTable.setText(0, 0, "Photo count on the peer");
		infoTable.setText(1, 0, "Photo count on the server");

		actionTable.setWidget(0, 0, deleteAllTagsLabel);
		actionTable.setWidget(0, 1, deleteAllTagsButton);

		actionTable.setWidget(1, 0, deleteAllPhotosLabel);
		actionTable.setWidget(1, 1, deleteAllPhotosButton);

		actionTable.setWidget(2, 0, importTagsLabel);
		actionTable.setWidget(2, 1, importTagsButton);

		actionTable.setWidget(3, 0, startUpdateWithPeerLabel);
		actionTable.setWidget(3, 1, startUpdateWithPeerButton);
		
		actionTable.setWidget(4, 0, calculateTagViewLabel);
		actionTable.setWidget(4, 1, calculateTagViewButton);

		mainGrid.setWidget(0, 0, infoTable);
		mainGrid.setWidget(1, 0, actionTable);
		mainGrid.setWidget(2, 0, cachePanel);
		
		
		TreePanel treePanel = new TreePanel(tagService, treeHandler, statusLabel);
		TagPanel tagPanel = new TagPanel(tagService, adminService, treePanel);
		//mainGrid.setWidget(3, 0, tagPanel);
		mainGrid.setText(3, 0, "Test");
		statusPanel.add(statusLabel);
		dockLayout.addWest(tagPanel, 500);
		dockLayout.addSouth(statusPanel, 100);
		dockLayout.add(mainGrid);
		
		RootLayoutPanel.get().add(dockLayout);
		addClickHandlers();
		getPhotoCount();
		getServerPhotoCount();

	}

	private void addClickHandlers() {
		importTagsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				importTags();
			}
		});
		deleteAllTagsButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteAllTags();
			}
		});
		deleteAllPhotosButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteAllPhotos();
			}
		});
		startUpdateWithPeerButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				startUpdate();
			}
		});
		calculateTagViewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				calculateTagViewablePhotos("46");
			}
		});
	}

	protected void startUpdate() {
		startUpdateWithPeerButton.setEnabled(false);
		statusLabel.setText("Waiting on remote to schedule update");
		adminService.update(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				statusLabel.setText(caught.getLocalizedMessage());
				startUpdateWithPeerButton.setEnabled(true);
			}

			@Override
			public void onSuccess(Void result) {
				statusLabel.setText("Update photo metadata was scheduled for the peer");
				startUpdateWithPeerButton.setEnabled(true);

			}
		});

	}

	
	private void calculateTagViewablePhotos(final String tagId) {
		calculateTagViewButton.setEnabled(false);
		adminService.tagViewablePhotos(tagId, new AsyncCallback<Long>() {
			@Override
			public void onSuccess(Long result) {
				statusLabel.setText("Started calculating the photos for tag: " + tagId);
				calculateTagViewablePhotosBatchId = result;
				calculateTagViewablePhotosTimer.scheduleRepeating(1000);
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to start calculating the photos for tag: " + tagId +
					" "	+ caught.getLocalizedMessage());
			}
		});
	}

	private void getBatchInfoForTagView(long batchId) {
		adminService.getBatchInfo(batchId, new AsyncCallback<BatchInfo>() {
			@Override
			public void onSuccess(BatchInfo info) {
				String text = "Running tag calculation (" + info.getInterationCount() + ")";
				calculateTagViewLabel.setText(text);
				if (!info.isRunning()) {
					calculateTagViewButton.setEnabled(true);
					calculateTagViewablePhotosTimer.cancel();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to get batch info: "
						+ caught.getLocalizedMessage());
			}
		});
	}

	private void getPhotoCount() {
		adminService.getPhotoCount(new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				infoTable.setText(0, 0, "Photo count on the peer: ");
				infoTable.setText(0, 1, result.toString());
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to get photo count "
						+ caught.getLocalizedMessage());
			}
		});
	}

	private void getServerPhotoCount() {
		adminService.getServerPhotoCount(new AsyncCallback<Long>() {
			@Override
			public void onSuccess(Long result) {
				infoTable.setText(1, 0, "Server BatchID : ");
				serverPhotoCountBatchId = result;
				serverPhotoCountTimer.scheduleRepeating(1000);
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to get server photo count going"
						+ caught.getLocalizedMessage());
			}
		});
	}

	private void getBatchInfoForPhotoCount(long batchId) {
		adminService.getBatchInfo(batchId, new AsyncCallback<BatchInfo>() {
			@Override
			public void onSuccess(BatchInfo info) {
				infoTable.setText(1, 0,
						"Server Batch photoCount on server intermediate result: "
								+ info.getResult() + "("
								+ info.getInterationCount() + ")");

				if (!info.isRunning()) {
					infoTable.setText(1, 0, "Server Photo count ");
					infoTable.setText(1, 1, info.getResult());
					infoTable.setText(1, 2, "(" + info.getInterationCount()
							+ ")");
					serverPhotoCountTimer.cancel();
					statusLabel.setText("ServerPhotoCount batch finished.");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to get batch info: "
						+ caught.getLocalizedMessage());
			}
		});
	}

	private void getBatchInfoForDeleteAllPhotos(long batchId) {
		adminService.getBatchInfo(batchId, new AsyncCallback<BatchInfo>() {
			@Override
			public void onSuccess(BatchInfo info) {
				String msg = "Delete all photos intermediate result: "
						+ info.getResult() + "("
						+ String.valueOf(info.getInterationCount()) + ")";
				deleteAllPhotosLabel.setText(msg);
				if (!info.isRunning()) {
					deleteAllPhotosTimer.cancel();
					deleteAllPhotosButton.setEnabled(true);
					deleteAllPhotosLabel
							.setText(CLEARS_ALL_PHOTOS_IMPORTED_FROM_THE_PEER);
					statusLabel.setText("deleteAllPhotos batch "
							+ deleteAllPhotosBatchId
							+ " finished on the server. " + info.getResult()
							+ " photos were deleted.");
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to get batch info: "
						+ caught.getLocalizedMessage());
			}
		});
	}

	private void deleteAllTags() {
		deleteAllTagsButton.setEnabled(false);
		statusLabel.setText("Waiting on remote to delete all tags");
		adminService.deleteAllTags(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				deleteAllTagsButton.setEnabled(true);
				statusLabel.setText(caught.getLocalizedMessage());
			}

			public void onSuccess(Void result) {
				deleteAllTagsButton.setEnabled(true);
				statusLabel.setText("All tags were deleted");
			}
		});
	}

	private void deleteAllPhotos() {
		deleteAllPhotosButton.setEnabled(false);
		statusLabel.setText("Waiting on remote to start deleteAllPhotos task");
		adminService.deleteAllPhotos(new AsyncCallback<Long>() {

			public void onFailure(Throwable caught) {
				deleteAllPhotosButton.setEnabled(true);
				statusLabel.setText(caught.getLocalizedMessage());
			}

			public void onSuccess(Long result) {
				statusLabel.setText("deleteAllPhotos task started");
				deleteAllPhotosBatchId = result;
				deleteAllPhotosTimer.scheduleRepeating(1000);
			}
		});
	}

	private void importTags() {
		importTagsButton.setEnabled(false);
		statusLabel.setText("Waiting on remote to import tags");
		adminService.importTags(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				statusLabel.setText(caught.getLocalizedMessage());
				importTagsButton.setEnabled(true);
			}

			@Override
			public void onSuccess(Void result) {
				statusLabel.setText("Import tags was scheduled for the peer");
				importTagsButton.setEnabled(true);
			}
		});
	}

}
