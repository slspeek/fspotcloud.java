package fspotcloud.client.main.view.api;

import fspotcloud.client.main.view.SlideshowControlsPresenter;

public interface SlideshowControlsPresenterFactory {
	SlideshowControlsPresenter get(ButtonPanelView view); 
}
