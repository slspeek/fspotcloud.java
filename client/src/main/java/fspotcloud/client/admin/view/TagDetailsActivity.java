package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.admin.view.api.TagDetailsView;
import fspotcloud.client.data.DataManager;
import fspotcloud.client.place.TagPlace;
import fspotcloud.rpc.AdminServiceAsync;

public class TagDetailsActivity extends AbstractActivity implements
		TagDetailsView.TagDetailsPresenter {

	private static final Logger log = Logger.getLogger(TagDetailsActivity.class
			.getName());
	final private TagDetailsView tagDetailsView;
	final private TagPlace tagPlace;
	final private DataManager dataManager;
	final private AdminServiceAsync adminServiceAsync;

	public TagDetailsActivity(TagDetailsView tagDetailsView, TagPlace tagPlace,
			DataManager dataManager, AdminServiceAsync adminServiceAsync) {
		super();
		this.tagDetailsView = tagDetailsView;
		this.tagPlace = tagPlace;
		this.dataManager = dataManager;
		this.adminServiceAsync = adminServiceAsync;
	}

	@Override
	public void init() {
		log.info("init");
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		tagDetailsView.setPresenter(this);
		panel.setWidget(tagDetailsView);
	}

	@Override
	public void importTag() {
		adminServiceAsync.importTag(tagPlace.getTagId(),
				new AsyncCallback<Void>() {

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
