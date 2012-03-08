package com.googlecode.fspotcloud.client.admin.gin;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.admin.view.TagDetailsActivity;
import com.googlecode.fspotcloud.client.admin.view.api.TagDetailsActivityFactory;
import com.googlecode.fspotcloud.client.admin.view.api.TagDetailsView;
import com.googlecode.fspotcloud.client.admin.view.api.TagDetailsView.TagDetailsPresenter;
import com.googlecode.fspotcloud.client.data.DataManager;
import com.googlecode.fspotcloud.client.place.TagPlace;

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
