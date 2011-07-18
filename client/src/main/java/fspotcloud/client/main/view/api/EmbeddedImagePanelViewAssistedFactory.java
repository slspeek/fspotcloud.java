package fspotcloud.client.main.view.api;

import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public interface EmbeddedImagePanelViewAssistedFactory {
	ImagePanelPresenter getEmbedded(BasePlace place);
}
