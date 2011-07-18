package fspotcloud.client.main.view.api;

import fspotcloud.client.place.BasePlace;

public interface PagerPresenterFactory {
	PagerView.PagerPresenter get(BasePlace place, PagerView view);
}
