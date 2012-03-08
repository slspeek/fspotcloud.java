package com.googlecode.fspotcloud.client.main.view.api;

import com.googlecode.fspotcloud.client.main.view.api.TagView.TagPresenter;
import com.googlecode.fspotcloud.client.place.BasePlace;

public interface TagPresenterFactory {
	TagPresenter get(BasePlace place);
}
