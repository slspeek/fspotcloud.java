package fspotcloud.client.main.view.api;

import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public interface ImagePanelPresenterAssistedFactory {
	ImagePanelPresenter get(BasePlace place);
}
