package fspotcloud.client.view;

import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;

public interface ImagePanelActivityFactory {
	
	ImagePanelPresenter get(BasePlace place);

}
