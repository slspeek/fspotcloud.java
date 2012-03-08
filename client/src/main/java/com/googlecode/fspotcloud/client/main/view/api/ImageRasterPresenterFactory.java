package com.googlecode.fspotcloud.client.main.view.api;

import com.googlecode.fspotcloud.client.place.BasePlace;

public interface ImageRasterPresenterFactory {
	ImageRasterView.ImageRasterPresenter get(BasePlace place, ImageRasterView view); 
}
