package fspotcloud.client.main.view.api;

import fspotcloud.client.place.BasePlace;

public interface SingleViewActivityFactory {
	SingleImageView.SingleImagePresenter get(BasePlace place);
}
