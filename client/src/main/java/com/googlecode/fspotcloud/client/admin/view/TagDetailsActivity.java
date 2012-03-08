package com.googlecode.fspotcloud.client.admin.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.googlecode.fspotcloud.client.admin.view.api.TagDetailsView;
import com.googlecode.fspotcloud.client.data.DataManager;
import com.googlecode.fspotcloud.client.place.TagPlace;
import com.googlecode.fspotcloud.shared.dashboard.actions.ImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.UnImportTag;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.tag.TagNode;

public class TagDetailsActivity extends AbstractActivity implements
		TagDetailsView.TagDetailsPresenter {

	private static final Logger log = Logger.getLogger(TagDetailsActivity.class
			.getName());
	final private TagDetailsView tagDetailsView;
	final private TagPlace tagPlace;
	private TagNode tagNode;
	final private DataManager dataManager;
	final private DispatchAsync dispatch;

	public TagDetailsActivity(TagDetailsView tagDetailsView, TagPlace tagPlace,
			DataManager dataManager, DispatchAsync dispatch) {
		super();
		this.tagDetailsView = tagDetailsView;
		this.tagPlace = tagPlace;
		this.dataManager = dataManager;
		this.dispatch = dispatch;
	}

	@Override
	public void init() {
		log.info("init");
		populateView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		tagDetailsView.setPresenter(this);
		panel.setWidget(tagDetailsView);
	}

	@Override
	public void importTag() {
		log.info("TagNode: " +tagNode + tagNode.isImportIssued());
		if (tagNode != null && tagNode.isImportIssued()) {
			dispatch.execute(new UnImportTag(tagPlace.getTagId()), new AsyncCallback<VoidResult>() {

						@Override
						public void onFailure(Throwable caught) {
							log.log(Level.SEVERE, "Action Exception ", caught);
						}

						@Override
						public void onSuccess(VoidResult result) {
							Window.Location.reload();
						}
					});
		} else {
			dispatch.execute(new ImportTag(tagPlace.getTagId(), 0),
					new AsyncCallback<VoidResult>() {

						@Override
						public void onFailure(Throwable caught) {
							log.log(Level.SEVERE, "Action Exception ", caught);
						}

						@Override
						public void onSuccess(VoidResult result) {
							Window.Location.reload();
						}
					});
		}
	}

	private void populateView() {
		String tagId = tagPlace.getTagId();
		dataManager.getAdminTagNode(tagId, new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.SEVERE, "Trouble retrieving admin tag tree ",
						caught);
			}

			@Override
			public void onSuccess(TagNode result) {
				tagNode = result;
				populateView(result);
				
			}
		});

	}

	private void populateView(TagNode tag) {
		tagDetailsView.getTagNameValue().setText(tag.getTagName());
		tagDetailsView.getTagDescriptionValue().setText(tag.getDescription());
		tagDetailsView.getTagImageCountValue().setText(
				String.valueOf(tag.getCount()));
		tagDetailsView.getTagImportIssuedValue().setText(
				tag.isImportIssued() ? "yes" : "no");
		// tagDetailsView.getImportButton().setEnabled(!tag.isImportIssued());
		tagDetailsView.getImportButtonText().setText(
				tag.isImportIssued() ? "Remove" : "Import");
		tagDetailsView.getTagLoadedImagesCountValue().setText(
				String.valueOf(tag.getCachedPhotoList().lastIndex() + 1));

	}
}
