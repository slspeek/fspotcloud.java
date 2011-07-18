package fspotcloud.client.main.view.api;

import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public interface FullscreenImagePanelPresenterAssistedFactory {
	ImagePanelPresenter getFullscreen(BasePlace place);
}
