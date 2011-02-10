package fspotcloud.client.view;

import fspotcloud.client.view.ImageView.ImagePresenter;

public interface Slideshow {
	void toggleSlideshow();

	void stopSlideshow();

	public void setPresenter(ImagePresenter presenter);
}
