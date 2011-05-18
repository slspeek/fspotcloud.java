package fspotcloud.client.view;

import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;

public interface ImagePanelActivityFactory {

	ImagePanelPresenter getEmbedded(BasePlace place);

	ImagePanelPresenter getFullscreen(BasePlace place);
}
