package fspotcloud.client.admin.gin;

import com.google.inject.Inject;

import fspotcloud.client.admin.view.TagDetailsActivity;
import fspotcloud.client.admin.view.api.TagDetailsActivityFactory;
import fspotcloud.client.admin.view.api.TagDetailsView;
import fspotcloud.client.admin.view.api.TagDetailsView.TagDetailsPresenter;
import fspotcloud.client.data.DataManager;
import fspotcloud.client.place.TagPlace;
import fspotcloud.rpc.AdminServiceAsync;

public class TagDetailsActivityFactoryImpl implements TagDetailsActivityFactory {

	final private TagDetailsView tagDetailsView;
	final private DataManager dataManager;
	final private AdminServiceAsync adminServiceAsync;

	@Inject
	public TagDetailsActivityFactoryImpl(TagDetailsView tagDetailsView,
			DataManager dataManager, AdminServiceAsync adminServiceAsync) {
		super();
		this.tagDetailsView = tagDetailsView;
		this.dataManager = dataManager;
		this.adminServiceAsync = adminServiceAsync;
	}

	@Override
	public TagDetailsPresenter get(TagPlace place) {
		TagDetailsPresenter presenter = new TagDetailsActivity(tagDetailsView,
				place, dataManager, adminServiceAsync);
		presenter.init();
		return presenter;
	}

}
