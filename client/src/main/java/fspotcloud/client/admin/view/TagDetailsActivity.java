package fspotcloud.client.admin.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.admin.view.api.TagDetailsView;
import fspotcloud.client.data.DataManager;
import fspotcloud.client.place.TagPlace;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.tag.TagNode;

public class TagDetailsActivity extends AbstractActivity implements
		TagDetailsView.TagDetailsPresenter {

	private static final Logger log = Logger.getLogger(TagDetailsActivity.class
			.getName());
	final private TagDetailsView tagDetailsView;
	final private TagPlace tagPlace;
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

		dispatch.execute(new ImportTag(tagPlace.getTagId()),
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

	private void populateView() {
		String tagId = tagPlace.getTagId();
		dataManager.getAdminTagNode(tagId, new AsyncCallback<TagNode>() {

			@Override
			public void onFailure(Throwable caught) {
				log.log(Level.SEVERE, "Trouble retrieving admin tag tree ", caught);
			}

			@Override
			public void onSuccess(TagNode result) {
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
		tagDetailsView.getTagLoadedImagesCountValue().setText(
				String.valueOf(tag.getCachedPhotoList().lastIndex() + 1));
	}
}
