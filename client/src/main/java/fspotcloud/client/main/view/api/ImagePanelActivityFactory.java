package fspotcloud.client.main.view.api;

import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public interface ImagePanelActivityFactory {

	ImagePanelPresenter getEmbedded(BasePlace place);

	ImagePanelPresenter getFullscreen(BasePlace place);
}
