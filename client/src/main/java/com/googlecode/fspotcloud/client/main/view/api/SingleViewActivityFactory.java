package com.googlecode.fspotcloud.client.main.view.api;

import com.googlecode.fspotcloud.client.place.BasePlace;

public interface SingleViewActivityFactory {
	SingleImageView.SingleImagePresenter get(BasePlace place);
}
