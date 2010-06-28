package fspotcloud.client.admin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Admin implements EntryPoint {

	private DockLayoutPanel dockLayout = new DockLayoutPanel(Unit.PX);
	private Label titleLabel = new Label("F-Spot Cloud Admin");

	private Grid mainGrid = new Grid(3, 1);

	private Label photoCountLabel = new Label("Photo count on the peer: ");
	private FlowPanel infoPanel = new FlowPanel();

	private Label deleteAllTagsLabel = new Label(
			"Clears all tags imported from the peer.");
	private Button deleteAllTagsButton = new Button("Clear tags");
	private Label deleteAllPhotosLabel = new Label(
			"Clears all photos imported from the peer.");
	private Button deleteAllPhotosButton = new Button("Clear photos");
	private Label importTagsLabel = new Label(
			"Schedule an import of the tags from the peer");
	private Button importTagsButton = new Button("Import tags");
	private FlexTable actionTable = new FlexTable();

	private FlowPanel cachePanel = new FlowPanel();
	private FlowPanel statusPanel = new FlowPanel();
	private Label statusLabel = new Label("Status bar. Ready.");

	private final AdminServiceAsync adminService = GWT
			.create(AdminService.class);

	@Override
	public void onModuleLoad() {
		dockLayout.addNorth(titleLabel, 80);
		titleLabel.addStyleDependentName("title");

		infoPanel.add(photoCountLabel);

		actionTable.setWidget(0, 0, deleteAllTagsLabel);
		actionTable.setWidget(0, 1, deleteAllTagsButton);

		actionTable.setWidget(1, 0, deleteAllPhotosLabel);
		actionTable.setWidget(1, 1, deleteAllPhotosButton);

		actionTable.setWidget(2, 0, importTagsLabel);
		actionTable.setWidget(2, 1, importTagsButton);

		mainGrid.setWidget(0, 0, infoPanel);
		mainGrid.setWidget(1, 0, actionTable);
		mainGrid.setWidget(2, 0, cachePanel);

		statusPanel.add(statusLabel);
		dockLayout.addSouth(statusPanel, 100);
		dockLayout.add(mainGrid);

		RootLayoutPanel.get().add(dockLayout);
		addClickHandlers();
		getPhotoCount();

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
	}

	private void getPhotoCount() {
		adminService.getPhotoCount(new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				photoCountLabel.setTitle("Succes");
				photoCountLabel.setText("Photo count on the peer: " + result);
			}

			@Override
			public void onFailure(Throwable caught) {
				statusLabel.setText("Not able to get photo count "
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
		statusLabel.setText("Waiting on remote to delete all Photos");
		adminService.deleteAllPhotos(new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				deleteAllPhotosButton.setEnabled(true);
				statusLabel.setText(caught.getLocalizedMessage());
			}

			public void onSuccess(Void result) {
				deleteAllPhotosButton.setEnabled(true);
				statusLabel.setText("All Photos were deleted");
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