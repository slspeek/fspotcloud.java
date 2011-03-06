package fspotcloud.client.view;

import fspotcloud.client.view.TagView.TagPresenter;

public interface TagPresenterFactory {
	TagPresenter get(BasePlace place);
}
