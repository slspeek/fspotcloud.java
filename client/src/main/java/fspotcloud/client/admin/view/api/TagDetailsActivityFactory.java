package fspotcloud.client.admin.view.api;

import fspotcloud.client.place.TagPlace;

public interface TagDetailsActivityFactory {

	TagDetailsView.TagDetailsPresenter get(TagPlace place);
}
