package fspotcloud.client.main.view.api;

import fspotcloud.client.main.view.api.TagView.TagPresenter;
import fspotcloud.client.place.BasePlace;

public interface TagPresenterFactory {
	TagPresenter get(BasePlace place);
}
