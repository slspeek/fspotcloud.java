package fspotcloud.client.admin.gin;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.inject.Inject;

import fspotcloud.client.admin.view.TagDetailsActivity;
import fspotcloud.client.admin.view.api.TagDetailsActivityFactory;
import fspotcloud.client.admin.view.api.TagDetailsView;
import fspotcloud.client.admin.view.api.TagDetailsView.TagDetailsPresenter;
import fspotcloud.client.data.DataManager;
import fspotcloud.client.place.TagPlace;

public class TagDetailsActivityFactoryImpl implements TagDetailsActivityFactory {

	final private TagDetailsView tagDetailsView;
	final private DataManager dataManager;
	final private DispatchAsync dispatch;

	@Inject
	public TagDetailsActivityFactoryImpl(TagDetailsView tagDetailsView,
			DataManager dataManager, DispatchAsync dispatch) {
		super();
		this.tagDetailsView = tagDetailsView;
		this.dataManager = dataManager;
		this.dispatch = dispatch;
	}

	@Override
	public TagDetailsPresenter get(TagPlace place) {
		TagDetailsPresenter presenter = new TagDetailsActivity(tagDetailsView,
				place, dataManager, dispatch);
		presenter.init();
		return presenter;
	}

}
