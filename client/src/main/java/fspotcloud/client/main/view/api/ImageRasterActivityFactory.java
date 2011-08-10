package fspotcloud.client.main.view.api;

import fspotcloud.client.place.BasePlace;

public interface ImageRasterActivityFactory {
	ImageRasterView.ImageRasterPresenter get(BasePlace place); 
}
