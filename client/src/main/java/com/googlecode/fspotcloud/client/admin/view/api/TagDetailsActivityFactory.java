package com.googlecode.fspotcloud.client.admin.view.api;

import com.googlecode.fspotcloud.client.place.TagPlace;

public interface TagDetailsActivityFactory {

	TagDetailsView.TagDetailsPresenter get(TagPlace place);
}
