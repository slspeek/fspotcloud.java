package fspotcloud.client.main.view.api;

import fspotcloud.client.place.BasePlace;

public interface ImageRasterPresenterFactory {
	ImageRasterView.ImageRasterPresenter get(BasePlace place, ImageRasterView view); 
}
